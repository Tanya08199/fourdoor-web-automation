package FourdoorWeb.Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.WebDriverManger.WebDriverSetup;

public class TestHooks {

    @Before
    public void setUp()
    {
        System.out.println("test hook setUp");
        WebDriver driver = WebDriverSetup.getDriver();
        driver.manage().window().maximize();
        driver.get("https://fourdoor-web-stage.fourdoor.dev/");
    }

    @After
    public void tearDown()
    {
        WebDriverSetup.quitDriver();
    }
}
