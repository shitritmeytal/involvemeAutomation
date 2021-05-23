package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DraftPreviewPage extends BasePage{

	@FindBy (css = "div.preview-notice")
	private WebElement draftBtn;

	
	public DraftPreviewPage(WebDriver driver) {
		super(driver);
	}
	
//	Actions
	
	
//	Validation
	
	public boolean isDraftExist() {
		if (draftBtn.isDisplayed()) {
			return true;
			
		}
		return false;
	}
}
