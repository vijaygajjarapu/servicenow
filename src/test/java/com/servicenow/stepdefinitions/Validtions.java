package com.servicenow.stepdefinitions;

import com.jda.automation.dataReaders.DataProvider;
import com.jda.automation.utilities.WrappedWebDriver;
import com.servicenow.helpers.BranchesHelper;
import com.servicenow.helpers.ValidationsHelper;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Validtions {
	
	public WrappedWebDriver driver = BrowserConfig.driver;	
	private ValidationsHelper validationsHelper;
	
	public Validtions(){
		validationsHelper = new ValidationsHelper(driver);
	}
	
	@Then("^I validate the min length of BranchName$")
	public void i_validate_the_min_length_of_BranchName() throws Throwable {
		validationsHelper.validateMinLengthOfBranchName(DataProvider.getValue("MinLengthBranchName"));
	}

	@Then("^I validate the max length of BranchName$")
	public void i_validate_the_max_length_of_BranchName() throws Throwable {
		validationsHelper.validateMaxLengthOfBranchName(DataProvider.getValue("MaxLengthBranchName"));
	}

	@Then("^I validate the BranchName sequence$")
	public void i_validate_the_BranchName_sequence() throws Throwable {
		validationsHelper.validateBranchNamePattern(DataProvider.getValue("BranchNamePattern"));
	}
	
	@Then("^I validate the min length of BranchCode$")
	public void i_validate_the_min_length_of_BranchCode() throws Throwable {
		validationsHelper.validateMinLengthOfBranchCode(DataProvider.getValue("MinLengthBranchCode"));
	}

	@Then("^I validate the max length of BranchCode$")
	public void i_validate_the_max_length_of_BranchCode() throws Throwable {
		validationsHelper.validateMaxLengthOfBranchCode(DataProvider.getValue("MaxLengthBranchCode"));
	}

	@Then("^I validate the BranchCode sequence$")
	public void i_validate_the_BranchCode_sequence() throws Throwable {
		validationsHelper.validateBranchCodePattern(DataProvider.getValue("BranchCodePattern"));
	}
	
	@Then("^I validate the min length of StaffName$")
	public void i_validate_the_min_length_of_StaffName() throws Throwable {
		validationsHelper.validateMinLengthOfStaffName(DataProvider.getValue("MinLengthStaffCode"));
	}

	@Then("^I validate the max length of StaffName$")
	public void i_validate_the_max_length_of_StaffName() throws Throwable {
		validationsHelper.validateMaxLengthOfStaffName(DataProvider.getValue("MaxLengthStaffCode"));
	}

	@Then("^I validate the StaffName sequence$")
	public void i_validate_the_StaffName_sequence() throws Throwable {
		validationsHelper.validateStaffNamePattern(DataProvider.getValue("StaffCodePattern"));
	}
}