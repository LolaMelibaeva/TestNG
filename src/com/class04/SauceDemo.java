package com.class04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class SauceDemo extends CommonMethods{
/*Identify Priority of Test Cases

https://www.saucedemo.com/

TC 1: Saucedemo Username1(parameters - username and password)
Step 1: Open browser and navigate to Saucedemo
Step 2: Enter username standard_user and enter password secret_sauce and click login button
Step 3: Verify user successfully logged in

TC 2: Saucedemo Username2(parameters - username and password)
Step 1: Open browser and navigate to Saucedemo
Step 2: Enter username problem_user and enter password secret_sauce and click login button
Step 3: Verify user successfully logged in


Note: Create BeforeMethod and AfterMethod annotations to open browser and logout from
 the application. Create a xml file with parameters username and password .*/
	
	@Parameters ({"browser", "url"})
	@BeforeMethod (alwaysRun=true)
	public void setUp(String chrome, String href) {
		setUpDriver(chrome, href);
		
	}
	@Parameters({"userName1", "password1"})
    @Test
    public void testUsername1 (String user1, String pass) {
        WebElement element=driver.findElement(By.xpath("//input[contains(@id,'user-name')]"));
        sendText(element, user1);
        element=driver.findElement(By.xpath("//input[contains(@id,'password')]"));
        sendText(element, pass);
        element= driver.findElement(By.xpath("//input[contains(@class,'btn_action')]"));
        click(element);
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='product_label']")).getText(), "Products");
}
	
	
	@Parameters({"userName2", "password2"})
    @Test
    public void testUsername2(String user1, String pass) {
        WebElement element=driver.findElement(By.xpath("//input[contains(@id,'user-name')]"));
        sendText(element, user1);
        element=driver.findElement(By.xpath("//input[contains(@id,'password')]"));
        sendText(element, pass);
        element= driver.findElement(By.xpath("//input[contains(@class,'btn_action')]"));
        click(element);
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='product_label']")).getText(), "Products");
}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
