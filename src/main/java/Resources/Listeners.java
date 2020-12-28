package Resources;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import learning.Appium.Utilities;

public class Listeners implements ITestListener
{
	@Override
	public void onTestFailure(ITestResult result) 
	{
		// result.getName() will get you the name of the test that failed also xml file needs some changes to recognize the listener file
		// Also the suite has to be run from testng.xml file only to activate listener file
		System.out.println("The failed test was : " +result.getName());
		try 
		{
			Utilities.takeScreenShot(result.getName());
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
