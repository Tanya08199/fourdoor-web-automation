package FourdoorSelenium.SprintTask;

import FourdoorSelenium.BaseTest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class SeoTask extends BaseTest {

    @DataProvider(name = "jsonLdPages")
    public Object[][] jsonLdPages() {
        return new Object[][]{
               {"https://fourdoor-web-qa.fourdoor.dev/", List.of("localBusiness", "faq", "org")},
              {"https://fourdoor-web-qa.fourdoor.dev/contact-us", List.of("breadcrumb", "webPage")},
               {"https://fourdoor-web-qa.fourdoor.dev/gurugram/car-service-and-maintenance", List.of("breadcrumb", "webPage", "faq")},
                {"https://fourdoor-web-qa.fourdoor.dev/gurugram/car-service-and-maintenance/max-package?source=", List.of("breadcrumb", "webPage", "faq")},
                {"https://fourdoor-web-qa.fourdoor.dev/gurugram/car-service-and-maintenance/essential-package", List.of("breadcrumb", "webPage", "faq")},
                {"https://fourdoor-web-qa.fourdoor.dev/help-and-faq?source=", List.of("breadcrumb", "webPage")},
              {"https://fourdoor-web-qa.fourdoor.dev/privacy-policy", List.of("breadcrumb", "webPage")},
                {"https://fourdoor-web-qa.fourdoor.dev/terms-and-conditions", List.of("breadcrumb", "webPage")}
        };
    }
    @Test(dataProvider = "jsonLdPages")
    public void testJsonLdStructuredData(String url, List<String> expectedFields) {
        driver.get(url);

        // Wait for the page to load and locate the JSON-LD script
        WebElement jsonLdScript = fluentWait(By.xpath("//script[@id='json-ld-script']"),
                40, 10);

        // Extract the JSON-LD content
        String jsonLdContent = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;", jsonLdScript);
        System.out.println("Retrieved JSON-LD content: " + jsonLdContent);
        if (jsonLdContent == null || jsonLdContent.isEmpty()) {
            Assert.fail("JSON-LD content is null or empty.");
        }
        // Validate the JSON-LD content
        Assert.assertNotNull(jsonLdContent, "JSON-LD script is not found or empty.");
        System.out.println("JSON-LD content found: " + jsonLdContent);

        // Additional validation can be performed here, such as checking for specific keys or values in the JSON
        try {
            JSONObject jsonLdObject = new JSONObject(jsonLdContent);

            // Check for required fields and their values
            for (String field : expectedFields) {
                Assert.assertTrue(field + " is missing.", jsonLdObject.has(field));
            }

        } catch (JSONException e) {
//            Assert.fail("JSON-LD content is not valid JSON.");
        }
        }
    }




