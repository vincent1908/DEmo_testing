package com.capgemini.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.Base.BasicOperation;

/**
 * Verification --- Class for verification of test steps using Webdriver
 * 
 * @author Prasad Joshi
 */

public class Verification {
	static Reporter reporter;
	Utilities utils;
	Map<String, String> DataMap;

	public Verification(Reporter reporter) {
		this.reporter = reporter;

		utils = new Utilities(reporter);
		DataMap = new HashMap();

	}

	// Verifying title of the page
	public Boolean verifyPageTitle(RemoteWebDriver webDriver,String strKey,Map<String, String> DataMap) {
		Boolean sFlag = true;
		Map<String, String> dataMapLocal = DataMap;
		String strData = null;
		boolean isExpecetdTitle = false;
		/*String strActualTtle = null;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");*/

		try {
			if (dataMapLocal.containsKey(strKey)) {
				strData = dataMapLocal.get(strKey);
				isExpecetdTitle=true;
			} else {
				sFlag = false;
				return sFlag;
			}

			
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());

		}
		if (isExpecetdTitle) {
			reporter.writeStepResult("Verification of Page Title",
					"Verify title of page",strData, "Pass",
					"Page title matches with expected", true, webDriver);
		} else {
			reporter.writeStepResult("Verification of Page Title",
					"Verify title of page", "Expected: "
							+ strData, "Pass",
					"Page title does not match with expected (Actual: "
							+strData  + ")", true, webDriver);
		}
		return sFlag;
	}

	public void verifyElementPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty) {
		
		boolean exists = false;
		try {
			for (int interval = 0; interval < 5; interval++) {
				if (strElementProperty.equalsIgnoreCase("name")) {
					if (webDriver.findElementsByName(strElement).size() != 0
							&& webDriver.findElementByName(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("id")) {
					if (webDriver.findElementsById(strElement).size() != 0
							&& webDriver.findElementById(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("xpath")) {
					if (webDriver.findElementsByXPath(strElement).size() != 0
							&& webDriver.findElementByXPath(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("css")) {
					if (webDriver.findElementsByCssSelector(strElement).size() != 0
							&& webDriver.findElementByCssSelector(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else {
					exists = false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (exists) {
			reporter.writeStepResult("VERIFY ",
					"Verify element is present on the page",
					strElement.toUpperCase(), "Pass",
					"Element is present on the page", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify element is present on the page",
					strElement.toUpperCase(), "Fail",
					"Element is not present on the page", true, webDriver);
		}
	}
	// Verifying whether element is present on the page
	public void verifyElementPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,String element) {
		
		String temp=element.toUpperCase();
		boolean exists = false;
		try {
			for (int interval = 0; interval < 5; interval++) {
				if (strElementProperty.equalsIgnoreCase("name")) {
					if (webDriver.findElementsByName(strElement).size() != 0
							&& webDriver.findElementByName(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("id")) {
					if (webDriver.findElementsById(strElement).size() != 0
							&& webDriver.findElementById(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("xpath")) {
					if (webDriver.findElementsByXPath(strElement).size() != 0
							&& webDriver.findElementByXPath(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("css")) {
					if (webDriver.findElementsByCssSelector(strElement).size() != 0
							&& webDriver.findElementByCssSelector(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else {
					exists = false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (exists) {
			reporter.writeStepResult("VERIFY "+temp,
					"Verify element is present on the page",
					strElement.toUpperCase(), "Pass",
					"Element is present on the page", true, webDriver);
		} else {
			reporter.writeStepResult("VERIFY "+temp,
					"Verify element is present on the page",
					strElement.toUpperCase(), "Fail",
					"Element is not present on the page", true, webDriver);
		}
	}

	// Verifying whether link is present on the page
	public void verifyLinkPresent(RemoteWebDriver webDriver, String strElement) {

		boolean exists = false;
		try {
			for (int interval = 0; interval < 30; interval++) {
				if (webDriver.findElementsByLinkText(strElement).size() != 0
						&& webDriver.findElementByLinkText(strElement)
								.isDisplayed()) {
					exists = true;
					break;
				} else if (webDriver.findElementsByName(strElement).size() != 0
						&& webDriver.findElementByName(strElement)
								.isDisplayed()) {
					exists = true;
					break;
				} else if (webDriver.findElementsById(strElement).size() != 0
						&& webDriver.findElementById(strElement).isDisplayed()) {
					exists = true;
					break;
				} else if (webDriver.findElementsByXPath(strElement).size() != 0
						&& webDriver.findElementByXPath(strElement)
								.isDisplayed()) {
					exists = true;
					break;
				} else {
					exists = false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify link is present on the page",
					strElement.toUpperCase(), "Fail",
					"Link is not present on the page", true, webDriver);
		}
		if (exists) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify link is present on the page",
					strElement.toUpperCase(), "Pass",
					"Link is present on the page", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify link is present on the page",
					strElement.toUpperCase(), "Fail",
					"Link is not present on the page", true, webDriver);
		}
	}

	// Verifying whether element text is present on the page
	public void verifyElementTextPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty, String strExpectedText) {

		String strActualText = null;
		

		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementById(strElement)
						.getAttribute("value");
				if (strActualText == null) {
					strActualText = webDriver.findElementByXPath(strElement)
							.getText();
				}
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByName(strElement)
						.getAttribute("value");
				if (strActualText == null) {
					strActualText = webDriver.findElementByXPath(strElement)
							.getText();
				}
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByXPath(strElement)
						.getAttribute("value");
				if (strActualText == null) {
					strActualText = webDriver.findElementByXPath(strElement)
							.getText();
				}

			}
			if (strElementProperty.equalsIgnoreCase("css")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByCssSelector(strElement)
						.getAttribute("value");
				if (strActualText == null) {
					strActualText = webDriver.findElementByCssSelector(
							strElement).getText();
				}

			}

		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualText.equals(strExpectedText)) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify text is present in the element", "Expected: "
							+ strExpectedText, "Pass",
					"Expected text  is present", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify text is present in the element", "Expected: "
							+ strExpectedText, "Fail",
					"Expected text  is not present (Actual: " + strActualText
							+ ")", true, webDriver);
		}
	}

	public void verifyDefaultTextPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty, String strExpectedText) {
		String strActualText = null;
	
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementById(strElement)
						.getAttribute("defaultTxt");
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByName(strElement)
						.getAttribute("defaultTxt");
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByXPath(strElement)
						.getAttribute("defaultTxt");
			}

		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualText.equals(strExpectedText)) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify default text is present in the element",
					"Expected: " + strExpectedText, "Pass",
					"Expected text is present", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify default text is present in the element",
					"Expected: " + strExpectedText, "Fail",
					"Expected text  is not present (Actual: " + strActualText
							+ ")", true, webDriver);
		}
	}

	// Verifying whether element attribute value is present on the page
	public void verifyTextValue(RemoteWebDriver webDriver, String strElement,
			String strElementProperty, String strExpectedText) {

		String strActualText = null;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementById(strElement)
						.getAttribute("value");
			}
			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByName(strElement)
						.getAttribute("value");
			}
			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByXPath(strElement)
						.getAttribute("value");
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualText.equals(strExpectedText)) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify value attribute of element", "Expected: "
							+ strExpectedText, "Pass",
					"Expected value is present", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify value attribute of element", "Expected: "
							+ strExpectedText, "Fail",
					"Expected value is not present (Actual: " + strActualText
							+ ")", true, webDriver);
		}
	}

	// Verifying whether element text is absent on the page
	public void verifyElementTextAbsent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty, String strExpectedText) {
		String strActualText = null;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");

		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementById(strElement).getText();
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByName(strElement)
						.getText();
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByXPath(strElement)
						.getText();
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (!strActualText.equals(strExpectedText)) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify text is not present in the element", "Expected: "
							+ strExpectedText, "Pass",
					"Expected text is absent", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify text is not present in the element", "Expected: "
							+ strExpectedText, "Fail",
					"Expected text is present", true, webDriver);
		}
	}

	// Verifying number of elemnts on the page
	public void verifyListValue(RemoteWebDriver webDriver, String strElement,
			String strElementProperty, String strExpectedValue) {

		WebElement listbox = null;
		boolean exists = false;
		
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementById(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementByName(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementByXPath(strElement);
			}

			List<WebElement> options = listbox.findElements(By
					.tagName("option"));
			for (WebElement option : options) {
				if (option.getText().equals(strExpectedValue)) {
					exists = true;
					break;
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (exists) {
			reporter.writeStepResult("VERIFY",
					"Verify value is present in the listbox", "Expected: "
							+ strExpectedValue, "Pass",
					"Expected value is prsenet in the listbox", true, webDriver);
		} else {
			reporter.writeStepResult("VERIFY",
					"Verify value is present in the listbox", "Expected: "
							+ strExpectedValue, "Fail",
					"Expected value is not prsenet in the listbox", true,
					webDriver);
		}
	}

	public void verifyListValues(RemoteWebDriver webDriver, String strElement,
			String strElementProperty, String strExpectedValues) {
		WebElement listbox = null;
		String[] arrListValues = strExpectedValues.split(";");
		int counter = 0;
		
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementById(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementByName(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementByXPath(strElement);
			}

			List<WebElement> options = listbox.findElements(By
					.tagName("option"));
			for (int i = 0; i < arrListValues.length; i++) {
				for (WebElement option : options) {
					if (option.getText().equals(arrListValues[i]))
						counter++;
					break;
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (counter == arrListValues.length) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify values are present in the listbox", "Expected: "
							+ strExpectedValues, "Pass",
					"Expected values are prsenet in the listbox", true,
					webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify values are present in the listbox", "Expected: "
							+ strExpectedValues, "Fail",
					"Expected values are not prsenet in the listbox", true,
					webDriver);
		}
	}

	public void verifySelectedListValue(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,
			String strExpectedSelectedValue) {
		WebElement listbox = null;
		boolean isSelected = false;
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementById(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementByName(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementByXPath(strElement);
			}

			List<WebElement> options = listbox.findElements(By
					.tagName("option"));
			for (WebElement option : options) {
				if (option.isSelected())
					isSelected = true;
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (isSelected) {
			reporter.writeStepResult("VERIFYING SELECT LIST VALUE",
					"VERIFY SELECTED VALUE IS PRESENT IN THE LISTBOX", "Expected: "
							+ strExpectedSelectedValue, "Pass",
					"Expected value is selected in the listbox", true,
					webDriver);
		} else {
			reporter.writeStepResult("VERIFYING SELECT LIST VALUE",
					"VERIFY SELECTED VALUE NOT PRESENT IN THE LISTBOX", "Expected: "
							+ strExpectedSelectedValue, "Fail",
					"Expected value is not selected in the listbox", true,
					webDriver);
		}
	}

	public void verifyCheckboxStatus(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,
			String strExpectedStatus) {

		String strActualStatus = null;

		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualStatus = webDriver.findElementById(strElement)
						.getAttribute("checked");
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualStatus = webDriver.findElementByName(strElement)
						.getAttribute("checked");
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualStatus = webDriver.findElementByXPath(strElement)
						.getAttribute("checked");
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualStatus.equalsIgnoreCase("true")
				&& strExpectedStatus.equalsIgnoreCase("checked")) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify checkbox is checked", "Expected: "
							+ strExpectedStatus, "Pass", "Checkbox is checked",
					true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify checkbox is checked", "Expected: "
							+ strExpectedStatus, "Fail",
					"Checkbox is not checked", true, webDriver);
		}

		if (strActualStatus.equalsIgnoreCase("false")
				&& strExpectedStatus.equalsIgnoreCase("unchecked")) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify checkbox is not checked", "Expected: "
							+ strExpectedStatus, "Pass",
					"Checkbox is not checked", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify checkbox is not checked", "Expected: "
							+ strExpectedStatus, "Fail", "Checkbox is checked",
					true, webDriver);
		}

	}

	public void verifyRadioButtonStatus(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,
			String strExpectedStatus) {
		String strActualStatus = null;
		boolean isSelected = false;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualStatus = webDriver.findElementById(strElement)
						.getAttribute("checked");
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualStatus = webDriver.findElementByName(strElement)
						.getAttribute("checked");
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualStatus = webDriver.findElementByXPath(strElement)
						.getAttribute("checked");
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualStatus.equalsIgnoreCase("true")
				&& strExpectedStatus.equalsIgnoreCase("selected")) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify radio button is selected", "Expected: "
							+ strExpectedStatus, "Pass",
					"Radio button is selected", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify radio button is checked", "Expected: "
							+ strExpectedStatus, "Fail",
					"Radio button is not selected", true, webDriver);
		}

		if (strActualStatus.equalsIgnoreCase("false")
				&& strExpectedStatus.equalsIgnoreCase("deselected")) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify radio button is not selected", "Expected: "
							+ strExpectedStatus, "Pass",
					"Radio button is not selected", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify radio button is not selected", "Expected: "
							+ strExpectedStatus, "Fail",
					"Radio button is selected", true, webDriver);
		}
	}

	// Verifying whether second element text is present on the page as the first
	// element is hidden
	public void verify2ndElementTextPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty, String strExpectedText) {

		String strActualText = null;
		
		List<WebElement> l1;

		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				l1 = webDriver.findElementsByCssSelector(strElement);
				strActualText = l1.get(1).getAttribute("value");
				if (strActualText == null) {
					strActualText = l1.get(1).getText();
				}
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				l1 = webDriver.findElementsByCssSelector(strElement);
				strActualText = l1.get(1).getAttribute("value");
				if (strActualText == null) {
					strActualText = l1.get(1).getText();
				}
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				l1 = webDriver.findElementsByCssSelector(strElement);
				strActualText = l1.get(1).getAttribute("value");
				if (strActualText == null) {
					strActualText = l1.get(1).getText();
				}

			}
			if (strElementProperty.equalsIgnoreCase("css")) {
				l1 = webDriver.findElementsByCssSelector(strElement);
				strActualText = l1.get(1).getAttribute("value");
				if (strActualText == null) {
					strActualText = l1.get(1).getText();
				}

			}

		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualText.equals(strExpectedText)) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify text is present in the element", "Expected: "
							+ strExpectedText, "Pass",
					"Expected text  is present", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify text is present in the element", "Expected: "
							+ strExpectedText, "Fail",
					"Expected text  is not present (Actual: " + strActualText
							+ ")", true, webDriver);
		}
	}
	public void verifyImagePresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,String element) {
		String temp=element.toUpperCase();
		boolean exists = false;
		try {
			for (int interval = 0; interval < 5; interval++) {
				if (strElementProperty.equalsIgnoreCase("name")) {
					if (webDriver.findElementsByName(strElement).size() != 0
							&& webDriver.findElementByName(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("id")) {
					if (webDriver.findElementsById(strElement).size() != 0
							&& webDriver.findElementById(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("xpath")) {
					if (webDriver.findElementsByXPath(strElement).size() != 0
							&& webDriver.findElementByXPath(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("css")) {
					if (webDriver.findElementsByCssSelector(strElement).size() != 0
							&& webDriver.findElementByCssSelector(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else {
					exists = false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (exists) {
			reporter.writeStepResult("VERIFICATION OF IMAGE "+temp,
					"Verify element is present on the page",
					strElement.toUpperCase(), "Pass",
					"Element is present on the page", true, webDriver);
		} else {
			reporter.writeStepResult("VERIFICATION OF IMAGE "+temp,
					"Verify element is present on the page",
					strElement.toUpperCase(), "Fail",
					"Element is not present on the page", true, webDriver);
		}
	}
	
	///---------------
	
	// Verifying heading is clickable

			public void headingClickable(RemoteWebDriver webDriver,
				String strElement, String strElementProperty) throws Exception	{
			try{
				
				String hyperlink = null;
				
					if (strElementProperty.equalsIgnoreCase("name")) {
						hyperlink  = webDriver.findElementByName(strElement).getAttribute("href");									
						if (hyperlink == null)
						{
							System.out.println("Heading is not clickable..");
						}						
					}

					else if (strElementProperty.equalsIgnoreCase("id")) {
						hyperlink  = webDriver.findElementById(strElement).getAttribute("href");											
						if (hyperlink == null)
						{
							System.out.println("Heading is not clickable..");
						}
					}//webDriver.findElementByXPath(strElement).getAttribute("value");
					else if (strElementProperty.equalsIgnoreCase("xpath")) {
						hyperlink  = webDriver.findElementByXPath(strElement).getAttribute("href");					
						System.out.println("hyperlink is "+hyperlink);
						if (hyperlink == null)
						{
							System.out.println("Heading is not clickable..");
						}	
						else {}
					}

			}catch (Exception e1) {
				System.out.println("Exception occurred -- " + e1.getMessage());
				System.out.println("Heading is clickable..");
			}
		}


	

	// Verifying Image is clickable-- if clickable the click on that

	public  void imageClickable(RemoteWebDriver webDriver,
		String strElement, String strElementProperty,
		String strElementhyperlink,String homestrElement,String PageTitle) throws Exception	{
		Boolean sFlag = true;
		
	try{
		
		String src = null;
		
			if (strElementProperty.equalsIgnoreCase("name")) {
					src = webDriver.findElementByName(strElement).getAttribute("src");	
					System.out.println("hyperlink is :"+src);
					
					if (src == null)
					{
						System.out.println("Image is not displayed..");
						sFlag = false;
					}			
					else
					{
						System.out.println("Image is displayed..");
						String clk =webDriver.findElementByName(strElementhyperlink).getAttribute("href");
						System.out.println("image hyperlink is :"+clk);
						if (clk != null)
						{
							System.out.println("Image is clickable..");
							webDriver.findElementByName(strElementhyperlink).click();
							Thread.sleep(3000);							
							// Get tile and verify title
							System.out.println(" tiltle is :"+webDriver.getTitle()+"*****************");
							String actualTitle = webDriver.getTitle();
							String expectedTitle = PageTitle;
							if (expectedTitle.equalsIgnoreCase(actualTitle)){
							       System.out.println("Verification Successful - The correct title is displayed on the web page.");
							       sFlag = true;
							      }
							    else
							       {
							            System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
							            sFlag = false;
							        }
							
							System.out.println("Back to home page.");
							webDriver.findElementByXPath(homestrElement).click();
							Thread.sleep(3000);
						}
						else
						{
							System.out.println("Image is not clickable..");
							sFlag = false;
						}
					}
			}

			else if (strElementProperty.equalsIgnoreCase("id")) {
				src = webDriver.findElementById(strElement).getAttribute("src");	
				System.out.println("hyperlink is :"+src);
				if (src == null)
				{
					System.out.println("Image is not displayed..");
					sFlag = false;
				}			
				else
				{
					System.out.println("Image is displayed..");
					String clk =webDriver.findElementById(strElementhyperlink).getAttribute("href");
					System.out.println("image hyperlink is :"+clk);
					if (clk != null)
					{
						System.out.println("Image is clickable..");
						webDriver.findElementById(strElementhyperlink).click();
						Thread.sleep(3000);

						// Get tile and verify title
						System.out.println(" tiltle is :"+webDriver.getTitle()+"*****************");
						String actualTitle = webDriver.getTitle();
						String expectedTitle = PageTitle;
						if (expectedTitle.equalsIgnoreCase(actualTitle)){
						       System.out.println("Verification Successful - The correct title is displayed on the web page.");
						       sFlag = true;
						      }
						    else
						       {
						            System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
						            sFlag = false;
						        }
						
						
						System.out.println("Back to home page.");
						webDriver.findElementByXPath(homestrElement).click();
						Thread.sleep(3000);
					}
					else
					{
						System.out.println("Image is not clickable..");
						 sFlag = false;
					}
				}
		}
			else if (strElementProperty.equalsIgnoreCase("xpath")) {
				System.out.println("Element is : "+strElement);
				src = webDriver.findElementByXPath(strElement).getAttribute("src");	
				System.out.println("hyperlink is :"+src);
				if (src == null)
				{
					System.out.println("Image is not displayed..");
					 sFlag = false;
				}			
				else
				{
					System.out.println("Image is displayed..");
					String clk =webDriver.findElementByXPath(strElementhyperlink).getAttribute("href");
					System.out.println("image hyperlink is :"+clk);
					if (clk != null)
					{
						System.out.println("Image is clickable..");
						webDriver.findElementByXPath(strElementhyperlink).click();
						Thread.sleep(3000);
					
						// Get tile and verify title
						System.out.println(" tiltle is :"+webDriver.getTitle()+"*****************");
						String actualTitle = webDriver.getTitle();
						String expectedTitle = PageTitle;
						if (expectedTitle.equals(actualTitle)){
						       System.out.println("Verification Successful - The correct title is displayed on the web page.");
						       sFlag = true;
						      }
						    else
						       {
						            System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
						            sFlag = false;
						        }
						
						System.out.println("Back to home page.");
						webDriver.findElementByXPath(homestrElement).click();
						Thread.sleep(3000);
					}
					else
					{
						System.out.println("Image is not clickable..");
						sFlag = false;
					}
				}
		}
			
				else {
					sFlag = false;
				}
			

	}catch (Exception e1) {
		System.out.println("Exception occurred -- " + e1.getMessage());
		System.out.println("Heading is clickable..");
		sFlag = false;
	}
	
	if(sFlag)
	{
			reporter.writeStepResult("Image Clickable ",
				"Verify image is clickable",
				strElement.toUpperCase(), "Pass",
				"Element is present on the page", true, webDriver);
		
//			reporter.writeStepResult("Image Clickable",
//				"Verify image is clickable ",strElement.toUpperCase(), "Pass",
//				"Image clickable successfull", true,webDriver);
	}
	else
	{
			reporter.writeStepResult("Image Clickable",
				"Verify image is not clickable ",strElement.toUpperCase(), "Fail",
				"Image clickable Failed", true,webDriver);
	}
}

	// Verifying ReadMore  is clickable-- if clickable the click on that and back to home page

	public  void Clickable(RemoteWebDriver webDriver,
		String strElement, String strElementProperty,
		String homestrElement,String PageTitle) throws Exception	{
		Boolean sFlag = true;
		String clk=null;
		
	try{
		
		String title = null;
		
			if (strElementProperty.equalsIgnoreCase("name")) {
				title = webDriver.findElementByName(strElement).getAttribute("title");	
				
				if (title == null)
					{
						System.out.println("Link is not displayed..");
						sFlag = false;
					}			
					else
					{
						System.out.println("Link is displayed..");
						 clk =webDriver.findElementByName(strElement).getAttribute("href");
						System.out.println("Link hyperlink is :"+clk);
						if (clk != null)
						{
							System.out.println("Link is clickable..");
							webDriver.findElementByName(strElement).click();
							Thread.sleep(3000);
							
							if (clk.contains(PageTitle))
							{
							      System.out.println("Verification Successful - The correct title is displayed on the web page.");
							      sFlag = true;
							 }
						  else
							  {
							         System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
							         sFlag = false;
							  }
						
							
							System.out.println("Back to home page.");
							webDriver.findElementByXPath(homestrElement).click();
							Thread.sleep(3000);
						}
						else
						{
							System.out.println("Link is not clickable..");
							sFlag = false;
						}
					}
			}

			else if (strElementProperty.equalsIgnoreCase("id")) {
				title = webDriver.findElementById(strElement).getAttribute("title");	
				
				if (title == null)
				{
					System.out.println("Link is not displayed..");
					sFlag = false;
				}			
				else
				{
					System.out.println("Link is displayed..");
					clk =webDriver.findElementById(strElement).getAttribute("href");
					System.out.println("Link hyperlink is :"+clk);
					if (clk != null)
					{
						System.out.println("Link is clickable..");
						webDriver.findElementById(strElement).click();
						Thread.sleep(3000);		

						// Get tile and verify title
						if (clk.contains(PageTitle))
							{
								System.out.println("Verification Successful - The correct title is displayed on the web page.");
								sFlag = true;
								}
						    else
						       {
						            System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
						            sFlag = false;
						        }
						
						
						System.out.println("Back to home page.");
						webDriver.findElementByXPath(homestrElement).click();
						Thread.sleep(3000);
					}
					else
					{
						System.out.println("Link is not clickable..");
						sFlag = false;
					}
				}
		}
			else if (strElementProperty.equalsIgnoreCase("xpath")) {							
				title = webDriver.findElementByXPath(strElement).getAttribute("title");	
				
				if (title == null)
				{
					System.out.println("Link is not displayed..");
					sFlag = false;
				}			
				else
				{
					System.out.println("Link is displayed..");
					clk =webDriver.findElementByXPath(strElement).getAttribute("href");
					System.out.println("Link hyperlink is :"+clk);
					if (clk != null)
					{
						System.out.println("Link is clickable..");
						webDriver.findElementByXPath(strElement).click();
						Thread.sleep(4000);

						// Get tile and verify title
						if (clk.contains(PageTitle))
						{
						      System.out.println("Verification Successful - The correct title is displayed on the web page.");
						      sFlag = true;
						 }
						 else
						   {
						       System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
						       sFlag = false;
						    }
						
						
						System.out.println("Back to home page.");
						webDriver.findElementByXPath(homestrElement).click();
						Thread.sleep(3000);
					}
					else
					{
						System.out.println("Link is not clickable..");
						sFlag = false;
					}
				}
		}
			
				else {sFlag = false;}
			

	}catch (Exception e1) {
		System.out.println("Exception occurred -- " + e1.getMessage());
		System.out.println("Read more  issue..");
		sFlag = false;
	}
					
	if(sFlag)
	{
		reporter.writeStepResult("Link Clickable",
				"Verify Link is clickable ","Link "+clk, "Pass",
				"Link clickable successfull", false,webDriver);
	}
	else
	{
		reporter.writeStepResult("Link Clickable",
				"Verify Link is clickable ","Link "+clk, "Fail",
				"Link clickable Failed", false,webDriver);
	}
	
}
	
	
	
}
