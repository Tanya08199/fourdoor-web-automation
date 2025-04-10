package WebAutomation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.regex.Pattern;

public class addcarwithoutLogin {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;



    @BeforeClass
    public static void setup(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://www.fourdoor.com/gurugram");
    }

    @Test
    public void CarIcon() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://www.fourdoor.com/gurugram");
        page.waitForSelector("#desktop-nav-add-car-icon");
        page.locator("#desktop-nav-add-car-icon").click();

        Locator textbox = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("DL 12 AA"));
        textbox.waitFor();
        textbox.fill("UP 13 CF 2411");

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add car")).waitFor();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add car")).click();

        System.out.println("Car added successfully.");

        browser.close();
        playwright.close();
    }

    @Test
    public void Hamburger() {
       Playwright playwright = Playwright.create();
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
      BrowserContext context = browser.newContext();
    Page page = context.newPage();

       page.navigate("https://www.fourdoor.com/gurugram");
        page.locator("#desktop-nav-add-car-icon").getByRole(AriaRole.IMG).click();

        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("DL 12 AA")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("DL 12 AA")).fill("hd gh dgvhfdg");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add car")).click();

        page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Maruti Suzuki$"))).dblclick();
        page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("Alto").setExact(true)).dblclick();
        page.getByText("Petrol").click();

        browser.close();
        playwright.close();
    }

    @Test
    public void Homepage() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://www.fourdoor.com/gurugram");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter your car number")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter your car number")).fill("bh bg hghghg");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add car").setExact(true)).click();
        page.getByText("Honda", new Page.GetByTextOptions().setExact(true)).click();
        page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("City")).click();
        page.getByText("Hybrid").click();

        browser.close();
        playwright.close();
    }
    @AfterClass
    public static void close(){
        browser.close();
        playwright.close();
    }
}
