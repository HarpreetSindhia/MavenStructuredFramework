package MavenFramework.pageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MavenFramework.abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
		
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory - Defined webelements using Page Factory.
	
	//WebElement userEmail = driver.findElement(By.cssSelector("#userEmail"));
	
	@FindBy(css="#userEmail")
	WebElement userEmail;
	
	//WebElement userPassword = driver.findElement(By.cssSelector("#userPassword"));
	
	@FindBy(css="#userPassword")
	WebElement userPassword;
	
	//WebElement submit = driver.findElement(By.cssSelector("input[value='Login']"));
	
	@FindBy(css="input[value='Login']")
	WebElement submit;
	
	//Create  method to write the corresponding actions
	

	public void goToApplication()
	{
	
		driver.get("https://rahulshettyacademy.com/client/");
		
	}

	
	public ProductCataloguePage loginApplication(String email , String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCataloguePage cataloguePage = new ProductCataloguePage(driver);
		return cataloguePage;
	}
	
	//driver.findElement(By.cssSelector("div[class*='toast-error']");
	
	//Page Factory
	@FindBy(css="div[class*='toast-error']")
	WebElement errorMessage;
	
	public String getErrorMessage()
	{
		
		return errorMessage.getText();
	}


	public ProductCataloguePage loginApplication(Object object, Object object2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
