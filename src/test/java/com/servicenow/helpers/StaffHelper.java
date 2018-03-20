package com.servicenow.helpers;

import org.openqa.selenium.WebDriver;

import com.servicenow.pages.BranchesPage;
import com.servicenow.pages.StaffPage;

public class StaffHelper {
	
	public WebDriver driver;
	public StaffPage staffPage;
	
	public StaffHelper(WebDriver driver) {
		this.driver = driver;
		staffPage = new StaffPage(driver);
	}
	
	public void viewStaff(String staffName){
		staffPage.clickOnEntityAndStaff();
		//branchesPage.viewBranchAndAssert(branchName);
	}
	
	public void viewStaffAndAssert(String staffName){
		staffPage.viewStaffAndAssert(staffName);
	}
	
	public void createStaff(String staffName,String branchName){
		staffPage.clickOnEntityAndStaff();
		staffPage.createStaff(staffName, branchName);
	}
	
	public void editStaff(String staffName){
		staffPage.clickOnEntityAndStaff();
		staffPage.editStaff(staffName);
	}
	
	public void deleteStaff(String staffName){
		staffPage.clickOnEntityAndStaff();
		staffPage.deleteStaff(staffName);
	}

}
