package com.capgemini.scripts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.read.biff.BiffException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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

public class Search_Functionality 
{
	public String TestCase="Search_Functionality";
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
	private static int linksCount = 0;
	private static int divCount = 0;

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
	Exception
	{
		BasicOperation.setDataMap(DataMap);
		BasicOperation.setReporter(TestCase, webDriver, reporter);
		BasicOperation.setPropertiesDataMap(objDataMap);
		
		
		stepExecutor.launchApplication("URL", DataMap, webDriver);
		BasicOperation.waitFor(10000);
		
		try{
			String testcase_sr = DataMap.get("Testcase ID");

					
					//TC_165();
					//TC_166();
					//TC_170();
					//TC_167();
					TC_161();
			
			

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	
	public void TC_165()
	{
		//Search box display in Blue banner section of Resource page
		verify.verifyElementPresent(webDriver,objDataMap.get("Search_Btn_Xpath"),"xpath","Search Box is present");
		BasicOperation.waitFor(6000);
		System.out.println("Search Box present...........");
		
		BasicOperation.click(objDataMap.get("Search_Btn_Xpath"), webDriver);
		BasicOperation.waitFor(6000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("SearchBX_Brochures_Link_Xpath"),"xpath","Brochures Link is present");
		BasicOperation.waitFor(6000);
		System.out.println("Brochures Link present...........");
		
		BasicOperation.click(objDataMap.get("SearchBX_Brochures_Link_Xpath"), webDriver);
		BasicOperation.waitFor(8000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("AssetTxt_Xpath"),"xpath","Asset Search Box is present");
		BasicOperation.waitFor(7000);
		System.out.println("Asset Search Box present...........");
		
		
		verify.verifyElementPresent(webDriver,objDataMap.get("search_Box_searchBtn"),"xpath","Search Btn is present");
		BasicOperation.waitFor(7000);
		System.out.println("Search Btn present...........");
		
		
		
		
		
		
	}
	public void TC_166()
	{
		//verify Search Button is Disabled
		verify.verifyElementPresent(webDriver,objDataMap.get("Search_Btn_Xpath"),"xpath","Search Box is present");
		BasicOperation.waitFor(6000);
		System.out.println("Search Box present...........");
		
		BasicOperation.click(objDataMap.get("Search_Btn_Xpath"), webDriver);
		BasicOperation.waitFor(6000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("SearchBX_Brochures_Link_Xpath"),"xpath","Brochures Link is present");
		BasicOperation.waitFor(8000);
		System.out.println("Brochures Link present...........");
		
		BasicOperation.click(objDataMap.get("SearchBX_Brochures_Link_Xpath"), webDriver);
		BasicOperation.waitFor(8000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Asset_SearchBtn_Xpath"),"xpath","Search Btn is present");
		BasicOperation.waitFor(7000);
		System.out.println("Search Btn present...........");
		
		verify.verifyElementPresent(webDriver,objDataMap.get("AssetTxt_Xpath"),"xpath","Asset Search Box is present");
		BasicOperation.waitFor(7000);
		
		
		String BtnProperty=null;
		String TextProperty=null;
		TextProperty=webDriver.findElementByXPath(objDataMap.get("AssetTxt_Xpath")).getAttribute("text");
		BtnProperty=webDriver.findElementByXPath(objDataMap.get("Asset_SearchBtn_Xpath")).getAttribute("disabled");
		System.out.println("Value of Btn Property...."+BtnProperty);
		System.out.println("Value of TextProperty...."+TextProperty);
			if(TextProperty==null)
				{
					if(BtnProperty.equalsIgnoreCase("true"))
					{
						System.out.println("Search Button is Disabled");
						reporter.writeStepResult(("Verify Search Button is Disabled").toUpperCase(),
								"Verify Search Button is Disabled", "Expected: "
										+ "Search Button is Disabled", "Pass",
								"Search Button is Disabled", true, webDriver);
					}
					else
					{
						System.out.println("Search Button enabled....Error in functionality");
						reporter.writeStepResult(("Verify Search Button is Disabled").toUpperCase(),
								"Verify Search Button is Disabled", "Expected: "
										+ "Search Button enabled", "Fail",
								"Search Button is Disabled", true, webDriver);
					}
			
				}
			

	}
	
	public void TC_167()
	{
		// Test case to check Search button is enable
		String BtnProperty=null;
		String TextProperty=null;
		Boolean textEnter;
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Search_Btn_Xpath"),"xpath","Search Box is present");
		BasicOperation.waitFor(6000);
		System.out.println("Search Box present...........");
		
		BasicOperation.click(objDataMap.get("Search_Btn_Xpath"), webDriver);
		BasicOperation.waitFor(6000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("SearchBX_Brochures_Link_Xpath"),"xpath","Brochures Link is present");
		BasicOperation.waitFor(8000);
		System.out.println("Brochures Link present...........");
		
		BasicOperation.click(objDataMap.get("SearchBX_Brochures_Link_Xpath"), webDriver);
		BasicOperation.waitFor(9000);
		
	
		verify.verifyElementPresent(webDriver,objDataMap.get("AssetTxt_Xpath"),"xpath","Asset Search Box is present");
		BasicOperation.waitFor(7000);
		System.out.println("Asset Search Box present...........");
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Asset_SearchBtn_Xpath"),"xpath","Search Btn is present");
		BasicOperation.waitFor(7000);
		System.out.println("Search Btn present...........");
		
	
		BasicOperation.click(objDataMap.get("AssetTxt_Xpath"), webDriver);
		BasicOperation.waitFor(2000);
		
		webDriver.findElementByXPath(objDataMap.get("AssetTxt_Xpath")).sendKeys(objDataMap.get("searchText"));
		BasicOperation.waitFor(4000);
		
		
		
	
		TextProperty=BasicOperation.txtValue(objDataMap.get("AssetTxt_Xpath"), webDriver);
		
		BtnProperty=webDriver.findElementByXPath(objDataMap.get("Asset_SearchBtn_Xpath")).getAttribute("disabled");
		System.out.println("Value of Btn Property...."+BtnProperty);
		System.out.println("Value of TextProperty...."+TextProperty);
			if(TextProperty!=null)
				{
					if(BtnProperty==null)
					{
						
						reporter.writeStepResult(("Verify Search Button is enabled").toUpperCase(),
								"Verify Search Button is enabled", "Expected: "
										+ "Search Button is enabled", "Pass",
								"Search Button is enabled", true, webDriver);
					}
					else
					{
						
						reporter.writeStepResult(("Verify Search Button is Disabled").toUpperCase(),
								"Verify Search Button is enabled", "Expected: "
										+ "Search Button Disabled", "Fail",
								"Verify Search Button is enabled", true, webDriver);
					}
			
				}
			
		
	}
	
	public void TC_170()
	{
		//navigate by using See more link on selected criteria
		verify.verifyElementPresent(webDriver,objDataMap.get("Search_Btn_Xpath"),"xpath","Search Box is present");
		BasicOperation.waitFor(6000);
		System.out.println("Search Box present...........");
		
		BasicOperation.click(objDataMap.get("Search_Btn_Xpath"), webDriver);
		BasicOperation.waitFor(6000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("SearchBX_Brochures_Link_Xpath"),"xpath","Brochures Link is present");
		BasicOperation.waitFor(8000);
		System.out.println("Brochures Link present...........");
		
		BasicOperation.click(objDataMap.get("SearchBX_Brochures_Link_Xpath"), webDriver);
		BasicOperation.waitFor(8000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("BroucherLink_Xpath"),"xpath","See More Brochures Link is present");
		BasicOperation.waitFor(8000);
		
		
		SCACommon.scrollForTridion(webDriver,4,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
		
		BasicOperation.click(objDataMap.get("BroucherLink_Xpath"), webDriver);
		BasicOperation.waitFor(8000);
		
		String curURL=null;
		String ExpURL="http://www.torkusa.com/about/resources/brochures/";

		curURL=webDriver.getCurrentUrl();
		System.out.println("cur URL....."+curURL);
		
		if(curURL.equalsIgnoreCase(ExpURL))
		{
			
			reporter.writeStepResult(("Verify Page Navigated to Selected Creteria").toUpperCase(),
					"Verify Page Navigated to Selected Criteria", "Expected: "
							+ "Page Navigated to Selected Criteria", "Pass",
					"Page Navigated to Selected Criteria", true, webDriver);
		}
		else
		{
			
			reporter.writeStepResult(("Verify Page Navigated to Selected Creteria").toUpperCase(),
					"Verify Page Navigated to Selected Criteria", "Expected: "
							+ "Page is not Navigated to Selected Creteria", "Fail",
					"Page Navigated to Selected Criteria", true, webDriver);
			
		}
	}
	
	public void TC_161()
	{
		verify.verifyElementPresent(webDriver,objDataMap.get("Search_Btn_Xpath"),"xpath","Search Box is present");
		BasicOperation.waitFor(6000);
		System.out.println("Search Box present...........");
		
		BasicOperation.click(objDataMap.get("Search_Btn_Xpath"), webDriver);
		BasicOperation.waitFor(6000);
		
		
		verify.verifyElementPresent(webDriver,objDataMap.get("search_Box_searchTxt"),"xpath","Search Box searchTxt is present");
		BasicOperation.waitFor(6000);
		
		
		verify.verifyElementPresent(webDriver,objDataMap.get("search_Box_searchBtn"),"xpath","Search Box searchBtn is present");
		BasicOperation.waitFor(6000);
		
		
		
		BasicOperation.click(objDataMap.get("search_Box_searchTxt"), webDriver);
		BasicOperation.waitFor(2000);
		
		webDriver.findElementByXPath(objDataMap.get("search_Box_searchTxt")).sendKeys(Keys.SPACE);
		BasicOperation.waitFor(4000);
		
		BasicOperation.click(objDataMap.get("search_Box_searchBtn"), webDriver);
		BasicOperation.waitFor(4000);
		
		
		Boolean articleTxt_DefaultList;
		articleTxt_DefaultList=verify.verifyElement(webDriver,objDataMap.get("articleHeader_txt"),"xpath","Article header is not present");
		System.out.println("Article text Status"+articleTxt_DefaultList);
		if(articleTxt_DefaultList==false)
		{
			System.out.println("Article text not found on default Product list page......Passed");
			reporter.writeStepResult(("Verify Article text not found on default Product list page").toUpperCase(),
					"Verify Article text not found on default Product list page", "Expected: "
							+ "Article text not found", "Pass",
					"Verify Article text not found on default Product list page", true, webDriver);
		}
		else
		{
			
			reporter.writeStepResult(("Verify Article text not found on default Product list page").toUpperCase(),
					"Verify Article text not found on default Product list page", "Expected: "
							+ "Article text Found", "Fail",
					"Verify Article text not found on default Product list page", true, webDriver);
			
		}
		
		BasicOperation.waitFor(4000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("HandTwel_ID"),"xpath","HandTowel Category is present");
		BasicOperation.waitFor(6000);
		
		
		BasicOperation.click("HandTwel_ID", webDriver);
		BasicOperation.waitFor(9000);
		
		Boolean articleTxt_selectedProduct;
		articleTxt_selectedProduct=verify.verifyElement(webDriver,objDataMap.get("articleHeader_txt"),"xpath","Article header is not present");
		System.out.println("articleTxt_selected Product..........."+articleTxt_selectedProduct);
		if(articleTxt_DefaultList==false)
		{
			
			reporter.writeStepResult(("Verify Article text not found on Selected Product list page").toUpperCase(),
					"Verify Article text not found on Selected Product list page", "Expected: "
							+ "Article text not found", "Pass",
					"Verify Article text not found on Selected Product list page", true, webDriver);
		}
		else
		{
			
			reporter.writeStepResult(("Verify Article text not found on Selected Product list page").toUpperCase(),
					"Verify Article text not found on Selected Product list page", "Expected: "
							+ "Article text found", "Fail",
					"Verify Article text not found on Selected Product list page", true, webDriver);
		}
		
		
	}
	
	
	
	
}
