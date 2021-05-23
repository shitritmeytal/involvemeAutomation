package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewFeaturPage extends TopMenuLight{
	
	@FindBy (css =" h1")
	private WebElement pageTitle;

	public NewFeaturPage(WebDriver driver) {
		super(driver);
	}

//	Action
	
//	Validation

	public String getTitle() {
		return getText(pageTitle);
	}
	public boolean isCorrectPage(String title) {
		if (getText(pageTitle).contains(title)) {
			return true;
		}
		return false;
	}
}
