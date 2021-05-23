package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HelloYellowPrevPage extends PreviewTopMenu{

	@FindBy (css = ".e-headline.is-shrinkable  span")
	private WebElement formHeader;
	
	@FindBy (css = ".input-label.firstName-label>input")
	private WebElement fNameField;
	
	@FindBy (css = ".lastName-label>input")
	private WebElement lNameField;
	
	@FindBy (css = ".email-label>input")
	private WebElement emailField;

	@FindBy (css = ".modal-main>div:nth-child(5) .el-checkbox__input")
	private WebElement agreeCheckBox;

	@FindBy (css = ".c-button.btn")
	private WebElement nextBtn;
	
	@FindBy (css = ".form-item.error .error-txt")
	private WebElement errMsg;
	
	@FindBy (css = "div.e-headline.is-shrinkable")
	private WebElement thankMsg;
	
	
	public HelloYellowPrevPage(WebDriver driver) {
		super(driver);
	}

//	Actions
	
	public void fillRegForm(String fName, String lName, String email) {
		fillText(fNameField, fName);
		fillText(lNameField, lName);
		fillText(emailField, email);
	}
	
	public void clickAgreeCheckBox() {
		String getStat = agreeCheckBox.getAttribute("class");
		if (!getStat.contains("is-checked")) {
			click(agreeCheckBox);
		}
	}
	
	public void unClickAgreeCheckBox() {
		String getStat = agreeCheckBox.getAttribute("class");
		if (getStat.contains("is-checked")) {
			click(agreeCheckBox);
		}
	}
	
	public void clickNext() {
		click(nextBtn);
	}
	
//	Validations
	
	public String getHeaderText() {
		return getText(formHeader);
	}
	
	public String getErrMsg() {
		return getText(errMsg);
	}
	
	public String getThankYouMsg() {
		return getText(thankMsg);
	}
	
}
