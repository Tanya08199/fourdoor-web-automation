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
import static utils.WebDriverManger.WebDriverUtils.*;

import java.time.Duration;
import java.util.List;

public class HeaderToBeFixed {

    private WebDriver driver = WebDriverSetup.getDriver();

    @Given("User is on the home page")
    public void onHomePage()
    {
        String expectedTitle = "Expert Car Service & Repair in Gurugram | Fourdoor";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Title mismatch");
    }

    @Given("User is on the listing page")
    public void listingPage()
    {
        driver.get("https://fourdoor-web-stage.fourdoor.dev/gurugram/car-service-and-maintenance");
        String expectedTitle = "Affordable Car Service & Maintenance in Gurugram | Fourdoor";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Title mismatch");

    }

    @Given("User is on the PDP page")
    public void pdpPage()
    {
        driver.get("https://fourdoor-web-qa.fourdoor.dev/gurugram/car-service-and-maintenance/essential-package?source=");
        String expectedTitle = "Essential Car Service Package in Gurugram | Fourdoor";
        String actualTile = driver.getTitle();
        Assert.assertEquals(actualTile,expectedTitle,"Title mismatch");
    }

    @When("User scroll the page")
    public void scrollDown()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    @Then("Verify the header is fixed")
    public void fixedPageHeader()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,\"header_headerWrap__zI_4G lg:relative z-20\")]")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long headerPositionBefore = (Long) js.executeScript("return arguments[0].getBoundingClientRect().top;", header);

        System.out.println(headerPositionBefore);

        js.executeScript("window.scrollBy(0,1000)");

        Long headerPositionAfter = (Long) js.executeScript("return arguments[0].getBoundingClientRect().top;", header);

        System.out.println(headerPositionAfter);

        Assert.assertEquals(headerPositionBefore,headerPositionAfter,"Not fixed");
    }

    @Then("User add the car through reg number")
    public void carAdd() throws InterruptedException {
        WebElement addCarButton = driver.findElement(By.xpath("//div[contains(@class,\"header_headerWrap__zI_4G lg:relative z-20\")]//span[contains(@class,\"cursor-pointer lg:hover:bg-bg-gray-300 rounded-xl\")]"));
        scrollToElement(driver,addCarButton);
        click(driver,addCarButton);
        Thread.sleep(3000);

        WebElement addCarDiv = driver.findElement(By.xpath("//div[contains(@class,\"flex flex-col h-full w-full relative\")]"));
        scrollToElement(driver,addCarDiv);

        WebElement regNumberInput = addCarDiv.findElement(By.id("carNumber"));
        waitForClickable(driver,regNumberInput);
        click(driver,regNumberInput);
        regNumberInput.sendKeys("HR26EP4782");


        WebElement addCarBtn = driver.findElement(By.xpath("//div[contains(@class,\"flex flex-col h-full w-full relative\")]//button[contains(text(),'Add car')]"));
        waitToEnable(driver,addCarBtn);
        click(driver,addCarBtn);

        WebElement loaderFetchCar = driver.findElement(By.xpath("//div[contains(@class,\"overflow-hidden shadow-xl transform transition-all\")]//p"));
        waitForInvisible(driver,loaderFetchCar);

        WebElement popupCarAdded = driver.findElement(By.xpath("//div[contains(@class,\"bg-white rounded-3xl overflow-hidden undefined\")]"));
        WebElement carImage = popupCarAdded.findElement(By.tagName("img"));
        waitForElementVisible(driver,carImage);
        System.out.println("The image source is "+ carImage.getAttribute("src"));

        WebElement carName = popupCarAdded.findElement(By.tagName("h5"));
        waitForElementVisible(driver,carName);
        System.out.println("The car name is "+ carName.getText());

        waitForInvisible(driver,popupCarAdded);

    }

    @Then("User add the package to cart")
    public void addToCart() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        List<WebElement> servicesDiv = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,\"px-0 pt-4 pb-2 lg:pt-10 lg:pb-8 w-full\")]")));
        for (WebElement service : servicesDiv)
        {

            List<WebElement> packageDiv = service.findElements(By.xpath(".//div[contains(@class,\"overflow-x-auto\")]"));

            for (WebElement pack : packageDiv)
            {
                List<WebElement> packageName = pack.findElements(By.xpath(".//div[contains(@class,\"shadow-landingCard\")]"));
                for(WebElement name : packageName)
                {
                    Thread.sleep(3000);
                    WebElement addToCart = name.findElement(By.tagName("button"));
                    click(driver,addToCart);
                    Thread.sleep(3000);
                    break;

                }
            }

        }


    }

    @Then("Verify cart header is fixed")
    public void checkCartHeader() throws InterruptedException {
        WebElement cartIcon = driver.findElement(By.xpath("//div[contains(@class,\"header_headerWrap__zI_4G lg:relative z-20\")]//span[contains(@class,\"cursor-pointer relative hidden lg:block lg:hover:bg-bg-gray-300 rounded-xl\")]"));
        click(driver,cartIcon);
        Thread.sleep(4000);

        WebElement headerDiv = driver.findElement(By.xpath("//div[contains(@class,\"flex flex-col sticky top-0 bg-white z-50\")]"));
        // WebElement discountStripDiv = driver.findElement(By.xpath("//div[contains(@class,\" sticky z-40 top-[60px] lg:top-[96px] bg-white lg:pt-7\")]"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long headerPositionBefore = (Long) js.executeScript("return arguments[0].getBoundingClientRect().top;", headerDiv);

        System.out.println(headerPositionBefore);

        js.executeScript("window.scrollBy(0,1000)");

        Long headerPositionAfter = (Long) js.executeScript("return arguments[0].getBoundingClientRect().top;", headerDiv);

        System.out.println(headerPositionAfter);

        Assert.assertEquals(headerPositionBefore,headerPositionAfter,"Not fixed");

    }



}
