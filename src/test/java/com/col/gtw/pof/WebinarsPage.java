package com.col.gtw.pof;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.col.gtw.core.CoreBase;

public class WebinarsPage extends CoreBase {
	
	@FindBy(xpath="//h1[contains(text(),'My Webinars')]")
	public WebElement webinarsPageHeaderLabel;

	
	
	
	@FindAll( { @FindBy(xpath="//a[contains(@href,'manageWebinar.tmpl')]/span") })
	public List<WebElement> myWebinarsList;
	
	@FindBy(id="upcomingWebinar-webinarSearchDateRangePicker_trig")
	public WebElement searchDateRange;
	
	@FindAll ( { @FindBy(xpath="//div[@id='upcomingWebinar-webinarSearchDateRangePicker__menu']/ul/li") })
	public List<WebElement> searchDateRangeMenu;
	
	@FindBy(id="upcomingWebinar-searchWebinarSearchBox")
	public WebElement searchWebinarField;
	
	
	@FindBy(xpath="//*[@class='myWebinarDate']")
	public WebElement webinarDate;
	
	@FindBy(xpath="(//*[@class='myWebinarDetailInfo'])[1]")
	public WebElement webinarTime;
	
	@FindBy(xpath="//div[@id='upcomingWebinar-noResult']/p")
	public WebElement noResult;
	
	
	public boolean isWebinarsPageVisible() {
		return webinarsPageHeaderLabel.isDisplayed();
	}
	
	
	
	public WebElement getWebinarItem(String webinarName) {
		
		Iterator<WebElement> itr = myWebinarsList.iterator();
		WebElement we;
		
		while(itr.hasNext()) {
			we = itr.next();	
			if(we.getText().equalsIgnoreCase(webinarName)) {
				//System.out.println(we.getText());
				return we;
			}
		}
		
		return null;
	}
	
  public void clickWebinarName(String webinarName) {
		
	  	WebElement we = getWebinarItem(webinarName);
		we.click();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
  
  
  
  public void selectSearchDateRange(String item) {
	  searchDateRange.click();
	  
	  Iterator<WebElement> itr = searchDateRangeMenu.iterator();
	  WebElement we;
	  
	  while(itr.hasNext()) {
		  we = itr.next();
		  
		  if(we.getText().contains(item)){
			  we.click();
			  break;
		  }
		  
	  }
  }
  
  public void setSearchWebinar(String name) {
	  searchWebinarField.clear();
	  searchWebinarField.sendKeys(name);
  }
  
  public void searchForWebinar(String dateRange,String name){
	  selectSearchDateRange(dateRange);
	  setSearchWebinar(name);
	  
	  try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
  
  
  public String getWebinarDate() {
	  return webinarDate.getText().replaceAll(" [A-Z][A-Z][A-Z]$", "");
  }
  
  public String getWebinarTime() {
	  return webinarTime.getText().replaceAll(" [A-Z][A-Z][A-Z]$", "");
  }
  
  public String getNoResultforSearchText() {
	  return noResult.getText();
  }
}
