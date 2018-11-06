package Rest_Assured_Test_Cases;


import java.util.ArrayList;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Rest_Assured.PAR_Service_Call;


public class Rest_Assured_TC_Get_Blackbook   {
	
	 static int agents_size =0;
	 static String userid = "PARKARapi01";
	 static String psw = "PARKARapi01";
	 static String Jsonpath_year = "drilldown.class_list.year_list.name";	
	 static String Jsonpath_Make = "drilldown.class_list.year_list.make_list.name";	
	 static String Jsonpath_Model= "drilldown.class_list.year_list.make_list.model_list.name";	
	 ArrayList<String> Years_Listfrm_Service	= new ArrayList<String>();
	 ArrayList<String> Makes_Listfrm_Service	= new ArrayList<String>();
	 ArrayList<String> Models_Listfrm_Service	= new ArrayList<String>();
	
	public static int row_count = 0;	

	@BeforeMethod
	
	   public void Setup() throws Exception {
		
		System.out.println("Before Test method");
		
				
	   }
	
	@Test(priority = 1)
	      
	   public void Main() throws Exception {  
		
					 
		   try {
	
			   String Service_URL = "https://service.blackbookcloud.com/UsedCarWS/UsedCarWS/Drilldown/ALL?drilldeep=true&getclass=false&customerid=test";	
			   Years_Listfrm_Service = PAR_Service_Call.Rest_Get_Service_Response_Retrieval(Service_URL,Jsonpath_year,userid,psw);
			   
			   //System.out.println("Years_Listfrm_Service  : "+ Years_Listfrm_Service.get(0) );
			   
			   for(int Years_List_int=0; Years_List_int<Years_Listfrm_Service.size(); Years_List_int++) 
					{
					   String Make_Service_URL = "https://service.blackbookcloud.com/UsedCarWS/UsedCarWS/Drilldown/ALL/"+Years_Listfrm_Service.get(Years_List_int)+"?drilldeep=true&getclass=false&customerid=test";
					 //  System.out.println("Year Passed : "+ Years_Listfrm_Service.get(Years_List_int) );				   
					   
					   Makes_Listfrm_Service = PAR_Service_Call.Rest_Get_Service_Response_Retrieval(Make_Service_URL,Jsonpath_Make,userid,psw);
					  // System.out.println("Makes_Listfrm_Service  : "+ Makes_Listfrm_Service );
					   //PAR_SOAP_Service_Make_Model_Year.Get_Make_Model_for_Year(Years_Listfrm_Service.get(Years_List_int));
					   
					   for(int Makes_List_int=0; Makes_List_int<Makes_Listfrm_Service.size(); Makes_List_int++) 
							{
							   
								  // String Model_Service_URL = "https://service.blackbookcloud.com/UsedCarWS/UsedCarWS/Drilldown/ALL/"+Makes_Listfrm_Service.get(Makes_List_int)+"?drilldeep=true&getclass=false&customerid=test";
								   String Model_Service_URL = "https://service.blackbookcloud.com/UsedCarWS/UsedCarWS/Drilldown/ALL/"+Years_Listfrm_Service.get(Years_List_int)+"/"+Makes_Listfrm_Service.get(Makes_List_int)+"?drilldeep=false&getclass=false&customerid=test";
								   String Year = Years_Listfrm_Service.get(Years_List_int);
								   String Make = Makes_Listfrm_Service.get(Makes_List_int);
								   System.out.println("Year & Make Passed : "+ Years_Listfrm_Service.get(Years_List_int) +" : "+ Makes_Listfrm_Service.get(Makes_List_int) );				   
								   
								   Models_Listfrm_Service = PAR_Service_Call.Rest_Get_Service_Response_Retrieval(Model_Service_URL,Jsonpath_Model,userid,psw);
								   System.out.println("Models_Listfrm_Service  : "+ Models_Listfrm_Service );
								   
								   String Query = " SELECT MODEL FROM IH_PAR.IH_PAR_BLACKBOOK_DRILLDOWN"
											+  " WHERE YEAR =" + Year + "AND MAKE = "+ Make;
								   
								  //Models_Listfrm_Database = PAR_SOAP_Service_Make_Model_Year.Retrieve_Database_Values(Query);
								
								  //System.out.println("after Models_Listfrm_Databse : " + Models_Listfrm_Database);	
									
								  // PAR_Service_Call.Compare_API_Versus_DB();
							   
							}
					}
				   
				   
			   
				}		   						   
				    	
		
		    catch (Exception e){				
								 
				e.printStackTrace();
				
		    				  }
     	     	
		}	 
	
	public static String removeLeadingSpaces(String param)
    {
        if (param == null) {
            return null;
        }
         
        if(param.isEmpty()) {
            return "";
        }
         
        int arrayIndex = 0;
        while(true)
        {
            if (!Character.isWhitespace(param.charAt(arrayIndex++))) {
                break;
            }
        }
        return param.substring(arrayIndex-1);
    }
	
			   
	
}
