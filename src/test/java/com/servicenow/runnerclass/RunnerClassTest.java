package com.servicenow.runnerclass;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

import com.servicenow.stepdefinitions.*;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(plugin = { "pretty:STDOUT", "html:format","pretty", "json:target/cucumber.json",
		"html:target/index.html" }, features = {"src/test/resources/com/servicenow/features"}, tags = {
				"@Gurukula" },glue = { "com.servicenow.stepdefinitions" })

public class RunnerClassTest {	
	@org.junit.AfterClass
	public static void afterRunner() {
		BrowserConfig.driver.quit();
	}
}