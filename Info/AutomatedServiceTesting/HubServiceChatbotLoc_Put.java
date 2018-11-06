package Rest_Assured_Chatbot;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Rest_Assured.Rest_Assured_Response;
import Rest_Assured.ServiceValuesObject;
import com.Adesa.Utility.Constant;
import com.Adesa.Utility.ExcelUtils;


public class HubServiceChatbotLoc_Put  {	

	
	@BeforeMethod
	public void Setup() throws Exception {
			
			
		System.out.println("Before Test method");
		       
	}

	@Test
	public void main() throws Exception {  
		
		    System.out.println("Starting Service saveCoordinates Test");
		    
		    Rest_Assured_Response.ReadExcelChatBotLoc_Put(Constant.Path_TestData + Constant.File_VTestData, "ChatbotServicePut");
		    
	        Rest_Assured_Response.WebServiceChatBotLoc_Put();
		
	}	      

	@AfterMethod
	public void Close() throws Exception {

	}	   

}
