package com.Base;

import com.capgemini.utilities.Reporter;

/**
 * Complex element is used to locate element and perform operation
 * on it which is not possible using selenium.
 * @author PRAVIRAN
 *@since 23 July 15
 * @param <K>
 */
public class ComplexElement<K> extends BasicOperation<K>{
	public ComplexElement(Reporter reporter) {
		super(reporter);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Complex id structure decoding.
	 * Please Do not modify any code.
	 * @author PRAVIRAN
	 * @param XpathOrid
	 * @return
	 */
	   private static String idCreator(String XpathOrid) {
		String id = "";
		if (XpathOrid.contains("@id")) {

			String idSplitter[] = XpathOrid.split("=");
			if (!idSplitter[1].contains("/")) {
				String tempid = idSplitter[1].substring(1, idSplitter[1].length()-2);

				if (tempid.contains(":")) {
					tempid = tempid.replace(":", "\\"+"\\:");
				}
				id = "$('#"+tempid+"')";
			}
			else{
				String tempid = idSplitter[1];
				String idSpiltterPath[] = tempid.split("/");
				idSpiltterPath[0] = idSpiltterPath[0].replace("']", "");
				idSpiltterPath[0] = idSpiltterPath[0].replace("'", "");
				id="$('#"+idSpiltterPath[0]+"')";
				for(int i=1;i<idSpiltterPath.length;i++){
					if(!idSpiltterPath[i].contains("[")){
						id+=".find('"+idSpiltterPath[i]+"')";
						}
						else{
							System.out.println(">>>>"+idSpiltterPath[i]);
							String childSplitter[] = idSpiltterPath[i].split("\\[");
							int childId = Integer.parseInt(childSplitter[1].substring(0, childSplitter[1].length()-1));
							id+=".find('"+childSplitter[0]+":eq("+(childId-1)+")')";
						}
				}
			}
		} else if (XpathOrid.contains("/")) {
			String idSpiltterPath[] = XpathOrid.split("/");
			id="$('"+idSpiltterPath[0]+"')";
			for(int i=1;i<idSpiltterPath.length;i++){
				if(!idSpiltterPath[i].contains("[")){
				id+=".find('"+idSpiltterPath[i]+"')";
				}
				else{
					System.out.println(">>>>"+idSpiltterPath[i]);
					String childSplitter[] = idSpiltterPath[i].split("\\[");
					int childId = Integer.parseInt(childSplitter[1].substring(0, childSplitter[1].length()-1));
					id+=".find('"+childSplitter[0]+":eq("+(childId-1)+")')";
				}
			}
		}

		String object = BaseDrivers.getWebDriver().executeScript(id).toString();
		if(object==""){
			
			
			idCreator(XpathOrid);
			
			
		}
		
		return id;
	}

	public static <K> void  fill(String XpathOrid, K value){
	//	addJQUERY();
		String id=idCreator(XpathOrid);
		String script = id+".val('"+value+"')";
		System.out.println("[ComplexElement INFO] >>>>"+script);
		executeScript(script);
	}
	
	public static <K> void  click(String XpathOrid){
	//	addJQUERY();
		String id=idCreator(XpathOrid);
		String script = id+".trigger('click')";
		System.out.println("[ComplexElement INFO] >>>>"+script);
		executeScript(script);
	}
	/**
	 * Provide path upto ul tag it will auto select li tag
	 * and select element by provided value
	 * @author PRAVIRAN
	 * @param XpathOrid
	 * @param value
	 */
	public static <K> void  selectBy(String XpathOrid, K value){
	//	addJQUERY();
		String id = idCreator(XpathOrid);
		String script = id+".find(\"li:contains('"+value+"')\").trigger('click')";
		System.out.println("[ComplexElement INFO] >>>>"+script);
		executeScript(script);
	}
	
	
	public static void main(String a[]){
		System.out.println(idCreator(".//*[@id='main']/div/div[2]/ul/li[1]/a"));
	}

	}
