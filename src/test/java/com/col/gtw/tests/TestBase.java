package com.col.gtw.tests;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.col.gtw.core.CoreBase;
import com.col.gtw.pof.*;

public class TestBase extends CoreBase{
	
	public LoginPage loginPage_POF;
	public WebinarsPage webinarsPage_POF;
	public SchedulePage schedulePage_POF;
	public ManageWebinar manageWebinarPage_POF;
	public Navigation navigation_POF;
	public PropertiesConfiguration uiStrings;
	
	
	public void initPageObjects()
	{
		loginPage_POF = PageFactory.initElements(driver,LoginPage.class);
		webinarsPage_POF = PageFactory.initElements(driver,WebinarsPage.class);
		schedulePage_POF = PageFactory.initElements(driver,SchedulePage.class);
		manageWebinarPage_POF = PageFactory.initElements(driver,ManageWebinar.class);
		navigation_POF = PageFactory.initElements(driver,Navigation.class);
	}
	
	
	public void initResourceBundle() {
		String fileName = homeDirPath + "/src/test/resources/resourceBundles/resourceBundle-" + locale + ".properties";
		try {
			uiStrings = new PropertiesConfiguration(fileName);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@BeforeSuite
	public void suiteSetUp() {
		init();
		initPageObjects();
		initResourceBundle();
	}

	/*@BeforeMethod
	public void testSetUp() {
		//initWebDriver(browser);
		driver.get(appUrl);
	}*/
	
	@AfterMethod
	public void testTierDown() {
		CoreBase.driver.get(logoutUrl);
		}
	
	@AfterSuite
	public void suiteTierDown() {
		close();
	}
}
