@Gurukula
Feature: Branches and Staffs Menu

@CreateBranch
Scenario: Create a Branch
Given I Logged into Gurukula
When I create a Branch
Then I Logged out of Gurukula

@CreateStaff
Scenario: Create a Staff
Given I Logged into Gurukula
When I create a Staff
Then I Logged out of Gurukula

@ViewStaff
Scenario: View a Staff
Given I Logged into Gurukula
When I navigated to view Staff
Then Staff should be viewed
Then I Logged out of Gurukula

@EditStaff
Scenario: Edit a Staff
Given I Logged into Gurukula
When I edit a Staff
Then I Logged out of Gurukula

@DeleteStaff
Scenario: Delete Staff
Given I Logged into Gurukula
When I delete a Staff
Then I Logged out of Gurukula

@ViewBranch
Scenario: View a Branch
Given I Logged into Gurukula
When I navigated to view Branch
Then Branch should be viewed
Then I Logged out of Gurukula

@EditBranch
Scenario: Edit a Branch
Given I Logged into Gurukula
When I edit a Branch
Then I Logged out of Gurukula

@DeleteBranch
Scenario: Delete Branch
Given I Logged into Gurukula
When I delete a Branch
Then I Logged out of Gurukula

@ValidateBranchNameMinLength
Scenario: Validate Branch Name Min Length
Given I Logged into Gurukula
Then I validate the min length of BranchName
Then I Logged out of Gurukula

@ValidateBranchNameMaxLength
Scenario: Validate Branch Name Max Length
Given I Logged into Gurukula
Then I validate the max length of BranchName
Then I Logged out of Gurukula

@ValidateBranchNamePattern
Scenario: Validate Branch Name Sequence
Given I Logged into Gurukula
Then I validate the BranchName sequence
Then I Logged out of Gurukula

@ValidateBranchCodeMinLength
Scenario: Validate Branch Code Min Length
Given I Logged into Gurukula
Then I validate the min length of BranchCode
Then I Logged out of Gurukula

@ValidateBranchCodeMaxLength
Scenario: Validate Branch Code Max Length
Given I Logged into Gurukula
Then I validate the max length of BranchCode
Then I Logged out of Gurukula

@ValidateBranchCodePattern
Scenario: Validate Branch Code Sequence
Given I Logged into Gurukula
Then I validate the BranchCode sequence
Then I Logged out of Gurukula

@ValidateStaffNameMinLength
Scenario: Validate Staff Name Min Length
Given I Logged into Gurukula
Then I validate the min length of StaffName
Then I Logged out of Gurukula

@ValidateStaffNameMaxLength
Scenario: Validate Staff Name Max Length
Given I Logged into Gurukula
Then I validate the max length of StaffName
Then I Logged out of Gurukula

@ValidateStaffNamePattern
Scenario: Validate Staff Name Sequence
Given I Logged into Gurukula
Then I validate the StaffName sequence
Then I Logged out of Gurukula