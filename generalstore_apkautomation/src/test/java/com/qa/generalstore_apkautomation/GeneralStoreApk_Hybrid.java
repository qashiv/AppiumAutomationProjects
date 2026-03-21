package com.qa.generalstore_apkautomation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.qa.generalstore.pageobjects.android.CartPage;
import org.qa.generalstore.pageobjects.android.GoogleHomePage;
import org.qa.generalstore.pageobjects.android.ProductCatalogue;
import org.qa.generalstore.testutils.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GeneralStoreApk_Hybrid extends AndroidBaseTest {

	@Test(dataProvider = "getData", groups = { "Smoke" })
	public void FillForm(HashMap<String, String> input) throws InterruptedException {
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		ProductCatalogue productCatalogue = formPage.submitForm();
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCartPage();

		double totalSum = cartPage.getProductsSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();
		Thread.sleep(3000);
		GoogleHomePage homePage = new GoogleHomePage(driver);
		homePage.searchOnBrowser(input.get("search"));
	}
	

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")
				+ "//src//test//resources//eCommerce.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}
