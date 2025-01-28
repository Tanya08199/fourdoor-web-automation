package FourdoorSelenium;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

import static FourdoorSelenium.Coupon.ApplyCoupon;
import static utils.WebDriverManger.WebDriverUtils.waitForInvisible;

public class referralCoupon extends BaseTest {

    public static void AppiledFromExisting() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        driver.get("https://fourdoor-web-qa.fourdoor.dev");
        WebElement CarIcon = fluentWait(By.xpath("//div[@class='ml-auto flex gap-6 justify-start items-center']/span[@class='cursor-pointer lg:hover:bg-bg-gray-300 rounded-xl']"), 30, 5);
        CarIcon.click();
        WebElement AddCar = driver.findElement(By.xpath("//div[contains(@class,'flex flex-col h-full w-full relative')]"));
        WebElement EnterCarNo = fluentWait(By.xpath("//div[contains(@class,'rounded-lg h-[58px] flex items-center relative')]/input"), 60, 5);
        EnterCarNo.sendKeys("HR51CG8855");
        Thread.sleep(3000);
        WebElement CarAdding = fluentWait(By.xpath("//button[contains(@class,'bg-primaryOrange text-white font-semibold lg:text-xl mt-6 w-full h-12 lg:h-auto lg:w-fit lg:px-12 lg:py-4 rounded-lg relative z-50 ')]"), 60, 10);
        CarAdding.click();
        WebElement loaderFetchCar = driver.findElement(By.xpath("//div[contains(@class,\"overflow-hidden shadow-xl transform transition-all\")]//p"));
        waitForInvisible(driver, loaderFetchCar);
    }

    @Test
    public static void packagesListing() throws InterruptedException {
        AppiledFromExisting();
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement ListofPackagegrid = fluentWait(By.xpath("//div[contains(@class,'max-w-screen-xl')]//div[contains(@class,'lg:shadow-landingCard')]/a"), 400, 10);
//        WebElement ListofPackage1 = ListofPackagegrid.findElement(By.xpath("//div[contains(@class,'max-w-screen-xl')]//div[contains(@class,'lg:shadow-landingCard')]/a[4]"));
//        ListofPackage1.click();

        for (int j = 1; j < 9; j++) {
            Map<String, Object> rowData = new HashMap<>(); // Create new rowData map for each iteration
            driver.findElement(By.xpath("//div[contains(@class,'max-w-screen-xl')]//div[contains(@class,'lg:shadow-landingCard')]/a["+j+"]")).click();
            Thread.sleep(4000);
         List<String> ServicesnameList = new ArrayList<>();
        List<String> buttonsList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            List<WebElement> buttons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[contains(@class,'w-full text-center px-1.5 -mt-4 lg:-mt-7 relative')]/button")
            ));        

            List<WebElement> Servicesname = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[contains(@class,'flex flex-col justify-start items-start')]//a//p[contains(@class,'text-custom-gray-800 text-sm lg:text-base leading-5 font-semibold mb-0')]")
            ));
            if (i < buttons.size()) {
                WebElement button = buttons.get(i);
                WebElement serviceName = Servicesname.get(i);
                try {
                    buttonsList.add(button.getText());
                    ServicesnameList.add(serviceName.getText());
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
                    wait.until(ExpectedConditions.elementToBeClickable(button));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
                    WebElement viewCartButton = fluentWait(
                            By.xpath("//button[contains(@class,'bg-primaryOrange text-white font-semibold lg:h-12 w-full rounded-lg')]"),400,10);
                    viewCartButton.click();
                    WebElement CouponGrid = fluentWait(
                            By.xpath("//div[contains(@class,'couponCard')]"),300,10);
                    WebElement ViewMoreCouponButton = CouponGrid.findElement(By.xpath("//div[contains(@class,'flex justify-between h-9 lg:h-[52px] items-center border-t border-dashed cursor-pointer')]/p"));
                    ViewMoreCouponButton.click();

                   System.out.println(ServicesnameList.get(i) + "added in cart" );
                    testDisplayedCoupons();
                    driver.navigate().back();
                    String home = driver.getCurrentUrl();
                    driver.get(home);

                    List<WebElement> removeServicesButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//div[contains(@class,'w-4 h-6 text-xl flex items-center justify-center font-extralight bg-transparent text-primaryOrange')]")));
                    if (removeServicesButtons.size() > 1) {
                        WebElement secondRemoveButton = removeServicesButtons.get(1);
                        wait.until(ExpectedConditions.elementToBeClickable(secondRemoveButton));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", secondRemoveButton);
                        secondRemoveButton.click();
                        Thread.sleep(5000);
                        System.out.println("Previous service is removed.");
                    } else {
                        System.out.println("Second Remove button is not found.");
                    }
                } catch (Exception e) {
                    System.out.println("Button " + (i + 1) + " is not clickable: " + e.getMessage());
                }
            } else {
                System.out.println("No more buttons to click.");
                break;
            }
        }
            driver.navigate().to("https://fourdoor-web-qa.fourdoor.dev");
        System.out.println("Total number of services for this package: " + buttonsList.size());

        }
 }

    @Test
    public static void testDisplayedCoupons() {
        List<WebElement> displayedCoupons = driver.findElements(By.xpath("//div[contains(@class,'flex h-full flex-col overflow-y-scroll bg-white py-6 shadow-xl ')]//div[contains(@class,'flex items-center justify-between')]//h5"));
         List<WebElement> applybutton = driver.findElements(By.xpath("//button[contains(@class,'absolute right-[16px]  top-[20px]')]"));
//     if (displayedCoupons.isEmpty()) {
//            System.out.println("No coupons are displayed.");
//        } else {
//            System.out.println("Displayed coupons count: " + displayedCoupons.size());
//            System.out.println("All the Displayed coupons for this Service:");
//            for (WebElement coupon : displayedCoupons) {
//                System.out.println(coupon.getText());
//            }
//        }
     for (int i = 0; i < displayedCoupons.size(); i++) {
                 String couponName = displayedCoupons.get(i).getText();
                 boolean isActive = applybutton.get(i).isEnabled();
                 String status = isActive ? "active" : "inactive";
                 System.out.println("Coupon Name: " + couponName + " - Status: " + status);
                 Assert.assertNotNull(couponName, "Coupon name should not be null");
                 Assert.assertTrue("Coupon status should be either active or inactive", status.equals("active") || status.equals("inactive"));
             }
         }
 }
      
