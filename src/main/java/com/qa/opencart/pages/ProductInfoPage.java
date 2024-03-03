package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By prodectImages = By.xpath("//ul[@class='thumbnails']/li");
	private By quantity = By.name("quantity");
	private By addToCartBtn = By.id("button-cart");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeaderValue() {
		return eleUtil.doElementGetText(productHeader);
	}

	public int getProductImagesCount() {
		int actProductImages = eleUtil.waitForElementsVisible(prodectImages, AppConstants.MEDIUM_TIME_OUT).size();
		System.out.println("Total product images for ==>" + getProductHeaderValue() + " ==> " + actProductImages);
		return actProductImages;
	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.waitForElementsVisible(productMetaData, AppConstants.MEDIUM_TIME_OUT);
		productMap = new HashMap<String,String>();
		for (WebElement e : metaList) {
			String metaText = e.getText();
			String key=metaText.split(":")[0].trim();
			String value = metaText.split(":")[1].trim();
			productMap.put(key, value);
		}
//		return metaMap;

	}
	
//	$2,000.00
//	Ex Tax: $2,000.00
	
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.waitForElementsVisible(productPriceData, AppConstants.MEDIUM_TIME_OUT);
		productMap= new HashMap<String, String>();
		
		String actPrice=priceList.get(0).getText().trim();
		String exTax=priceList.get(0).getText().split(":")[0].trim();
		String exTaxValue=priceList.get(0).getText().split(":")[1].trim();
		
		productMap.put("price", actPrice);
		productMap.put(exTax, exTaxValue);
		
//		return priceMap;
		
	}
	
	public Map<String, String> getProductData() {
		productMap=new HashMap<String, String>();
		productMap.put("productheader", getProductHeaderValue());
		productMap.put("productimages", String.valueOf(getProductImagesCount()));
		
		getProductMetaData();
		getProductPriceData();
		
		return productMap;
	}

}
