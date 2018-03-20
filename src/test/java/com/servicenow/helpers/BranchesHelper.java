package com.servicenow.helpers;

import org.openqa.selenium.WebDriver;

import com.servicenow.pages.BranchesPage;
import com.servicenow.pages.LoginPage;

public class BranchesHelper {	
	public WebDriver driver;
	public BranchesPage branchesPage;
	
	public BranchesHelper(WebDriver driver) {
		this.driver = driver;
		branchesPage = new BranchesPage(driver);
	}
	
	public void viewBranch(String branchName){
		branchesPage.clickOnEntityAndBranch();
		//branchesPage.viewBranchAndAssert(branchName);
	}
	
	public void viewBranchAndAssert(String branchName){
		branchesPage.viewBranchAndAssert(branchName);
	}
	
	public void createBranch(String branchName,String code){
		branchesPage.clickOnEntityAndBranch();
		branchesPage.createBranch(branchName, code);
	}
	
	public void editBranch(String branchName){
		branchesPage.clickOnEntityAndBranch();
		branchesPage.editBranch(branchName);
	}
	
	public void deleteBranch(String branchName){
		branchesPage.clickOnEntityAndBranch();
		branchesPage.deleteBranch(branchName);
	}	
}