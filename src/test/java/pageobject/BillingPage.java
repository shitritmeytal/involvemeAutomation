package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BillingPage extends TopMenuDark{

	@FindBy (css = ".e-title")
	private WebElement header;
	
	public BillingPage(WebDriver driver) {
		super(driver);
	}

	
//	Actions
	
//	Validations
	
	public String getHeaderText() {
		return getText(header);
	}
}
