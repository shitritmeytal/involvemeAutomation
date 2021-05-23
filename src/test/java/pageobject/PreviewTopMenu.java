package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class PreviewTopMenu extends BasePage{

	@FindBy (css = ".e-use-template.float-right")
	private WebElement useTempBtn;
	
	@FindBy (css = ".flex.c-buttonGroup li:nth-child(1)")
	private WebElement backwardsArrowBtn;
	
	@FindBy (css = ".e-close>img")
	private WebElement closeBtn;
	
	@FindBy (css = "div.e-try")
	private WebElement tryItBtn;
	
	public PreviewTopMenu(WebDriver driver) {
		super(driver);
	}

//	Action
	
	public void clickUseTemp() {
		click(useTempBtn);
	}
	
	public void clickBackwards() {
		click(backwardsArrowBtn);
	}
	
	public void clickClose() {
		click(closeBtn);
	}
	
	public void clickTryIt() {
		click(tryItBtn);
	}
	
//	Validation
	
	public boolean isUseTempDisplay() {
		if (useTempBtn.isDisplayed()) {
			return true;
		}
		return false;
	}
	

}
