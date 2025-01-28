package FourdoorSelenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManger.WebDriverSetup;
import java.time.Duration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverSetup.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Timeout for waiting
        driver.manage().window().maximize();
    }

    // Method to check page load complete state
    public void testPageLoad() {
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
    }

    // Scroll to an element using JavascriptExecutor
    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("window.scrollBy(0,-104);"); // Adjusting to prevent the element being covered
    }

    // General click method with visibility and clickability checks
    public static void click(String element) {
        waitForElementVisibility(element);
        elementToBeClickable(element).click();
    }

    // Wait until the element is visible
    public static void waitForElementVisibility(String locator) {
        WebElement element = driver.findElement(By.id(locator)); // Or use a different locating strategy
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    // Wait until the element is clickable
    public static WebElement elementToBeClickable(String element) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
    }

    // Fluent wait for an element with timeout and polling interval
    public static WebElement fluentWait(By locator, int timeoutSeconds, int pollingMillis) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .ignoring(NoSuchElementException.class);

        return fluentWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Quit the driver after each test
    @AfterMethod
    public void tearDown() {
        WebDriverSetup.quitDriver();
    }
}
