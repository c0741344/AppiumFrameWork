package learning.Appium;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.Set;

import org.openqa.selenium.Keys;

import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;		// Remeber this
import static io.appium.java_client.touch.offset.ElementOption.element;				// Remeber this	
import static io.appium.java_client.touch.TapOptions.tapOptions;					// Remeber this
import static java.time.Duration.ofSeconds;											// Remeber this
import io.appium.java_client.android.nativekey.AndroidKey;							// Remeber this
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.CartPage;
import pageObjects.FormPage;
import pageObjects.ProductsListPage;
import pageObjects.WebPage;
public class EcommereTest extends Setup
{
	FormPage formPage;
	ProductsListPage productListPage;
	CartPage cartPage;
	WebPage webPage;
	Utilities utilities;
	@BeforeClass
	public void requirements()
	{
		this.formPage = new FormPage(androidDriver);
		this.productListPage = new ProductsListPage(androidDriver);
		this.cartPage = new CartPage(androidDriver);
		this.webPage = new WebPage(androidDriver);
		this.utilities = new Utilities(androidDriver);
	}
	
	
	@Test
	public void tc1() throws InterruptedException
	{	
		landerPage("Aruba");
	}
	@Test
	public void tc2() throws InterruptedException
	{
		addItem("Air Jordan 4 Retro");
		addItem("PG 3");
		checkCart();
	}
	@Test
	public void tc3()
	{
		handleTouchActions();
	}
	@Test
	public void tc4() throws InterruptedException
	{
		changeContextToWeb();
		doYourStuffOnWeb();
		changeContextToNative();
		doYourStuffOnNative();
	}
	
	
	public void landerPage(String countryName)
	{
		formPage.nameFieldElement.sendKeys("Hello");
		formPage.femaleCheckBoxElement.click();
		formPage.countrySelectorElement.click();
		utilities.scrollToText(countryName);
		formPage.arubaCountryElement.click();
		formPage.letsShopButtonElement.click();
		productListPage.productListElement.isDisplayed();
	}
	
	
	public void addItem(String productName)
	{	
//		androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"com.androidsample.generalstore:id/productName\").text(\""+productName+"\"));");
		utilities.scrollToText(productName);
		
	
		int productsOnScreen = productListPage.productsOnScreen.size();
		for (int i = 0; i < productsOnScreen; i++)
		{
			String prodcutNameString = productListPage.productNameElements.get(i).getText();
			if (prodcutNameString.equalsIgnoreCase(productName)) 
			{
				productListPage.addToCartButtElements.get(i).click();
				break;
			}
		}
	}
	
	public void checkCart() throws InterruptedException 
	{	
		productListPage.cartButtonElement.click();
		Thread.sleep(5000); // Using because stale reference object exception is thrown
		double expectedValue = 0.0;
		for (int i = 0; i < cartPage.cartProductPricesElements.size(); i++) 
		{
			expectedValue = expectedValue + Double.parseDouble(cartPage.cartProductPricesElements.get(i).getText().replace("$", ""));
		}
		Double cartTotalString = Double.parseDouble(cartPage.totalAmmoutElement.getText().replace("$", ""));
		AssertJUnit.assertTrue("The sum total of the values is wrong", expectedValue==cartTotalString);
	}
	
	public void handleTouchActions()
	{
		TouchAction touchAction = new TouchAction(androidDriver);
		touchAction.longPress(longPressOptions().withElement(element(cartPage.termsButtonElement)).withDuration(ofSeconds(2))).release().perform();
		cartPage.termsMessagElement.isDisplayed();
		cartPage.closeTermButtonElement.click();
		touchAction.tap(tapOptions().withElement(element(cartPage.checkBoxElement))).perform();
		touchAction.tap(tapOptions().withElement(element(cartPage.visitWebButtonElement))).perform();	
	}
	
	public void changeContextToWeb() throws InterruptedException
	{
		Thread.sleep(5000); // Used to load the webView	
		printAvailableContext();
		androidDriver.context("WEBVIEW_com.androidsample.generalstore");	
	}
	
	public void printAvailableContext()
	{
		Set<String>availableContext = androidDriver.getContextHandles();
		for(String contString : availableContext)
		{
			System.out.println(contString);
		}
	}
	
	public void doYourStuffOnWeb() 
	{	
		webPage.googleSearchFiledElement.sendKeys("Vivek Batra"+Keys.ENTER);
		androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
	}
	
	public void changeContextToNative() throws InterruptedException
	{
		Thread.sleep(5000); // Used to load the Native App		
		printAvailableContext();
		androidDriver.context("NATIVE_APP");
	}
	
	public void doYourStuffOnNative() 
	{
		if (!formPage.headerElement.isDisplayed()) 
		{
			throw new VerifyError("Main Page's Header Text was not found");
		}
	}
}
