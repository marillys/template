package testes;

import base.BaseTests;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class TestsPets extends BaseTests {

    @BeforeClass
    public void configClasse()
    {
        super.url = prop.getProp("base_url_pets");
    }

    @BeforeMethod
    public void config(){
        super.uri ="/pet";
    }

    @Test
    public void cadastrarPet_ArquivoJson() throws IOException {
        String json = arquivos.lerJson("src/test/resources/data/petCadastrar.json");
        super.headers.put("Accept", "application/json");
        super.headers.put("Content-Type", "application/json");
        super.body = new JSONObject(json);

        super.POST();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getBody().jsonPath().get("id").toString(),"8989");
        Assert.assertEquals(response.getBody().jsonPath().get("name").toString(),"Lili");
        Assert.assertEquals(response.getBody().jsonPath().get("status").toString(),"ativo");
    }

    @Test
    public void consultarPet()
    {
        String petId = "8989";
        super.headers.put("Accept", "application/json");
        super.headers.put("Content-Type", "application/json");
        super.uri +="/"+petId;

        GET();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getBody().jsonPath().get("name").toString(),"Lili");
        Assert.assertEquals(response.getBody().jsonPath().get("category.name").toString(),"dog");
    }

    @Test
    public void alterarPet_ArquivoJSON() throws IOException {
        String jsonBody = arquivos.lerJson("src/test/resources/data/petAlterar.json");

        super.headers.put("Accept", "application/json");
        super.headers.put("Content-Type", "application/json");
        super.body = new JSONObject(jsonBody);

        super.PUT();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getBody().jsonPath().get("id").toString(),"4203");
        Assert.assertEquals(response.getBody().jsonPath().get("name").toString(),"Lili");
        Assert.assertEquals(response.getBody().jsonPath().get("status").toString(),"adopted");
    }

    @Test
    public void excluirPet()
    {
        String petId = "8989";
        super.headers.put("Accept", "application/json");
        super.headers.put("Content-Type", "application/json");
        super.uri +="/"+petId;

        super.DELETE();

        Assert.assertEquals(response.getStatusCode(),200);
    }
}