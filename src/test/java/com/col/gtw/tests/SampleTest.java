package com.col.gtw.tests;

import java.text.Format;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.col.gtw.core.CoreBase;
import com.col.gtw.core.Utils;

public class SampleTest extends TestBase {



	@Test (groups="sample")
	public void colTest() {
		String webinarName = "TEST_"+Utils.getCurrentTime_seconds();
		String webinarDesc = "DESC_"+Utils.getCurrentTime_seconds();
		String expectedBanner = uiStrings.getProperty("webinar.createSuccess").toString();
		
		long inputStartTime = 3*24*60*60*1000;
		long inputEndTime = inputStartTime + 1*60*60*1000;
		
		String startDate = Utils.getRequiredDate(inputStartTime);
		String startMonth = Utils.getRequiredMonthNameShort(inputStartTime);
		String startYear = Utils.getRequiredYear(inputStartTime);
		String startTime = Utils.getRequiredTime_uptoMinutes(inputStartTime).replaceFirst("^0", "");
		String startTimeAMPM = Utils.getRequiredAMPMmarker(inputStartTime);
		
		String endTime = Utils.getRequiredTime_uptoMinutes(inputEndTime).replaceFirst("^0", "");
		String endTimeAMPM = Utils.getRequiredAMPMmarker(inputEndTime);
		
		String expectedDate = Utils.getRequiredDayShort(inputStartTime) + ", " + startMonth + " " + startDate;
		String expectedTime = startTime + " " + startTimeAMPM + " - " + endTime + " " + endTimeAMPM;
		
		loginPage_POF.logIn(username,password);
		navigation_POF.clickScheduleAWebinar();
		schedulePage_POF.setWebinarTitle(webinarName);
		schedulePage_POF.setDescription(webinarDesc);
		schedulePage_POF.selectStartDate(startDate, startMonth, startYear);
		schedulePage_POF.setStartTime(startTime);
		schedulePage_POF.selectStartTimeAMPM(startTimeAMPM);
		schedulePage_POF.endTime.click();
		schedulePage_POF.clickScheduleButton();
		Assert.assertTrue(schedulePage_POF.isWebinarCreationSuccessful(), "Scheduling a webinar failed");
		Assert.assertTrue(schedulePage_POF.getBannerText().contains(expectedBanner), "Success banner has incorrect text");
		navigation_POF.clickMyWebinars();
		Assert.assertNotNull(webinarsPage_POF.getWebinarItem(webinarName), "Webinar not found");
		webinarsPage_POF.searchForWebinar("7", webinarName);
		Assert.assertEquals(webinarsPage_POF.getWebinarDate(), expectedDate, "Webinar dates are mismatching");
		Assert.assertEquals(webinarsPage_POF.getWebinarTime(), expectedTime, "Webinar times are mismatching");
	}
	
	
	
	@Test (enabled = false)
	public void methodTrials(){
		
		loginPage_POF.logIn(username,password);
		navigation_POF.clickScheduleAWebinar();
		
		
		//schedulePage_POF.selectChooseAWebinar("TEST");
		
		schedulePage_POF.clickScheduleButton();
		Assert.assertEquals(schedulePage_POF.getTitleErrorMsg(), uiStrings.getProperty("webinar.emptyTitleError"));
		
		schedulePage_POF.setWebinarTitle("ABC");
		schedulePage_POF.setDescription("DESC_");
		//schedulePage_POF.selectChooseAWebinar("TEST");
		//schedulePage_POF.setStartTime("9:00");
		//schedulePage_POF.selectStartTimeAMPM("PM");
		//schedulePage_POF.setEndTime("11:00");
		schedulePage_POF.selectOccurence("Daily");
		//schedulePage_POF.addAnotherSession();
		
		//schedulePage_POF.selectOccurence("Monthly");
		//schedulePage_POF.selectOnTheDate("Second");
		//schedulePage_POF.selectStartDate("20","January","2016");
		//schedulePage_POF.selectEndDate("22", "January", "2016");
		//schedulePage_POF.selectWebinarTimeZone("Mountain Time");
		//schedulePage_POF.selectRegistrationType("individual");
		//schedulePage_POF.selectWebinarLanguage("Deu");
		//schedulePage_POF.selectWebinarTimeZone("Mountain");
		schedulePage_POF.clickScheduleButton();
		
		//schedulePage_POF.removeCustomSession("1");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test (groups="others")
	public void calStartDateActiveDatesTest() {
		String calDate;
		String curDate = Utils.getCurrentDate();
		
		loginPage_POF.logIn(username,password);
		navigation_POF.clickScheduleAWebinar();
		schedulePage_POF.openStartDateCalendar();
		calDate = schedulePage_POF.getEarliestDateInCal();
		
		if(Integer.valueOf(calDate) < Integer.valueOf(curDate)) {
			Assert.fail("Start Date Calender is displaying incorrect active dates");
		}else {
			Assert.assertTrue(true);
		}
		
}
	
	
	@Test (groups="others")
	public void calEndDateActiveDatesTest() {
		
		HashMap<String, String> testData = Utils.getTestDataAsHashMap((Utils.readCSV(homeDirPath+"/src/test/resources/datasets/calEndDateActiveDatesTest.csv")));
		String failedDataSets = null;
		String calDate;
				
		loginPage_POF.logIn(username,password);
		
		
		for(int i=1; i <= Integer.valueOf(testData.get("dataSetCounter")) ; i++) {
				navigation_POF.clickScheduleAWebinar();
				schedulePage_POF.selectOccurence("Daily");
				schedulePage_POF.selectStartDate(testData.get("date_"+i),testData.get("month_"+i),testData.get("year_"+i));
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				schedulePage_POF.openEndDateCalendar();
				calDate = schedulePage_POF.getEarliestDateInCal();
				
				if(Integer.valueOf(calDate) < Integer.valueOf(testData.get("date_"+i))) {
					failedDataSets = failedDataSets + " " + String.valueOf(i);
					try {
						captureScreen("SampleTest","calEndDateActiveDatesTest",String.valueOf(i));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
				navigation_POF.clickMyWebinars();
				
			
		}
		
		Assert.assertNull(failedDataSets, "Failed DataSets are : " + failedDataSets);
	
	}
	
	
	@Test (groups="others")
	public void createWebinarOneSession() {
		String webinarName = "TEST_"+Utils.getCurrentTime_seconds();
		String expectedBanner = uiStrings.getProperty("webinar.createSuccess").toString();
		String expectedCancelBanner = uiStrings.getProperty("webinar.cancelSuccess").toString();
		
		
		loginPage_POF.logIn(username,password);
		navigation_POF.clickScheduleAWebinar();
		schedulePage_POF.setWebinarTitle(webinarName);
		schedulePage_POF.setDescription("DESC_"+Utils.getCurrentTime_seconds());
		schedulePage_POF.clickScheduleButton();
		
		Assert.assertTrue(schedulePage_POF.isWebinarCreationSuccessful(), "Scheduling a webinar failed");
		Assert.assertTrue(schedulePage_POF.getBannerText().contains(expectedBanner),"Success banner has incorrect text");
		
		manageWebinarPage_POF.clickCancelWebinar(true);
		try {
		Assert.assertTrue(manageWebinarPage_POF.isWebinarCancellationSuccessful(), "Cancel a webinar failed");
		Assert.assertTrue(manageWebinarPage_POF.getBannerText().contains(expectedCancelBanner), "Cancel success banner has incorrect text");
		}catch (Exception e) {
			webinarsPage_POF.searchForWebinar("365", webinarName);
			Assert.assertEquals(webinarsPage_POF.getNoResultforSearchText(), uiStrings.getProperty("webinar.noResult"));
		}
	}
	
	@Test (groups="others")
	public void createWebinarDaily() {
		String webinarName = "TEST_"+Utils.getCurrentTime_seconds();
		String expectedBanner = uiStrings.getProperty("webinar.createSuccess").toString();
		String expectedCancelBanner = uiStrings.getProperty("webinar.cancelSuccess").toString();
		
		loginPage_POF.logIn(username,password);
		navigation_POF.clickScheduleAWebinar();
		schedulePage_POF.setWebinarTitle(webinarName);
		schedulePage_POF.setDescription("DESC_"+Utils.getCurrentTime_seconds());
		schedulePage_POF.selectOccurence("Daily");
		schedulePage_POF.selectStartDate("20","March","2016");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schedulePage_POF.selectEndDate("20","April","2016");
		schedulePage_POF.clickScheduleButton();
		
		
		Assert.assertTrue(schedulePage_POF.isWebinarCreationSuccessful(), "Scheduling a webinar failed");
		Assert.assertTrue(schedulePage_POF.getBannerText().contains(expectedBanner), "Success banner has incorrect text");
		
		manageWebinarPage_POF.clickCancelWebinar(true);
		try {
			Assert.assertTrue(manageWebinarPage_POF.isWebinarCancellationSuccessful(), "Cancel a webinar failed");
			Assert.assertTrue(manageWebinarPage_POF.getBannerText().contains(expectedCancelBanner), "Cancel success banner has incorrect text");
			}catch (Exception e) {
				webinarsPage_POF.searchForWebinar("365", webinarName);
				Assert.assertEquals(webinarsPage_POF.getNoResultforSearchText(), uiStrings.getProperty("webinar.noResult"));
			}
	}
	
	
	@Test(groups="others")
	public void noWebinarTtileError() {
		
		loginPage_POF.logIn(username,password);
		navigation_POF.clickScheduleAWebinar();
		schedulePage_POF.clickScheduleButton();
		
		Assert.assertEquals(schedulePage_POF.getTitleErrorMsg(), uiStrings.getProperty("webinar.emptyTitleError"),"No Title Error message is incorrect");
	}
	
	
	
	
	@DataProvider (name = "loginError")
	public String[][] getInvalidUserNamePasswdSignInData() {
		String[][] testData = Utils.getTestDataAs2DArray(Utils.readCSV(homeDirPath+"/src/test/resources/datasets/invalidUserNamePasswdSignIn.csv"));
		return testData;
	}
	
	@Test(dataProvider="loginError" , groups="others")
	public void invalidUserNamePasswdSignInSample(String uname, String pword, String errorID) {
		
		loginPage_POF.logIn(uname,pword);
		Assert.assertEquals(CoreBase.driver.findElement(By.id(errorID)).getText(),uiStrings.getProperty(errorID));
		
	}
}
