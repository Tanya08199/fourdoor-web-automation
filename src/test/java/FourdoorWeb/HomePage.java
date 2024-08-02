package FourdoorWeb;

import io.cucumber.java.en.When;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.*;
import utils.WebDriverManger.WebDriverSetup;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static utils.ImageValidation.isValidUrl;


public class HomePage{

    private WebDriver driver = WebDriverSetup.getDriver();

    @When("User is at the fourdoor home page")
    public void verifyHomePage()
    {
        String expectedTitle = "Expert Car Service & Repair in Gurugram | Fourdoor";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Title mismatch");
    }

    @When("User is at Super Category section")
    public void superCategory() throws IOException {

        WebElement superCategoryDiv = driver.findElement(By.xpath("//div[contains(@class,\"lg:shadow-landingCard\")]"));

        XSSFSheet sheet = ExcelUtils.readExcel("data/TestExcel (3).xlsx","Sheet1");
        for(int i =1; i<= sheet.getLastRowNum(); i++) {
            String expectedSuperCatName = ExcelUtils.getCellValue(sheet, i, 2);
            String expectedIcon = ExcelUtils.getCellValue(sheet, i, 3);

            List<WebElement> superCategoryName = superCategoryDiv.findElements(By.tagName("a"));
            boolean matchFound = false;
            for (WebElement name : superCategoryName) {
                String actualSuperCatName = name.getText().trim();

                if(expectedSuperCatName.equals(actualSuperCatName))
                {
                    matchFound=true;
                    WebElement Icon = name.findElement(By.tagName("img"));
                    String actualIcon = Icon.getAttribute("src");
                    Assert.assertEquals(actualIcon,expectedIcon,"Icon Url mismatch for super category "+expectedSuperCatName);
                    break;
                }

            }
            Assert.assertTrue(matchFound,"Super category name mismatch for row " + i + ": Expected " + expectedSuperCatName);
        }
    }

    @When("User is at Assurance Banner section")
    public void assuranceBanner()
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement assuranceBanner = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,\"relative w-full h-auto md:h-40 lg:h-[224px]\")]")));
        List<WebElement> img = assuranceBanner.findElements(By.tagName("img"));
        for(WebElement imgName : img)
        {
            String alt = imgName.getAttribute("alt");
            System.out.println(alt);

            String src = imgName.getAttribute("src");
            if(isValidUrl(src))
            {
                System.out.println("The image src is valid "+ src );
               // openInBrowser(src);
            }

        }
    }

    @When("User is at Home Page Services section")
    public void homPageServices()
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        List<WebElement> servicesDiv = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,\"px-0 pt-4 pb-2 lg:pt-10 lg:pb-8 w-full\")]")));
        for (WebElement service : servicesDiv)
        {
            WebElement h2 = service.findElement(By.tagName("h2"));
            System.out.println(h2.getText());

            List<WebElement> packageDiv = service.findElements(By.xpath(".//div[contains(@class,\"overflow-x-auto\")]"));

            for (WebElement pack : packageDiv)
            {
                List<WebElement> packageName = pack.findElements(By.xpath(".//div[contains(@class,\"shadow-landingCard\")]"));
                for(WebElement name : packageName)
                {
                    WebElement a = name.findElement(By.tagName("a"));
                    System.out.println(a.getAttribute("href"));

                    WebElement p = name.findElement(By.tagName("p"));
                    System.out.println(p.getText());
                }
            }

        }
    }

    @When("User is at Price Comparison section")
    public void priceComparison()
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement priceComparison = driver.findElement(By.xpath("//div[contains(@class,\"py-4 lg:px-4 lg:py-10 w-full\")]"));
        WebElement h2 = priceComparison.findElement(By.tagName("h2"));
        System.out.println("The h2 for this banner is "+ h2.getText());

        List<WebElement> imgTag = priceComparison.findElements(By.tagName("img"));
        for(WebElement img : imgTag)
        {
            System.out.println("The alt for image is "+img.getAttribute("alt"));
            System.out.println("The src for image is "+img.getAttribute("src"));
        }

    }

    @When("User is at Our Workshop section")
    public void ourWorkshop()
    {
        WebElement ourWorkshopDiv = driver.findElement(By.xpath("//div[contains(@class,\"bg-bg-gray-300\")]//div[contains(@class,\"max-w-screen-xl mx-auto px-4\")]"));
        WebElement h2 = ourWorkshopDiv.findElement(By.tagName("h2"));
        System.out.println(h2.getText());

        WebElement a = ourWorkshopDiv.findElement(By.tagName("a"));
        String workShopMap = a.getAttribute("href");

        String googleMapUrlPattern = "https://www\\.google\\.com/maps/.*";
        boolean isValid = Pattern.matches(googleMapUrlPattern, workShopMap);

        Assert.assertTrue(isValid,"The google map link is not valid");


    }

    @When("User is at Testimonial section")
    public void testimonial() throws IOException {
        WebElement testimonial = driver.findElement(By.xpath("//div[contains(@class,\"pl-4 lg:px-4 w-full pt-4 pb-2 lg:py-10\")]"));
        WebElement h2 = testimonial.findElement(By.tagName("h2"));
        System.out.println(h2.getText());

        List<WebElement> testimonialData = testimonial.findElements(By.xpath(".//div[contains(@class,'overflow-x-auto')]//div[contains(@class,'shadow-landingCard')]"));
        XSSFSheet sheet = ExcelUtils.readExcel("data/TestExcel (4).xlsx","Testimonial");

        for(int i=1; i<sheet.getLastRowNum(); i++)
        {
            String expectedCar = ExcelUtils.getCellValue(sheet,i,1);
            String expectedImage = ExcelUtils.getCellValue(sheet,i,2);
            String expectedName = ExcelUtils.getCellValue(sheet,i,3);
            String expectedDesc = ExcelUtils.getCellValue(sheet,i,5);

            WebElement data = testimonialData.get(i-1);

            WebElement desc = data.findElement(By.tagName("p"));
            WebElement date = data.findElement(By.xpath(".//div[contains(@class,\"flex justify-between my-4\")]//span[contains(@class,\"text-custom-gray-300\")]"));
            WebElement name = data.findElement(By.xpath(".//div[contains(@class, \"justify-center border-t\")]//span[contains(@class,\"text-custom-gray-800\")]"));
            WebElement car = data.findElement(By.xpath(".//div[contains(@class, \"justify-center border-t\")]//span[contains(@class,\"text-custom-gray-300\")]"));

            try {
                Assert.assertEquals(car.getText(),expectedCar,"Car details mismatch at row "+i);
                Assert.assertEquals(name.getText(),expectedName, "Name mismatch at row "+i);
                Assert.assertEquals(desc.getText(),expectedDesc,"Description mismatch at row "+i);
            }
            catch (AssertionError e)
            {
                System.out.println(e.getMessage());
            }
            }

    }

    @When("User is at Why choose fourdoor section")
    public void whyChooseFourdoor() throws IOException {
        WebElement h2 = driver.findElement(By.xpath("//div[contains(@class,\"px-4 py-4 lg:py-10 w-full\")]//h2[text()='Why choose Fourdoor for car service?']"));
        System.out.println(h2.getText());

        List<WebElement> whyChooseDiv = driver.findElements(By.xpath("//div[contains(@class,\"px-4 py-4 lg:py-10 w-full\")]//div[contains(@class,\"lg:grid-cols-3\")]//div[contains(@class,\"justify-start\")]"));

        XSSFSheet sheet = ExcelUtils.readExcel("data/TestExcel (5).xlsx","Why_Choose_Fourdoor");

        for(int i =1; i<sheet.getLastRowNum(); i++)
        {
            String expectedImg = ExcelUtils.getCellValue(sheet,i,3);
            String expectedTitle = ExcelUtils.getCellValue(sheet,i,1);
            String expectedDesc = ExcelUtils.getCellValue(sheet,i,2);

            WebElement data = whyChooseDiv.get(i-1);

            WebElement img = data.findElement(By.tagName("img"));
            WebElement title = data.findElement(By.tagName("h4"));
            WebElement desc = data.findElement(By.tagName("p"));

            try {
                Assert.assertEquals(img.getAttribute("src"),expectedImg,"Image icon mismatch at row "+i);
                Assert.assertEquals(title.getText(),expectedTitle,"Title mismatch at row "+i);
                Assert.assertEquals(desc.getText(),expectedDesc, "Description mismatch at row "+i);
            }
            catch (AssertionError e)
            {
                System.out.println(e.getMessage());
            }

        }
    }


}
