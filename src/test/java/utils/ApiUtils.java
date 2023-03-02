package utils;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class ApiUtils extends LogUtils {
    protected static Response response;
    protected static JSONObject body;
    protected static String uri;
    protected static Map<String, String> headers = new HashMap<>();
    protected static Map<String, String> params = new HashMap<>();
    protected static String token;

    public void log(String verbo) {
        super.logInfo(" ****** Dados enviados no request ******");
        super.logInfo(verbo + " " + uri);
        super.logInfo("Body : " + " " + body);
        super.logInfo("Headers : " + " " + headers);
        super.logInfo("Params : " + " " + params);

        super.logInfo(" ****** Dados Recebidos ******");
        super.logInfo("Status code : " + response.statusCode());
        super.logInfo("Payload recebido:  \n" + response.asPrettyString());
        super.logInfo("Tempo de resposta: " + response.timeIn(TimeUnit.MILLISECONDS));
    }

    /*TODO o código abaixo foi copiado da classe BaseTests... porque não estava funcionando, já que não tem apontamentos para a classe em questão com o Hooks
    Precisa analisar como refazer essa parte
    */
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
