package com.class02;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class Homework extends CommonMethods {

/*Identify Priority of Test Cases

TC 1: OrangeHRM Verify Successful Login
Step 1: Open browser and navigate to OrangeHRM
Step 2: Enter valid UID and valid PWD and click login button
Step 3: Verify user successfully logged in

TC 2: OrangeHRM Add Employee
	Step 1: Click on PIM link and Add Employee
	Step 2: Provide Details and Save
	Step 3: Verify Employee is added 

TC 3: OrangeHRM Verify Employee Details
	Step 1: Click on PIM link and Employee List
	Step 2: Search for employee by it id
	Step 3: Verify Employee details are displayed 

Note: Create BeforeClass and AfterClass annotations to open browser and logout from the application*/

	String firstName="LadyGaga";
	String lastName="Goldman";
	String ID;
	@BeforeClass
	public void setUp() {
		setUpDriver("chrome", "https://opensource-demo.orangehrmlive.com/");
	}
	
	
	@Test(priority=0)
	public void login () {
		sendText(driver.findElement(By.xpath("//*[@id=\"txtUsername\"]")), "Admin");
		sendText(driver.findElement(By.xpath("//*[@id=\"txtPassword\"]")), "admin123");
		click(driver.findElement(By.cssSelector("input#btnLogin")));
		
		Assert.assertTrue(driver.findElement(By.cssSelector("a#welcome")).isDisplayed(), "User did not log in");
		System.out.println("User Login successfully");
	}
	@Test(priority=1)
	public void addEmpoyee() throws InterruptedException {
		click(driver.findElement(By.xpath("//*[@id=\"menu_pim_viewPimModule\"]/b")));
		click(driver.findElement(By.linkText("Add Employee")));
		sendText(driver.findElement(By.cssSelector("input#firstName")), "LadyGaga");
		sendText(driver.findElement(By.cssSelector("input#lastName")), "Goldman");
		click(driver.findElement(By.cssSelector("input#chkLogin")));
		sendText(driver.findElement(By.cssSelector("input#user_name")), "LadyGaga123");
		sendText(driver.findElement(By.cssSelector("input#user_password")), "Goldman@123");
		sendText(driver.findElement(By.cssSelector("input#re_password")), "Goldman@123");
	     waitForElementBeClickable(driver.findElement(By.cssSelector("select#status")),10);
	     ID=driver.findElement(By.cssSelector("input#employeeId")).getAttribute("value");
	     System.out.println("Employee ID is "+ID);
	     Thread.sleep(2000);
	     click(driver.findElement(By.xpath("//input[@value='Save']")));
		
		
		}
		
	@Test(priority=3)
	public void verifyEmpDetails() throws Exception {
		
		 
		WebElement pim=driver.findElement(By.cssSelector("a#menu_pim_viewPimModule"));
		click(pim);
		WebElement empList=driver.findElement(By.cssSelector("a#menu_pim_viewEmployeeList"));
		click(empList);
		WebElement id=driver.findElement(By.cssSelector("input#empsearch_id"));
		click(id);
		Thread.sleep(2000);
		WebElement enterID=driver.findElement(By.cssSelector("input#empsearch_id"));
		sendText(enterID, ID);		
		WebElement searchBtn=driver.findElement(By.cssSelector("input#searchBtn"));
		click(searchBtn);
		Thread.sleep(2000);
		List <WebElement> rows=driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td"));
		tableCompareCellValueToExpected(rows, "LadyGaga");
		tableCompareCellValueToExpected(rows, "Goldman");
		
		Thread.sleep(2000);
		}
	
	@Test (priority=4)
	
	public void logout() throws InterruptedException {
		WebElement welcomeAdmin=driver.findElement(By.xpath("//a[@id='welcome']"));
		
		Actions action=new Actions(driver);
		action.moveToElement(welcomeAdmin).click().perform();
		WebElement logout=driver.findElement(By.xpath("//*[@id=\"welcome-menu\"]/ul/li[2]/a"));
		click(logout);
		Thread.sleep(2000);	
	}
	
	
		@AfterClass	
		public void closeDriver() {
		driver.close();
}

}

