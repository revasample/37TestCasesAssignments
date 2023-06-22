package com.salesforce.tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.salesforce.tests.*;

import com.tekarch.utilities.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected static WebDriver driver;
	protected WebDriverWait wait;
	protected static Logger log = LogManager.getLogger(BaseTest.class);;
	protected static ExtentReportsUtility report = ExtentReportsUtility.getInstance();
	
//	public static void setUpForBeforeTest() {
//		log = LogManager.getLogger(BaseTest.class);
//	}	
	public void setUpBeforeTestMethod(@Optional("edge") String browName) {
		PropertiesUtility pro = new PropertiesUtility();
		Properties appProp = pro.loadFile("applicationDataProperties");
		String url = appProp.getProperty("url");
		launchBrowser(browName);
		goToUrl(url);
	}	
//	public void tearDownAfterTestMethod() {
//		driver.quit();
//		driver.close();
//		log.info("driver.....closed.......");
//	}
	public static void launchBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			
			log.info("Edge browser launched");
		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			log.info("Chrome browser launched");
		}
	}

	public static void salesforceLoginPage(String url, String userName, String password) {
		driver.get(url);

		WebElement usernameElement = driver.findElement(By.id("username"));
		enterText(usernameElement, userName, "username");
		log.info("username entered");

		WebElement passwordElement = driver.findElement(By.id("password"));
		enterText(passwordElement, password, "password");
		log.info("password entered");

		WebElement buttonElement = driver.findElement(By.id("Login"));
		clickElement(buttonElement, "login button");
		log.info("button clicked");
	}

	public static void enterText(WebElement element, String data, String objectName) {
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(data);
			log.info("Pass:"+objectName + " is entered to the username field");
			//report.logTestInfo("Pass:"+objectName + " is entered to the username field");
		} else {
			log.error("Fail:"+objectName + " element is not displayed");
		}
	}

	public static void clickElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.click();
			log.info("pass:" + objName + "element cleared");
		} else {
			log.error("fail:" + objName + "element not displayed");
		}
	}

	public static void clearElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.click();
			log.info("pass:" + objName + "element clicked");
		} else {
			log.error("fail:" + objName + "element not clicked");
		}
	}

	public static void goToUrl(String url) {
		driver.get(url);
		log.info(url + "is entered");
	}

	public static void closeBrowser() {
		driver.close();
		log.info("current browser closed");
	}

	public static String getPageTitle() {
		return driver.getTitle();
	}
	
	public static void refreshPage() {
		driver.navigate().refresh();
	}

	public static String getTextFromWebElement(WebElement element, String name) {
		if (element.isDisplayed()) {
			return element.getText();
		} else {
			log.info(name + "web element is not displated");
		}
		return null;
	}

	public static void displayAlert() {
		Alert alert = switchToAlert();
		alert.dismiss();
		log.info("Alert dismissed");
	}

	public static Alert switchToAlert() {
		Alert alert = driver.switchTo().alert();
		log.info("switch to alert");
		return alert;
	}

	public static void AcceptAlert(Alert alert) {
		log.info("Alert accepted");
		alert.accept();
	}

	public static String getAlertText(Alert alert) {
		log.info("extracting text in the alert");
		return alert.getText();
	}

	public void WaitUntilElementIsVisible(WebElement el, String objName) {
		log.info("waiting element " + objName + " for its visibility");
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(el));
	}

	public void WaitUntilPresenceOfElementLocatedBy(By locator, String objName) {
		log.info("waiting element " + objName + " for its visibility");
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public File getScreenshotOfThePage(WebDriver driver) {
		String date=new SimpleDateFormat("yyyy_MM=dd_hh_mm_ss").format(new Date());
		String curDir=System.getProperty("user.dir");
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		File imgFile=screenshot.getScreenshotAs(OutputType.FILE);
		File destFile=new File(Constants.SCREENSHOTS_DIRECTORY_PATH+date+".png");
		
		try {
			FileUtils.copyFile(imgFile, destFile);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return destFile;
	}

	public WebDriver returnDriverInstamce() {
		// TODO Auto-generated method stub
		return null;
	}

}
