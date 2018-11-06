package Rest_Assured_Test_Cases;

import java.util.ArrayList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Rest_Assured.PAR_Service_Call;
import com.Adesa.Utility.Constant;
import com.Adesa.Utility.ExcelUtils;

public class Rest_Assured_TC_PAR_Blackbook_Get_Year   {
	
	static int agents_size =0;
	
	public static int row_count = 0;	

	@BeforeMethod
	
	   public void Setup() throws Exception {
		
		System.out.println("Before Test method");
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_VTestData,"GetYears");
		row_count = ExcelUtils.getexcelrow(Constant.Path_TestData + Constant.File_VTestData,"GetYears");
	    System.out.println(" Total Excel row count:" + row_count);
				
	   }
	
	
	@Test(priority = 1)
	      
	   public void Get_Model() throws Exception {  
		
		ArrayList<String> Years_Listfrm_Service	= new ArrayList<String>();
		ArrayList<String> Years_Listfrm_Database 	= new ArrayList<String>();
		 
		  try {
			   
				for (int row=0;row<row_count;row++)
				{			   
			   						
					String GetYears_Post_Request 	= ExcelUtils.getCellData(1, 0);				
					String Service_URL 				= ExcelUtils.getCellData(1, 1);				
					String XMLpath 					= "Envelope.Body.getYearsResponse.Year";
					
					String Query = " SELECT YEAR FROM IH_PAR.IH_PAR_BLACKBOOK_DRILLDOWN ";
								
					
					//	Make = xmlPath.getList("getMakesResponse.Make");				
					// Make = xmlPath.getList("Envelope.Body.getMakesResponse.Make");
							
					Years_Listfrm_Service = PAR_Service_Call.Soap_Service_Response_Retrieval(GetYears_Post_Request,Service_URL,XMLpath);
					
					//Years_Listfrm_Database = PAR_SOAP_Service_Make_Model_Year.Retrieve_Database_Values(Query);
					
					System.out.println("after Models_Listfrm_Service : " + Years_Listfrm_Service);
					//System.out.println("after Models_Listfrm_Databse : " + Years_Listfrm_Database);	
					
					//PAR_SOAP_Service_Make_Model_Year.Compare_API_Versus_DB();
		    		    	 
		    		
				} 
				
		  	}catch (Exception e){
			  
			  System.out.println("Get_Model Method Failed");
			  e.printStackTrace();
				
		  	}
     	     	
		}	  	 	 		   

	}
