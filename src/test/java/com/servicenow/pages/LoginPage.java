package com.servicenow.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.jda.automation.assertions.ValidationType;
import com.jda.automation.reports.Report;
import com.jda.automation.reports.Status;
import com.jda.automation.utilities.PageHelper;
import com.jda.automation.utilities.WaitType;

public class LoginPage extends PageHelper {

	public LoginPage(WebDriver pDriver) {
		super(pDriver);
		
		element("txtBx_UserName", By.xpath("//div/input[@id='username']"));
		element("txtBx_Password", By.xpath("//input[@id='password']"));			
		element("lnk_Login", By.xpath("//div/a[@href='#/login']"));		
		element("btn_Authenticate", By.xpath("//button[text()='Authenticate']"));		
		element("header", By.xpath("//div[contains(@id,'navbar-collapse')]"));		
		element("drpdwn_Account", By.xpath("//li[@class='dropdown pointer']/a"));		
		element("logout", By.xpath("//li[@class='dropdown pointer open']//following-sibling::li//span[contains(text(),'Log out')]"));		
		element("lnk_Login", By.xpath("//a[contains(text(),'login')]"));
	}

	@Override
	public void validatePageExists(ValidationType vType) {
	
	}

	public void launchApplication(String url) {		
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public void enterUsername(String username) {		
		element("lnk_Login").ajaxWait(WaitType.Clickable).click();
		element("txtBx_UserName").ajaxWait(WaitType.Clickable).click();
		element("txtBx_UserName").ajaxWait(WaitType.Clickable).sendKeys(username);
		Report.log("Entered UserName", Status.PASS);
	}
	
	public void enterPassword(String password) {
		element("txtBx_Password").ajaxWait(WaitType.Clickable).sendKeys(password);
		Report.log("Entered Password", Status.PASS);
	}

	public void clickLogin() {
		element("btn_Authenticate").ajaxWait(WaitType.Clickable).click();		
		Report.log("Clicked on Authenticate Button", Status.PASS);
		element("header").ajaxWait(WaitType.Visible);	
	}

	public void logout() {
		//driver.switchTo().defaultContent();
		element("drpdwn_Account").ajaxWait(WaitType.Clickable).click();
		element("logout").ajaxWait(WaitType.Pause);
		element("logout").ajaxWait(WaitType.Clickable).click();
		element("lnk_Login").ajaxWait(WaitType.Visible);
	}	
}