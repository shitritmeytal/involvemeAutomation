package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobject.HelloYellowPrevPage;
import pageobject.ProjectsPage;
import pageobject.TemplatesPage;

public class FormPreviewTest extends BaseTest{

//	----- Hello Yellow sing up Form -------
	
	@Test (description = "Verify correct form")
	public void tc01_verifyCorrectForm() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.clickTemplates();
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickCategory("Form");
		tp.clickPreviewItem("Hello Yellow Sign Up Form");
		HelloYellowPrevPage hwpp = new HelloYellowPrevPage(driver);  
		String expected = "Register for our great Service";
		String actual = hwpp.getHeaderText();  // Get the header
		Assert.assertEquals(actual, expected);
	}

	// Test invalid and null data options using Data Provider
	@Test (dataProvider = "getData", description = "Register - invalid email")
	public void tc02_regFailed(String fName, String lName, String email, String errMsg) {
		HelloYellowPrevPage hwpp = new HelloYellowPrevPage(driver);  
		hwpp.fillRegForm(fName, lName, email);
		hwpp.clickAgreeCheckBox();
		hwpp.clickNext();
		String expected =errMsg;
		String actual = hwpp.getErrMsg();  // Get the error
		Assert.assertEquals(actual, expected);
	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][] myData = {
				{"Meytal","Shitrit" , "meytalshitrit@##.com" , "Please enter a valid email address."},    // invalid email
				{"","Shitrit" , "meytalshitrit@gmail.com", "This field is required."},                    // null first name
				{"Meytal","" , "meytalshitrit@gmail.com", "This field is required."},                     // null last name
				{"Meytal","Shitrit" , "", "This field is required."}, 									  // null email
		};
		return myData;
	}
	
	// Fill registration form and click Next - with no selecting the mandatory check box - registration should fail
	@Test(description = "Verify error message if checkBox is not selected")
	public void tc03_regFailed() {
		String fName = "Meytal";
		String lName = "Shitrit";
		String email = "meytal@gmail.com";
		HelloYellowPrevPage hwpp = new HelloYellowPrevPage(driver);  
		hwpp.fillRegForm(fName, lName, email);
		// if the check box is selected ---> click again to cancel selection 
		hwpp.unClickAgreeCheckBox(); 
		hwpp.clickNext();
		String expected ="This field is required.";
		String actual = hwpp.getErrMsg();  // Get the error
		Assert.assertEquals(actual, expected);
	}
	
	// Valid registration  - Positive test
	@Test (description = " Valid registration  - Positive test")
	public void tc04_regSuccess() {
		String fName = "Meytal";
		String lName = "Shitrit";
		String email = "meytal@gmail.com";
		HelloYellowPrevPage hwpp = new HelloYellowPrevPage(driver);  
		hwpp.fillRegForm(fName, lName, email);
		hwpp.clickAgreeCheckBox();
		hwpp.clickNext();
		hwpp.sleep(2000);
		String expected ="Thank you!";
		String actual = hwpp.getThankYouMsg();  // Get the error
		Assert.assertEquals(actual, expected);
	}
}