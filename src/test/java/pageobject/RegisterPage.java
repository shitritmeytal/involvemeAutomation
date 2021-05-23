package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends TopMenuDark{

	@FindBy (css = ".e-form-heading")
	private WebElement pageTitle;
	
	public RegisterPage(WebDriver driver) {
		super(driver);
	}

//	Action
	
//	Validation
	
	public boolean isCorrectPage(String title) {
		if (getText(pageTitle).equalsIgnoreCase(title)) {
			return true;
		}
		return false;
	}
}
