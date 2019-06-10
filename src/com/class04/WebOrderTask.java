package com.class04;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class WebOrderTask extends CommonMethods {
/*Identify Priority of Test Cases

http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx

TC 1: WebOrder Verify Successful Login ( )
Step 1: Open browser and navigate to WebOrder
Step 2: Enter valid username, enter valid password and click on the  login button
Step 3: Verify user successfully logged in

TC 2: WebOrder Creating and verifying Users( )
Step 1: Create Two users and fill out all the required fields
 Step 2: Verify that user one name appears within the table 
 Step 3: Edit user one  and update user one’s State, assert the new updated State is displayed and save the changes.
 Step 4: Verify that user two name appears within the table 
 Step 5: Edit user two and update user two’s City and assert the new updated City is displayed and save the changes.

TC 3: WebOrder verifying Users( )
        Step 1 : Assert Both User one and User Two are displayed

Note: Create BeforeClass and AfterClass annotations to open browser and logout from the application. The creation of users two should depend on the creation of users one.The validation both users should depend on the creation of both users. Create xml file and share a screenshot of the test.
*/
	
	@BeforeClass(alwaysRun =true)
	public void setUp() {
		String url="http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx";
		setUpDriver("chrome", url);
	}
	
	//TC1
	@Test
	public void login() throws InterruptedException {
		WebElement element=driver.findElement(By.id("ctl00_MainContent_username"));
		sendText(element, "Tester");
		element=driver.findElement(By.id("ctl00_MainContent_password"));
		sendText(element, "test");
		element=driver.findElement(By.id("ctl00_MainContent_login_button"));
		click(element);
		element=driver.findElement(By.cssSelector("div.login_info"));
		String actual=element.getText();
		String expected="Welcome, Tester! | Logout";
		Assert.assertEquals(actual, expected);
		System.out.println(actual+" matches "+expected +" text.");
		Thread.sleep(3000);
	}
		
		@DataProvider()
		public Object [][] setUpData(){
			Object [][] data = new Object[2][10];
			data[0][0]="FamilyAlbum";
			data[0][1]="5";
			data[0][2]="Anna Smith";
			data[0][3]="123 Walnut Street";
			data[0][4]="Chantilly";
			data[0][5]="VA";
			data[0][6]="20147";
			data[0][7]="MasterCard";
			data[0][8]="4215456478941412";
			data[0][9]="11/20";
			
			
			data[1][0]="MyMoney";
			data[1][1]="10";
			data[1][2]="Lora Cohen";
			data[1][3]="456 Chestnut Street";
			data[1][4]="Alexandria";
			data[1][5]="VA";
			data[1][6]="20147";
			data[1][7]="Visa";
			data[1][8]="4115456468941410";
			data[1][9]="03/25";
			
			return data;
		}
	
	@Test(dataProvider ="setUpData",dependsOnMethods="login")
	
	public void creatingAndVerifyingUsers(String product, String quantity, String name,
			String street, String city, 
			String state, String zipcode, String ccType, String ccNumber, String expDate) throws InterruptedException {
		
		WebElement element=driver.findElement(By.xpath("//a[text()='Order']"));
		click(element);
		//choose product
		element=driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));
		selectValueFromDD(element, product);
		//quantity
		
		element=driver.findElement(By.cssSelector("input[id$='Quantity']"));
        sendText(element,quantity );
        //customer name
        element=driver.findElement(By.cssSelector("input[id$='Order_txtName']"));
        sendText(element, name);
        //Street 
        element=driver.findElement(By.cssSelector("input[id$='Order_TextBox2']"));
        sendText(element, street);
        //city
        element=driver.findElement(By.cssSelector("input[id$='Order_TextBox3']"));
        sendText(element, city);
        //state
        element=driver.findElement(By.cssSelector("input[id$='Order_TextBox4']"));
        sendText(element, state);
        //zipcode
        element=driver.findElement(By.cssSelector("input[id$='Order_TextBox5']"));
        sendText(element, zipcode);
        
        //choose CC
        List <WebElement> radio=driver.findElements(By.xpath("//input[@type='radio']"));
        selectFromRadioButton(radio, ccType);
        
        //enter CC number
        element=driver.findElement(By.cssSelector("input[id$='Order_TextBox6"));
        sendText(element, ccNumber);
        
        //Exp.date
        
        element=driver.findElement(By.cssSelector("input[id$='Order_TextBox1"));
        sendText(element, expDate);
        
        Thread.sleep(2000);
        //click Process
        WebElement clickProcess=driver.findElement(By.cssSelector("a#ctl00_MainContent_fmwOrder_InsertButton"));
        click(clickProcess);
        
        //click View all orders
        element=driver.findElement(By.xpath("//a[text()='View all orders']"));
         click(element);
	}
	

      
	@Test(dependsOnMethods="creatingAndVerifyingUsers")
      public void verifyAndUpdateUserOne() throws InterruptedException {
      List<WebElement> rows=driver.findElements(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr"));
     
     	 for (int i = 1; i < rows.size(); i++) {
     		if (driver.findElement(By.xpath("//table[@class='SampleTable']/tbody/tr["+i+"]")).getText()
					.contains("Anna Smith")) {
								
				click(driver.findElement(By.xpath("//table[@class='SampleTable']/tbody/tr["+i+"]/td[13]")));
				break;
			}
		}
      WebElement element=driver.findElement(By.cssSelector("input[id$='Order_TextBox4']"));
       sendText(element, "MD");      
      driver.findElement(By.xpath("//a[contains(@id,'UpdateButton')]")).click();
      
              }
            
          
   
	@Test(dependsOnMethods="creatingAndVerifyingUsers")
    public void verifyAndUpdateUserTwo() throws InterruptedException {
		
	
    List <WebElement> rows=driver.findElements(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr"));
   
   	 for (int i = 1; i < rows.size(); i++) {
   		if (driver.findElement(By.xpath("//table[@class='SampleTable']/tbody/tr["+i+"]")).getText()
				.contains("Lora Cohen")) {
							
			click(driver.findElement(By.xpath("//table[@class='SampleTable']/tbody/tr["+i+"]/td[13]")));
			break;
		}
	}
       
	
                
                WebElement element=driver.findElement(By.cssSelector("input[id$='Order_TextBox3']"));
          sendText(element, "Washington");
          element=driver.findElement(By.xpath("//a[contains(@id,'UpdateButton')]"));
          click(element);
	
	
            }
	
	
	
	@Test(dependsOnMethods="verifyAndUpdateUserOne")
	public void verifyUserOne() {
		click(driver.findElement(By.linkText("View all orders")));

		List<WebElement> cell = driver.findElements(By.xpath("//table[@class='SampleTable']/tbody/tr/td"));
		for(WebElement run:cell) {
			String value=run.getText();
			if(value.equals("Anna Smith")) {
				
				Assert.assertEquals(value, "Anna Smith");
				System.out.println("Anna Smith is present");
				break;
			}
			
		}
	}
	
	@Test(dependsOnMethods="verifyAndUpdateUserTwo")
	public void verifyUserTwo() {
		click(driver.findElement(By.linkText("View all orders")));

		List<WebElement> cell = driver.findElements(By.xpath("//table[@class='SampleTable']/tbody/tr/td"));
		for(WebElement run:cell) {
			String value=run.getText();
			if(value.equals("Lora Cohen")) {
				
				Assert.assertEquals(value, "Lora Cohen");
				System.out.println("Lora Cohen is present");
				break;
			}
			
		}
	}
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	