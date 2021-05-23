package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject.BillingPage;
import pageobject.ChooseProjectTypePage;
import pageobject.EditorPage;
import pageobject.ProjectsPage;

public class ProjectTest extends BaseTest{

	@Test (description = "Rename workspace")
	public void tc01_renameWorkspace() {
		ProjectsPage pp = new ProjectsPage(driver);
		String newName = "Meytal Workspace";
		// rename workspace by new name
		pp.renameWorkspace(newName); 
		String actual = pp.getSubTitle();
		Assert.assertEquals(actual, newName);
	}

	@Test (description = "Add new workspace")
	public void tc02_addNeWorkspace() {
		ProjectsPage pp = new ProjectsPage(driver);
		String newWorspaceName = "New Workspace";
		// add the new workspace
		pp.addNewWorkspace(newWorspaceName); 
		// get the workspace name 
		String actual = pp.getSubTitle(); 
		// Verify workspace added has the right name
		Assert.assertEquals(actual, newWorspaceName); 
		// Verify that the workspace is empty
		Assert.assertTrue(pp.isProjectEmpty()); 
	}

	@Test (description = "Delete workspace")
	public void tc03_deleteWorkspace() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.deleteWorkspace();
		// verify workspace deleted is no longer exist
		Assert.assertFalse(pp.isWorspaceExist("New Work space"));
	}

	@Test (description = "Invite user - negative test - need to upgrade")
	public void tc04_failedInviteUser() {
		ProjectsPage pp = new ProjectsPage(driver);
		String email = "mm@mm.mm";
		pp.invitUser(email);
		String actual = pp.getAvailbleSeatsErrMsg();
		String expected = "You've used all your available seats.";
		Assert.assertEquals(actual, expected);
	}

	@Test (description = "Verify upgrade link")
	public void tc05_upgradeLink() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.clickUpgradeLink();
		pp.moveToNewWin();
		BillingPage bp = new BillingPage(driver);
		String actual = bp.getHeaderText();
		String expected = "Choose a subscription that suits you.";
		Assert.assertEquals(actual, expected);
		bp.closeWindow();
		pp.moveBackToMain();
		 //closing the setting window in order to prepare the next test
		pp.clickOutWin(10,25);
	}

	@Test (description = "Create new project - from scratch")
	public void tc06_createProject() {
		ProjectsPage pp = new ProjectsPage(driver);
		String worspaceName = "Meytal Workspace";
		// get workspace total projects number before creating a new one 
		int before = pp.getWSProNum(worspaceName); 
		pp.clickCreatePro();
		ChooseProjectTypePage cptp = new ChooseProjectTypePage(driver);
		cptp.clickStartScratch();
		EditorPage ep = new EditorPage(driver);
		String projectName ="Meytal Project";
		//Insert project name and choose type
		ep.setProject(projectName); 
		ep.clickSaveExit();
		//wait until project page present
		pp.waitUntilProjectPresent(); 
		// get workspace total projects number after creating a new one 
		int after = pp.getWSProNum(worspaceName); 
		Assert.assertEquals(before, after-1);
	}

	@Test (description =  "Move one project to another workspace")
	public void tc07_moveProjectToWS() {
		ProjectsPage pp = new ProjectsPage(driver);
		String projectName = "Meytal Project";
		String fromWorspace = "Meytal Workspace";
		String toWorkspace = "Very new";
		// Add new workspace
		pp.addNewWorkspace(toWorkspace); 
		// click workspace to transfer from
		pp.chooseWorkspace(fromWorspace);
		 // move project from Wspace to new Wspace by project name
		pp.moveProToWorkspace(fromWorspace, toWorkspace, projectName);
		//click to open  the new Wspace
		pp.chooseWorkspace(toWorkspace); 
		//verify project exist in the new Wspace by name
		Assert.assertTrue(pp.isProjectExist(projectName)); 
	}

	@Test (description = "Delete project")
	public void tc08_deleteProject() {
		ProjectsPage pp = new ProjectsPage(driver);
		String projectName = "Meytal Project";
		String worspaceName = "Very new";
		// get workspace total projects number before delete project
		int before = pp.getWSProNum(worspaceName); 
		// Delete project by name and wait until project no longer exist
		pp.deleteProject(projectName); 
		//Verify project is not exist
		Assert.assertFalse(pp.isProjectExist(projectName));
		// get the total projects number after creating a new one 
		int after = pp.getWSProNum(worspaceName);
		Assert.assertEquals(before-1, after);
	}
	
	@Test (description = "Set for next run")
	public void tc09_setNextRun() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.deleteWorkspace(); 
		String newName = "Workspace";
		pp.renameWorkspace(newName); // rename workspace
	}
}
