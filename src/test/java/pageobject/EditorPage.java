package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditorPage extends BasePage{


	@FindBy (css = ".new-project-modal>.swal-title")
	private WebElement newProjectTitle;

	@FindBy (css=".dropdown.nav-item")
	private WebElement helpBtn;

	@FindBy (css = "div.info-drag strong")
	private WebElement emptyPageTitle;

	@FindBy (css = "button.swal-button--danger")
	private WebElement deleteAlertBtn;

	@FindBy (css = "#project-title")
	private WebElement pageNameField;

	@FindBy (css = ".swal-button--confirm")
	private WebElement saveBtn;

	@FindBy (css = "input#project-name")
	private WebElement projectNameField;

	@FindBy (css = ".nav-link.project-name")
	private WebElement projectName;

	@FindBy (css = ".save-btn")
	private WebElement publishBtn;

	@FindBy (css = ".preview-btn")
	private WebElement designPrevBtn;

	@FindBy (css = "div.swal-title")
	private WebElement emptyPagesErrMsg;

	@FindBy (css = ".swal-button--publishErr")
	private WebElement publishAnywayBtn;

	@FindBy (css = "div.info-drag")
	private WebElement emptyPage;

	@FindBy (css = ".e-close.nav-link")
	private WebElement saveExitBtn;
	
	@FindBy (css = "#tab1contentitems > div > div:nth-child(1) > div.c-menu-button.content-menu-item")
	private WebElement addBtn;

	public EditorPage(WebDriver driver) {
		super(driver);
	}


//	Actions

	public void setProject(String name) {
		fillProjectName(name);
		clickHighlightOption();
	}

	// Sub method -- setProject(String name)-- insert project name
	private void fillProjectName(String name) {  
		fillText(projectNameField, name);
	}

	// Sub method -- setProject(String name)-- choose project type(default)
	private void clickHighlightOption() {
		List<WebElement> list = driver.findElements(By.cssSelector("label .modal-btn-start.swal-button"));
		for (WebElement el : list) {
			if (el.isDisplayed()) {
				click(el);
				break;
			}
		}
	}

	public void clickAddPage() {
		List<WebElement> list = driver.findElements(By.cssSelector(".add-page-container .fa-plus"));
		click(list.get(list.size()-1));
	}

	public void deletePage(String pageName) {
		// Create list of pages
		List<WebElement> list = driver.findElements(By.cssSelector("div.editable-pages-container .e-page-item")); 
		for (WebElement el : list) {
			//get page title 
			String title = getText(el.findElement(By.cssSelector(" p"))); 
			if (title.equalsIgnoreCase(pageName)) {
				moveToElement(el);
				// click delete icon
				click(el.findElement(By.cssSelector(" .js-remove-page"))); 
				break;
			}
		}
		click(deleteAlertBtn); //click delete button ---> confirm message
	}

	public void editPageName(String pageNumber, String subTitle) {  
		List<WebElement> list = driver.findElements(By.cssSelector("div.editable-pages-container .e-page-item")); // list of pages
		for (WebElement el : list) {
			String pageNum = getText(el.findElement(By.cssSelector(" p:nth-child(1)")));
			if (pageNum.equalsIgnoreCase(pageNumber)) {
				moveToElement(el);
				click(el.findElement(By.cssSelector(" .js-edit-page"))); 
				break;
			}
		}
		fillText(pageNameField, subTitle);
		click(saveBtn);
	}

	public void duplicatePage(String pageNum) {
		List<WebElement> list = driver.findElements(By.cssSelector("div.editable-pages-container .e-page-item")); // list of pages
		for (WebElement el : list) {
			String pageNumber = getText(el.findElement(By.cssSelector(" p:nth-child(1)")));
			if (pageNumber.contentEquals(pageNum)) {
				moveToElement(el);
				//click duplicate icon
				click(el.findElement(By.cssSelector(" .js-duplicate-page"))); 
				break;
			}
		}
	}

	public void clickPublish() {
		click(publishBtn);
	}

	public void clickDesignPreview() {
		click(designPrevBtn);
	}

	public void clickPublishAnyway() {
		click(publishAnywayBtn);
	}
	
	public void clickSaveExit() {
		click(saveExitBtn);
	}
	
	
	
//	Validations

	// return the number of pages in the project
	public int getPageNum() {
		List<WebElement> list = driver.findElements(By.cssSelector("div.editable-pages-container .e-page-item"));  // get pages list
		return list.size(); 
	}

	// return empty page title
	public String getEmptyTitle() {
		return getText(emptyPageTitle);
	}
	
	// return boolean ---> true/false if page exist in the pages list
	public boolean isPageExist(String pageName) {
		List<WebElement> list = driver.findElements(By.cssSelector("div.editable-pages-container .e-page-item p"));// get pages nmaes list
		for (WebElement el : list) {
			if (getText(el).equalsIgnoreCase(pageName)) {
				return true;
			}
		}
		return false;
	}
	
	// return sub title by page number
	public String getSubTitle(String pageNum) {
		List<WebElement> list = driver.findElements(By.cssSelector("div.editable-pages-container .e-page-item")); // list of pages
		String subTitle = " ";
		for (WebElement el : list) {
			String pageNumber = getText(el.findElement(By.cssSelector(" p:nth-child(1)")));  
			if (pageNumber.equalsIgnoreCase(pageNum)) {
				subTitle = getText(el.findElement(By.cssSelector(" p:nth-child(2)")));
				break;
			}
		}
		return subTitle;
	}

	//return error message - publish project with empty pages
	public String getNoLinkErr() {
		return getText(emptyPagesErrMsg);
	}
	
	
	// return boolean for the default project name 
	public boolean isDefaultPNRight(String projectName) {
		String proName = projectNameField.getAttribute("value");
		if (projectName.equalsIgnoreCase(proName)) {
			return true;
		}
		return false;
	}

	// return the new project window header text
	public String getNewPHeader() {
		return getText(newProjectTitle);
	}

	//return project name
	public String getProjectName() {
		return getText(projectName);
	}

}
