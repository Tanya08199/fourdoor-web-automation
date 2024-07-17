package FourdoorSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class CarAdd extends BaseTest{
            static String CarNo = "HR26EP4780";

    @Test(priority = 1)
    public static void AddCarWithoutLogin() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://fourdoor-web-stage.fourdoor.dev");
        WebElement abc = driver.findElement(By.xpath("//div[contains(@class,'max-w-screen-xl')]//div[contains(@class,'lg:shadow-landingCard')]/a[2]"));
        click(abc);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[contains(@class,'relative')]/a[1]")).click();
        Thread.sleep(7000);
        driver.findElement(By.xpath("//div[contains(@class,'flex lg:static bg-white w-full z-100 pt-2 pb-5 px-4 lg:p-0 left-0 fixed bottom-0 justify-between items-center mt-6 ')]/button")).click();
        Thread.sleep(5000);
    }
    @Test (priority = 2)
        public static void AddCarWithCarNumber() throws InterruptedException {
        String carNumber = CarNo; // or you can pass a specific value if needed
        AddCarWithCarNumber(carNumber);
    }

        public static void AddCarWithCarNumber(String carNumber) throws InterruptedException {
        if(carNumber == null) {
            carNumber = CarNo;
        }
        AddCarWithoutLogin();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement AddCar = driver.findElement(By.xpath("//div[contains(@class,'flex flex-col h-full w-full relative')]"));
        WebElement EnterCarNo = AddCar.findElement(By.xpath("//div[contains(@class,'rounded-lg h-[58px] flex items-center relative')]/input"));
        EnterCarNo.sendKeys(carNumber);
        Thread.sleep(5000);
        WebElement CarAdding = AddCar.findElement(By.xpath("//button[contains(@class, 'bg-primaryOrange text-white font-semibold h-12 mt-4 w-full lg:w-fit lg:px-12 lg:py-4 rounded-lg ')]"));
        CarAdding.click();
        Thread.sleep(5000);

    }

    @Test (priority = 3)
    public static void AddCarManually() throws InterruptedException {
       String diffCarNo = "1234567890";
       AddCarWithCarNumber(diffCarNo);
       WebElement unableFetchFrame = driver.findElement(By.xpath("//div[contains(@class,'relative min-h-80 min-w-80')]"));
               Thread.sleep(5000);

       WebElement unableFetch = unableFetchFrame.findElement(By.xpath("//button[text() ='Continue']"));
        unableFetch.click();
        Thread.sleep(5000);
        WebElement IconLisit = unableFetchFrame.findElement(By.xpath("//div[contains(@class,'border cursor-pointer rounded-lg flex items-center justify-center border-custom-gray-700 h-[56px] min-h-[56px]')][1]"));
        IconLisit.click();
        Thread.sleep(5000);
         WebElement brandName =  unableFetchFrame.findElement(By.xpath("//div[contains(@class,' border cursor-pointer items-center justify-center py-2 border-custom-gray-700 rounded-lg')][1]"));
          brandName.click();
          Thread.sleep(5000);
          WebElement Fuel =  unableFetchFrame.findElement(By.xpath("//div[contains(@class,'border cursor-pointer flex items-center rounded-lg justify-start gap-2 py-2 px-4 border-border-blue-100')][1]"));
           Fuel.click();
          Thread.sleep(5000);
                  System.out.println("Car Manually Successfully added");
 }

}