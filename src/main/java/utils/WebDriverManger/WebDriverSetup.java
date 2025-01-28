package utils.WebDriverManger;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.SessionNotCreatedException;

public class WebDriverSetup {

    public static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {
            try {
                // WebDriverManager will automatically download and set the path for the chromedriver
                WebDriverManager.chromedriver().driverVersion("131.0.6778.264").setup();
                driver = new ChromeDriver(); // No need to set system property manually
            } catch (Exception e) {
                throw new SessionNotCreatedException("Could not create a new WebDriver session: " + e.getMessage(), e);
            }
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
