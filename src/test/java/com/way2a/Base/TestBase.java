package com.way2a.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.way2a.utilities.ExcelReader;
import com.way2a.utilities.ExtentManager;
import com.way2a.utilities.TestUtil;

public class TestBase {

	/*
	 * WebDriver Properties Logs ExtentReports DB Excel Mail
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;

	@BeforeSuite
	public void setUp() {
		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("config file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				// System.setProperty("webdriver.chrome.driver",
				// "C:\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("chrome browser loaded");

			}
			if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.debug("Firefox browser loaded");

			}
			if (config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				log.debug("IE browser loaded");

			}

			driver.get(config.getProperty("testsiteurl"));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("impicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
		}

	}

	public boolean isElementresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {

			return false;
		}

	}

	public void click(String locator) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector((OR.getProperty(locator)))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath((OR.getProperty(locator)))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id((OR.getProperty(locator)))).click();
		}
		test.log(LogStatus.INFO, " Clicking on   :  " + locator);

	}

	public void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector((OR.getProperty(locator)))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath((OR.getProperty(locator)))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id((OR.getProperty(locator)))).sendKeys(value);
		}
		test.log(LogStatus.INFO, " Typing in    :  " + locator + " entered value as :" + value);

	}
	public static void verifyEquals(String expected, String actual) throws IOException{
		System.out.println("this is verify equals method");
		try{
			Assert.assertEquals(actual, expected);
						
		}catch(Throwable e){
			TestUtil.captureScreenshot();
			//ReportNG
			/*Reporter.log("<br>"+"Verificaton failure :+"+e.getMessage()+"<br>");
		    Reporter.log("<a target=\"_blank\" href="+TestUtil.screenShotName+"><img src="+TestUtil.screenShotName+" height=200 width=200></img></a>");
		    Reporter.log("<br>");
		    
		   //Extent Reports
		    test.log(LogStatus.FAIL, "Verification failedwith an exception ;"+e.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenShotName));*/
		}
	}
	static WebElement dropdown;
	public void select(String locator, String value){
	if (locator.endsWith("_CSS")) {
		dropdown =driver.findElement(By.cssSelector((OR.getProperty(locator))));
	} else if (locator.endsWith("_XPATH")) {
		dropdown=driver.findElement(By.xpath((OR.getProperty(locator))));
	} else if (locator.endsWith("_ID")) {
		dropdown=driver.findElement(By.id((OR.getProperty(locator))));
	}
	Select select=new Select(dropdown);
	select.selectByVisibleText(value);
	test.log(LogStatus.INFO, " Selecting from dropdown   :  " + locator + " selected value as :" + value);

}
	
	
	
	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		log.debug("Test execution completed !!");

	}
}
