package com.Adesa.DataCreation;

//------------------------------------------------------------------------------------------------//
//****************** This Test Case is to Create Test Data for RDN Application********************//
//------------------------------------------------------------------------------------------------//

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Adesa.PageObjects.*;
import com.Adesa.Utility.*;
import com.Adesa.PageObjects.RdnLogin_Page;


public class RdnTestdataCreation extends ExtentManager{
	
   public static WebDriver driver = null;
   
   @BeforeMethod
   public void Setup() throws Exception {
	
//	   ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RDNTestData,"RdnLogin");

	   test = extent.createTest("***Test Data Creation RdnTestdataCreation", "Test Data Creation RdnTestdataCreation");
	   driver = Drivers.SelectDriver();
       Thread.sleep(1000);   
       
   }

   @Test
   public void main() throws Exception {  
    	
	   String sEnvionment = "STAGE";
	   GetNewVIN.SetNewVINforExcelNew(Constant.Path_TestData + Constant.File_RDNTestData, "RdnANROScreen", 4, sEnvionment);

	   driver.get(Constant.RDNURL);
	   
    	RdnPageTitle_Page.RdnUrlTitle(driver);
    	
 	    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RDNTestData,"RdnLogin");
		System.out.println("RdnLogin");

 	    RdnLogin_Page.Login(driver);
    
      	Thread.sleep(2000);

      	int row_count = ExcelUtils.getexcelrow(Constant.Path_TestData + Constant.File_RDNTestData,"RdnANROScreen");
   
      	int col_count = ExcelUtils.getexcelcol(Constant.Path_TestData + Constant.File_RDNTestData,"RdnANROScreen");
       	
      	System.out.println("row count:" + row_count);
    	
      	System.out.println("col count:" + col_count);

        for(int i=1;i<=row_count;i++)
      	      	
	    {
        	
		int k=0;
			    
			RdnMainMenu_Page.AddNewCasebtn(driver);

			RdnPageTitle_Page.RdnZiplkupTitle(driver);
    
			RdnZiplkup_Page.Ziplkup(driver);
    
			RdnAgencySel_Page.btnSeeAllAgency(driver);
    
			RdnAgencySel_Page.SelParNorthAmerica(driver);
    
			ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RDNTestData,"RdnANROScreen");
    
			RdnANRO_Page.AssignNewRepossession(driver, i, k);
    
			RdnANRO_Page.AssignNewReposCollateral(driver, i, k);
    
			RdnANRO_Page.AssignNewReposBorrower(driver, i, k);
    
			RdnANRO_Page.AssignNewReposCoSigner(driver, i, k);
    
			RdnANRO_Page.btnSendOrderNowRdn(driver);
    
			RobotClass.RdnClsPrtScrn(driver);
     
			String CaseNoReturn=  RdnFinalCase_Page.RdnFinalCase(driver); 
    	
			ExcelUtils.setCellData(CaseNoReturn,"PAR_Rdn_TestData.xlsx","RdnANROScreen", i, k+59);
   
	    }
        
        System.out.println("Test Data Creation Successful");  
   }
      

   @AfterMethod 
   public void CloseBrowser() throws Exception {
	   
//    	RdnCloseApplication.CloseApp(driver);
    	
   }    
  
}
