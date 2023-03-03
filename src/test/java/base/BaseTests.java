package base;

import api.ApiRequest;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ExtentReportsUtils;
import utils.ManipulacaoArquivos;
import utils.PropertiesUtils;

import java.lang.reflect.Method;

public class BaseTests extends ApiRequest implements ITestListener {
    public PropertiesUtils prop = new PropertiesUtils();
    public ManipulacaoArquivos arquivos = new ManipulacaoArquivos();

    @BeforeSuite
    public void incializarRelatorio() {
        System.out.println("SUITE");

        ExtentReportsUtils.createReport();
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
