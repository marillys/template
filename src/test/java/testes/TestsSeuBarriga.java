package testes;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.PropertiesUtils;

public class TestsSeuBarriga extends BaseTests {
    PropertiesUtils prop = new PropertiesUtils();

    @BeforeSuite
    public void configClasse()
    {
        //Inicializar variáveis também
        super.uri = prop.getProp("base_url_seuBarriga") + "/logar";
    }

    @Test
    public void testeAplicacaoWeb()
    {
        super.uri = prop.getProp("base_url_seuBarriga") + "/logar";

        super.formParam.put("senha", "Marilia");
        super.formParam.put("email", "marilia@a");

        super.headers.put("charset","utf-8");
        super.headers.put("Content-Type","application/x-www-form-urlencoded");

        super.POST();

        Assert.assertEquals(super.response.getStatusCode(),200);
    }
}
