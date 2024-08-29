package MavenFramework.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import MavenFramework.abstractcomponents.AbstractComponents;

public class OrdersPage extends AbstractComponents{
	
	WebDriver driver;
	Actions actions;
	
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		this.actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	
	
	//List<WebElement> orderedProducts = driver.findElements(By.cssSelector(".table tbody td:nth-child(3)"));
	
	@FindBy(css=".table tbody td:nth-child(3)")
	List<WebElement> orderedProducts;
	
	public boolean matchOrderedProductsToList(List<String> targetProducts)
	{
	
		for(String targetProduct : targetProducts)
		{
		
	//targetProducts.forEach(targetProduct -> {
		
		boolean match1 = orderedProducts.stream().anyMatch(n -> n.getText().equalsIgnoreCase(targetProduct));
		
		if(!match1)
		{
			return false;
		}
	}
	
	return true;
}
	
	//actions.moveToElement(driver.findElement(By.cssSelector("button[routerlink='/dashboard/']"))).click().build().perform();
	
	@FindBy(css="button[routerlink='/dashboard/']")
	WebElement homePage;
	
	public FilterProductPage clickOnHomeTab()
	{
		actions.moveToElement(homePage).click().build().perform();
		FilterProductPage filterProduct = new FilterProductPage(driver);
		return filterProduct;
	}
}
