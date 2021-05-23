package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject.DraftPreviewPage;
import pageobject.EditorPage;
import pageobject.GeneralSettingPage;
import pageobject.ProjectsPage;
import pageobject.TemplatesPage;
import pageobject.WelcomeSurveyPrevPage;

public class EditorTest extends BaseTest{

	@Test (description = "Create new project - verify project name")
	public void tc01_newProjectName() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.clickTemplates();
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickCategory("Survey");
		 // choose survey to edit
		tp.clickChooseItem("Welcome Survey");
		EditorPage ep = new EditorPage(driver);
		String projectName ="My First Project";
		//Insert project name and choose type
		ep.setProject(projectName); 
		String actual = ep.getProjectName();
		Assert.assertEquals(projectName.toUpperCase(), actual);
	}

	@Test (description = "Add a new page to the project")
	public void tc02_addNewPage() {
		EditorPage ep = new EditorPage(driver);
		int pagesBefore = ep.getPageNum();
		ep.clickAddPage();
		int pagesAfter = ep.getPageNum();
		//Compare the number of pages before and after(-1) 
		Assert.assertEquals(pagesBefore, pagesAfter -1); 
		String actual = ep.getEmptyTitle();
		String expected  = "drag & drop";
		 // Verify new Page open by title
		Assert.assertEquals(actual, expected);
	}

	@Test (description = "Delete page")
	public void tc03_deletePage() {
		EditorPage ep = new EditorPage(driver);
		int pagesBefore = ep.getPageNum();
		String pageName = "5";
		ep.deletePage(pageName);
		int pagesAfter = ep.getPageNum();
		//Compare the number of pages before(-1) and after 
		Assert.assertEquals(pagesBefore -1, pagesAfter); 
		// verify page is no longer exist in the pages list
		Assert.assertFalse(ep.isPageExist(pageName));  
	}

	@Test (description = "Edit page sub title")
	public void tc04_editPageName() {
		EditorPage ep = new EditorPage(driver);
		String pageNumber = "3";
		String subTitle = "Sub Title";
		// enter the sub title by page number
		ep.editPageName(pageNumber, subTitle);
		// get the actual sub title by page number
		String actualSubTitle = ep.getSubTitle(pageNumber); 
		Assert.assertEquals(actualSubTitle, subTitle);
	}

	@Test (description = "duplicate page")
	public void tc05_duplicatePage() {
		EditorPage ep = new EditorPage(driver);
		int pagesBefore = ep.getPageNum();
		String pageNumber = "3";
		// get original sub title in order to compare the copy page sub title
		String mainSub = ep.getSubTitle(pageNumber);
		 //duplicate page by number(String)
		ep.duplicatePage(pageNumber); 
		int pagesAfter = ep.getPageNum();
		//Compare the number of pages before and after(-1) 
		Assert.assertEquals(pagesBefore, pagesAfter -1);
		 // get the sub title of the next page witch should be the copy page
		String actual = ep.getSubTitle("4");
		// Default sub title structure
		String expected = "(copy)"+mainSub; 
		Assert.assertEquals(actual, expected);
	}
	
	@Test (description = "Try it - preview")
	public void tc06_tryItPreview() {
		EditorPage ep = new EditorPage(driver);
		ep.clickDesignPreview();
		WelcomeSurveyPrevPage wsp = new WelcomeSurveyPrevPage(driver);
		wsp.clickTryIt();
		wsp.moveToNewWin();
		DraftPreviewPage dpp = new DraftPreviewPage(driver);
		Assert.assertTrue(dpp.isDraftExist());
		dpp.closeWindow();
		wsp.moveBackToMain();
		
	}

	// Warning message should appear, but "publish anyway" button should be clickable
	@Test (description = "Publish anyway - check first warning")
	public void tc07_publishFirstWarning() {
		EditorPage ep = new EditorPage(driver);
		ep.clickAddPage();
		ep.clickPublish();
//		ep.sleep(2000);
		String expected = "Some pages have no link to the next page";
		String actual = ep.getNoLinkErr();
		Assert.assertEquals(actual, expected);
	}
	
	// Another warning message should appear again
	@Test (description = "Publish anyway - check second warning")
	public void tc08_publishSecondWarning() {
		EditorPage ep = new EditorPage(driver);
		ep.clickPublishAnyway();
		GeneralSettingPage gsp = new GeneralSettingPage(driver);
		gsp.clickPublish();
		gsp.sleep(1000);
		String actual = gsp.getPublishWarningMsg();
		String expected = "Some pages have no link to the next page";
		Assert.assertEquals(actual, expected);
	}
}
