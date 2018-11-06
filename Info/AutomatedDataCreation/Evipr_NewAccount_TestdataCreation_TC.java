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

public class Evipr_NewAccount_TestdataCreation_TC extends ExtentManager{
	
	public static WebDriver driver = null;
	
	public static String EParStkNo = null;
	
	
	@BeforeMethod
	
	   public void Setup() throws Exception {
		
		   test = extent.createTest("***Test Data Creation Evipr_NewAccount_TestdataCreation_TC", "Test Data Creation Evipr_NewAccount_TestdataCreation_TC");
		   driver = Drivers.SelectDriver();
	       Thread.sleep(1000);   
	       
	   }
	
	   @Test(priority = 1)
	      
	   public void Main() throws Exception {  
		   
//		   GetNewVIN.SetNewVINforExcel(Constant.Path_TestData + Constant.File_RepoRedemption, "TestData", 7);

		   ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionNew,"TestData");
		   
	       int row_count = ExcelUtils.getexcelrow(Constant.Path_TestData + Constant.File_RepoRedemptionNew,"TestData");
	       
	       System.out.println("red row count:" + row_count);
	       
	        for(int i=1;i<=row_count;i++)
      	      	
		    {
	        	
			int k=0;
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

		   	driver.get(EVUrl); 
		    
		    Thread.sleep(1000);
		   
		    EViprPageTitle_Page.EViprUrlTitle(driver);
		    
		    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_ETestData,"EViprLogin");	 
	    	
	    	EviprLogin_Page.Login(driver);
	    	
	    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionNew,"TestData");

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

	    	EviprLogoff_Page.EViprlogoff(driver);
	    	
	    	driver.close();
	    	
			driver = Drivers.ChromeDriver();
				 
		    Thread.sleep(1000);		   
			   
		    String VUrl=SelectUrl.ViprUrl(driver);   

			driver.get(VUrl);
			   
			ViprPageTitle_Page.ViprUrlTitle(driver);
			    		    	    	
		    ViprLogin_Page.Login(driver);
		    
		    ViprAccountLkup_Page.SrchCriteriaSrch(driver, EParStkNo);
		    
		    ViprAccountLkup_Page.SrchResultDetailbtn(driver);
		    
		    ViprAccountLkup_Page.SelectTabRepo(driver);

//Checking for Waiting to be assigned Status	
		    
		    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_ETestData,"ConstantFilterFLD");
		    
		    String ERSts    = ExcelUtils.getCellData(2,2);
		    
		    String RepStat = ViprAccountLkup_Page.TabRepoAssignInfoRepoStatCapture(driver , ERSts);  //"2"
		    
		    System.out.println("RepStat :"+RepStat);

		    //Checking for Repo TYpe is Impound
		    
		    String ERTyp    = ExcelUtils.getCellData(3,4);
		    
		    String RepType = ViprAccountLkup_Page.TabRepoAssignInfoRepoTypeCapture(driver,ERTyp);  //"3"
		    
		    System.out.println("RepType :"+RepType);
		    
//Assign an Agent
		    
		    ViprAccountLkup_Page.SelectTabRepoAgtAdNwbtn(driver);
		       
		    ViprAccountLkup_Page.SelTabRepoAdAgntWindwFindAgtbtn(driver);
		       
	    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionNew,"TestData");
//		    ViprAccountLkup_Page.SelTabRepoAdAgntWindwSAgnt(driver);
		    ViprAccountLkup_Page.SelTabRepoAdAgntWindwSAgntNew(driver, sENV, i, 68);
		       
		    ViprAccountLkup_Page.SelTabRepoAdAgntWindwRdMilbtn(driver);
		       
		    ViprAccountLkup_Page.SelTabRepoAdAgntWindwAdActbtn(driver);
		       
		    ViprAccountLkup_Page.SelectTabRepoSavebtn(driver);


	    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionNew,"TestData");
		    // Asad Assignement Status Column# 66
		    String sRepoRedemp    = ExcelUtils.getCellData(i,49);
	    	if (sRepoRedemp.equals("Agent"))
	    	{
			    ViprQueues_Page.TabRepoAssignInfoSet(driver, Constant.Path_TestData + Constant.File_RepoRedemptionNew, "TestData", i, 66);
	    	}

	    	//Checking for Active Status	
		    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_ETestData,"ConstantFilterFLD");
		    
		    String ERSts1    = ExcelUtils.getCellData(1,2);
		    
		    String RepStat1 = ViprAccountLkup_Page.TabRepoAssignInfoRepoStatCapture(driver , ERSts1);  //"1"
		    
		    System.out.println("RepStat :"+RepStat1);
		    
//  Assign status in ReMarketing Tab		
		    
		    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_RepoRedemptionNew,"TestData");
		    
		    String vRemReq  = ExcelUtils.getCellData(i,k+54);
		    
		    if("Y".equalsIgnoreCase(vRemReq)){
		    
		          ViprAccountLkup_Page.SelectTabRem(driver);
		    
		          ViprAccountLkup_Page.TabRem_RemInfo_Create(driver, i, k);
		    
		          ViprAccountLkup_Page.VAcntLkupTabSavebtn(driver);
		    
		    }     
		    
//  Assign info in Redemption Tab	
		      
		    ViprAccountLkup_Page.SelectTabRedem(driver);
		    
		    ViprAccountLkup_Page.TabRedem_RedemInfo_Create(driver, i, k);
		    

//  Assign info to Tracking Tab	
		    
		    String vTracReq  = ExcelUtils.getCellData(i,k+52);
		    
		    if("Y".equalsIgnoreCase(vTracReq)){
		    
		    	  ViprAccountLkup_Page.VAcntLkupTabSavebtn(driver);
		    	
		    	  ViprAccountLkup_Page.SelectTabTrak(driver);
		    	
		    	  ViprAccountLkup_Page.TabTrak_TrakInfo_Create(driver, i, k);
		    	
		    }
		    
		    ViprAccountLkup_Page.VAcntLkupTabSaveClsbtn(driver);
		    
		    ViprLogoff_Page.Viprlogoff(driver);
		    
		    }
		    
		    test.pass("Test Case Completed Successfully");
		       
		  }	   
		   

	   @AfterMethod
			
		public void Close() throws Exception {
								
                   driver.close();
				   
				   test.pass("Application Closed Successfully");  
			       
		}	   




}
