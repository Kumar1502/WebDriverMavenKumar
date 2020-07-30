package com.way2a.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.way2a.Base.TestBase;
import com.way2a.utilities.TestUtil;

public class AddCustomerTest extends TestBase {
	
	@Test (dataProviderClass=TestUtil.class, dataProvider="dp")
	public void addCustomerTest(String firstName, String lastName, String postCode, String alertText) throws InterruptedException{
		
		click("addCustBtn_CSS");
		type("firstName_CSS",firstName);
		type("lastName_XPATH",lastName);
		type("postCode_CSS",postCode);
		click("addBtn_CSS");
		Thread.sleep(2000);
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue((alert.getText().contains(alertText)),"fetching alert text failed");
		alert.accept();
		Thread.sleep(2000);
		
		
	}
	
}
