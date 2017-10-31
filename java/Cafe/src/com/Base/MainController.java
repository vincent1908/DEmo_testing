package com.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.capgemini.executor.WriteTestData;
import com.capgemini.utilities.Reporter;

public class MainController {
	static int cellNo =1;
	public static Date endTime;
	//	public void readToExcel(){
	//		try{
	//			
	//		
	//		FileInputStream file = new FileInputStream(new File("C:\\test.xls"));
	//		//Get the workbook instance for XLS file
	//		HSSFWorkbook workbook = new HSSFWorkbook(file);
	//		//Get first sheet from the workbook
	//		HSSFSheet sheet = workbook.getSheetAt(0);
	//		//Iterate through each rows from first sheet
	//		Iterator<Row> rowIterator = sheet.iterator();
	//		while(rowIterator.hasNext()) {
	//		//Row row = rowIterator.next();
	//		Row row = rowIterator.next();
	//	}
	//
	//		//For each row, iterate through each columns
	//		Iterator<Cell> cellIterator = rowIterator.
	//		while(cellIterator.hasNext()) {
	//		Cell cell = cellIterator.next();
	//		switch(cell.getCellType()) {
	//		case Cell.CELL_TYPE_BOOLEAN:
	//		System.out.print(cell.getBooleanCellValue() + "\t\t");
	//		break;
	//		case Cell.CELL_TYPE_NUMERIC:
	//		System.out.print(cell.getNumericCellValue() + "\t\t");
	//		break;
	//		case Cell.CELL_TYPE_STRING:
	//		System.out.print(cell.getStringCellValue() + "\t\t");
	//		break;
	//		}
	//		}
	//		System.out.println("");
	//		}
	//		catch(Exception e){
	//		e.printStackTrace();	
	//		}
	//		
	//		
	//		
	//
	//}

	public  double readExcel(String sheetname,int rownumber,int cellnumber) 
	{
		double value=0.0;
		try{
			FileInputStream fis = new FileInputStream("data\\SingleBooking\\SingleBooking_Data.xls");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetname);
			Row row = sh.getRow(rownumber);
			value = row.getCell(cellnumber).getNumericCellValue();


		}
		catch(Exception e)
		{
			e.printStackTrace();


		}
		return value;
	}




	public void writeToTheExcel(String sheetname,int rowno,int colno, long bookingno) throws Exception{
		FileInputStream fis = new FileInputStream("data\\MasterSheet.xls");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row row = sh.getRow(rowno);
		Cell cell = row.createCell(colno);  
		//cell.setCellType(cell.CELL_TYPE_STRING);
		//	cell.setCellValue("pass");

		cell.setCellValue(bookingno);

		//cell.setCellType(cell.CELL_TYPE_STRING);
		//cell.setCellValue(bookingno);

		FileOutputStream fos = new FileOutputStream("data\\MasterSheet.xls");
		wb.write(fos);

		fos.close();



	}



	public String readToExcelForIntegrate(String sheetname,int cell){
		String lastcellno ="";

		try{
			FileInputStream fis = new FileInputStream("data\\MasterSheet.xls");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetname);
			Row row = sh.getRow(0);
			String  str;
			try{
				str = row.getCell(cell).getStringCellValue();
			}catch(Exception e){
				str =  row.getCell(cell).getNumericCellValue()+"";
				str = str.substring(0,str.length()-2);
			}


			lastcellno = str;
		}


		catch(Exception e4){

		}

		System.out.println("Return Booking No:"+lastcellno);
		return lastcellno;


	}


	public void writeToExcelForIntegrate(String sheetname,int rowno,int colno, int bookingno){

		try{
			FileInputStream fis = new FileInputStream("data\\MasterSheet.xls");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetname);
			Row row = sh.getRow(rowno);

			if(bookingno != 1){

				Cell cell = row.createCell(cellNo++);
				cell.setCellValue(bookingno);

				FileOutputStream fos = new FileOutputStream("data\\MasterSheet.xls");
				wb.write(fos);
				fos.close();
			}

		}
		catch(Exception e){

		}



	}




	public void writeDataToExcel(String sheetName, int rowNum, int cellNum, String data) throws IOException, InvalidFormatException{
		try {
			FileInputStream fis = new FileInputStream("D:\\seleniumbsw9\\data.xlsx");
			//get the workbook object
			Workbook wb = WorkbookFactory.create(fis);
			//get the sheet [a particular sheet in the workbook]
			Sheet s = wb.getSheet(sheetName);
			//get the row where data needs to be written. This step 
			//assumes that we are writing to a cell in an existing row
			Row r = s.getRow(rowNum);           
			//Create the cell in that row. If we are trying to write to
			//a cell which has not been created, it will throw error. First
			//we need to create a cell, then set the value of the cell
			//This step is not needed if we are writing to an existing cell
			Cell c = r.createCell(cellNum);         
			c.setCellValue(data);
			FileOutputStream fos=new FileOutputStream("D:\\seleniumbsw9\\data.xlsx");
			wb.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}



















	/**
	 * 
	 * Editing 
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */







	@SuppressWarnings({ "rawtypes", "unused" })
	public Map readExcel(String file) throws Exception
	{
		//FileInputStream fis = new FileInputStream("D:\\kiraan sel\\Cafe2.2\\Cafe 2.2\\data\\AREnquiry\\AREnquiry_Data.xls");

		FileInputStream tempfis = new FileInputStream(file);
		//	System.out.println("Reading File:"+file);

		Workbook wb = WorkbookFactory.create(tempfis);
		Sheet sh = wb.getSheet("Objects");
		int rowno = sh.getLastRowNum();




		Map<String,Map<String, String>> list = new HashMap<String,Map<String, String>>();


		for(int i= 1;i<rowno;i++){
			Map<String,String> entrylist = new LinkedHashMap<>();

			Row row = sh.getRow(i);
			Row entryrow  = sh.getRow(0);

			short c = row.getLastCellNum();
			String str="" ;
			String mapentry="";
			for(int y=0;y<c;y++){
				Cell cell = row.getCell(y);

				try{
					mapentry = entryrow.getCell(y).getStringCellValue();	
					str =  row.getCell(y).getStringCellValue();
				}
				catch(Exception e4){
					try{
						mapentry = entryrow.getCell(y).getNumericCellValue()+"";	
					}catch(Exception e){mapentry = entryrow.getCell(y).getStringCellValue();	
					}

				}
				try{
					str = row.getCell(y).getNumericCellValue()+"";
				}catch(Exception e){
					str =  row.getCell(y).getStringCellValue();}



				//Map<EntrySet, String> map = new HashMap<>();

				entrylist.put(mapentry, str);


			}



			list.put( row.getCell(0).getStringCellValue(), entrylist);

			if(i==rowno-1){
				//	System.out.println("Reading complete");
			}

		}



		list.remove("");
		list.remove(null);

		return list;

	}





	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public Map compareAndwrite(String sheetname1,String sheetname2) throws Exception
	{
		Map<String,Map<String,String>> list1 = readExcel(sheetname1);
		Map<String,Map<String,String>> list2 = readExcel(sheetname2);
		int count=0;
		Map.Entry list2Value;
		Map<String , Map<String,String> > map=  readExcel(sheetname2);
		Map.Entry tempmap2;
		Map<String,String> entr2=null;
		Map<String , String > last =  new LinkedHashMap<>();
		Iterator entry1 =  list1.entrySet().iterator();

		while(entry1.hasNext())
		{

			Map.Entry list1Value = (Map.Entry)entry1.next();
			String UCID = list1Value.getKey().toString();

			Iterator entry2 =  list2.entrySet().iterator();
			while(entry2.hasNext())
			{

				list2Value = (Map.Entry)entry2.next();
				String UCIDOFList2 = list2Value.getKey().toString();



				if(UCID.equalsIgnoreCase(UCIDOFList2)){


					System.out.println("UC Match:"+UCID);
					//				ArrayList<Map<String,String>> list1arraylist = (ArrayList) list1Value.getValue();
					//				
					//				ArrayList<Map<String,String>> list2arraylist = (ArrayList) list2Value.getValue();
					//				
					//				
					Map<String,String> thirdarraylist = new LinkedHashMap<String,String>();

					Map entr = (Map) list1Value.getValue();
					Iterator it = entr.entrySet().iterator();


					while(it.hasNext())
					{




						Map.Entry tempmap = (Map.Entry)it.next();
						String key = tempmap.getKey().toString();
						String value = tempmap.getValue().toString();


						entr2 = (Map<String,String>) list2Value.getValue();
						Iterator it2 = entr2.entrySet().iterator();

						while(it2.hasNext())
						{



							tempmap2 = (Map.Entry)it2.next();
							String key2 = tempmap2.getKey().toString();
							String value2 = tempmap2.getValue().toString();
							System.out
							.println("Key1:" + key +" Key2:"+key2);
							System.out
							.println("Value1: "+value+" Value2:"+value2);

							if(key.equals(key2))
							{

								thirdarraylist.remove(key2);

								thirdarraylist.put(key2,value);
								System.out
								.println("Matched");
							}
							//else{

							//EntrySet set2 = new EntrySet(key2);
							//	
							//	thirdarraylist.put(set2,value2);
							//	}
							//

						}



					}


					Iterator bn = entr2.entrySet().iterator();
					while(bn.hasNext())
					{

						Map.Entry tempMap1 = (Map.Entry) bn.next();

						String key = tempMap1.getKey().toString();
						String value= tempMap1.getValue().toString();
						last.put(key, value);
					}


					Iterator yt = thirdarraylist.entrySet().iterator();
					while(yt.hasNext())
					{
						Map.Entry e = (Map.Entry) yt.next();
						String key = e.getKey().toString();
						String value = e.getValue().toString();

						//	Map.Entry map2 = (Map.Entry)list2Value;

						last.put(key, value);
					}

				}


				System.out.println("Last:"+last);

				map.put(UCIDOFList2,last );
				System.out.println("Last Map:"+map);


			}	


		}
		return map;


	}





	public static void writeToNextUC(String UCNAME, String Title, String Value, int datarow)
	{

		try{
			FileInputStream fis = new FileInputStream("data\\MasterSheet.xls");
			HSSFWorkbook workbook = new HSSFWorkbook(fis);

			Sheet sheet= workbook.getSheet(UCNAME);
			System.out.println("In Write UC");

			Cell cell = null;

			Row row = sheet.getRow(0);

			int last = row.getLastCellNum();
			for(int i=0;i<last;i++)
			{
				String title=null;
				try{
					title =  row.getCell(i).getStringCellValue();
				}
				catch(Exception e){

					title=row.getCell(i).getNumericCellValue()+"";
				}
				if(title.equalsIgnoreCase(Title))
				{
					System.out.println("Matched");
					cell = sheet.getRow(datarow).getCell(i); 
					cell.setCellValue(Value);
					System.out.println("Replace");
				}
			}

			fis.close();

			FileOutputStream output_file =new FileOutputStream(new File("data\\MasterSheet.xls"));  //Open FileOutputStream to write updates

			workbook.write(output_file); //write changes

			output_file.close();







		}catch(Exception e){System.out.println("Error in Writing Next UC"+e); e.printStackTrace();}

	}





	public void writeToTheExcel(String sheetname,int rownumb,int colno, String value ) {
		try{
			FileInputStream fis = new FileInputStream("data\\MasterSheet.xls");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetname);
			Row row = sh.getRow(rownumb);
			Cell cell = row.createCell(colno);  

			cell.setCellValue(value);

			FileOutputStream fos = new FileOutputStream("data\\MasterSheet.xls");
			wb.write(fos);
			fis.close();
			fos.close();
		}catch(Exception e){ e.printStackTrace();}


	}




	public  String readExcelString(String sheetname,int rownumber,int cellnumber) 
	{
		String value="";
		try{
			FileInputStream fis = new FileInputStream("data\\MasterSheet.xls");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetname);
			Row row = sh.getRow(rownumber);
			try{
				value = row.getCell(cellnumber).getStringCellValue();
			}catch(Exception e){  try{ value = row.getCell(cellnumber).getNumericCellValue()+""; }catch(Exception e2){value="pass"; } }
			System.out.println("Value:"+value);

		}
		catch(Exception e)
		{
			System.out.println("?Value:"+value);
			e.printStackTrace();


		}
		return value;
	}


	public void final_result(String sheetname,Reporter reporter){


		try{



			FileInputStream fis = new FileInputStream("data\\MasterSheet.xls");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetname);
			Row row = sh.getRow(1);
			int totalrows = sh.getLastRowNum();
			int lastcellNo = row.getLastCellNum(); 

			ArrayList<String> arr  = new ArrayList<String>();
			for(int i=1;i<=totalrows;i++){
				String result1 = readExcelString(sheetname, i, lastcellNo-2);
				//String result2 = main1.readExcel("Circuit", 2, 26);

				arr.add(result1);
			}

			String a[]=null;
			int current_row= reporter.getCurrentExecutionRow( a);
			System.out.println("final:"+current_row);
			if( arr.contains("Fail")){

				writeToTheExcel("MasterSheet", current_row, 4, "Fail");

			}
			else{
				writeToTheExcel("MasterSheet", current_row, 4, "Pass");

			}



		}catch(Exception e){ e.printStackTrace();}

	}



	@SuppressWarnings("rawtypes")
	public void resultVerification(String sheetname,String message , int current_Itr){
		int lastcellNo=0;
		try{

			System.out.println("The captured value is :"+message);
			List<String> expectedvalue =new ArrayList<>();

			expectedvalue.add("success");
			expectedvalue.add("activat");
			expectedvalue.add("created");

			FileInputStream fis = new FileInputStream("data\\MasterSheet.xls");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetname);
			Row row = sh.getRow(0);
			fis.close();
			lastcellNo = row.getLastCellNum(); 

			boolean checvkFalg=false;
			Iterator it= expectedvalue.iterator();
			while(it.hasNext())
			{
				String str = it.next().toString();
				if(message.contains(str)){
					checvkFalg=true;
					break;
				}
			}

			if (checvkFalg){
				//	main1.writeToExcelForIntegrate(sheetname, rowno, colno, bookingno);, rowno, colno, bookingno);("Circuit", 1, 10, "Pass");

				try{
					writeToTheExcel(sheetname, current_Itr, lastcellNo-2,"Pass");
				}catch(Exception w){ w.printStackTrace();}
			}
			else{try{
				writeToTheExcel(sheetname,current_Itr, lastcellNo-2,"Fail");
			}catch(Exception b){b.printStackTrace();}
			}









		}

		catch(Exception e){

		}
	}	     







	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public void readToNext(String file,String file2) throws Exception{
		//FileInputStream fis = new FileInputStream(file);
		Map<String, Map<String, String>> rownum = compareAndwrite(file, file2);




		//Workbook wb = WorkbookFactory.create(fis);
		FileInputStream fis = new FileInputStream(file);

		//	 FileOutputStream fos = new FileOutputStream(file2);
		//	 FileInputStream fis2 = new FileInputStream(file2);
		//     Workbook wb2  = WorkbookFactory.create(fis2);
		//     Sheet sh2 = wb2.getSheet("Objects");


		System.out.println("Base Map size"+rownum.size());
		Iterator mainiterator = rownum.entrySet().iterator();
		int currentRow=0;
		int Mapcount=0;
		FileInputStream fsIP= new FileInputStream(new File(file2)); 
		HSSFWorkbook wb3 = new HSSFWorkbook(fsIP);
		HSSFSheet worksheet = wb3.getSheetAt(6);

		while(mainiterator.hasNext())
		{


			try{
				Map.Entry entry = (Map.Entry) mainiterator.next();

				System.out.println("Entry:"+entry);

				Map<String,String>  commonMap = (Map<String,String>) entry.getValue();
				//Iterator innerIt = entry.entrySet().iterator();
				Iterator innerIt = commonMap.entrySet().iterator();
				System.out.println("Inner Size:"+commonMap.size());
				int col = 0;
				int y=0;
				Set set = commonMap.keySet();

				Iterator lastIt1 = set.iterator();

				while(lastIt1.hasNext() ){

					String str= lastIt1.next().toString();

					System.out.println(str );
					Cell cell = null; // declare a Cell object

					cell = worksheet.getRow(0).getCell(col);
					cell.setCellValue(str); 
					//System.out.println("Inner:"+innerIt);

					col++;
				}
				col=0;
				Iterator lastIt = set.iterator();
				while(lastIt.hasNext() ){

					String str= lastIt.next().toString();

					System.out.println(str );
					Cell cell = null; // declare a Cell object

					cell = worksheet.getRow(currentRow).getCell(col);
					cell.setCellValue(commonMap.get(str)); 
					//System.out.println("Inner:"+innerIt);

					col++;
				}




				currentRow++;  


			}catch(Exception e){System.out.println("Exception in OuterMap:"+e); 	e.printStackTrace();}


			FileOutputStream output_file =new FileOutputStream(new File(file2));  //Open FileOutputStream to write updates
			//       
			wb3.write(output_file); //write changes
			//       
			output_file.close(); 


		}


		try{

			//   	 FileInputStream fsIP= new FileInputStream(new File(file2)); 
			//   	 HSSFWorkbook wb3 = new HSSFWorkbook(fsIP);
			//   	 HSSFSheet worksheet = wb3.getSheetAt(6);
			//
			//   	 Cell cell = null; // declare a Cell object
			//        
			//        cell = worksheet.getRow(row2).getCell(1);
			//
			//        
			//        cell.setCellValue(writeValue); 
			//        
			//	 
			//        fsIP.close(); //Close the InputStream
			//        
			//        FileOutputStream output_file =new FileOutputStream(new File(file2));  //Open FileOutputStream to write updates
			//          
			//        wb.write(output_file); //write changes
			//          
			//        output_file.close(); 
			//



			int rowcount=0;
			//	 while(itr.hasNext())
			{
				//	 Map.Entry entry = (Map.Entry)  itr.next();
				//	 System.out.println("Key:"+entry.getKey().toString()+" val:"+entry.getValue().toString());



				//  	 Cell cell = null; // declare a Cell object

				//    cell = worksheet.getRow(rowcount).getCell(0);
				//
				//        
				//      cell.setCellValue(entry.getKey().toString()); 
				//        
				//     cell = worksheet.getRow(rowcount++).getCell(1);
				//
				//                
				//            cell.setCellValue(entry.getValue().toString()); 
				//	 
				//   fsIP.close(); //Close the InputStream
				//        
				//   FileOutputStream output_file =new FileOutputStream(new File(file2));  //Open FileOutputStream to write updates
				//          
				//     wb.write(output_file); //write changes
				//          
				//    output_file.close(); 
				//


			}


		}catch(Exception fileerror){ System.out.println("Exception in File Operation:"+fileerror);}


		fis.close();



		//FileInputStream fis = new FileInputStream(file);


	}
	//public void readFromExcelManyRows(){
	//	FileInputStream fis = new FileInputStream("");
	//	Workbook wb = WorkbookFactory.create(fis);
	//	Sheet sh = wb.getSheet("");
	//	int rowcount = sh.getLastRowNum();
	//	Row row = sh.getRow(1);
	//	
	//	for(int i=0;i<rowcount;i++){
	//		int cellcount = row.getLastCellNum();
	//		for(int j=0;i<cellcount;i++){
	//			ArrayList<String> arr=new ArrayList<String>();
	//			arr.add(j+"");
	//			Set<String> set;
	//		//	ArrayList< String> arr2 = new ArrayL
	//			
	//		}

	//         public void enterFile(){
	//	     
	//        	 FileInputStream fsIP= new FileInputStream(new File(file2)); 
	//     	 HSSFWorkbook wb3 = new HSSFWorkbook(fsIP);
	//     	 HSSFSheet worksheet = wb3.getSheetAt(6);
	//   
	//	 Cell cell = null; // declare a Cell object
	//           
	//            cell = worksheet.getRow(row2).getCell(1);
	//    
	//            
	//          cell.setCellValue(writeValue); 
	//           
	//   	            fsIP.close(); //Close the InputStream
	//           
	//          FileOutputStream output_file =new FileOutputStream(new File(file2));  //Open FileOutputStream to write updates
	//              
	//           wb.write(output_file); //write changes
	//             
	//           output_file.close(); 
	//    
	//	     
	public void printMessage(String sheetname,int rownumb, String value ) {
		try{
			
			
			endTime = new Date();
			String UC = BasicOperation.AppName;
			int executed  = (ExecutionHistory.readData().containsKey(UC)) ? Integer.parseInt(ExecutionHistory.readData().get(UC).toString()) : 0;
			ExecutionHistory.writeData2(UC+":"+executed, MainController.endTime);
			
			
			FileInputStream fis = new FileInputStream("data\\MasterSheet.xls");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetname);
			Row row = sh.getRow(rownumb);

		
			
		

			Row row2= sh.getRow(0);
			int lastcellno = row2.getLastCellNum();
			Cell cell = row.createCell(lastcellno-1);  

			cell.setCellValue(value);

			FileOutputStream fos = new FileOutputStream("data\\MasterSheet.xls");
			wb.write(fos);
			fis.close();
			fos.close();
		}catch(Exception e){ e.printStackTrace();}


	}



}	













