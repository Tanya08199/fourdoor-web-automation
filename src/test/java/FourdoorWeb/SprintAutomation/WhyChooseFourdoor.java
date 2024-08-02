package FourdoorWeb.SprintAutomation;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.WebDriverManger.WebDriverSetup;
import static utils.WebDriverManger.WebDriverUtils.*;

import java.util.List;

public class WhyChooseFourdoor {

    private WebDriver driver = WebDriverSetup.getDriver();

    @When("^User clicks on the super category (.*)$")
    public void clickOnSuperCategory(String category)
    {
        WebElement superCategoryDiv = driver.findElement(By.xpath("//div[contains(@class,\"lg:shadow-landingCard\")]"));
        List<WebElement> superCategoryName = superCategoryDiv.findElements(By.tagName("a"));

        for (WebElement name: superCategoryName)
        {
            if(name.getText().equalsIgnoreCase(category))
            {
                click(driver,name);
                break;
            }
        }
    }

    @Then("User checks for \"Why choose Fourdoor\"")
    public void verifyWhyChooseFourdoor()
    {
        WebElement headlineText = driver.findElement(By.xpath("//button[@id='headlessui-disclosure-button-:ra:']//span"));
        Assert.assertTrue(headlineText.isDisplayed(),"Why choose fourddor is missing");
        System.out.println(headlineText.getText());
    }



}
