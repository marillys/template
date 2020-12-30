package testes;

import base.BaseTests;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestsValidando extends BaseTests {

    @Test
    public void teste()
    {
        given()
                .log().all()
                .queryParam("q", "Fortaleza,BR")
                .queryParam("appid", "6a6424d1582a9636d6d186ea0cc53620")
                .queryParam("units","metric")
                .when()
                .get("http://api.openweathermap.org/data/2.5/weather")
                .then().log().all();
    }
}
