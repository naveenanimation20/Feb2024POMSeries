package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 200: Design open cart accounts page")
@Story("US 201: Accounts page features")
@Feature("F51: Feature accounts page")
public class AccountsPageTest extends BaseTest {

    @BeforeClass
    @Description("Setup method to log in and navigate to the accounts page.")
    public void accSetUp() {
        accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Description("Test Case: Verify the title of the accounts page. This test ensures that the accounts page title matches the expected value defined in AppConstants.")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 1)
    public void accPageTitleTest() {
        Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
    }

    @Description("Test Case: Verify the URL of the accounts page. This test checks if the accounts page URL contains the expected URL fraction defined in AppConstants.")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void accPageURLTest() {
        Assert.assertTrue(accPage.getAccPageURL().contains(AppConstants.ACC_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);
    }

    @Description("Test Case: Verify the headers on the accounts page. This test checks that the headers on the accounts page match the expected headers defined in AppConstants.")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 3)
    public void accPageHeadersTest() {
        List<String> accPageHeadersList = accPage.getAccPageHeaders();
        Assert.assertEquals(accPageHeadersList, AppConstants.ACC_PAGE_HEADERS_LIST, AppError.LIST_IS_NOT_MATCHED);
    }

    @DataProvider
    public Object[][] getSearchData() {
        return new Object[][] {
            {"macbook", 3},
            {"imac", 1},
            {"samsung", 2},
            {"Airtel", 0}
        };
    }

    @Description("Test Case: Verify the search functionality on the accounts page. This test uses a data provider to check multiple search keywords and their expected result counts.")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "getSearchData", priority = 4)
    public void searchTest(String searchKey, int resultsCount) {
        searchResultsPage = accPage.doSearch(searchKey);
        Assert.assertEquals(searchResultsPage.getSearchResultsCount(), resultsCount, AppError.RESULTS_COUNT_MISMATCHED);
    }
}
