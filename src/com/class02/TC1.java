package com.class02;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;

/*TC 1: OrangeHRM Title Validation

TC 2: OrangeHRM Successful Login 
https://opensource-demo.orangehrmlive.com/
Please make sure to use the following:
 annotations:
	@BeforeMethod
	@AfterMethod
	@Test

What would you do if you do not want to execute any specific test case?
What would you do if you want to execute any specific test case first?*/

public class TC1 extends CommonMethods {

	
	@BeforeMethod
	public void setUp() {
		setUpDriver("chrome", "https://opensource-demo.orangehrmlive.com/");
	
	
		String orangehrm=driver.getTitle();
	String expectedValue="OrangeHRM";
	if (orangehrm.equalsIgnoreCase(expectedValue)) {
		System.out.println("This is the right title");
	}else {
		System.out.println("This is NOT right title");
	}}
	@Test
	public void login () {
		sendText(driver.findElement(By.cssSelector("input#txtUsername']")), "Admin");
		sendText(driver.findElement(By.cssSelector("input#txtPassword']")), "admin123");
		driver.findElement(By.cssSelector("input#btnLogin")).click();
	}
	@AfterMethod
	public void closeDriver() {
	driver.close();
	}
}
