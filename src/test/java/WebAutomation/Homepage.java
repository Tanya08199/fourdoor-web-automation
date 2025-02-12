package WebAutomation;

import com.microsoft.playwright.*;
import org.junit.Test;

import java.nio.file.Paths;

public class Homepage {
    @Test
    public void login(){
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://www.fourdoor.com/gurugram");
            page.locator("#openloginnudge").first().click();
            page.locator("role=link[name='My cars My cars']").click();
            page.locator("role=button[name='Add new car']").click();
            page.locator("role=textbox[name='Enter your car number']").click();
            page.locator("role=textbox[name='Enter your car number']").fill("sb fj hbehbfh");
            page.locator("role=button[name='Add car']").click();
            page.locator("role=button[name='Close']").click();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));
            System.out.println("Screenshot saved as screenshot.png");
            browser.close();
        }
    }
}
