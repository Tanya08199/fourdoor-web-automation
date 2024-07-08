package FourdoorSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class dateAndTimeOnCheckOut extends Checkout{

    @Test

    public static void preferredDateAndTimeOnCheckOut() throws InterruptedException {
        Checkout dateandTime = new dateAndTimeOnCheckOut();
        pickupAndDrop();
        WebElement SelectDateButton = driver.findElement(By.xpath("//div[contains(@class,'flex-nowrap')]//div[2]"));
        SelectDateButton.click();
        Thread.sleep(2000);
        WebElement SelectTimeButton = driver.findElement(By.xpath("//div[contains(@class,'flex flex-wrap gap-4 py-2')]//div[contains(text(),\"2 - 3 PM\")]"));
        SelectTimeButton.click();//button[contains(@class, 'bg-primaryOrange text-white font-semibold h-12 mt-4 w-full lg:w-fit lg:px-12 lg:py-4 rounded-lg ')]
        Thread.sleep(2000);
        WebElement proceedToBookNowButton = driver.findElement(By.xpath("//button[text()='Proceed to book now']"));
        proceedToBookNowButton.click();
        System.out.println("preferred DateAndTime for CheckOut selected");

        Thread.sleep(3000);

        WebElement BookNowButton = driver.findElement(By.xpath("//button[text()='Book now']"));
        BookNowButton.click();
        System.out.println("Booking confirmed");
        Thread.sleep(9000);

        WebElement BookId = driver.findElement(By.xpath("//p[contains(@class,'text-custom-gray-800 lg:text-primaryText text-base lg:text-2xl leading-6 font-semibold lg:font-medium')]"));
        System.out.println(BookId.getText());



    }


}
