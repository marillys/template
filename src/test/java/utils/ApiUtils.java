package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
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

    public void log(String verbo) {
        //TODO Arrumar uma forma de colocar nos logs a request completa
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
}
