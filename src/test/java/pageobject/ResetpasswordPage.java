package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResetpasswordPage extends TopMenuDark{

	@FindBy (css = "[name='email']")
	private WebElement emailField;
	
	@FindBy (css = ".btn-primary")
	private WebElement sendBtn;
	
	public ResetpasswordPage(WebDriver driver) {
		super(driver);
	}
	
//	Action 
	
	public void sendRequest(String email) {
		fillText(emailField, email);
		click(sendBtn);
	}
	
//	Validation
	
	public String getConfirmMsg() {
		String msg = getText(driver.findElement(By.cssSelector(".alert-success")));
		return msg;
	}

}
