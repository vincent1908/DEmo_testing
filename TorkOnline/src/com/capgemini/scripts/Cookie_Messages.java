package com.capgemini.scripts;

import java.io.IOException;
import java.util.*;

import com.Base.*;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.driver.CreateDriver;
import com.capgemini.driver.ScriptExecutor;
import com.capgemini.driver.StepExecutor;
import com.capgemini.executor.ExecutionRowNumber;
import com.capgemini.executor.WriteMaster;
import com.capgemini.utilities.ReadExcel;
import com.capgemini.utilities.Reporter;
import com.capgemini.utilities.Utilities;
import com.capgemini.utilities.Verification;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Cookie_Messages {
	
	public String TestCase="Cookie_Messages";
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

	public String getExecutionStartTime() {
		return StrExecutionStartTime;
	}

	public String getStartTime() {
		return String.valueOf(executionStartTime);
	}
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
				
//			if (!BasicOperation.FailCase) 
//			main1.printMessage(TestCase, Integer.parseInt(DataMap.get("index")), "script executed successfully!");
//			else
//			main1.printMessage(TestCase, Integer.parseInt(DataMap.get("index")), "script execution fail!");
			
			
			//NextFunctionCall
			//WriteMaster.updateNextURL(TestCase,webDriver.getCurrentUrl());
			reporter.closeIterator();
			System.out.println("\t \t \t \t \t Row number: " + i);
			webDriver.close();
		}
		String strStopTime = reporter.stop();
		reporter.strStopTime = strStopTime;
		float timeElapsed = reporter.getElapsedTime();
		reporter.timeElapsed = timeElapsed;
		reporter.CreateSummary("Cafe#"+browserName);
		main1.final_result(TestCase,reporter);

	}	



		
	///////// Header--TC_70/////////////////////		
	public void testcaseMain() throws InterruptedException, BiffException,
			Exception {
		
		stepExecutor.launchApplication("URL", DataMap, webDriver);
		BasicOperation.waitFor(10000);

		try {
			torkAccessThruCookie();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		
	//NextFuncBody	
	/** 
	        Created By - Pratibha Bhosale
	        Cretaed On - 11/15/2016 
	        Test case covered - Header--TC_70
	        Objective - Header - Cookies message.
	        
	 */	

	private void torkAccessThruCookie() throws IOException, InterruptedException {		

		try{
			BasicOperation.waitForPageToLoad(webDriver);
			BasicOperation.click("cookie_header", webDriver);	
			BasicOperation.waitFor(5000);
			BasicOperation.waitForPageToLoad(webDriver);
			BasicOperation.click("cookie_cross",webDriver);
		}
		catch(Exception e){
			System.out.println(e.getMessage());			
		}

	}	
	
	
}
