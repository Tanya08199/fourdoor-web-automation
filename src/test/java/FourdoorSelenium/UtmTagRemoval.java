package FourdoorSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class UtmTagRemoval extends BaseTest {


    @Test
    public void checkUtmRemovalFromFooter()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement footerDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,\"footerPattern\")]//div[contains(@class,\"grid grid-cols-1 lg:grid-cols-3\")]")));

        List<WebElement> footerLinks = footerDiv.findElements(By.tagName("a"));

        for(WebElement links : footerLinks)
        {
            String url = links.getAttribute("href");
            if(url != null && url.contains("source="))
            {
                System.out.println("UTM found " + url);
            }
            else {
                System.out.println("UTM not found " + url);
            }

        }
    }

    @Test
    public void checkUtmRemovalFromSection()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        List<WebElement> category = driver.findElements(By.xpath("//div[contains(@class,\"flex flex-col max-w-screen-xl mx-auto w-full\")]//div[contains(@class,\"px-0 pt-4 pb-2 lg:pt-10 lg:pb-8 w-full\")]"));
        for (WebElement cat : category)
        {
           List<WebElement> serviceLink = cat.findElements(By.tagName("a"));
            for(WebElement links : serviceLink)
            {
                String url = links.getAttribute("href");
                if(url != null && url.contains("source="))
                {
                    System.out.println("UTM found " + url);
                }
                else {
                    System.out.println("UTM not found " + url);
                }

            }

        }

    }

}
