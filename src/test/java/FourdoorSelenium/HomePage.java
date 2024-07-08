package FourdoorSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomePage extends BaseTest {

    @Test
    public void launchUrl()
    {
        driver.get("https://fourdoor-web-stage.fourdoor.dev/");

        String expectedTitle = "Expert Car Service & Repair in Gurugram | Fourdoor";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void getAltImage()
    {
        driver.get("https://fourdoor-web-stage.fourdoor.dev/");
        Set<String> altAttributeValue = new HashSet<String>();
        int count =0;

        List<WebElement> imageElements = driver.findElements(By.tagName("img"));
        for (WebElement imageElement : imageElements) {
            String altAttributeValue1 = imageElement.getAttribute("alt");
            altAttributeValue.add(altAttributeValue1);
            count++;
        }

        System.out.println(altAttributeValue);
    }
}
