package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class RegisterPageTest extends BaseTest {
	@BeforeClass
	public void accPageSetup() {
//		loginPage.doClickLogout();
		registerPage = loginPage.doClickRegister();
	}
	@DataProvider
	public Object[][] resisterTestData() {
		return new Object[][] {
			{"FTestName","LTestName","test@xyz.com","9999123335","test123"}
		};
	}
	
	
	@Test(dataProvider = "resisterTestData")
	public void fillRegisterPageTest(String fName,String lName,String eId,String tel,String pwd) {
		registerPage.fillRegisterPage(fName, lName, eId, tel, pwd);
	}
}
