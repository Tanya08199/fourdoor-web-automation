package FourdoorWeb.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;
    @FindBy(id = "username")
    WebElement txt_username;

    @FindBy(id = "password")
    WebElement txt_password;

    @FindBy(id = "submit")
    WebElement submit;

    public LoginPage(WebDriver driver)
    {
       this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void loginVerify(String username, String password)
    {
        txt_username.sendKeys(username);
        txt_password.sendKeys(password);
        submit.click();
    }

}
