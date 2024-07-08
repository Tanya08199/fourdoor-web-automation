package FourdoorSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Checkout extends Coupon {

    @Test
    public static void pickupAndDrop() throws InterruptedException {
        Coupon checkout1 = new Checkout();
        ApplyCoupon();
        Thread.sleep(5000);
        WebElement pickupAndDropButton = driver.findElement(By.xpath("//div[contains(@class,'p-4')]/p[contains(@class,'text-custom-gray-800 text-sm lg:text-xl lg:leading-6 font-semibold lg:font-medium mb-2 lg:mb-3')]"));
        pickupAndDropButton.click();
        Thread.sleep(5000);
        WebElement addressSearchFrame = driver.findElement(By.xpath("//div[contains(@class,'w-full flex justify-center items-center')]"));
        WebElement searchBox = addressSearchFrame.findElement(By.xpath("//div[contains(@class,'relative flex items-center mt-4 lg:mt-0 w-full flex-1')]//input[contains(@placeholder, 'Search for society/ location/ apartment')]"));

        System.out.println("Search address");
        searchBox.sendKeys("Kanhai, Sector 45, Gurugram, Haryana 122003, India");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchBox);
        Thread.sleep(2000);
        searchBox.click();
        Thread.sleep(7000);
        System.out.println("address Selected");

        WebElement firstAddressSuggestion = driver.findElement(By.xpath("//div[contains(@class,'pac-container pac-logo hdpi')][2]"));
        firstAddressSuggestion.click();
        Thread.sleep(2000);
        System.out.println("House no entered");

        WebElement fullAddress = driver.findElement(By.xpath("//div[contains(@class,'relative flex items-center w-full   mb-4')]//input[contains(@placeholder, 'House No, Street, Area*')]"));
        fullAddress.sendKeys("Hno1234");
        Thread.sleep(2000);
        WebElement confirmAddress = driver.findElement(By.xpath("//div[contains(@class,'absolute left-0 bottom-0 w-full mt-2 px-4 lg:px-0 lg:pl-4 bg-white')]/button"));
        confirmAddress.click();
        Thread.sleep(2000);
    }
}