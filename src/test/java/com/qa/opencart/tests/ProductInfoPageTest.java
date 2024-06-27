package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 300: Design open cart product info page")
@Story("US 301: Product information page features")
@Feature("F52: Feature product info page")
public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	@Description("Setup method to log in and navigate to the product information page.")
	public void productInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"imac", "iMac"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"samsung", "Samsung Galaxy Tab 10.1"},
			{"canon", "Canon EOS 5D"}
		};
	}

	@Description("Test Case: Verify the product header. This test ensures that the product header matches the expected product name.")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getProductData", priority = 1)
	public void productHeaderTest(String searchKey, String productName) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(), productName, AppError.HEADER_NOT_FOUND);
	}

	@DataProvider
	public Object[][] getProductImageData() {
		return new Object[][] {
			{"macbook", "MacBook Pro", 4},
			{"imac", "iMac", 3},
			{"samsung", "Samsung SyncMaster 941BW", 1},
			{"samsung", "Samsung Galaxy Tab 10.1", 7},
			{"canon", "Canon EOS 5D", 3}
		};
	}

	@DataProvider
	public Object[][] getProductImageSheetData() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_IMAGES_SHEET_NAME);
	}

	@Description("Test Case: Verify the number of product images. This test checks that the number of images for each product matches the expected count using data from a sheet.")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getProductImageSheetData", groups = {"sanity"}, priority = 2)
	public void productImagesCountTest(String searchKey, String productName, String imagesCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Integer.parseInt(imagesCount), AppError.IMAGES_COUNT_MISMATCHED);
	}

	@Description("Test Case: Verify the product information. This test checks multiple product information fields using soft assertions.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 3)
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productInfoMap = productInfoPage.getProductInfoMap();
		System.out.println("======product information========");
		System.out.println(productInfoMap);

		softAssert.assertEquals(productInfoMap.get("productname"), "MacBook Pro", AppError.HEADER_NOT_FOUND);
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple", AppError.BRAND_NOT_FOUND);
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18", AppError.CODE_NOT_FOUND);
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800", AppError.REWARD_POINTS_NOT_FOUND);
		softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock", AppError.AVAILABILITY_NOT_FOUND);
		softAssert.assertEquals(productInfoMap.get("productprice"), "$2,000.00", AppError.PRICE_NOT_FOUND);
		softAssert.assertEquals(productInfoMap.get("exTaxPrice"), "$2,000.00", AppError.EX_TAX_PRICE_NOT_FOUND);

		softAssert.assertAll();
	}
}
