package com.Base;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.capgemini.driver.ScriptExecutor;
import com.capgemini.executor.Executioner;
import com.capgemini.utilities.Reporter;
import com.capgemini.utilities.Utilities;

public class RemoteClient {
	public static boolean testNG=false;
	Utilities utils = new Utilities();
	public static String strExecutionStartTime = null;
	public static String strExecutionStopTime = null;
	public static float totalExecutionTime = 0;
	public static String StrCBTReportFile = null;
	public static int totalScannedPages = 0;
	public static String strExecutionBrowser = null;
	private static String browser;
	Thread executionThread =null;
	public RemoteClient(final String args[]) {
		try{
		ServerSocket sock = new ServerSocket(5000);
		
		while(true){
			System.out.println("waiting for client ...");
			Socket localSocket = sock.accept();
			BufferedReader br = new BufferedReader(new InputStreamReader(localSocket.getInputStream()));
			PrintWriter pw = new PrintWriter (new OutputStreamWriter(localSocket.getOutputStream()));
			String command = br.readLine();
			try{
			if(command.equalsIgnoreCase("execute")){
				try{
				 executionThread = new Thread(){	
				   public void run(){
					execute(args);
				   }
				};
				
				executionThread.start();
				}catch(Exception e){}
			}
			else if(command.equalsIgnoreCase("stop")){
				executionThread.destroy();
			}
			else if(command.equalsIgnoreCase("pause")){
				executionThread.stop();
			}
			else if (command.equalsIgnoreCase("restart")) {
				executionThread.resume();
			}
			pw.write("Operation ok!"+"\r\n");
			pw.flush();
			}catch(Exception e){ pw.write(e.getMessage()+"\r\n"); pw.flush(); }
			localSocket.close();
		}
		
		}catch(Exception e){}
	}
	
	private void execute(String args[]){
		try{
			RemoteWebDriver driver = null;
			ScriptExecutor executor = new ScriptExecutor();
			Executioner exe = new Executioner();
			String strBrowserType = null;
			float timeElapsed = 0;
			Properties properties = new Properties();
			try {
				properties.load(new FileInputStream("./data/config.properties"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String path = properties.getProperty("file_path");
			String objpath = properties.getProperty("objfile_path");
			//System.out.println(path);
			// System.out.println(path);
			System.setProperty("File", path);
			System.setProperty("ObjFile", objpath);

			if (args.length > 2 && !args[1].isEmpty()) {
				strBrowserType = args[1];
				browser=strBrowserType;
				Reporter.browser =browser;
			} else {

				//strBrowserType = "Chrome";//exe.getBrowserType();
			}
			//System.out.println("Browser in executioner"+args[1]);
			//reporter.setBrowser(args[1]);
			/* executioner.setExecutionBrowser(strBrowserType); */
			strExecutionBrowser = strBrowserType;

			// executioner.setExecutionBrowser(strBrowserType);

			if (strBrowserType == null) {
				strBrowserType = "Internet Explorer";
			}
			/* executioner.selectBrowser(strBrowserType); */

			// cbtReport.start(cbtReport.calendar);
			// executor.executeScript();
			if (args.length > 2 && !args[2].isEmpty()) {
	//Parallel
				
				// driver = new CreateDriver().getWebDriver(args[0],
				// args[1]);
				try {
					executor.executeScriptMultiple(driver, args[0], args[1],args[2]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
	//Single
				try {
					executor.executeScriptMultiple();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// code to kill ChromeDriver & IEDriver
			/*
			 * if (strBrowserType.equalsIgnoreCase("Chrome")){
			 * //Runtime.getRuntime().exec("cmd.exe /c " +
			 * strBatFileLocation);
			 * Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe"
			 * );
			 * 
			 * } if (strBrowserType.equalsIgnoreCase("Internet Explorer")){
			 * Runtime
			 * .getRuntime().exec("taskkill /F /IM IEDriverServer.exe"); }
			 */
			if(testNG==false){
				
				JOptionPane.showMessageDialog(null, "Execution Completed",
					"WELCOME TO SELENIUM CAFE", 1);
				
			
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RemoteClient(args);
	}

}
