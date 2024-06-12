package FourdoorSelenium;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WebDriverManger.WebDriverSetup;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp()
    {
        driver = WebDriverSetup.getDriver();
        driver.manage().window().maximize();
    }


    @AfterMethod
    public void tearDown()
    {
        WebDriverSetup.quitDriver();
    }
}
