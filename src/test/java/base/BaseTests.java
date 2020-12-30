package base;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReportsUtils;

import java.lang.reflect.Method;

public class BaseTests implements ITestListener {

    @BeforeSuite
    public void incializarRelatorio()
    {
        System.out.println("SUITE");
        ExtentReportsUtils.createReport();
    }

    @BeforeMethod
    public void antesTest(Method method)
    {
        ExtentReportsUtils.addTest(method.getName(), method.getDeclaringClass().getSimpleName());
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest(ITestResult result) {
        ExtentReportsUtils.addTestResult(result);
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        ExtentReportsUtils.generateReport();
    }
}
