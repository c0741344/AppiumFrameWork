package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage 
{
	public FormPage(AndroidDriver<AndroidElement> androidDriver) 
	{
		PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);		
	}
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	public WebElement nameFieldElement;
	
	@AndroidFindBy(xpath = "//*[@text='Female']")
	public WebElement femaleCheckBoxElement;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	public WebElement countrySelectorElement;
	
//	@AndroidBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(text('Aruba'));")
//	public WebElement scrollToAruba;
	
	@AndroidFindBy(xpath = "//*[@text='Aruba']")
	public WebElement arubaCountryElement;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	public WebElement letsShopButtonElement;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='General Store']")
	public WebElement headerElement;
	
	}
