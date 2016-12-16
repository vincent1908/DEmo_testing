package com.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bouncycastle.util.Store;

public class ExecutionHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5716867966118288370L;

	public static  ExecutionHistory his= new ExecutionHistory(); 
	public static int Pass = 0;
	public static int Fail = 0;
	public static   Map readData()
	{
		Map ins = null;
		Date date = new Date();	
		try
		{
			FileInputStream inp = new FileInputStream("results/"+date.getDate()+"_"+date.getMonth()+"_"+date.getYear()+".obj");
			ObjectInputStream io = new ObjectInputStream(inp);
			ins = (Map) io.readObject();
			io.close();
			inp.close();
		}catch(Exception e)
		{  
		}
		System.out.println((ins==null));
		return  (ins==null) ? new HashMap() : ins;
	}

	public static  void writeData(Object Key, Object value)
	{
		try{
			Date date = new Date();	
			Map data = readData(); 
			System.out.println("read Data :" + (data));
			data.put(Key, value);
			File file=	new File("results/"+date.getDate()+"_"+date.getMonth()+"_"+date.getYear()+".obj");
			FileOutputStream out = new FileOutputStream(file);
			ObjectOutputStream ios = new ObjectOutputStream(out);
			ios.writeObject(data);
			System.out.println("write Data :" + (data));
			//ios.flush();
			ios.close();
			out.close();

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public static void addToHistory(String UC, boolean result,Map data)
	{
		
		if(result){
			Fail++;
		}
		else{
			Pass++;
		}
		int executed  = (readData().containsKey(UC)) ? Integer.parseInt(readData().get(UC).toString()) : 0;
		writeData(UC, ++executed);
		writeData(UC+":"+executed, result);
		writeData0(UC+":"+executed, new Date());
		writeData1(UC+":"+executed, data);
		
	}

	public static   Map readData1()
	{
		Date date =new Date();
		return  readFile("DataMap",date);
	}

	private static Map readFile(String fileType , Date date)
	{
		Map ins = null;
		FileInputStream inp=null;
		try{
			if(fileType.equalsIgnoreCase("DataMap"))
				inp = new FileInputStream("results/"+date.getDate()+"_"+date.getMonth()+"_"+date.getYear()+"_DataMap.obj");

			ObjectInputStream io = new ObjectInputStream(inp);
			ins = (Map) io.readObject();
			io.close();
			inp.close();
		}catch(Exception e)
		{}
		return  (ins==null) ? new HashMap() : ins;
	}

	public static  void writeData1(Object Key, Map value)
	{
		Date date = new Date();	
		Map data = readData1(); 
		System.out.println("read Data :" + (data));
		data.put(Key, value);
		try{
			File file=	new File("results/"+date.getDate()+"_"+date.getMonth()+"_"+date.getYear()+"_DataMap.obj");
			FileOutputStream out = new FileOutputStream(file);
			ObjectOutputStream ios = new ObjectOutputStream(out);
			ios.writeObject(data);
			System.out.println("write Data :" + (data));
			//ios.flush();
			ios.close();
			out.close();
		}
		catch(Exception e)
		{}
	}
	
	
	public static  void writeData2(Object Key, Object value)
	{
		try
		{
			Date date = new Date();	
			Map data = readData2(); 
			System.out.println("read Data :" + (data));
			data.put(Key, value);
			File file=	new File("results/"+date.getDate()+"_"+date.getMonth()+"_"+date.getYear()+"_timeStamp2.obj");
			FileOutputStream out = new FileOutputStream(file);
			ObjectOutputStream ios = new ObjectOutputStream(out);
			ios.writeObject(data);
			System.out.println("write Data :" + (data));
			//ios.flush();
			ios.close();
			out.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	
	public static   Map readData2()
	{
		Map ins = null;
		Date date = new Date();	
		try{
			FileInputStream inp = new FileInputStream("results/"+date.getDate()+"_"+date.getMonth()+"_"+date.getYear()+"_timeStamp2.obj");
			ObjectInputStream io = new ObjectInputStream(inp);
			ins = (Map) io.readObject();
			io.close();
			inp.close();
		}catch(Exception e)
		{  
		}
		System.out.println((ins==null));
		return  (ins==null) ? new HashMap() : ins;
	}
	
	
	public static  void writeData0(Object Key, Object value)
	{
		try
		{
			Date date = new Date();	
			Map data = readData0(); 
			System.out.println("read Data :" + (data));
			data.put(Key, value);
			File file=	new File("results/"+date.getDate()+"_"+date.getMonth()+"_"+date.getYear()+"_timeStamp.obj");
			FileOutputStream out = new FileOutputStream(file);
			ObjectOutputStream ios = new ObjectOutputStream(out);
			ios.writeObject(data);
			System.out.println("write Data :" + (data));
			//ios.flush();
			ios.close();
			out.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static   Map readData0()
	{
		Map ins = null;
		Date date = new Date();	
		try{
			FileInputStream inp = new FileInputStream("results/"+date.getDate()+"_"+date.getMonth()+"_"+date.getYear()+"_timeStamp.obj");
			ObjectInputStream io = new ObjectInputStream(inp);
			ins = (Map) io.readObject();
			io.close();
			inp.close();
		}catch(Exception e)
		{  
		}
		System.out.println((ins==null));
		return  (ins==null) ? new HashMap() : ins;
	}

	public static void main(String a[])
	{
		System.out.println("Map:"+ readData());
	}
}




