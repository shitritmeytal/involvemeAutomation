package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends TopMenuLight{

	@FindBy (css = ".login")
	private WebElement loginBtn;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

//	Actions
	public void clickLogin() {
		click(loginBtn);
	}
}
