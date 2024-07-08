package FourdoorSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;

public class Servicesaddtocart extends CarAdd{
    static WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));


    @Test
    public static void ServicesAddToCart() throws InterruptedException {
        CarAdd addCar = new CarAdd();
        AddCarManually();
        Thread.sleep(5000);
        WebElement AddCarButton = driver.findElement(By.xpath("//div[contains(@class,'flex lg:static bg-white w-full z-100 pt-2 pb-5 px-4 lg:p-0 left-0 fixed bottom-0 justify-between items-center mt-6 shadow-landingCard lg:shadow-removeShadow')]/button"));
        AddCarButton.click();
        Thread.sleep(3000);
        WebElement ViewCart = driver.findElement(By.xpath("//div[@class='ml-auto flex gap-6 justify-start items-center']/span[@class='cursor-pointer relative hidden lg:block lg:hover:bg-bg-gray-300 rounded-xl']"));
        ViewCart.click();
        Thread.sleep(5000);
        System.out.println("Service Successfully Added");

    }

}
