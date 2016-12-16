package com.Base;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicOptionPaneUI;

import net.sf.cglib.core.ClassNameReader;

import org.jboss.netty.handler.codec.serialization.SoftReferenceMap;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.seleniumemulation.RunScript;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.beust.jcommander.internal.Nullable;
import com.capgemini.driver.ScriptExecutor;
import com.capgemini.executor.ExecutionRowNumber;
import com.capgemini.utilities.Reporter;
import com.capgemini.utilities.Utilities;
import com.capgemini.utilities.Verification;

/**
 * Note - {@code Please Do not Modify any code in framework}</font> <br>
 * New Methods added for Lookup<br>
 * 
 * new optimized Generic Methods<br>
 * Java Script Executor <br>
 * 
 * @author PRAVIRAN
 *
 */

@SuppressWarnings("unused")
public class BasicOperation<K> extends LoadDriver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7559752506276249561L;

	/**
	 * Added for Centralized Database
	 * 
	 * @author PRAVIRAN
	 * @since 23 Oct 2015
	 * 
	 */

	public static boolean fetchXpathFromServerDb = false;

	public static String DataBase = "SystemAutomation";

	public static String serverHost = "10.74.159.148";

	/**
	 * Check whether application is already Login or not if this flag is true
	 * then application perform login action
	 * 
	 * @author PRAVIRAN
	 */
	public static boolean needLogin = true;

	/**
	 * Check whether BO Report application is already Login or not if this flag
	 * is true then application performe login action
	 * 
	 * @author PRAVIRAN
	 */

	public static boolean needBOReportLogin = true;

	/**
	 * if cont_operation = false all fill(), selectBy() and click() methods
	 * ignore element importance = High
	 * 
	 * @author PRAVIRAN
	 */

	public static boolean cont_operation = true;

	/**
	 * Check iteration time for element presence
	 * 
	 * @author PRAVIRAN
	 */
	public static int iteration = 10;

	/**
	 * Not used in current framework will be used for checking previous
	 * condition and reason of fail
	 * 
	 * @author PRAVIRAN
	 */

	public static String prev;

	/**
	 * To stop selecting random data from list
	 * 
	 * @since 27 March 15
	 * @param Xpath
	 * @return
	 */
	public static boolean RestrictionMode = false;

	public static boolean resumeScriptOn = true;

	private static Map<? extends Object, ? extends Object> propertyMap;

	/**
	 * Map used to get Data from Excel sheet check By Fill(), Click() and
	 * SelectById() method
	 * 
	 * @author PRAVIRAN
	 */

	private static Map<? extends Object, ? extends Object> DataMap = new HashMap<>();

	protected static Reporter reporter;

	/**
	 * AppName used for report creation
	 */

	public static String AppName = "";

	/**
	 * BaseDiver Class used for Driver sharing
	 * 
	 */
	private static RemoteWebDriver Driver;

	/**
	 * Image No used to iterate to next image
	 */
	static public int image_no = 0;

	/**
	 * check path for image save
	 */
	protected static File screenshotfile = null;

	/**
	 * Flag used to start Thread Cont_Operation_diable_reason check for
	 * Exception
	 */
	private static int Cont_Operation_diable_reason_Thread_start = 1;

	private MainController main = new MainController();

	public static boolean FailCase = false;
	static FileHandler logFileWriter;
	static Logger log;
	public static int steps = 0;
	// static Port port = new Port(Port.CURRENT_PORT);
	public static Map tempmap = null;
	public static List<Integer> timeStamp = new ArrayList();
	public static int click = 0, fill = 0, selectById = 0, clear = 0, readValue = 0, valueOf = 0;

	/**
	 * Store Data while script are in execution state! Share data from one UC to
	 * another UC
	 * 
	 * @since 9 April 15
	 */

	public static Map Session = new HashMap<>();

	public static boolean showExecutionReport = true;

	//public static ExecutionReporter repotr = new ExecutionReporter();

	public static int Datacount = 0;
	public static int readindex = 1;
	public static Object[][] reportdata = null;
	public static boolean startExecution = true;
	public static Map dataModel = new HashMap();
	public static Date date = new Date();
	public static long startTime = System.currentTimeMillis();

	/**
	 * return needLogin Flag
	 * 
	 * @author PRAVIRAN
	 */

	Utilities utils;
	private Verification verify;

	public BasicOperation(Reporter reporter) {

		this.reporter = reporter;
		utils = new Utilities(reporter);

		verify = new Verification(reporter);

	}

	// CreateDriver driver = new CreateDriver();
	// RemoteWebDriver webDriver = driver.getWebDriver();

	/*
	 * // Object for calling verification functions VerificationFunctions
	 * verificationFunctions = new VerificationFunctions();
	 */

	/*
	 * public void launchApplication(String strColumnName, String
	 * strDataFileName, RemoteWebDriver webDriver){ String strDetails =
	 * utils.getDataFileInfo(); int rowNumber =
	 * executionRowNumber.getExecutionRowNumber(); String strData =
	 * scriptExecutor.readDataFile(strDataFileName, rowNumber, strColumnName);
	 * String [] arrDetails = strDetails.split("_"); webDriver.get(strData);
	 * 
	 * reporter.writeStepResult(arrDetails[1].toUpperCase(),
	 * "Launch Application URL", strData, "Pass",
	 * "Launched Application URL successfully", true, webDriver); }
	 */

	// Launching the specific URL
	public static final Boolean launchApplication(String strColumnName,
			Map<String, String> DataMap, RemoteWebDriver webDriver) {
		/* String strDetails = utils.getDataFileInfo(); */
		/* int rowNumber = executionRowNumber.getExecutionRowNumber(); */
		Boolean sFlag = true;
		String strData = null;
		String strKey = strColumnName;
		System.out.println(strKey);
		
		Map<String, String> dataMapLocal = DataMap;
		if (dataMapLocal.containsKey(strKey)) {
			strData = dataMapLocal.get(strKey);
		} else {
			sFlag = false;
			return sFlag;
		}
		webDriver.manage().window().maximize();
		webDriver.get(strData);

		reporter.writeStepResult("LAUNCHAPPLICATION", "Lauch Application URL",
				strData, "Pass", "Lauched Application URL successfully", true,
				webDriver);
		return sFlag;
		
	}

	/** 
	        Updated By - Siddhartha Haldar
	        Created On - 11/22/2016 
	        Updated content- Find by Css Selector
	        
	 */	
	//scroll to the particular element
	public static void scroll(RemoteWebDriver webDriver,String strAttribute)
	{
		JavascriptExecutor jse = (JavascriptExecutor)webDriver;
		String scrollelement=null;
		scrollelement=getElementIdentification(strAttribute);
		try {
			
				jse.executeScript("arguments[0].scrollIntoView(true);",scrollelement);
				waitFor(5000);
			
			}
		
			catch (WebDriverException w1) 
			{
				System.out.println(w1.getMessage());
				reporter.writeStepResult("Scroll Function", "Scroll Function",
						"Scroll Issue", "Fail", "Scroll Issue", true,
						webDriver);
			}
			
	}

	
	//cursor move to particular element
	public static final void cursorMove(RemoteWebDriver webDriver,String strAttribute,String msghead) 
	{	
		Point coordinates;
		WebElement cursorelement = getElement(strAttribute);
		try
		{	
		
			coordinates = cursorelement.getLocation();
			waitFor(2000);
			Robot robot = new Robot();
			 robot.mouseMove(coordinates.getX(),coordinates.getY()+80);
			 reporter.writeStepResult(msghead,
						"Move cursor to required element","Test data not available", "Pass",
						"Cursor is moved to required element", true,webDriver);
		}
		catch (WebDriverException w1)
			{
				System.out.println(w1.getMessage());
				reporter.writeStepResult(msghead,
						"Move cursor to required element","Test data not available", "Fail",
						"Cursor is not moved to required element", true,webDriver);
			} 
		catch (AWTException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				reporter.writeStepResult(msghead,
						"Move cursor to required element","Test data not available", "Fail",
						"Cursor is not moved to required element", true,webDriver);
			}
			
			
	}
	
	
	//Enter URL
	public static final Boolean gotoURL(Map<String, String> DataMap,String strKey,
			RemoteWebDriver webDriver, String sAppname) {
		Boolean sFlag = true;
		Map<String, String> dataMapLocal = DataMap;
		String strData = null;
		if (dataMapLocal.containsKey(strKey)) {
			strData = dataMapLocal.get(strKey);
		} else {
			sFlag = false;
			return sFlag;
		}

		try {
			webDriver.get(strData);
			
			reporter.writeStepResult(sAppname.toUpperCase(),
					"Enter URL in Address bar", strData, "Pass",
					"Value entered successfully", true, webDriver);
		} catch (WebDriverException w1) {
			// String strErrorMessage = w1.getMessage();
			// String [] arrMessages = strErrorMessage.split("(");
			sFlag = false;
			reporter.writeStepResult(sAppname.toUpperCase(),
					"Enter URL in Address bar", strData, "Fail",
					"Unable to enter value", true, webDriver);
		} catch (Exception e1) {
			sFlag = false;
			reporter.writeStepResult(sAppname.toUpperCase(),
					"Enter URL in Address bar", strData, "Fail",
					"Unable to enter value", true, webDriver);
		}
		return sFlag;
	}

	// Entering text in text box
	public static final Boolean enterTextValue(String strAttribute,
			Map<String, String> DataMap, String strKey,
			RemoteWebDriver webDriver, String sAppname)
	{
		WebElement hlelement=null;
		String Object = null;
		Boolean sFlag = true;
		Map<String, String> dataMapLocal = DataMap;
		String strData = null;
		if (dataMapLocal.containsKey(strKey)) {
			strData = dataMapLocal.get(strKey);
		} else {
			sFlag = false;
			return sFlag;
		}

		try
		{		
				Object = getElementIdentification(strAttribute);
				hlelement=getElement(Object);
				//hlelement.click();
				hlelement.sendKeys(strData);
				reporter.writeStepResult(sAppname.toUpperCase(),
					"Enter Value in text field", strData, "Pass",
					"Value entered successfully", true,webDriver);
		}
		catch (WebDriverException w1) {
			sFlag = false;
			reporter.writeStepResult(sAppname.toUpperCase(),
					"Enter Value in text field", strData, "Fail",
					"Unable to enter value", true,webDriver);
		} catch (Exception e1) {
			sFlag = false;
			reporter.writeStepResult(sAppname.toUpperCase(),
					"Enter Value in text field", strData, "Fail",
					"Unable to enter value", true,webDriver);
		}
		return sFlag;
	}

	
	// Entering text in textarea
		public static final Boolean enterTextAreaValue(RemoteWebDriver webDriver,Map<String, String> DataMap, String strKey,
					 String strAttribute,String msgHead) {
				String Object=null;
				WebElement hlelement=null;
				Boolean sFlag = true;
				Map<String, String> dataMapLocal = DataMap;
				String strData = null;
				if (dataMapLocal.containsKey(strKey)) {
					strData = dataMapLocal.get(strKey);
				} else {
					sFlag = false;
					return sFlag;
				}

				try {
						Object=getElementIdentification(strAttribute);
						hlelement = getElement(Object);
					
						hlelement.sendKeys(Keys.TAB,strData);
					
					reporter.writeStepResult(msgHead,
							"Enter Value in text area field", strData, "Pass",
							"Value entered successfully", true,webDriver);
				} catch (WebDriverException w1) {
					sFlag = false;
					reporter.writeStepResult(msgHead,
							"Enter Value in text area field", strData, "Fail",
							"Unable to enter value", true,webDriver);
				} catch (Exception e1) {
					sFlag = false;
					reporter.writeStepResult(msgHead,
							"Enter Value in text area field", strData, "Fail",
							"Unable to enter value", true,webDriver);
				}
				return sFlag;
			}

	
	//pickelementfromdropdown
	
	/*public Boolean selectListValue(String strDriverMethod, String strAttribute,
            Map<String, String> DataMap, String strKey,
            RemoteWebDriver webDriver, String sAppname)
            throws InterruptedException {
     Boolean sFlag = true;
     Map<String, String> dataMapLocal = DataMap;
     String strData = null;
     if (dataMapLocal.containsKey(strKey)) {
            strData = dataMapLocal.get(strKey);
     } else {
            sFlag = false;
            return sFlag;
     }
     Select dd = null;
     if (strData.isEmpty())
            return sFlag;
     try {
            if (strDriverMethod.equals("findElementByName")) {
                  dd = new Select(webDriver.findElementByName(strAttribute));
            } else if (strDriverMethod.equals("findElementById")) {
                  dd = new Select(webDriver.findElementById(strAttribute));
            } else if (strDriverMethod.equals("findElementByXpath")) {
                  dd = new Select(webDriver.findElementByXPath(strAttribute));
            } else if (strDriverMethod.equals("findElementByCss")) {
                  dd = new Select(
                                webDriver.findElementByCssSelector(strAttribute));
            }
            dd.selectByVisibleText(strData);
            reporter.writeStepResult(sAppname.toUpperCase(),
                         "Select Value in dropdown field", strData, "Pass",
                         "Value selected successfully", true, webDriver);
     } catch (WebDriverException w1) {
            // String strErrorMessage = w1.getMessage();
            // String [] arrMessages = strErrorMessage.split("(");
            sFlag = false;
            reporter.writeStepResult(sAppname.toUpperCase(),
                         "Select Value in dropdown field", strData, "Fail",
                         "Unable to select value", true, webDriver);
     } catch (Exception e1) {
            sFlag = false;
            reporter.writeStepResult(sAppname.toUpperCase(),
                         "Select Value in dropdown field", strData, "Fail",
                         "Unable to select value", true, webDriver);
     }
     Thread.sleep(3000);
     return sFlag;



public Boolean selectListValueByIndex(String strDriverMethod, String strAttribute,
            Map<String, String> DataMap, String strKey,
            RemoteWebDriver webDriver, String sAppname)
            throws InterruptedException {
     Boolean sFlag = true;
     Map<String, String> dataMapLocal = DataMap;
     String strData = null;
     if (dataMapLocal.containsKey(strKey)) {
            strData = dataMapLocal.get(strKey);
     } else {
            sFlag = false;
            return sFlag;
     }
     Select dd = null;
     if (strData.isEmpty())
            return sFlag;
     try {
            if (strDriverMethod.equals("findElementByName")) {
                  dd = new Select(webDriver.findElementByName(strAttribute));
            } else if (strDriverMethod.equals("findElementById")) {
                  dd = new Select(webDriver.findElementById(strAttribute));
            } else if (strDriverMethod.equals("findElementByXpath")) {
                  dd = new Select(webDriver.findElementByXPath(strAttribute));
            } else if (strDriverMethod.equals("findElementByCss")) {
                  dd = new Select(
                                webDriver.findElementByCssSelector(strAttribute));
            }
            dd.selectByIndex(Integer.parseInt(strData)-1);
            reporter.writeStepResult(sAppname.toUpperCase(),
                         "Select Value in dropdown field", strData, "Pass",
                         "Value selected successfully", true, webDriver);
     } catch (WebDriverException w1) {
            // String strErrorMessage = w1.getMessage();
            // String [] arrMessages = strErrorMessage.split("(");
            sFlag = false;
            reporter.writeStepResult(sAppname.toUpperCase(),
                         "Select Value in dropdown field", strData, "Fail",
                         "Unable to select value", true, webDriver);
     } catch (Exception e1) {
            sFlag = false;
            reporter.writeStepResult(sAppname.toUpperCase(),
                         "Select Value in dropdown field", strData, "Fail",
                         "Unable to select value", true, webDriver);
     }
     Thread.sleep(3000);
     return sFlag;


}*/

	

	

	//clear text values
		public static final void clearValue(RemoteWebDriver webDriver,String strAttribute) {
				String Object=null;
				WebElement hlelement=null;
			try {
				Object=getElementIdentification(strAttribute);
				hlelement=getElement(Object);
				hlelement.clear();
				
				System.out.println("Text cleared");
			} catch (WebDriverException w1) {
				System.out.println("Unable to clear text");
				
				reporter.writeStepResult("clearValue Function", "clearValue Function",
						"clearValue Issue", "Fail", "clearValue Issue", true,webDriver);
				
			} catch (Exception e1) {
				System.out.println("Unable to clear text");
				
				reporter.writeStepResult("clearValue Function", "clearValue Function",
						"clearValue Issue", "Fail", "clearValue Issue", true,webDriver);
			}
		}
	
	
		//pick values from autosuggest dropdown list
		public static final Boolean clickAutoSuggestDropdown(Map<String, String> DataMap, String strKey,
				RemoteWebDriver webDriver, String sAppname)
		{
			String Object=null;
			WebElement hlelement=null;
			Boolean sFlag = true;
			Map<String, String> dataMapLocal = DataMap;
			String strData = null;
			if (dataMapLocal.containsKey(strKey)) {
				strData = dataMapLocal.get(strKey);
			} else {
				sFlag = false;
				return sFlag;
			}

			try {
				Object=getElementIdentification(strData);
				hlelement=getElement(Object);
				hlelement.click();
				reporter.writeStepResult(sAppname.toUpperCase(),
						"Pick any values from the dropdownlist", strData, "Pass",
						"Value picked successfully", true,webDriver);
			} catch (WebDriverException w1) {
				sFlag = false;
				reporter.writeStepResult(sAppname.toUpperCase(),
						"Pick any values from the dropdownlist", strData, "Fail",
						"Value not picked successfully", true,webDriver);
			} catch (Exception e1) {
				sFlag = false;
				reporter.writeStepResult(sAppname.toUpperCase(),
						"Pick any values from the dropdownlist", strData, "Fail",
						"Value not picked successfully", true,webDriver);
			}
			return sFlag;
		}
		
		
		
	//pick values from listbox/dropdown pickElementFromListusing option value
		public static final Boolean pickElementFromListOptionValue(String strDriverMethod,Map<String,String> DataMap,
					String strKey,String strAttribute,RemoteWebDriver webDriver, String sAppname) {
				String Object=null;
				WebElement hlelement=null;
				Select select=null;
				Boolean sFlag = true;
				Map<String, String> dataMapLocal = DataMap;
				String strData = null;
				if (dataMapLocal.containsKey(strKey)) {
					strData = dataMapLocal.get(strKey);
				} else {
					sFlag = false;
					return sFlag;
				}

				try {
					Object=getElementIdentification(strAttribute);
					hlelement= getElement(Object);
					select = new Select(hlelement);
					select.selectByValue(strData);
					
					reporter.writeStepResult(sAppname.toUpperCase(),
							"Pick any values from the dropdownlist", strData, "Pass",
							"Value picked successfully", true,webDriver);
				} catch (WebDriverException w1) {
					// String strErrorMessage = w1.getMessage();
					// String [] arrMessages = strErrorMessage.split("(");
					sFlag = false;
					reporter.writeStepResult(sAppname.toUpperCase(),
							"Pick any values from the dropdownlist", strData, "Fail",
							"Value not picked successfully", true,webDriver);
				} catch (Exception e1) {
					sFlag = false;
					reporter.writeStepResult(sAppname.toUpperCase(),
							"Pick any values from the dropdownlist", strData, "Fail",
							"Value not picked successfully", true,webDriver);
				}
				return sFlag;
			}
		
		

	//pick values from listbox/dropdown pickElementFromListusing option value
		public static final Boolean pickElementFromListIndex(Map<String,String> DataMap,
							String strKey,String strAttribute,RemoteWebDriver webDriver, String sAppname) {
						String Object=null;
						WebElement hlelement=null;
						Select select=null;
						Boolean sFlag = true;
						Map<String, String> dataMapLocal = DataMap;
						int index = 0;
						String strData=null;
						if (dataMapLocal.containsKey(strKey)) {
							strData = dataMapLocal.get(strKey);
							index=Integer.parseInt(strData);
						} else {
							sFlag = false;
							return sFlag;
						}

						try {
								Object=getElementIdentification(strAttribute);
								hlelement=getElement(Object);
								select = new Select(hlelement);
								select.selectByIndex(index);
								
							reporter.writeStepResult(sAppname.toUpperCase(),
									"Pick any values from the dropdownlist", strData, "Pass",
									"Value picked successfully", true,webDriver);
						} catch (WebDriverException w1) {
							// String strErrorMessage = w1.getMessage();
							// String [] arrMessages = strErrorMessage.split("(");
							sFlag = false;
							reporter.writeStepResult(sAppname.toUpperCase(),
									"Pick any values from the dropdownlist", strData, "Fail",
									"Value not picked successfully", true,webDriver);
						} catch (Exception e1) {
							sFlag = false;
							reporter.writeStepResult(sAppname.toUpperCase(),
									"Pick any values from the dropdownlist", strData, "Fail",
									"Value not picked successfully", true,webDriver);
						}
						return sFlag;
					}
				

				
			
	// Clicking button
	public static final void clickButton(String strAttribute,
			RemoteWebDriver webDriver, String msghead) {
			String Object=null;
		WebElement hlelement=null;

		try {
				Object=getElementIdentification(strAttribute);
				hlelement=getElement(Object);
			
				hlelement.click();
			
			reporter.writeStepResult(msghead.toUpperCase(), "Click on Button",
					"Test data not available", "Pass", "Clicked on Button successfully", false,webDriver);
		} 
		catch (WebDriverException w1) 
		{
			reporter.writeStepResult(msghead.toUpperCase(), "Click on Button",
					"Test data not available", "Fail", w1.getMessage(), false,webDriver);
		}
		catch (Exception e1)
		{
			reporter.writeStepResult(msghead.toUpperCase(), "Click on Button",
					"Test data not available", "Fail", "Not able to click on Button", false,webDriver);
		}
	}

	

	// Changing check box status
	public static final boolean changeCheckboxStatus(String strDriverMethod,
			String strAttribute, Map<String, String> DataMap, String strKey,
			RemoteWebDriver webDriver, String sAppname) {
		Boolean sFlag = true;
		Map<String, String> dataMapLocal = DataMap;
		String strData = null;
		if (dataMapLocal.containsKey(strKey)) {
			strData = dataMapLocal.get(strKey);
		} else {
			sFlag = false;
			return sFlag;
		}
		try {
			if (strDriverMethod.equals("findElementByName")) {
				if (strData.equalsIgnoreCase("uncheck")
						&& webDriver.findElementByName(strAttribute)
								.isSelected()) {					
					webDriver.findElementByName(strAttribute).click();
				}
				if (strData.equalsIgnoreCase("check")
						&& !webDriver.findElementByName(strAttribute)
								.isSelected()) {					
					webDriver.findElementByName(strAttribute).click();
				}
			} else if (strDriverMethod.equals("findElementById")) {
				if (strData.equalsIgnoreCase("uncheck")
						&& webDriver.findElementById(strAttribute).isSelected()) {					
					webDriver.findElementById(strAttribute).click();
				}
				if (strData.equalsIgnoreCase("check")
						&& !webDriver.findElementById(strAttribute)
								.isSelected()) {					
					webDriver.findElementById(strAttribute).click();
				}
			} else {
				if (strData.equalsIgnoreCase("uncheck")
						&& webDriver.findElementByXPath(strAttribute)
								.isSelected()) {					
					webDriver.findElementByXPath(strAttribute).click();
				}
				if (strData.equalsIgnoreCase("check")
						&& !webDriver.findElementByXPath(strAttribute)
								.isSelected()) {					
					webDriver.findElementByXPath(strAttribute).click();
				}
			}
			reporter.writeStepResult(sAppname.toUpperCase(),
					"Change checkbox status", strData, "Pass", "Checkbox is "
							+ strData.toUpperCase() + "ED", true, webDriver);
		} catch (WebDriverException w1) {
			reporter.writeStepResult(sAppname.toUpperCase(),
					"Change checkbox status", strData, "Fail", w1.getMessage(),
					true, webDriver);
		} catch (Exception e1) {
			reporter.writeStepResult(sAppname.toUpperCase(),
					"Change checkbox status", strData, "Fail",
					"Not able to perform expected action", true, webDriver);
		}
		return sFlag;
	}

	// Changing radio button status
	public static final boolean changeRadioButtonStatus(String strDriverMethod,
			String strAttribute, Map<String, String> DataMap, String strKey,
			RemoteWebDriver webDriver, String sAppname) {
		Boolean sFlag = true;
		Map<String, String> dataMapLocal = DataMap;
		String strData = null;
		if (dataMapLocal.containsKey(strKey)) {
			strData = dataMapLocal.get(strKey);
		} else {
			sFlag = false;
			return sFlag;
		}
		try {
			if (strDriverMethod.equals("findElementByName")) {
				if (strData.equalsIgnoreCase("deselect")
						&& webDriver.findElementByName(strAttribute)
								.isSelected()) {					
					webDriver.findElementByName(strAttribute).click();
				}
				if (strData.equalsIgnoreCase("select")
						&& !webDriver.findElementByName(strAttribute)
								.isSelected()) {					
					webDriver.findElementByName(strAttribute).click();
				}
			} else if (strDriverMethod.equals("findElementById")) {
				if (strData.equalsIgnoreCase("deselect")
						&& webDriver.findElementById(strAttribute).isSelected()) {					
					webDriver.findElementById(strAttribute).click();
				}
				if (strData.equalsIgnoreCase("select")
						&& !webDriver.findElementById(strAttribute)
								.isSelected()) {					
					webDriver.findElementById(strAttribute).click();
				}
			} else {
				if (strData.equalsIgnoreCase("deselect")
						&& webDriver.findElementByXPath(strAttribute)
								.isSelected()) {					
					webDriver.findElementByXPath(strAttribute).click();
				}
				if (strData.equalsIgnoreCase("select")
						&& !webDriver.findElementByXPath(strAttribute)
								.isSelected()) {					
					webDriver.findElementByXPath(strAttribute).click();
				}
			}
			reporter.writeStepResult(sAppname.toUpperCase(),
					"Change radio button status", strData, "Pass",
					"Radio button is " + strData.toUpperCase() + "ED", true,
					webDriver);
		} catch (WebDriverException w1) {
			reporter.writeStepResult(sAppname.toUpperCase(),
					"Change radio button status", strData, "Fail",
					w1.getMessage(), true, webDriver);
		} catch (Exception e1) {
			reporter.writeStepResult(sAppname.toUpperCase(),
					"Change radio button status", strData, "Fail",
					"Not able to perform expected action ", true, webDriver);
		}
		return sFlag;
	}

	

	//click on link
	public static final void clickLink(String strAttribute,
			RemoteWebDriver webDriver, String msgtitle) {
		String Object=null;
		WebElement helement=null;
		try {
				Object=getElementIdentification(strAttribute);
				helement=getElement(Object);
			
				helement.click();
			
			reporter.writeStepResult(msgtitle.toUpperCase(), "Click on Link",
					"Test data not available", "Pass", "Clicked on Link successfully", false,webDriver);
			} 
			catch (WebDriverException w1)
			{
			reporter.writeStepResult(msgtitle.toUpperCase(), "Click on Link",
					"Test data not available", "Fail", w1.getMessage(), false,webDriver);
			} 
			catch (Exception e1)
			{
			reporter.writeStepResult(msgtitle.toUpperCase(), "Click on Link",
					"Test data not available", "Fail", "Not able to click on Link", false,webDriver);
		}
	}
	

	//right click on any element and click on any menu item
	public static final void rightClick(String strAttribute,String epilink,
			RemoteWebDriver webDriver, String msgtitle) {
		String objectepilink=null;
		String objectepimenu=null;
		WebElement epilinkTst=null;
		WebElement epimenu=null;
		try {
				objectepilink=getElementIdentification(strAttribute);
				epilinkTst=getElement(objectepilink);
				objectepimenu=getElementIdentification(strAttribute);
				epimenu=getElement(objectepimenu);
				
		
				Actions actions=new Actions(webDriver).contextClick(epimenu);
				actions.build().perform();
				epilinkTst.click();
			
			
			reporter.writeStepResult(msgtitle.toUpperCase(), "Click on Edit Mode",
					"Test data not available", "Pass", "Clicked on Edit Mode successfully", false,webDriver);
			} 
		catch (WebDriverException w1)
		{
			reporter.writeStepResult(msgtitle.toUpperCase(), "Click on Edit Mode",
					"Test data not available", "Fail", w1.getMessage(), false,webDriver);
		}
		catch (Exception e1)
		{
			reporter.writeStepResult(msgtitle.toUpperCase(), "Click on Edit Mode",
					"Test data not available", "Fail", "Not able to click on Link", false,webDriver);
		}
	}
	
	//switch to child frame
	public static final void childframeswitch(RemoteWebDriver webDriver, String framename) {

		try {
				webDriver.switchTo().frame(framename);			
		} catch (Exception e1) {
			System.out.println("Unable to switch to required frame");
			
			reporter.writeStepResult("childframeswitch Function", "childframeswitch Function",
					"childframeswitch Issue", "Fail", "childframeswitch Issue", true, webDriver);
		}
	}
	
	//switch to parent frame
	public void parentframeswitch(RemoteWebDriver webDriver) {

		try {
				webDriver.switchTo().defaultContent();			
		} catch (Exception e1) {
			System.out.println("Unable to switch to parent frame");
			
			reporter.writeStepResult("parentframeswitch Function", "parentframeswitch Function",
					"parentframeswitch Issue", "Fail", "parentframeswitch Issue", true, webDriver);
		}
	}
	
	public static final void executeSQLQuery(String strQuery) {
		String driverName = null;// "sun.jdbc.odbc.JdbcOdbcDriver"
		String serverName = "127.0.0.1";
		String portNumber = "1521";
		String sid = "mydatabase";
		String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":"
				+ sid;
		Statement stmt = null;

		try {
			Class.forName(driverName); // Or any other driver
		} catch (Exception x) {
			System.out.println("Unable to load the driver class!");
		}

		try {
			Connection dbConnection = DriverManager.getConnection(url,
					"loginName", "Password");
			stmt = dbConnection.createStatement();
			int rows = stmt.executeUpdate(strQuery);
			if (rows > 0) {

			} else {

			}
		} catch (SQLException x) {
			System.out.println("Couldn’t get connection!");
		}

	}


	public static final String clickRadioButtonProductFinder(RemoteWebDriver webDriver,
			Map<String, String> DataMap, String strKey) {
		// TODO Auto-generated method stub
		WebElement hlelement=null;
		Map<String, String> dataMapLocal = DataMap;
		String strData = null,yes=null;
		String no=null;
		if (dataMapLocal.containsKey(strKey)) {
			strData = dataMapLocal.get(strKey);
		} else {
			System.out.println("no value present");
		}

		if(strData.equalsIgnoreCase("yes"))
		{
		try {		
			yes=".//*[@id='984']";
			hlelement=webDriver.findElementByXPath(yes);
		
			reporter.writeStepResult("Click on Yes button",
					"Click on Yes radio button", strData, "Pass",
					"Clicked on Yes successfully", true,webDriver);
			webDriver.findElementByXPath(yes).click();
		} catch (WebDriverException w1) {
			reporter.writeStepResult("Click on Yes button",
					"Click on Yes radio button", strData, "Fail",
					"Option not present", true,webDriver);
		} catch (Exception e1) {
			reporter.writeStepResult("Click on Yes button",
					"Click on Yes radio button", strData, "Fail",
					"Option not present", true,webDriver);
		}
	}
		
		else if(strData.equalsIgnoreCase("no"))
		{
			
		try {		
			no=".//*[@id='983']";
			hlelement=webDriver.findElementByXPath(no);				
			reporter.writeStepResult("Click on No button",
					"Click on No radio button", strData, "Pass",
					"Clicked on No successfully", true,webDriver);
			webDriver.findElementByXPath(no).click();
			
		} catch (WebDriverException w1) {
			reporter.writeStepResult("Click on No button",
					"Click on No radio button", strData, "Fail",
					"WebDriverException", true,webDriver);
		} catch (Exception e1) {
			reporter.writeStepResult("Click on No button",
					"Click on No radio button", strData, "Fail",
					"Option not present", true,webDriver);
		}
	}
		return strData;
		
	}


	public static final String storeProduct(String ElementObject,
			RemoteWebDriver webDriver) {
				
		// TODO Auto-generated method stub
				
	String selectedproduct=null;
	String Object = getElementIdentification(ElementObject);
						
	try {
			selectedproduct= getElement(Object).getText();
		} catch (WebDriverException w1) {
			System.out.println("Product not selected");
			
			reporter.writeStepResult("storeProduct Function", "storeProduct Function",
			"storeProduct Issue", "Fail", "storeProduct Issue", true,webDriver);
		}
		return selectedproduct;
	}
	
	//Insert new scenarios
	public static final void writeNewScenario(RemoteWebDriver webDriver,String msghead) {
		WebElement hlelement=null;
		
			reporter.writeStepResult(msghead,
					"", "", "",
					"", false,webDriver);
		} 
	
	//accept alert confirmation box
	public static final void acceptAlert(RemoteWebDriver webDriver, String msghead) {
		WebElement hlelement=null;
		try {
			String alertmessage=webDriver.switchTo().alert().getText();
			webDriver.switchTo().alert().accept();
			reporter.writeStepResult(msghead,
					"Accept the confirmation", "Alert message: "+alertmessage, "Pass",
					"Ok button has been clicked succesfully", false,webDriver);
		} catch (WebDriverException w1) {
			System.out.println(w1.getMessage());
		} catch (Exception e1) {
			reporter.writeStepResult(msghead,
					"Accept the confirmation","No alert message", "Fail",
					"Unable to click on OK button", false,webDriver);
		}
	}

	//dismiss or cancel alert confirmation box
	public static final void cancelAlert(RemoteWebDriver webDriver, String msghead) {
				WebElement hlelement=null;
				try {
					String alertmessage=webDriver.switchTo().alert().getText();
					webDriver.switchTo().alert().dismiss();
					reporter.writeStepResult(msghead,
							"Reject the confirmation", "Alert message: "+alertmessage, "Pass",
							"Cancel button has been clicked succesfully", false,webDriver);
				} catch (WebDriverException w1) {
					System.out.println(w1.getMessage());
					reporter.writeStepResult(msghead,
							"Reject the confirmation","No alert message", "Fail",
							"Unable to click on Cancel button", false,webDriver);
					
				} catch (Exception e1) {
					reporter.writeStepResult(msghead,
							"Reject the confirmation","No alert message", "Fail",
							"Unable to click on Cancel button", false,webDriver);
				}
			}
	public static final void waitForExpectedElement( String strDriverMethod,String strAttribute,RemoteWebDriver webDriver){
		   try {
			   if(strDriverMethod.equalsIgnoreCase("Xpath")){
			   (new WebDriverWait(webDriver, 30))
			 .until(ExpectedConditions.presenceOfElementLocated(By.xpath(strAttribute)));
			   }
			   else if(strDriverMethod.equalsIgnoreCase("Id")){
				   (new WebDriverWait(webDriver, 30))
				 .until(ExpectedConditions.presenceOfElementLocated(By.id(strAttribute)));
			   }
		   
		   }
		   catch (Exception e) {
			   
			   reporter.writeStepResult("waitForExpectedElement Function", "waitForExpectedElement Function",
						"waitForExpectedElement Issue", "Fail", "waitForExpectedElement Issue", true, webDriver);
			
		}
	}	
	   public static void waitForPageToLoad(RemoteWebDriver webDriver){
			  try {
				webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			} catch (Exception e) {
				// TODO: handle exception
				
				reporter.writeStepResult("waitForPageToLoad Function", "waitForPageToLoad Function",
						"waitForPageToLoad Issue", "Fail", "waitForPageToLoad Issue", true, webDriver);
			}
	 }
	   public static final void clickElementByCSS(String strAttribute, RemoteWebDriver webDriver) {
			/*
			 * String strDetails = utils.getDataFileInfo(); String [] arrDetails =
			 * strDetails.split("_");
			 */
			/*
			 * WebElement element=webDriver.findElementByCssSelector(strAttribute);
			 * Actions action = new Actions(webDriver);
			 * action.moveToElement(element).click().perform();
			 */

			JavascriptExecutor js = ((JavascriptExecutor) webDriver);
			WebElement element = webDriver.findElementByCssSelector(strAttribute);

			/* highlightElement(webDriver, element); */
			js.executeScript("arguments[0].click();", element);

			/*
			 * WebElement element =
			 * webDriver.findElementByCssSelector(strAttribute);
			 * 
			 * JavascriptExecutor executor = (JavascriptExecutor) webDriver;
			 * executor.executeScript("arguments[0].click();", element);
			 */
			// webDriver.findElementByCssSelector(strAttribute).click();

			/*
			 * boolean sFalg = true;
			 * 
			 * while (sFalg) {
			 * 
			 * sFalg = clickByCss(strAttribute, webDriver);
			 * 
			 * }
			 */
		}   
	   public static final void VerifyFrame(RemoteWebDriver webDriver) {
			/*
			 * try{ Thread.sleep(2000); List<WebElement> frames =
			 * webDriver.findElementsByTagName("iframe"); if(frames.size()>0){
			 * webDriver.switchTo().frame(0);
			 * webDriver.findElementByXPath("//a[.='OK']").click(); }
			 * Thread.sleep(1000); webDriver.switchTo().defaultContent(); }
			 * 
			 * catch(Exception e){ System.out.println(); }
			 */
			try {
				List<WebElement> frames = webDriver.findElementsByTagName("iframe");
				if (frames.size() > 0) {
					webDriver.switchTo().frame(1);
					try {
						/*
						 * if
						 * (webDriver.findElementByCssSelector(".ardbnText .trimJustc"
						 * ).getText().equals(
						 * "Please select whether Off Pending Date is Required")){
						 * webDriver
						 * .findElementByXPath("//fieldset/div/span[2]/label"
						 * ).click();
						 * webDriver.findElementByCssSelector(".ardbnOk .f1"
						 * ).click(); Thread.sleep(4000); }
						 */

						webDriver.findElementByCssSelector(".ardbnOk .f1").click();
						Thread.sleep(4000);
					} catch (Exception ex) {
						// System.out.println();
						
						reporter.writeStepResult("VerifyFrame Function", "VerifyFrame Function",
								"VerifyFrame Issue", "Fail", "VerifyFrame Issue", true,webDriver);
					}
				}

				webDriver.switchTo().defaultContent();
				frames = webDriver.findElementsByTagName("iframe");

				if (frames.size() > 0) {
					webDriver.switchTo().frame(1);
					try {
						webDriver.findElementByXPath("//a[.='Yes']").click();
					} catch (Exception ex) {
						// System.out.println();
					}
				}

				Thread.sleep(1000);
				webDriver.switchTo().defaultContent();
			} catch (Exception e) {
				// System.out.println();
			} finally {
				VerifyAlert(webDriver);
				webDriver.switchTo().defaultContent();
			}
		}
	   public static final void clickImage(String ElementObject,RemoteWebDriver webDriver, String sAppname) {

			try {
				String Object = getElementIdentification(ElementObject);
				getElement(Object).click();
				reporter.writeStepResult(sAppname.toUpperCase(), "Click on Image","", "Pass", "Clicked on image successfully", true,webDriver);
			} catch (WebDriverException w1) {
				reporter.writeStepResult(sAppname.toUpperCase(), "Click on Image",
						"", "Fail", w1.getMessage(), true, webDriver);
			} catch (Exception e1) {
				reporter.writeStepResult(sAppname.toUpperCase(), "Click on Image",
						"", "Fail", "Not able to click on Image", true, webDriver);
			}
		}
	// Selecting List value by using css Selector
	   public static final boolean selectListValueByCss(String strAttribute,
				Map<String, String> DataMap, String strKey,
				RemoteWebDriver webDriver, String sAppName) {
			/*
			 * String strDetails = utils.getDataFileInfo(); int rowNumber =
			 * executionRowNumber.getExecutionRowNumber(); String strData =
			 * scriptExecutor.readDataFile(strDataFileName, rowNumber, strElement);
			 * String [] arrDetails = strDetails.split("_");
			 */
			boolean sFlag = false;
			Map<String, String> dataMapLocal = DataMap;
			String strData = null;
			if (dataMapLocal.containsKey(strKey)) {
				strData = dataMapLocal.get(strKey);
			} else {

				return sFlag;
			}
			try {
				WebElement elementList1 = null;
				Thread.sleep(500);
				List<WebElement> impactDropdown = webDriver.findElements(By
						.cssSelector(strAttribute));
				boolean isPresent = false;
				for (WebElement dropdownItem : impactDropdown) {
					if (dropdownItem.getText().equals(strData)) {
						/*
						 * Actions builder = new Actions(webDriver);
						 * builder.moveToElement
						 * (dropdownItem).click().build().perform();
						 */

						// dropdownItem.click();
						Locatable hoverItem = (Locatable) dropdownItem;
						Mouse mouse = ((HasInputDevices) webDriver).getMouse();
						Thread.sleep(500);
						mouse.mouseMove(hoverItem.getCoordinates());
						JavascriptExecutor executor = (JavascriptExecutor) webDriver;
						executor.executeScript("arguments[0].click();",
								dropdownItem);
						Thread.sleep(500);
						isPresent = true;
						sFlag = true;
						break;

					}
				}

				if (isPresent) {
					reporter.writeStepResult(sAppName.toUpperCase(),
							"Select value from Listbox", strData, "Pass",
							"Expected value is selected", true, webDriver);
				} else {
					reporter.writeStepResult(sAppName.toUpperCase(),
							"Select value from Listbox", strData, "Fail",
							"Expected value is not prsenet in the listbox", true,
							webDriver);
				}

			} catch (WebDriverException w1) {
				reporter.writeStepResult(sAppName.toUpperCase(),
						"Select value from Listbox", strData, "Fail",
						w1.getMessage(), false, webDriver);
			} catch (Exception e1) {
				reporter.writeStepResult(sAppName.toUpperCase(),
						"Select value from Listbox", strData, "Fail",
						"Not able to select expected value", false, webDriver);
			}
			return sFlag;

		}
		// Select Menu Item using css Selector
	   public static final boolean SelectMenuItem(String sCssName, Map<String, String> DataMap,
				String strKey, RemoteWebDriver webDriver) {

			// Get the Elements to Click
			boolean sFlag = false;
			Map<String, String> dataMapLocal = DataMap;
			String strData = null;
			if (dataMapLocal.containsKey(strKey)) {
				strData = dataMapLocal.get(strKey);
			} else {

				return sFlag;
			}
			String[] Elements = strData.split(",");
			int iLen = Elements.length;

			for (int i = 0; i < iLen; i++) {
				sFlag = SelectInternalMenuItem(sCssName, Elements[i], DataMap,
						webDriver);
				if (sFlag == false) {
					reporter.writeStepResult(DataMap.get("ListVal1"),
							"Select Menu item", "", "Fail",
							"Not able to select Menu item '" + DataMap.get(strKey)
									+ "'", true, webDriver);
					// webDriver.findElementById("WIN_0_301583700").click();

					break;
				}

			}
			return sFlag;
		}
		@SuppressWarnings("finally")
		public static final boolean SelectInternalMenuItem(String sCssName, String sSelection,
				Map<String, String> DataMap, RemoteWebDriver webDriver) {
			boolean bFlag = false;
			JavascriptExecutor executor = (JavascriptExecutor) webDriver;

			try {
				Boolean bNeedSelection = false;
				int selectionNumber = 1;
				int conter = 1;
				String[] aOptionSelection = sSelection.split("@");
				if (aOptionSelection.length > 1) {
					sSelection = aOptionSelection[0];
					bNeedSelection = true;
					selectionNumber = Integer.parseInt(aOptionSelection[1]);
				}

				List<WebElement> applicationMenuItems = webDriver.findElements(By
						.cssSelector(sCssName));
				for (WebElement menuItem : applicationMenuItems) {
					if (menuItem.getText().equals(sSelection)) {
						if (bNeedSelection) {
							if (conter == selectionNumber) {
								// executor.executeScript("arguments[0].click();",
								// menuItem);
								menuItem.click();
								bFlag = true;
								break;
							} else {
								conter = conter + 1;
							}
						} else {
							menuItem.click();
							// executor.executeScript("arguments[0].click();",
							// menuItem);
							bFlag = true;
							break;
						}
					}

				}
			} catch (WebDriverException w1) {
				reporter.writeStepResult(DataMap.get("ListVal1"),
						"Select Menu item", "", "Fail", w1.getMessage(), true,
						webDriver);
			} catch (Exception e1) {
				reporter.writeStepResult(DataMap.get("ListVal1"),
						"Select Menu item", "", "Fail",
						"Not able to select Menu item '" + sSelection + "'", true,
						webDriver);
			} finally {
				return bFlag;
			}
		}
		public static final void VerifyAlert(RemoteWebDriver webDriver) {
			try {
				Thread.sleep(2000);
				Alert alert = webDriver.switchTo().alert();
				// alert.accept();
				if (alert != null) {
					alert.dismiss();
					webDriver.navigate().refresh();
				}
				webDriver.switchTo().defaultContent();
			}

			catch (Exception e) {
				webDriver.switchTo().defaultContent();
			}
		}
	public static final boolean isNeedLogin() {
		System.out.println("Return value of needLogin " + needLogin);

		return needLogin;

	}


	public static final void setNeedLogin(boolean needLogin) {
		System.out.println("NeedLogin value changed to " + needLogin);
		BasicOperation.needLogin = needLogin;
	}

	/**
	 * Save value in Framework {@link BasicOperation#Session} and
	 * {@link BasicOperation#tempmap}<br>
	 * <br>
	 * {@code Scope.APPLICATION} for store value till execution complete<br>
	 * {@code Scope.SCENARIO} for store value till Scenario executed.<br>
	 * 
	 * @param key
	 * @param value
	 * @param scope
	 * @author PRAVIRAN
	 */

	@SuppressWarnings("unchecked")
	public static final <K> void addValue(String key, K value, Scope scope) {
		String val = value.toString();
		// Port port =new Port(Port.CURRENT_PORT);
		if (scope == Scope.SCENARIO) {
			tempmap.put(key, val);
		} else if (scope == Scope.APPLIACATION) {
			Session.put(key, value);
		} else {
			System.out.println("Invalid Scope variable");
		}
	}

	/**
	 * Return value from stored in Framework {@link BasicOperation#Session} and
	 * {@link BasicOperation#tempmap}<br>
	 * {@code Scope.APPLICATION} for return value from Application Scope<br>
	 * {@code Scope.SCENARIO} for return value from Scenario Scope.<br>
	 * 
	 * @param key
	 * @param scope
	 * @author PRAVIRAN
	 */
	public static final <K> Object getValue(String key, Scope scope) {
		Object obj = null;
		try {
			if (scope == Scope.SCENARIO) {
				obj = tempmap.get(key);
			} else if (scope == Scope.APPLIACATION) {
				obj = Session.get(key);
			} else {
				System.out.println("Invalid Scope variable");
			}
		} catch (Exception e) {
			System.err.println("[Framework Session Error ] Invalid Key :" + key);
		}
		return obj;
	}


	public static final void addJQUERY() {

		String pageSource = BaseDrivers.getWebDriver().getPageSource();
		while (!pageSource.contains("http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js")) {
			try {
				String jqueryAttachement1 = "node = document.createElement('script');";
				executeScript(jqueryAttachement1);
				waitFor(300);
				String jqueryAttachement2 = "node.setAttribute('src','http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js');"
						+ "\r\n";
				executeScript(jqueryAttachement2);
				waitFor(300);
				String jqueryAttachement3 = "document.head.appendChild(node);" + "\r\n";
				executeScript(jqueryAttachement3);
				waitFor(300);
			} catch (Exception e) {
			}
			pageSource = BaseDrivers.getWebDriver().getPageSource();
		}

		System.err.println("-----> JQuery Added to page");
	}

	
	public static final void executeScript(String script) {
		try {

			System.out.println("[JavaScript]" + script);
			JavascriptExecutor javascript = (JavascriptExecutor) BaseDrivers.getWebDriver();
			String response = (String) javascript.executeScript("return " + script);
			System.out.println("[JavaScript Response]" + response);
		} catch (Exception e) {

			// e.printStackTrace();
			checkForException(BaseDrivers.getWebDriver());
		}
	}

	/**
	 * No Used Method
	 * 
	 * @param Object
	 * @param Table
	 * @param TBody
	 */
	public static final void checkTableElement(String Object, String Table, String TBody) {

		try {
			(new WebDriverWait(driver, 120)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(Object)));
		} catch (Exception e) {
			try {
				(new WebDriverWait(driver, 120)).until(ExpectedConditions.presenceOfElementLocated(By.id(Object)));

			} catch (Exception b) {
				try {
					(new WebDriverWait(driver, 120))
							.until(ExpectedConditions.presenceOfElementLocated(By.name(Object)));

				} catch (Exception df) {
				}
			}
		}

		WebElement table_element = null;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {
			table_element = driver.findElement(By.xpath(Table));
		} catch (Exception e) {
			try {
				table_element = driver.findElement(By.id(Table));
			} catch (Exception ew) {
				try {
					table_element = driver.findElement(By.name(Table));
				} catch (Exception t) {

				}
			}
		}

		List<WebElement> tr_collection = table_element.findElements(By.xpath(TBody));

		System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
		int row_num, col_num;
		row_num = 1;
		for (WebElement trElement : tr_collection) {
			List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS=" + td_collection.size());
			col_num = 1;
			for (WebElement tdElement : td_collection) {
				System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
				col_num++;
			}
			row_num++;
		}

	}

	/**
	 * Screenshot method called from all action methods fill(), selectById() and
	 * click()
	 * 
	 * @author PRAVIRAN
	 */

	public static final void takeScreenShot() {
		try {

			/*
			 * Class of Java.awt.Robot used to captured image
			 */

			Robot robo = new Robot();

			// System.err.println("[Image Captured] Capture image:"+image_no);

			/*
			 * Captured image with screen resolution
			 */

			BufferedImage image = robo.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

			/*
			 * Image file write in .JPG format in Data/Result/screenshot Folder
			 */
			ImageIO.write(image, "jpg", new File(screenshotfile + "/" + (image_no) + ".jpg"));

			image_no++;

		} catch (Exception e) {
			
		}

	}

	public static final void saveDocument(String path) {

		JavaApplication1.save(path);

	}

	/**
	 * Method used to create report and set flag of cont_operation to true for
	 * new Data iteration
	 */

	@SuppressWarnings("deprecation")
	public static final void setReporter(String TestCase, RemoteWebDriver Driver, Reporter reporter) {
		/**
		 * For new data iteration
		 */
		cont_operation = true;
		tempmap = new HashMap<>();
		/**
		 * FailCase flag re-initialize
		 */
		FailCase = false;
		try {
			Date date = new Date();
			// logFileWriter = new
			// FileHandler(TestCase+"."+date.getDate()+"."+(date.getMonth()+1)+"."+(date.getYear()+1900)+"."+date.getTime()+".log");
			// log.addHandler(logFileWriter);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		

		/**
		 * Call ImageDoc to get all captured images in .Doc file
		 */

		if (screenshotfile != null) {
			ImagesDoc.save();

		}

		/**
		 * Save image with Testcase , Date and random number
		 */
		screenshotfile = new File("results/screenshot/" + TestCase + "_" + new Date().getDay() + "_"
				+ new Date().getMonth() + "_" + new Date().getYear() + "_" + ((int) (Math.random() * 100)));
		// System.err.println("[Screen Shot] Created Folder for Images"+screenshotfile.getName());

		screenshotfile.mkdir();

		image_no = 0;
		//
		Class cl = null;
		try {
			cl = Class.forName("com.capgemini.scripts." + TestCase);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log = Logger.getLogger(cl.getName());

		iteration = 10;
		BasicOperation.AppName = TestCase;
		BasicOperation.Driver = Driver;
		BasicOperation.reporter = reporter;

	}

	/**
	 * LookUp action for circuit, Theatre and Release
	 * 
	 * @param Object
	 * @param webDriver
	 */
	public static final <K> void selectLookUpAction(String Object, K popupURL, WebDriver webDriver) {
		try {
		BasicOperation.waitFor(2000);
		System.err.println("----------- [Look Up in Action] ---------");

		String Currenthandle = webDriver.getWindowHandle();

		System.out.println("[Look Up in Action]parent window id:" + Currenthandle);

		Set<String> handles = webDriver.getWindowHandles();

		for (String winHandle : webDriver.getWindowHandles()) {

			System.out.println("[Look Up in Action]In Handle loop" + winHandle);

			if (!Currenthandle.equals(winHandle)) {
				webDriver.switchTo().window(winHandle);
				System.out.println("[Look Up in action] URL:" + webDriver.getCurrentUrl());
				if (webDriver.getCurrentUrl().contains(popupURL.toString()))
					BasicOperation.click(Object, webDriver);
			}
		}

		System.err.println("------------ [Look up in action end] --------------");

		driver.switchTo().window(Currenthandle);
		} catch (Exception e) {			
			e.printStackTrace();	
			
			
		}
	}

	public static final void changeTab(String Object) {
		click(Object, LoadDriver.driver);

	}

	/**
	 * No Used Method
	 * 
	 * @return
	 */

	/**
	 * <p>
	 * Wait for milliseconds.<br>
	 * it used <B>Thread.sleep(Milliseconds)</B> for waiting.<br>
	 * if <B>BasicOperation.<font color=red>cont_operation</font> </B> is
	 * <B><font color=blue>FALSE</font></B> then it never call sleep method.
	 * </p>
	 * 
	 * @param MillinSeconds
	 */

	public static final void waitFor(int MillinSeconds) {
		try {
			if (BasicOperation.cont_operation)
				Thread.sleep(MillinSeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * get <b> Map<? extends Object, ? extends Object> </b> from current running
	 * UC<br>
	 * to pass value from excelsheet <br>
	 * Create Database Connection using Server Host Name
	 * 
	 * @param map
	 */
	public static final Map<? extends Object, ? extends Object> getPropertyMap() {
		return propertyMap;
	}

	public static final void setPropertyMap(Map<? extends Object, ? extends Object> propertyMap) {
		BasicOperation.propertyMap = propertyMap;
	}

	public static final Map<? extends Object, ? extends Object> getDataMap() {
		return DataMap;
	}

	@SuppressWarnings("unchecked")
	public static final void setDataMap(Map<? extends Object, ? extends Object> map) {
		DataMap = (Map<String, String>) map;
		try {
			// repotr.addSubTab(AppName, reportdata);
		} catch (Exception e) {
		}
		String command = "wmic process where name=\"IEDRIVERSERVER.exe\" CALL setpriority \"high priority\"";
		Runtime run = Runtime.getRuntime();
		try {
			run.exec(command);
			System.out.println("Command on high priority");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in execution of command!");
			e.printStackTrace();
		}

		/**
		 * Create Database Connection using Server Host Name @ Pravin
		 **/
		FailCase = false;
		DBConnection.setDatabase((String) map.get("URL"));
		if (Cont_Operation_disable_reason.isAlive()) {
			Cont_Operation_disable_reason.start();
			Cont_Operation_diable_reason_Thread_start = 0;
		}

	}

	@SuppressWarnings("unchecked")
	public static final void setPropertiesDataMap(Map<? extends Object, ? extends Object> map) {
		propertyMap = map;

		if (fetchXpathFromServerDb) {
			try {

				Socket sock = new Socket(serverHost, 10005);
				System.out.println("Server Info >>>" + sock);
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());

				pw.write(DataBase + "\r\n");
				pw.flush();
				pw.write(AppName + "\r\n");
				pw.flush();
				pw.write(DataMap.get("Territory") + "\r\n");
				pw.flush();

				propertyMap = (Map) in.readObject();

				System.out.println("=========>>>>>>> " + propertyMap);
				in.close();
				pw.close();
				sock.close();

			} catch (Exception e) {
				JOptionPane
						.showMessageDialog(
								null,
								" UNABLE TO CONNECT WITH DATABSE SERVER : "
										+ BasicOperation.serverHost
										+ "\r\n Please Contact to Server Admin. \r\n OR \r\n Set  => BasicOpration.fetchXpathFromServerDB = 'false';  <= to read Data from ObjectIdentifier sheet.",
								"Framework Issue", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}

		reportdata = new Object[100][100];
		steps += readindex;
		timeStamp.add(readindex);
		readindex = 1;
		Datacount = 0;
		//dataModel.put(AppName + ":" + (ExecutionReporter.iteration++), reportdata);

		// repotr.addSubTab(AppName, reportdata);

		ExecutionHistory.addToHistory(AppName, FailCase, DataMap);

		FailCase = false;

	}

	/**
	 * Select CheckBok depends on Yes or No parameter <br>
	 * used in update scenario.
	 * 
	 * @param Object
	 * @param Yes_No
	 * @param webDriver
	 */
	public static final void clickCheckBox(String ElementObject, String Yes_No, WebDriver webDriver) {

		int count = 0;
		// Port port =new Port(Port.CURRENT_PORT);
		String Object = (ElementObject);

		/*
		 * needupdate flag is used when Module Parameter is in 'update' state
		 */
		int needUpdate = 0;
		boolean opertionContinue = true;
		String value = "";

		/*
		 * get DataMap from UC and read value from this Map
		 */
		@SuppressWarnings("unchecked")
		Map<String, String> dataMapLocal = (Map<String, String>) DataMap;

		/*
		 * checking for checkbox isSelected() status
		 */
		if (dataMapLocal.containsKey(Yes_No)) {
			value = dataMapLocal.get(Yes_No);
		} else {
			value = Yes_No;
		}

		/*
		 * get Module action from Excel sheet
		 */
		String addOrUpdate = dataMapLocal.get("Module");

		String prev_value = "";
		String next_value = "";

		if (addOrUpdate.equalsIgnoreCase("update") && (!value.equalsIgnoreCase("null")) && (value.length() >= 1)) {

			next_value = value;

			prev_value = checkBoxValue(Object, webDriver);

			System.out.println("New checkbox Val:" + next_value + " , Prev checkbox Val:" + prev_value);

			if (!prev_value.equalsIgnoreCase(next_value)) {
				clear(Object, webDriver);
			} else {
				needUpdate = 1;
			}

		}

		while (opertionContinue && cont_operation && (needUpdate == 0) && (!value.equalsIgnoreCase("null"))
				&& value.length() >= 1) {

			try {
				// driver.wait(getTimeOut(), 3000);
				/*
				 * Check element whether Object parameter is found by 'xpath'
				 */
				getElement(Object).click();
				String mydata[] = { (readindex++) + "", "Click", ElementObject, Yes_No, "-", "Pass" };
				reportdata[Datacount++] = mydata;
				System.out.println("[Info] 'Click' is checking with path:" + Object);
				opertionContinue = false;
				prev = Object;
			} catch (Exception e) {

				try {
					Thread.sleep(LoadDriver.sleepTime);
					count++;
					if (count > iteration) {

						/*
						 * Fail Condition
						 */
						opertionContinue = false;
						FailCase = true;
						cont_operation = false;
						iteration = 1;

						reporter.writeStepResult(AppName, "Click on element", "", "Fail", "Clicked element Fail", true,
								Driver);
						// System.exit(1);

					}
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error Occurs :\n" + e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

			}

		}

	}

	public static final Thread Cont_Operation_disable_reason = new Thread() {
		@SuppressWarnings("static-access")
		@Override
		public  void run() {
			System.out.println("[BasicOperation Thread] Cont_Operation_diable_reason THREAD start.");
			for (;;) {
				if (!cont_operation) {
					try {
						this.sleep(1000);
					} catch (Exception e) {
					}
					checkForException(BaseDrivers.webDriver);
				}
			}

		}
	};

	/**
	 * Added for Redundant code remove purpose
	 * 
	 * @author PRAVIRAN
	 * @since 23 Oct 2015
	 */

	public static String getElementIdentification(String ElementObject) {
		String Object = null;
		String Objectbackup = ElementObject;
		if (fetchXpathFromServerDb) {
			try {
				ElementObject = ElementObject.replaceAll(" ", "_");
				ElementObject = ElementObject.replaceAll("-", "XXX");
				ElementObject = ElementObject.replaceAll(":", "__");
				ElementObject = ElementObject.toLowerCase();
				// System.out.println("ElementObject >>>> " + ElementObject);
				if (propertyMap.containsKey(ElementObject) || propertyMap.containsKey(ElementObject + "_")) {
					Object = propertyMap.containsKey(ElementObject) ? propertyMap.get(ElementObject).toString()
							: propertyMap.get(ElementObject + "_").toString();
				} else
					Object = Objectbackup;
			} catch (Exception rr) {
				Object = Objectbackup;
			}
		} else if (propertyMap.containsKey(ElementObject)) {
			Object = (String) propertyMap.get(ElementObject);
		} else
			Object = ElementObject;

		return Object;
	}

	/**
	 * This method used <br>
	 * <font color=blue> Xpath, Id, Name, Css<br>
	 * </font> is used to locate element. <br>
	 * It auto select element and perform click action.
	 * 
	 * @param ElementObject
	 * @param webDriver
	 * @author PRAVIRAN
	 * @see BasicOperation#genericClick(String)
	 */

	@Test
	public static final void click(String ElementObject, WebDriver webDriver) {

		int count = 0;
		click++;
		boolean opertionContinue = true;
		String orignalName = ElementObject;
		String Object = getElementIdentification(ElementObject);
		// Port port =new Port(Port.CURRENT_PORT);

		// System.err.println();
		log.info("Object >" + Object);

		while (opertionContinue && cont_operation) {

			try {
				// driver.wait(getTimeOut(), 3000);
				getElement(Object).click();
				String mydata[] = { (readindex++) + "", "Click", ElementObject, "-", "-", "Pass" };
				reportdata[Datacount++] = mydata;
				log.info("'Click' is checking with path:" + Object);
				takeScreenShot();
				opertionContinue = false;
				prev = Object;
			} catch (Exception e) {

				try {
					waitFor(LoadDriver.sleepTime);
					count++;
					if (count > iteration) {
						takeScreenShot();

						if (resumeScriptOn) {
							ResumeScript res = new ResumeScript(ElementObject, Object, "Click Operation without value",
									"click");
							boolean flag = true;
							while (flag) {
								flag = res.frame2.isVisible();
								if (!flag) {
									System.out.println("Windows Disposed!");
								}
							}

							String action = ResumeScript.action;
							if (action.equalsIgnoreCase("Continue")) {
								if (ResumeScript.newXpathValue.trim().length() > 1) {
									Object = ResumeScript.newXpathValue;
									System.out.println(">>>> New Object :" + Object);
								}

								count = 0;
							} else if (action.equalsIgnoreCase("Discard")) {
								resumeScriptOn = false;
								String mydata[] = { (readindex++) + "", "Click", ElementObject, "-", "-", "Fail" };
							} else {
								System.out.println("Other option");
							}
						}

						if (!resumeScriptOn) {
							endReport();
							opertionContinue = false;
							cont_operation = false;
							FailCase = true;
							checkForException(webDriver);
							iteration = 1;
							System.out
									.println("\n\n\n [FrameWork Warning] Unable to Find Following Component :\n --------->"
											+ orignalName + "\n\n\n");
							reporter.writeStepResult(AppName, "Click on element", "", "Fail", "Clicked element Fail",
									true, Driver);
							// System.exit(1);
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error Occurs :\n" + e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
					
					reporter.writeStepResult(AppName, "Click on element", "", "Fail", "Clicked element Fail",
							true, Driver);
				}

			}
		}

	}

	/**
	 * <br>
	 * Read value of {@code Textarea Textfield} from webApplication
	 * 
	 * @param ElementObject
	 * @param webDriver
	 * @see BasicOperation#valueOf(String, WebDriver)
	 * 
	 * @return
	 */
	public static final String txtValue(String ElementObject, WebDriver webDriver) {
		String value = "";
		String Object = getElementIdentification(ElementObject);
		readValue++;

		try {
			value = getElement(Object).getAttribute("value");
			String data[] = { (readindex++) + "", "Read Value", ElementObject, "-", value, "Pass" };
			reportdata[Datacount++] = data;
		} catch (Exception e) {
			
			e.printStackTrace();

		}

		return value;
	}

	/**
	 * @see BasicOperation#genericClickCheckBox(String, String)
	 * @param ElementObject
	 * @param webDriver
	 * @return
	 */

	public static final String checkBoxValue(String ElementObject, WebDriver webDriver) {
		Boolean value = true;

		String Object = getElementIdentification(ElementObject);

		try {
			value = getElement(Object).isSelected();
		} catch (Exception e) {
		}

		if (value)
			return "Yes";

		else
			return "No";

	}

	/** 
	        Updated By - Siddhartha Haldar
	        Created On - 11/22/2016 
	        Updated content - To find element by linktext,cssSelector
	        
	 */	

	public static WebElement getElement(String ElementObject) {
		String Object = getElementIdentification(ElementObject);
		WebElement element = null;
		WebDriver webDriver = BaseDrivers.getWebDriver();

		try {
			element = webDriver.findElement(By.xpath(Object));
		} catch (Exception e) {
			try {
				element = webDriver.findElement(By.id(Object));
			} catch (Exception e2) {
				try {
					element = webDriver.findElement(By.name(Object));
				} catch (Exception er) {
					try {
						element = webDriver.findElement(By.className(Object));
					} catch (Exception ern) {
						try {
							element = webDriver.findElement(By.cssSelector(Object));
						} catch (Exception ern1) {
							try {
								element = webDriver.findElement(By.linkText(Object));
							} catch (Exception ern2) {
							}
						}
					}
				}
			}
		}

		return element;
	}

	/** 
	        Updated By - Siddhartha Haldar
	        Created On - 11/22/2016 
	        Updated content - To find element by linktext,cssSelector
	        
	 */	

	//Return List of Elements Present

	public static List<WebElement> getElements(String ElementObject) {
		String Object = getElementIdentification(ElementObject);
		List<WebElement> element = null;
		WebDriver webDriver = BaseDrivers.getWebDriver();
		try {
			element = webDriver.findElements(By.xpath(Object));
		} catch (Exception e) {
			try {
				element = webDriver.findElements(By.id(Object));
			} catch (Exception e2) {
				try {
					element = webDriver.findElements(By.name(Object));
				} catch (Exception er) {
					try {
						element = webDriver.findElements(By.cssSelector(Object));
					} catch (Exception ern1) {
						try {
							element = webDriver.findElements(By.linkText(Object));
						} catch (Exception ern2) {
						}
					}
				}
			}
		}

		return element;
	}

	/**
	 * Return TRUE when checkbox is selected otherwise return FALSE
	 * 
	 * @since 2 March 15
	 * @param ElementObject
	 * @return
	 */

	public static boolean checkBoxValue(String ElementObject) {
		return (checkBoxValue(ElementObject, BaseDrivers.getWebDriver()).equalsIgnoreCase("yes")) ? true : false;
	}

	/**
	 * @see BasicOperation#clear(String, WebDriver)
	 * 
	 * @param ResourceObject
	 */
	/**
	 * @see BasicOperation#genericClear(String)
	 * @param ElementObject
	 * @param webDriver
	 */
	public static void clear(String ElementObject, WebDriver webDriver) {

		String Object = ElementObject;
		clear++;

		try {
			BasicOperation.getElement(Object).clear();
			String data[] = { (readindex++) + "", "Clear Value", ElementObject, "", "", "Pass" };
			reportdata[Datacount++] = data;
		} catch (Exception e) {

		}

	}


	/**
	 * This method used <br>
	 * <font color=blue> Xpath, Id, Name, Css<br>
	 * </font> is used to locate element. <br>
	 * It auto select element and perform click action. <br>
	 * <br>
	 * {@code
	 * Added new option $Blank$ to update field to blank.
	 * Modified  6 May 15
	 * }
	 * 
	 * @param Object
	 * @param webDriver
	 * @author PRAVIRAN
	 */
	@Test
	public static final <K> void fill(String ElementObject, K key, WebDriver webDriver) {
		int count = 0;
		fill++;
		boolean opertionContinue = true;
		String orignalName = ElementObject;
		String elem = key.toString();
		boolean valueChangeforBlank = true;
		// Port port =new Port(Port.CURRENT_PORT);
		if (elem.trim().length() > 1) {

			String Object = ElementObject;

			System.err.println("Fill >" + Object.trim());

			int needUpdate = 0;
			String value = null;
			@SuppressWarnings("unchecked")
			Map<String, String> dataMapLocal = (Map<String, String>) DataMap;
			System.out.println("Data Map:" + DataMap);

			if (dataMapLocal.containsKey(elem.trim())) {
				value = dataMapLocal.get(key).trim();
			} else {
				value = elem.trim();
			}

			String addOrUpdate = dataMapLocal.get("Module").trim();

			String prev_value = "";
			String next_value = "";
			if (addOrUpdate.equalsIgnoreCase("update")) {

				next_value = value;
				prev_value = BasicOperation.txtValue(Object, webDriver).trim();

				System.out.println("New Val:" + next_value + " , Prev Val:" + prev_value);

				if (!prev_value.equalsIgnoreCase(next_value) && (!value.equalsIgnoreCase("null"))
						&& value.length() >= 1) {
					BasicOperation.clear(Object, webDriver);
				} else {
					needUpdate = 1;
				}

			}

			while (opertionContinue && cont_operation && (needUpdate == 0) && value.length() >= 1
					&& (!value.equalsIgnoreCase("null"))) {

				try {

					if (value.equalsIgnoreCase("$blank$") && valueChangeforBlank) {
						value = "";
						valueChangeforBlank = false;
					}

					getElement(Object).sendKeys(value);
					log.info("'Fill' is checking with :" + Object);
					opertionContinue = false;
					String data[] = { (readindex++) + "", "Fill Value", ElementObject, value + "", "-", "Pass" };
					reportdata[Datacount++] = data;
					takeScreenShot();
					try {
						reporter.writeStepResult(AppName, "Enter Value in text field", value, "Pass",
								"Value entered successfully", true, Driver);
					} catch (Exception e2) {
						System.out.println(e2);
						e2.printStackTrace();
					}
				} catch (Exception r) {

					try {
						waitFor(LoadDriver.sleepTime);
						count++;
						if (count > iteration) {

							if (resumeScriptOn) {
								ResumeScript res = new ResumeScript(ElementObject, Object, value, "fill");
								boolean flag = true;
								while (flag) {
									flag = res.frame2.isVisible();
									if (!flag) {
										System.out.println("Windows Disposed!");
									}
								}

								String action = ResumeScript.action;
								if (action.equalsIgnoreCase("Continue")) {
									if (ResumeScript.newXpathValue.trim().length() > 1) {
										Object = ResumeScript.newXpathValue;
										System.out.println(">>>> New Object :" + Object);
									}
									if (ResumeScript.newtxtValue.trim().length() > 1) {
										value = ResumeScript.newtxtValue;
										System.out.println(">>> New Value :" + value);
									}
									count = 0;
								} else if (action.equalsIgnoreCase("Discard")) {
									resumeScriptOn = false;
									String data[] = { (readindex++) + "", "Fill Value", ElementObject, value + "", "-",
											"Fail" };
									reportdata[Datacount++] = data;
								} else {
									System.out.println("Ohter option");
								}
							}

							if (!resumeScriptOn) {
								endReport();
								opertionContinue = false;
								cont_operation = false;
								FailCase = true;
								iteration = 1;
								log.info("\n\n\n [FrameWork Warning] Unable to Find Following Component :\n --------->"
										+ orignalName + "\n\n\n");
								System.out
										.println("\n\n\n [FrameWork Warning] Unable to Find Following Component :\n --------->"
												+ orignalName + "\n\n\n");
								reporter.writeStepResult(AppName, "Enter Value in text field", value, "Fail",
										"Value insertion Fail", true, Driver);
							}

						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						// JOptionPane.showMessageDialog(null,
						// "Error Occurs :\n"+e1,"Error",JOptionPane.ERROR_MESSAGE);

						e1.printStackTrace();
					}
				}

			}

		}

	}

	/**
	 * This method used <br>
	 * <font color=blue> Xpath, Id, Name, Css<br>
	 * </font> is used to locate element. <br>
	 * It auto identify element and perform select action.
	 * 
	 * @see BasicOperation#genericSelectById(String, int)
	 * @param Object
	 * @param webDriver
	 * @author PRAVIRAN
	 */

	@SuppressWarnings("rawtypes")
	public static final void selectValueFromDropdown(String ElementObject, int select, WebDriver webDriver) {

		// int count=0;
		int flag = 0;
		selectById++;
		String orignalName = ElementObject;
		int count2 = 0;
		boolean opertionContinue = true;
		WebElement selected = null;
		// Port port =new Port(Port.CURRENT_PORT);
		String Object = (ElementObject);

		while (opertionContinue) {

			try {

				selected = getElement(Object);
				String data[] = { (readindex++) + "", "SelectById", ElementObject, select + "", "-", "Pass" };
				reportdata[Datacount++] = data;
				log.info("[Info] 'SelectByID' is checking with ID:" + Object);
				System.out.println("[Info] 'SelectByID' is checking with ID:" + Object);
				opertionContinue = false;
			} catch (Exception e) {
				try {
					flag = 1;

					try {
						waitFor(LoadDriver.sleepTime);
						count2++;
						if (count2 > iteration) {

							if (resumeScriptOn) {
								ResumeScript res = new ResumeScript(ElementObject, Object, select + "", "selectById");
								boolean flag2 = true;
								while (flag2) {
									flag2 = res.frame2.isVisible();
									if (!flag2) {
										System.out.println("Windows Disposed!");
									}
								}

								String action = ResumeScript.action;
								if (action.equalsIgnoreCase("Continue")) {
									if (ResumeScript.newXpathValue.trim().length() > 1) {
										Object = ResumeScript.newXpathValue;
										System.out.println(">>>> New Object :" + Object);
									}

									count2 = 0;
								} else if (action.equalsIgnoreCase("Discard")) {
									resumeScriptOn = false;
									String data[] = { (readindex++) + "", "SelectById", ElementObject, select + "",
											"-", "Fail" };
									reportdata[Datacount++] = data;
								} else {
									System.out.println("Ohter option");
								}
							}

							if (!resumeScriptOn) {
								endReport();
								opertionContinue = false;
								cont_operation = false;
								FailCase = true;
								iteration = 1;
								log.info("\n\n\n [FrameWork Warning] Unable to Find Following Component :\n --------->"
										+ orignalName + "\n\n\n");
								System.out
										.println("\n\n\n [FrameWork Warning] Unable to Find Following Component :\n --------->"
												+ orignalName + "\n\n\n");
								reporter.writeStepResult(AppName, "Enter Value in text field", "Option" + select,
										"Fail", "Value insertion Fail", true, Driver);
							}
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						// JOptionPane.showMessageDialog(null,
						// "Error Occurs :\n"+e1,"Error",JOptionPane.ERROR_MESSAGE);

						e1.printStackTrace();
						System.err.println("No Element Found Error");
					}

				} catch (Exception es) {
				}
			}

		}

		if (flag == 0) {
			List options = null;
			while (options == null || options.size() < 1)
				options = (List) selected.findElements(By.tagName("option"));

			@SuppressWarnings("unchecked")
			Iterator<WebElement> i = ((java.util.List<WebElement>) options).iterator();
			int count = 0;
			while (i.hasNext()) {

				WebElement str = i.next();
				// System.out.println(str.getText());
				if (count == select) {
					str.click();
					break;
				}

				else if ((options.size() <= select) && (!RestrictionMode)) {
					if (!str.getText().equalsIgnoreCase("select")) {
						str.click();
						break;
					}
				}
				count++;

			}

		}

	}

	/**
	 * <br>
	 * Read value of Label from webApplication
	 * 
	 * @param ElementObject
	 * @param webDriver
	 * @see BasicOperation#txtValue(String, WebDriver)
	 * 
	 * @return
	 */

	public static final String valueOf(String ElementObject, WebDriver webDriver) {
		int count = 0;
		readValue++;
		String Object = (ElementObject);

		boolean opertionContinue = true;
		String returnValue = "";
		WebElement element = null;
		// String strData = scriptExecutor.readDataFile(strDataFileName,
		// rowNumber, strElement);
		while (opertionContinue && cont_operation) {
			try {
				element = getElement(Object);
				returnValue = element.getText();
				String data[] = { (readindex++) + "", "Read Value", ElementObject, "-", returnValue, "Pass" };
				reportdata[Datacount++] = data;
				log.info("'valueOf' is checking with path:" + Object);
				opertionContinue = false;
			} catch (Exception r) {

				try {
					waitFor(LoadDriver.sleepTime);
					count++;
					if (count > iteration) {
						opertionContinue = false;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					
				}
			}
		}
		return returnValue;
	}

	/**
	 * check whether parameter string is null or empty
	 * 
	 * @param str
	 * @return boolean flag
	 */

	public static final boolean isBlank(String str) {
		boolean checkType = false;

		if (str.trim().length() < 1)
			return true;
		else if (str.isEmpty())
			return true;

		else if (str.equalsIgnoreCase(""))
			return true;

		else if (str == null)
			return true;

		else if (str.equalsIgnoreCase(null))
			return true;
		else if (str.equalsIgnoreCase("null"))
			return true;

		return checkType;
	}


	/**
	 * This method used <br>
	 * <font color=blue> Xpath, Id, Name, Css<br>
	 * </font> is used to locate element. <br>
	 * It auto identify element and perform select action.
	 * 
	 * @param Object
	 * @param webDriver
	 * @author PRAVIRAN
	 */

	@SuppressWarnings("rawtypes")
	public static final <K> void selectById(String ElementObject, K key, WebDriver webDriver) {
		String orignalName = ElementObject;
		// int count=0;
		int flag = 0;
		int needUpdate = 0;
		selectById++;
		int count2 = 0;
		String elem = key.toString();
		// Port port =new Port(Port.CURRENT_PORT);
		boolean opertionContinue = true;
		WebElement selected = null;
		String value = null;
		@SuppressWarnings("unchecked")
		Map<String, String> dataMapLocal = (Map<String, String>) DataMap;
		System.out.println("Data Map:" + DataMap);
		String strData = null;

		String Object = getElementIdentification(ElementObject);

		if (dataMapLocal.containsKey(key)) {
			value = dataMapLocal.get(key);
		} else {
			value = elem.trim();
		}

		String addOrUpdate = dataMapLocal.get("Module");

		String prev_value = "";
		String next_value = "";
		//if (addOrUpdate.equalsIgnoreCase("update")) {

		next_value = value;
		prev_value = BasicOperation.txtValue(Object, webDriver);

		System.out.println("New Val:" + next_value + " , Prev Val:" + prev_value);
		if (!prev_value.equalsIgnoreCase(next_value)) {
			// BasicOperation.clear(Object, webDriver);
		} else {
			needUpdate = 1;
		}

		//}

		while (opertionContinue && cont_operation && (needUpdate == 0) && value.length() >= 1
				&& (!value.equalsIgnoreCase("null"))) {
			try {
				selected = getElement(Object);
				System.out.println("[Info] 'SelectByID' is checking with ID:" + Object);
				reporter.writeStepResult(AppName, "Select Value in dropdown field", value, "Pass",
						"Value Selected successfully", true, Driver);
				opertionContinue = false;
			} catch (Exception e) {

				flag = 1;
				try {
					waitFor(LoadDriver.sleepTime);
					count2++;
					if (count2 > iteration) {
						if (resumeScriptOn) {
							ResumeScript res = new ResumeScript(ElementObject, Object, value, "selectById");
							boolean flag2 = true;
							while (flag2) {
								flag2 = res.frame2.isVisible();
								if (!flag2) {
									System.out.println("Windows Disposed!");
								}
							}
							String action = ResumeScript.action;
							if (action.equalsIgnoreCase("Continue")) {
								if (ResumeScript.newXpathValue.trim().length() > 1) {
									Object = ResumeScript.newXpathValue;
									System.out.println(">>>> New Object :" + Object);
								}
								if (ResumeScript.newtxtValue.trim().length() > 1) {
									value = ResumeScript.newtxtValue;
									System.out.println(">>> New Value :" + value);
								}
							} else if (action.equalsIgnoreCase("Discard")) {
								resumeScriptOn = false;
							} else {
								System.out.println("Ohter option");
							}
						}

						if (!resumeScriptOn) {

							opertionContinue = false;
							cont_operation = false;
							endReport();
							iteration = 1;
							log.info("\n\n\n [FrameWork Warning] Unable to Find Following Component :\n --------->"
									+ orignalName + "\n\n\n");
							System.out
									.println("\n\n\n [FrameWork Warning] Unable to Find Following Component :\n --------->"
											+ orignalName + "\n\n\n");
							reporter.writeStepResult(AppName, "Select Value in dropdown field", value, "Fail",
									"Value Selection Fail", true, Driver);
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error Occurs :\n" + e1, "Error", JOptionPane.ERROR_MESSAGE);

					e1.printStackTrace();
					System.err.println("No Element Found Error");
				}

			}

			if (flag == 0) {
				List options = null;
				while (options == null || options.size() < 1)
					options = (List) selected.findElements(By.tagName("option"));

				@SuppressWarnings("unchecked")
				Iterator<WebElement> i = ((java.util.List<WebElement>) options).iterator();
				int count = 0;
				System.out.println("[Info] List Element : {");
				boolean selectionFlag = false;
				while (i.hasNext()) {

					WebElement str = i.next();
					String element = str.getText();
					System.out.println();
					System.out.print("\t Option :" + element);
					if (element == value || element.equalsIgnoreCase(value) || element.equals(value)) {
						str.click();
						String data[] = { (readindex++) + "", "Select By", ElementObject, str.getText(), "-", "Pass" };
						reportdata[Datacount++] = data;
						selectionFlag = true;
						System.err.print("<---- Selected");
						takeScreenShot();
						System.out.println();
						break;
					}
					count++;
				}
				if ((!selectionFlag) && (!RestrictionMode)) {
					Iterator<WebElement> i2 = ((java.util.List<WebElement>) options).iterator();
					while (i2.hasNext()) {
						WebElement str = i2.next();
						String element = str.getText();
						String data[] = { (readindex++) + "", "Select By", ElementObject, str.getText(), "-", "Pass" };
						reportdata[Datacount++] = data;
						System.out.println();
						System.out.print("\t Option :" + element);
						if (!(element.equalsIgnoreCase("Select") || element.contains("Select"))) {
							str.click();
							String data2[] = { (readindex++) + "", "Select By", ElementObject, str.getText(), "-",
									"Pass" };
							reportdata[Datacount++] = data2;
							selectionFlag = true;
							System.err.print("<---- Selected");
							takeScreenShot();
							System.out.println();
							break;
						}
						count++;
					}

				}
				System.out.println("}");
			}
		}

	}

	/**
	 * Select Value from List By Value <br>
	 * Value can accept String or Array of String
	 * 
	 * @since 2 March 15
	 * @author PRAVIRAN
	 * @param Xpath
	 * @param value
	 * @param webDriver
	 */
	public static final <K> void selectElementFromList(String Xpath, K value, WebDriver webDriver) {
		String Object = getElementIdentification(Xpath);

		String elemn = value.toString();
		try {

		if (DataMap.containsKey(elemn.trim()))
			elemn = DataMap.get(elemn.trim()).toString().trim();

		if (cont_operation && elemn.length() > 1) {
			if (!elemn.contains(",")) {

				Select select = new Select(BasicOperation.getElement(Xpath));
				select.deselectAll();
				select.selectByValue(elemn);

				takeScreenShot();
			} else {
				Select select = new Select(BasicOperation.getElement(Xpath));
				select.deselectAll();
				String values[] = elemn.split(",");

				for (String regex : values) {
					select.selectByValue(regex);

				}
				takeScreenShot();
			}
		}
		} catch (Exception e) {			
			e.printStackTrace();			
		}
	}

	/**
	 * Select Value from List By Index
	 * 
	 * @since 2 March 15
	 * @author PRAVIRAN
	 * @param Xpath
	 * @param value
	 * @param webDriver
	 */
	public static final void selectElementFromList(String Xpath, int index, WebDriver webDriver) {
		String Object = getElementIdentification(Xpath);
		try {
		if (cont_operation) {
			Select select = new Select(BasicOperation.getElement(Xpath));
			select.deselectAll();
			select.selectByIndex(index);
			takeScreenShot();
		}
		} catch (Exception e) {			
			e.printStackTrace();				
		}
	}

	/**
	 * @since 27 March 15 [Modified]
	 * @param Xpath
	 * @author PRAVIRAN
	 *
	 * @return
	 */
	public static final <K> List<K> getSelectedElementsFromList(String Xpath) {
		String Object = getElementIdentification(Xpath);
		List<WebElement> elementList = new ArrayList<>();
		Select select = new Select(BasicOperation.getElement(Xpath));
		elementList = select.getAllSelectedOptions();
		List<String> element = new ArrayList<>();
		for (WebElement elements : elementList) {
			element.add(elements.getText());
		}
		return (List<K>) element;
	}

	/**
	 * @since 27 March 15
	 * @param Xpath
	 * @return
	 */
	public static final <K> List<K> getAllElementsFromList(String Xpath) {
		String Object = getElementIdentification(Xpath);
		List<WebElement> elementList = new ArrayList<>();
		Select select = new Select(BasicOperation.getElement(Xpath));
		elementList = select.getOptions();
		List<K> element = new ArrayList<>();
		for (WebElement elements : elementList) {
			element.add((K) elements.getText());
		}
		return (List<K>) element;

	}

	public static final void checkForException(WebDriver webDriver) {
		try {
			System.out.println("[BasicOperation Exception checking] Checking for Exception");
			if (webDriver.getPageSource().contains("Exception") && webDriver.getPageSource().contains("Stack")
					&& webDriver.getPageSource().contains("Error")) {
				takeScreenShot();
				System.err.println("[Basic Operation Alert] ******* Getting Exception on Page !!! ********");
				takeScreenShot();
				BasicOperation.cont_operation = false;
				// needLogin=true;
				setNeedLogin(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	        Created By - Siddhartha Haldar
	        Cretaed On - 11/22/2016
			Updated By - Pratibha Bhosale
	        Updated On - 11/29/2016  
	        Objective - To click on element
	        
	 */		
		public static final void clickElement(String ElementObject,	RemoteWebDriver webDriver, String msgtitle) {

			WebElement helement=null;
			try {
				String Object = getElementIdentification(ElementObject);
				helement=getElement(Object);
				helement.click();

				reporter.writeStepResult(msgtitle.toUpperCase(), "Click on Element",
						"Test data not available", "Pass", "Clicked on element successfully", false,webDriver);
			} catch (Exception e1) {
				reporter.writeStepResult(msgtitle.toUpperCase(), "Click on Element",
						"Test data not available", "Fail", "Not able to click on element", false,webDriver);
			}
		}
	
	
	public static final void endReport() {
		int executed = (ExecutionHistory.readData().containsKey(AppName)) ? Integer.parseInt(ExecutionHistory
				.readData().get(AppName).toString()) : 0;

		try {

			if (BaseDrivers.getWebDriver().getPageSource().contains("Exception")
					&& BaseDrivers.getWebDriver().getPageSource().contains("Stack")
					&& BaseDrivers.getWebDriver().getPageSource().contains("Error")) {
				String data[] = { (BasicOperation.readindex++) + "", "Execution Fail beacuase of Exception on page",
						"Execution Fail", "Execution Fail", "", "Fail" };
				reportdata[Datacount++] = data;
			} else if (BasicOperation.getElement(ResumeScript.oldPath.getText()).isDisplayed()) {
				if (BasicOperation.getElement(ResumeScript.oldPath.getText()).isEnabled()) {
					String data[] = { (BasicOperation.readindex++) + "", "Execution Fail beacuase Element disabled",
							"Execution Fail", "Execution Fail", "", "Fail" };
					BasicOperation.reportdata[BasicOperation.Datacount++] = data;
				} else {
					String data[] = { (BasicOperation.readindex++) + "",
							"Execution Fail beacuase Element is not present on webpage", "Execution Fail",
							"Execution Fail", "", "Fail" };
					BasicOperation.reportdata[BasicOperation.Datacount++] = data;
				}
				MainController m = new MainController();
				try {

					m.printMessage(BasicOperation.AppName,
							Integer.parseInt(BasicOperation.getDataMap().get("Index").toString()), "Script Failed!");
				} catch (Exception ee) {
					m.printMessage(BasicOperation.AppName, 1, "Script Failed!");
				}
			}

		} catch (Exception ee) {

			String data[] = { (BasicOperation.readindex++) + "", "Execution Fail beacuase of unknown reason",
					"Execution Fail", "Execution Fail", "", "Fail" };
			reportdata[Datacount++] = data;
			MainController m = new MainController();
			try {

				m.printMessage(BasicOperation.AppName, Integer.parseInt(getDataMap().get("Index").toString()),
						"Script Failed!");
			} catch (Exception ef) {
				m.printMessage(BasicOperation.AppName, 1, "Script Failed!");
			}

		}

		finally {
			// ExecutionHistory.writeData(AppName+":"+executed, "true");

			//dataModel.put(AppName + ":" + (ExecutionReporter.iteration++), reportdata);

			// repotr.addSubTab(AppName, reportdata);

			ExecutionHistory.addToHistory(AppName, true, DataMap);

			if (resumeScriptOn && ResumeScript.action.equalsIgnoreCase("exit")) {
				BasicOperation.FailCase = true;
				//ExecutionReporter ex = repotr;
				//ex.setVisible();
			}
		}

	}

}
