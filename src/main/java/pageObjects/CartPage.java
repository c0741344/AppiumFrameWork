package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage 
{
	public CartPage(AndroidDriver<AndroidElement> androidDriver) 
	{
		PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);		
	}
	
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	public List<WebElement> cartProductPricesElements;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement totalAmmoutElement;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	public WebElement termsButtonElement;
	
	@AndroidFindBy(id = "android:id/message")
	public WebElement termsMessagElement;
	
	@AndroidFindBy(id = "android:id/button1")
	public WebElement closeTermButtonElement;
	
	@AndroidFindBy(className = "android.widget.CheckBox")
	public WebElement checkBoxElement;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	public WebElement visitWebButtonElement;

}
