package com.servicenow.pages;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.jda.automation.assertions.Assert;
import com.jda.automation.assertions.ValidationType;
import com.jda.automation.dataReaders.DataProvider;
import com.jda.automation.reports.Report;
import com.jda.automation.reports.Status;
import com.jda.automation.utilities.PageHelper;
import com.jda.automation.utilities.WaitType;

public class StaffPage extends PageHelper {
	
	public StaffPage(WebDriver pDriver) {
		super(pDriver);
		element("txtBx_UserName", By.xpath("//div/input[@id='username']"));
		element("drpdwn_Entities", By.xpath("//li[@class='dropdown pointer ng-scope']/a"));
		element("staff", By.xpath("//ul[contains(@class,'dropdown-menu')]//a[@href='#/staff']"));
		element("staffPageTitle", By.xpath("//h2[text()='staffs']"));
		element("btn_Back", By.xpath("//button[@href='#/staff']"));
		element("btn_CreateStaff", By.xpath("//div[@class='row']//button[contains(@class,'btn btn-primary')]"));
		element("createStaffPopUpHeader", By.xpath("//h4[contains(text(),'Create or edit a Staff')]"));
		element("txt_CreateStaffName", By.xpath("//label[contains(text(),'Name')]//following-sibling::input[contains(@type,'text')]"));
		element("drp_branch", By.xpath("//label[contains(text(),'Branch')]/..//select"));
		element("txt_CreateCode", By.xpath("//label[contains(text(),'Code')]//following-sibling::input[contains(@type,'text')]"));
		element("btn_Cancel", By.xpath("//form[@name='editForm']//button[(@type='button' and @class='btn btn-default')]"));
		element("btn_Save", By.xpath("//form[@name='editForm']//button[(@type='submit' and @class='btn btn-primary')]"));		
		element("confirmDeletePopUp", By.xpath("//h4[text()='Confirm delete operation']"));
		element("btn_Delete", By.xpath("//form[@name='deleteForm']//button[(@type='submit' and @class='btn btn-danger')]"));
	}
	
	public void clickOnEntityAndStaff() {	
		element("drpdwn_Entities").ajaxWait(WaitType.Clickable).click();
		element("staff").ajaxWait(WaitType.Pause);
		element("staff").ajaxWait(WaitType.Clickable).click();
		element("btn_CreateStaff").ajaxWait(WaitType.Visible);
	}
	
	public void viewStaffAndAssert(String staffName) {		
		String staff = "//td[text()='";
		String view="']/..//following-sibling::td//button[contains(@class,'btn btn-info btn-sm')]";
		WebElement ele = driver.findElement(By.xpath(staff+staffName+view));
		ele.click();
		element("btn_Back").ajaxWait(WaitType.Clickable);
		Report.log("Navigated to View Staff screen", Status.PASS);
		Assert.assertEquals(ValidationType.Verify, "Viewed the Staff", staffName,driver.findElement(By.xpath("//td/span[contains(text(),'Name')]"
				+ "/..//following-sibling::td/input")).getAttribute("value") );
	}
	
	
	public void createStaff(String staffName,String branchName) {
		element("btn_CreateStaff").ajaxWait(WaitType.Clickable).click();
		Report.log("Clicked on Create staff button", Status.PASS);
		element("createStaffPopUpHeader").ajaxWait(WaitType.Visible);
		Report.log("Create Staff Pop is available", Status.PASS);
		element("txt_CreateStaffName").ajaxWait(WaitType.Visible).sendKeys(staffName);
		
		Select selectBranch = new Select(driver.findElement(By.name("related_branch")));
		selectBranch.selectByVisibleText(branchName);		
		element("btn_Save").ajaxWait(WaitType.Visible).click();;			
		element("staffPageTitle").ajaxWait(WaitType.Pause);
		element("btn_CreateStaff").ajaxWait(WaitType.Visible);	
		Report.log("Successfully Created a Staff", Status.PASS);
		Assert.assertEquals(ValidationType.Verify, "Created a staff", staffName,driver.findElement(By.xpath("//td[text()='"
				+staffName
				+"']")).getText() );
	}

	@Override
	public void validatePageExists(ValidationType vType) {
				
	}
	
	public void editStaff(String staffName) {
		String staff = "//td[text()='";
		String edit="']/..//following-sibling::td//button[contains(@class,'btn btn-primary btn-sm')]";
		WebElement ele = driver.findElement(By.xpath(staff+staffName+edit));
		ele.click();
		element("btn_Save").ajaxWait(WaitType.Clickable);
		Report.log("Navigated to Edit staff screen", Status.PASS);
		element("txt_CreateStaffName").ajaxWait(WaitType.Clickable).clear();
		element("txt_CreateStaffName").ajaxWait(WaitType.Clickable).sendKeys(staffName+"updated");
		element("btn_Save").ajaxWait(WaitType.Clickable).click();
		Report.log("Modified Staff name", Status.PASS);
		element("staffPageTitle").ajaxWait(WaitType.Pause);
		element("btn_CreateStaff").ajaxWait(WaitType.Visible);
				
	}

	public void deleteStaff(String staffName) {
		String staff = "//td[text()='";
		String edit="']/..//following-sibling::td//button[contains(@class,'btn btn-danger btn-sm')]";
		WebElement ele = driver.findElement(By.xpath(staff+staffName+"updated"+edit));
		ele.click();
		element("confirmDeletePopUp").ajaxWait(WaitType.Visible);
		Report.log("Navigated to delete pop up screen", Status.PASS);
		element("btn_Delete").ajaxWait(WaitType.Clickable).click();		
		element("staffPageTitle").ajaxWait(WaitType.Pause);
		element("btn_CreateStaff").ajaxWait(WaitType.Visible);		
		List<WebElement> staffs = driver.findElements(By.xpath("//table/tbody/tr"));
		int index = staffs.size();
		
		for(int i=1;i<=index;i++){
			String xpath1 = "//table/tbody/tr[";
			String xpath2 = "]/td[2]";
			
			String bName = driver.findElement(By.xpath(xpath1+i+xpath2)).getText();
			if(bName.equalsIgnoreCase(staffName)){
				Report.log("Staff is not deleted", Status.FAIL);
				break;
				}else
					Report.log("Deleted Staff Name", Status.PASS);	
		}
	}
	
	public void validateMinLengthOfStaffName(String staffName){
		int len = staffName.length();
		if(len<1){
			element("btn_CreateStaff").ajaxWait(WaitType.Clickable).click();
			Report.log("Clicked on Create staff button", Status.PASS);
			element("createStaffPopUpHeader").ajaxWait(WaitType.Visible);
			Report.log("Create Staff Pop is available", Status.PASS);
			element("txt_CreateStaffName").ajaxWait(WaitType.Visible).sendKeys(staffName);			
			WebElement element = driver.findElement(By.xpath("//label[text()='Name']/../div/p[text()='This field is required.']"));
			Report.log(element.getText(), Status.FAIL);
			element("btn_Cancel").ajaxWait(WaitType.Clickable).click();
			element("staffPageTitle").ajaxWait(WaitType.Pause);
			element("btn_CreateStaff").ajaxWait(WaitType.Visible);
		}
	}
	
	public void validateMaxLengthOfStaffName(String staffName){
		int len = staffName.length();
		if(len>50){
			element("btn_CreateStaff").ajaxWait(WaitType.Clickable).click();
			Report.log("Clicked on Create staff button", Status.PASS);
			element("createStaffPopUpHeader").ajaxWait(WaitType.Visible);
			Report.log("Create Staff Pop is available", Status.PASS);
			element("txt_CreateStaffName").ajaxWait(WaitType.Visible).sendKeys(staffName);			
			WebElement element = driver.findElement(By.xpath("//label[text()='Name']/../div/p[text()='This field cannot be longer than 50 characters.']"));
			Report.log(element.getText(), Status.FAIL);
			element("btn_Cancel").ajaxWait(WaitType.Clickable).click();
			element("staffPageTitle").ajaxWait(WaitType.Pause);
			element("btn_CreateStaff").ajaxWait(WaitType.Visible);
		}
	}
	
	public void validateStaffNamePattern(String staffName){
		String regx="^[A-Z0-9]{1,50}$";
		boolean pattern = Pattern.matches(regx,staffName);		
		if(pattern){
			Report.log("Branch code is according to the pattern", Status.PASS);			
		} else {
			element("btn_CreateStaff").ajaxWait(WaitType.Clickable).click();
			Report.log("Clicked on Create staff button", Status.PASS);
			element("createStaffPopUpHeader").ajaxWait(WaitType.Visible);
			Report.log("Create Staff Pop is available", Status.PASS);
			element("txt_CreateStaffName").ajaxWait(WaitType.Visible).sendKeys(staffName);			
			WebElement element = driver.findElement(By.xpath("//label[text()='Name']/../div/p[contains(text(),'This field should follow pattern')]"));
			Report.log(element.getText(), Status.FAIL);
			element("btn_Cancel").ajaxWait(WaitType.Clickable).click();
			element("staffPageTitle").ajaxWait(WaitType.Pause);
			element("btn_CreateStaff").ajaxWait(WaitType.Visible);
		}
	}
}