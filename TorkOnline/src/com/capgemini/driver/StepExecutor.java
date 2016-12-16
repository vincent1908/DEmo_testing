package com.capgemini.driver;

import java.awt.AWTException;
import java.awt.Robot;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.executor.ExecutionRowNumber;
import com.capgemini.utilities.CreateResult;
import com.capgemini.utilities.Reporter;
import com.capgemini.utilities.Utilities;
import com.capgemini.utilities.Verification;

/**
 * StepExecutor --- Class for the executing the steps in the script
 * 
 * @author Sunil Kumar
 */

public class StepExecutor {
	Reporter reporter;
	Utilities utils;
	private Verification verify;
//
	public StepExecutor(Reporter reporter) {

		this.reporter = reporter;
		utils = new Utilities(reporter);

		verify = new Verification(reporter);

	}

//	// Launching the specific URL
	public Boolean launchApplication(String strColumnName,
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
}
