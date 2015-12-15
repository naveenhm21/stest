package com.col.gtw.pof;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.col.gtw.core.CoreBase;

public class Navigation extends CoreBase {

	@FindBy(name="topNavLnk_logout")
	public WebElement logout;
	
	@FindBy(xpath="//a[contains(@href,'schedule.tmpl')]")
	public WebElement scheduleLink;
	
	@FindBy(xpath="//a[contains(@href,'webinars.tmpl')]")
	public WebElement myWebinarLink;
	
	
	
	public void clickLogout() {
		logout.click();
}
	
	
	public void clickScheduleAWebinar()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scheduleLink.click();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void clickMyWebinars()
	{
		
		//myWebinarLink.click();
		CoreBase.driver.get(CoreBase.appUrl);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
