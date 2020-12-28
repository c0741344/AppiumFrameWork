package learning.Appium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Setup 
{	
	AndroidDriver<AndroidElement> androidDriver;
	public static AppiumDriverLocalService service;
	
	@BeforeClass
	public void setingUp() throws IOException  
	{
		// Will not work if the Driver folder does not have a chromeDriver auto downloaded. Look for other comments on this file
		startAppiumServer();			
		
		String currentDirectory = System.getProperty("user.dir");											// Getting current directory 
		FileInputStream fileInputStream = new FileInputStream(currentDirectory+"/src/main/java/learning/Appium/global.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, properties.get("DeviceName"));
		desiredCapabilities.setCapability("appPackage", properties.get("GeneralStoreAppPackage"));
		desiredCapabilities.setCapability("appActivity", properties.get("GeneralStoreAppActivity"));
		desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

		// To run emulator programmatically 
		desiredCapabilities.setCapability("avd",properties.get("DeviceName"));	
		
		// To Auto Download the chromeDriver in mentioned directory and use the one if already existed. But if version do not match run with "appium --allow-insecure chromedriver_autodownload"
		desiredCapabilities.setCapability("chromedriverExecutableDir", currentDirectory+"/drivers");		
		
		androidDriver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
		androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	
	@AfterClass
	public void tearDown() throws InterruptedException, IOException
	{
		Thread.sleep(5000);
		androidDriver.quit();
		service.stop();
		
	}
	
	public void startAppiumServer() throws ExecuteException, IOException 
	{
		boolean flag = checkIfServerIsRunning(4723);
		if (!flag) 
		{
			// Have to find way to programmatically execute "appium --allow-insecure chromedriver_autodownload"
			service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
		            .withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js")));
		    service.start();
		}
	}


	private static boolean checkIfServerIsRunning(int port) 
	{
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try 
		{
			serverSocket = new ServerSocket(port);
			serverSocket.close();
			
		} catch (IOException e) 
		{
			// If control comes here, then it means port is in use
			isServerRunning = true;
		}
		finally 
		{
			serverSocket = null;
		}
		return isServerRunning;
	}



}
