package FourdoorWeb;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "/Users/user/IdeaProjects/fourdoor-web-automation/src/main/resources/FeatureFile",
        glue = "FourdoorWeb",
        tags = "not @ignore",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class RunCucumberTest {
}