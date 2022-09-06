package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import utils.TestContextSetup;

public class LandingPageStepDefinition {
public WebDriver driver; 
public String landingPageproductName;
TestContextSetup testContextSetup;
LandingPage landingPage;
//Spring framework, EJB


public LandingPageStepDefinition(TestContextSetup testContextSetup)
{
	this.testContextSetup = testContextSetup;
	this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
}

	@Given("User is on GreenKart Landing page")
	public void user_is_on_green_kart_landing_page() {

		Assert.assertTrue(landingPage.getTitleLandingPage().contains("GreenKart"));
	}
	
	@When("^user searched with Shortname (.+) and extracted actual name  of product$")
	public void user_searched_with_shortname_and_extracted_actual_name_of_product(String shortName) throws InterruptedException {
		
		landingPage.searchItem(shortName);
		
//		testContextSetup.driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortName);
		Thread.sleep(2000);
		testContextSetup.landingPageProductName = landingPage.getProductName().split("-")[0].trim();
	    System.out.println(testContextSetup.landingPageProductName+ " is extracted fom home page");
	}
	
	@When("Added {string} items of the selected product to cart")
	public void Added_items_product(String quantity)
	{
		landingPage.incrementQuantity(Integer.parseInt(quantity));
		landingPage.addToCart();
	}
	
}
