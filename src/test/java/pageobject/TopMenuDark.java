package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class TopMenuDark extends BasePage{

	@FindBy (css = ".nav.navbar-nav li:nth-child(1)>a")
	private WebElement loginBtn;
	
	@FindBy (css = "#nav-dropdown .text-xs")
	private WebElement userName;
	
	@FindBy (css = ".flex.items-start a:nth-child(2)")
	private WebElement templatesBtn;
	
	public TopMenuDark(WebDriver driver) {
		super(driver);
	}
	
//	Actions 
	
	public void clickLogin() {
		click(loginBtn);
	}
	
	public void clickTemplates() {
		click(templatesBtn);
	}
	
//	Validation
	
	public String getUserName() {
		return getText(userName);
	}

}
