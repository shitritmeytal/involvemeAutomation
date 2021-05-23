package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomeSurveyPrevPage extends PreviewTopMenu{

	@FindBy (css = ".c-button.btn")
	private WebElement getStartBtn;
	
	@FindBy (css = ".c-question span")
	private WebElement firstQ;
	
	public WelcomeSurveyPrevPage(WebDriver driver) {
		super(driver);
	}

//	Actions 
	
	public void clickGetStarted() {
		click(getStartBtn);
	}
	
	public void clickFirstOption() {
		List<WebElement> ansList = driver.findElements(By.cssSelector(".wrapper.align-left button"));
		click(ansList.get(0));
	}
	
//	Validation
	
	public String getFirstQuesText() {
		return getText(firstQ);
	}
}
