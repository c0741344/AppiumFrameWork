package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsListPage 
{
	public ProductsListPage(AndroidDriver<AndroidElement> androidDriver) 
	{
		PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);		
	}
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/rvProductList")
	public WebElement productListElement;
	
	@AndroidFindBy(className = "android.widget.RelativeLayout")
	public List<WebElement> productsOnScreen;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	public List<WebElement> productNameElements;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
	public List<WebElement> addToCartButtElements;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement cartButtonElement;	

}
