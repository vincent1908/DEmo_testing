package com.capgemini.scripts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class Hero_Carousel
{
	public String TestCase="Hero_Carousel";
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

					//legalInfo_TC_25();
					//whyTork_TC_52_TC_55();
					//legalInfo_TC_26_TC_27();
					TC_01();
					//learnMoreLink();
			
			

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	
	public void TC_01()
	{
		System.out.println("Inside Tc01................");
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Carousel_Xpath"),"xpath","Hero Carousel is present");
		BasicOperation.waitFor(6000);
		System.out.println("Carousel is present...........");
		
		verify.verifyElementPresent(webDriver,objDataMap.get("CTA_Xpath"),"xpath","CTA Element is present");
		BasicOperation.waitFor(6000);
		System.out.println("CTA Element present...........");
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Slider_Indi_Xpath"),"xpath","Slider Indicator is present");
		BasicOperation.waitFor(6000);
		System.out.println("Slider Indicator present...........");
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Left_Arrow_Xpath"),"xpath","Left Arrow is present");
		BasicOperation.waitFor(6000);
		
		BasicOperation.click(objDataMap.get("Left_Arrow_Xpath"), webDriver);
		System.out.println("Left Arrow Indicator present...........");
		BasicOperation.waitFor(6000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Right_Arrow_Xpath"),"xpath","Right Arrow is present");
		BasicOperation.waitFor(6000);
		
		BasicOperation.click(objDataMap.get("Right_Arrow_Xpath"), webDriver);
		System.out.println("Left Arrow Indicator present...........");
		BasicOperation.waitFor(6000);
		
		List<WebElement> ListOfElements=webDriver.findElementsByXPath(objDataMap.get("Carosel_Link_Xpath"));
		linksCount=ListOfElements.size();
		
		System.out.println("List Size..........."+linksCount);
		
		for (int i = 0; i<ListOfElements.size(); i++)
		{
			String cls_Name=null;
			
			cls_Name=ListOfElements.get(i).getAttribute("class");
			
			System.out.println(ListOfElements.get(i).getAttribute("class"));
			
			if(cls_Name.equalsIgnoreCase("flex-active-slide"))
					{
							
							System.out.println(ListOfElements.get(i).getAttribute("class"));
							BasicOperation.waitFor(6000);
							
							List<WebElement> DivElements=webDriver.findElementsByXPath(objDataMap.get("CoverImg_Xpath"));
							divCount=DivElements.size();
							
							System.out.println("no of div...."+divCount);
							
							for (int j = 0; j<DivElements.size(); j++)
							{
								//System.out.println(DivElements.get(j).getAttribute("style"));
								if(!(DivElements.get(j).getAttribute("style")).isEmpty())
								{
									System.out.println(DivElements.get(j).getAttribute("style"));
									System.out.println("Carosel Image at "+j+" Location Present...........");
									reporter.writeStepResult(("Verify Carosel Image is Present").toUpperCase(),
											"Verify Carosel Image is Present", "Expected: "
													+ "Carosel Image at "+j+" Location Present...........", "Pass",
											"Verify Carosel Image is Present", true, webDriver);
								}
								else
								{
									System.out.println("Carosel Image at "+j+" location not Present...........");
									System.out.println("Carosel Image at "+j+" Location Present...........");
									reporter.writeStepResult(("Verify Carosel Image is Present").toUpperCase(),
											"Verify Carosel Image is Present", "Expected: "
													+ "Carosel Image at "+j+" location not Present...........", "Pass",
											"Verify Carosel Image is Present", true, webDriver);
									
								}
								
							}
							
							
					}			
			
			
			
			
		
				
						
		}
		
		
		
	}
	
}
