package com.capgemini.scripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedTransferQueue;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.seleniumemulation.GetText;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Base.BaseDrivers;
import com.Base.BasicOperation;
import com.Base.MainController;
import com.Base.SCACommon;
import com.capgemini.driver.CreateDriver;
import com.capgemini.driver.ScriptExecutor;
import com.capgemini.driver.StepExecutor;
import com.capgemini.executor.ExecutionRowNumber;
import com.capgemini.utilities.ReadExcel;
import com.capgemini.utilities.Reporter;
import com.capgemini.utilities.Utilities;
import com.capgemini.utilities.Verification;


/** 
        
        Created By - Sumit Kahar
        Cretaed On - 11/23/2016 
        Test case covered - Home Module
        Objective - To check the working of Home page 
        Parameters - <mention if reusable methods defined> 
        
        */


public class Home
{
	
	public String TestCase="Home";
	MainController main1 = new MainController();
	DesiredCapabilities capabilities = new DesiredCapabilities();
	Reporter reporter = new Reporter(this.getClass().getSimpleName());
	CreateDriver driver = BaseDrivers.getDriver();
	RemoteWebDriver webDriver = null;
	private Utilities utils = new Utilities(reporter);
	private ScriptExecutor scriptExecutor = new ScriptExecutor();
	private ExecutionRowNumber executionRowNumber = new ExecutionRowNumber();
	// Object for calling verification functions
	private Verification verify = new Verification(reporter);
	WebDriverWait wait = null;
	private StepExecutor stepExecutor = new StepExecutor(reporter);
	private String StrExecutionStartTime = null;
	private long executionStartTime = 0;
	Map<String, String> DataMap = new HashMap();
	Map<String, String> objDataMap = new HashMap();
	Boolean sExecutionStatus;
	ReadExcel readExcel = new ReadExcel(reporter);
	ReadExcel readExcel1 = new ReadExcel(reporter);
	
	static String WindowHandle=null;
	private static String[] links = null;
	private static String[] linksText = null;
	private static int linksCount = 0;
	private static int linksTextCount = 0;

	public String getExecutionStartTime() {
		return StrExecutionStartTime;
	}

	public String getStartTime() {
		return String.valueOf(executionStartTime);
	}
	@SuppressWarnings("unchecked")
	public void executeTestcase(String browserName) throws Exception {
	int iNumberOfRows = 0;
	readExcel.setInputFile(System.getProperty("File"));
    readExcel1.setInputFile(System.getProperty("ObjFile"));
	readExcel.setSheetName(TestCase);
	readExcel1.setSheetName(TestCase);
	DataMap=readExcel.loadDataMap();
	iNumberOfRows = readExcel.getiNOfRows();
	
	reporter.start(reporter.calendar);
	StrExecutionStartTime = reporter.strStartTime;
	executionStartTime = reporter.startTime;
	
	reporter.ReportGenerator("Cafe#"+browserName);
	for (int i = 1; i <= iNumberOfRows; i++) {
		webDriver=BaseDrivers.getWebDriver();
		wait = new WebDriverWait(webDriver, 10);
		readExcel.setDataMap(DataMap);
		readExcel.readByIndex(i);
		System.out.println(capabilities.getBrowserName());
		reporter.setStrBrowser(capabilities.getBrowserName());
		reporter.addIterator(i);
		objDataMap = (Map<String, String>) readExcel.readPropertyFile();
		testcaseMain();	
		//if (!BasicOperation.FailCase) 
	//	main1.printMessage(TestCase, Integer.parseInt(DataMap.get("index")), "script executed successfully!");
	//	else
	//	main1.printMessage(TestCase, Integer.parseInt(DataMap.get("index")), "script execution fail!");
		
		
		//NextFunctionCall
		//WriteMaster.updateNextURL(TestCase,webDriver.getCurrentUrl());
		reporter.closeIterator();
		System.out.println("\t \t \t \t \t Row number: " + i);
		//webDriver.close();
	}
	String strStopTime = reporter.stop();
	reporter.strStopTime = strStopTime;
	float timeElapsed = reporter.getElapsedTime();
	reporter.timeElapsed = timeElapsed;
	reporter.CreateSummary("Cafe#"+browserName);
	main1.final_result(TestCase,reporter);
	}	

	public void testcaseMain() throws InterruptedException, BiffException,
	Exception
	{
		BasicOperation.setDataMap(DataMap);
		BasicOperation.setReporter(TestCase, webDriver, reporter);
		BasicOperation.setPropertiesDataMap(objDataMap);
		
		
		stepExecutor.launchApplication("URL", DataMap, webDriver);
		BasicOperation.waitFor(10000);
		
		try{
			String testcase_sr = DataMap.get("Testcase ID");

				System.out.println("test case executed are "+testcase_sr );	
			
				if(testcase_sr.equals("TC_153")){	
					TC_153();
				}
				else if(testcase_sr.equals("TC_56")){	
					TC_56();
				}
				else {	
					System.out.println("...No Test case will be executed .....");
				}		

			

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	public void TC_153() 
	{
		try
		{
			verify.verifyElementPresent(webDriver,objDataMap.get("Search_lnk"),"xpath","Search Link is present");	
			BasicOperation.waitFor(3000);			
			BasicOperation.click(objDataMap.get("Search_lnk"), webDriver);
			BasicOperation.waitFor(4000);
			System.out.println("Search link Clicked...........");

			verify.verifyElementPresent(webDriver,objDataMap.get("click_lnk"),"xpath","Click Link is present");
			BasicOperation.waitFor(3000);			
			BasicOperation.click(objDataMap.get("click_lnk"), webDriver);
			BasicOperation.waitFor(4000);
			System.out.println("Clicked if you are looking for brochures and videos, click here ...........");
		
			verify.verifyElementPresent(webDriver,objDataMap.get("Why_tork_lnk"),"xpath","Why_tork Link is present");
			BasicOperation.waitFor(3000);			
			BasicOperation.click(objDataMap.get("Why_tork_lnk"), webDriver);
			BasicOperation.waitFor(4000);
			System.out.println("Why_tork Link Clicked...........");
		
			verify.verifyElementPresent(webDriver,objDataMap.get("Resource_lnk"),"xpath","Resource Link is present");
			BasicOperation.waitFor(3000);				
			verify.clickable(webDriver, objDataMap.get("Resource_lnk"),"xpath", objDataMap.get("home_link"),objDataMap.get("Resource_title"));
			BasicOperation.waitFor(4000);
			System.out.println("Resource Link Clicked...........");
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
				
		}
		
	}
		
	public void TC_56() 
	{
		try
		{		
			verify.verifyElementPresent(webDriver,objDataMap.get("Recommendations_lnk"),"xpath","Recommendations link is present");
			BasicOperation.waitFor(3000);
			BasicOperation.click(objDataMap.get("Recommendations_lnk"), webDriver);		
			BasicOperation.click(objDataMap.get("Healthcare_lnk"), webDriver);
			
			verify.verifyElementPresent(webDriver,objDataMap.get("breadcrumbs_lnk"),"xpath","Breadcrumbs link is present");
			verify.verifyElementPresent(webDriver,objDataMap.get("breadc_curr_pg"),"xpath","Current page in breadcrumbs is highlighted.");
			verify.verifyElementPresent(webDriver,objDataMap.get("breadc_home_lnk"),"xpath","Breadcrumbs Home link is present and is clickable");
						
			BasicOperation.click(objDataMap.get("breadc_home_lnk"), webDriver);	
					
			
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
}
