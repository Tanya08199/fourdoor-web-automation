package FourdoorSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WebDriverManger.WebDriverSetup;

public class BaseTest {

    protected static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverSetup.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
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



    @AfterMethod
    public void tearDown() {
        WebDriverSetup.quitDriver();
    }
}