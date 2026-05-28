package crm.opp;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Test Case: Create Opportunity in CRM Application
 * 
 * Author      : Piyush
 * Experience  : Automation Tester | 2 Years
 * Tool Stack  : Java + Selenium WebDriver
 * 
 * Objective:
 * Automate the end-to-end flow of creating a new Opportunity
 * in the CRM application and validate successful creation.
 * 
 * Test Flow:
 * 1. Launch Browser
 * 2. Login to CRM Application
 * 3. Navigate to Opportunities Module
 * 4. Create New Opportunity
 * 5. Select Related Organization
 * 6. Enter Opportunity Details
 * 7. Validate Opportunity Creation
 * 8. Close Browser
 */

public class CreateOppTest {

	public static void main(String[] args) throws InterruptedException {

		// ==============================
		// Browser Setup
		// ==============================

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// ==============================
		// Launch Application
		// ==============================

		driver.get("http://localhost:8888/");

		// ==============================
		// Login to CRM Application
		// ==============================

		driver.findElement(By.name("user_name")).sendKeys("admin");

		driver.findElement(By.name("user_password")).sendKeys("password");

		driver.findElement(By.id("submitButton")).click();

		System.out.println("Login successful");

		// ==============================
		// Navigate to Opportunities Module
		// ==============================

		driver.findElement(By.linkText("Opportunities")).click();

		// Click on Create Opportunity Icon
		driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();

		// ==============================
		// Create New Opportunity
		// ==============================

		// Generate unique Opportunity Name
		int randomNum = (int)(Math.random() * 1000);

		String expectedOpportunityName = "CRM Deal_" + randomNum;

		String expectedOrgName = "automationwithpiyush_0123";

		String expectedAmount = "50000";

		String expectedSalesStage = "Qualification";

		String expectedCloseDate = "2026-12-30";

		// Enter Opportunity Name
		driver.findElement(By.name("potentialname"))
		.sendKeys(expectedOpportunityName);

		// Enter Amount
		driver.findElement(By.name("amount"))
		.sendKeys(expectedAmount);

		System.out.println("Opportunity details entered successfully");

		// ==============================
		// Select Related To Type
		// ==============================

		Select relatedToType =
				new Select(driver.findElement(By.name("related_to_type")));

		relatedToType.selectByVisibleText("Organizations");

		// ==============================
		// Handle Organization Lookup Popup
		// ==============================

		// Capture Parent Window ID
		String parentWindow = driver.getWindowHandle();

		// Click Related Organization Lookup Icon
		driver.findElement(
				By.xpath("//input[@name='related_to_display']/following::img[1]"))
		.click();

		// Capture All Window IDs
		Set<String> allWindows = driver.getWindowHandles();

		// Switch Control to Child Window
		for (String window : allWindows) {

			if (!window.equals(parentWindow)) {

				driver.switchTo().window(window);

				break;
			}
		}

		// ==============================
		// Search Existing Organization
		// ==============================

		driver.findElement(By.name("search_text"))
		.sendKeys(expectedOrgName + Keys.ENTER);

		// Select Organization from Search Result
		driver.findElement(
				By.xpath("//a[text()='automationwithpiyush_0123']"))
		.click();

		System.out.println("Organization selected successfully");

		// ==============================
		// Switch Back to Parent Window
		// ==============================

		driver.switchTo().window(parentWindow);

		Thread.sleep(2000);

		// Verify Selected Organization
		String actualSelectedOrganization =
				driver.findElement(By.name("related_to_display"))
				.getAttribute("value");

		System.out.println("Selected Organization : "
				+ actualSelectedOrganization);

		// ==============================
		// Select Sales Stage
		// ==============================

		Select salesStage =
				new Select(driver.findElement(By.name("sales_stage")));

		salesStage.selectByVisibleText(expectedSalesStage);

		System.out.println("Sales stage selected successfully");

		// ==============================
		// Enter Expected Closing Date
		// ==============================

		driver.findElement(By.name("closingdate")).clear();

		driver.findElement(By.name("closingdate"))
		.sendKeys(expectedCloseDate);

		System.out.println("Expected close date entered successfully");

		// ==============================
		// Save Opportunity
		// ==============================

		driver.findElement(By.name("button")).click();

		System.out.println("Opportunity creation form submitted");

		Thread.sleep(3000);

		// ==============================
		// Validation
		// ==============================

		String actualOpportunityName =
				driver.findElement(By.className("dvHeaderText")).getText();

		if (actualOpportunityName.contains(expectedOpportunityName)) {

			System.out.println("PASS : Opportunity Created Successfully");

			System.out.println("Created Opportunity Name : "
					+ expectedOpportunityName);

		} else {

			System.out.println("FAIL : Opportunity Creation Failed");

			System.out.println("Expected : " + expectedOpportunityName);

			System.out.println("Actual   : " + actualOpportunityName);
		}

		// ==============================
		// Close Browser
		// ==============================

		driver.quit();

		System.out.println("Browser closed successfully");
	}
}