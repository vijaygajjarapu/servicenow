package com.servicenow.stepdefinitions;

import org.openqa.selenium.By;

import com.jda.automation.assertions.Assert;
import com.jda.automation.assertions.ValidationType;
import com.jda.automation.dataReaders.DataProvider;
import com.jda.automation.reports.Report;
import com.jda.automation.reports.Status;
import com.jda.automation.utilities.WrappedWebDriver;
import com.servicenow.helpers.LoginHelper;
import com.servicenow.stepdefinitions.BrowserConfig;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Login {
	public WrappedWebDriver driver = BrowserConfig.driver;	
	private LoginHelper loginHelper;
	
	public Login(){
		loginHelper = new LoginHelper(driver);
	}
	
	@When("^I Logged into Gurukula$")
	public void i_Logged_into_Gurukula() throws Throwable {
		loginHelper.login(DataProvider.GetDataFromJSON("Environment", "url"),
				DataProvider.GetDataFromJSON("Username", "username"),
				DataProvider.GetDataFromJSON("Password", "password"));
		Report.log("Logged INN", Status.PASS);
	}

	@Then("^I Logged out of Gurukula$")
	public void i_Logged_out_of_Gurukula() throws Throwable {
	   loginHelper.logout();
	   Report.log("Logged Out", Status.PASS);
	}
}