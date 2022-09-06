package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import pageObjects.LandingPage;
import pageObjects.OffersPage;
import pageObjects.PageObjectManager;
import utils.TestContextSetup;

public class OfferPageStepDefinition {
//public WebDriver driver; 
//public String landingPageproductName;
	
	//Single responsibility principle
	//loosely coupled
	//factory design pattern
	public String offerPageProductName;
	TestContextSetup testContextSetup;
	PageObjectManager pageObjectManager;
	
	public OfferPageStepDefinition(TestContextSetup testContextSetup)
	{
		this.testContextSetup = testContextSetup;
	}
	
	@Then("^user searched for (.+) shortname in offers page to check if product exist with same name$")
	public void user_searched_for_same_shortname_in_offers_page_to_check_if_product_exist(String shortName) throws InterruptedException {
		//offer page->enter->grab text
		switchToOffersPage();
//		OffersPage offersPage = new OffersPage(testContextSetup.driver);
		OffersPage offersPage = testContextSetup.pageObjectManager.getOffersPage();
		offersPage.searchItem(shortName);
		Thread.sleep(2000);
		offerPageProductName = offersPage.getProductName();
		System.out.println(offerPageProductName);
		System.out.println("1. " +testContextSetup.landingPageProductName);
		System.out.println("2. " +offerPageProductName);
		
		Assert.assertEquals(testContextSetup.landingPageProductName, offerPageProductName );
		
	}
	
	public void switchToOffersPage()
	{
		//if switched to offers page -> skip the below code
//		if(testContextSetup.driver.getCurrentUrl().equalsIgnoreCase("testContextSetup.driver."))
//		pageObjectManager = new PageObjectManager(testContextSetup.driver);
		LandingPage landingPage = testContextSetup.pageObjectManager.getLandingPage();
		// LandingPage landingPage = new LandingPage(testContextSetup.driver);
		landingPage.selectTopDealsPage();
		testContextSetup.genericUtils.SwitchWindowToChild();
//		testContextSetup.driver.findElement(By.linkText("Top Deals")).click();
		
	}
}
