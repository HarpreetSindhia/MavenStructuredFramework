package MavenFramework.abstractcomponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MavenFramework.pageobjects.CheckoutPage;
import MavenFramework.pageobjects.OrdersPage;

public class AbstractComponents {
	
	WebDriver driver;
	Actions actions;
	
	public AbstractComponents(WebDriver driver)
	{
		this.driver = driver;
		this.actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	//All the reusable methods will be placed here .
	
	
	//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
	//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-tns-c31-1")));
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-trigger-flyInOut")));
	
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".col-sm-10"));
	
	//WebElement addToCart = product.findElement(By.cssSelector(".card-body button:last-of-type"));
	
	public void waitForTheWebElementToDisppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.invisibilityOf(findBy));
	}
	
	
	public void waitForTheWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	public void waitForWebElementToAppear(List<WebElement> findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfAllElements(findBy));
	}
	
	
	public void waitForTheElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	
	public void waitForTheElementToDisappear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	//WebElement cartIcon = driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartIcon;
	
	
	public CheckoutPage goToCart()
	{
		waitForTheWebElementToAppear(cartIcon);
		cartIcon.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}


	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement ordersTab;
	
	public OrdersPage goToOrdersTab()
	{
		actions.moveToElement(ordersTab).click().build().perform();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
}
