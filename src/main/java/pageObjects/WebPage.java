package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WebPage 
{
	public WebPage(AndroidDriver<AndroidElement> androidDriver) 
	{
		PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);		
	}
	
	
	@FindBy(name = "q")
	public WebElement googleSearchFiledElement;
}
