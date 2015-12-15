package com.col.gtw.pof;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.col.gtw.core.CoreBase;

public class ManageWebinar extends CoreBase {
	
	By cancelWebinar = By.linkText("Cancel Webinar");
	
	@FindBy(id="confirmDeleteAll")
	public WebElement yesCancel;
	
	@FindBy(id="cancelDeleteAll")
	public WebElement noCancel;
	
	public By banner = By.xpath("//*[contains(@class,'banner-content')]/p");
	
		
	
	public void clickCancelWebinar(boolean confirm) {
		driver.findElement(cancelWebinar).click();
		if(confirm) {
			yesCancel.click();
		}else {
			noCancel.click();
		}
	}
	
	 public boolean isWebinarCancellationSuccessful() {
			WebElement we = (new WebDriverWait(driver, 10))
					.until(ExpectedConditions.presenceOfElementLocated(banner));
			return we.isDisplayed();
			
		}
		
		public String getBannerText() {
			WebElement we = (new WebDriverWait(driver, 10))
					.until(ExpectedConditions.presenceOfElementLocated(banner));
			return we.getText();
		}
		
	
}
