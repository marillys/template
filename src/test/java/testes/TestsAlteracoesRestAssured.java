package testes;

import base.BaseTests;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestsAlteracoesRestAssured extends BaseTests {

    @BeforeSuite
    public void configClasse()
    {
        //Inicializar variáveis também
        super.request.baseUri(prop.getProp("base_url"));
        super.request.basePath("/data/2.5/weather");
    }

    @Test
    public void testeMethod()
    {
        super.params.put("q","Fortaleza,BR");
        super.params.put("appid", "6a6424d1582a9636d6d186ea0cc53620");
        super.params.put("units","metric");

        super.GET();
        Assert.assertEquals(response.getStatusCode(),400);
    }

    @Test
    public void testeSucesso()
    {
        super.params.put("q","Fortaleza,BR");
        super.params.put("appid", "6a6424d1582a9636d6d186ea0cc53620");
        super.params.put("units","metric");

        super.request.contentType(ContentType.JSON);

        super.GET();
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
