package MavenFramework.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MavenFramework.pageobjects.CheckoutPage;
import MavenFramework.pageobjects.ConfirmationPage;
import MavenFramework.pageobjects.FilterProductPage;
import MavenFramework.pageobjects.OrdersPage;
import MavenFramework.pageobjects.ProductCataloguePage;
import MavenFramework.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	//List<String> targetProducts = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO");

	String inputName = "aus";
	String countryName = "Australia";

	@Test(dataProvider = "getData", groups = { "PurchaseOrder" })
	public void submitOrder(HashMap<String, Object> input) throws IOException {

		{
			ProductCataloguePage cataloguePage = landingPage.loginApplication((String)input.get("email"),((String)input.get("password")));
			cataloguePage.getProductList();
			cataloguePage.getProductsAndAddToCart((List<String>)input.get("targetProducts"));
			CheckoutPage checkoutPage = cataloguePage.goToCart();
			checkoutPage.matchCartProductsToList((List<String>)input.get("targetProducts"));
			Assert.assertTrue(true);
			ConfirmationPage confirmation = checkoutPage.clickOnCheckout();
			confirmation.inputCountryName(inputName);
			confirmation.getCountryList();
			confirmation.selectCountryByName(countryName);
			confirmation.placeOrder();
			String confirmMessage = confirmation.confirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
			OrdersPage ordersPage = confirmation.goToOrdersTab();
			ordersPage.matchOrderedProductsToList((List<String>)input.get("targetProducts"));
			Assert.assertTrue(true);
			FilterProductPage filterProduct = ordersPage.clickOnHomeTab();
			filterProduct.searchProductByFilter();
		}
	}

	@DataProvider
	public Object[][] getData() throws IOException 
	{
		
		List<HashMap<String,Object>> data = getJsonDataToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\MavenFramework\\data\\DataReader.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	
	
	
}	
		
		/*@DataProvider
		public Object[][] getData()
		{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", "harrysindhia@gmail.com");
		map.put("password", "harry@Code5");
		map.put("targetProducts", Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO"));

		HashMap<String, Object> map11 = new HashMap<String, Object>();
		map11.put("email", "harrycoder@gmail.com");
		map11.put("password", "harryCode@5");
		map11.put("targetProducts", Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO"));

		return new Object[][] { { map }, { map11 } };
		
		}*/
				



// @DataProvider

/*
 * public Object[][] getData() { return new Object[][]
 * {{"harrysindhia@gmail.com","harry@Code5",targetProducts},{
 * "harrycoder@gmail.com","harryCode@5",targetProducts}}; }
 */
