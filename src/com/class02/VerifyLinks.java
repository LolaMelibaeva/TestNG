package com.class02;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;


import utils.CommonMethods;

public class VerifyLinks extends CommonMethods {

	public static void main(String[] args) {
		String url = "http://Newtours.Demoaut.com";

		setUpDriver("chrome", url);

		List<WebElement> links = driver.findElements(By.tagName("a"));

		System.out.println("Total links are " + links.size());

		for (int i = 0; i < links.size(); i++) {

			WebElement ele = links.get(i);

			String url1 = ele.getAttribute("href");

			verifyLinkActive(url1);

		}

	}

	public static void verifyLinkActive(String linkUrl) {
		try {
			URL url = new URL(linkUrl);

			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

			httpURLConnect.setConnectTimeout(3000);

			httpURLConnect.connect();

			if (httpURLConnect.getResponseCode() == 200) {
				System.out.println("Valid links--------"+linkUrl + " - " + httpURLConnect.getResponseMessage());
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println("Invalid links-----"+linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
			}
		} catch (Exception e) {

		}
	}

}
