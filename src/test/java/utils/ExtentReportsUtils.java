package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import org.testng.ITestResult;

public class ExtentReportsUtils {

    private static PropertiesUtils prop = new PropertiesUtils();

    private static ExtentReports EXTENT_REPORT = null;
    private static ExtentTest TEST;
    private static ExtentHtmlReporter HTML_REPORTER = null;
    private static String caminhoRelatorio = prop.getProp("caminhoRelatorio");
    private static String arquivo = prop.getProp("nomeArquivo");
    private static String arquivoRelatorio = caminhoRelatorio + arquivo;
    private static String tituloDocumento = prop.getProp("tituloDocumento");
    private static String nomeRelatorio = prop.getProp("nomeRelatorio");
    private static String formatoData = prop.getProp("formatoData");

    /**
     * Cria o relatório quando não existe com as configurações do arquivo de configurações
     */
    public static void createReport() {
        //cria quando não existe
        if (EXTENT_REPORT == null) {
            HTML_REPORTER = new ExtentHtmlReporter(arquivoRelatorio);

            HTML_REPORTER.config().setEncoding("utf-8");
            HTML_REPORTER.config().setDocumentTitle(tituloDocumento);
            HTML_REPORTER.config().setReportName(nomeRelatorio);
            HTML_REPORTER.config().setTheme(Theme.STANDARD);
            HTML_REPORTER.config().setTimeStampFormat(formatoData);

            EXTENT_REPORT = new ExtentReports();
            EXTENT_REPORT.setSystemInfo("API", "-");
            EXTENT_REPORT.attachReporter(HTML_REPORTER);
        }
    }

    /**
     * Adiciona testes ao relatório HTML
     */
    public static void addTest(String testName, String testCategory) {
        TEST = EXTENT_REPORT.createTest(testName).assignCategory(testCategory.replace("Tests", ""));
    }

    /**
     * Adiciona o status do teste ao relatório
     * Recebe pelo ouvinte do testng o resultado do teste
     */
    public static void addTestResult(ITestResult result) {
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                TEST.log(Status.FAIL, "Test Result: " + Status.FAIL +
                        "<pre>Mensagem\n" + result.getThrowable().toString() + " </pre>");

                break;

            case ITestResult.SKIP:
                TEST.log(Status.SKIP, "Test Result: " + Status.SKIP);
                break;

            default:
                TEST.log(Status.PASS, "Test Result: " + Status.PASS);
                break;
        }
    }

    /**
     * Adiciona resultado do teste conforme os resultados do cenário do cucumber
     */
    public static void addTestResultCucumber(Scenario scenario) {
        switch (scenario.getStatus()) {
            case FAILED:
                TEST.log(Status.FAIL, "Test Result: " + Status.FAIL +
                        "<pre>Mensagem\n" + scenario.getName() + " </pre>");
//TODO pegar o trace da falha e colocar no relatório
//tags que foram executadas scenario.getSourceTagNames()
                //Enderfeço do arquivo featrure scenario.getId()
                break;

            case SKIPPED:
                TEST.log(Status.SKIP, "Test Result: " + Status.SKIP);
                break;

            default:
                TEST.log(Status.PASS, "Test Result: " + Status.PASS);
                break;
        }
    }

    /**
     * Adiciona informações ao relatório
     * Essas informações ficam com o ícone de informações
     */
    public static void addInfoTest(String valor) {
        TEST.log(Status.INFO, "<pre>" + valor + " </pre>");
    }

    public static void addFailTest(String valor) {
        TEST.log(Status.FAIL, "<pre>" + valor + " </pre>");
    }

    /**
     * Adiciona uma informação mais completa ao relatório
     */
    public static void addRespostaTeste(String url, Response resposta, String tipoEsperado) {
        TEST.log(Status.INFO, "<pre>URL: " + url + " </pre>");
        TEST.log(Status.INFO, "<pre>CONTENT-TYPE: " + resposta.contentType() + "</pre>");
        TEST.log(Status.INFO, "<pre>STATUS CODE: " + resposta.getStatusCode() + "</pre>");

        switch (tipoEsperado.toUpperCase()) {
            case "HTML":
                TEST.log(Status.INFO, "<pre>RESPOSTA DA REQUISIÇÃO: " + resposta.body().htmlPath().get().toString() + "</pre>");
                break;

            case "JSON":
                TEST.log(Status.INFO, "<pre>RESPOSTA DA REQUISIÇÃO: " + resposta.body().jsonPath().get().toString() + "</pre>");
                break;
        }
    }

    /**
     * Gera o relatório
     */
    public static void generateReport() {
        EXTENT_REPORT.flush();
    }
}