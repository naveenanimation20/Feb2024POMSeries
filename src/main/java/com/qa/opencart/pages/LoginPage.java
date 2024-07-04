package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. Page Objects: By Locators:

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// 2. public const.. of the page:
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. public page actions/methods
	@Step("getting login page title value...")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("login page title : " + title);
		return title;
	}

	@Step("getting login page URL...")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		System.out.println("login page url : " + url);
		return url;
	}

	@Step("getting the state of forgot pwd link exist...")
	public boolean checkForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}

	@Step("login to application with username: {0} and password: {1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("user creds : " + username + ":" + pwd);
		eleUtil.doSendKeys(emailId, username, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);

	}

	@Step("navigating to register page...")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink, TimeUtil.DEFAULT_TIME);
		return new RegisterPage(driver);
	}

}
