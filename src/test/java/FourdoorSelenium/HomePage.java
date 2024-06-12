package FourdoorSelenium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePage extends BaseTest {

    @Test
    public void launchUrl()
    {
        driver.get("https://fourdoor-web-stage.fourdoor.dev/");

        String expectedTitle = "Expert Car Service & Repair in Gurugram | Fourdoor";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle,expectedTitle);
    }
}
