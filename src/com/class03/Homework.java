package com.class03;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utils.CommonMethods;

public class Homework extends CommonMethods {
@BeforeClass
    
    public void setUp() {
        setUpDriver("chrome","https://opensource-demo.orangehrmlive.com/");
    }
    
    
    
    @Test
    public void title() {
        final String actual=driver.getTitle();
        final String expected="OrangeHRM";
        Assert.assertTrue(actual.equals(expected), "title is not correct");
        System.out.println("title is correct");
    
    }
    
    @Test(priority=1)
    public void logIn() {
        
        sendText(driver.findElement(By.cssSelector("input#txtUsername")), "Admin");
        sendText(driver.findElement(By.cssSelector("input#txtPassword")), "admin123");
        click(driver.findElement(By.cssSelector("input#btnLogin")));
                
        Assert.assertTrue(driver.findElement(By.cssSelector("a#welcome")).isDisplayed(), "User did not logIn");
        System.out.println("User logIn");
    }
    
    
    @Test(priority=2)
    public void addEmployee() {
            
        //boolean pim=driver.findElement(By.cssSelector("a#menu_pim_viewPimModule")).isDisplayed();
        SoftAssert soft=new SoftAssert();
        soft.assertTrue(driver.findElement(By.cssSelector("a#menu_pim_viewPimModule")).isDisplayed(), "Pim button is not displayed on a page");
        click(driver.findElement(By.cssSelector("a#menu_pim_viewPimModule")));
        soft.assertAll();
        
    
        soft.assertTrue(driver.findElement(By.cssSelector("input#btnAdd")).isDisplayed());
        click(driver.findElement(By.cssSelector("input#btnAdd")));
        soft.assertAll();
        
        String firstName="Name";
        String lastName="LastName";
        String ID=driver.findElement(By.cssSelector("input#employeeId")).getText();
        
        
        sendText(driver.findElement(By.cssSelector("input#firstName")),firstName);

        sendText(driver.findElement(By.cssSelector("input#lastName")),lastName);
        
        click(driver.findElement(By.cssSelector("input#btnSave")));
        
        String actual=driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).getText();
        soft.assertSame(firstName, actual);
        System.out.println("firstName match");
                
        actual=driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).getText();
        soft.assertSame(lastName, actual);
        System.out.println("lastName match");
        
        actual=driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).getText();
        soft.assertSame(ID, actual);
        System.out.println("ID match");
        
        
        
    }
    
    @Test(priority=3)
    public void employeeDetailsVerification() {
        String firstName="Name";
        String lastName="LastName";
        String ID="0011";
        
        
        click(driver.findElement(By.cssSelector("a#menu_pim_viewPimModule")));
        click(driver.findElement(By.cssSelector("a#menu_pim_viewEmployeeList")));
        WebElement id=driver.findElement(By.cssSelector("input#empsearch_id"));
		click(id);
		WebElement enterID=driver.findElement(By.cssSelector("input#empsearch_id"));
		sendText(enterID,"0011");
		WebElement searchBtn=driver.findElement(By.cssSelector("input#searchBtn"));
		click(searchBtn);
        
		List <WebElement> table=driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr"));
        
		 selectCellFromTable(table, firstName);
		 selectCellFromTable(table, lastName);       
             
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
