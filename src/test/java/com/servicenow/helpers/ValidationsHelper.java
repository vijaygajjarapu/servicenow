package com.servicenow.helpers;

import org.openqa.selenium.WebDriver;

import com.servicenow.pages.BranchesPage;
import com.servicenow.pages.StaffPage;

public class ValidationsHelper {

	
	public WebDriver driver;
	public BranchesPage branchesPage;
	public StaffPage staffPage;
	
	public ValidationsHelper(WebDriver driver) {
		this.driver = driver;
		branchesPage = new BranchesPage(driver);
		staffPage = new StaffPage(driver);
	}
	
	public void validateMinLengthOfBranchName(String branchName){
		branchesPage.clickOnEntityAndBranch();
		branchesPage.validateMinLengthOfBranchName(branchName);
	}
	
	public void validateMaxLengthOfBranchName(String branchName){
		branchesPage.clickOnEntityAndBranch();
		branchesPage.validateMaxLengthOfBranchName(branchName);
	}
	
	public void validateBranchNamePattern(String branchName){
		branchesPage.clickOnEntityAndBranch();
		branchesPage.validateBranchNamePattern(branchName);
	}

	public void validateMinLengthOfBranchCode(String branchCode) {
		branchesPage.clickOnEntityAndBranch();
		branchesPage.validateMinLengthOfBranchCode(branchCode);
		
	}

	public void validateMaxLengthOfBranchCode(String branchCode) {
		branchesPage.clickOnEntityAndBranch();
		branchesPage.validateMaxLengthOfBranchCode(branchCode);
		
	}

	public void validateBranchCodePattern(String branchCode) {
		branchesPage.clickOnEntityAndBranch();
		branchesPage.validateBranchCodePattern(branchCode);		
	}
	
	public void validateMinLengthOfStaffName(String staffName) {
		staffPage.clickOnEntityAndStaff();
		staffPage.validateMinLengthOfStaffName(staffName);		
	}

	public void validateMaxLengthOfStaffName(String staffName) {
		staffPage.clickOnEntityAndStaff();
		staffPage.validateMaxLengthOfStaffName(staffName);		
	}

	public void validateStaffNamePattern(String staffName) {
		staffPage.clickOnEntityAndStaff();
		staffPage.validateStaffNamePattern(staffName);		
	}
}