package com.way2a.testcases;


import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.way2a.Base.TestBase;

public class BankManagerLoginTest extends TestBase {
	
	@Test
	public void loginAsBankManager() throws InterruptedException, IOException{
		
		verifyEquals("abc","xyz");
		Thread.sleep(3000);
		log.debug("Inside Logintest");
		click("bmlBtn_CSS");
	    Thread.sleep(3000);
	    Assert.assertTrue(isElementresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))), "Login is not successful");
	    log.debug("Login sccessfully executed");
	    Reporter.log("bankmanager login successful!!");
	    //Assert.fail("Login not successful");
	    
	 ;
	}

}
