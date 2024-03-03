package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class RegisterPageTest extends BaseTest {
	@BeforeClass
	public void regSetup() {
		regPage = loginPage.navigateToRegisterPage();
	}

	String curMilTime;

	public String getRandomCurrTimeInMills() {
//		return curMilTime= System.currentTimeMillis()+"";
		return curMilTime = String.valueOf(System.currentTimeMillis());
	}

	@DataProvider
	public Object[][] gerUserResTestData() {
		getRandomCurrTimeInMills();
		return new Object[][] { { "FNTest" + curMilTime, "LNTest" + curMilTime, "fnls" + curMilTime + "@test.com",
				"9999999998", "test" + curMilTime, "Yes" },
			{"FTestName"+curMilTime,"LTestName"+curMilTime,"test"+curMilTime+"@xyz.com","9999123335","test12"+curMilTime,"No"}
		};
	}

	@Test(dataProvider = "gerUserResTestData")
	public void userRegisterTest(String fName, String lName, String eId, String tel, String pwd, String subscrib) {
		Assert.assertTrue(regPage.registerUser(fName, lName, eId, tel, pwd, subscrib));
	}
}
