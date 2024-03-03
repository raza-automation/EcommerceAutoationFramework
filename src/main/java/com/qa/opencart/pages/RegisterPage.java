package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName =By.id("input-lastname");
	private By email =By.id("input-email");
	private By telephone =By.id("input-telephone");
	private By password =By.id("input-password");
	private By confirmPassword =By.id("input-confirm");
	
	private By subscribeYes =By.xpath("//label[normalize-space()='Yes']//input[@name='newsletter']");
	private By subscribeNo =By.xpath("//label[normalize-space()='No']//input[@name='newsletter']");
	
	private By agreeCheckBox =By.name("agree");
	private By continueButn = By.xpath("//input[@type='submit' and @value='Continue']");
	private By sussenssMesg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink=By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public boolean registerUser(String fName,String lName,String eId,String tel,String pwd, String subscribe ) {
		eleUtil.waitForElementVisible(firstName, AppConstants.SHORT_TIME_OUT).sendKeys(fName);
		eleUtil.doSendKeys(lastName, lName);
		eleUtil.doSendKeys(email, eId);
		eleUtil.doSendKeys(telephone, tel);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doSendKeys(confirmPassword, pwd);
		
		if(subscribe.equalsIgnoreCase("Yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButn);
		
		String susscessMesg= eleUtil.waitForElementVisible(this.sussenssMesg, AppConstants.MEDIUM_TIME_OUT).getText();
		System.out.println(susscessMesg);
		
		if(susscessMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
}
