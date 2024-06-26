package FourdoorWeb;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.WebDriverManger.WebDriverSetup;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp()
    {
        driver = WebDriverSetup.getDriver();
        driver.manage().window().maximize();
        driver.get("https://fourdoor-web-stage.fourdoor.dev/");
    }


    @AfterClass
    public void tearDown()
    {
        WebDriverSetup.quitDriver();
    }
}
