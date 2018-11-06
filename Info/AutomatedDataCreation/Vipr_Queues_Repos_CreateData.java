package com.Adesa.DataCreation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Adesa.PageObjects.EviprAgentScoreboard_page;
import com.Adesa.PageObjects.EviprLogin_Page;
import com.Adesa.PageObjects.EviprLogoff_Page;
import com.Adesa.PageObjects.SelectUrl;
import com.Adesa.PageObjects.ViprAccountLkup_Page;
import com.Adesa.PageObjects.ViprLogin_Page;
import com.Adesa.PageObjects.ViprLogoff_Page;
import com.Adesa.PageObjects.ViprQueues_Page;
import com.Adesa.Utility.Constant;
import com.Adesa.Utility.Drivers;
import com.Adesa.Utility.ExcelUtils;
import com.Adesa.Utility.ExtentManager;
import com.Adesa.Utility.CommonObjectUtils;
import com.Adesa.Utility.GetNewVIN;

public class Vipr_Queues_Repos_CreateData extends ExtentManager{
	   public static WebDriver driver = null;
		
	   @BeforeMethod
	   public void Setup() throws Exception {
			
			   test = extent.createTest("***Test Data Creation Vipr_Queues_Repos_CreateData", "Test Data Creation Vipr_Queues_Repos_CreateData");
			   driver = Drivers.SelectDriver();
		       Thread.sleep(1000);
		       
		}

	@Test
	public void main() throws Exception {

		   String sENV = "STAGE";
		   String sLoginUser = "viprrepomgr1";
		   String VUrl;

		   // Login to Vipr
		   VUrl=SelectUrl.ViprUrl(driver);

		   if(VUrl.toUpperCase().contains("TEST"))
		   	{
		   		sENV = "TEST";
		   	}
		   	else
		   	{
		   		sENV = "STAGE";
		   	}
		   GetNewVIN.SetNewVINforExcelNew(Constant.Path_TestData + Constant.File_VTestData, "RepoCreateData", 1, sENV);

		   driver.get(VUrl);
		   
		   ViprLogin_Page.Login_with_User(driver, sLoginUser);

		   int row_count = ExcelUtils.getexcelrow(Constant.Path_TestData + Constant.File_VTestData,"RepoCreateData");
	        
		   System.out.println("row count: " + row_count);
	    	
	       for(int i=1;i<=row_count;i++)
	       {
	 		   
	    	   ViprAccountLkup_Page.AccountLkupTabbtn(driver);

	    	   ViprQueues_Page.Vipr_CreateNewAccount(driver, "RepoCreateData", i);
			   
	    	   ViprQueues_Page.Vipr_NewAccountPARRepoSaveAndClose(driver);
	    	   
//			   ViprAccountLkup_Page.SelectTabRepoAgtAdNwbtn(driver);
//			   
//		       ViprAccountLkup_Page.SelTabRepoAdAgntWindwFindAgtbtn(driver);
//		       
//		       ViprAccountLkup_Page.SelTabRepoAdAgntWindwSAgnt(driver);
//		       
//		       ViprAccountLkup_Page.SelTabRepoAdAgntWindwRdMilbtn(driver);
//		       
//		       ViprAccountLkup_Page.SelTabRepoAdAgntWindwAdActbtn(driver);
//		       
//		       ViprQueues_Page.Vipr_NewAccountPARRepoSave(driver);
//	
//		       ViprQueues_Page.TabRepoAssignInfo(driver, i);
//		       
//		       ViprQueues_Page.Vipr_NewAccountPARRepoSave(driver);
//		       
//		       ViprQueues_Page.Vipr_ReportingRepoUpdate(driver, i);
//		       
//			   ViprQueues_Page.QueuesTabbtn(driver);
//	
//			   ViprQueues_Page.ReposReportedLnk(driver);
//		       
//			   ViprQueues_Page.Vipr_RepoReportedValidate(driver, i);
//			   
//			   ViprAccountLkup_Page.AccountLkupParStkNo_ReportingRepo_Finalize(driver, i);
//	
//			   ViprQueues_Page.QueuesTabbtn(driver);
//	
//			   ViprQueues_Page.ReposReportedLnk(driver);
//	
//			   ViprQueues_Page.Vipr_RepoReportedValidateNoPARNo(driver, i);

	       }
		   
		   ViprLogoff_Page.Viprlogoff(driver);

	}

	  @AfterMethod
	  public void Close() throws Exception {
								
		   driver.close();
		   test.pass("Application Closed Successfully");  
		   extent.flush();
	  }

}

