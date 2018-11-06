package Rest_Assured_Test_Cases;

import java.util.ArrayList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Rest_Assured.PAR_Service_Call;
import com.Adesa.Utility.Constant;
import com.Adesa.Utility.ExcelUtils;

public class Rest_Assured_TC_PAR_Blackbook_Get_Model   {
	
	static int agents_size =0;
	
	public static int row_count = 0;	

	@BeforeMethod
	
	   public void Setup() throws Exception {
		
		System.out.println("Before Test method");
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_VTestData,"GetModels");
		row_count = ExcelUtils.getexcelrow(Constant.Path_TestData + Constant.File_VTestData,"GetModels");
	    System.out.println(" Total Excel row count:" + row_count);
				
	   }
	
	
	@Test(priority = 1)
	      
	   public void Get_Model() throws Exception {  
		
		ArrayList<String> Models_Listfrm_Service	= new ArrayList<String>();
		ArrayList<String> Models_Listfrm_Database 	= new ArrayList<String>();
		 
		  try {
			   
				for (int row=0;row<row_count;row++)
				{			   
			   						
					String GetModels_Post_Request 	= ExcelUtils.getCellData(1, 0);
					String Year  					= ExcelUtils.getCellDataNum(1, 1);
					String Make   					= ExcelUtils.getCellData(1, 2);
					String Service_URL 				= ExcelUtils.getCellData(1, 3);				
					String XMLpath 					= "Envelope.Body.getModelsResponse.Model";
					
					String Query = " SELECT MODEL FROM IH_PAR.IH_PAR_BLACKBOOK_DRILLDOWN"
								+  " WHERE YEAR =" + Year + "AND MAKE = "+ Make;
					
					//	Make = xmlPath.getList("getMakesResponse.Make");				
					// Make = xmlPath.getList("Envelope.Body.getMakesResponse.Make");
							
					Models_Listfrm_Service = PAR_Service_Call.Soap_Service_Response_Retrieval(GetModels_Post_Request,Service_URL,XMLpath);
					
					//Models_Listfrm_Database = PAR_Service_Call.Retrieve_Database_Values(Query);
					
					System.out.println("after Models_Listfrm_Service : " + Models_Listfrm_Service);
					//System.out.println("after Models_Listfrm_Databse : " + Models_Listfrm_Database);	
					
					PAR_Service_Call.Compare_API_Versus_DB();
		    		    	 
		    		
				} 
				
		  	}catch (Exception e){
			  
			  System.out.println("Get_Model Method Failed");
			  e.printStackTrace();
				
		  	}
     	     	
		}	  	 	 		   

	}
