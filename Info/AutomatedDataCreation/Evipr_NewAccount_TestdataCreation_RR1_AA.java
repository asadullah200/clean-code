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
import com.Adesa.Utility.TakeScreenShot;

//This is a Test Data Creation Test Case for Repo Redemption  

public class Evipr_NewAccount_TestdataCreation_RR1_AA extends ExtentManager{
	
	public static WebDriver driver = null;
	
	public static String EParStkNo = null;
	
	
	@BeforeMethod
	
	   public void Setup() throws Exception {
		
		   test = extent.createTest("***Test Data Creation Evipr_NewAccount_TestdataCreation_RR1_AA", "Test Data Creation Evipr_NewAccount_TestdataCreation_RR1_AA");
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

		   GetNewVIN.SetNewVINforExcelNewYMMS(Constant.Path_TestData + Constant.File_RepoRedemptionRR1, "TestData", 7, sENV);

		   ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionRR1,"TestData");
		   
	       int row_count = ExcelUtils.getexcelrow(Constant.Path_TestData + Constant.File_RepoRedemptionRR1,"TestData");
	       
	       System.out.println("red row count:" + row_count);
	       
	        for(int i=1;i<=row_count;i++)
      	      	
		    {
	        	
				int k=0;
	
			   	driver.get(EVUrl); 
			    
			    Thread.sleep(1000);
			   
//			    EViprPageTitle_Page.EViprUrlTitle(driver);
			    
			    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_ETestData,"EViprLogin");	 
		    	
		    	EviprLogin_Page.Login(driver);
		    	
		    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionRR1,"TestData");
	
//		    	EViprPageTitle_Page.EViprConsignmentTitle(driver);
		    	
				test = extent.createTest("Create eVIPR Accounts", "Create eVIPR Accounts");

				EviprConsignment_Page.Createbtn(driver);
		    	
		    	EviprConsignment_Page.ConsWizStep1(driver, i, k);
		    	
		    	EviprConsignment_Page.Continuebtn(driver);
		    	
		    	EviprConsignment_Page.ConsDICreate(driver, i, k);
		    	
		    	EviprConsignment_Page.ConsCICreate(driver, i, k);	    	
		    	
		    	EviprConsignment_Page.Continuebtn(driver);
	
		    	EParStkNo = EviprConsignment_Page.ECaptureParStkNo(driver);
				TakeScreenShot.PassScreenShot(driver, "EviprConsignment_Page");
		    	
		    	System.out.println("EParStkNo :- "+EParStkNo);
		    	
		    	ExcelUtils.setCellDataNew(EParStkNo, Constant.Path_TestData + Constant.File_RepoRedemptionRR1, "TestData", i, k+67);
		    	ExcelUtils.setCellDataNew(EParStkNo, Constant.Path_TestData + Constant.File_RepoRedemptionRR1, "ReportingRepo", i, 0);
		    	
		    	EviprConsignment_Page.Submitbtn(driver);
	
		    	EviprLogoff_Page.EViprlogoff(driver);
		    	
			    Thread.sleep(1000);		   
				   
			    String VUrl=SelectUrl.ViprUrl(driver);   
	
			    String sLoginUser = "viprrepomgr1";
				driver.get(VUrl);
				   
				ViprPageTitle_Page.ViprUrlTitle(driver);
				    		    	    	
				ViprLogin_Page.Login_with_User(driver, sLoginUser);
			    
				test = extent.createTest("Create VIPR Data", "Create VIPR Data");
				
			    ViprAccountLkup_Page.SrchCriteriaSrch(driver, EParStkNo);
			    
			    ViprAccountLkup_Page.SrchResultDetailbtn(driver);
			    
			    ViprAccountLkup_Page.SelectTabRepo(driver);
				TakeScreenShot.PassScreenShot(driver, "SelectTabRepo");
	
				//Checking for Waiting to be assigned Status	
			    
			    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_ETestData,"ConstantFilterFLD");
			    
			    String ERSts    = ExcelUtils.getCellData(2,2);
			    
			    String RepStat = ViprAccountLkup_Page.TabRepoAssignInfoRepoStatCapture(driver , ERSts);  //"2"
			    
			    System.out.println("RepStat :"+RepStat);
	
			    //Assign an Agent
			    
			    ViprAccountLkup_Page.SelectTabRepoAgtAdNwbtn(driver);
			       
			    ViprAccountLkup_Page.SelTabRepoAdAgntWindwFindAgtbtn(driver);
			       
		    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionRR1,"TestData");

		    	ViprAccountLkup_Page.SelTabRepoAdAgntWindwSAgntNew(driver, sENV, i, 68);
			       
			    ViprAccountLkup_Page.SelTabRepoAdAgntWindwRdMilbtn(driver);
			       
				Thread.sleep(1000);
			    ViprAccountLkup_Page.SelTabRepoAdAgntWindwAdActbtn(driver);
				TakeScreenShot.PassScreenShot(driver, "SelTabRepoAdAgntWindwAdActbtn");

				ViprAccountLkup_Page.SelectTabRepoSavebtn(driver);
	
	
		    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionRR1,"TestData");
			    // Asad Assignement Status Column# 66
			    String sRepoRedemp    = ExcelUtils.getCellData(i,49);
		    	if (sRepoRedemp.equals("Agent"))
		    	{
				    ViprQueues_Page.TabRepoAssignInfoSet(driver, Constant.Path_TestData + Constant.File_RepoRedemptionRR1, "TestData", i, 66);
					TakeScreenShot.PassScreenShot(driver, "TabRepoAssignInfoSet");
		    	}

		    	//Checking for Active Status	
			    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_ETestData,"ConstantFilterFLD");
			    
			    String ERSts1    = ExcelUtils.getCellData(1,2);
			    
			    String RepStat1 = ViprAccountLkup_Page.TabRepoAssignInfoRepoStatCapture(driver , ERSts1);  //"1"
			    
			    System.out.println("RepStat :"+RepStat1);
	
			    ViprAccountLkup_Page.SelectTabRepoAgtAdNwbtn(driver);
			       
			    ViprAccountLkup_Page.SelTabRepoAdAgntWindwFindAgtbtn(driver);
			       
		    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionRR1,"TestData");

			    ViprAccountLkup_Page.SelTabRepoAdAgntWindwSAgntNew(driver, sENV, i, 69);
			       
			    ViprAccountLkup_Page.SelTabRepoAdAgntWindwRdMilbtn(driver);
			       
				Thread.sleep(1000);
			    ViprAccountLkup_Page.SelTabRepoAdAgntWindwAdActbtn(driver);
				TakeScreenShot.PassScreenShot(driver, "SelectTabRepo");
				
				//Select Agent Name
			    ViprQueues_Page.TabRepoSelectAgent2(driver, Constant.Path_TestData + Constant.File_RepoRedemptionRR1, "TestData", i, 69);
			       
			    ViprAccountLkup_Page.SelectTabRepoSavebtn(driver);
				Thread.sleep(1000);
	
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
