package org.qa.generalstore.testutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.qa.generalstore.pageobjects.android.FormPage;
import org.qa.generalstore.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AndroidBaseTest extends AppiumUtils {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass(alwaysRun = true)
	public void setupAppium() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties");
		prop.load(fis);
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress"): prop.getProperty("ipAddress");
		System.out.println(ipAddress);

		// String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");

		service = startAppiumServer(ipAddress, Integer.parseInt(port));

		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setDeviceName(prop.getProperty("AndroidDeviceNames"));
		options.setChromedriverExecutable(System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
		options.setApp(System.getProperty("user.dir") + "\\src\\test\\resources\\General-Store.apk");

		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
	}
	
//	@BeforeMethod(alwaysRun = true)
//	public void preSetup() {
//		formPage.setActivity();
//	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
