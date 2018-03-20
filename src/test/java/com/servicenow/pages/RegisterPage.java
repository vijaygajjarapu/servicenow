package com.servicenow.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.jda.automation.assertions.Assert;
import com.jda.automation.assertions.ValidationType;
import com.jda.automation.dataReaders.DataProvider;
import com.jda.automation.reports.Report;
import com.jda.automation.reports.Status;
import com.jda.automation.utilities.PageHelper;
import com.jda.automation.utilities.WaitType;

public class RegisterPage extends PageHelper {


	public RegisterPage(WebDriver pDriver) {
		super(pDriver);
		element("lnk_RegisterANewAccount", By.xpath("//div/a[@href='#/register']"));
		element("registrationPage", By.xpath("//h1[contains(text(),'Registration')]"));
		element("txt_YourLogin", By.xpath("//input[contains(@name,'login')]"));
		element("txt_YourEmail", By.xpath("//input[contains(@name,'email')]"));
		element("txt_NewPassword", By.xpath("//input[contains(@name,'password')]"));
		element("txt_ConfirmNewPassword", By.xpath("//input[contains(@name,'confirmPassword')]"));
		element("btn_Register", By.xpath("//button[contains(@class,'btn btn-primary ng-scope')]"));
		element("txt_CreateBranchName", By.xpath("//label[contains(text(),'Name')]//following-sibling::input[contains(@type,'text')]"));
		element("txt_CreateCode", By.xpath("//label[contains(text(),'Code')]//following-sibling::input[contains(@type,'text')]"));
		element("btn_Cancel", By.xpath("//form[@name='editForm']//button[(@type='button' and @class='btn btn-default')]"));
		element("btn_Save", By.xpath("//form[@name='editForm']//button[(@type='submit' and @class='btn btn-primary')]"));		
		element("confirmDeletePopUp", By.xpath("//h4[text()='Confirm delete operation']"));
		element("btn_Delete", By.xpath("//form[@name='deleteForm']//button[(@type='submit' and @class='btn btn-danger')]"));
	}
	
	public void registerAccount() {	
		element("lnk_RegisterANewAccount").ajaxWait(WaitType.Clickable).click();
		element("registrationPage").ajaxWait(WaitType.Visible);
		element("txt_YourLogin").ajaxWait(WaitType.Clickable).sendKeys("");
		element("brachesPageTitle").ajaxWait(WaitType.Visible);
	}
	
	public void viewBranchAndAssert(String branchName) {		
		String branch = "//td[text()='";
		String view="']/..//following-sibling::td//button[contains(@class,'btn btn-info btn-sm')]";
		WebElement ele = driver.findElement(By.xpath(branch+branchName+view));
		ele.click();
		element("btn_Back").ajaxWait(WaitType.Clickable);
		Report.log("Navigated to View Branch screen", Status.PASS);
		Assert.assertEquals(ValidationType.Verify, "Viewed the Branch", DataProvider.getValue("BranchName"),driver.findElement(By.xpath("//td/span[contains(text(),'Name')]"
				+ "/..//following-sibling::td/input")).getAttribute("value") );
	}
	
	
	public void createBranch(String branchName,String code) {
		element("btn_CreateBranch").ajaxWait(WaitType.Clickable).click();
		Report.log("Clicked on Create Branch button", Status.PASS);
		element("createBranchPopUpHeader").ajaxWait(WaitType.Visible);
		Report.log("Create Branch Pop is available", Status.PASS);
		element("txt_CreateBranchName").ajaxWait(WaitType.Visible).sendKeys(branchName);
		element("txt_CreateCode").ajaxWait(WaitType.Visible).sendKeys(code);		
		element("btn_Save").ajaxWait(WaitType.Visible).click();;			
		element("brachesPageTitle").ajaxWait(WaitType.Pause);
		element("btn_CreateBranch").ajaxWait(WaitType.Visible);	
		Report.log("Successfully Created a Branch", Status.PASS);
		Assert.assertEquals(ValidationType.Verify, "Viewed the Branch", DataProvider.getValue("BranchName"),driver.findElement(By.xpath("//td[text()='"
				+branchName
				+"']")).getText() );
	}

	@Override
	public void validatePageExists(ValidationType vType) {
				
	}
	
	public void editBranch(String branchName) {
		String branch = "//td[text()='";
		String edit="']/..//following-sibling::td//button[contains(@class,'btn btn-primary btn-sm')]";
		WebElement ele = driver.findElement(By.xpath(branch+branchName+edit));
		ele.click();
		element("btn_Save").ajaxWait(WaitType.Clickable);
		Report.log("Navigated to Edit Branch screen", Status.PASS);
		element("txt_CreateBranchName").ajaxWait(WaitType.Clickable).clear();
		element("txt_CreateBranchName").ajaxWait(WaitType.Clickable).sendKeys(branchName+"updated");
		element("btn_Save").ajaxWait(WaitType.Clickable).click();
		Report.log("Modified Branch name", Status.PASS);
		element("brachesPageTitle").ajaxWait(WaitType.Pause);
		element("btn_CreateBranch").ajaxWait(WaitType.Visible);
				
	}

	public void deleteBranch(String branchName) {
		String branch = "//td[text()='";
		String edit="']/..//following-sibling::td//button[contains(@class,'btn btn-danger btn-sm')]";
		WebElement ele = driver.findElement(By.xpath(branch+branchName+"updated"+edit));
		ele.click();
		element("confirmDeletePopUp").ajaxWait(WaitType.Visible);
		Report.log("Navigated to delete pop up screen", Status.PASS);
		element("btn_Delete").ajaxWait(WaitType.Clickable).click();		
		element("brachesPageTitle").ajaxWait(WaitType.Pause);
		element("btn_CreateBranch").ajaxWait(WaitType.Visible);		
		List<WebElement> branches = driver.findElements(By.xpath("//table/tbody/tr"));
		int index = branches.size();
		
		for(int i=1;i<=index;i++){
			String xpath1 = "//table/tbody/tr[";
			String xpath2 = "]/td[2]";
			
			String bName = driver.findElement(By.xpath(xpath1+i+xpath2)).getText();
			if(bName.equalsIgnoreCase(branchName)){
				Report.log("Branch is not deleted", Status.FAIL);	
				break;
				}else
					Report.log("Deleted Branch Name", Status.PASS);	
		}
	}


}
