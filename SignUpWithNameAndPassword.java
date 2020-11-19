package tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class SignUpWithNameAndPassword {
	static AppiumDriver<MobileElement> driver;
	String username = tests.TestData.getName();
	String email = tests.TestData.getEmail();
	String password = tests.TestData.getPassword();
	
	@BeforeClass
	public static void main(){
		
		//Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "52000695b44575bd"); //Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "9.0");
		caps.setCapability("automationName", "uiautomator2");
		caps.setCapability("appPackage", "org.stepic.droid");
		caps.setCapability("appActivity", "org.stepik.android.view.auth.ui.activity.SocialAuthActivity");
		caps.setCapability("noReset", "true");
		
		
		//Instantiate Appium Driver
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		
		MobileElement el = driver.findElementById("launchSignUpButton");
		el.click();
	}
	
	@Test
	public void signUpWithNameAndPassword() {
		MobileElement etName = driver.findElementById("firstNameField");
		etName.click();
		etName.sendKeys(username);
		MobileElement etPass = driver.findElementById("passwordField");
		etPass.click();
		etPass.sendKeys(password);
		MobileElement elSignUp = driver.findElementById("signUpButton");
		Assert.assertFalse("Button is active!", elSignUp.isEnabled());
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
	    driver.quit();
	}
}
