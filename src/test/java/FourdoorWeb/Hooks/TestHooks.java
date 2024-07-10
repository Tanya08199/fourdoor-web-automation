package FourdoorWeb.Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.WebDriverManger.WebDriverSetup;

public class TestHooks {

    @Before
    public void setUp()
    {
        WebDriverSetup.getDriver().manage().window().maximize();
        WebDriverSetup.getDriver().get("https://fourdoor-web-stage.fourdoor.dev/");
    }

    @After
    public void tearDown()
    {
        WebDriverSetup.quitDriver();
    }
}
