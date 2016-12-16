package com.capgemini.scripts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;






import jxl.read.biff.BiffException;






import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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


public class Testimonial
{
	public String TestCase="Testimonial";
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

					
					//TC_15();
					//TC_16();
					//TC_90();
					TC_91_TC_92();
			
			

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	
	public void TC_15() throws Exception
	{
		System.out.println("Inside Tc_15................");
		
		SCACommon.scrollForTridion(webDriver,13,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
		
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Testimonial_Xpath"),"xpath","Testimonial module is present............");
		BasicOperation.waitFor(6000);
		System.out.println("Testimonial module is present...........");
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Testimonial_Quote_Txt"),"xpath","Testimonial_Quote_Txt is present............");
		BasicOperation.waitFor(6000);
		System.out.println("Testimonial_Quote_Txt is present...........");
		
		
		verify.verifyElementPresent(webDriver,objDataMap.get("PenctuationMark"),"xpath","PenctuationMark is present............");
		BasicOperation.waitFor(6000);
		System.out.println("PenctuationMark is present...........");
		
		
		verify.headingClickable(webDriver,objDataMap.get("Testimonial_Quote_Txt"),"xpath");
		BasicOperation.waitFor(6000);
		System.out.println("Verifying Quote...........");
		
		
		
		
		
	}
	
	public void TC_16()
	{
		
		System.out.println("Inside Tc_16................");
		Boolean VerifySignature;
		
		SCACommon.scrollForTridion(webDriver,13,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
		
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Testimonial_Xpath"),"xpath","Testimonial module is present............");
		BasicOperation.waitFor(6000);
		System.out.println("Testimonial module is present...........");
		
		SCACommon.scrollForTridion(webDriver,13,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
		
		System.out.println("Verifying signature module is present...........");
		VerifySignature=verify.verifyElement(webDriver,objDataMap.get("signature_Xpath"),"xpath","Signature Module is Present");
		
		if(VerifySignature==true)
		{
			reporter.writeStepResult(("Verifying signature module is present").toUpperCase(),
					"Verifying signature module is present", "Expected: "
							+ "signature module is Present...........", "Pass",
					"Verifying signature module is present", true, webDriver);
			
			verify.verifyElementPresent(webDriver,objDataMap.get("signature_Title_Xpath"),"xpath","signature_Title is present............");
			BasicOperation.waitFor(6000);
			System.out.println("signature_Title is present...........");
		
			verify.verifyElementPresent(webDriver,objDataMap.get("signature_Name_Xpath"),"xpath","signature_Name is present............");
			BasicOperation.waitFor(6000);
			System.out.println("signature_Name is present...........");
		
		}
		else
		{
			reporter.writeStepResult(("Verifying signature module is present").toUpperCase(),
					"Verifying signature module is present", "Expected: "
							+ "signature module Not Present...........", "Fail",
					"Verifying signature module is present", true, webDriver);
			
			System.out.println("Signature not present...........");
		}
		
	}
	
	public void TC_90()
	{
		System.out.println("Inside Tc_90................");
		Boolean Verify_coverImg=null;
		
		WebElement elemnt=null;
		String proprty=null;
		String Object=null;
		
		SCACommon.scrollForTridion(webDriver,13,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
		
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Testimonial_Xpath"),"xpath","Testimonial module is present............");
		BasicOperation.waitFor(6000);
		System.out.println("Testimonial module is present...........");
		
		SCACommon.scrollForTridion(webDriver,13,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Testimonial_cover_img"),"xpath","Testimonial_cover_img is present............");
		BasicOperation.waitFor(6000);
		System.out.println("Testimonial_cover_img is present...........");
		
		//Testimonial_cover_img
		Object=BasicOperation.getElementIdentification("Testimonial_Img_Xpath");

		elemnt=BasicOperation.getElement(Object);
		Verify_coverImg=verify.verifyElement(webDriver,objDataMap.get("Testimonial_cover_img"),"xpath","Testimonial_cover_img is Present");
		
		System.out.println("Cover Imag module........."+Verify_coverImg);
		
		proprty=elemnt.getAttribute("src");
		
		
		if(Verify_coverImg==true)
		{
			System.out.println(proprty);
			reporter.writeStepResult(("Verifying Testimonial cover_img module is present").toUpperCase(),
					"Verifying Testimonial cover_img module is present", "Expected: "
							+ "Testimonial cover_img Present...........", "Pass",
					"Verifying Testimonial cover_img module is present", true, webDriver);
			
			if((!(elemnt.getAttribute("src")).isEmpty())||(elemnt.getAttribute("src")!=null))
			{
				System.out.println(elemnt.getAttribute("src"));
				System.out.println("Testimonial cover_img Image is Present...........");
				reporter.writeStepResult(("Verifying Testimonial cover_img image is present").toUpperCase(),
						"Verifying Testimonial cover_img image is present", "Expected: "
								+ "Testimonial cover_img Image is Present...........", "Pass",
						"Verifying Testimonial cover_img image is present", true, webDriver);
				
			}
			else
			{
				System.out.println("Testimonial cover_img Image is Not Present...........");
				reporter.writeStepResult(("Verifying Testimonial cover_img image is present").toUpperCase(),
						"Verifying Testimonial cover_img image is present", "Expected: "
								+ "Testimonial cover_img Image is Not Present...........", "Fail",
						"Verifying Testimonial cover_img image is present", true, webDriver);
				
			}
			
		}
		else
		{
			
			reporter.writeStepResult(("Verifying Testimonial cover_img module is present").toUpperCase(),
					"Verifying Testimonial cover_img module is present", "Expected: "
							+ "Testimonial cover_img  Module not Present...........", "Fail",
					"Verifying Testimonial cover_img module is present", true, webDriver);
		}
		
		
		
		
	}
	public void TC_91_TC_92() throws Exception
	{
		System.out.println("Inside Tc_91_TC_92................");
		//Boolean TestimonialTxt;
		//String ToolTipReadMore=null;
		WebElement elemnt=null;
		String proprty=null;
		String Object=null;
		Boolean Verify_coverImg=null;
		String curURL=null;
		
		SCACommon.scrollForTridion(webDriver,13,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
		
		
		//testimonial_txt
		verify.verifyElementPresent(webDriver,objDataMap.get("testimonial_txt"),"xpath","testimonial_txt module is present............");
		BasicOperation.waitFor(6000);
		System.out.println("testimonial_txt module is present...........");
		
		verify.headingClickable(webDriver,objDataMap.get("testimonial_txt"),"xpath");
		BasicOperation.waitFor(6000);
		System.out.println("Verifying testimonial_txt ...........");
		
		SCACommon.scrollForTridion(webDriver,13,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
		
		
		Object=BasicOperation.getElementIdentification(objDataMap.get("testimonial_readmore"));

		elemnt=BasicOperation.getElement(Object);
		Verify_coverImg=verify.verifyElement(webDriver,objDataMap.get("testimonial_readmore"),"xpath","testimonial_readmore is Present");
		
			if(Verify_coverImg==true)
			{
				proprty=elemnt.getAttribute("href");
				System.out.println("href...."+proprty);
			
			}
			
			
			BasicOperation.click(objDataMap.get("testimonial_readmore"), webDriver);
			BasicOperation.waitFor(8000);
			
			
			curURL=webDriver.getCurrentUrl();
			if(curURL.contains(proprty))
			{
				System.out.println("Page is Navigated Successfully..............");
				reporter.writeStepResult(("Verifying Page is Navigated Successfully").toUpperCase(),
						"Verifying Page is Navigated Successfully", "Expected: "
								+ "Page is Navigated Successfully...........", "Pass",
						"Verifying Page is Navigated Successfully", true, webDriver);
				
			}
			else
			{
				reporter.writeStepResult(("Verifying Page is Navigated Successfully").toUpperCase(),
						"Verifying Page is Navigated Successfully", "Expected: "
								+ "Page is not Navigated...........", "Fail",
								"Verifying Page is Navigated Successfully", true, webDriver);
				
			}
			
			
		}
	
	

	
	
}
