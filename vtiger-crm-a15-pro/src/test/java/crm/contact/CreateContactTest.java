package crm.contact;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Test Case: Create Contact in CRM Application
 * 
 * Author      : Piyush
 * Experience  : Automation Tester | 2 Years
 * Tool Stack  : Java + Selenium WebDriver
 * 
 * Objective:
 * Automate the end-to-end flow of creating a new Contact
 * in the CRM application and validate successful creation.
 * 
 * Test Flow:
 * 1. Launch Browser
 * 2. Login to CRM Application
 * 3. Navigate to Contacts Module
 * 4. Create New Contact
 * 5. Validate Contact Creation
 * 6. Close Browser
 */

public class CreateContactTest {

	public static void main(String[] args) {

		// ==============================
		// Browser Setup
		// ==============================

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// ==============================
		// Launch Application
		// ==============================

		driver.get("http://localhost:8888");

		// ==============================
		// Login to CRM Application
		// ==============================

		driver.findElement(By.name("user_name")).sendKeys("admin");

		driver.findElement(By.name("user_password"))
		.sendKeys("password" + Keys.ENTER);

		System.out.println("Login successful");

		// ==============================
		// Navigate to Contacts Module
		// ==============================

		driver.findElement(By.linkText("Contacts")).click();

		// Click on Create Contact Icon
		driver.findElement(By.cssSelector("img[title='Create Contact...']")).click();

		// ==============================
		// Create New Contact
		// ==============================

		// Generate unique Contact Last Name
		int random = (int)(Math.random()*1000);

		String expectedLastName = "Sharma_" + random;

		// Enter Mandatory Field
		driver.findElement(By.name("lastname")).sendKeys(expectedLastName);

		// Save Contact
		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();

		System.out.println("Contact creation form submitted");

		// ==============================
		// Validation
		// ==============================

		String actualLastName =
				driver.findElement(By.id("dtlview_Last Name")).getText();

		if(actualLastName.equals(expectedLastName)) {

			System.out.println("PASS : Contact Created Successfully");

			System.out.println("Created Contact Last Name : " + actualLastName);
		}
		else {

			System.out.println("FAIL : Contact Creation Failed");

			System.out.println("Expected : " + expectedLastName);

			System.out.println("Actual   : " + actualLastName);
		}

		// ==============================
		// Close Browser
		// ==============================

		driver.quit();

		System.out.println("Browser closed successfully");
	}
}