package com.col.gtw.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class CoreBase {

	public static WebDriver driver;
	public String homeDirPath=System.getProperty("user.dir");
	public String configPath = homeDirPath+"/src/test/resources/";
	public static String appUrl, browser, locale, logoutUrl, username, password;
	
	
	public void loadConfigs (String filePath) {
		String envType, fileName;
		
		envType = (System.getProperty("envType","default").equalsIgnoreCase("default") ) ? "qa" : System.getProperty("envType");
		fileName = filePath + "config-" + envType + ".properties";
		//System.out.println(fileName);
		
		try {
			PropertiesConfiguration config = new PropertiesConfiguration(fileName);
			appUrl = config.getString("base.url") + config.getString("landingPage");
			logoutUrl = config.getString("base.url") + config.getString("logoutLink");
			browser = config.getString("browser.type");
			locale = config.getString("browser.locale");
			username = config.getString("username");
			password = config.getString("password");
		} catch (Exception e) {
			System.out.println("Unable to load config values from file : " + fileName);
			System.exit(1);
		}
		
	}

	
	
	public void initWebDriver(String browser) {
		DesiredCapabilities dc;
		 if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", homeDirPath+"/src/test/resources/chromedriver.exe");
			dc = DesiredCapabilities.chrome();
			dc.setCapability("applicationCacheEnabled", "flase");
			driver = new ChromeDriver(dc);
		}else {
			//defaults to chrome
			System.setProperty("webdriver.chrome.driver", homeDirPath+"/src/test/resources/chromedriver.exe");
			dc = DesiredCapabilities.chrome();
			dc.setCapability("applicationCacheEnabled", "flase");
			driver = new ChromeDriver(dc);
		}
		 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	
		
	
	public void captureScreen(String className, String testName, String slNo) throws Exception{
    	
   	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
				FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/test-output/images/"+
									className+"/"+testName+"/"+slNo+".png"));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
   } 
	
	
	
	
	
	public void init() {
		loadConfigs(configPath);
		initWebDriver(browser);
		driver.get(appUrl);
		
		
	}
	
	public void close() {
		driver.quit();
	}
}


