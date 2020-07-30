package com.way2a.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.way2a.Base.TestBase;

public class TestUtil extends TestBase {
	
	public static String screenShotPath;
	public static String screenShotName;
	
	public static void captureScreenshot() throws IOException{
		
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d=new Date();
		String screenShotName=d.toString().replace(":", " ").replace(" ", "_")+".jpg";
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenShotName));
	}
	
	@DataProvider(name="dp")
	   public Object[][] getData(Method m){
			
			String sheetName=m.getName();
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
