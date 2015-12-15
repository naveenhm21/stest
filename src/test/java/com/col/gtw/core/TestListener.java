package com.col.gtw.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	
	
	public void takeScreenShot(String className, String testName) throws Exception{
    	
    	 File scrFile = ((TakesScreenshot)CoreBase.driver).getScreenshotAs(OutputType.FILE);
         String[] name = className.split("\\.");
         int i = name.length - 1;
         
            try {
				FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/test-output/images/"+
									name[i]+"/"+testName+".png"));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
	
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		
		System.out.println("*** Error "+arg0.getInstanceName()+"#"+arg0.getName().toString()+" test has failed ***");
       	try {
			takeScreenShot(arg0.getInstanceName().toString(),arg0.getName().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
