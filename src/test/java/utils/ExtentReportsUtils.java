package utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.response.Response;
import org.testng.ITestResult;

import java.util.Locale;

public class ExtentReportsUtils {
    public static ExtentReports EXTENT_REPORT = null;
    public static ExtentTest TEST;
    public static ExtentHtmlReporter HTML_REPORTER = null;
    public static String caminhoRelatorio = "./target/reports/";
    public static String nomeRelatorio = "relatorio2.htm";
    public static String arquivoRelatorio = caminhoRelatorio + nomeRelatorio;

    /**configurar tudo o que se diz respeito aos relatórios
    O formato dos relatórios*/


    //criar relatório
    public static void createReport()
    {
        //cria quando não existe
        if(EXTENT_REPORT == null)
        {
            HTML_REPORTER = new ExtentHtmlReporter(arquivoRelatorio);


            HTML_REPORTER.config().setEncoding("utf-8");
            HTML_REPORTER.config().setDocumentTitle("Resultados dos Testes Automáticos");
            HTML_REPORTER.config().setReportName("Resultados dos Testes");
            HTML_REPORTER.config().setTheme(Theme.STANDARD);

            EXTENT_REPORT = new ExtentReports();
            EXTENT_REPORT.setSystemInfo("Browser", "Chrome");
            EXTENT_REPORT.attachReporter(HTML_REPORTER);
        }
    }

    public static void addTest(String testName, String testCategory){
        TEST = EXTENT_REPORT.createTest(testName).assignCategory(testCategory.replace("Tests",""));
    }

    // Adicionar o status do teste ao relatório
    // Recebe pelo ouvinte do testng o resultado do teste
    public static void addTestResult(ITestResult result)
    {
        //Falta pegar o trace em caso de falha
        switch (result.getStatus())
        {
            case ITestResult.FAILURE:
                TEST.log(Status.FAIL, "Test Result: " + Status.FAIL +
                        "<pre>Mensagem"+result.getThrowable().toString()+" </pre>");
                break;

            case ITestResult.SKIP:
                TEST.log(Status.SKIP, "Test Result: " + Status.SKIP);
                break;

            default:
                TEST.log(Status.PASS, "Test Result: " + Status.PASS);
                break;
        }

    }

    public static void addRespostaTeste(String url, Response resposta, String tipoEsperado)
    {
        TEST.log(Status.INFO, "<pre>URL: "+ url+" </pre>");
        TEST.log(Status.INFO, "<pre>CONTENT-TYPE: "+resposta.contentType()+"</pre>");
        TEST.log(Status.INFO, "<pre>STATUS CODE: "+resposta.getStatusCode() +"</pre>");

        switch (tipoEsperado.toUpperCase())
        {
            case "HTML":
                TEST.log(Status.INFO, "<pre>RESPOSTA DA REQUISIÇÃO: "+resposta.body().htmlPath().get().toString() +"</pre>");
                break;

            case "JSON":
                TEST.log(Status.INFO, "<pre>RESPOSTA DA REQUISIÇÃO: "+resposta.body().jsonPath().get().toString() +"</pre>");
                break;
        }


    }

    public static void generateReport(){
        EXTENT_REPORT.flush();
    }
}
