package com.servicenow.helpers;

import org.openqa.selenium.WebDriver;

import com.servicenow.pages.LoginPage;

public class LoginHelper {
	public WebDriver driver;
	public LoginPage loginPage;
	
	public LoginHelper(WebDriver driver) {
		this.driver = driver;
		loginPage = new LoginPage(driver);
	}
	
	public void login(String url, String username, String password) throws Exception {
		driver.manage().deleteAllCookies();
		loginPage.launchApplication(url);
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
	}

	public void logout() {
		loginPage.logout();
	}

}
