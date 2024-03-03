package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. private By locators - page locators

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logoutLink = By.linkText("Logout");
	private By registerLink= By.linkText("Register");

	// 2. public page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. public page actions/methods

	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		System.out.println("Login page title is: " + title);
		return title;
	}

	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_TIME_OUT);
		System.out.println("Login page URL is: " + url);
		return url;
	}

	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.MEDIUM_TIME_OUT).isDisplayed();
	}

	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("App credientials are: " + username + " : " + pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.SHORT_TIME_OUT).sendKeys(username);
		eleUtil.waitForElementVisible(password, AppConstants.SHORT_TIME_OUT).sendKeys(pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public void doClickLogout() {
		eleUtil.clickElementWhenReady(logoutLink, AppConstants.MEDIUM_TIME_OUT);
		
	}
	
	public RegisterPage doClickRegister() {
		eleUtil.clickElementWhenReady(registerLink, AppConstants.MEDIUM_TIME_OUT);
		return new RegisterPage(driver);
	}

	

}
