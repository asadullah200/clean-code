package Rest_Assured_Chatbot;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Rest_Assured.PAR_Service_Call;


public class Rest_Assured_TC_Get_Chatbot   {
	

	static String path = "status";		
	static int statusCode;		
	
	public static int row_count = 0;	

	@BeforeMethod
	
	   public void Setup() throws Exception {
		
		System.out.println("Before Test method");
		
				
	   }
	
	@Test(priority = 1)
	      
	   public void Main() throws Exception {  
		
					 
		   try {
	
			   String Service_URL = "http://services.test.parnorthamerica.biz/VIPR-Services/rest/asset/chatBotAuth?vin=1N4AL21E78N434180&agentMasterName=Sun west Recovery inc.&postalCode=33950";			
			   statusCode = PAR_Service_Call.Rest_Get_Service_status_code(Service_URL);	
			   
			 //  Thread.sleep(5000);
			   
			   System.out.println("statusCode is : "+ statusCode);
				    	
		
		   } catch (Exception e){
				
								 
				e.printStackTrace();
				
	}
     	     	
		}	  	 		   

	

}
