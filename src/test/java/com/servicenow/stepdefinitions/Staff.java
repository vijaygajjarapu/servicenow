package com.servicenow.stepdefinitions;

import com.jda.automation.dataReaders.DataProvider;
import com.jda.automation.utilities.WrappedWebDriver;
import com.servicenow.helpers.BranchesHelper;
import com.servicenow.helpers.StaffHelper;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Staff {
	
	public WrappedWebDriver driver = BrowserConfig.driver;	
	private StaffHelper staffHelper;
	
	public Staff(){
		staffHelper = new StaffHelper(driver);
	}
	
	@When("^I navigated to view Staff$")
	public void i_navigated_to_view_Branch() throws Throwable {
		staffHelper.viewStaff(DataProvider.getValue("StaffName"));
	}
	
	@When("^I create a Staff$")
	public void i_create_a_Branch() throws Throwable {
		staffHelper.createStaff(DataProvider.getValue("StaffName"),DataProvider.getValue("BranchName"));
	}

	@Then("^Staff should be viewed$")
	public void branch_should_be_viewed() throws Throwable {
		staffHelper.viewStaffAndAssert(DataProvider.getValue("StaffName"));
	}
	
	@When("^I edit a Staff$")
	public void i_edit_a_Branch() throws Throwable {
		staffHelper.editStaff(DataProvider.getValue("StaffName"));
	}

	@When("^I delete a Staff$")
	public void i_delete_a_Branch() throws Throwable {
		staffHelper.deleteStaff(DataProvider.getValue("StaffName"));
	}

}
