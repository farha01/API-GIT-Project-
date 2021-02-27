package com.githubapi.framework.webdriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.githubapi.pages.BasePage;
import com.githubapi.utils.ExcelService;
import com.githubapi.utils.ReadDataFromProps;

public class WebDriverBase {

	static int counter;
	public static File folder;
	protected WebDriver driver;
	public static ExtentReports report = null;
	public static ExtentTest logger = null;
	
	public static Properties appProperties() throws IOException {
    Properties prop = ReadDataFromProps.readPropertiesFile(System.getProperty("user.dir")+"\\Resorces\\application.properties");
	return prop;
	}
	@DataProvider(name = "ApplicationTestData")
	public Iterator<Object[]> madataprovider(Method method) throws IOException {
		System.out.println("@DataProvider");
		System.out.println("counter===" + counter++);
		return new ExcelService().readTestDataFromExcel(appProperties().getProperty("APPTestDataWorkBookName"), "TestData",
				method.getName());
	}
	
	@BeforeSuite
	public void setUpSuite() {
		System.out.println("@BeforeSuite");
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/ProjectTestReport_"+BasePage.getuniqueIDByCurrentTime()+".html"));
		report=new ExtentReports();
		report.attachReporter(extent);
	}
	
	public static void initiateTestReport(String testcasename) {
		logger = report.createTest(testcasename);
	}
	
	public static ExtentTest getLogger() {
		return logger;		
	}

	@BeforeMethod(alwaysRun = true)
	public synchronized void setUp() throws IOException {
		System.out.println("@BeforeMethod");
		String browserType = WebDriverBase.appProperties().getProperty("browsername");
		
		if (browserType.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			
			driver= new ChromeDriver(options);
			
			DriverManager.setWebDriver(driver);
		} else if (browserType.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			DriverManager.setWebDriver(driver);
		}
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws IOException {
		System.out.println("@AfterMethod");
		report.flush();
		driver.quit();	
	}
	
	public static WebDriver getDriver() {
		return DriverManager.getDriver();
	}
	
}
