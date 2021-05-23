package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends TopMenuDark{

	@FindBy (css = "[name='email']")
	private WebElement emailField;
	
	@FindBy (css = "[name='password']")
	private WebElement passwordField;
	
	@FindBy (css = ".btn-lg")
	private WebElement loginBtn;
	
	@FindBy (css = ".form-group a:nth-child(1)")
	private WebElement forgotPassLink;
	
	@FindBy (css = ".form-group a:nth-child(2)")
	private WebElement createAccountLink;
	
	@FindBy (css = ".btn-secondary")
	private WebElement seeHowWorkBtn;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
//	Actions
	public void login(String email, String password) {
		fillText(emailField, email);
		fillText(passwordField, password);
		click(loginBtn);
	}
	
	public void clickForgotPass() {
		click(forgotPassLink);
	}
	
	public void clickCreateAccount() {
		click(createAccountLink);
	}
	
	public void clickSeeHowItWork() {
		click(seeHowWorkBtn);
	}
	
	
	
//	Validation 
	
	public String getEmailErrnMsg() {
		String errorMessage = emailField.getAttribute("validationMessage");
		return errorMessage;
	}

	public String getNullpassErrnMsg() {
		String errorMessage = passwordField.getAttribute("validationMessage");
		return errorMessage;
	}
	
	public String getPasswordErrnMsg() {
		String errorMessage =getText(driver.findElement(By.cssSelector(".alert-danger")));
		return errorMessage;
	}
	
	
}
