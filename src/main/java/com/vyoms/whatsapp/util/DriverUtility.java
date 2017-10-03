package com.vyoms.whatsapp.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vyoms.whatsapp.structure.BrowserSetupLocation;
import com.vyoms.whatsapp.structure.BrowserType;

public class DriverUtility {

	public static WebDriver getDriver(int browserType,String downloadFilePath) {
		WebDriver driver;
		if (browserType == BrowserType.PHANTOM_BROWSER) {
			System.setProperty("phantomjs.binary.path", BrowserSetupLocation.PHANTOM_LOCATION);
			DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
			//
			ArrayList<String> cliArgsCap = new ArrayList<String>();
			capabilities = DesiredCapabilities.phantomjs();
			cliArgsCap.add("--web-security=no");
			cliArgsCap.add("--ssl-protocol=any");
			cliArgsCap.add("--ignore-ssl-errors=yes");
			capabilities.setCapability("takesScreenshot", true);
			capabilities.setCapability(
			    PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
			capabilities.setCapability(
			    PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
			        new String[] { "--logLevel=2" });
			driver = new PhantomJSDriver(capabilities);
		} else {
			System.setProperty("webdriver.chrome.driver", BrowserSetupLocation.CHROME_LOCATION);			
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilePath);
			chromePrefs.put("chrome.switches", "--start-maximized");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOptions("prefs", chromePrefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			
			driver = new ChromeDriver(cap);
		}
		return driver;
	}
}
