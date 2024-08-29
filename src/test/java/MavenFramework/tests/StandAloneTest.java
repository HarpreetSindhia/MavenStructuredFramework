package MavenFramework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class StandAloneTest {
	
	String productName = "ADIDAS ORIGINAL";
	
	@Test
	public void submitCartTest() {
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("harrysindhia@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("harry@Code5");
		driver.findElement(By.cssSelector("#login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement myProduct = products.stream().filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		myProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-trigger-fadeIn")));
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(a -> a.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("tha");
		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-item"));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ta-results button")));
		WebElement myCountry = countries.stream().filter(a -> a.getText().equalsIgnoreCase("Thailand")).findFirst().orElse(null);
		myCountry.click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String orderPlaceMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(orderPlaceMessage);
		Assert.assertTrue(orderPlaceMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.findElement(By.cssSelector("label[routerlink='/dashboard/myorders']")).click();
		List<WebElement> orderedProducts = driver.findElements(By.cssSelector(".table-hover td:nth-child(3)"));
		Boolean matchFound = orderedProducts.stream().anyMatch(a -> a.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(matchFound);
		
		
		
		
		
		
		
		/*String orderID = driver.findElement(By.cssSelector("label[class='ng-star-inserted']")).getText();
		System.out.println(orderID);
		driver.findElement(By.cssSelector("label[routerlink='/dashboard/myorders']")).click();
		boolean cartOrder =driver.findElement(By.cssSelector(".table-bordered tbody tr th")).getText().contains(orderID);
		Assert.assertTrue(cartOrder);*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
