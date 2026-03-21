package com.qa.generalstore_apkautomation;

import org.openqa.selenium.By;
import org.qa.generalstore.testutils.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class GeneralStoreApk extends AndroidBaseTest{
	
	@Test
	public void fillDetails_ErrorValidation() throws InterruptedException {
		
	//	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Shiv Babu");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"American Samoa\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='American Samoa']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(toastMessage,"Please - your name");
			
	}
	

	@Test
	public void fillDetails_PositiveFlow() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Shiv Babu");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"American Samoa\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='American Samoa']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
	}
	
}
