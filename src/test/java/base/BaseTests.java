package base;

import api.ApiRequest;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReportsUtils;
import utils.ManipulacaoArquivos;
import utils.PropertiesUtils;

import java.lang.reflect.Method;
import java.util.HashMap;

public class BaseTests extends ApiRequest implements ITestListener {
    public PropertiesUtils prop = new PropertiesUtils();
    public ManipulacaoArquivos arquivos = new ManipulacaoArquivos();

    @BeforeSuite
    public void incializarRelatorio() {
        ExtentReportsUtils.createReport();
    }

    @BeforeMethod
    public void antesTest(Method method) {
        ExtentReportsUtils.addTest(method.getName(), method.getDeclaringClass().getSimpleName());
    }

    @AfterMethod(alwaysRun = true)
    public void limparVariaveis(){
        super.body=null;
        super.token = "";
        super.headers = new HashMap<>();
        super.params = new HashMap<>();
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
