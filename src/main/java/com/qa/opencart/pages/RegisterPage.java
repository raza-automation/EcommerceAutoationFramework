package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By fNameTxt = By.id("input-firstname");
	private By lNameTxt =By.id("input-lastname");
	private By eIdTxt =By.id("input-email");
	private By telTxt =By.id("input-telephone");
	private By passTxt =By.id("input-password");
	private By passConTxt =By.id("input-confirm");
	private By radioYesBtn =By.xpath("//label[normalize-space()='Yes']//input[@name='newsletter']");
	private By agreeChck =By.xpath("//input[@type='checkbox']");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public void fillRegisterPage(String fName,String lName,String eId,String tel,String pwd) {
		eleUtil.waitForElementPresence(fNameTxt, AppConstants.SHORT_TIME_OUT).sendKeys(fName);
		eleUtil.doSendKeys(lNameTxt, lName);
		eleUtil.doSendKeys(eIdTxt, eId);
		eleUtil.doSendKeys(telTxt, tel);
		eleUtil.doSendKeys(passTxt, pwd);
		eleUtil.doSendKeys(passConTxt, pwd);
		eleUtil.clickElementWhenReady(radioYesBtn, AppConstants.SHORT_TIME_OUT);
		eleUtil.doClick(agreeChck);
		
	}

}
