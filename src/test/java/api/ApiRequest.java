package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import  io.restassured.specification.FilterableRequestSpecification;

import utils.ApiUtils;
import utils.RestAssuredUtils;

import static io.restassured.RestAssured.given;

public class ApiRequest extends ApiUtils implements ApiVerbos {
    RestAssuredUtils rest = new RestAssuredUtils();
    private RequestSpecification request;

    @Override
    public void GET() {
        request = RestAssured.given().log().all().relaxedHTTPSValidation();
        preencherDadosRequest();

        response = rest.executarRestRequest(Method.GET, request);

        super.logRequestResponse();
    }

    @Override
    public void POST() {
        request = RestAssured.given().log().all().relaxedHTTPSValidation();
        RequestBody();

        response = rest.executarRestRequest(Method.POST,request);

        super.logRequestResponse();
    }

    @Override
    public void PUT() {
        request = RestAssured.given().log().all().relaxedHTTPSValidation();
        RequestBody();

        response = rest.executarRestRequest(Method.PUT, request);

        super.logRequestResponse();
    }

    @Override
    public void PATCH() {
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .body(body.toString())
                .patch(uri);

        super.log("PATCH");
    }

    @Override
    public void DELETE() {
        request = RestAssured.given().log().all().relaxedHTTPSValidation();
        preencherDadosRequest();

        response = rest.executarRestRequest(Method.DELETE, request);

        super.logRequestResponse();
    }

    private void preencherDadosRequest() {
        try{
        httpRequest = (FilterableRequestSpecification) request;
        request.queryParams(super.params);
        request.headers(super.headers);
        request.basePath(super.uri);
        request.baseUri(super.url);}
        catch (Exception e)
        {
            logError(e.toString());
        }
    }
    private void RequestBody()
    {
        preencherDadosRequest();
        request.contentType(ContentType.JSON);
        request.body(super.body.toString());
    }
}
