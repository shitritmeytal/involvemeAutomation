package pageobject;

import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

	WebDriver driver;
	private Actions a;
	WebDriverWait wait;
	private String mainWindow;
	Select select;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		a = new Actions(this.driver);
		wait = new WebDriverWait(driver, 10);
	}

	//	Actions

	// Basic actions  - Selenium
	void fillText(WebElement el, String text) {
		waitUntilElementIsVisible(el);
		highlightElement(el, "green");
		el.clear();
		el.sendKeys(text);
	}

	void click(WebElement el) {
		waitUntilElementIsClickable(el);
		highlightElement(el, "orange");
		el.click();
	}

	String getText(WebElement el) {
		waitUntilElementIsVisible(el);
		highlightElement(el, "blue");
		return el.getText();
	}

	public void sleep(long millisec) {
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// --------------- Browser---------------
	public void navigateBack() {
		driver.navigate().back();
	}

	public void refresh() {
		driver.navigate().refresh();
	}


	// -------------- Window Handle --------------

	// move to new window 
	public void moveToNewWin() {
		mainWindow = driver.getWindowHandle();
		Set<String> list = driver.getWindowHandles();
		for (String win : list) {
			driver.switchTo().window(win);
		}
	}

	// move back to the main window 
	public void moveBackToMain() {
		driver.switchTo().window(mainWindow);
	}

	// close window
	public void closeWindow() {
		driver.close();
	}


	//	-------------- Select ------------

	//By value
	public void selectByValue(WebElement el , String value) {
		click(el);
		sleep(2000);
		select.selectByValue(value);
	}

	//By visible text
	public void selectByText(WebElement el , String text) {
		click(el);
		sleep(2000);
		select.selectByVisibleText(text);
	}


	//	-------------- Mouse --------------

	//move to element
	public void moveToElement(WebElement el) {
		a.moveToElement(el).build().perform();  
	}

	//Move by offset - coordinates - and click
	public void moveOutOfElement(int x, int y) {
		a.moveByOffset(x , y ).click().build().perform(); 
	}


	//	--------------Alerts ---->	

	//Ok
	public void alertOK() {
		driver.switchTo().alert().accept();
	}

	//Send keys
	public void alertOK(String text) {
		driver.switchTo().alert().sendKeys(text);
		driver.switchTo().alert().accept();
	}

	//Cancel
	public void alertCancel() {
		driver.switchTo().alert().dismiss();
	}

	//	--------------Wait ---->

	//Wait until element is visible
	public void waitUntilElementIsVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}


	//Wait until element is invisible 
	public void waitUntilElementIsVisibleOff(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}


	//Wait until element is clickable
	public void waitUntilElementIsClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// Wait until text appears
	public void waitUntilTextPresent(WebElement element, String text) {
		wait.until(ExpectedConditions.textToBePresentInElement(element,text));
	}


	// -------------- Highlight Elements --------------

	private void highlightElement(WebElement element, String color) {
		//keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "background-color: yellow; border: 1px solid " + color + ";" + originalStyle;
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Change the style 
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
				element);

		// Change the style back after few miliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);

	}



}
