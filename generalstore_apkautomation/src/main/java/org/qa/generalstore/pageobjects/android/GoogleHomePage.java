package org.qa.generalstore.pageobjects.android;

import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qa.generalstore.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class GoogleHomePage extends AndroidActions {

	AndroidDriver driver;
	
	@FindBy(name="q")
	private WebElement searchBox;
	

	
	public GoogleHomePage(AndroidDriver driver)
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void searchOnBrowser(String enterToSearch) {
		/// switch to browser
		Set<String> contexts = driver.getContextHandles();
		for(String context : contexts) {
			if(context.contains("WEBVIEW")) {
				driver.context(context);
				break;
			}
		}
		
		searchBox.sendKeys(enterToSearch);
		searchBox.sendKeys(Keys.ENTER);
		// validate search result
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		// now, again switch back to native
		driver.context("NATIVE_APP");
	}
	
}
