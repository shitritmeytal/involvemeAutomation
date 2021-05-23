package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GetStartedTutrPage extends TopMenuLight {

	@FindBy (css = ".inner.text-center>h1")
	private WebElement header;
	
	public GetStartedTutrPage(WebDriver driver) {
		super(driver);
	}

//	Actions
	
//	Validations
	
	public String getHeader() {
		return getText(header);
	}
	
}
