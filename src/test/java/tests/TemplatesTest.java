package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.EditorPage;
import pageobject.ProjectsPage;
import pageobject.TemplatesPage;
import pageobject.WelcomeSurveyPrevPage;

public class TemplatesTest extends BaseTest{
	
	@Test (description = "Verify correct page")
	public void tc01_verifyCorrectPage() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.clickTemplates();
		TemplatesPage tp = new TemplatesPage(driver);
		String expected = "Templates";
		String actual = tp.getPageTitle();
		Assert.assertEquals(actual, expected);
	}
	
//	Verify that the number on the badge compatible to the list item size - single category
	@Test (description = "Verify badge value to item list size - use category name")
	public void tc02_verifyBadgeList() {
		TemplatesPage tp = new TemplatesPage(driver);
		String categoryName = "Form";
		// verify category Badge Match the number of templates	
		Assert.assertTrue(tp.isBadgeMatchItems(categoryName)); 
	}
	
//	Check view all category button(page bottom)- should lead to all items
	@Test (description = "Verify 'View All' button ")
	public void tc03_viewAllBtn() {
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickViewAll();
		tp.sleep(1000);		
		Assert.assertTrue(tp.isAllSelected());
	}

//	Check 'Choose' button on template-should lead to 'Choose Project Type'
	@Test (description = "Verify 'Choose' button ")
	public void tc04_verifyChooseBtn() {
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickCategory("Survey");
		String pTitle = "Welcome Survey"; //choose the right survey by name
		tp.clickChooseItem(pTitle);
		EditorPage ep = new EditorPage(driver);
		String actual = ep.getNewPHeader();
		String expected = "Choose Project Type";
		Assert.assertEquals(actual, expected); // Verify the header
		Assert.assertTrue(ep.isDefaultPNRight(pTitle));  // verify that the 'default project name' match the 'project name'
	}
	
//	Check 'Preview' button on item-should lead to 'Preview Template'
	@Test (description = "Verify 'Preview' button")
	public void tc05_verifyPreviewButton() {
		EditorPage ep = new EditorPage(driver);
		ep.navigateBack();
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickCategory("Survey");
		String pTitle = "Welcome Survey";  //choose the right survey by name
		tp.clickPreviewItem(pTitle);
		WelcomeSurveyPrevPage wspp = new WelcomeSurveyPrevPage(driver);
		Assert.assertTrue(wspp.isUseTempDisplay()); // preview page - 'Use Template' button should be displayed
	}
	
//	--------  Tests for Preview Page  -------------
	
//	Check 'Use Template' button
	@Test (description = "Verify 'Use Template' button - preview page")
	public void tc06_verifyUseTemp() {
		WelcomeSurveyPrevPage wspp = new WelcomeSurveyPrevPage(driver);
		wspp.clickUseTemp();
		EditorPage ep = new EditorPage(driver);
		String actual = ep.getNewPHeader();
		String expected = "Choose Project Type";
		Assert.assertEquals(actual, expected);
		String pTitle = "Welcome Survey"; 
		Assert.assertTrue(ep.isDefaultPNRight(pTitle));
	}
	
//	 Check 'BackWards' arrow -  "Welcome Survey"
	@Test (description = "Verify 'BackWards' button - preview page")
	public void tc07_verifyBackArrow() {
		// get back to the preview page
		EditorPage ep = new EditorPage(driver);  
		ep.navigateBack();
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickCategory("Survey");
		String pTitle = "Welcome Survey";
		 //click preview item by name
		tp.clickPreviewItem(pTitle);
		//'Welcome Survey' preview page
		WelcomeSurveyPrevPage wspp = new WelcomeSurveyPrevPage(driver); 
		wspp.clickGetStarted();
		 // get the question before
		String expected= wspp.getFirstQuesText();
		// click option to move forwards
		wspp.clickFirstOption(); 
		// click Backwards arrow - should lead to prev' question
		wspp.clickBackwards();   
		// get the question after returning back and compare
		String actual = wspp.getFirstQuesText(); 
		Assert.assertEquals(actual, expected);
	}
	
//	Check 'Close' button
	@Test (description = "Verify 'Close' button - preview page")
	public void tc08_verifyCloseBtn() {
		WelcomeSurveyPrevPage wspp = new WelcomeSurveyPrevPage(driver);
		wspp.clickClose();  // when closing preview page - should lead back to templates - All selected
		TemplatesPage tp = new TemplatesPage(driver);
		Assert.assertTrue(tp.isAllSelected()); 
	}
}

