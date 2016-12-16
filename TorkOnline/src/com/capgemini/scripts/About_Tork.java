package com.capgemini.scripts;

import java.io.IOException;
import java.util.*;

import com.Base.*;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class About_Tork {
	
	public String TestCase="About_Tork";
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
	String browser= null;

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
	browser =browserName;
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



		
	/////////About Tork--TC_17,TC_34,TC_131/////////////////////
	public void testcaseMain() throws InterruptedException, BiffException,
			Exception {
		BasicOperation.setDataMap(DataMap);
		BasicOperation.setReporter(TestCase, webDriver, reporter);
		BasicOperation.setPropertiesDataMap(objDataMap);
		
		stepExecutor.launchApplication("URL", DataMap, webDriver);


		try {

			navigateEditorialSection();
			checkFunctionality(browser);
			validateReadmoreEditorialCampaign();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		
	/** 
	        Created By - Pratibha Bhosale
	        Cretaed On - 11/22/2016 
	        Test case covered - About Tork--TC_17
	        Objective - About Tork_Image, video and link can be clickable in about tork page.
	        
	 */		
	public void checkFunctionality(String browser) throws InterruptedException, BiffException,Exception 
	{	
		try 
		{
			BasicOperation.scroll(webDriver,"Solution");
			BasicOperation.waitFor(4000);
			if(browser.equalsIgnoreCase("IE"))
			{
				BasicOperation.clickLink("IconLink", webDriver, "Click on Print icon link");
				BasicOperation.waitFor(4000);
			Runtime.getRuntime().exec("D:\\Automation_Projects_Home\\AutoIT\\Print.exe");
			}
			BasicOperation.waitFor(4000);
			BasicOperation.scroll(webDriver,"Solution");			
			BasicOperation.clickLink("ShareIcon", webDriver, "Click on Share icon link");
			Thread.sleep(4000);
			BasicOperation.childframeswitch(webDriver,"at3winshare-iframe");
			verify.verifyElementTextPresent(webDriver,objDataMap.get("ShareTitle"),"xpath",DataMap.get("sharetitle"),"Verify Share Title");
			BasicOperation.clickLink("Gmail", webDriver, "Click on Gmail");
			Thread.sleep(15000);
			for (String newwindow : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(newwindow); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			BasicOperation.enterTextValue("Email", DataMap, "gmailuser", webDriver, "Enter Gmail Username");
			BasicOperation.clickButton("Next", webDriver,"Click on Next button");
			BasicOperation.enterTextValue("Password", DataMap, "gmailpassword", webDriver, "Enter Gmail Password");
			BasicOperation.clickButton("SignIn", webDriver,"Click on SignIn button");
			Thread.sleep(10000);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	/** 
	        Created By - Pratibha Bhosale
	        Cretaed On - 11/16/2016 
	        Test case covered - About Tork--TC_131
	        Objective - Click on quote box
	        
	 */	
	public void navigateEditorialSection() throws InterruptedException, BiffException,Exception 
	{	
		try 
		{
			verify.verifyImageBroken(webDriver, objDataMap.get("TorkLogo"), "Verify presence of Tork logo on homepage");
			BasicOperation.scroll(webDriver,"Testimonial");
			BasicOperation.clickLink("AboutTork", webDriver, "Click on About Tork");
			Thread.sleep(5000);
			verify.verifyImageBroken(webDriver,objDataMap.get("AbtTorkBanner"),"Check About Tork Banner Image");
			verify.verifyElementTextPresent(webDriver, objDataMap.get("abouttorktitle"),"xpath", DataMap.get("abouttorktitle"),"Verify about tork title");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/** 
	        Created By - Pratibha Bhosale
	        Cretaed On - 11/16/2016 
	        Test case covered - About Tork--TC_34
	        Objective - Campaign Page_Readmore can take the user to relevant landing page in editorial campaign module.
	        
	 */	
	public void validateReadmoreEditorialCampaign() throws InterruptedException, BiffException,Exception 
	{	
		try 
		{
			BasicOperation.click("LeftNavigation_left", webDriver);
			BasicOperation.waitForPageToLoad(webDriver);
			BasicOperation.click("ReadMore1", webDriver);
			BasicOperation.waitForPageToLoad(webDriver);			
			verify.verifyElementTextPresent(webDriver, objDataMap.get("CampaignText"),"xpath", DataMap.get("campaigntitle"),"Verify campaign title");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
