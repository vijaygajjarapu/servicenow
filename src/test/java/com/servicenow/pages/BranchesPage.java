package com.servicenow.pages;

import java.util.List;
import java.util.regex.Pattern;

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

public class BranchesPage extends PageHelper {

	public BranchesPage(WebDriver pDriver) {
		super(pDriver);
		element("txtBx_UserName", By.xpath("//div/input[@id='username']"));
		element("drpdwn_Entities", By.xpath("//li[@class='dropdown pointer ng-scope']/a"));
		element("branch", By.xpath("//ul[contains(@class,'dropdown-menu')]//a[@href='#/branch']"));
		element("brachesPageTitle", By.xpath("//h2[text()='Branches']"));
		element("btn_Back", By.xpath("//button[@href='#/branch']"));
		element("btn_CreateBranch", By.xpath("//div[@class='row']//button[contains(@class,'btn btn-primary')]"));
		element("createBranchPopUpHeader", By.xpath("//h4[contains(text(),'Create or edit a Branch')]"));
		element("txt_CreateBranchName", By.xpath("//label[contains(text(),'Name')]//following-sibling::input[contains(@type,'text')]"));
		element("txt_CreateCode", By.xpath("//label[contains(text(),'Code')]//following-sibling::input[contains(@type,'text')]"));
		element("btn_Cancel", By.xpath("//form[@name='editForm']//button[(@type='button' and @class='btn btn-default')]"));
		element("btn_Save", By.xpath("//form[@name='editForm']//button[(@type='submit' and @class='btn btn-primary')]"));		
		element("confirmDeletePopUp", By.xpath("//h4[text()='Confirm delete operation']"));
		element("btn_Delete", By.xpath("//form[@name='deleteForm']//button[(@type='submit' and @class='btn btn-danger')]"));
	}
	
	public void clickOnEntityAndBranch() {	
		element("drpdwn_Entities").ajaxWait(WaitType.Clickable).click();
		element("branch").ajaxWait(WaitType.Pause);
		element("branch").ajaxWait(WaitType.Clickable).click();
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
	
	public void validateMinLengthOfBranchName(String branchName){
		int len = branchName.length();
		if(len<5){
			element("btn_CreateBranch").ajaxWait(WaitType.Clickable).click();
			Report.log("Clicked on Create Branch button", Status.PASS);
			element("createBranchPopUpHeader").ajaxWait(WaitType.Visible);
			Report.log("Create Branch Pop is available", Status.PASS);
			element("txt_CreateBranchName").ajaxWait(WaitType.Visible).sendKeys(branchName);
			element("txt_CreateBranchName").ajaxWait(WaitType.Pause);
			WebElement element = driver.findElement(By.xpath("//label[text()='Name']/../div/p[text()='This field is required to be at least 5 characters.']"));
			Assert.assertEquals(ValidationType.Verify, "Validated the minumum length of the Branch Name", "This field is required to be at least 5 characters.",element.getText());
			//Report.log(element.getText(), Status.FAIL);
			element("btn_Cancel").ajaxWait(WaitType.Clickable).click();
			element("brachesPageTitle").ajaxWait(WaitType.Pause);
			element("btn_CreateBranch").ajaxWait(WaitType.Visible);
		}
	}
	
	public void validateMaxLengthOfBranchName(String branchName){
		int len = branchName.length();
		if(len>20){
			element("btn_CreateBranch").ajaxWait(WaitType.Clickable).click();
			Report.log("Clicked on Create Branch button", Status.PASS);
			element("createBranchPopUpHeader").ajaxWait(WaitType.Visible);
			Report.log("Create Branch Pop is available", Status.PASS);
			element("txt_CreateBranchName").ajaxWait(WaitType.Visible).sendKeys(branchName);
			element("txt_CreateBranchName").ajaxWait(WaitType.Pause);
			WebElement element = driver.findElement(By.xpath("//label[text()='Name']/../div/p[text()='This field cannot be longer than 20 characters.']"));
			Assert.assertEquals(ValidationType.Verify, "Validated the maximum length of the Branch Name", "This field cannot be longer than 20 characters.",element.getText());
			//Report.log(element.getText(), Status.FAIL);
			element("btn_Cancel").ajaxWait(WaitType.Clickable).click();
			element("brachesPageTitle").ajaxWait(WaitType.Pause);
			element("btn_CreateBranch").ajaxWait(WaitType.Visible);
		}
	}
	
	public void validateBranchNamePattern(String branchName){
		String regx="^[a-zA-Z ]{5,20}$";
		boolean pattern = Pattern.matches(regx,branchName);		
		if(pattern){
			Report.log("Branch name is according to the pattern", Status.PASS);			
		} else {
			element("btn_CreateBranch").ajaxWait(WaitType.Clickable).click();
			Report.log("Clicked on Create Branch button", Status.PASS);
			element("createBranchPopUpHeader").ajaxWait(WaitType.Visible);
			Report.log("Create Branch Pop is available", Status.PASS);
			element("txt_CreateBranchName").ajaxWait(WaitType.Visible).sendKeys(branchName);
			element("txt_CreateBranchName").ajaxWait(WaitType.Pause);
			WebElement element = driver.findElement(By.xpath("//label[text()='Name']/../div/p[contains(text(),'This field should follow pattern')]"));
			Report.log(element.getText(), Status.FAIL);
			element("btn_Cancel").ajaxWait(WaitType.Clickable).click();
			element("brachesPageTitle").ajaxWait(WaitType.Pause);
			element("btn_CreateBranch").ajaxWait(WaitType.Visible);
		}
	}	
	
	public void validateMinLengthOfBranchCode(String code){
		int len = code.length();
		if(len<2){
			element("btn_CreateBranch").ajaxWait(WaitType.Clickable).click();
			Report.log("Clicked on Create Branch button", Status.PASS);
			element("createBranchPopUpHeader").ajaxWait(WaitType.Visible);
			Report.log("Create Branch Pop is available", Status.PASS);
			element("txt_CreateCode").ajaxWait(WaitType.Visible).sendKeys(code);
			element("txt_CreateCode").ajaxWait(WaitType.Pause);
			WebElement element = driver.findElement(By.xpath("//label[text()='Code']/../div/p[text()='This field is required to be at least 2 characters.']"));
			Report.log(element.getText(), Status.FAIL);
			element("btn_Cancel").ajaxWait(WaitType.Clickable).click();
			element("brachesPageTitle").ajaxWait(WaitType.Pause);
			element("btn_CreateBranch").ajaxWait(WaitType.Visible);
		}
	}
	
	public void validateMaxLengthOfBranchCode(String code){
		int len = code.length();
		if(len>10){
			element("btn_CreateBranch").ajaxWait(WaitType.Clickable).click();
			Report.log("Clicked on Create Branch button", Status.PASS);
			element("createBranchPopUpHeader").ajaxWait(WaitType.Visible);
			Report.log("Create Branch Pop is available", Status.PASS);
			element("txt_CreateCode").ajaxWait(WaitType.Visible).sendKeys(code);
			element("txt_CreateCode").ajaxWait(WaitType.Pause);
			WebElement element = driver.findElement(By.xpath("//label[text()='Code']/../div/p[text()='This field cannot be longer than 10 characters.']"));
			Report.log(element.getText(), Status.FAIL);
			element("btn_Cancel").ajaxWait(WaitType.Clickable).click();
			element("brachesPageTitle").ajaxWait(WaitType.Pause);
			element("btn_CreateBranch").ajaxWait(WaitType.Visible);
		}
	}
	
	public void validateBranchCodePattern(String code){
		String regx="^[A-Z0-9]{2,10}$";
		boolean pattern = Pattern.matches(regx,code);		
		if(pattern){
			Report.log("Branch code is according to the pattern", Status.PASS);			
		} else {
			element("btn_CreateBranch").ajaxWait(WaitType.Clickable).click();
			Report.log("Clicked on Create Branch button", Status.PASS);
			element("createBranchPopUpHeader").ajaxWait(WaitType.Visible);
			Report.log("Create Branch Pop is available", Status.PASS);
			element("txt_CreateBranchName").ajaxWait(WaitType.Visible).sendKeys(code);
			element("txt_CreateBranchName").ajaxWait(WaitType.Pause);
			WebElement element = driver.findElement(By.xpath("//label[text()='Code']/../div/p[contains(text(),'This field should follow pattern')]"));
			Report.log(element.getText(), Status.FAIL);
			element("btn_Cancel").ajaxWait(WaitType.Clickable).click();
			element("brachesPageTitle").ajaxWait(WaitType.Pause);
			element("btn_CreateBranch").ajaxWait(WaitType.Visible);
		}
	}	
}