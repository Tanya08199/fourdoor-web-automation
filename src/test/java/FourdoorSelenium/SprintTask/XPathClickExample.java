package FourdoorSelenium.SprintTask;

import FourdoorSelenium.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static utils.WebDriverManger.WebDriverSetup.getDriver;
public class XPathClickExample extends BaseTest {
   public static void main(String[] args) throws InterruptedException {

        WebDriver driver = getDriver();
        driver.get("https://www.fourdoor.com/gurugram");
        driver.manage().window().maximize();
//       WebElement addcarboxonhomepage = driver.findElement(By.xpath("//input[@id='carNumber']"));
//       addcarboxonhomepage.sendKeys("HR26EP4780");
//       WebElement addCarButton = driver.findElement(By.xpath("//div[@class='mt-0']//div[@id='add-car-button']/button[normalize-space(text())='Add car' and contains(@class, 'bg-primaryOrange')]"));
//       addCarButton.click();
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Hamburger = driver.findElement(By.xpath("//span[@class='cursor-pointer hidden lg:block']/div[contains(@class,'relative flex items-center z-50')]/span[@id='openloginnudge']"));
        Hamburger .click();
        Thread.sleep(2000);
        WebElement AddCar = driver.findElement(By.xpath("//div[contains(@class,'divide-y divide-custom-gray-border mt-2 px-4 lg:px-0')]/a[1]"));
        AddCar.click();
        WebElement EnterCarNo = AddCar.findElement(By.xpath("//div[contains(@class,'rounded-lg h-[58px] flex items-center relative')]/input[@placeholder='DL 12 AA 1234']"));
        EnterCarNo.sendKeys("HR26EP4780");
       WebElement addCarButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='mt-6 lg:max-w-80']//div[@id='add-car-button']/button[normalize-space(text())='Add car' and contains(@class, 'bg-primaryOrange')]")));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addCarButton1);//              element.click();
      addCarButton1.click();}
    }

