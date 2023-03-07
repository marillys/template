package testes;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestsAlteracoesRestAssured extends BaseTests {

    @BeforeClass
    public void configClasse() {
        super.uri = "/data/2.5/weather";
        super.url = prop.getProp("base_url");
    }

    @Test
    public void testeMethod() {
        super.params.put("q", "Fortaleza,BR");
        super.params.put("appid", "6a6424d1582a9636d6d186ea0cc53620");
        super.params.put("units", "metric");

        super.GET();
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    public void testeSucesso() {
        super.params.put("q", "Fortaleza,BR");
        super.params.put("appid", "6a6424d1582a9636d6d186ea0cc53620");
        super.params.put("units", "metric");
        super.headers.put("Accept", "application/json");

        super.GET();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
