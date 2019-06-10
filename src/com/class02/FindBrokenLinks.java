package com.class02;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.CommonMethods;

public class FindBrokenLinks extends CommonMethods {

	public static void main(String[] args) {
		String url = "http://Newtours.Demoaut.com";
		//String url="https://opensource-demo.orangehrmlive.com/";
		setUpDriver("chrome", url);
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total links are " + links.size());
		for (WebElement link : links) {
			String linkURL = link.getAttribute("href");
			;
			
			try {
				URL url1=new URL(linkURL);
				HttpURLConnection conn=(HttpURLConnection) url1.openConnection();
				int code=conn.getResponseCode();
				if(code==200) {
					System.out.println("Link is valid---------" + linkURL+" - "+conn.getResponseMessage());
				} else {
					System.out.println("Link is broken-------" + linkURL+" - "+conn.getResponseMessage());
				}
			}
				catch (Exception e) {
					e.printStackTrace();
				}

			
		}
	
driver.close();
	}
}
