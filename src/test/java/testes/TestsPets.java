package testes;

import base.BaseTests;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestsPets extends BaseTests {

    @BeforeClass
    public void configClasse()
    {
        super.request.baseUri(prop.getProp("base_url_pets"));
        super.uri ="/pet";
    }

    @Test
    public void cadastrarPet_ArquivoJson() throws IOException {
        String body = arquivos.lerJson("src/test/resources/data/petCadastrar.json");

        super.request.body(body);
        super.request.contentType(ContentType.JSON);

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
        super.request.contentType(ContentType.JSON);
        super.uri ="/pet/"+petId;

        super.GET();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getBody().jsonPath().get("name").toString(),"Lili");
        Assert.assertEquals(response.getBody().jsonPath().get("category.name").toString(),"Dogs");
    }

    @Test
    public void alterarPet_ArquivoJSON() throws IOException {
        String jsonBody = arquivos.lerJson("src/test/resources/data/petAlterar.json");

        super.request.contentType(ContentType.JSON);
        super.request.body(jsonBody);

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
        super.request.contentType(ContentType.JSON);
        super.uri ="/pet/"+petId;

        super.DELETE();

        Assert.assertEquals(response.getStatusCode(),200);
    }
}