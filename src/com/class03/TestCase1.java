package com.class03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class TestCase1 extends CommonMethods {
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
	
	
	@BeforeMethod()
    public void setUp() {
        setUpDriver("chrome", "https://www.saucedemo.com/");
    }
    
    
    @Test(groups = {"Smoke1"})
    public void personOneLogin() {
        driver.findElement(By.xpath("//input[contains(@id,'user-name')]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[contains(@class,'btn_action')]")).click();
         driver.findElement(By.xpath("//button[text()='Open Menu']")).click();
        driver.close();
    }
    
    
    @Test(groups = {"Regression1"})
    public void personThreeLogin() {
        driver.findElement(By.xpath("//input[contains(@id,'user-name')]")).sendKeys("problem_user");
        driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[contains(@class,'btn_action')]")).click();
         driver.findElement(By.xpath("    //button[text()='Open Menu']")).click();
        driver.close();
    }
    
    @Test(groups = {"Regression2"})
    public void personFourLogin() {
        driver.findElement(By.xpath("//input[contains(@id,'user-name')]")).sendKeys("performance_glitch_user");
        driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[contains(@class,'btn_action')]")).click();
        driver.findElement(By.xpath("//button[text()='Open Menu']")).click();
        driver.close();
    }
    
    @AfterMethod
    public void close() {
        driver.close();
    }

	

}