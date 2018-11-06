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

//This is a Test Data Creation Test Case for Repo Redemption Enter PD Information

public class Evipr_NewAccount_TestdataCreation_RR1_PD extends ExtentManager{
	
	public static WebDriver driver = null;
	
	public static String EParStkNo = null;
	
	
	   @BeforeMethod
	   public void Setup() throws Exception {
		
		   test = extent.createTest("***Test Data Creation Evipr_NewAccount_TestdataCreation_RR1_PD", "Test Data Creation Evipr_NewAccount_TestdataCreation_RR1_PD");
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

		    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionRR1,"TestData");
		   
	        int row_count = ExcelUtils.getexcelrow(Constant.Path_TestData + Constant.File_RepoRedemptionRR1,"TestData");
	       
	        System.out.println("red row count:" + row_count);
	       
	        for(int i=1;i<=row_count;i++)
      	      	
		    {
	        	
			    String VUrl=SelectUrl.ViprUrl(driver);   
	
			    String sLoginUser = "viprrepomgr1";
				driver.get(VUrl);
				   
				ViprPageTitle_Page.ViprUrlTitle(driver);
				    		    	    	
				ViprLogin_Page.Login_with_User(driver, sLoginUser);
			    
				test = extent.createTest("Create VIPR Data", "Create VIPR Data");

				ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionRR1,"ReportingRepo");
				
			    EParStkNo  = ExcelUtils.getCellData( i, 0);
			    
		    	System.out.println("ParStkNo :- "+EParStkNo);

				ViprAccountLkup_Page.SrchCriteriaSrch(driver, EParStkNo);
			    
			    ViprAccountLkup_Page.SrchResultDetailbtn(driver);
			    
			    // Add Skip Tab Information Here
			    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionRR1,"Skip");
			    ViprAccountLkup_Page.SelectTabSkip(driver);
			    ViprAccountLkup_Page.SelectSkipStatus(driver, i, 0);
			    ViprAccountLkup_Page.SelectSkipType(driver, i, 1);
			    ViprAccountLkup_Page.SelectTabSkipSavebtn(driver);
			    
			    ViprAccountLkup_Page.SelectTabRepo(driver);
	
			    //Navigate to Reporting Repo Tab and entering the data
			    
			    ViprAccountLkup_Page.TabRepoReportingRepoLnk(driver);
			    
			    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionRR1,"ReportingRepo");
			    
			    int c=1;
			    
			    ViprAccountLkup_Page.ReportingRepoCreate(driver, i, c);
	
			    ViprAccountLkup_Page.ReportingRepoCreatePD(driver, i, 20);

			    ViprAccountLkup_Page.SelectTabRepoSavebtn(driver);
				Thread.sleep(1000);
			    
	//		    ViprAccountLkup_Page.SelectTabRepoRepRepoFnlbtn(driver);
				
			    
			    
			    ViprLogoff_Page.Viprlogoff(driver);
		    
		    }
		    
		    test.pass("Test Case Completed Successfully");
		       
		  }	   
		   

	    @AfterMethod
		public void Close() throws Exception {
								
		    System.out.println("Closing Driver");
//            driver.close();
				   
            test.pass("Application Closed Successfully");  
			       
		}	   




}
