package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChooseProjectTypePage extends TopMenuDark{

	
	@FindBy (css = ".blank-center .icon")
	private WebElement startScratchBtn;
	
	public ChooseProjectTypePage(WebDriver driver) {
		super(driver);
	}

//	Actions
	
	public void clickStartScratch() {
		click(startScratchBtn);
	}
	
//	Validations
	
}
