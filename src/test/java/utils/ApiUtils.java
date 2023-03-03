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
    protected static Map<String, String> headers = new HashMap<>();
    protected static Map<String, String> params = new HashMap<>();
    protected static Map<String, String> formParam = new HashMap<>();
    protected static String token;
    protected static RequestSpecification request = RestAssured.given().log().all().relaxedHTTPSValidation();

    public void log(String verbo) {
        super.logInfo(" ****** Dados enviados no request ******");
        super.logInfo(verbo + " " + uri);
        super.logInfo("Body : " + " " + body);
        super.logInfo("Headers : " + " " + headers);
        super.logInfo("Params : " + " " + params);

        super.logInfo(" ****** Dados Recebidos ******");
        super.logInfo("Status code : " + response.statusCode());
        super.logInfo("Payload recebido:  \n" + response.asPrettyString());//TODO essa linha não funciona quando a response é HTML
        super.logInfo("Tempo de resposta: " + response.timeIn(TimeUnit.MILLISECONDS));
    }
}
