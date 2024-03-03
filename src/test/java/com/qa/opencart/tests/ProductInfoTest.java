package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void prodInfoSetup() {
		accPage = loginPage.doLogin("waseem.razain@gmail.com", "System@6");
	}
	
	@DataProvider
	public Object[][] productTestData() {
		return new Object[][] {
			{"macbook","MacBook"},
			{"macbook","MacBook Air"},
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(dataProvider = "productTestData")
	public void productHeaderTest(String searchKey, String productName) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		String actProductHeader = productInfoPage.getProductHeaderValue();
		Assert.assertEquals(actProductHeader, productName);
	}
	
	@DataProvider
	public Object[][] productImageTestData() {
		return new Object[][] {
			{"macbook","MacBook",5},
			{"macbook","MacBook Air",4},
			{"macbook","MacBook Pro",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7}
		};
	}
	
	@Test(dataProvider = "productImageTestData")
	public void productImagesCountTest(String searchKey, String productName, int expProductImagesCount) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		int actProductImagesCount= productInfoPage.getProductImagesCount();
		Assert.assertEquals(actProductImagesCount, expProductImagesCount);
	}
	
	@Test
	public void productInfoTest() {
		searchResPage = accPage.doSearch("macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String, String> productActualData = productInfoPage.getProductData();
		System.out.println(productActualData);
		softAssert.assertEquals(productActualData.get("Brand"), "Apple");
		softAssert.assertEquals(productActualData.get("Availability"), "In Stock");
		softAssert.assertEquals(productActualData.get("productheader"), "MacBook Pro");
		softAssert.assertEquals(productActualData.get("price"), "$2,000.00");
		softAssert.assertEquals(productActualData.get("Reward Points"), "800");
		softAssert.assertAll();
	}

}
