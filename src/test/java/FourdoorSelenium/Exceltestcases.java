package FourdoorSelenium;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Exceltestcases extends BaseTest {

    @Test(dataProvider = "fourdoor", dataProviderClass = Exceldata.class)
public void validatingdata(String username , String password){

        driver.manage().window().maximize();
        driver.get("https://stage-refurb-console.qac24svc.dev/");
        driver.findElement(By.id("okta-signin-username")).sendKeys(username);
        driver.findElement(By.id("okta-signin-password")).sendKeys(password);
        driver.findElement(By.id("okta-signin-submit")).click();
       driver.quit();

    }

}
