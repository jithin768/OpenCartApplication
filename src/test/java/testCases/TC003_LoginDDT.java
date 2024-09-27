package testCases;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")
	public void verify_loginDDT(String email,String pwd,String exp){
		
		logger.info("**** Starting TC003_LoginDDT ****");
		
		try {
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.setLoginClick();
		
		MyAccountPage MAP=new MyAccountPage(driver);
		boolean targetPage=MAP.msgConfirmation();
		
		
		/* data is valid - login success - test pass -logout
		 * 				-	login failed - test fail
		 * 
		 * 
		 * Data is invalid - login success - test fail -logout
		 * 					-login failed - test pass
		 * 
		 */
		
		
		if(exp.equalsIgnoreCase("valid")) {
			if(targetPage==true) {
				
				Assert.assertTrue(true);
				MAP.clickLogout();
				
			}else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) {
		
			if(targetPage==true) {
				
				MAP.clickLogout();
				Assert.assertTrue(false);
		
			}else {
				Assert.assertTrue(true);
			}

		}
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("**** Finished TC003_LoginDDT ****");
		
	}
}
