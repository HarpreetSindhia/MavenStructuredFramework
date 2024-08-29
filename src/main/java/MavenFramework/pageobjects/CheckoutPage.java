package MavenFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MavenFramework.abstractcomponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	
	WebDriver driver;
	Actions actions;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		this.actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	public List<WebElement> getCartList()
	{
		return cartProducts;
	}
	
	public boolean matchCartProductsToList(List<String> targetProducts)
	{
	
		for(String targetProduct : targetProducts)
		{
	//Return statement cannot be used inside a Lambda expression
		boolean match = getCartList().stream().anyMatch(p -> p.getText().equalsIgnoreCase(targetProduct));
		
		if(!match)
		{
			return false;
		}
	}
	
	return true;
	
}

	//Actions checkOut = actions.moveToElement(driver.findElement(By.cssSelector(".totalRow button:first-of-type"))).click().build().perform();
	
	@FindBy(css=".totalRow button:first-of-type")
	WebElement checkOut;
	
	public ConfirmationPage clickOnCheckout()
	{
		actions.moveToElement(checkOut).click().build().perform();
		ConfirmationPage confirmation = new ConfirmationPage(driver);
		return confirmation;
	}
	
}
