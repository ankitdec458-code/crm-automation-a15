package generic_utility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	WebDriver driver = null ;

	public WebDriverUtility(WebDriver driver) {
//		to differentiate between local and global variable names we use this for global
		this.driver = driver;
	}
	
//	hover
	public void hover(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

//	right click
	public void righClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
	}

//	explicit wait for element to be clickable
	public void waitForEleToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitAndClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
}
