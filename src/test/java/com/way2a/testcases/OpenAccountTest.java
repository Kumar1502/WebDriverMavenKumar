package com.way2a.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.way2a.Base.TestBase;
import com.way2a.utilities.TestUtil;

public class OpenAccountTest extends TestBase {
	
	@Test (dataProviderClass=TestUtil.class,dataProvider="dp")
	public void openAccountTest(String customer, String currency) throws InterruptedException{
		
		click("openaccount_CSS");
		select("customer_CSS",customer);
		select("currency_CSS",currency);		
		click("process_CSS");
		Thread.sleep(2000);
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		
		
		
		
		
	}
	@DataProvider
   public Object[][] getData(){
		
		String sheetName="AddCustomerTest";
		int rows=excel.getRowCount(sheetName);
		int cols=excel.getColumnCount(sheetName);
		
		//System.out.println(rows+" "+cols);
		
		Object[][] data=new Object[rows-1][cols];
		
		for (int rowNum=2;rowNum<=rows;rowNum++){
			
			for(int colNum=0;colNum<cols;colNum++){
				
				//data[0][0]
				//System.out.println(excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum);
				
				
			}
		}
		
		return data;
		
	   
   }
}
