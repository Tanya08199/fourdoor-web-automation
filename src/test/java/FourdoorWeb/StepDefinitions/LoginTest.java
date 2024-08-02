package FourdoorWeb.StepDefinitions;

import FourdoorWeb.PageFactory.LoginPage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class LoginTest {

    WebDriver driver = null;

    LoginPage login = new LoginPage(driver);

    @Given("browser is open")
    public void openBrowser()
    {
       login.loginVerify("sdf","dff");
    }
}
