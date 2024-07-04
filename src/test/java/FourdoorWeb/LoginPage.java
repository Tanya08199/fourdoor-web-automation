package FourdoorWeb;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.WebDriverManger.WebDriverSetup;

import java.time.Duration;
import java.util.List;

import static utils.WebDriverManger.WebDriverUtils.*;

public class LoginPage {

    private WebDriver driver = WebDriverSetup.getDriver();


    @When("User run the login functionality")
    public void Login() throws InterruptedException {
        // Get the viewport width using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long viewportWidth = (Long) js.executeScript("return window.innerWidth;");

        if (viewportWidth > 768) {
            WebElement desktopElement = driver.findElement(By.xpath("//div[contains(@class,'header_headerWrap__zI_4G')]//div[@class='relative flex items-center z-40']"));
            scrollToElement(driver,desktopElement);
            click(driver,desktopElement);
        }
        WebElement hamBurger = driver.findElement(By.xpath("//div[contains(@class,\"px-4 py-6 lg:p-6\")]"));
        scrollToElement(driver,hamBurger);
        WebElement loginButton = hamBurger.findElement(By.xpath("//div[contains(text(),\"Log In\")]"));
        click(driver,loginButton);

        WebElement loginBox = driver.findElement(By.xpath("//div[contains(@class,\"bg-white rounded-3xl overflow-hidden undefined \")]"));
        waitForElementVisible(driver,loginBox);
        scrollToElement(driver,loginBox);

       WebElement numberBox = loginBox.findElement(By.xpath("//div[contains(@class,\"flex rounded-lg relative\")]//input"));
       waitForClickable(driver,numberBox);
       click(driver,numberBox);
       numberBox.sendKeys("8510932973");

       WebElement otpButton = loginBox.findElement(By.xpath("//button[contains(text(),'Get OTP')]"));
       waitToEnable(driver,otpButton);
       click(driver,otpButton);

       waitForOtpFieldsToBeVisible(driver);


       List<WebElement> otpField = loginBox.findElements(By.xpath("//div[contains(@class,\"flex gap-2 items-center\")]//input"));
        for(WebElement otp : otpField)
        {
            waitForClickable(driver,otp);
            click(driver,otp);
            otp.sendKeys("1");

        }
        Thread.sleep(2000);

        WebElement verifyButton = loginBox.findElement(By.xpath("//button[contains(text(),'Verify')]"));
        waitToEnable(driver,verifyButton);
        click(driver,verifyButton);
        Thread.sleep(3000);


    }

    @Then("Verify the user is logged in or not")
    public void verifyLogin()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long viewportWidth = (Long) js.executeScript("return window.innerWidth;");

        if (viewportWidth > 768) {
            WebElement desktopElement = driver.findElement(By.xpath("//div[contains(@class,'header_headerWrap__zI_4G')]//div[@class='relative flex items-center z-40']"));
            scrollToElement(driver,desktopElement);
            click(driver,desktopElement);
        }

        WebElement hamBurger = driver.findElement(By.xpath("//div[contains(@class,\"px-4 py-6 lg:p-6\")]"));
        scrollToElement(driver,hamBurger);

        WebElement logoutButton = hamBurger.findElement(By.xpath("//div[contains(text(),\"Log out\")]"));
        Assert.assertTrue(logoutButton.isDisplayed(),"Login is failed");

    }

    private void waitForOtpFieldsToBeVisible(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,\"flex gap-2 items-center\")]//input")));
    }
}
