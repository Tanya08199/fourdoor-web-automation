package FourdoorSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static FourdoorSelenium.Servicesaddtocart.ServicesAddToCart;
import static FourdoorSelenium.login.loginWithValiddNumber;

public class  Coupon extends CarAdd{

    @Test

    public static void ApplyCoupon() throws InterruptedException {
        Servicesaddtocart Coupon = new Servicesaddtocart();
        ServicesAddToCart();
        Thread.sleep(3000);
        WebElement CouponGrid = driver.findElement(By.xpath("//div[contains(@class,'couponCard')]"));
        WebElement ViewMoreCouponButton = CouponGrid.findElement(By.xpath("//div[contains(@class,'flex justify-between h-9 lg:h-[52px] items-center border-t border-dashed cursor-pointer')]/p"));
        ViewMoreCouponButton.click();
        Thread.sleep(3000);
        WebElement ApplyCouponGrid = driver.findElement(By.xpath("//div[contains(@class,'pointer-events-none fixed inset-y-0 right-0 flex max-w-full pl-0 lg:pl-10')]"));
        WebElement ApplyCouponButton = ApplyCouponGrid.findElement(By.xpath("//button[contains(@class,'flex justify-between  h-10 items-center border-primaryOrange  border-t w-full border-dashed')]/span"));
        Thread.sleep(3000);
        ApplyCouponButton.click();
        Thread.sleep(5000);
        WebElement CheckOutButton = driver.findElement(By.xpath("//button[contains(@class,'w-[160px] lg:w-full h-12 lg:h-16 flex justify-center items-center gap-4 text-base  font-semibold rounded-lg lg:mt-2 lg:h-[48px] bg-primaryOrange text-white')]"));
        CheckOutButton.click();
        Thread.sleep(4000);
        System.out.println("Coupon Applied");
        login couponlogin = new login();
        loginWithValiddNumber();
        System.out.println("Login Successfully");
    }

}
