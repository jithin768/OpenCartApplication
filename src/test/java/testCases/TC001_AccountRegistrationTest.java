package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	
	@Test(groups={"Master","Regression"})
	public void verify_account_registration() {
		
		logger.info(" *** Starting Test case TC001_AccountRegistrationTest *** ");
		
		try {
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAcountLink");
		hp.clickRegister();
		logger.info("Clicked on RegisterLink");
		
		
		AccountRegistrationPage regPage=new AccountRegistrationPage(driver);
		
		logger.info(" Entering Customer informations ");
		regPage.setFirstName(randomeString().toUpperCase());
		regPage.setLastName(randomeString().toUpperCase());
		regPage.setEmail(randomeString()+"@asp.com");
		regPage.setTelephone(randomeNumber());
		
		String password = randomeAlphaNumeric();
		
		regPage.setPassword(password);
		regPage.setConfirmPassword(password);
		
		regPage.setPrivacyPolicy();
		regPage.clickContinue();
		
		logger.info(" Validating Confirmation Message");
		
		String confmsg=regPage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		
		}catch(Exception e) {
			logger.error("Test failed");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		
		logger.info(" *** Finished TC001_AccountRegistrationTest *** ");
	}
	
	
	
}
