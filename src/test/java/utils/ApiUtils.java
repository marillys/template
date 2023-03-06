package utils;

import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ApiUtils extends LogUtils {
    protected static Response response;
    protected static JSONObject body;
    protected static String uri;
    protected static String url;
    protected static Map<String, String> headers = new HashMap<>();
    protected static Map<String, String> params = new HashMap<>();
    protected static Map<String, String> formParam = new HashMap<>();
    protected static String token;
    protected static FilterableRequestSpecification httpRequest;

    public void log(String verbo) {
        super.logInfo(" ****** Dados enviados no request ******");
        super.logInfo(verbo + " " + url + uri);
        super.logInfo("Body : " + " " + body);
        super.logInfo("Headers : " + " " + headers);
        super.logInfo("Params : " + " " + params);

        super.logInfo(" ****** Dados Recebidos ******");
        super.logInfo("Status code : " + response.statusCode());
        super.logInfo("Payload recebido:  \n" + response.asPrettyString());//TODO essa linha não funciona quando a response é HTML
        super.logInfo("Tempo de resposta: " + response.timeIn(TimeUnit.MILLISECONDS));
    }

    /**
     * Escreve a response no relatório
     */
    public void logRequest() {
        String request = "Request method: " + httpRequest.getMethod() +
                "\nRequest URI: " + httpRequest.getURI() +
                //"\nProxy: " + httpRequest.getProxySpecification(). +
                "\nRequest params: " + httpRequest.getRequestParams() +
                "\nQuery params: " + httpRequest.getQueryParams() +
                "\nForm params:  " + httpRequest.getFormParams() +
                "\nPath params:	 " + httpRequest.getPathParams() +
                "\nHeaders:		 " + httpRequest.getHeaders() +
                "\nCookies:		 " + httpRequest.getCookies() +
                "\nMultiparts:		 " + httpRequest.getMultiPartParams() +
                "\nBody:  " + httpRequest.getBody();

        super.logInfo(" ****** Request ******\n" + request);
    }

    /**
     * Escreve a response no relatório
     */
    public void logResponse() {
        String texto = "Status code : " + response.statusCode() +
                "\nHeaders: " + response.getHeaders() +
                "\nPayload recebido:  \n" + response.getBody().prettyPrint() +
                "\nTempo de resposta: " + response.timeIn(TimeUnit.MILLISECONDS);

        super.logInfo(" ****** Response ******\n" + texto);
    }

    /**
     * Loga a request e a response em um formato específico
     */
    public void logRequestResponse() {
        logRequest();
        logResponse();
    }


}
