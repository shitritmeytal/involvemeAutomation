package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.NewFeaturPage;
import pageobject.ProjectsPage;
import pageobject.RegisterPage;
import pageobject.ResetpasswordPage;
import utils.Utils;

public class LoginTest extends BaseTest{
	
	// Override method(BaseTest) so it wont perform valid login
	@BeforeClass
	public void setupLogin() {
	}
	
	@Test(description = "login  - invalid email")
	public void tc01_loginFailed() {
		HomePage hp = new HomePage(driver);
		hp.clickLogin();
		LoginPage lp = new LoginPage(driver);
		String email = "meytalshitrit";
		String password = "Meytal";
		lp.login(email, password);
		String actual = lp.getEmailErrnMsg();
		String expected = "Please include an '@' in the email address. 'meytalshitrit' is missing an '@'.";
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "login  - invalid password")
	public void tc02_loginFailed() {
		LoginPage lp = new LoginPage(driver);
		String email = "meytalshitrit@gmail.com";
		String password = "Meytal";
		lp.login(email, password);
		String actual = lp.getPasswordErrnMsg();
		String expected = "These credentials do not match our records.";
		Assert.assertEquals(actual, expected);
	}
	
	@Test(description = "login - null email & valid pasword")
	public void tc03_loginFailed() {
		LoginPage lp = new LoginPage(driver);
		String email = " ";
		String password = "Meytal";
		lp.login(email, password);
		String actual = lp.getEmailErrnMsg();
		String expected = "Please fill out this field.";
		Assert.assertEquals(actual, expected);
	}
	
	@Test (description = "login - valid email & null passsword")
	public void tc04_loginFailed() {
		LoginPage lp = new LoginPage(driver);
		String email = "meytalshitrit@gmail.com";
		String password = "";
		lp.login(email, password);
		String actual = lp.getNullpassErrnMsg();
		String expected = "Please fill out this field.";
		Assert.assertEquals(actual, expected);
	}
	
	//verify forgot your password flow 
	@Test (description = "Forgot password from login page")
	public void tc05_forgotPassword() {
		LoginPage lp =  new LoginPage(driver);
		lp.clickForgotPass();
		ResetpasswordPage rpp = new ResetpasswordPage(driver);
		String email = "meytalshitrit@gmail.com";
		rpp.sendRequest(email);
		String actual = rpp.getConfirmMsg();
		String expected = "A reset link has been sent to the email address, if it has been used to register for an account.";
		Assert.assertEquals(actual, expected);
	}
	
	//verify that the "Create an account" link leads to the right page
	@Test (description = "Click 'Create an account' from login page ")
	public void tc06_createAccount() {
		ResetpasswordPage rpp = new ResetpasswordPage(driver);
		rpp.clickLogin();
		LoginPage lp =  new LoginPage(driver);
		lp.clickCreateAccount();
		RegisterPage rp = new RegisterPage(driver);
		String title = "Get started for free";
		Assert.assertTrue(rp.isCorrectPage(title));
	}
	
	//Verify that the "See how it works" link leads to the right page
	@Test (description = "Click 'See how it works' from login page ")
	public void tc07_seeHowItWork() {
		RegisterPage rp = new RegisterPage(driver);
		rp.clickLogin();
		LoginPage lp =  new LoginPage(driver);
		lp.clickSeeHowItWork();
		lp.moveToNewWin();  //Remember the main window and move to new window opened
		NewFeaturPage nfp = new NewFeaturPage(driver);
		Assert.assertTrue(nfp.isCorrectPage("NEW FEATURE"));    //verify title
		nfp.closeWindow();
		lp.moveBackToMain(); // 	close and switch back to main window

	}
	
	//positive test - login
	@Test(description = "login - positive - valid email & password")
	public void tc08_loginSuccess() {
		LoginPage lp = new LoginPage(driver);
		String email = Utils.readProperty("user");
		String password = Utils.readProperty("password");
		lp.login(email, password);
		ProjectsPage pp = new ProjectsPage(driver);
		String actual = pp.getUserName();
		String expected = "meytal";
		Assert.assertEquals(actual, expected);
	}

}
