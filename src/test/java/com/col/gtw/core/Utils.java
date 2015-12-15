package com.col.gtw.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class Utils {

	public static ArrayList<String> readCSV(String filePath) {
		
		ArrayList<String> fileContents = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line;
			while(( line = br.readLine()) != null){
				    if(!line.startsWith("#")) {
					fileContents.add(line);		
				    }
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fileContents;
	}

	
	public static HashMap<String,String> getTestDataAsHashMap(ArrayList<String> testDataSet) {
		
		String[] varList = testDataSet.remove(0).replace("\"", "").split(",");
		HashMap<String, String> testDataMap = new HashMap<String, String>();
		
		
		Iterator<String> itr = testDataSet.iterator();
		int dataSetCounter = 0;
		
		
		while(itr.hasNext()){
			//System.out.println(itr.next().replace("\"", ""));
			String[] testData;
			testData = itr.next().replace("\"", "").split(",");
			
			for(int i=1;i<testData.length;i++)
			{
					
					testDataMap.put(varList[i]+"_"+testData[0], testData[i]);
					
			}	
			dataSetCounter++;
				
		}
		testDataMap.put("dataSetCounter", String.valueOf(dataSetCounter));
		return testDataMap;
	
	}


public static String[][] getTestDataAs2DArray(ArrayList<String> testDataSet) {
		
		String[] col = testDataSet.remove(0).replace("\"", "").split(",");
		int row = testDataSet.size();
		String[][] testData2D = new String[row][col.length];
		
		
		Iterator<String> itr = testDataSet.iterator();
		int i = 0;
		
		
		while(itr.hasNext()){
			//System.out.println(itr.next().replace("\"", ""));
			String[] testData;
			testData = itr.next().replace("\"", "").split(",");
						
			for(int j=0;j<testData.length;j++)
			{		
					testData2D[i][j] = testData[j];
			}	
			i++;
				
		}
		
		return testData2D;
	
	}


	public static String getCurrentTime_milliseconds(){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentTime_seconds(){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentTime_uptoMinutes() {
		return new SimpleDateFormat("hh:mm").format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentTime_uptoHours() {
		return new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentAMPMmarker() {
		return new SimpleDateFormat("a").format(Calendar.getInstance().getTime());
	}
	public static String getCurrentFullDate(){
		return new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentDate(){
		return new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentDayShort(){
		return new SimpleDateFormat("EEE").format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentDay(){
		return new SimpleDateFormat("EEEE").format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentMonth(){
		return new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentMonthNameShort(){
		return new SimpleDateFormat("MMM").format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentMonthName(){
		return new SimpleDateFormat("MMMM").format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentYear(){
		return new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
	}
	
	public static String getEpoch(){
		return String.valueOf(System.currentTimeMillis());
	}
	
	public static String getRequiredTime_uptoMinutes(long milliseconds) {
		return new SimpleDateFormat("hh:mm").format(System.currentTimeMillis() + milliseconds);
	}
	
	public static String getRequiredTime_uptoHours(long milliseconds) {
		return new SimpleDateFormat("HH").format(System.currentTimeMillis() + milliseconds);
	}
	
	public static String getRequiredAMPMmarker(long milliseconds) {
		return new SimpleDateFormat("a").format(System.currentTimeMillis() + milliseconds);
	}
	public static String getRequiredFullDate(long milliseconds){
		return new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis() + milliseconds);
	}
	
	public static String getRequiredDate(long milliseconds){
		return new SimpleDateFormat("dd").format(System.currentTimeMillis() + milliseconds);
	}
	
	public static String getRequiredDayShort(long milliseconds){
		return new SimpleDateFormat("EEE").format(System.currentTimeMillis() + milliseconds);
	}
	
	public static String getRequiredDay(long milliseconds){
		return new SimpleDateFormat("EEEE").format(System.currentTimeMillis() + milliseconds);
	}
	
	public static String getRequiredMonth(long milliseconds){
		return new SimpleDateFormat("MM").format(System.currentTimeMillis() + milliseconds);
	}
	
	public static String getRequiredMonthNameShort(long milliseconds){
		return new SimpleDateFormat("MMM").format(System.currentTimeMillis() + milliseconds);
	}
	
	public static String getRequiredMonthName(long milliseconds){
		return new SimpleDateFormat("MMMM").format(System.currentTimeMillis() + milliseconds);
	}
	
	public static String getRequiredYear(long milliseconds){
		return new SimpleDateFormat("yyyy").format(System.currentTimeMillis() + milliseconds);
	}
}
