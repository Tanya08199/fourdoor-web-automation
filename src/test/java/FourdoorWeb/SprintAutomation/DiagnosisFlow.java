package FourdoorWeb.SprintAutomation;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.WebDriverManger.WebDriverSetup;

import java.util.List;

import static utils.WebDriverManger.WebDriverUtils.*;

public class DiagnosisFlow {

    private WebDriver driver = WebDriverSetup.getDriver();

    @Given("Verify the user is on home page")
    public void verifyHomePage()
    {
        String expectedTitle = "Expert Car Service & Repair in Gurugram | Fourdoor";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Title mismatch");

    }

    @When("The user click on the service and maintenance super category")
    public void clickOnTheSuperCategory() throws InterruptedException {
        WebElement superCat = driver.findElement(By.xpath("//div[contains(@class,\"lg:shadow-landingCard\")]//h3[contains(text(),'Service & Maintenance')]"));

        waitForElementVisible(driver,superCat);
        superCat.click();
        Thread.sleep(3000);
    }

    @Then("Verify the carousal image on the listing page")
    public void verifyCarousalImage() throws InterruptedException {
        WebElement listingBanner = driver.findElement(By.xpath("//div[contains(@class,\"slick-slide slick-active slick-current\")]//img[@alt='service']"));
        waitForElementVisible(driver,listingBanner);
        Assert.assertTrue(listingBanner.isDisplayed(),"Image not displayed");
        Thread.sleep(3000);
    }

    @When("The user move to the detail page")
    public void moveToDetailPage() throws InterruptedException {
        WebElement bookNow = driver.findElement(By.xpath("//div[contains(@class,\"slick-slide slick-active slick-current\")]//button"));
        waitForElementVisible(driver,bookNow);
        waitForClickable(driver,bookNow);

        if (bookNow.isDisplayed() && bookNow.isEnabled()) {
            System.out.println("The button is clickable.");
            bookNow.click();
            Thread.sleep(3000);
        } else {
            System.out.println("The button is not clickable.");
        }

    }

    @Then("Verify the diagnosis details page")
    public void verifyDetailPage() throws InterruptedException {
        WebElement detailDiv = driver.findElement(By.xpath("//div[contains(@class,\"mx-auto flex w-full items-start gap-x-6 \")]"));
        waitForElementVisible(driver,detailDiv);

        WebElement bannerImage = detailDiv.findElement(By.xpath("//img[@alt='car']"));
        Assert.assertTrue(bannerImage.isDisplayed(),"Banner image not displayed");

        WebElement howItsWorkDiv = detailDiv.findElement(By.xpath("//div[contains(@class,\"py-4 pb-0 mx-4\")]"));

        String howItsWork = howItsWorkDiv.findElement(By.tagName("h5")).getText();
        Assert.assertEquals(howItsWork,"How it works?","H5 tag mismatch");

        List<WebElement> howItsWorkData = howItsWorkDiv.findElements(By.xpath("//div[contains(@class,\"flex items-start mb-3\")]"));

        for (WebElement data : howItsWorkData)
        {
            WebElement h6 = data.findElement(By.tagName("h6"));
            System.out.println("The h6 text is " + h6.getText());

            WebElement p = data.findElement(By.tagName("p"));
            System.out.println("The p text is  "+p.getText());

            WebElement img = data.findElement(By.tagName("img"));
            Assert.assertTrue(img.isDisplayed(),"Image not displayed");
        }

        List<WebElement> rightDivs = driver.findElements(By.xpath("//div[contains(@class,\"py-4 pb-0 lg:p-4 mx-4 lg:border\")]"));
       int i =1;
       for (WebElement rightDiv : rightDivs)
       {
           if(i==2)
           {
               WebElement h1 = rightDiv.findElement(By.tagName("h1"));
               System.out.println(h1.getText());

               WebElement p = rightDiv.findElement(By.tagName("p"));
               System.out.println(p.getText());

               WebElement h5 = rightDiv.findElement(By.tagName("h5"));
               System.out.println(h5.getText());

               List<WebElement> spanDiv = rightDiv.findElements(By.xpath(".//div[contains(@class,\"flex flex-wrap\")]//span"));

               for (WebElement span : spanDiv)
               {
                   System.out.println(span.getText());
               }


           }
           i++;
       }

    }




}
