package testes;

import base.BaseTests;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testes.modelos.UsersLombok;

public class TestsGorest extends BaseTests {
    UsersLombok user;
    Faker faker = new Faker();

    @BeforeClass
    public void configClasse() {
        super.uri = "/v1/users";
        super.url = prop.getProp("url.gorest");
    }

    @Test
    public void cadastrarNovoUsuario() {
        super.token = prop.getProp("token_gorest");
        //super.headers.put("Accept", "application/json");
        super.headers.put("Authorization", token);

        user = UsersLombok.builder()
                .email(faker.internet().emailAddress())
                .name(faker.name().fullName())
                .gender("female")
                .status("inactive")
                .build();

        super.body = new JSONObject((new Gson().toJson(user)));

        super.POST();
        Assert.assertEquals(201, response.statusCode());
    }
}
