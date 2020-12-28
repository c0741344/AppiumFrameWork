package learning.Appium;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utilities extends Setup
{
	static AndroidDriver<AndroidElement> androidDriver;
	
	public Utilities(AndroidDriver<AndroidElement> androidDriver) 
	{
		this.androidDriver = androidDriver;		
	}
	public void scrollToText(String text)
	{
		androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
	}
	public static void takeScreenShot(String testName) throws IOException
	{
		File screenshotFile = ((TakesScreenshot)androidDriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(System.getProperty("user.dir")+"/FailureScreenShots/"+testName+".png"));
	}

}
