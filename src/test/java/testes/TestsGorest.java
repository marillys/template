package testes;

import base.BaseTests;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testes.modelos.UsersLombok;

public class TestsGorest extends BaseTests {
    UsersLombok user;
    Faker faker = new Faker();

    @BeforeClass
    public void configClasse() {
        super.uri = prop.getProp("url.gorest") + "/v1/users";
        super.url = "";
    }

    @Test
    public void cadastrarNovoUsuario() {
        super.token = prop.getProp("token_gorest");
        super.headers.put("Accept", "application/json");
        super.headers.put("Authorization", token);

        user = UsersLombok.builder()
                .email(faker.internet().emailAddress())
                .name(faker.name().fullName())
                .gender("female")
                .status("inactive")
                .build();

        super.body = new JSONObject((new Gson().toJson(user)));

        super.POST();
    }
}
