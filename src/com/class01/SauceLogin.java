package com.class01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods2;

public class SauceLogin extends CommonMethods2 {
/*
 * TC 1: Swag Lab Title and Login Verification

@BeforeMethod should contain navigation to the URL and title verification

@Test should contain steps to login and “Product” word verification

@AfterMethod should logOut from the application and close the browser
 */
	
	@BeforeMethod
	public void setUp(){
		setUpDriver("chrome", "https://www.saucedemo.com/");	
	}
	
	@Test
	
	public void login () {
		sendText(driver.findElement(By.cssSelector("input#user-name")), "standard_user");
		sendText(driver.findElement(By.cssSelector("input#password")), "secret_sauce");
		clickElement(driver.findElement(By.xpath("//input[@type='submit']")));
		WebElement ele=driver.findElement(By.xpath("//div[text()='Products']"));
		String eleText=ele.getText();
		System.out.println(eleText);
		String expectedValue="Products";
		if (eleText.equals(expectedValue)) {
			System.out.println("The word " +eleText+" is displayed successfully");
		}else {
			System.out.println("Please verify your codes");
		}
	}
	
	@AfterMethod
	public void logout() throws InterruptedException {
		clickElement(driver.findElement(By.xpath("//*[@id=\"menu_button_container\"]/div/div[3]/div/button")));
		WebElement clickable=driver.findElement(By.xpath("//a[text()='Logout']"));
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.textToBePresentInElementLocated((By.xpath("//a[text()='Logout']")), "Logout"));
		clickElement(driver.findElement(By.cssSelector("a#logout_sidebar_link")));
		
		
		driver.close();
	}
}
