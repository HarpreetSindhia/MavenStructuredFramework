package MavenFramework.pageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MavenFramework.abstractcomponents.AbstractComponents;

public class FilterProductPage extends AbstractComponents {

	WebDriver driver;
	Actions actions;
	
	public FilterProductPage(WebDriver driver)
	{
		super(driver);
		this.driver =  driver;
		this.actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	//actions.moveToElement(driver.findElement(By.cssSelector("#sidebar form div:nth-child(3) input"))).click().build().perform();
	
	@FindBy(css="#sidebar form div:nth-child(3) input")
	WebElement searchByFilter;
	
	public void searchProductByFilter()
	{
		actions.moveToElement(searchByFilter).click().build().perform();
	}
}
