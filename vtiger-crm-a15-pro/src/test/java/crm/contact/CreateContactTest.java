package crm.contact;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import generic_utility.FileUtility;

/**
 * Test Case: Create Contact in CRM Application
 * 
 * Author : Piyush Experience : Automation Tester | 2 Years Tool Stack : Java +
 * Selenium WebDriver
 * 
 * Objective: Automate the end-to-end flow of creating a new Contact in the CRM
 * application and validate successful creation.
 * 
 * Test Flow: 1. Launch Browser 2. Login to CRM Application 3. Navigate to
 * Contacts Module 4. Create New Contact 5. Validate Contact Creation 6. Close
 * Browser
 */

public class CreateContactTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

//		get data from json file
		FileUtility fUtil = new FileUtility();
		String BROWSER = fUtil.getDataFromJsonFile("bro");
		String URL = fUtil.getDataFromJsonFile("url");
		String USERNAME = fUtil.getDataFromJsonFile("un");
		String PASSWORD = fUtil.getDataFromJsonFile("pwd");
		
		
//		get data from excel
		String expectedLastName = fUtil.getDataFromExcelFile("contact", 1, 0);
		
		// ==============================
		// Browser Setup
		// ==============================

		WebDriver driver = null;
//		String BROWSER = "chrome";

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("safari")) {
			driver = new SafariDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// ==============================
		// Launch Application
		// ==============================

		driver.get(URL);

		// ==============================
		// Login to CRM Application
		// ==============================

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);

		driver.findElement(By.name("user_password")).sendKeys(PASSWORD + Keys.ENTER);

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

//		String expectedLastName = "Sharma" ;

		// Enter Mandatory Field
		driver.findElement(By.name("lastname")).sendKeys(expectedLastName);

		// Save Contact
		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();

		System.out.println("Contact creation form submitted");

		// ==============================
		// Validation
		// ==============================

		String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();

		if (actualLastName.equals(expectedLastName)) {

			System.out.println("PASS : Contact Created Successfully");

			System.out.println("Created Contact Last Name : " + actualLastName);
		} else {

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