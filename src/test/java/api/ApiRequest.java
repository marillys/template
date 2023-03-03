package api;

import io.restassured.http.Method;
import utils.ApiUtils;
import utils.RestAssuredUtils;

import static io.restassured.RestAssured.given;

public class ApiRequest extends ApiUtils implements ApiVerbos {
    RestAssuredUtils rest = new RestAssuredUtils();
    @Override
    public void GET() {
        super.request.params(super.params);
        super.request.baseUri(super.uri);

        response = rest.executarRestRequestRequestSpecification(Method.GET, super.request);

        super.log("GET");
    }

    @Override
    public void POST() {
        response = rest.executarRestRequestRequestSpecification(Method.POST, super.uri, super.params,
                super.formParam, super.headers, super.request);

        super.log("POST");
    }

    @Override
    public void PUT() {
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .body(body.toString())
                .put(uri);

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
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .delete(uri);

        super.log("DELETE");
    }
}
