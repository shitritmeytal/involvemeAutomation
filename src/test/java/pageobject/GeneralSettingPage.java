package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GeneralSettingPage extends TopMenuDark{

	@FindBy (css = ".justify-between.items-center > button")
	private WebElement publishBtn;
	
	@FindBy (css = "#confirm-publish-button ")
	private WebElement publishNowBtn;
	
	@FindBy (css = "p.block>a")
	private WebElement warningMsg;
	
	public GeneralSettingPage(WebDriver driver) {
		super(driver);
	}
	
//	Action
	
	public void clickPublish() {
		click(publishBtn);
		sleep(1000);
		clickPublishNow();
	}
	
	private void clickPublishNow() {
		click(publishNowBtn);
	}
	
	
//	Validations
	
	public String getPublishWarningMsg() {
		return getText(warningMsg);
	}


}
