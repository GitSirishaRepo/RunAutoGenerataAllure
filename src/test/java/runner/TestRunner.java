package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
//       tags = "@Regression",
        features = "src/test/resources/features/LoginInvestor.feature",
        glue={"utility", "steps"},
        plugin ={ "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"}
//        "pretty",
//        "html:target/cucumber-reports.html",
//                "json:target/cucumber-reports/Cucunber.json"},
//        monochrome = true


)
public class TestRunner {


}
