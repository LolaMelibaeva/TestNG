package com.class03;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class TC1 extends CommonMethods{
	/*TC 1: Saucedemo Username1(tag the groups - Smoke1, Regression1)
	Step 1: Open browser and navigate to Saucedemo
	Step 2: Enter username standard_user and enter password secret_sauce and click login button
	Step 3: Verify user successfully logged inTC 
	2: Saucedemo Username2(tag the groups - Smoke2, Regression2)
	Step 1: Open browser and navigate to Saucedemo
	Step 2: Enter username problem_user and enter password secret_sauce and click login button
	Step 3: Verify user successfully logged in
	Note: Create BeforeMethod and AfterMethod annotations to open browser and logout from the
	 application. 
	Create a xml file and include smoke1, regression2 and exclude smoke2, regression1.*/
		
	@BeforeMethod(alwaysRun=true, groups="Smoke")
    public void setUp() {
        setUpDriver("chrome", "https://www.saucedemo.com/");
    }
    
    
    @Test(groups = {"Smoke"})
    public void testUsername1() {
        driver.findElement(By.xpath("//input[contains(@id,'user-name')]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[contains(@class,'btn_action')]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Products']")).isDisplayed(), true);
        
    }
    
    
    @Test(groups = {"Regression"})
    public void testUsername2() {
        driver.findElement(By.xpath("//input[contains(@id,'user-name')]")).sendKeys("problem_user");
        driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[contains(@class,'btn_action')]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Products']")).isDisplayed(), true);
       
    }
 
    
    @AfterMethod(alwaysRun=true,groups="Smoke")
    public void close() {
        driver.close();
    }

	

}

