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

public class Corporate_Nav_Bar
{
	
	public String TestCase="Corporate_Nav_Bar";
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
			
					//legalInfo_TC_25();
					whyTork_TC_52_TC_55();
					//legalInfo_TC_26_TC_27();
					//TC_1();
					//learnMoreLink();
			
			

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	public void legalInfo_TC_25() throws InterruptedException
	{
		System.out.println("TC_25...............");
		SCACommon.scrollForTridion(webDriver,14,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);

		verify.verifyElementPresent(webDriver,objDataMap.get("legalinfo_Xpath"),"xpath","legalinfo Link is present");
		BasicOperation.waitFor(6000);
		
		
		BasicOperation.click(objDataMap.get("legalinfo_Xpath"), webDriver);
		BasicOperation.waitFor(4000);
		
		
		List<WebElement> LegalInFoPageLinks=webDriver.findElementsByXPath(".//*[@id='mainForm']//a");
		linksCount=LegalInFoPageLinks.size();
		
		
		System.out.println("No of Anchor Links:----"+linksCount);
		System.out.println("-----------------------------------");
		links=new String[linksCount];
		linksText=new String[linksCount];

		
		System.out.println("URL of the Links........................");
		for (int i = 0; i<LegalInFoPageLinks.size(); i++)

		{
			
				System.out.println(LegalInFoPageLinks.get(i).getAttribute("href"));
				//links[i] = LegalInFoPageLinks.get(i).getAttribute("href");
				
				
						
		}
		
		System.out.println();
		
		System.out.println("Text of the Links........................");
		
		System.out.println();
		
		for (int i = 0; i<LegalInFoPageLinks.size(); i++)
		{
			if((!LegalInFoPageLinks.get(i).getText().isEmpty()))
			{
				System.out.println("\n"+LegalInFoPageLinks.get(i).getText());
			}
			else
			{
				System.out.println("Contains Blank Values\n");
				
			}
		
		}
		
		
		
		
		
		
	}
		
	public void legalInfo_TC_26_TC_27() throws InterruptedException
	{
		
		System.out.println("Inside TC_26_27...........");
		
		SCACommon.scrollForTridion(webDriver,14,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);

		verify.verifyElementPresent(webDriver,objDataMap.get("legalinfo_Xpath"),"xpath","legalinfo Link is present");
		BasicOperation.waitFor(6000);
		
		
		BasicOperation.click(objDataMap.get("legalinfo_Xpath"), webDriver);
		BasicOperation.waitFor(4000);
		System.out.println("Legal Info Clicked...........");
		
		verify.verifyElementPresent(webDriver,objDataMap.get("LegatStmt_Xpath"),"xpath","Legal Stmt Link is present");
		BasicOperation.click(objDataMap.get("LegatStmt_Xpath"), webDriver);
		System.out.println("Legal Stmt Link Clicked...........");
		BasicOperation.waitFor(5000);
		
		SCACommon.scrollForTridion(webDriver,0,"pagetop","Reached Top");
		
		verify.verifyElementPresent(webDriver,objDataMap.get("PrivcyPolicy_Xpath"),"xpath","Privacy Policy Link is present");
		BasicOperation.click("PrivcyPolicy_Xpath", webDriver);
		System.out.println("Privacy Policy Clicked...........");
		
		
		BasicOperation.waitFor(2000);
		
		SCACommon.scrollForTridion(webDriver,0,"pagetop","Reached Top");
		
		verify.verifyElementPresent(webDriver,objDataMap.get("CookiesInfo_Xpath"),"xpath","Cookies Info Link is present");
		BasicOperation.click("CookiesInfo_Xpath", webDriver);
		System.out.println("Cookies Info Clicked...........");
		BasicOperation.waitFor(2000);
		
		SCACommon.scrollForTridion(webDriver,5,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(4000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("BckToTop_Xpath"),"xpath","Back To Top Link is present");
		BasicOperation.click("BckToTop_Xpath", webDriver);
		System.out.println("Back To Top Link Clicked...........");
		BasicOperation.waitFor(2000);
		
		
	}
	
	public void whyTork_TC_52_TC_55()
	{
	
		//verify.verifyElementPresent(webDriver,objDataMap.get("whyTorkLink_Xpath"),"xpath","Why Tork Link is present");
		System.out.println("Insise whyTork_TC_52_TC_55....................");
		
		SCACommon.scrollForTridion(webDriver,14,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);

		
		BasicOperation.click(objDataMap.get("whyTorkLink_Xpath"), webDriver);
		BasicOperation.waitFor(4000);
		System.out.println("whyTorkLink Clicked...........");
		
		String CrtUrl=null;
		CrtUrl=webDriver.getCurrentUrl();
		System.out.println(""+CrtUrl);
		if(CrtUrl.equalsIgnoreCase(objDataMap.get("chkURLTork")))
		{
			System.out.println("WhyTork URL Verify");
			reporter.writeStepResult(("Verify WhyTork URL ").toUpperCase(),
					"Verify WhyTork URL", "Expected: "
							+ "WhyTork URL Present", "Pass",
					"Verify WhyTork URL", true, webDriver);
			
		}
		else
		{
			//System.out.println("URl Not Found");
			reporter.writeStepResult(("Verify WhyTork URL ").toUpperCase(),
					"Verify WhyTork URL", "Expected: "
							+ "URl Not Found", "Fail",
					"Verify WhyTork URL", true, webDriver);
		}
		
		//==============TC_55==================
		SCACommon.scrollForTridion(webDriver,6,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(4000);
		verify.verifyImagePresent(webDriver,objDataMap.get("SCA_Logo_footer_Xpath"),"xpath","SCA Logo At Bottom");
		System.out.println("SCA_Logo_footer Verified...........");
		
		BasicOperation.waitFor(4000);
		verify.verifyImagePresent(webDriver,objDataMap.get("SCA_Logo_footer_Xpath"),"xpath","SCA Logo At Bottom");
		System.out.println("SCA_Logo_footer Verified...........");
		
		
		String copyright=null;
		copyright=webDriver.findElementByXPath(".//*[@id='logoFooter']/div/p").getText();
		System.out.println(""+copyright);
		if(copyright.contains("©"))
		{
			System.out.println("Copy right Test Present...............");
			reporter.writeStepResult(("Verify Copyright Text is present ").toUpperCase(),
					"Verify Copyright Text is present ", "Expected: "
							+ "Copyright Text is present..... ", "Pass",
					"Verify Copyright Text is present ", true, webDriver);
			
		}
		else
		{
			
			System.out.println("Text Not Found");
			
			reporter.writeStepResult(("Verify Copyright Text is present ").toUpperCase(),
					"Verify Copyright Text is present ", "Expected: "
							+ "Copyright Text Not Found..... ", "Fail",
					"Verify Copyright Text is present ", true, webDriver);
			
			
		}
		
		//==============TC_57==================
		BasicOperation.waitFor(4000);
		verify.verifyImagePresent(webDriver,objDataMap.get("Tork_Logo_Xpath"),"xpath","SCA Logo At Bottom");
		System.out.println("Tork Logo at Top Verified...........");
		BasicOperation.click("Tork_Logo_Xpath", webDriver);
		
		
		
	}
	
	
	

}
