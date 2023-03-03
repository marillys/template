package testes;

import base.BaseTests;
import static io.restassured.RestAssured.given;

import io.restassured.http.Method;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportsUtils;
import utils.RestAssuredUtils;

import java.util.HashMap;
import java.util.Map;

public class TestsValidandoRelatorio extends BaseTests {

    @Test
    public void teste()
    {
        XmlPath xml = given()
                .log().all()
                .queryParam("q", "Fortaleza,BR")
                .queryParam("appid", "6a6424d1582a9636d6d186ea0cc53620")
                .queryParam("units","metric")
                .when()
                .get("http://api.openweathermap.org/data/2.5/weather")
                .andReturn().xmlPath();
    }

    @Test
    public void testeResponse()
    {
        Response resposta = given()
                .log().all()
                .queryParam("q", "Fortaleza,BR")
                .queryParam("appid", "6a6424d1582a9636d6d186ea0cc53620")
                .queryParam("units","metric")
                .when()
                .get("http://api.openweathermap.org/data/2.5/weather")
                .andReturn();

        //ExtentReportsUtils.addInformacoesTest("http://api.openweathermap.org/data/2.5/weather", resposta, "json");
        ExtentReportsUtils.addRespostaTeste("http://api.openweathermap.org/data/2.5/weather", resposta, "html");

        Assert.assertEquals(resposta.getStatusCode(),400);

        System.out.println("STATUS LINE "+resposta.getStatusLine());
        System.out.println("ID "+resposta.getSessionId());
        System.out.println("CODE "+resposta.getStatusCode());

    }

    @Test
    public void testeMethodo()
    {
        //parametros
        Map<String,String> queryParam = new HashMap<>();

        queryParam.put("q","Fortaleza,BR");
        queryParam.put("appid", "6a6424d1582a9636d6d186ea0cc53620");
        queryParam.put("units","metric");

        RestAssuredUtils rest = new RestAssuredUtils();
        Response resposta = rest.executarRestRequest(Method.GET,
                "http://api.openweathermap.org/data/2.5/weather",
                queryParam);

        Assert.assertEquals(resposta.getStatusCode(),400);
    }

    @Test
    public void testeAplicacaoWeb()
    {
        //parametros
        Map<String,String> formParam = new HashMap<>();
        Map<String,String> queryParam = new HashMap<>();//não tem valor pra passar
        Map<String,String> headers = new HashMap<>();

        formParam.put("senha", "Marilia");
        formParam.put("email", "marilia@a");

        headers.put("charset","utf-8");
        headers.put("Content-Type","application/x-www-form-urlencoded");

        RestAssuredUtils rest = new RestAssuredUtils();

        Response resposta = rest.executarRequestAplicacaoWeb(Method.POST,
                "http://seubarriga.wcaquino.me/logar",
                queryParam, formParam,headers);

        Assert.assertEquals(resposta.getStatusCode(),200);
    }
}
