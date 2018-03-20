package com.servicenow.stepdefinitions;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.rules.TestWatcher;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Step;

import com.gargoylesoftware.htmlunit.javascript.host.intl.DateTimeFormat;
import com.jda.automation.dataReaders.DataProvider;
import com.jda.automation.reports.Report;
//import com.jda.automation.utilities.InputData;
import com.jda.automation.utilities.WrappedWebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class BrowserConfig {
	public static WrappedWebDriver driver = null;
	Properties CONFIG = null;
//	public Map<String, String> readProperties;
//	public static HashMap<String, String> inputData;
//	public static HashMap<String, String> envData;
//	public Logger APP_LOGS = Logger.getLogger("devpinoyLogger");
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public void initConfig(String fileName) {
		String filePath = System.getProperty("user.dir") + "//envConfig//" + fileName;
		 DataProvider.loadTestData(filePath);						
	}
	
	@Before
	public void beforeScenario(Scenario scenario) throws Throwable {
		initConfig("config.json");
		if (driver == null) {
			driver = new WrappedWebDriver("chrome");
		}
		ArrayList<String> tags = (ArrayList<String>)scenario.getSourceTagNames();
		String[] cat =  tags.toArray(new String[tags.size()]);		
		Report.startReportSuite();
		Report.startReportTest(scenario.getName(), scenario.getName(), cat);
		Report.setWebDriver((WrappedWebDriver) driver);
	}
	
	@After
	public void afterScenario(Scenario scenario) throws Throwable {				
		try{
			Report.endReportTest();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}