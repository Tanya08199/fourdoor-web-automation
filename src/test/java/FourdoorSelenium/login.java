package FourdoorSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class login extends BaseTest {

    @Test
    public static void vaildlogin() throws InterruptedException {
        driver.get("https://fourdoor-web-stage.fourdoor.dev");
        Thread.sleep(5000);

        WebElement menu = driver.findElement(By.xpath("(//div[@class='ml-auto flex gap-6 justify-start items-center']//div[@class='relative flex items-center z-40'])[1]"));
        menu.click();
        Thread.sleep(5000);

        WebElement menu1 = driver.findElement(By.xpath("//div[contains(@class,'fixed lg:absolute top-0 right-0 lg:top-16 lg:-right-3 ')]"));
        WebElement loginButton = menu1.findElement(By.xpath("//div[contains(@class,'px-4 py-6 lg:p-6')]//div[contains(@class,'absolute bottom-0')]//div[2]"));
        loginButton.click();
    }

        public static void loginWithValiddNumber() throws InterruptedException {

        WebElement login = driver.findElement(By.xpath("//div[contains(@class,'relative min-h-80 min-w-80')]"));
        Thread.sleep(5000);

        WebElement PhoNum = login.findElement(By.xpath("//input[contains(@placeholder, 'XXX-XXX-XXXX')]"));
        PhoNum.sendKeys("8368843814");
        driver.findElement(By.xpath("//button[text() ='Get OTP ']")).click();
        Thread.sleep(3000);

        // Find individual OTP input fields within the container
        List<WebElement> otpFields = driver.findElements(By.xpath("//div[contains(@class,'flex-1 h-[58px]')]//input"));

        // Check that we have the correct number of OTP fields
        if (otpFields.size() != 4) {
            throw new IllegalStateException("Expected 4 OTP input fields but found " + otpFields.size());
        }

        String otpValue = "1111"; // The OTP value you want to enter

        // Fill OTP fields
        for (int i = 0; i < otpValue.length(); i++) {
            WebElement otpField = otpFields.get(i);
            otpField.sendKeys(String.valueOf(otpValue.charAt(i)));
        }

        driver.findElement(By.xpath("//div[contains(@class,'my-6 w-full')]//button")).click();

        Thread.sleep(8000);}
    }



