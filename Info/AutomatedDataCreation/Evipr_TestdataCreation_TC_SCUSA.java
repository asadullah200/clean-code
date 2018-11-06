package com.Adesa.DataCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Adesa.PageObjects.EViprPageTitle_Page;
import com.Adesa.PageObjects.EviprAccountLkup_Page;
import com.Adesa.PageObjects.EviprConsignment_Page;
import com.Adesa.PageObjects.EviprLogin_Page;
import com.Adesa.PageObjects.EviprLogoff_Page;
import com.Adesa.PageObjects.RdnFinalCase_Page;
import com.Adesa.PageObjects.SelectUrl;
import com.Adesa.PageObjects.ViprAccountLkup_Page;
import com.Adesa.PageObjects.ViprLogin_Page;
import com.Adesa.PageObjects.ViprLogoff_Page;
import com.Adesa.PageObjects.ViprPageTitle_Page;
import com.Adesa.PageObjects.ViprQueues_Page;
import com.Adesa.Utility.Constant;
import com.Adesa.Utility.Drivers;
import com.Adesa.Utility.ExcelUtils;
import com.Adesa.Utility.ExtentManager;
import com.Adesa.Utility.GetNewVIN;

//This is a Test Data Creation Test Case for Repo Redemption  

public class Evipr_TestdataCreation_TC_SCUSA extends ExtentManager{
	
	public static WebDriver driver = null;
	
	public static String EParStkNo = null;
	
	
	@BeforeMethod
	
	   public void Setup() throws Exception {
		
		   test = extent.createTest("***Test Data Creation Evipr_TestdataCreation_TC", "Test Data Creation Evipr_TestdataCreation_TC");
		   driver = Drivers.SelectDriver();
	       Thread.sleep(1000);   
	       
	   }
	
	   @Test(priority = 1)
	      
	   public void Main() throws Exception {  
		   
			String sENV = "STAGE";
		    String EVUrl=SelectUrl.EViprUrl(driver);
		   
		   	if(EVUrl.toUpperCase().contains("TEST"))
		   	{
		   		sENV = "TEST";
		   	}
		   	else
		   	{
		   		sENV = "STAGE";
		   	}
		   GetNewVIN.SetNewVINforExcelNew(Constant.Path_TestData + Constant.File_RepoRedemptionSCUSA, "TestData", 7, sENV);

		   ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionSCUSA,"TestData");
		   
	       int row_count = ExcelUtils.getexcelrow(Constant.Path_TestData + Constant.File_RepoRedemptionSCUSA,"TestData");
	       
	       System.out.println("red row count:" + row_count);

	       EVUrl=SelectUrl.EViprUrl(driver);   

		   driver.get(EVUrl); 
		    
		    Thread.sleep(1000);
		   
		    EViprPageTitle_Page.EViprUrlTitle(driver);
		    
		    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_ETestData,"EViprLogin");	 
	    	
	    	EviprLogin_Page.Login(driver);
	    	
	       
	        for(int i=1;i<=row_count;i++)
      	      	
		    {
	        	
				int k=0; 
		    	
		    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionSCUSA,"TestData");
		    	
		    	EViprPageTitle_Page.EViprConsignmentTitle(driver);
		    	
		    	EviprConsignment_Page.Createbtn(driver);
		    	
		    	EviprConsignment_Page.ConsWizStep1(driver, i, k);
		    	
		    	EviprConsignment_Page.Continuebtn(driver);
		    	
		    	EviprConsignment_Page.ConsDICreate(driver, i, k);
		    	
		    	EviprConsignment_Page.ConsCICreate(driver, i, k);	    	
		    	
		    	EviprConsignment_Page.Continuebtn(driver);
	
		    	EParStkNo = EviprConsignment_Page.ECaptureParStkNo(driver);
		    	
		    	System.out.println("EParStkNo :- "+EParStkNo);
		    	
				ExcelUtils.setCellDataRedem(EParStkNo,"TestData", i, k+67);
		    	
		    	EviprConsignment_Page.Submitbtn(driver);
		    
		    }
		    
	    	EviprLogoff_Page.EViprlogoff(driver);

	    	test.pass("Test Case Completed Successfully");
		       
		  }	   
		   

	   @AfterMethod
			
		public void Close() throws Exception {
								
                   driver.close();
				   
				   test.pass("Application Closed Successfully");  
			       
		}	   




}
