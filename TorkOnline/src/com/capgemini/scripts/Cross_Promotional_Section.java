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

/** 
        
        Created By - Sumit Kahar
        Cretaed On - 11/17/2016 
        Test case covered - Cross-Promotional Module
        Objective - To check the working of Cross-Promotional Module located on Home page 
        Parameters - <mention if reusable methods defined> 
        
        */

public class Cross_Promotional_Section {
	
	public String TestCase="Cross_Promotional_Section";
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
		BasicOperation.waitFor(5000);
		
		try{
			String testcase_sr = DataMap.get("Testcase ID");
			System.out.println("Test scriptsto be executed are :"+testcase_sr);
			
			if(testcase_sr.equals("TC_02")){			
				TC_02();
			}
			else if(testcase_sr.equals("TC_03")){			
				TC_03();
			}
			else if(testcase_sr.equals("TC_84")){			
				TC_84();
			}
			else if(testcase_sr.equals("TC_86")){	
				TC_86();
			}
			else if(testcase_sr.equals("TC_87")){	
				TC_87();
			}
			else if(testcase_sr.equals("TC_88")){	
				TC_88();
			}
			else if(testcase_sr.equals("TC_89")){	
				TC_89();
			}
			else {	
				System.out.println("...No Test case will be executed .....");
			}		

		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public void TC_02() throws Exception	{
		try	{
			System.out.println("--Home Page_In module title text is Non-clickable in cross promotional section.--");
				
			verify.verifyElementPresent(webDriver,objDataMap.get("cross_promo_id"),"id","Cross Promotional module is present");
			verify.verifyElementPresent(webDriver,objDataMap.get("testimonial_id"),"id","Testimonial module is present");
			System.out.println("--Verifying cross promotional module is above testimonial---");
			verify.verifyElementPresent(webDriver, objDataMap.get("crosspromo_above_test_verify"),"xpath","Cross Promotional module is present above testimonial");		
			BasicOperation.waitFor(1000);
			verify.headingClickable(webDriver, objDataMap.get("cross_heading"),"xpath");
			verify.imageClickable(webDriver, objDataMap.get("cross_imagelink"),"xpath", objDataMap.get("cross_img_hyperlink"), objDataMap.get("home_link"),objDataMap.get("cross_imagelink_title"));
		
			System.out.println("------Test Case 02 successfully completed------");
			BasicOperation.Wait(1000);
			} catch (Exception e) {			
				e.printStackTrace();
				reporter.writeStepResult(("TC_02 is Successfull ").toUpperCase(),
						"TC_02 is Successfull ", "Expected: "
								+ "TC_02 is failed ", "Fail",
						"TC_02 is failed ", true, webDriver);	
			}
	
	}
	public void TC_03() throws Exception	{
		try	{
			System.out.println("--Home Page_Readmore can take the user to relevant landing page in cross promotional section.--");
				
			verify.verifyElementPresent(webDriver,objDataMap.get("cross_promo_id"),"id","Cross Promotional module is present");
			BasicOperation.Wait(3000);
		
			verify.verifyElementPresent(webDriver,objDataMap.get("testimonial_id"),"id","Testimonial module is present");
			BasicOperation.Wait(3000);
			System.out.println("--Verifying cross promotional module is above testimonial---");
			verify.verifyElementPresent(webDriver, objDataMap.get("crosspromo_above_test_verify"),"xpath","Cross Promotional module is present above testimonial");
			BasicOperation.Wait(3000);
					
			System.out.println("--Read More--");

			verify.clickable(webDriver, objDataMap.get("cross_readmore1"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross1_title"));
			BasicOperation.Wait(3000);		
			verify.clickable(webDriver, objDataMap.get("cross_readmore2"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross2_title"));
			BasicOperation.Wait(3000);		
			verify.clickable(webDriver, objDataMap.get("cross_readmore3"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross3_title"));
			BasicOperation.Wait(3000);		
			verify.clickable(webDriver, objDataMap.get("cross_readmore4"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross4_title"));
			BasicOperation.Wait(3000);
				
			System.out.println("------Test Case 03 successfully completed------");
		} catch (Exception e) {			
			e.printStackTrace();
			reporter.writeStepResult(("TC_03 is Successfull ").toUpperCase(),
					"TC_03 is Successfull ", "Expected: "
							+ "TC_03 is failed ", "Fail",
					"TC_03 is failed ", true, webDriver);	
		}
	}
	
	public void TC_84() throws Exception	{		
		try	{
			System.out.println("--Click on cross promotional intro heading and text--");		
	
			verify.headingClickable(webDriver, objDataMap.get("cross_heading"),"xpath");
			verify.imageClickable(webDriver, objDataMap.get("cross_imagelink"),"xpath", objDataMap.get("cross_img_hyperlink"), objDataMap.get("home_link"),objDataMap.get("cross_imagelink_title"));
		
			System.out.println("------Test Case 84 successfully completed------");
			} catch (Exception e) {			
			e.printStackTrace();
			reporter.writeStepResult(("TC_84 is Successfull ").toUpperCase(),
					"TC_84 is Successfull ", "Expected: "
							+ "TC_84 is failed ", "Fail",
					"TC_84 is failed ", true, webDriver);	
			}
	}
	public void TC_86() throws Exception	{		
		try	{
			System.out.println("--Click on any cross promotional box heading--");
		
			verify.clickable(webDriver, objDataMap.get("cross_headingnText1"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross1_title"));
			BasicOperation.Wait(3000);		
			verify.clickable(webDriver, objDataMap.get("cross_headingnText2"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross2_title"));
			BasicOperation.Wait(3000);		
			verify.clickable(webDriver, objDataMap.get("cross_headingnText3"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross3_title"));
			BasicOperation.Wait(3000);		
			verify.clickable(webDriver, objDataMap.get("cross_headingnText4"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross4_title"));
			BasicOperation.Wait(3000);				
			System.out.println("------Test Case 86 successfully completed------");
		} catch (Exception e) {			
		e.printStackTrace();
		reporter.writeStepResult(("TC_86 is Successfull ").toUpperCase(),
				"TC_86 is Successfull ", "Expected: "
						+ "TC_86 is failed ", "Fail",
				"TC_86 is failed ", true, webDriver);	
		}
	}
	
	public void TC_87() throws Exception	{	
		try	{
			System.out.println("--Click on any cross promotional box text--");

			verify.clickable(webDriver, objDataMap.get("cross_headingnText1"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross1_title"));
			BasicOperation.Wait(3000);		
			verify.clickable(webDriver, objDataMap.get("cross_headingnText2"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross2_title"));
			BasicOperation.Wait(3000);		
			verify.clickable(webDriver, objDataMap.get("cross_headingnText3"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross3_title"));
			BasicOperation.Wait(3000);		
			verify.clickable(webDriver, objDataMap.get("cross_headingnText4"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross4_title"));
			BasicOperation.Wait(3000);				
		
			System.out.println("------Test Case 87 successfully completed------");
			} catch (Exception e) {			
				e.printStackTrace();
				reporter.writeStepResult(("TC_87 is Successfull ").toUpperCase(),
						"TC_87 is Successfull ", "Expected: "
								+ "TC_87 is failed ", "Fail",
						"TC_87 is failed ", true, webDriver);	
			}
	}
	
	public void TC_88() throws Exception	{	
		try	{
			System.out.println("--Click on any cross promotional box read more link--");
		
			verify.clickable(webDriver, objDataMap.get("cross_readmore1"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross1_title"));
			BasicOperation.Wait(3000);		
			verify.clickable(webDriver, objDataMap.get("cross_readmore2"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross2_title"));
			BasicOperation.Wait(3000);		
			verify.clickable(webDriver, objDataMap.get("cross_readmore3"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross3_title"));
			BasicOperation.Wait(3000);		
			verify.clickable(webDriver, objDataMap.get("cross_readmore4"),"xpath", objDataMap.get("home_link"),objDataMap.get("cross4_title"));
			BasicOperation.Wait(3000);
				
			System.out.println("------Test Case 88 successfully completed------");
		} catch (Exception e) {			
			e.printStackTrace();
			reporter.writeStepResult(("TC_88 is Successfull ").toUpperCase(),
					"TC_88 is Successfull ", "Expected: "
							+ "TC_88 is failed ", "Fail",
					"TC_88 is failed ", true, webDriver);	
		}
	}
	
	public void TC_89() throws Exception	{
		try	{
			System.out.println("--Click on any cross promotional box image.--");			

			verify.imageClickable(webDriver, objDataMap.get("cross_image2"),"xpath", objDataMap.get("cross_image_hyperlink2"), objDataMap.get("home_link"),objDataMap.get("cross_imagelink2_title"));
		
			System.out.println("------Test Case 89 successfully completed------");
			} catch (Exception e) {			
				e.printStackTrace();
				reporter.writeStepResult(("TC_89 is Successfull ").toUpperCase(),
						"TC_89 is Successfull ", "Expected: "
								+ "TC_89 is failed ", "Fail",
						"TC_89 is failed ", true, webDriver);	
			}
	}
	
	
}