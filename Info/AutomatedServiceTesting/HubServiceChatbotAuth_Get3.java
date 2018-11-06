package Rest_Assured_Chatbot;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Rest_Assured.Rest_Assured_Response;

import com.Adesa.Utility.Constant;

public class HubServiceChatbotAuth_Get3 {

	
	@BeforeMethod
	public void Setup() throws Exception {
			
		System.out.println("Before Test method");
		       
	}

	@Test
	public void main() throws Exception {  
		
	    	System.out.println("Starting Service chatBotAuth Test");
	    	
	    	Rest_Assured_Response.ReadExcelchatBotAuth_Get(Constant.Path_TestData + Constant.File_VTestData, "ChatbotServiceGet3");
			
			Rest_Assured_Response.WebServicechatBotAuth_Get();
		   
	}
	
	@AfterMethod
	public void Close() throws Exception {

	}

}
