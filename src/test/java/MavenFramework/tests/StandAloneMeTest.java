package MavenFramework.tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class StandAloneMeTest {

	List<String> targetProducts = Arrays.asList("ZARA COAT 3","ADIDAS ORIGINAL","IPHONE 13 PRO");
	
	@Test
	public void submitCart(){
		{
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
			driver.get("https://rahulshettyacademy.com/client/");
			driver.findElement(By.cssSelector("#userEmail")).sendKeys("harrysindhia@gmail.com");
			driver.findElement(By.cssSelector("#userPassword")).sendKeys("harry@Code5");
			driver.findElement(By.cssSelector("input[value='Login']")).click();
			List<WebElement> products = driver.findElements(By.cssSelector(".col-sm-10"));
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
			//Loop through the target products and add them to the cart.
			for(String targetProduct : targetProducts)
			{
				
				WebElement product = products.stream().filter(a -> a.findElement(By.tagName("b")).getText().equals(targetProduct)).findFirst().orElse(null);
				
				if(product!=null)
				{
					
					product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-tns-c31-1")));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-trigger-flyInOut")));
					
				}
				
			}
			
			
			driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
			
			//Validation to check if items displayed in the cart matches your list (targetProduct)
			
			List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
			for(String targetProduct : targetProducts)
			{
				
				Boolean match = cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(targetProduct));
				
				Assert.assertTrue(match);
			}
			
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(By.cssSelector(".totalRow button:first-of-type"))).click().build().perform();
			
			driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("aus");
			List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results button"));
			WebElement myCountry = countries.stream().filter(a -> a.getText().equalsIgnoreCase("Australia")).findFirst().orElse(null);
			myCountry.click();
			driver.findElement(By.cssSelector(".action__submit")).click();
			
			String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
			
			actions.moveToElement(driver.findElement(By.cssSelector("button[routerlink='/dashboard/myorders']"))).click().build().perform();
			List<WebElement> orderedProducts = driver.findElements(By.cssSelector(".table tbody td:nth-child(3)"));
			for(String targetProduct : targetProducts)
			{	
				Boolean match = orderedProducts.stream().anyMatch(n -> n.getText().equalsIgnoreCase(targetProduct));
				
				Assert.assertTrue(match);
				
			}
			
			actions.moveToElement(driver.findElement(By.cssSelector("button[routerlink='/dashboard/']"))).click().build().perform();
			
			//actions.moveToElement(driver.findElement(By.cssSelector("#sidebar form div input"))).click().keyDown(Keys.SHIFT).sendKeys("zara").keyDown(Keys.ENTER).click().build().perform();
			
			//actions.moveToElement(driver.findElement(By.cssSelector("#sidebar form div:nth-child(2) input"))).click().sendKeys("31500");
			
			//actions.moveToElement(driver.findElement(By.cssSelector("#sidebar form div:nth-child(2) div div:nth-child(2) input"))).click().sendKeys("35500").keyDown(Keys.ENTER).click().build().perform();
			
			
			actions.moveToElement(driver.findElement(By.cssSelector("#sidebar form div:nth-child(3) input"))).click().build().perform();
			actions.moveToElement(driver.findElement(By.cssSelector("#sidebar form div:nth-child(4) div:nth-child(4)"))).click().build().perform();
			actions.moveToElement(driver.findElement(By.cssSelector("#sidebar form div:nth-child(4) div:nth-child(5)"))).click().build().perform();
			
			driver.quit();
			
		}
	}
	
}
