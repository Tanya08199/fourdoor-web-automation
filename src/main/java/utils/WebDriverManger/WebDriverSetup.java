package utils.WebDriverManger;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSetup {

    public static WebDriver driver;

    public static WebDriver getDriver()
    {

        if (driver==null)
        {

            WebDriverManager.chromedriver().driverVersion("126.0.0").setup();
            System.setProperty("webdriver.chrome.driver", "/Users/user/IdeaProjects/fourdoor-web-automation/src/main/java/utils/WebDriverManger/ChromeDriver/chromedriver-mac-x64/chromedriver");
            driver= new ChromeDriver();

        }

        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
