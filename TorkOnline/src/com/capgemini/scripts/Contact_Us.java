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

public class Contact_Us {
	
	public String TestCase="Contact_Us";
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
//		if (!BasicOperation.FailCase) 
//		main1.printMessage(TestCase, Integer.parseInt(DataMap.get("index")), "script executed successfully!");
//		else
//		main1.printMessage(TestCase, Integer.parseInt(DataMap.get("index")), "script execution fail!");
		
		
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
	
	///////// Contact Us--TC_21,TC_74/////////////////////
	public void testcaseMain() throws InterruptedException, BiffException,
			Exception {
		BasicOperation.setDataMap(DataMap);
		BasicOperation.setReporter(TestCase, webDriver, reporter);
		BasicOperation.setPropertiesDataMap(objDataMap);
		
		stepExecutor.launchApplication("URL", DataMap, webDriver);

		
		try {

			contactUSLink();    
			contactUSLinkBox();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	        Created By - Pratibha Bhosale
	        Cretaed On - 11/16/2016 
	        Test case covered - Contact us--TC_21
	        Objective - Utility Navigator_Contact us  link functionality .
	        
	 */	
		
	private void contactUSLink() throws IOException, InterruptedException {	

		try{
			BasicOperation.waitForPageToLoad(webDriver);
			BasicOperation.click("contact_us", webDriver);			
			reporter.writeStepResult("Contact US Link validation", "Contact US Link validation", "Expected: " + "Contact US link is displayed at top right corner", "Pass", "Contact US link is validated", true, webDriver);
		}
		catch(Exception e){
			e.printStackTrace();
			reporter.writeStepResult("Contact US Link validation", "Contact US Link validation", "Expected: " + "Contact US link is not displayed at top right corner", "Fail", "Contact US link is validated", true, webDriver);
		}

	}	
	
	/** 
	        Created By - Pratibha Bhosale
	        Cretaed On - 11/16/2016 
	        Test case covered - Contact us--TC_74
	        Objective - Click on Contact Us icon in utility navigation
	        
	 */	
	private void contactUSLinkBox() throws IOException, InterruptedException {	

		try{
			BasicOperation.waitForPageToLoad(webDriver);
			BasicOperation.click("contact_us", webDriver);	
			verify.verifyElementPresent(webDriver, objDataMap.get("ContactInfoBox"), "xpath","Verify contact information box is present");
			reporter.writeStepResult("Contact US Link box validation", "Contact US Link box validation", "Expected: " + "Contact US link box should be displayed", "Pass", "Contact US link box is validated", true, webDriver);
		}
		catch(Exception e){
			e.printStackTrace();
			reporter.writeStepResult("Contact US Link box validation", "Contact US Link box validation", "Expected: " + "Contact US link box is not displayed at top right corner", "Fail", "Contact US link box is not validated", true, webDriver);
		}

	}	
	
	
}
