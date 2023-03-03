package api;

import io.restassured.http.Method;
import utils.ApiUtils;
import utils.RestAssuredUtils;

import static io.restassured.RestAssured.given;

public class ApiRequest extends ApiUtils implements ApiVerbos {
    RestAssuredUtils rest = new RestAssuredUtils();

    @Override
    public void GET() {
        preencherDadosRequest();

        response = rest.executarRestRequestRequestSpecification(Method.GET, super.request);

        super.log("GET");
    }

    @Override
    public void POST() {
        preencherDadosRequest();

        response = rest.executarRestRequestRequestSpecification(Method.POST, super.request);

        super.log("POST");
    }

    @Override
    public void PUT() {
        preencherDadosRequest();

        response = rest.executarRestRequestRequestSpecification(Method.PUT, super.request);

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
        preencherDadosRequest();

        response = rest.executarRestRequestRequestSpecification(Method.DELETE, super.request);

        super.log("DELETE");
    }

    private void preencherDadosRequest() {
        super.request.queryParams(super.params);
        super.request.basePath(super.uri);
        super.request.headers(super.headers);
    }
}
