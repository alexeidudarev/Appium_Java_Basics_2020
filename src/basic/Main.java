package basic;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Main {
	private static final String APP = "C:\\android projects\\app-release.apk";
	private static final String APPIUM = "http://127.0.0.1:4723/wd/hub";
    private static AndroidDriver<WebElement> driver;
    private static DesiredCapabilities dc;
    private static URL url;
    public static void main(String[] args) throws MalformedURLException {
		//installAppOnEmulator();
		//installAppOnDevice();
    	try {
			openCalculatorApp();
			//Logger.log( Level.FINE, "processing {0} entries in loop", result );

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void installAppOnDevice() throws MalformedURLException {
		dc  = getdeviceCapabilities();
		dc.setCapability(MobileCapabilityType.APP,APP);
		url = new URL(APPIUM);
		driver = new AndroidDriver<WebElement>(url, dc);
		driver.quit();

	}
	public static void installAppOnEmulator() throws MalformedURLException {
		//those capabilities are common to android emulator

		DesiredCapabilities dc  = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"Appium");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,"5.0.2");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");
		dc.setCapability(MobileCapabilityType.APP,"C:\\android projects\\app-release.apk");
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(url, dc);
	}
	
	public static String openCalculatorApp() throws MalformedURLException, InterruptedException {
		//those capabilities are common to android device built in apps

		dc = getdeviceCapabilities();
		dc.setCapability("appPackage","com.meizu.flyme.calculator");
		dc.setCapability("appActivity","com.meizu.flyme.calculator.Calculator");
		url = new URL(APPIUM);
		driver = new AndroidDriver<WebElement>(url, dc);
		Thread.sleep(5000);
		driver.findElementById("com.meizu.flyme.calculator:id/digit1").click();
		driver.findElementById("com.meizu.flyme.calculator:id/plus").click();
		driver.findElementById("com.meizu.flyme.calculator:id/digit3").click();
		driver.findElementById("com.meizu.flyme.calculator:id/eq").click();
		WebElement el = driver.findElementById("com.meizu.flyme.calculator:id/edit_text");
		el.click();
		String result = el.getText();
		Thread.sleep(6000);

		System.out.println("the result is :"+result);
		if(result.equals("4")) {
			
			System.out.println("test passed");
		}else {
			System.out.println("test failed");

		}
		driver.quit();
		return result;
	}
	public static DesiredCapabilities getdeviceCapabilities() {
		DesiredCapabilities dc  = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"Appium");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,"5.1");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME,"Android");
		return dc;
	}
}
