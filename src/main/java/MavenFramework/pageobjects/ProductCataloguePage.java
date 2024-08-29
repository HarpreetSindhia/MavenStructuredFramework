package MavenFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MavenFramework.abstractcomponents.AbstractComponents;

public class ProductCataloguePage extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCataloguePage(WebDriver driver)
	{
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".col-sm-10"));
	
	//WebElement spinner = driver.findElement(By.css(".ng-animating"));
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By visibleElement = By.cssSelector("#toast-container");
	
	@FindBy(css=".col-sm-10")
	List<WebElement> products;
	
	public List<WebElement> getProductList()
	{
		waitForWebElementToAppear(products);
		return products;
	}
	
	
	@FindBy(css=".card-body button:last-of-type")
	WebElement addButton;
	
	
	
	public void getProductsAndAddToCart(List<String> targetProducts) {

	for(String targetProduct : targetProducts)
	{	
		WebElement product = getProductList().stream().filter(a -> a.findElement(By.tagName("b")).getText().equals(targetProduct)).findFirst().orElse(null);
			
		if(product!=null)
		{
			WebElement addButton = product.findElement(By.cssSelector(".card-body button:last-of-type"));
			addButton.click();
			System.out.println("Clicked on product: " + targetProduct);
			waitForTheElementToAppear(visibleElement);
			waitForTheWebElementToDisppear(spinner);
			
		}
		
		}
	}
	
	}

	
	
	
	

	




