package api;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
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

        response = rest.executarRestRequestRequestSpecification(Method.GET, super.url+super.uri, request);

        super.log("GET");
    }

    @Override
    public void POST() {
        request = RestAssured.given().log().all().relaxedHTTPSValidation();
        preencherDadosRequest();
        request.body(super.body.toString());

        response = rest.executarRestRequestRequestSpecification(Method.POST, super.url+super.uri,request);

        super.log("POST");
    }

    @Override
    public void PUT() {
        request = RestAssured.given().log().all().relaxedHTTPSValidation();
        preencherDadosRequest();
        request.body(super.body.toString());

        response = rest.executarRestRequestRequestSpecification(Method.PUT, super.url+super.uri, request);

        super.log("PUT");
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

        response = rest.executarRestRequestRequestSpecification(Method.DELETE, super.url+super.uri, request);

        super.log("DELETE");
    }

    private void preencherDadosRequest() {
        request.queryParams(super.params);
        request.headers(super.headers);
    }
}
