package FourdoorSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import utils.Constant;
import utils.XPaths;

import static utils.XPaths.addCarButtonOnHome;

public class AddCarWithoutLogin extends BaseTest{

    @Test
    public void hamburger() throws InterruptedException {
        driver.get("https://fourdoor-web-qa.fourdoor.dev/gurugram");
        WebElement Hamburger = driver.findElement(By.xpath(XPaths.Hamburger));
        Hamburger.click();
        WebElement AddCar = driver.findElement(By.xpath(XPaths.MenuAddCar));
        AddCar.click();
        WebElement EnterCarNo = AddCar.findElement(By.xpath(XPaths.EnterCarNoInIframe));
        EnterCarNo.sendKeys(Constant.CarNo);
        WebElement addCarButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPaths.AddCarButtonINIframe)));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addCarButton1);
        addCarButton1.click();
        System.out.println("Test Case Passed: [AddCarWithoutLogin for hamburger ]");

    }

    @Test
    public void Homepage() throws InterruptedException {
        driver.get("https://www.fourdoor.com/bengaluru");
        WebElement AddCarBoxOnHomepage = driver.findElement(By.xpath(XPaths.AddCarBoxOnHomepage));
        AddCarBoxOnHomepage.sendKeys(Constant.CarNo);
        WebElement addCarButton = driver.findElement(By.xpath(addCarButtonOnHome));
        scrollToElement(driver, addCarButton);
        addCarButton.click();
        System.out.println("Test Case Passed: [AddCarWithoutLogin for Homepage ]");

    }
    @Test
    public void Listing() throws InterruptedException {
        driver.get("https://www.fourdoor.com/gurugram/car-service-and-maintenance");
        WebElement AddCarOnListing = driver.findElement(By.xpath(XPaths.AddCarButtonOnListing));
        AddCarOnListing.click();
        WebElement EnterCarNo = AddCarOnListing.findElement(By.xpath(XPaths.EnterCarNoInIframe));
        EnterCarNo.sendKeys(Constant.CarNo);
        WebElement addCarButtonListing1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='mt-6 lg:max-w-80']//div[@id='add-car-button']/button[normalize-space(text())='Add car' and contains(@class, 'bg-primaryOrange')]")));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addCarButtonListing1);
        addCarButtonListing1.click();
        System.out.println("Test Case Passed: [AddCarWithoutLogin for Listing Page ]");


    }
    @Test
    public void Pdp(){
        driver.get("https://www.fourdoor.com/gurugram/car-service-and-maintenance/essential-package");
        WebElement addCarButtonPDP = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPaths.AddCarButtonOnPdp)));
        addCarButtonPDP.click();
        WebElement EnterCarNo = addCarButtonPDP.findElement(By.xpath(XPaths.EnterCarNoInIframe));
        EnterCarNo.sendKeys(Constant.CarNo);
        WebElement addCarButtonPDP1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPaths.AddCarButtonINIframe)));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addCarButtonPDP1);
        addCarButtonPDP1.click();
        System.out.println("Test Case Passed: [AddCarWithoutLogin for PDP Page ]");
    }
    @Test
    public void CarIcon() throws InterruptedException {
        driver.get("https://fourdoor-web-qa.fourdoor.dev/gurugram");
        WebElement AddCarIconOnHome = driver.findElement(By.xpath(XPaths.CarIcon));
        AddCarIconOnHome.click();
        WebElement EnterCarNo = AddCarIconOnHome.findElement(By.xpath(XPaths.EnterCarNoInIframe));
        EnterCarNo.sendKeys(Constant.CarNo);
        WebElement AddCarIconOnHome1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPaths.AddCarButtonINIframe)));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", AddCarIconOnHome1);
        AddCarIconOnHome1.click();
        System.out.println("Test Case Passed: [AddCarWithoutLogin for CarIcon ]");
    }

}


