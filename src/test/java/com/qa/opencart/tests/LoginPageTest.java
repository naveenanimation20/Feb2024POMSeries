package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design Open Cart Application with Shopping Workflow")
@Story("US 101: Design login page for Open Cart Application")
@Feature("F50: Feature login page")

//@Listeners() - only report will work, retry is not working here
public class LoginPageTest extends BaseTest {

	@Description("checking login page title test ----")
	@Severity(SeverityLevel.MINOR)
	@Owner("Naveen Automation Labs")
	@Issue("Login-1234")
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}

	@Description("checking login page url ----")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Naveen Automation Labs")
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);
	}

	@Description("checking forgot password link exist on the login page ----")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Naveen Automation Labs")
	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.checkForgotPwdLinkExist(), AppError.ELEMENT_NOT_FOUND);
	}

	@Description("checking user is able to login to app successfully ----")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Naveen Automation Labs")
	@Test(priority = 4)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	
	@Test
	public void mytest() {
		Assert.assertEquals(true, false);
	}
	
	

}
