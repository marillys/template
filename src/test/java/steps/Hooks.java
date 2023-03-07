package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import utils.ExtentReportsUtils;
import utils.ScenarioUtils;

import java.lang.reflect.Method;

public class Hooks {

    @Before
    public void before(Scenario scenario) {
        ScenarioUtils.add(scenario);
        ExtentReportsUtils.createReport();
        ExtentReportsUtils.addTest(scenario.getName(), scenario.getId());
    }


    @After
    public void after(Scenario scenario) {
        ScenarioUtils.remove();
        ExtentReportsUtils.addTestResultCucumber(scenario);
        ExtentReportsUtils.generateReport();
    }

}