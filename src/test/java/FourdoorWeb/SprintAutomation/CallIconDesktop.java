package FourdoorWeb.SprintAutomation;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.WebDriverManger.WebDriverSetup;

import java.time.Duration;
import java.util.List;

import static utils.WebDriverManger.WebDriverUtils.click;
import static utils.WebDriverManger.WebDriverUtils.scrollToElement;

public class CallIconDesktop {

    private WebDriver driver = WebDriverSetup.getDriver();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @Given("User is on the home page fourddor")
    public void verifyHomePage()
    {
        String expectedTitle = "Expert Car Service & Repair in Gurugram | Fourdoor";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Title mismatch");
    }

    @Then("Check the call icon visibility")
    public void verifyIconVisibility()
    {
       WebElement callIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,\"bg-primaryOrange\")]")));
       Assert.assertTrue(callIcon.isDisplayed(),"Call icon not displayed");

    }
    @When("Click on the call icon")
    public void clickOnCallIcon()
    {

        WebElement callIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,\"bg-primaryOrange\")]")));
        callIcon.click();
        WebElement requestCallBack = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,\"bg-white rounded-3xl overflow-hidden undefined \")]//p")));
        Assert.assertTrue(requestCallBack.isDisplayed(),"Request call back not displayed");
    }

    @Given("User is on the listing page fourdoor")
    public void verifyListingPage()
    {
        driver.get("https://fourdoor-web-stage.fourdoor.dev/gurugram/car-service-and-maintenance");
        String expectedTitle = "Affordable Car Service & Maintenance in Gurugram | Fourdoor";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Title mismatch");
    }

    @Given("User is on the PDP page fourdoor")
    public void verifyPdpPage()
    {
        driver.get("https://fourdoor-web-qa.fourdoor.dev/gurugram/car-service-and-maintenance/essential-package?source=");
        String expectedTitle = "Essential Car Service Package in Gurugram | Fourdoor";
        String actualTile = driver.getTitle();
        Assert.assertEquals(actualTile,expectedTitle,"Title mismatch");
    }

    @Given("User click on the help section")
    public void clickHelpSection() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long viewportWidth = (Long) js.executeScript("return window.innerWidth;");

        if (viewportWidth > 768) {
            WebElement desktopElement = driver.findElement(By.xpath("//div[contains(@class,'header_headerWrap__zI_4G')]//div[@class='relative flex items-center z-50']"));
            scrollToElement(driver,desktopElement);
            click(driver,desktopElement);
        }
        WebElement hamBurger = driver.findElement(By.xpath("//div[contains(@class,\"px-4 py-6 lg:p-6\")]"));
        scrollToElement(driver,hamBurger);
        WebElement help = hamBurger.findElement(By.xpath("//div[contains(text(),'Help')]"));
        click(driver,help);
        Thread.sleep(3000);

    }

    @Then("User is on the help page")
    public void verifyHelpPage()
    {
        String expectedTitle = "Help & FAQs - Fourdoor Support Center";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Title mismatch");
    }


}
