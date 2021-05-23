package pageobject;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectsPage extends TopMenuDark{

	@FindBy (css = ".items-center>h1")
	private WebElement subTitle;

	@FindBy (css = ".block.items-baseline a:nth-child(2)")
	private WebElement draftLink;

	@FindBy (css = " .dropdown")
	private WebElement dropDownBtn;

	@FindBy (css = "ul>li:nth-child(9) > button")
	private WebElement deleteProjectBtn;

	@FindBy (css = "#confirm-delete-button")
	private WebElement deleteBtn;

	@FindBy (css = ".dropdown.relative.mr-3")
	private WebElement workspaceArrBtn;

	@FindBy (css = "form div input")
	private WebElement projectNameField;

	@FindBy (css = "#confirm-create-button")
	private WebElement confirmBtn;

	@FindBy (css = ".flex.justify-between>button")
	private WebElement plusWorkspaceBtn;

	@FindBy (css = ".flex>span>img")
	private WebElement userBtn;

	@FindBy (css = "#user-email")
	private WebElement userEmailField;

	@FindBy (css = "button.relative")
	private WebElement inviteBtn;

	@FindBy (css = ".ml-2.text-red-600")
	private WebElement availbleSeatsErrMsg;

	@FindBy (css = ".text-sm.rounded-b> a")
	private WebElement upgradeLink;

	@FindBy (css = " h1.block")
	private WebElement emptyWorkSpaceHeader;

	@FindBy (css = "input[type='text']")
	private WebElement verifyFieldWPDelete;

	@FindBy (css = ".justify-between.items-center > a")
	private WebElement createProBtn;

	@FindBy (css = " form > div.p-6.md\\:p-8 > select")
	private WebElement selectWorkspace;



	public ProjectsPage(WebDriver driver) {
		super(driver);
	}

//	Action

	// Rename workspace
	public void renameWorkspace(String newName) {
		clickWorkspaceArrBtn();
		chooseDropDownOptionWS("Rename Workspace");
		fillText(projectNameField, newName);
		click(confirmBtn);
		waitUntilTextPresent(subTitle, newName);
	}

	// Sub method -- renameWorkspace(String newName) -- Click workspace drop down button 
	private void clickWorkspaceArrBtn() {
		click(workspaceArrBtn);
	}
	
	// Sub method --> Choose option from  ***Workspace*** drop down list by option name
		private void chooseDropDownOptionWS(String option) {
			List<WebElement> list = driver.findElements(By.cssSelector(".dropdown.relative.mr-3 li"));
			for (WebElement el : list) {
				if (getText(el).equalsIgnoreCase(option)) {
					click(el);
					break;
				}
			}
		}

	//Add new workspace
	public void addNewWorkspace(String name) {
		click(plusWorkspaceBtn);
		fillText(projectNameField,name);
		click(confirmBtn);
		waitUntilTextPresent(subTitle, name);
	}

	// Delete workspace - when workspace is open 
	public void deleteWorkspace() {
		String workSpaceName = getText(subTitle);
		clickWorkspaceArrBtn();
		// click delete workspace 
		chooseDropDownOptionWS("Delete Workspace"); 
		// delete verification
		fillText(verifyFieldWPDelete, workSpaceName); 
		click(confirmBtn);
		sleep(2000);
	}

	//Invite user
	public void invitUser(String email) {
		click(userBtn);
		fillText(userEmailField, email);
		click(inviteBtn);
	}

	public void clickUpgradeLink() {
		click(upgradeLink);
	}

	// click out of window
	public void clickOutWin(int x,int y) {
		moveOutOfElement(x, y);
	}

	public void clickCreatePro() {
		click(createProBtn);
	}


	//choose workspace from left menu 
	public void chooseWorkspace(String workspaceName) {
		List<WebElement> list = driver.findElements(By.cssSelector("span.mr-3.truncate")); // list of workspaces name
		for (WebElement el : list) {
			if (getText(el).equalsIgnoreCase(workspaceName)) {
				click(el);
				break;
			}
		}
	}

	//Main method - move project from one workspace to another
	public void moveProToWorkspace(String fromWorkspace, String toWorkspace, String projectName) {
		chooseWorkspace(fromWorkspace);
		clickMoveToWorspace(projectName);
		selectWorspace(toWorkspace);
		sleep(2000);
	}

	//First locate the right project by name and then choose move option from drop down list
	private void clickMoveToWorspace(String projectName) {
		List<WebElement> list = driver.findElements(By.cssSelector("div>.relative.flex.flex-col")); // List of projects
		for (WebElement el : list) {
			String name = getText(el.findElement(By.cssSelector(" h1>a")));
			if (name.equalsIgnoreCase(projectName)) {
				// click drop down button
				click(el.findElement(By.cssSelector(".dropdown"))); 
				// choose move to workspace option
				chooseDropDownOptionProject("Move to workspace"); 
				break;
			}
		}
	}

	// Sub method -- Choose option from  ***Project*** drop down list by option name
	private void chooseDropDownOptionProject(String option) {
		List<WebElement> list = driver.findElements(By.cssSelector(" div.relative.flex.border-blue-600 .items-center ul > li"));
		for (WebElement el : list) {
			if (getText(el).equalsIgnoreCase(option)) {
				click(el);
				break;
			}
		}
	}

	// select workspace from drop down list
	private void selectWorspace(String workspaceName) {
		click(selectWorkspace);
		List<WebElement> list = driver.findElements(By.cssSelector(" form div select>option")); // list of workspaces - drop down
		for (WebElement el : list) {
			if (getText(el).equalsIgnoreCase(workspaceName)) {
				click(el);
				click(confirmBtn);
				break;
			}
		}
	}


	// Delete project by project name(workspace is open)
	public void deleteProject(String projectName) {
		List<WebElement> list = driver.findElements(By.cssSelector("div>.relative.flex.flex-col")); // List of projects
		for (WebElement el : list) {
			String name = getText(el.findElement(By.cssSelector(" h1>a")));
			if (name.equalsIgnoreCase(projectName)) {
				click(el.findElement(By.cssSelector(".dropdown")));
				chooseDropDownOptionProject("Delete Project");
				click(deleteBtn);
				waitUntilElementIsVisibleOff(el); // wait until the project is no longer visible
				break;
			}
		}
	}

//	Validation

	public String getSubTitle() {
		return getText(subTitle);
	}

	public String getAvailbleSeatsErrMsg() {
		return getText(availbleSeatsErrMsg);
	}

	public boolean isProjectEmpty() {
		if (emptyWorkSpaceHeader.isDisplayed()) {
			return true;
		}
		return false;
	}

	public boolean isWorspaceExist(String wSName) {
		List<WebElement> list = driver.findElements(By.cssSelector("span.mr-3.truncate")); // list of workspaces name
		for (WebElement el : list) {
			if (getText(el).equalsIgnoreCase(wSName)) {  // means that the workspace exist
				return true;
			}
		}
		return false;
	}

	public boolean isProjectExist(String projectName) {
		// list of projects name elements
		List<WebElement> list = driver.findElements(By.cssSelector("div>.relative.flex.flex-col  h1>a")); 
		for (WebElement el : list) {
			if (getText(el).equalsIgnoreCase(projectName)) {
				return true;
			}
		}
		return false;
	}
	
	// Locating the right workspace and return int number of item
		public int getWSProNum(String wSName) {
			int itemNum = 0;
			List<WebElement> list = driver.findElements(By.cssSelector("div.leading-loose a")); // list of workspaces
			for (WebElement el : list) {
				String elName = getText(el.findElement(By.cssSelector("span:nth-child(1)"))); // get the workspace name
				if (elName.equalsIgnoreCase(wSName)) { 
					String elQuant = getText(el.findElement(By.cssSelector("span:nth-child(2)"))); // get the item number
					itemNum = Integer.parseInt(elQuant); //turn String into integer
					break;
				}
			}
			return itemNum;
		}

		public void waitUntilProjectPresent() {
			waitUntilElementIsVisible(subTitle);
		}


}
