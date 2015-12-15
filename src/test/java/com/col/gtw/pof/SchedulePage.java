package com.col.gtw.pof;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.col.gtw.core.CoreBase;
import com.col.gtw.core.Utils;

public class SchedulePage extends CoreBase {
	
	@FindBy(xpath="//h1[contains(text(),'Schedule a webinar')]") 
	public WebElement headerLabel;
	
	@FindBy(xpath="//id('scheduleForm')/div[1]/div/div[1]/div[1]/label") 
	public WebElement copyWebinarLabel;

	@FindBy (xpath="//a[@class='select2-choice']")
	public WebElement chooseAWebinar;
	
	@FindBys ( {@FindBy (xpath="//*[@role='option']") })
	public List<WebElement> webinarList;
	
	@FindBy(id="nameLabel")
	public WebElement titleLabel;
	
	@FindBy(id="name")
	public WebElement title;
	
	@FindBy(id="descriptionLabel")
	public WebElement descritionLabel;
	
	@FindBy(id="description")
	public WebElement description;
	
	@FindBy(xpath="//*[@id='scheduleForm']/div[1]/div/div[1]/div[4]/label") 
	public WebElement occursLabel;
	
	@FindBy(xpath="//a[@id='recurrenceForm_recurs_trig']/span[1]/span") 
	public WebElement occursField;
	
	@FindAll( { @FindBy (xpath="//*[@id='recurrenceForm_recurs__menu']/ul/li") })
	public List<WebElement> occursList;
	
	@FindBy(id="recur-on-date_trig")
	public WebElement onTheDateField;
	
	@FindAll( { @FindBy (xpath="//*[@id='recur-on-date__menu']/ul/li") })
	public List<WebElement> onTheDateList;
	
	@FindBy(id="webinarTimesForm.dateTimes_0.baseDate")
	public WebElement startDate;
	
	@FindBy(id="recurrenceForm.endDate")
	public WebElement endDate;
	
	@FindBy(xpath="//a[@title='Prev']")
	public WebElement calPrev;
	
	@FindBy(xpath="//a[@title='Next']")
	public WebElement calNext;
	
	@FindBy(xpath="//div[@class='dp-close ui-icon']")
	public WebElement calClose;
	
	
	@FindBy (xpath="//span[@class='ui-datepicker-month']")
	public WebElement calMonth;
	
	@FindBy (xpath="//span[@class='ui-datepicker-year']")
	public WebElement calYear;
	
	@FindBy(id="webinarTimesForm.dateTimes_0.startTime")
	public WebElement startTime;
	
	@FindBy(xpath="//a[@id='webinarTimesForm_dateTimes_0_startAmPm_trig']/span[1]/span")
	public WebElement startTimeAMPM;
	
	@FindBy(xpath="//*[@id='webinarTimesForm_dateTimes_0_startAmPm__menu']/ul/li[1]")
	public WebElement startTimeAMmenu;
	
	@FindBy(xpath="//*[@id='webinarTimesForm_dateTimes_0_startAmPm__menu']/ul/li[2]")
	public WebElement startTimePMmenu;
	
	@FindBy(id="webinarTimesForm.dateTimes_0.endTime")
	public WebElement endTime;
	
	@FindBy(xpath="//a[@id='webinarTimesForm_dateTimes_0_endAmPm_trig']/span[1]/span")
	public WebElement endTimeAMPM;
	
	@FindBy(xpath="//*[@id='webinarTimesForm_dateTimes_0_endAmPm__menu']/ul/li[1]")
	public WebElement endTimeAMmenu;
	
	@FindBy(xpath="//*[@id='webinarTimesForm_dateTimes_0_endAmPm__menu']/ul/li[2]")
	public WebElement endTimePMmenu;
	
	@FindBy(id="addAnother")
	public WebElement addAnotherSessionField;
	
	@FindAll ({@FindBy(xpath="//a[contains(@id,'deleteDate')]") })
	public List<WebElement> customRemoveDateList;
	
	@FindAll ({@FindBy(xpath="//input[contains(@id,'baseDate')]") })
	public List<WebElement> customSessionDateList;
	
	@FindBy(id="webinarTimesForm_timeZone_trig")
	public WebElement webinarTZ;

	@FindAll({ @FindBy(xpath="//li[contains(text(),'GMT')]")})
	public List<WebElement> webinarTZMenu;
	
	@FindAll ({ @FindBy(xpath="//input[@type='radio']")})
	public List<WebElement> registrationType;
	
	@FindBy(id="language_trig")
	public WebElement languageField;
	
	@FindAll( { @FindBy (xpath="//*[@id='language__menu']/ul/li") })
	public List<WebElement> languageMenu;
	
	@FindBy(id="schedule.submit.button")
	public WebElement scheduleButton;

	@FindBy(xpath="//input[@id='name']/../p")
	public WebElement titleErrorMsg;
	
	public By banner = By.xpath("//*[contains(@class,'banner-content')]/p");
	
	
	@FindBy(xpath="(//*[@data-handler='selectDay'])[1]")
	public WebElement earliestDateInCal;
	
	
	
public void setWebinarTitle(String name) {

	title.clear();
	title.sendKeys(name);
}


public void setDescription(String desc) {
	description.clear();
	description.sendKeys(desc);
}

public void clickScheduleButton() {
	scheduleButton.click();
}

public boolean isSchedulePageVisible() {
	return headerLabel.isDisplayed();
}

public void selectChooseAWebinar(String name) {
	chooseAWebinar.click();
	
	Iterator<WebElement> itr = webinarList.iterator();
	WebElement we;
	String weName;
	
	while(itr.hasNext())
	{
		we = itr.next();
		weName = we.getText();
		
		if(weName.contains(name)) {
			we.click();
			}
	}
}

	public void setStartTime(String value){
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		startTime.sendKeys(selectAll);
		startTime.sendKeys(value);
	}
	

	public void selectStartTimeAMPM(String name) {
		startTimeAMPM.click();
		if(name.equalsIgnoreCase("AM"))
		{
			startTimeAMmenu.click();
		}else{
			startTimePMmenu.click();
		}
	}

	
	public void setEndTime(String value){
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		endTime.sendKeys(selectAll);
		endTime.sendKeys(value);
	}
	
	
	public void selectEndTimeAMPM(String name) {
		endTimeAMPM.click();
		if(name.equalsIgnoreCase("AM"))
		{
			endTimeAMmenu.click();
		}else{
			endTimePMmenu.click();
		}
	}
	
	public void selectOccurence(String type){
		occursField.click();
				
		Iterator<WebElement> itr = occursList.iterator();
		WebElement we;
		String weText;
		
		while(itr.hasNext()) {
			we = itr.next();
			weText = we.getText();
			
			if(weText.equalsIgnoreCase(type)) {
				we.click();
			}
				
		}
	}
	
	public void selectOnTheDate(String type){
		onTheDateField.click();
				
		Iterator<WebElement> itr = onTheDateList.iterator();
		WebElement we;
		String weText;
		
		while(itr.hasNext()) {
			we = itr.next();
			weText = we.getText();
			
			if(weText.contains(type)) {
				we.click();
			}
				
		}
	}
	
	
	public void openStartDateCalendar(){
		startDate.click();
	}
	
	public void openEndDateCalendar(){
		endDate.click();
	}
	
	public void closeCalendar(){
		calClose.click();
	}
	
	
	public void selectStartDate(String date, String month, String year){
		openStartDateCalendar();
		
		while(!calYear.getText().equals(year))
		{
			calNext.click();
		}
		
		while(!calMonth.getText().contains(month))
		{
			calNext.click();
		}
		
		driver.findElement(By.linkText(date)).click();
		
	}
	
	
	public void selectEndDate(String date, String month, String year){
		
		openEndDateCalendar();
		
		
		while(!calPrev.getAttribute("class").contains("ui-state-disabled")){
			calPrev.click();
		}
		
		while(!calYear.getText().equals(year))
		{
			calNext.click();
		}
		
		while(!calMonth.getText().contains(month))
		{
			calNext.click();
		}
		
		driver.findElement(By.linkText(date)).click();
		
	}
	
	
	public void selectWebinarTimeZone(String tz){
		languageField.click(); //Workaround to display longer list of TZs 
		webinarTZ.click();
		
		Iterator<WebElement> itr = webinarTZMenu.iterator();
		WebElement we;
		while(itr.hasNext())
		{
			we = itr.next();
			if(we.getText().contains(tz)) {
				Actions builder = new Actions(driver);
				builder.moveToElement(we).build().perform();
				we.click();
			}
		}
	}
	
public void selectRegistrationType(String type){
		
		if(type.equalsIgnoreCase("all")) {
			registrationType.get(0).click();
		}else if(type.equalsIgnoreCase("individual")){
			registrationType.get(1).click();
		}
		
	}
	

public void selectWebinarLanguage(String langName){
	
	languageField.click();
	
	Iterator<WebElement> itr = languageMenu.iterator();
	WebElement we;
	while(itr.hasNext())
	{
		we = itr.next();
		if(we.getText().contains(langName)) {
			we.click();
		}
	}
}


public void addAnotherSession() {
	
	addAnotherSessionField.click(); 
	
	String index;
	int currIndex = customRemoveDateList.size() - 1;
	String idAttr = customRemoveDateList.get(currIndex).getAttribute("id");
	String[] idAttrList = idAttr.split("_");
	index = idAttrList[1];
	//System.out.println(size);
	
	String iStartDate = "webinarTimesForm.dateTimes_"+index+".baseDate";
	String iStartTime = "webinarTimesForm.dateTimes_"+index+".startTime";
	String iStartTimeAMPM = "webinarTimesForm_dateTimes_"+index+"_startAmPm_trig";
	String iStartTimeAMmenu = "//*[contains(@id,'webinarTimesForm_dateTimes_"+index+"_startAmPm__menu')]/ul/li[1]";
	String iStartTimePMmenu = "//*[contains(@id='webinarTimesForm_dateTimes_"+index+"_startAmPm__menu')]/ul/li[2]";
	String iEndTime = "webinarTimesForm.dateTimes_"+index+".endTime";
	String iEndtTimeAMPM = "webinarTimesForm_dateTimes_"+index+"_endAmPm_trig";;
	String iEndTimeAMmenu = "//*[contains(@id,'webinarTimesForm_dateTimes_"+index+"_endAmPm__menu')]/ul/li[1]";;
	String iEndTimePMmenu= "//*[contains(@id,'webinarTimesForm_dateTimes_"+index+"_endAmPm__menu')]/ul/li[2]";;
	
	//addAnotherSessionField.click();
	// to be implemented
	
	driver.findElement(By.id(iStartDate)).click();
	driver.findElement(By.id(iStartTimeAMPM)).click();
	driver.findElement(By.xpath(iStartTimeAMmenu)).click();
	//closeCalendar();
	
}

public void removeCustomSession(String index) {
	String ss = "deleteDate_"+index;	
	driver.findElement(By.id(ss)).click();
}


public String getTitleErrorMsg() {
	return titleErrorMsg.getText();
}

public String getBannerText() {
	WebElement we = (new WebDriverWait(driver, 10))
			.until(ExpectedConditions.presenceOfElementLocated(banner));
	return we.getText();
}

public String getEarliestDateInCal() {
	return earliestDateInCal.getText();
}

public boolean isWebinarCreationSuccessful2() {
	try {
		
	
		if(titleErrorMsg.isDisplayed()) { 
			return false;
		} else {
			return true;
		}
	} catch (NoSuchElementException e) {
		return true;
	}
}

public boolean isWebinarCreationSuccessful() {
	WebElement we = (new WebDriverWait(driver, 10))
			.until(ExpectedConditions.presenceOfElementLocated(banner));
	return we.isDisplayed();
	
}


}

