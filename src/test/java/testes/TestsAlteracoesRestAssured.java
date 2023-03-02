package testes;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestsAlteracoesRestAssured extends BaseTests {

    @Test
    public void testeMethodo()
    {
        super.params.put("q","Fortaleza,BR");
        super.params.put("appid", "6a6424d1582a9636d6d186ea0cc53620");
        super.params.put("units","metric");

        super.GET();
        Assert.assertEquals(response.getStatusCode(),400);
    }
}
