package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResulsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productsResults = By.cssSelector("div.product-layout");

	public SearchResulsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	/**
	 * 
	 * @return
	 */
	public int getSearchResulsCount() {
		return eleUtil.waitForElementsVisible(productsResults, AppConstants.MEDIUM_TIME_OUT).size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		eleUtil.clickElementWhenReady(By.linkText(productName), AppConstants.MEDIUM_TIME_OUT);
		return new ProductInfoPage(driver);
	}

}
