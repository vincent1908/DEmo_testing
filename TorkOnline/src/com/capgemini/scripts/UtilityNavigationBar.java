package com.capgemini.scripts;

import java.awt.Robot;
import java.awt.event.KeyEvent;
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
import com.sun.jna.platform.unix.X11.XClientMessageEvent.Data;

import org.openqa.selenium.remote.DesiredCapabilities;

public class UtilityNavigationBar {
	
	public String TestCase="UtilityNavigationBar";
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



		
			
	public void testcaseMain() throws InterruptedException, BiffException,
			Exception {
		BasicOperation.setDataMap(DataMap);
		BasicOperation.setReporter(TestCase, webDriver, reporter);
		BasicOperation.setPropertiesDataMap(objDataMap);
		
		stepExecutor.launchApplication("URL", DataMap, webDriver);


		try {
			String testcase_id = DataMap.get("Testcase ID") ;
			if(testcase_id.equals("TC_149")){
				TC_149();
			}
			else if(testcase_id.equals("TC_154")){
				TC_154();
			}
			else if(testcase_id.equals("TC_75")){
				TC_75();
			}
			else if(testcase_id.equals("TC_77")){
				TC_77();
			}
			else if(testcase_id.equals("TC_139")){
				TC_139();
			}
			else if(testcase_id.equals("TC_140")){
				TC_140();
			}
			else if(testcase_id.equals("TC_156")){
				TC_156();
			}
			else if(testcase_id.equals("TC_19_TC_72")){
				TC_19_TC_72();
			}
			else if(testcase_id.equals("TC_115")){
				TC_115();
			}
			else if(testcase_id.equals("TC_63")){
				TC_63();
			}
			else if(testcase_id.equals("TC_63_TC_64")){
				TC_63_TC_64();
			}
			else if(testcase_id.equals("TC_117")){
				TC_117();
			}
			else if(testcase_id.equals("TC_118")){
				TC_118();
			}
			else if(testcase_id.equals("TC_119")){
				TC_119();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void TC_156() {
		// TODO Auto-generated method stub
		BasicOperation.click("Tork_Logo", webDriver);
		BasicOperation.waitForPageToLoad(webDriver);
		BasicOperation.click("Search_Btn_Xpath", webDriver);
		BasicOperation.waitFor(3000);
		BasicOperation.enterTextValue("SearchField", DataMap, "BlankSpaceEntry", webDriver, "entering blank search value");
		BasicOperation.waitFor(5000);
		BasicOperation.click("SearchButton", webDriver);
		BasicOperation.waitForPageToLoad(webDriver);
		verify.verifyPageTitle(webDriver, "ProductPageTitle", DataMap);
		BasicOperation.click("Filter1", webDriver);
		///////////need verification////
		
	}


	private void TC_140() {
		// TODO Auto-generated method stub
		BasicOperation.click("Tork_Logo", webDriver);
		BasicOperation.waitForPageToLoad(webDriver);
		BasicOperation.click("ContactInfo", webDriver);
		BasicOperation.waitFor(3000);
		verify.verifyElementPresent(webDriver, objDataMap.get("Contact_Title"), "xpath", "Verify title");
		verify.verifyElementPresent(webDriver, objDataMap.get("Contact_Fname"), "xpath", "Verify First name");
		verify.verifyElementPresent(webDriver, objDataMap.get("Contact_Lname"), "xpath", "Verify Last name");
		verify.verifyElementPresent(webDriver, objDataMap.get("Contact_Email"), "xpath", "Verify email address");
		verify.verifyElementPresent(webDriver, objDataMap.get("Contact_Company"), "xpath", "Verify company name");
		verify.verifyElementPresent(webDriver, objDataMap.get("Contact_Phone"), "xpath", "Verify phone number");
		verify.verifyElementPresent(webDriver, objDataMap.get("Contact_Add"), "xpath", "verify address");
		verify.verifyElementPresent(webDriver, objDataMap.get("Contact_zip"), "xpath", "Verify zipcode");
		verify.verifyElementPresent(webDriver, objDataMap.get("Contact_city"), "xpath", "verify city");
		verify.verifyElementPresent(webDriver, objDataMap.get("Contact_message"), "xpath", "Verify message");
		verify.verifyElementPresent(webDriver, objDataMap.get("SendMeCopy"), "xpath", "Verify send me a copy checkbox");
		verify.verifyElementPresent(webDriver, objDataMap.get("CloseButton"), "xpath", "Verify close button");
		verify.verifyElementPresent(webDriver, objDataMap.get("SubmitButton"), "xpath", "Verify submit button");
		
	}

	private void TC_139() {
		// TODO Auto-generated method stub
		BasicOperation.click("Tork_Logo", webDriver);
		BasicOperation.waitForPageToLoad(webDriver);
		BasicOperation.click("Search_Btn_Xpath", webDriver);
		BasicOperation.waitFor(3000);
		BasicOperation.enterTextValue("SearchField", DataMap, "SearchField", webDriver, "entering search value");
		BasicOperation.waitFor(5000);
		BasicOperation.click("SearchButton", webDriver);
		BasicOperation.waitForPageToLoad(webDriver);
		verify.verifyElementPresent(webDriver, objDataMap.get("Article"), "xpath", "verify Aricle section is present");
		
	}

	private void TC_77() {
		// TODO Auto-generated method stub
		BasicOperation.click("Tork_Logo", webDriver);
		BasicOperation.waitForPageToLoad(webDriver);
		BasicOperation.click("Search_Btn_Xpath", webDriver);
		BasicOperation.enterTextValue("SearchField", DataMap, "SearchField", webDriver, "entering search value");
		BasicOperation.waitFor(5000);
		verify.verifyTextValue(webDriver, objDataMap.get("SearchSuggestion"), "xpath", DataMap.get("SearchField"));
		
		
	}

	private void TC_75() {
		// TODO Auto-generated method stub
		BasicOperation.click("Tork_Logo", webDriver);
		BasicOperation.waitForPageToLoad(webDriver);
		BasicOperation.click("ProductTab", webDriver);
		verify.verifyElementPresent(webDriver, objDataMap.get("ProductListTab"), "xpath", "Verify product list page exists");
	}

	private void TC_154() {
		verify.verifyElementPresent(webDriver,objDataMap.get("Search_Btn_Xpath"),"xpath","Search Box is present");
		BasicOperation.waitForPageToLoad(webDriver);
		BasicOperation.waitFor(3000);
		System.out.println("Search Box present...........");
		
		BasicOperation.click("Search_Btn_Xpath", webDriver);
		BasicOperation.waitFor(6000);
		
		BasicOperation.click(objDataMap.get("SearchBX_Brochures_Link_Xpath"), webDriver);
		BasicOperation.waitFor(8000);
		verify.verifyElementPresent(webDriver, objDataMap.get("VideoBroachure"), "xpath", "Verify all broachure and video present");
		
	}

	private void TC_149() throws InterruptedException {
		BasicOperation.waitForPageToLoad(webDriver);	
		SCACommon.selectMultipleProductList(webDriver, objDataMap, verify, "Verification for Products");	
		BasicOperation.waitFor(5000);
		BasicOperation.click("ProductTab", webDriver);	
		BasicOperation.waitForExpectedElement("Xpath",objDataMap.get("ShoppingList"),webDriver );			
		BasicOperation.clickLink("ShoppingList", webDriver, "Click on Shopping List");			
		BasicOperation.waitFor(5000);
		
		Set<String> windowIterator = webDriver.getWindowHandles();
		for(String window:windowIterator){	
			BasicOperation.childframeswitch(webDriver, window);
		}		
		BasicOperation.waitForPageToLoad(webDriver);
		BasicOperation.waitFor(5000);
		BasicOperation.waitForExpectedElement("xpath", objDataMap.get("ProductListLink"), webDriver);
		BasicOperation.scroll(webDriver, "EmailSelected");
		BasicOperation.click("EmailSelected", webDriver);
		BasicOperation.waitFor(3000);
		BasicOperation.enterTextValue("EnterName", DataMap, "Name", webDriver, "Entering name for email");
		BasicOperation.click("SendEmail", webDriver);			
		verify.verifyElementTextPresent(webDriver, objDataMap.get("EnterValueForEmailMessage"), "Xpath", DataMap.get("EmailValidationText"), "Validation of Email field");
		
	}
	
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 12/06/2016 
	        Test case covered - Utility Navigation--TC_19_TC_72
			Objective - Click on distributor logo in utility navigation
	        
	 */	
	
	
	private void TC_19_TC_72() throws InterruptedException {
		try
		{
			BasicOperation.gotoURL(DataMap,"URL", webDriver, "Go to homepage");
			BasicOperation.waitFor(5000);
			verify.verifyElementPresent(webDriver,objDataMap.get("DF_icon"), "xpath", "Verify presence of Distributor icon");
			BasicOperation.clickElement("DF_icon", webDriver, "Click on Distributor finder");
			BasicOperation.waitFor(5000);
			verify.verifyPageTitle(webDriver, "DF_Page_Title", DataMap);	
		}
		catch(Exception e)
		{
			
		}

	}
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 12/06/2016 
	        Test case covered - Utility Navigation--TC_115
			Objective - Check list of distributors	        
	 */		
	
	
	private void TC_115() throws InterruptedException {	
		try
		{
			BasicOperation.gotoURL(DataMap,"URL", webDriver, "Go to homepage");
			BasicOperation.waitFor(5000);
			verify.verifyElementPresent(webDriver,objDataMap.get("DF_icon"), "xpath", "Verify presence of Distributor icon");
			BasicOperation.clickElement("DF_icon", webDriver, "Click on Distributor finder");
			BasicOperation.waitFor(5000);
			List<WebElement> dfList = BasicOperation.getElements("DF_list");
			if(dfList.size()>0)
			{
				reporter.writeStepResult("Verify Distributor links are loaded","Expected Distributor links should get loaded on left side","NA","Pass","Expected Distributor links gets loaded on left side", true, webDriver);
			}
			else
			{
				reporter.writeStepResult("Verify Distributor links are loaded","Expected Distributor links should get loaded on left side","NA","Fail","Expected Distributor links does not get loaded on left side", true, webDriver);
			}
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 12/06/2016 
	        Test case covered - Utility Navigation--TC_63,TC_64
			Objective - Enter postcode without space in distributor and check the content	        
	 */		
	
	
	private void TC_63() throws InterruptedException {	
		try
		{
			BasicOperation.gotoURL(DataMap,"URL", webDriver, "Go to homepage");
			BasicOperation.waitFor(5000);
			verify.verifyElementPresent(webDriver,objDataMap.get("DF_icon"), "xpath", "Verify presence of Distributor icon");
			BasicOperation.clickElement("DF_icon", webDriver, "Click on Distributor finder");
			BasicOperation.waitFor(5000);
			BasicOperation.enterTextValue(objDataMap.get("DF_Textbox"), DataMap, "DF_postcode", webDriver,"Enter postcode");
			BasicOperation.waitFor(2000);
			BasicOperation.clickElement("DF_Address", webDriver, "Click on Distributor finder autosuggest address");
			BasicOperation.waitFor(3000);
			verify.verifyElementPresent(webDriver,objDataMap.get("DF_map_point"), "xpath", "Check address appears on map");
			verify.verifyElementPresent(webDriver,objDataMap.get("DF_list_actual"), "xpath", "Check selected distributor address appears on list");
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 12/06/2016 
	        Test case covered - Utility Navigation--TC_63,TC_64
			Objective - Enter postcode with space in distributor and check the content	        
	 */	
	
	private void TC_63_TC_64() throws InterruptedException {	
		try
		{
			BasicOperation.gotoURL(DataMap,"URL", webDriver, "Go to homepage");
			BasicOperation.waitFor(5000);
			verify.verifyElementPresent(webDriver,objDataMap.get("DF_icon"), "xpath", "Verify presence of Distributor icon");
			BasicOperation.clickElement("DF_icon", webDriver, "Click on Distributor finder");
			BasicOperation.waitFor(5000);
			BasicOperation.enterTextValue(objDataMap.get("DF_Textbox"), DataMap, "DF_postcode_wo_space", webDriver,"Enter postcode");
			BasicOperation.waitFor(2000);
			BasicOperation.clickElement("DF_Address", webDriver, "Click on Distributor finder autosuggest address");
			BasicOperation.waitFor(3000);
			verify.verifyElementPresent(webDriver,objDataMap.get("DF_map_point"), "xpath", "Check address appears on map");
			verify.verifyElementPresent(webDriver,objDataMap.get("DF_list_actual"), "xpath", "Check selected distributor address appears on list");
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 12/06/2016 
	        Test case covered - Utility Navigation--TC_118
			Objective - Verify distributor links more than four are shown when clicked on View More link,
			also required distributor finder is shown when searched by any city name	        
	 */	
	
	private void TC_118() throws InterruptedException {	
		try
		{
			BasicOperation.gotoURL(DataMap,"URL", webDriver, "Go to homepage");
			BasicOperation.waitFor(5000);
			verify.verifyElementPresent(webDriver,objDataMap.get("DF_icon"), "xpath", "Verify presence of Distributor icon");
			BasicOperation.clickElement("DF_icon", webDriver, "Click on Distributor finder");
			BasicOperation.waitFor(5000);
			BasicOperation.enterTextValue(objDataMap.get("DF_Textbox"), DataMap, "DF_city", webDriver,"Enter DF name");
			BasicOperation.waitFor(2000);
			BasicOperation.clickElement("viewMoreLink", webDriver, "Click on View More link");
			BasicOperation.waitFor(3000);
			List<WebElement> dfList = BasicOperation.getElements("DF_list");
			if(dfList.size()>4)
			{
				reporter.writeStepResult("Verify Distributor links more than 4 are loaded","Expected Distributor links should get loaded on left side","NA","Pass","Expected Distributor links gets loaded on left side", true, webDriver);
			}
			else
			{
				reporter.writeStepResult("Verify Distributor links more than 4 are loaded","Expected Distributor links should get loaded on left side","NA","Fail","Expected Distributor links does not get loaded on left side", true, webDriver);
			}
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	/** 
	        Created By - Pratibha Bhosale
	        Created On - 12/06/2016 
	        Test case covered - Utility Navigation--TC_119	        
	 */	
	
	
	private void TC_119() {
		// TODO Auto-generated method stub
		try{
		BasicOperation.click("TorkLogo", webDriver);
		BasicOperation.waitForPageToLoad(webDriver);
		BasicOperation.click("DF_icon", webDriver);
		BasicOperation.waitForPageToLoad(webDriver);
		BasicOperation.enterTextValue("AutoAdd", DataMap, "AutoIncorrect", webDriver, "Entering text value in auto suggestion");
		BasicOperation.waitFor(5000);
		verify.verifyElementTextPresent(webDriver, objDataMap.get("NoLocationText"), "xpath", DataMap.get("NoLocationText"), "Check if no matching distributors find message comes up");
		}
		catch(Exception e)
		{
			
		}
		
	}

	
	/** 
	        Created By - Pratibha Bhosale
	        Created On - 12/06/2016 
	        Test case covered - Utility Navigation--TC_117	        
	 */	
	
	private void TC_117() {
		// TODO Auto-generated method stub
		try
		{
		BasicOperation.click("TorkLogo", webDriver);
		BasicOperation.waitForPageToLoad(webDriver);
		BasicOperation.click("DF_icon", webDriver);
		BasicOperation.waitForPageToLoad(webDriver);
		BasicOperation.enterTextValue("AutoAdd", DataMap, "AutoAdd", webDriver, "Entering text value in auto suggestion");
		BasicOperation.waitFor(5000);
		verify.verifyListValue(webDriver, objDataMap.get("DFList"), "xpath", DataMap.get("AutoSuggestionValue"));
		}
		catch(Exception e)
		{
			
		}
		
	}
	
}
