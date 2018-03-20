package com.servicenow.stepdefinitions;

import com.jda.automation.dataReaders.DataProvider;
import com.jda.automation.utilities.WrappedWebDriver;
import com.servicenow.helpers.BranchesHelper;
import com.servicenow.helpers.LoginHelper;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Branches {
	
	public WrappedWebDriver driver = BrowserConfig.driver;	
	private BranchesHelper branchesHelper;
	
	public Branches(){
		branchesHelper = new BranchesHelper(driver);
	}
	
	@When("^I navigated to view Branch$")
	public void i_navigated_to_view_Branch() throws Throwable {
		branchesHelper.viewBranch(DataProvider.getValue("BranchName"));
	}
	
	@When("^I create a Branch$")
	public void i_create_a_Branch() throws Throwable {
		branchesHelper.createBranch(DataProvider.getValue("BranchName"),DataProvider.getValue("BranchCode"));
	}

	@Then("^Branch should be viewed$")
	public void branch_should_be_viewed() throws Throwable {
		branchesHelper.viewBranchAndAssert(DataProvider.getValue("BranchName"));
	}
	
	@When("^I edit a Branch$")
	public void i_edit_a_Branch() throws Throwable {
		branchesHelper.editBranch(DataProvider.getValue("BranchName"));
	}

	@When("^I delete a Branch$")
	public void i_delete_a_Branch() throws Throwable {
		branchesHelper.deleteBranch(DataProvider.getValue("BranchName"));
	}
}