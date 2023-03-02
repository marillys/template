package base;

import api.ApiRequest;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ExtentReportsUtils;
import utils.PropertiesUtils;

import java.lang.reflect.Method;

public class BaseTests extends ApiRequest implements ITestListener {

    PropertiesUtils prop = new PropertiesUtils();

    @BeforeSuite
    public void incializarRelatorio() {
        System.out.println("SUITE");

        ExtentReportsUtils.createReport();

        //Inicializar variáveis também
        super.uri = prop.getProp("base_url") + "/data/2.5/weather";
    }

    @BeforeMethod
    public void antesTest(Method method) {
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
