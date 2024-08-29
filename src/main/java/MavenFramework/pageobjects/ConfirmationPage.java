package MavenFramework.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MavenFramework.abstractcomponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("aus");
	
	//Page Factory
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement inputCountry;
	
	public void inputCountryName(String inputName)
	{
		inputCountry.sendKeys(inputName);
	}
	
	//List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results button"));
	
	@FindBy(css=".ta-results button")
	List<WebElement> countries;
	
	public List<WebElement> getCountryList()
	{
		
		waitForWebElementToAppear(countries);
		return countries;
	}
	
	
	//WebElement myCountry = countries.stream().filter(a -> a.getText().equalsIgnoreCase("Australia")).findFirst().orElse(null);
		//myCountry.click();

	public void selectCountryByName(String countryName)
	{
		
		WebElement myCountry = countries.stream().filter(a -> a.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
		myCountry.click();
	}
	
	
	
	
	//driver.findElement(By.cssSelector(".action__submit")).click();
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	
	public void placeOrder()
	{
		placeOrder.click();
	}
	
	//String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	public String confirmationMessage()
	{
		return confirmMessage.getText();
	}
}
