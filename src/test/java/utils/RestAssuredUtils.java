package utils;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;


public class RestAssuredUtils {

    //métodos para fazer a execução dos serviços
    public static Response executarRestRequest(Method method, String url, Map<String, String> queryParam)
    {
        Response resposta;

        RequestSpecification requestSpecification = RestAssured.given().log().all().relaxedHTTPSValidation();

        //Adicionar todos os queryParam para a request
        for (Map.Entry<String,String> parametros: queryParam.entrySet())
        {
            requestSpecification.queryParam(parametros.getKey(),parametros.getValue());
        }

        resposta = requestSpecification.request(method,url);

        return resposta;
    }

    //métodos para fazer a execução dos serviços
    public static Response executarRestRequestRequestSpecification(Method method, RequestSpecification requestSpecification)
    {
        Response resposta;

        resposta = requestSpecification.request(method);
                //.request(method,url);

        return resposta;
    }

    public static Response executarRestRequestRequestSpecification(Method method, String url, Map<String, String> queryParam,
                                                       Map<String,String> formParam,
                                                       Map<String,String> headers, RequestSpecification requestSpecification)
    {
        Response resposta;

        //Adicionar todos os headers para a request
        for (Map.Entry<String,String> parametros: headers.entrySet())
        {
            //requestSpecification.headers(parametros.getKey(),parametros.getValue());
            if(parametros.getKey() == "charset") {
                requestSpecification.contentType(ContentType.URLENC.withCharset(parametros.getValue()));
            }
        }

        //Adicionar todos os queryParam para a request
        for (Map.Entry<String,String> parametros: queryParam.entrySet())
        {
            requestSpecification.queryParam(parametros.getKey(),parametros.getValue());
        }

        //Adicionar todos os formParam para a request
        for (Map.Entry<String,String> parametros: formParam.entrySet())
        {
            requestSpecification.formParam(parametros.getKey(),parametros.getValue());
        }

        resposta = requestSpecification.request(method,url);

        return resposta;
    }

    public static Response executarRequestAplicacaoWeb(Method method, String url, Map<String, String> queryParam,
                                                Map<String,String> formParam,
                                                Map<String,String> headers)
    {
        Response resposta;

        RequestSpecification requestSpecification = RestAssured.given().log().all().relaxedHTTPSValidation();

        //Adicionar todos os headers para a request
        for (Map.Entry<String,String> parametros: headers.entrySet())
        {
            //requestSpecification.headers(parametros.getKey(),parametros.getValue());
            if(parametros.getKey() == "charset") {
                requestSpecification.contentType(ContentType.URLENC.withCharset(parametros.getValue()));
            }
        }

        //Adicionar todos os queryParam para a request
        for (Map.Entry<String,String> parametros: queryParam.entrySet())
        {
            requestSpecification.queryParam(parametros.getKey(),parametros.getValue());
        }

        //Adicionar todos os formParam para a request
        for (Map.Entry<String,String> parametros: formParam.entrySet())
        {
            requestSpecification.formParam(parametros.getKey(),parametros.getValue());
        }

        resposta = requestSpecification.request(method,url);

        //Adiciona resultados ao relatório
        //ExtentReportsUtils.addInformacoesTesteWeb(url, resposta);
        ExtentReportsUtils.addRespostaTeste(url, resposta, "html");

        return resposta;
    }
}
