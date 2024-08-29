package MavenFramework.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import MavenFramework.pageobjects.CheckoutPage;
import MavenFramework.pageobjects.ProductCataloguePage;
import MavenFramework.testcomponents.BaseTest;
import MavenFramework.testcomponents.Retry;


public class ErrorValidationTest extends BaseTest {

	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException{
		{
			
			landingPage.loginApplication("harrysindhia@gmail.com", "haarry@Code5");
			Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
			
		}
	}
	
	@Test
	public void ProductErrorValidation() throws IOException{
		{
			
			List<String> targetProducts = Arrays.asList("ZARA COAT 3","ADIDAS ORIGINAL","IPHONE 14 PRO");
			
			ProductCataloguePage cataloguePage = landingPage.loginApplication("harrycoder@gmail.com", "harryCode@5");
			cataloguePage.getProductsAndAddToCart(targetProducts);
			CheckoutPage checkoutPage = cataloguePage.goToCart();
			
			checkoutPage.matchCartProductsToList(targetProducts);
			Assert.assertFalse(false);
			
		}
	}
	
	
}
