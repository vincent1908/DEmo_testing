package com.Base;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TransferXpathtoDatabase
{
	
	
	public static String sheetName="Xyz";
	public static String Database = BasicOperation.DataBase;
	
	
	public static boolean insertDb(String query){
		try{
		Class.forName("org.postgresql.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection(
		   "jdbc:postgresql://"+BasicOperation.serverHost+":10001/"+Database,"postgres", "system");
		
		System.out.println("Connected with DataBase...");
		
		
	
		System.out.println("Query >>>> "+query);
		boolean result = connection.createStatement().execute(query);
		return result;
		}catch(Exception e){ e.printStackTrace();
		
		}
		return false;
	}
	
	
    public static void main(String[] args)
    {
        try
        {
            FileInputStream file = new FileInputStream(new File("C:/a/ObjectIdentifier2.xls"));
 
            //Create Workbook instance holding reference to .xlsx file
            org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
 
            //Get first/desired sheet from the workbook
            
            int no = workbook.getNumberOfSheets();
            int headr = 0;
            int colCount = 0;
            int dataCount = 0;
            
            
            int sheetNo=40;
            
            for(int i=1;i<41;i++){
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(i);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            headr=0;
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                
                String sheetname = workbook.getSheetName(i);
                String query ="";
                //For each row, iterate through all the columns
                if(headr==0){
                query = "create table "+sheetname +" (";
                }
                else{
                	query = "insert into "+sheetname + " Values (";
                }
                Iterator<Cell> cellIterator = row.cellIterator();
                 dataCount=0;
                 List list  = new ArrayList();
                while (cellIterator.hasNext())
                {
                	boolean flag = true;
                    Cell cell = cellIterator.next();
                    
                    String col="";
                    try{		
                    col = cell.getStringCellValue();
                    }catch(Exception e){  col = cell.getNumericCellValue()+""; }
                    
                    Iterator it = list.iterator();
                    
                    while(it.hasNext()){
                    	String tmp = it.next().toString();
                    	if(tmp.equalsIgnoreCase(col))
                    		flag = false;
                    }
                    if(headr==0 && col.length()>1 && flag){
                    	
                    	col = col.replaceAll(" ", "_");
                    	col = col.replaceAll("-", "XXX");
                    	col = col.replaceAll(":", "__");
                    	if(!col.equalsIgnoreCase("territory"))
                    	col = col +" character varying(500),";
                    	else
                    		col = col +" character varying(500) primary key,";
                    	
                    	colCount++;
                    	list.add(col);
                    }
                    else if(headr==1 && col.length()>1){
                    	col = col.replaceAll("'", "@@");
                    	col="'"+col+"',";
                    	dataCount++;
                    }
                    
                    if(col.length()>1 && flag)
                    query = query+col;
                }
                headr=1;
                
                query = query.substring(0, (query.length()-1));
                query = query +" );";
                System.out.println("Query :" + query);
               
                System.out.println("Inserted ??" +insertDb(query));
                
                System.err.println("Col Count >>> "+ colCount +"  DataCount >>>>"+dataCount);
            
            }
            file.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}