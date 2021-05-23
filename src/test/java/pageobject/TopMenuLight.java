package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class TopMenuLight extends BasePage{


	@FindBy (css = ".login")
	private WebElement loginBtn;
	
	public TopMenuLight(WebDriver driver) {
		super(driver);
	}
	
//	Actions
	public void clickLogin() {
		click(loginBtn);
	}

}
