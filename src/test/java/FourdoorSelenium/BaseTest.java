package FourdoorSelenium;

<<<<<<< HEAD
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.function.Function;

=======
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
>>>>>>> anshuman-super-api
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.WebDriverManger.WebDriverSetup;

public class BaseTest {

    protected static WebDriver driver;
    private static WebDriverWait wait;

<<<<<<< HEAD
    @BeforeMethod
    public void setUp() {
=======
    @BeforeClass
    public void setUp()
    {
>>>>>>> anshuman-super-api
        driver = WebDriverSetup.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://fourdoor-web-stage.fourdoor.dev/");
    }
    public void testPageLoad() {
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
    }
    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("window.scrollBy(0,-104);");

    }

    public static void click(WebElement element)
    {
        waitForElementVisibility(element);
        elementToBeClickable(element).click();

    }

    public static void waitForElementVisibility(WebElement element)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement elementToBeClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement fluentWait(final By locator, int timeoutSeconds, int pollingSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofSeconds(pollingSeconds))
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }
    public static void waitForInvisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }


<<<<<<< HEAD
    @AfterMethod
    public void tearDown() {
=======
    @AfterClass
    public void tearDown()
    {
>>>>>>> anshuman-super-api
        WebDriverSetup.quitDriver();
    }
}