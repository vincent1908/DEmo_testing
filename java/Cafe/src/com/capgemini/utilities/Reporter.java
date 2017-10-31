package com.capgemini.utilities;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.collections.map.StaticBucketMap;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

import com.Base.BasicOperation;
import com.capgemini.driver.ScriptExecutor;
import com.capgemini.executor.Executioner;
import com.capgemini.executor.MasterReport;
import com.capgemini.executor.New_Executioner;
//import com.capgemini.executor.Executioner;
import com.thoughtworks.selenium.Selenium;

import org.w3c.dom.Element;

/**
 * Reporter --- Class for generation detail and high level reports
 * 
 * @author Prasad Joshi
 */

public class Reporter {
	ReadExcel excel = new ReadExcel();
	private String strDetails;	
	private String passFile="temp.html";
	private String failFile="temp2.html";
	List<Boolean> arr = new ArrayList<Boolean>(); 
	
	FileWriter wr;
	FileWriter wr2;
	
	
	RemoteWebDriver webDriver = null;
	public Reporter(String testcasename) {
		strDetails = testcasename;
		passFile="results/"+testcasename+(int)(Math.random()*100)+"_pass.html";
		File file = new File(passFile);
		passFile= file.getAbsolutePath();
		
		failFile="results/"+testcasename+(int)(Math.random()*100)+"_fail.html";
		File file2 = new File(failFile);
		failFile= file2.getAbsolutePath();
		
	}
	public Reporter() {
		//strDetails = testcasename;

	}
	String StrStatus = null;
	String sHyperLink = "";
	static String strIdTemp;
	private String browserTestng;
	private String browserName;
	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd-hh.mm.ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	private String strAbsolutepath = new File("").getAbsolutePath();
	private String strScreenshot;
	static String strAbsolutePath = new File("").getAbsolutePath();
	// static List<String> tempList_scenario_name1 = new ArrayList<String>(0);
	// static List<String> tempList_teststep_description = new
	// ArrayList<String>(0);
	// static List<String> tempList_test_data = new ArrayList<String>(0);
	// static List<String> tempList_snapshot = new ArrayList<String>(0);
	// static List<String> tempList_status = new ArrayList<String>(0);
	// static List<String> tempList_result_description = new
	// ArrayList<String>(0);

	// static List<String> listScenarioDetails = new ArrayList<String>(0);
	List<Integer> listPassDetails = new ArrayList<Integer>(0);
	List<Integer> listFailDetails = new ArrayList<Integer>(0);
	List<Integer> listTotalStepsDetails = new ArrayList<Integer>(0);
	List<String> listReportFileDetails = new ArrayList<String>(0);

	String strScenarioDetails = null;
	String strPassDetails = null;
	String strFailDetails = null;
	String strTotalStepsDetails = null;
	String strReportFileDetails = null;
	int iPassCount = 0;
	int iFailCount = 0;
	int iterPassCount = 0;
	int iterFailCount = 0;
	int iPercentageOfPassFail = 0;
	
public static String browser;
	public static int month;
	public static int day;
	public static int year;

	public String strFinalStartTime;
	public String strFinalStopTime;

	public String strStartTime;
	public String strStopTime;

	public float timeElapsed;
	public long startTime;
	public long stopTime;

	public Calendar calendar = new GregorianCalendar();

	public Executioner exe = new Executioner();

	private static int hour;
	private static int min;
	private static int sec;
	private static String am_pm;
	String sPathTillMonth;
	String sPathTillDate;
	String sPathTillUserName;
	private static boolean running = false;
	public String[] monthName = { "January", "February", "March", "April",
			"May", "June", "July", "August", "September", "October",
			"November", "December" };

	// For screen Shot

	// public ScriptExecutor scriptExe = new ScriptExecutor();
	private String strReportFile;
	private int iNoOfRows;
	public int iCurrentIndex;
	public int counter;
	String strBrowser;
	String ItrResult="Pass";

	public void setStrBrowser(String strBrowser) {
		this.strBrowser = strBrowser;
		//System.out.println("in setstrBrowser"+strBrowser);
	}

	// private String strDataPath = strAbsolutepath + "/data/";
	// private String strReportFile;

	// public ReportProperties reportProp = new ReportProperties();

	public void start(Calendar calander) {

		Long actualStartTime = System.currentTimeMillis();
		hour = calander.get(Calendar.HOUR);
		min = calander.get(Calendar.MINUTE);
		sec = calander.get(Calendar.SECOND);
		if (calander.get(Calendar.AM_PM) == 0)
			am_pm = "AM";
		else
			am_pm = "PM";

		running = true;
		startTime = actualStartTime;
		strStartTime = "" + hour + ":" + min + ":" + sec + " " + am_pm;
	}

	public String stop() {
		String strStoTime = null;
		Calendar stop = new GregorianCalendar();
		Long actualstopTime = System.currentTimeMillis();
		hour = stop.get(Calendar.HOUR);
		min = stop.get(Calendar.MINUTE);
		sec = stop.get(Calendar.SECOND);
		if (stop.get(Calendar.AM_PM) == 0)
			am_pm = "AM";
		else
			am_pm = "PM";
		// .currentTimeMillis();
		stopTime = actualstopTime;
		strStoTime = "" + hour + ":" + min + ":" + sec + " " + am_pm;

		running = false;
		return strStoTime;
	}

	// Total Execution time in milliseconds
	public float getElapsedTime() {
		float elapsedTime = 0;
		if (running) {
			elapsedTime = (System.currentTimeMillis() - startTime);
			// .currentTimeMillis() - startTime);
		} else {
			elapsedTime = (stopTime - startTime);
		}
		return elapsedTime;
	}

	public String now() {
		String strScreenshotPath = strAbsolutePath + "\\results\\screenshot\\";
		Calendar cal = Calendar.getInstance();
		month = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);
		year = cal.get(Calendar.YEAR);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		Random rand = new Random();
		int num = rand.nextInt(1000);
		strScreenshot = (String) (strScreenshotPath + strDetails
				+ sdf.format(cal.getTime()) + num + ".png");
		return sdf.format(cal.getTime());
		
	}

	public String moduleReportGenerator() throws IOException {
		String strSummarizedReportFile = "";
		try{
		FileWriter aWriter = null;
		// String strBrowser = exe.getExecutionBrowser();
		String strOSName = System.getProperty("os.name");
		// String sTestCaseName = System.getProperty("TestCaseName");
		// String strDetails = System.getProperty("sheetName");
		// String sNoOfRows = System.getProperty("NoOfRows");


		iNoOfRows = counter;// Integer.parseInt(sNoOfRows);

		try {
			Calendar cal = Calendar.getInstance();
			int iMonth = cal.get(Calendar.MONTH);
			String sMonthName = monthName[iMonth];
			String userName = System.getProperty("user.name");
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			String sDate = sdf.format(cal.getTime());
			sPathTillUserName = strAbsolutepath + "/results/" + userName;
			sPathTillMonth = sPathTillUserName + "/" + sMonthName;
			sPathTillDate = sPathTillMonth + "/" + sDate;
			String time = now();
			File oFilePathTillUserName = new File(sPathTillUserName);
			if (!oFilePathTillUserName.exists()) {
				oFilePathTillUserName.mkdir();
			}
			File osPathTillMonth = new File(sPathTillMonth);
			if (!osPathTillMonth.exists()) {
				osPathTillMonth.mkdir();
			}
			File osPathTilldate = new File(sPathTillDate);
			if (!osPathTilldate.exists()) {
				osPathTilldate.mkdir();
			}
			File resultFolder = new File(sPathTillDate + "/" + strDetails);
			if (!resultFolder.exists()) {
				resultFolder.mkdir();
			}
			File cssFile = new File(sPathTillDate + "/" + strDetails + "/pages");
			if (!cssFile.exists()) {
				FileUtils.copyDirectory(new File(strAbsolutepath
						+ "/results/pages"), new File(sPathTillDate + "/"
						+ strDetails + "/pages"));
			}

			strSummarizedReportFile = resultFolder + "/" + strDetails
					+ "_Summarized_Report_" + time + ".html";
			
			
		
			
			try{
			wr= new FileWriter(passFile,true);
			wr2= new FileWriter(failFile,true);
			}catch(Exception e2){ e2.printStackTrace();}
			aWriter = new FileWriter(strSummarizedReportFile, true);

			aWriter.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> ");
			aWriter.write("<html>");
			aWriter.write("<head>");

			aWriter.write("<link type=\"text/css\" href=\"./pages/css/themes/ui-lightness/jquery-ui-1.8.16.custom.css\" rel=\"Stylesheet\" />");
			aWriter.write("<link type=\"text/css\" href=\"./pages/css/myStyle.css\" rel=\"Stylesheet\" />");
			aWriter.write("<script type=\"text/javascript\" src=\"./pages/js/jquery-1.6.2.min.js\"></script>");
			aWriter.write("<script type=\"text/javascript\" src=\"./pages/js/jquery-ui-1.8.16.custom.min.js\"></script>");
			aWriter.write("<script type=\"text/javascript\" src=\"./pages/js/my.js\"></script>");
			aWriter.write("<script>$(document).ready(function(){$(\".toggle\").click(function() {$(\".list_table_tr1\").show();});});</script>");
			aWriter.write("</head>");
			aWriter.write("<script type=\"text/javascript\">");

			aWriter.write("$(document).ready(function(){");
			aWriter.write("$(\"#tabs_environment,#tabs_plan,#tabs_set,#set_content_tabs\").tabs({");
			aWriter.write("selected: 0,");
			aWriter.write("deselectable: true");
			aWriter.write("});");
			aWriter.write("$(\"button\", \".btn\" ).button();");
			aWriter.write("$(\"button\", \".plan_step_list\" ).button();");
			aWriter.write("$(\"#tabs_plan\").hide();");
			aWriter.write("$(\"#tabs_set\").hide();");
			aWriter.write("$(\"#dialog\").dialog({");
			aWriter.write("autoOpen:false,");
			aWriter.write("modal:true,");
			aWriter.write("buttons:{");
			aWriter.write(" Store:function(){");
			aWriter.write("return;");
			aWriter.write("}");
			aWriter.write("},");
			aWriter.write("dialogClass: 'f2',");
			aWriter.write("resizable: true,");
			aWriter.write("show: 'slide',");
			aWriter.write("height:120");
			aWriter.write("});");

			aWriter.write("});");
			aWriter.write("</script>");
			aWriter.write("<body>");
			aWriter.write("<div class=\"page_container\">");
			aWriter.write("<div class=\"head\">");
			aWriter.write("<img alt=\"Capgemini\" src=\"./pages/images/logo160.gif\">");
			aWriter.write("</div>");
			aWriter.write("<div class=\"content\">");
			aWriter.write("<table class=\"content_table\" cellpadding=\"0\" cellspacing=\"0\">");
			aWriter.write("<tr>");
			aWriter.write("<td valign=\"top\">");
			aWriter.write("<div class=\"right_content\">");
			aWriter.write("<div id=\"tabs_environment\">");
			aWriter.write("<ul>");
			aWriter.write("<li><a href=\"#tabs-set-2\" class=\"f2\">"
					+ "Summarized Execution Report </a></li>");
			aWriter.write("</ul>");
			aWriter.write("<div id=\"tabs-set-1\"  class=\"f2\">");
			aWriter.write("<div style=\"margin-top: 10px;\">");
			aWriter.write("<table id=\"set_table\" width=\"100%\" class=\"f2\" cellpadding=\"\" cellspacing=\"10\" ><tr>");
			aWriter.write("<td><b>Execution Date</b></td>");
			/*
			 * aWriter.write("<td><b>Execution Start Time</b></td>");
			 * aWriter.write("<td><b>Execution End Time</b></td>");
			 */
			aWriter.write("<td><b>TestCase Name</b></td>");
			aWriter.write("<td><b>Operating System</b></td>");
			aWriter.write("<td><b>Browser</b></td>");
			aWriter.write("</tr>");
			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td>" + day + "-" + month + "-" + year + "</td>");
			/*
			 * aWriter.write("<td>" + strStartTime + "</td>");
			 * aWriter.write("<td>" + strStopTime + "</td>");
			 * aWriter.write("<td>" + Math.round((timeElapsed / (60000)) * 60) +
			 * " " + "seconds" + "</td>");
			 */
			aWriter.write("<td>" + strDetails + "</td>");
			
			
			
			
			// Edited
			aWriter.write("<li><a href=\"#tabs-set-2\" class=\"f2\">"
                    + "Summarized Execution Report </a></li>");

			aWriter.write("</tr>");
			aWriter.write(" <tr class=\"list_table_tr\">");
			aWriter.write(" <td></td>");
			aWriter.write(" <td></td>");
			aWriter.write(" <td></td>");
			aWriter.write("<td></td>");
			aWriter.write(" <td></td>");
			aWriter.write("<td></td>");
			aWriter.write("</tr>");
			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td><b>Serial Number</b></td>");
			aWriter.write("<td><b>Total Steps</b></td>");
			aWriter.write("<td><b>Pass Steps</b></td>");
			aWriter.write("<td><b>Fail Steps</b></td>");
			aWriter.write("<td><b>Link to detail result</b></td>");
			aWriter.write("</tr>");

			for (int i = 0; i < iNoOfRows; i++) {
				int k = i + 1;
				aWriter.write("<tr class=\"list_table_tr\"><td >" + k + "</td>");
				aWriter.write("<td >" + listTotalStepsDetails.get(i) + "</td>");
				aWriter.write("<td ><font color=\"Green\">"
						+ listPassDetails.get(i) + "</td>");
				if ((listFailDetails.get(i)) == 0) {
					aWriter.write("<td >" + listFailDetails.get(i) + "</td>");
					//wr2.write("<td >" + listFailDetails.get(i) + "</td>");
				} else {
					aWriter.write("<td ><font color=\"Red\">"
							+ listFailDetails.get(i) + "</td>");
				//	wr2.write("<td ><font color=\"Red\">"
						//	+ listFailDetails.get(i) + "</td>");
				}
				aWriter.write("<td >");
				aWriter.write("<a href =\"");
				aWriter.write("file:///" + listReportFileDetails.get(i));
				aWriter.write("\" target=\"_blank\">Detail Result</td>\n");
			}

			/*
			 * listPassDetails.clear(); listFailDetails.clear();
			 * listTotalStepsDetails.clear(); listReportFileDetails.clear();
			 */

			aWriter.write("</table>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</td>");
			aWriter.write("</tr>");
			aWriter.write("</table>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</body>");
			aWriter.write("</html>");
			aWriter.flush();
			aWriter.close();
			
			wr.flush();
			wr.close();
			wr2.flush();
			wr2.close();
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		}catch(Exception e){e.printStackTrace();}
		
		return strSummarizedReportFile;
	}


	
	public void CreateSummary(String newBrowser) throws IOException, BiffException {

		try{
				
		// String strReportFile = System.getProperty("reportFilePath");
		listFailDetails.add(iterFailCount);
		listPassDetails.add(iterPassCount);
		listTotalStepsDetails.add(iterPassCount + iterFailCount);
		// System.out.println(strDetails+"list size is:"+listTotalStepsDetails.size());
		listReportFileDetails.add(strReportFile);
		/*
		 * int iPassCount = iPassCount ; int iFailCount =
		 * Integer.parseInt(listFailDetails.get(0));
		 */
//		if(iterPassCount<=0 || iterFailCount){
//			
//		}
//		
		try{
		int iPercentageOfPassFail = (iterPassCount * 100 / (iterPassCount + iterFailCount));
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		FileWriter aWriter = new FileWriter(strReportFile, true);
		wr=new FileWriter(passFile, true);
		wr2=new FileWriter(failFile, true);
		
		aWriter.write("</table>");
		aWriter.write("<table class=\"content_table\" cellpadding=\"0\" cellspacing=\"0\">");
		aWriter.write("<tr class=\"list_table_tr\">");
		aWriter.write("<td valign=\"top\">");
		aWriter.write("<div class=\"right_content\">");
		aWriter.write("<div id=\"tabs_environment\">");
		aWriter.write("<ul>");
		aWriter.write("<li><a href=\"#tabs-set-2\" class=\"f2\">"
				+ "Report Summary </a></li>");
		aWriter.write("</ul>");
		aWriter.write("<div id=\"tabs-set-1\"  class=\"f2\">");
		aWriter.write("<div style=\"margin-top: 10px;\">");
		aWriter.write("<table id=\"set_table\" width=\"100%\" class=\"f2\" cellpadding=\"\" cellspacing=\"10\" ><tr class=\"list_table_tr\">");
		aWriter.write("<td><b>Data Sheet</b></td>");
		aWriter.write("<td>" + System.getProperty("File") + "</td>");
		aWriter.write("</tr>");
		aWriter.write("<tr class=\"list_table_tr\">");
		aWriter.write("<td><b>End Test Run</b></td>");
		aWriter.write("<td>" + strStopTime + "</td>");
		aWriter.write("</tr>");
		aWriter.write("<tr class=\"list_table_tr\">");
		aWriter.write("<td><b>Elapsed Test Time</b></td>");
		int seconds = (int) (timeElapsed / 1000) % 60;
		int minutes = (int) ((timeElapsed / (1000 * 60)) % 60);
		aWriter.write("<td>" + minutes + " " + "min" + " " + seconds + " "
				+ "seconds" + "</td>");
		aWriter.write("</tr>");
		aWriter.write("<tr class=\"list_table_tr\">");
		aWriter.write("<td><b>Number of Iteration Passed</b></td>");
		aWriter.write("<td> <font color=\"Green\"><a href=\""+passFile+"\">" + iterPassCount + "</a></td>");
		aWriter.write("</tr>");
		aWriter.write("<tr class=\"list_table_tr\">");
		aWriter.write("<td><b>Number of Iteration Failed</b></td>");
		aWriter.write("<td><font color=\"Red\"><a href=\""+failFile+"\">" + iterFailCount + "</td>");
		aWriter.write("</tr>");
		aWriter.write("<tr class=\"list_table_tr\">");
		aWriter.write("<td><b>Percentage of Iteration Passed</b></td>");
		aWriter.write("<td>" + iPercentageOfPassFail + "</td>");
		aWriter.write("</tr>");
		aWriter.write("</table>");
		aWriter.write("</div>");
		aWriter.write("</div>");
		aWriter.write("</div>");
		aWriter.write("</div>");
		aWriter.write("</td>");
		aWriter.write("</tr>");
		aWriter.write("</table>");
		aWriter.write("</div>");
		aWriter.write("</div>");
		aWriter.write("</div>");
		aWriter.write("</div>");
		aWriter.write("</td>");
		aWriter.write("</tr>");
		aWriter.write("</table>");
		aWriter.write("</div>");
		aWriter.write("</div>");
		aWriter.write("</body>");
		aWriter.write("</html>");
		aWriter.flush();
		aWriter.close();
		wr.flush();
		wr.close();
		wr2.flush();
		wr2.close();
	
		// Update Status in Input Data File
		int iCurrentExeNumber = 0;
	
		String strInputfileName = System.getProperty("File");
		File inputWorkbook = new File(strInputfileName);
		Workbook w;
		w = Workbook.getWorkbook(inputWorkbook);
		Sheet sMasterSheet = w.getSheet("MasterSheet");
		POIFSFileSystem fs;
		fs = new POIFSFileSystem(new FileInputStream(strInputfileName));
		HSSFWorkbook workbook = new HSSFWorkbook(fs);

		HSSFSheet dataSheet = null;
		dataSheet = workbook.getSheet("MasterSheet");
		HSSFRow dataRow = null;
		Iterator rows = dataSheet.rowIterator();
		int noOfRows = 0;
		while (rows.hasNext()) {
			HSSFRow row = (HSSFRow) rows.next();
			noOfRows++;
		}

		HSSFFont hssFont = workbook.createFont();
		HSSFCellStyle style = workbook.createCellStyle();
		
		
		
		
		// String sNoOfRows = System.getProperty("NoOfRows");//
		// System.setProperty("NoOfRows")
		iNoOfRows = counter; // Integer.parseInt(sNoOfRows);
	
		//if (iNoOfRows == 1) {
			if (iterFailCount > 0) {
				StrStatus = "Fail";
				hssFont.setColor(HSSFColor.DARK_RED.index);
				style.setFont(hssFont);

			} else {
				StrStatus = "Pass";
				hssFont.setColor(HSSFColor.DARK_GREEN.index);
				style.setFont(hssFont);
				}
			sHyperLink = "HYPERLINK(\"[" + strReportFile + "]Reporlink\" )";
			
			if (browser==null&&newBrowser.startsWith("Cafe#")) {
		 		String[] temp=newBrowser.split("#");
		 	iCurrentExeNumber = getCurrentExecutionRow(temp);
		 	System.out.println("write On Cell"+iCurrentExeNumber);
		 	dataRow = dataSheet.getRow(iCurrentExeNumber);
			dataRow.createCell(4).setCellStyle(style);
			dataRow.createCell(4).setCellType(HSSFCell.CELL_TYPE_STRING);
			dataRow.createCell(4).setCellValue(StrStatus);
			dataRow.createCell(5).setCellFormula(sHyperLink);
		 	}
			System.out.println(StrStatus);
			System.out.println(strDetails);
			System.out.println(sHyperLink);
			FileOutputStream fileOut = new FileOutputStream(strInputfileName);
			workbook.write(fileOut);
			fileOut.close();
			iterFailCount=0;
			iterPassCount=0;
			System.out.println("update done");
		
		
			if(browser==null && newBrowser.startsWith("Cafe#")){
				String[] temp=newBrowser.split("#");
				
			//	browserName=excel.browserName(strDetails);

				sHyperLink=sHyperLink.replace("HYPERLINK(\"[", "");
				sHyperLink=sHyperLink.replace("]Reporlink\" )", "");
				//sHyperLink=sHyperLink.replace("Reporlink", "");
				createOverallReport(strDetails, temp[1],StrStatus, sHyperLink);
				}
			
			else{
					
					sHyperLink=sHyperLink.replace("HYPERLINK(\"[", "");
					sHyperLink=sHyperLink.replace("]Reporlink\" )", "");
					createOverallReport(strDetails, newBrowser, StrStatus,sHyperLink);
				}
		
		
		}catch(Exception e){	e.printStackTrace();}
	}

	public void createOverallReport(String testCaseName,String testCaseBrowser,String strStatus,String testCaseLink) throws IOException
	{
		try{
		String tName=testCaseName;
		String tBrowser=testCaseBrowser;
		String tLink=testCaseLink;
		String tStatus=strStatus;
		FileWriter aWriter = null;
		System.out.println("in testcasebrowser"+testCaseBrowser);
		aWriter= new FileWriter("./results/MasterReport.html",true);
		
		//System.out.println(strReportFile);
		aWriter.write("<tr class=\"list_table_tr\">");
		aWriter.write("<td >" + tName+ "</td>");
		aWriter.write("<td>"+tBrowser+"</td>");
		aWriter.write("<td >" + tStatus+ "</td>");
		aWriter.write("<td >" );

			
			tLink=tLink.replace('[', ' ');
			tLink=tLink.replace(']', ' ');
			tLink=tLink.replace("Reporlink", "");
			aWriter.write("<a href =\"");
			aWriter.write("file:///" +tLink);
			System.out.println(tLink);
			aWriter.write("\" target=\"_blank\">Scenario Links</td>\n");
			
		aWriter.write("</td>");
		aWriter.write("</tr>");
/*		String MasterReport=readFile("./results/MasterReport.html");
		String toWrite = "";
		toWrite="<tr class=\"list_table_tr\">";
		toWrite+="<td >" + tName+ "</td>";
		toWrite+="<td>"+tBrowser+"</td>";
		toWrite+="<td >" + tStatus+ "</td>";
		toWrite+="<td >";

			
			tLink=tLink.replace('[', ' ');
			tLink=tLink.replace(']', ' ');
			tLink=tLink.replace("Reporlink", "");
			toWrite+="<a href =\"";
			toWrite+="file:///" +tLink;
			toWrite+="\" target=\"_blank\">Scenario Links</td>\n";
			
		toWrite+="</td>";
		toWrite+="</tr>";
		int start=0;
		int end=0;
		start=MasterReport.s
		MasterReport=MasterReport.replace("<!--NEXTTRTOCOME-->", toWrite+"<!--NEXTTRTOCOME-->");
		aWriter = new FileWriter("./results/MasterReport.html");
		aWriter.write(MasterReport);*/
		aWriter.flush();
		aWriter.close();

/*		
		File file = new File("MasterReport.txt");
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.write(content1);
		bw.write(content2);
		bw.close();
*/
		
		System.out.println("done");
		}catch(Exception e){	e.printStackTrace();}
	}

	public  void  writeStepResult(String strScenarioName,
			String strStepDescription, String strTestData, String strStatus,
			String strRessultDescription, boolean isScreenshot,
			RemoteWebDriver webDriver) {

		try{
		
		FileWriter aWriter = null;
		String strComponent = null;
		// String strReportFile = System.getProperty("reportFilePath");
		try {

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			
			if (isScreenshot) {
				now();

				try {

//					File srcFile;
//
//					srcFile = ((TakesScreenshot) webDriver)
//							.getScreenshotAs(OutputType.FILE);
//
//					FileUtils.copyFile(srcFile, new File(strScreenshot));

					// tempList_snapshot.add(strScreenshot);

				} catch (Exception e) {
				
				}

			} else {
				// tempList_snapshot.add("No screenshot available");
			}
			// tempList_status.add(strStatus);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			aWriter = new FileWriter(strReportFile, true);
			wr=new FileWriter(passFile, true);
			wr2=new FileWriter(failFile, true);
			/*aWriter.write("<tr class=\"list_table_tr\">");
			*//*aWriter.write("<script type=\"text/javascript\" src=\"./pages/js/jquery-1.6.2.min.js\"></script>");
			aWriter.write("<script type=\"text/javascript\" src=\"./pages/js/jquery-ui-1.8.16.custom.min.js\"></script>");
			aWriter.write("<script type=\"text/javascript\" src=\"./pages/js/my.js\"></script>");
			aWriter.write("<script>");
			aWriter.write("$(document).ready(function(){");
			aWriter.write("$(\".list_table_tr1\").hide();");
			aWriter.write("});");
			aWriter.write("</script>");*/
			aWriter.write("<tr class=\"list_table_tr\"><td >" + strScenarioName
					+ "</td>");
			aWriter.write("<td >" + strStepDescription + "</td>");
			aWriter.write("<td >" + strTestData + "</td>");
			if ((strStatus).equalsIgnoreCase("Pass")) {
				try{
				aWriter.write("<td><font color=\"Green\">" + strStatus
						+ "</td>\n");
				arr.add(true);
				}catch(Exception e){e.printStackTrace();}
				
			} else {
				try{
				aWriter.write("<td><font color=\"Red\">" + strStatus
						+ "</td>\n");
				arr.add(false);
			}catch(Exception e){e.printStackTrace();}
			}
			aWriter.write("<td >" + strRessultDescription + "</td>");
			aWriter.write("<td >");

			if (strScreenshot.contentEquals("No screenshot available")) {
				aWriter.write("No Screenshot available");

			} else {
				aWriter.write("<a href =\"");
				aWriter.write("file:///" + strScreenshot);
				aWriter.write("\" target=\"_blank\">Screenshot</td>\n");

			}

			aWriter.flush();
			aWriter.close();
			wr.flush();
			wr.close();
			wr2.flush();
			wr2.close();
			
			
			if (strStatus.equalsIgnoreCase("Pass")) {
				iPassCount++;
			}

			if (strStatus.equalsIgnoreCase("Fail")) {
				iFailCount++;
			}

			/*
			 * tempList_scenario_name1.clear();
			 * tempList_teststep_description.clear();
			 * tempList_test_data.clear(); tempList_snapshot.clear();
			 * tempList_status.clear(); tempList_result_description.clear();
			 */

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		}catch(Exception e){	e.printStackTrace();}
	}

	public void writeStepResult(String strScenarioName,
			String strStepDescription, String strTestData, String strStatus,
			String strRessultDescription, boolean isScreenshot,
			Selenium selenium) {
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

			// tempList_scenario_name1.add(strScenarioName);
			// tempList_teststep_description.add(strStepDescription);
			// tempList_test_data.add(strTestData);
			// tempList_result_description.add(strRessultDescription);

			if (isScreenshot) {
				now();
				Random rand = new Random();
				int num = rand.nextInt(1000);

				try {
					Robot robot = new Robot();
					BufferedImage screenShot = robot
							.createScreenCapture(new Rectangle(Toolkit
									.getDefaultToolkit().getScreenSize()));
					ImageIO.write(screenShot, "png", new File(strScreenshot));
					/*
					 * selenium.captureScreenshot(strScreenshot);
					 * tempList_snapshot.add(strScreenshot);
					 */

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				// tempList_snapshot.add("No screenshot available");
			}
			// tempList_status.add(strStatus);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public int getCurrentExecutionRow(String[] temp) throws BiffException,
	IOException {
		int sCurrentRowNumber = 0;
try{
String strInputfileName = System.getProperty("File");
//System.out.println(strInputfileName + "    if getcurrent  " );
File inputWorkbook = new File(strInputfileName);
Workbook w;
w = Workbook.getWorkbook(inputWorkbook);
Sheet sMasterSheet = w.getSheet("MasterSheet");

for (int i = 1; i < sMasterSheet.getRows(); i++) {
    String sCurrentExecutionCondition = sMasterSheet.getCell(3, i).getContents();
   
    String sBrowserName = sMasterSheet.getCell(1, i).getContents();
   System.out.println("TestCase:"+sBrowserName);

    
    if(browser==null){
    //strBrowser=excel.browserName(strDetails);
   
    if (sCurrentExecutionCondition.equalsIgnoreCase("Yes")&& sBrowserName.equalsIgnoreCase(strDetails) )
                 
    {
                  
            		
                     sCurrentRowNumber=i;
                     
                     System.out.println("sCurrentRowNumber"+sCurrentRowNumber);
                    break;
            
    }
    } 
  
    }

}catch(Exception e){	e.printStackTrace();}
return sCurrentRowNumber;

}


	

	public void writeTestSumary(String scenarioId,String scenarioName, String scenarioBrowserName,
			String scenarioExecute, String scenarioResult,String scenarioLinks) {
		FileWriter aWriter = null;
		String strComponent = null;
		try{

		// String strReportFile = System.getProperty("reportFilePath");
		try {

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			//System.out.println(scenarioId);
			//System.out.println(scenarioName);
			aWriter = new FileWriter(strReportFile, true);
			wr=new FileWriter(passFile, true);
			wr2=new FileWriter(failFile, true);
			//System.out.println(strReportFile);
		
			aWriter.write("<tr class=\"list_table_tr\">");

			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td >" + scenarioId + "</td>");
			aWriter.write("<td >" + scenarioName + "</td>");
			
			aWriter.write("<td>"+scenarioBrowserName+"</td>");
			aWriter.write("<td >" + scenarioExecute + "</td>");
			aWriter.write("<td >" + scenarioResult+ "</td>");
			aWriter.write("<td >" );
			if (scenarioLinks==null) {
				aWriter.write("No links available");

			} else {
				scenarioLinks=scenarioLinks.replace('[', ' ');
				scenarioLinks=scenarioLinks.replace(']', ' ');
				scenarioLinks=scenarioLinks.replace("Reporlink", "");
				aWriter.write("<a href =\"");
				aWriter.write("file:///" + scenarioLinks.trim());
				System.out.println(scenarioLinks);
				aWriter.write("\" target=\"_blank\">Scenario Links</td>\n");
				

			}
			aWriter.write("</td>");
			aWriter.write("</tr>");

			
			aWriter.flush();
			aWriter.close();
			wr.flush();
			wr.close();
			wr2.flush();
			wr2.close();

			/*if (strStatus.equalsIgnoreCase("Pass")) {
				iPassCount++;
			}

			if (strStatus.equalsIgnoreCase("Fail")) {
				iFailCount++;
			}*/

			/*
			 * tempList_scenario_name1.clear();
			 * tempList_teststep_description.clear();
			 * tempList_test_data.clear(); tempList_snapshot.clear();
			 * tempList_status.clear(); tempList_result_description.clear();
			 */

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void MasterReportGenerator() throws IOException {
		FileWriter aWriter = null;
		String strComponent = null;
		// String strBrowser = exe.getExecutionBrowser();
		String strOSName = System.getProperty("os.name");

		Calendar cal = Calendar.getInstance();
		int iMonth = cal.get(Calendar.MONTH);
		String sMonthName = monthName[iMonth];
		String userName = System.getProperty("user.name");
		//System.out.println(userName);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String sDate = sdf.format(cal.getTime());
		sPathTillUserName = strAbsolutepath + "/results/" + userName;
		sPathTillMonth = sPathTillUserName + "/" + sMonthName;
		sPathTillDate = sPathTillMonth + "/" + sDate+ "_Summary";

		try {
			
			String time = now();
			File oFilePathTillUserName = new File(sPathTillUserName);
			if (!oFilePathTillUserName.exists()) {
				oFilePathTillUserName.mkdir();
			}
			File osPathTillMonth = new File(sPathTillMonth);
			if (!osPathTillMonth.exists()) {
				osPathTillMonth.mkdir();
			}
			File osPathTilldate = new File(sPathTillDate);
			if (!osPathTilldate.exists()) {
				osPathTilldate.mkdir();
			}
		/*	File resultFolder = new File(sPathTillDate + "/" + strDetails);
			if (!resultFolder.exists()) {
				resultFolder.mkdir();
			}
			File cssFile = new File(sPathTillDate + "/" + strDetails + "/pages");
			if (!cssFile.exists()) {
				FileUtils.copyDirectory(new File(strAbsolutepath
						+ "/results/pages"), new File(sPathTillDate + "/"
						+ strDetails + "/pages"));
			}*/

			

			/*strReportFile = resultFolder + "\\" + strDetails + "_Report_" + time
					+ Math.random() + ".html";*/
			strReportFile="./results/MasterReport.html";
			aWriter = new FileWriter(strReportFile, true);
			wr=new FileWriter(passFile, true);
			wr2=new FileWriter(failFile, true);
					//	aWriter.write("<div id=\"tabs-set-1\"  class=\"f2\">");
			/*aWriter.write("<div style=\"margin-top: 10px;\">");
			aWriter.write("<table id=\"set_table\" width=\"100%\" class=\"f2\" cellpadding=\"\" cellspacing=\"10\" ><tr>");
			*/aWriter.write("<td><b>Execution Date</b></td>");
			aWriter.write("<td><b>Execution Start Time</b></td>");
			aWriter.write("<td><b>TestSuite Name</b></td>");
			/*
			 * aWriter.write("<td><b>Execution End Time</b></td>");
			 * aWriter.write("<td><b>Total Execution Time</b></td>");
			 */
			aWriter.write("<td><b>Operating System</b></td>");
		//	aWriter.write("<td><b>Browser</b></td>");
			aWriter.write("</tr>");
			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td>" + day + "-" + month + "-" + year + "</td>");
			aWriter.write("<td>" + strStartTime + "</td>");
			// aWriter.write("<td>" + System.getProperty("TestCaseName") +
			// "</td>");

			aWriter.write("<td>" + strDetails + "</td>");
			/*
			 * aWriter.write("<td>" + strStopTime + "</td>");
			 * aWriter.write("<td>" + Math.round((timeElapsed / (60000)) * 60) +
			 * " " + "seconds" + "</td>");
			 */
			aWriter.write("<td>" + strOSName + "</td>");
		//	aWriter.write("<td>" + strBrowser + "</td>");
			aWriter.write("</tr>");
			aWriter.write(" <tr class=\"list_table_tr\">");
			aWriter.write(" <td></td>");
			aWriter.write(" <td></td>");
			aWriter.write(" <td></td>");
			aWriter.write("<td></td>");
			aWriter.write(" <td></td>");
			aWriter.write("<td></td>");
			aWriter.write("</tr>");
		/*	aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td><b>TestCase Name</b></td>");
			aWriter.write("<td><b>BrowserName</b></td>");
			aWriter.write("<td><b>Result</b></td>");
			aWriter.write("<td><b>URL</b></td>");
			aWriter.write("</tr>");*/

			aWriter.flush();
			aWriter.close();
			wr.flush();
			wr.close();
			wr2.flush();
			wr2.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		}
	

		public void createHierarchy()
		{
			try{
			FileWriter aWriter = null;
			String strComponent = null;
			// String strBrowser = exe.getExecutionBrowser();
			String strOSName = System.getProperty("os.name");

			Calendar cal = Calendar.getInstance();
			int iMonth = cal.get(Calendar.MONTH);
			String sMonthName = monthName[iMonth];
			String userName = System.getProperty("user.name");
			//System.out.println(userName);
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			String sDate = sdf.format(cal.getTime());
			sPathTillUserName = strAbsolutepath + "/results/" + userName;
			sPathTillMonth = sPathTillUserName + "/" + sMonthName;
			sPathTillDate = sPathTillMonth + "/" + sDate+ "_Summary";

			try {
				strComponent = "BMC REMEDY";
				String time = now();
				File oFilePathTillUserName = new File(sPathTillUserName);
				if (!oFilePathTillUserName.exists()) {
					oFilePathTillUserName.mkdir();
				}
				File osPathTillMonth = new File(sPathTillMonth);
				if (!osPathTillMonth.exists()) {
					osPathTillMonth.mkdir();
				}
				File osPathTilldate = new File(sPathTillDate);
				if (!osPathTilldate.exists()) {
					osPathTilldate.mkdir();
				}
				File resultFolder = new File(sPathTillDate + "/" + strDetails);
				if (!resultFolder.exists()) {
					resultFolder.mkdir();
				}
				File cssFile = new File(sPathTillDate + "/" + strDetails + "/pages");
				if (!cssFile.exists()) {
					FileUtils.copyDirectory(new File(strAbsolutepath
							+ "/results/pages"), new File(sPathTillDate + "/"
							+ strDetails + "/pages"));
				}

				// String strReportFile = resultFolder + "/" + strDetails +
				// "_Report_"
				/*
				 * reportProp.setReportFile(resultFolder + "/" + strDetails +
				 * "_Report_" + time + Math.random() + ".html");
				 */
				//System.out.println(resultFolder);
				//System.out.println(strDetails);

				strReportFile = resultFolder + "\\" + strDetails + "_Report_" + time
						+ Math.random() + ".html";
				passFile= resultFolder + "\\" + strDetails + "_Report_min_" + time
						+ Math.random() + ".html";
				failFile=resultFolder + "\\" + strDetails + "_Report_max_" + time
						+ Math.random() + ".html";
				System.out.println(strReportFile);
				/*
				 * System.clearProperty("reportFilePath");
				 * System.setProperty("reportFilePath", strReportFile);
				 */
				// String strReportFileName = strDetails + "_Report_" + time +
				// ".html";

				wr = new FileWriter(passFile, true);
				wr2 = new FileWriter(strReportFile, true);
				
				aWriter = new FileWriter(strReportFile, true);

				aWriter.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> ");
				aWriter.write("<html>");
				aWriter.write("<head>");

				aWriter.write("<link type=\"text/css\" href=\"./pages/css/themes/ui-lightness/jquery-ui-1.8.16.custom.css\" rel=\"Stylesheet\" />");
				aWriter.write("<link type=\"text/css\" href=\"./pages/css/myStyle.css\" rel=\"Stylesheet\" />");
				aWriter.write("<script type=\"text/javascript\" src=\"./pages/js/jquery-1.6.2.min.js\"></script>");
				aWriter.write("<script type=\"text/javascript\" src=\"./pages/js/jquery-ui-1.8.16.custom.min.js\"></script>");
				aWriter.write("<script type=\"text/javascript\" src=\"./pages/js/my.js\"></script>");
				aWriter.write("</head>");
				aWriter.write("<script type=\"text/javascript\">");

				aWriter.write("$(document).ready(function(){");
				aWriter.write("$(\"#tabs_environment,#tabs_plan,#tabs_set,#set_content_tabs\").tabs({");
				aWriter.write("selected: 0,");
				aWriter.write("deselectable: true");
				aWriter.write("});");
				aWriter.write("$(\"button\", \".btn\" ).button();");
				aWriter.write("$(\"button\", \".plan_step_list\" ).button();");
				aWriter.write("$(\"#tabs_plan\").hide();");
				aWriter.write("$(\"#tabs_set\").hide();");
				aWriter.write("$(\"#dialog\").dialog({");
				aWriter.write("autoOpen:false,");
				aWriter.write("modal:true,");
				aWriter.write("buttons:{");
				aWriter.write(" Store:function(){");
				aWriter.write("return;");
				aWriter.write("}");
				aWriter.write("},");
				aWriter.write("dialogClass: 'f2',");
				aWriter.write("resizable: true,");
				aWriter.write("show: 'slide',");
				aWriter.write("height:120");
				aWriter.write("});");

				aWriter.write("});");
				aWriter.write("</script>");
				aWriter.write("<body>");
				aWriter.write("<div class=\"page_container\">");
				aWriter.write("<div class=\"head\">");
				aWriter.write("<img alt=\"Capgemini\" src=\"./pages/images/logo160.gif\">");
				aWriter.write("</div>");
				aWriter.write("<div class=\"content\">");
				aWriter.write("<table class=\"content_table\" cellpadding=\"0\" cellspacing=\"0\">");
				aWriter.write("<tr>");
				aWriter.write("<td valign=\"top\">");
				aWriter.write("<div class=\"right_content\">");
				aWriter.write("<div id=\"tabs_environment\">");
				aWriter.write("<ul>");
				aWriter.write("<li><a href=\"#tabs-set-2\" class=\"f2\">"
						+ "Master Summary Report </a></li>");
				aWriter.write("</ul>");
				aWriter.write("<div id=\"tabs-set-1\"  class=\"f2\">");
				aWriter.write("<div style=\"margin-top: 10px;\">");
				aWriter.write("<table id=\"set_table\" width=\"100%\" class=\"f2\" cellpadding=\"\" cellspacing=\"10\" ><tr>");
				aWriter.write("<td><b>Execution Date</b></td>");
				aWriter.write("<td><b>Execution Start Time</b></td>");
				aWriter.write("<td><b>TestSuite Name</b></td>");
				/*
				 * aWriter.write("<td><b>Execution End Time</b></td>");
				 * aWriter.write("<td><b>Total Execution Time</b></td>");
				 */
				aWriter.write("<td><b>Operating System</b></td>");
			//	aWriter.write("<td><b>Browser</b></td>");
				aWriter.write("</tr>");
				aWriter.write("<tr class=\"list_table_tr\">");
				aWriter.write("<td>" + day + "-" + month + "-" + year + "</td>");
				aWriter.write("<td>" + strStartTime + "</td>");
				// aWriter.write("<td>" + System.getProperty("TestCaseName") +
				// "</td>");

				aWriter.write("<td>" + strDetails + "</td>");
				/*
				 * aWriter.write("<td>" + strStopTime + "</td>");
				 * aWriter.write("<td>" + Math.round((timeElapsed / (60000)) * 60) +
				 * " " + "seconds" + "</td>");
				 */
				aWriter.write("<td>" + strOSName + "</td>");
			//	aWriter.write("<td>" + strBrowser + "</td>");
				aWriter.write("</tr>");
				aWriter.write(" <tr class=\"list_table_tr\">");
				aWriter.write(" <td></td>");
				aWriter.write(" <td></td>");
				aWriter.write(" <td></td>");
				aWriter.write("<td></td>");
				aWriter.write(" <td></td>");
				aWriter.write("<td></td>");
				aWriter.write("</tr>");
				aWriter.write("<tr class=\"list_table_tr\">");
				aWriter.write("<td><b>TestCase ID</b></td>");
				aWriter.write("<td><b>TestCase Name</b></td>");
				aWriter.write("<td><b>TestCase Sheet Name</b></td>");
				aWriter.write("<td><b>BrowserName</b></td>");
				aWriter.write("<td><b>Execute</b></td>");
				aWriter.write("<td><b>Result</b></td>");
				aWriter.write("<td><b>URL</b></td>");
				aWriter.write("</tr>");

				aWriter.flush();
				aWriter.close();
				wr.flush();
				wr.close();
				wr2.flush();
				wr2.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		
		public static void browserParallel(String browserValue) throws InterruptedException {
			 browser=browserValue;
			 Thread.sleep(1000);
			System.out.println(browser);
			
		}
		public void ReportGenerator(String newBrowser) {
			try{
			FileWriter aWriter = null;
			String strComponent = null;
			// String strBrowser = exe.getExecutionBrowser();
			String strOSName = System.getProperty("os.name");

			Calendar cal = Calendar.getInstance();
			int iMonth = cal.get(Calendar.MONTH);
			String sMonthName = monthName[iMonth];
			String userName = System.getProperty("user.name");
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			String sDate = sdf.format(cal.getTime());
			sPathTillUserName = strAbsolutepath + "/results/" + userName;
			sPathTillMonth = sPathTillUserName + "/" + sMonthName;
			sPathTillDate = sPathTillMonth + "/" + sDate;

			try {
				
				strComponent = "BMC REMEDY";
				String time = now();
				File oFilePathTillUserName = new File(sPathTillUserName);
				if (!oFilePathTillUserName.exists()) {
					oFilePathTillUserName.mkdir();
				}
				File osPathTillMonth = new File(sPathTillMonth);
				if (!osPathTillMonth.exists()) {
					osPathTillMonth.mkdir();
				}
				File osPathTilldate = new File(sPathTillDate);
				if (!osPathTilldate.exists()) {
					osPathTilldate.mkdir();
				}
				File resultFolder = new File(sPathTillDate + "/" + strDetails);
				if (!resultFolder.exists()) {
					resultFolder.mkdir();
				}
				File cssFile = new File(sPathTillDate + "/" + strDetails + "/pages");
				if (!cssFile.exists()) {
					FileUtils.copyDirectory(new File(strAbsolutepath
							+ "/results/pages"), new File(sPathTillDate + "/"
							+ strDetails + "/pages"));
				}

				// String strReportFile = resultFolder + "/" + strDetails +
				// "_Report_"
				/*
				 * reportProp.setReportFile(resultFolder + "/" + strDetails +
				 * "_Report_" + time + Math.random() + ".html");
				 */
				//System.out.println(resultFolder);
				//System.out.println(strDetails);
				strReportFile = resultFolder + "/" + strDetails + "_Report_" + time
						+ Math.random() + ".html";
				//System.out.println(strReportFile);
				/*
				 * System.clearProperty("reportFilePath");
				 * System.setProperty("reportFilePath", strReportFile);
				 */
				// String strReportFileName = strDetails + "_Report_" + time +
				// ".html";
				
				aWriter = new FileWriter(strReportFile, true);
				
				wr=new FileWriter(passFile,true);
				wr2=new FileWriter(failFile,true);
				
				
			
				aWriter.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> ");
				
				
				
				aWriter.write("<html>");
				aWriter.write("<head>");

				aWriter.write("<link type=\"text/css\" href=\"./pages/css/themes/ui-lightness/jquery-ui-1.8.16.custom.css\" rel=\"Stylesheet\" />");
				aWriter.write("<link type=\"text/css\" href=\"./pages/css/myStyle.css\" rel=\"Stylesheet\" />");
				
				
				aWriter.write("<script type=\"text/javascript\" src=\"./pages/js/jquery-1.6.2.min.js\"></script>");
				aWriter.write("<script type=\"text/javascript\" src=\"./pages/js/jquery-ui-1.8.16.custom.min.js\"></script>");
				aWriter.write("<script type=\"text/javascript\" src=\"./pages/js/my.js\"></script>");
				
				
				

				wr.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> ");
				
				
				
				wr.write("<html>");
				wr.write("<head>");

				wr.write("<link type=\"text/css\" href=\"./pages/css/themes/ui-lightness/jquery-ui-1.8.16.custom.css\" rel=\"Stylesheet\" />");
				wr.write("<link type=\"text/css\" href=\"./pages/css/myStyle.css\" rel=\"Stylesheet\" />");
				
				
				wr.write("<script type=\"text/javascript\" src=\"./pages/js/jquery-1.6.2.min.js\"></script>");
				wr.write("<script type=\"text/javascript\" src=\"./pages/js/jquery-ui-1.8.16.custom.min.js\"></script>");
				wr.write("<script type=\"text/javascript\" src=\"./pages/js/my.js\"></script>");
				
				wr.write("<script>  $(document).ready(function(){"
						+ " $('#tabs_environment').tabs();"
						+ "});  </script>");
			
				
				wr2.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> ");
				
				
				
				wr2.write("<html>");
				wr2.write("<head>");

				wr2.write("<link type=\"text/css\" href=\"./pages/css/themes/ui-lightness/jquery-ui-1.8.16.custom.css\" rel=\"Stylesheet\" />");
				wr2.write("<link type=\"text/css\" href=\"./pages/css/myStyle.css\" rel=\"Stylesheet\" />");
				
				
				wr2.write("<script type=\"text/javascript\" src=\"./pages/js/jquery-1.6.2.min.js\"></script>");
				wr2.write("<script type=\"text/javascript\" src=\"./pages/js/jquery-ui-1.8.16.custom.min.js\"></script>");
				wr2.write("<script type=\"text/javascript\" src=\"./pages/js/my.js\"></script>");
				
				
				
				
				wr2.write("<script>  $(document).ready(function(){"
						+ " $('#tabs_environment').tabs();"
						+ "});  </script>");
				
				
				
				/*aWriter.write("<script>$(document).ready(function(){$(\".toggle\").click(function() {$(\".list_table_tr1\").show();});});</script>");
				aWriter.write("<script>$(document).ready(function(){$(\".toggle_collapse\").click(function() {$(\".list_table_tr1\").hide();});});</script>");
				*/
				aWriter.write("</head>");
				aWriter.write("<script>");
				

				aWriter.write("$(document).ready(function(){");
				aWriter.write("$(\"#tabs_environment,#tabs_plan,#tabs_set,#set_content_tabs\").tabs({");
				aWriter.write("selected: 0,");
				aWriter.write("deselectable: true");
				aWriter.write("});");
				aWriter.write("$(\"button\", \".btn\" ).button();");
				aWriter.write("$(\"button\", \".plan_step_list\" ).button();");
				aWriter.write("$(\"#tabs_plan\").hide();");
				aWriter.write("$(\"#tabs_set\").hide();");
				aWriter.write("$(\"#dialog\").dialog({");
				aWriter.write("autoOpen:false,");
				aWriter.write("modal:true,");
				aWriter.write("buttons:{");
				aWriter.write(" Store:function(){");
				aWriter.write("return;");
				aWriter.write("}");
				aWriter.write("},");
				aWriter.write("dialogClass: 'f2',");
				aWriter.write("resizable: true,");
				aWriter.write("show: 'slide',");
				aWriter.write("height:120");
				aWriter.write("});");

				aWriter.write("});");
				aWriter.write("</script>");
				aWriter.write("<script>");
				aWriter.write("$(document).ready(function(){$(\"#hrefid\").click(function() {$(\".expand\").toggle();});});");
				aWriter.write("</script>");
				aWriter.write("<script type=\"text/javascript\">");
				aWriter.write("function toggleTable(id) {");
				aWriter.write("var iterationId = document.getElementById(\"tr_\" + id);");
				aWriter.write("var buttonValueId = document.getElementById(\"button_\" + id);");
				aWriter.write("iterationId.style.display == \"block\" ? iterationId.style.display = \"none\" : iterationId.style.display = \"block\";");
				aWriter.write("if(buttonValueId.value==\"Show \")");
				aWriter.write("buttonValueId.value=\"Hide \";");
				aWriter.write("else ");
				aWriter.write("buttonValueId.value=\"Show \";");
				aWriter.write("}");
				aWriter.write("</script>");
		/*		aWriter.write("<script>");
				aWriter.write("$(document).ready(function(){");
				aWriter.write("$(.list_table_tr1).show()");
				aWriter.write("});");
				aWriter.write("</script>");*/
				aWriter.write("<body>");
				aWriter.write("<div class=\"page_container\">");
				aWriter.write("<div class=\"head\">");
				aWriter.write("<img alt=\"Capgemini\" src=\"./pages/images/logo160.gif\">");
				aWriter.write("</div>");
				aWriter.write("<div class=\"content\">");
				
				
				wr.write("</div>");
				wr.write("<div class=\"content\">");
				wr2.write("</div>");
				wr2.write("<div class=\"content\">");
				
				/*aWriter.write("<table class=\"content_table\" cellpadding=\"0\" cellspacing=\"0\">");
				aWriter.write("<tr>");
				aWriter.write("<td valign=\"top\">");
				*/
				
				aWriter.write("<div class=\"right_content\">");
				aWriter.write("<div id=\"tabs_environment\">");
				aWriter.write("<ul>");
				aWriter.write("<li><a href=\"#tabs-set-2\" class=\"f2\">"
						+ "Execution Report </a></li><br></br>");
				aWriter.write("</ul>");
				aWriter.write("<div id=\"tabs-set-1\"  class=\"f2\">");
				aWriter.write("<div style=\"margin-top: 10px;\" id=\"style_div\">");

				
				wr.write("<div class=\"right_content\">");
				wr.write("<div id=\"tabs_environment\">");
				wr.write("<ul>");
				wr.write("<li><a href=\"#tabs-set-2\" class=\"f2\">"
						+ "Execution Report </a></li><br></br>");
				wr.write("</ul>");
				wr.write("<div id=\"tabs-set-1\"  class=\"f2\">");
				wr.write("<div style=\"margin-top: 10px;\" id=\"style_div\">");
				
				
				
				wr2.write("<div class=\"right_content\">");
				wr2.write("<div id=\"tabs_environment\">");
				wr2.write("<ul>");
				wr2.write("<li class=\"ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all ui-state-default ui-corner-top ui-tabs-selected ui-state-active\"><a href=\"#tabs-set-2\" class=\"f2\">"
						+ "Execution Report </a></li><br></br>");
				wr2.write("</ul>");
				wr2.write("<div id=\"tabs-set-1\"  class=\"f2\">");
				wr2.write("<div style=\"margin-top: 10px;\" id=\"style_div\">");
				
				
				
				aWriter.write("<table  style=\"width:100%;\">");
				aWriter.write("<tr >");
				aWriter.write("<td><b>Execution Date   </b></td>");
				aWriter.write("<td><b>Execution Start Time  </b></td>");
				aWriter.write("<td><b>TestCase Name  </b></td>");
				
				
				wr.write("<table  style=\"width:60%;\">");
				wr.write("<tr >");
				
				wr.write("<td><b>TestCase Name  </b></td>");
				wr.write("<td><b>Status  </b></td></table>");
				
				wr2.write("<table  style=\"width:100%;\">");
				wr2.write("<tr >");
				
				wr2.write("<td><b>TestCase Name  </b></td>");
				wr2.write("<td><b>Status  </b></td></table>");
				
				
				
				
				aWriter.write("<td><b>Operating System </b></td>");
				aWriter.write("<td><b>Browser  </b></td>");
				aWriter.write("</tr>");
				
				aWriter.write("<tr class=\"list_table_tr\">");
				aWriter.write("<td><td>");
				aWriter.write("<td></td>");
				aWriter.write("<td></td>");
				aWriter.write("<td></td>");
				aWriter.write("<td></td>");
				aWriter.write("</tr>");
				aWriter.write("<tr class=\"list_table_tr\">");
				
				aWriter.write("<td>"+day + "-" + month + "-" + year +"</td>");
				aWriter.write("<td>"+strStartTime+"</td>");
				aWriter.write("<td>"+strDetails+"</td>");
				aWriter.write("<td>"+strOSName+"</td>");
				//For Serial Run
			/*	String strInputfileName = System.getProperty("File");
				File inputWorkbook = new File(strInputfileName);
				Workbook w;
				String browserName = "";
				w = Workbook.getWorkbook(inputWorkbook);
				Sheet sMasterSheet = w.getSheet("MasterSheet");
				for (int i = 1; i < sMasterSheet.getRows(); i++) {
					// Get execute Condition.
				String sCurrentExecutionCondition = sMasterSheet.getCell(3, i).getContents();
						if (sCurrentExecutionCondition.equals("Yes")) {
							browserName = browserName 
									+ sMasterSheet.getCell(2, i).getContents();

						}
				}
				aWriter.write("<td>"+browserName+"</td>");*/
				
				if(browser==null && newBrowser.startsWith("Cafe#")){
					String[] temp=newBrowser.split("#");
			//	browserName=excel.browserName(strDetails);
				aWriter.write("<td>"+temp[1]+"</td>");
				}else{
					System.out.println("IN report gen browser"+newBrowser);
					aWriter.write("<td>"+newBrowser+"</td>");
				}
				
				
				aWriter.write("</tr>");
				aWriter.write("</table><br>");
				
				wr.write("</tr>");
				wr.write("</table><br>");
				
				wr2.write("</tr>");
				wr2.write("</table><br>");
				
				aWriter.write(" <a href=\"#\" id=\"hrefid\" style=\"color: blue;\" >Click for test-case data</a><br>");
				aWriter.flush();
				aWriter.close();
				wr.flush();
				wr.close();wr2.flush();
				wr2.close();
				
			    }catch (Exception ex) {
				ex.printStackTrace();
			}
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		
		public void addIterator(int i) throws IOException {
			try{
			FileWriter	aWriter = new FileWriter(strReportFile, true);
			wr=new FileWriter(passFile,true);
			wr2=new FileWriter(failFile,true);
			
			//System.out.println("in addIterator ");
			aWriter.write("<div class=\"expand\">");
			aWriter.write("<table  style=\"width:100%;\">");
			aWriter.write("<thead>");
			//aWriter.write("<tr >");
			aWriter.write("<td ><input type=\"button\" id=\"button_"+i+"\" onclick=\"toggleTable("+i+")\" style=\" height:25px; width:auto;text-align: center;\" value=\"Show \">&nbsp;&nbsp;&nbsp; Iteration "+i+" </td>");
		
			wr.write("<table  width=60%> <tr><td>TestCase "+i+"</t></td>");
			wr2.write("<table width=60%> <tr><td>TestCase "+i+"</t></td>");
			
			aWriter.write("</thead>");
			aWriter.write("<tbody id=\"tr_"+i+"\" style=\"display:none;\">");
			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td><b>Test Scenario Name</b></td>");
			aWriter.write("<td><b>Test Step Description</b></td>");
			aWriter.write("<td><b>Test Data</b></td>");
			aWriter.write("<td><b>Status</b></td>");
			aWriter.write("<td><b>Result Description</b></td>");
			aWriter.write("<td><b>ScreenShot</b></td>");
			aWriter.write("</tr>");		
			aWriter.flush();
			aWriter.close();
			wr.flush();
			wr.close();
			wr2.flush();
			wr2.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		public void closeIterator() throws IOException {
			FileWriter	aWriter = new FileWriter(strReportFile, true);
			wr=new FileWriter(passFile, true);
			wr2=new FileWriter(failFile, true);
			
			//System.out.println("in closeiiteratorIterator ");
			listFailDetails.add(iPassCount);
			listPassDetails.add(iFailCount);
			listTotalStepsDetails.add(iPassCount + iFailCount);
			// System.out.println(strDetails+"list size is:"+listTotalStepsDetails.size());
			listReportFileDetails.add(strReportFile);
			/*
			 * int iPassCount = iPassCount ; int iFailCount =
			 * Integer.parseInt(listFailDetails.get(0));
			 * 
			 * 
			 */
			 
				if (iFailCount>0){
	                ItrResult = "Fail";
	          }
				
			 int iPercentageOfPassFail=0;
			try{
				 
			iPercentageOfPassFail = (iPassCount * 100 / (iPassCount + iFailCount));
			 }catch(Exception e){e.printStackTrace();}
			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td valign=\"top\" colspan=\"6\">");
			aWriter.write("<div class=\"right_content\">");
			aWriter.write("<div id=\"tabs_environment\">");
			aWriter.write("<ul>");
			aWriter.write("<li><a href=\"#tabs-set-2\" class=\"f2\">"
					+ "Iteration Summary </a></li>");
			aWriter.write("</ul>");
			aWriter.write("<div id=\"tabs-set-1\"  class=\"f2\">");
			//aWriter.write("<div style=\"margin-top: 10px;\">");
			//aWriter.write("<table id=\"set_table\" width=\"100%\" class=\"f2\" cellpadding=\"\" cellspacing=\"10\" ><tr class=\"list_table_tr\">");
			aWriter.write("<tr class=\"list_table_tr\"><td><b>Data Sheet</b></td>");
			aWriter.write("<td>" + System.getProperty("File") + "</td>");
			aWriter.write("</tr>");
			aWriter.write("</tr>");
			aWriter.write("<tr class=\"list_table_tr\">");
            aWriter.write("<td><b>Number of Steps Passed</b></td>");
            aWriter.write("<td> <font color=\"Green\">" + iPassCount + "</td>");
            aWriter.write("</tr>");
            aWriter.write("<tr class=\"list_table_tr\">");
            aWriter.write("<td><b>Number of Steps Failed</b></td>");
            aWriter.write("<td><font color=\"Red\">" + iFailCount + "</td>");
            aWriter.write("<tr class=\"list_table_tr\">");
            aWriter.write("<td><b>Iteration Result</b></td>");
            aWriter.write("<td><b>" + ItrResult + "</b></td>");
            aWriter.write("</tr>");
            aWriter.write("<tr class=\"list_table_tr\">");
            aWriter.write("<td><b>Pass Fail Percentage</b></td>");
            aWriter.write("<td>" + iPercentageOfPassFail + "</td>");
            aWriter.write("</tr>");
			aWriter.write("</div>");
			aWriter.write("</div>");
		
			aWriter.write("</td>");
			aWriter.write("</tr>");
			aWriter.write("</tbody>");
			aWriter.write("</table>");
			aWriter.write("<hr>");
			aWriter.write("</div>");
		
			if(iFailCount>0)
			{
				iterFailCount++;
			}else{
				iterPassCount++;
			}
			iPassCount=0;
			iFailCount=0;
			
			if(arr.contains(false) || BasicOperation.cont_operation==false)
			{
				wr2.write("<td>  <lable> <font color=red>Fail</font> </lable> </td></tr></table>");
			}
			else{
				wr.write("<td>  <lable> <font color=green>Pass</font> </lable> </td></tr></table>");
			}
			aWriter.flush();
			aWriter.close();
			
			wr.flush();
			wr.close();
			wr2.flush();
			wr2.close();
			
		}
		
		String readFile(String fileName) throws IOException {
		    BufferedReader br = new BufferedReader(new FileReader(fileName));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            sb.append("\n");
		            line = br.readLine();
		        }
		        return sb.toString();
		    } finally {
		        br.close();
		    }
		}
}
		

