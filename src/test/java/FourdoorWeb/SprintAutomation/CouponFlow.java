package FourdoorWeb.SprintAutomation;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WebDriverManger.WebDriverSetup;

import java.util.List;

import static utils.WebDriverManger.WebDriverUtils.*;

public class CouponFlow {

    private WebDriver driver = WebDriverSetup.getDriver();

    @When("User move to the cart page")
    public void clickCart() throws InterruptedException {
        WebElement cartIcon = driver.findElement(By.xpath("//div[contains(@class,\"header_headerWrap__zI_4G lg:relative z-20\")]//span[contains(@class,\"cursor-pointer relative hidden lg:block lg:hover:bg-bg-gray-300 rounded-xl\")]"));
        click(driver,cartIcon);
        Thread.sleep(6000);
    }

    @Then("User click on the view more coupon")
    public void clickViewMoreCoupon() throws InterruptedException {
        WebElement viewMoreCoupon = driver.findElement(By.xpath("//div[contains(@class,\"couponCard\")]//p[contains(text(),\"View more coupons\")]"));
        click(driver,viewMoreCoupon);
        Thread.sleep(4000);
    }

    @Then("User enter the coupon code and apply")
    public void enterCouponCode() throws InterruptedException {
        WebElement applyCouponDiv = driver.findElement(By.xpath("//div[contains(@class,\"pointer-events-auto w-screen max-w-full lg:max-w-lg\")]"));
        WebElement inputBox = applyCouponDiv.findElement(By.tagName("input"));
        click(driver,inputBox);
        inputBox.sendKeys("BUMPER100");

        WebElement applyCoupon = applyCouponDiv.findElement(By.xpath("//div[contains(@class,\"relative flex items-center my-2 w-full  \")]//button"));
        click(driver,applyCoupon);

        Thread.sleep(3000);

        WebElement couponAppliedPopup = driver.findElement(By.xpath("//div[contains(@class,\"w-11/12 z-20 lg:max-w-[340px] rounded-2xl relative\")]"));
        waitForElementVisible(driver,couponAppliedPopup);
        waitForInvisible(driver,couponAppliedPopup);

    }

    @And("User apply the coupon from list")
    public void applyCouponFromList() throws InterruptedException {
        WebElement applyCouponDiv = driver.findElement(By.xpath("//div[contains(@class,\"pointer-events-auto w-screen max-w-full lg:max-w-lg\")]"));
        List<WebElement> applyCoupon = applyCouponDiv.findElements(By.xpath("//div[contains(@class,\"relative mt-6 w-full justify-center couponCard couponSlideCard\")]//button"));

        for(WebElement apply : applyCoupon)
        {
            click(driver,apply);
            break;

        }
        Thread.sleep(3000);

        WebElement couponAppliedPopup = driver.findElement(By.xpath("//div[contains(@class,\"w-11/12 z-20 lg:max-w-[340px] rounded-2xl relative\")]"));
        waitForElementVisible(driver,couponAppliedPopup);
        waitForInvisible(driver,couponAppliedPopup);
    }

}
