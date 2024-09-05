package utils;


/*
 * DataUtility.java
 *
 * Description:
 * This program is used for creating and data that are required in EquityBrix application
 *
 * Usage:
 * randomEmailGenerator() is used for generating random email
 * that appends time in millis
 * and is used for creating a account.
 *
 *
 *
 */


import org.openqa.selenium.WebDriver;

public class DataUtility {
	
	WebDriver driver;

	public DataUtility(WebDriver driver) {
		this.driver=driver;
	}
	public String randomEmailGenerator() {
		 String randomemail = "lsnsponsor+"+System.currentTimeMillis()+"@gmail.com";
		 System.out.println("email"+randomemail);
		return randomemail;
	  }
}
