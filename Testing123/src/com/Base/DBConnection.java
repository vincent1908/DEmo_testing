package com.Base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * Please Do not Modify any code 
 * 
 * @author PRAVIRAN
 *
 */

interface DataBaseConnection{
	
	String MigrationDev="jdbc:Oracle:thin:@10.114.148.172:1521:COSPSW";
	String MigrationTest="jdbc:Oracle:thin:@10.114.148.172:1521:CRPTEST";
	String NBCU = "jdbc:Oracle:thin:@10.114.148.173:1521:COSMOS";
	String SonyCerti="jdbc:Oracle:thin:@10.114.148.173:1521:COSMOS";
	String weinstein = "jdbc:Oracle:thin:@10.114.148.173:1521:COSMOS";
}



public class DBConnection {

	public static String Database_url="";
	
	public static String UserName="DBO_IFDS";
	public static String Password="ifds_dbo";
	
	private static List<List<String>> dataList = new ArrayList<List<String>>();
	
	public static void setDatabase(String serverHost)
	{
		if(serverHost.contains("138:8080")){
			DBConnection.Database_url= DataBaseConnection.MigrationDev;
			System.err.println("[Database] Connection to MigrationDev :"+DataBaseConnection.MigrationDev);
		}
		else if(serverHost.contains("138:8180")){
			DBConnection.Database_url=DataBaseConnection.MigrationTest;
			System.err.println("[Database] Connection to MigrationTest :"+DataBaseConnection.MigrationTest);
		}
		else if(serverHost.contains("166:8180")){
			DBConnection.Database_url=DataBaseConnection.NBCU;
			System.err.println("[Database] Connection to NBCU :"+DataBaseConnection.NBCU);
		}
		else if(serverHost.contains("165:8180")){
			DBConnection.Database_url=DataBaseConnection.SonyCerti;
			System.err.println("[Database] Connection to SonyCerti :"+DataBaseConnection.SonyCerti);
		}
		else if(serverHost.contains("167:8180")){
			DBConnection.Database_url=DataBaseConnection.weinstein;
			System.err.println("[Database] Connection to weinstein :"+DataBaseConnection.weinstein);
		}
		else{
			DBConnection.Database_url=serverHost;
			System.err.println("[Database] Connection to User Database :"+serverHost);
		}
	}
	
	
	public static void queryResult()
	{	int i=0;
		Iterator it=dataList.iterator();
		while(it.hasNext()){
			String str=it.next().toString()+"";
			
			
			if(i==0){
				System.out.println("[Database: > Column]"+str);
				for(int k=0;k<str.length();k++)
				System.out.print("_");	
			System.out.println();
			i++;
			}
			else
				System.out.println("[Database: > Data]"+str);
		}
	}
	
	public static List<List<String>> executeQuery(String Query){
		
		
		dataList.removeAll(dataList);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(Database_url,UserName,Password);
			Statement stmnt = con.createStatement();
			System.err.println("[Database: Query]"+Query);
			ResultSet set=stmnt.executeQuery(Query);
			ResultSetMetaData metadata = set.getMetaData();
			int count = metadata.getColumnCount();
			System.out.println("[Database] Number of Columns:"+count);
			
			String data ="";
			List<String> colmns = new ArrayList<>();
			for(int i=1;i<count;i++){
				data="";
			data= metadata.getColumnName(i);
			colmns.add(data);
			}
			
			dataList.add(colmns);
			while(set.next())
			{
				List<String> list = new ArrayList<String>();
				
				for(int j=1;j<count;j++){
				list.add(set.getString(j));
				
				}
				
				dataList.add(list);
				//System.out.println();
			}
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataList;
	}
	
	
	
	public static void refreshMaterializedView(){
	/*	try{
		String Query = "DECLARE \r\n"
+"v_failures NUMBER(12) := 0; \r\n"
+"BEGIN \r\n"
+"DBMS_MVIEW.REFRESH_ALL_MVIEWS(number_of_failures => v_failures, \r\n"
                              +"method => 'C', \r\n"
                              +"rollback_seg => '', \r\n" 
                              +"refresh_after_errors => TRUE, \r\n"
                              +"atomic_refresh => TRUE); \r\n"
         
+"DBMS_OUTPUT.PUT_LINE('v_failures => ' || v_failures); \r\n  End;" ;
		
		Connection con = DriverManager.getConnection(Database_url,UserName,Password);
		Statement stmnt = con.createStatement();
		System.err.println("[Database: Query]"+Query);
		
		int set=stmnt.executeUpdate(Query);
		
		if(set==1){
		System.err.println("[Database] Materialized View refresh successfully!!");
		}
		else{
			System.err.println("[Database] Refreshing Materialized View Failed!!");
		}
		con.close();
		
		}catch(Exception e){ e.printStackTrace();}
		*/
	}
	
	
	public static void main(String args[])
	{
		DBConnection.setDatabase("http://10.114.148.138:8180/SWAppWeb/dummylogin.jsf");
		int size=DBConnection.executeQuery( "Select * from stg_vpf_theatre where STG_VPF_THEATRE_ID between 1734 and 1750").size();
		DBConnection.queryResult();
		DBConnection.refreshMaterializedView();
		
		if(size<1){
			System.out.println("");
		}
	}
}
