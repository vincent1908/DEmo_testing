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
import com.capgemini.driver.CreateDriver;
import com.capgemini.driver.ScriptExecutor;
import com.capgemini.driver.StepExecutor;
import com.capgemini.executor.ExecutionRowNumber;
import com.capgemini.utilities.ReadExcel;
import com.capgemini.utilities.Reporter;
import com.capgemini.utilities.Utilities;
import com.capgemini.utilities.Verification;

/** 
        
        Created By - Madhura Katkar 
        Cretaed On - 11/9/2016 
        Test case covered - Campaign Module 
        Objective - To check the working of Campaign Module located on Home page 
        Parameters - <mention if reusable methods defined> 
        
        */

public class Campaign
{
	
	public String TestCase="Campaign";
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
	Exception {

			BasicOperation.setDataMap(DataMap);
			BasicOperation.setReporter(TestCase, webDriver, reporter);
			BasicOperation.setPropertiesDataMap(objDataMap);
					
								
			stepExecutor.launchApplication("URL", DataMap, webDriver);
				
				
			CampaignImage();
			CampaignStaticText();
			campaignReadMore();

}


public void CampaignImage() {

	//######Start of Campaign Module>>TC_12########//

	BasicOperation.waitFor(2000);
	try{
	boolean imgCheck = webDriver.findElementByXPath("//*[@id='landingPage']/div[3]/div[1]/div/div/div[1]/div").isDisplayed();
	if (imgCheck == true){
		System.out.println("Expected Campaign section image is displayed");
	}
	else{
		System.out.println("Expected Campaign section image is not displayed");
	}
	} catch (Exception e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
				
	//######End of Campaign Module>>TC_12########//
	
}

public void CampaignStaticText() {

//######Start of Campaign Module>>TC_83########//

	BasicOperation.waitFor(2000);
	BasicOperation.click("CampaignText", webDriver);
	BasicOperation.waitFor(2000);
	String PageTitle = webDriver.getTitle();
	if (DataMap.get("ExpectedTitle").equals(PageTitle)){
		System.out.println("Static Text is clickable" + "Verification has been failed");
	}
	else{
		System.out.println("Static Text is not clickable" + "Verification has been completed successfully");	
	}
	//######End of Campaign Module>>TC_13########//
}		

public void campaignReadMore() {	

//######Start of Campaign Module>>TC_85########//

	BasicOperation.click("CampaignReadMore", webDriver);
	BasicOperation.waitFor(7000);
	String nxtPageTitle = webDriver.getTitle();
	if (DataMap.get("ExpectedTitle").equals(nxtPageTitle)){
		System.out.println("Test Case" + DataMap.get("Testcase_ID") + "has been completed successfully");	
	}
	else{
		System.out.println("Test Case" + DataMap.get("Testcase_ID") + "has been failed");
	}

//######End of Campaign Module>>TC_85########//
	
}
}