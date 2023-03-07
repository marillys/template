package steps;

import api.ApiHeaders;
import api.ApiRequest;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import testes.modelos.UsersLombok;
import utils.PropertiesUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GorestSteps extends ApiRequest {

    PropertiesUtils prop = new PropertiesUtils();
    //JsonUtils jsonUtils = new JsonUtils();
    ApiHeaders apiHeaders = new ApiHeaders();
    UsersLombok user;
    Faker faker = new Faker();
    String userId;

    Map<String, String> jsonValues = new HashMap<>();

    @After
    public void limparVariaveis(){
        super.body=null;
        super.token = "";
        super.headers = new HashMap<>();
        super.params = new HashMap<>();
    }

    public GorestSteps() {
        super.uri = "/v1/users";
        super.url = prop.getProp("url.gorest");
    }

    @Dado("que possuo gorest token valido")
    public void que_possuo_gorest_token_valido() {
        super.token = prop.getProp("token_gorest");
        super.headers.put("Authorization", super.token);
    }

    @Dado("que tenho os dados preenchidos corretamente")
    public void que_tenho_os_dados_preenchidos_corretamente() {
        user = UsersLombok.builder()
                .email(faker.internet().emailAddress())
                .name(faker.name().fullName())
                .gender("female")
                .status("inactive")
                .build();

        super.body = new JSONObject((new Gson().toJson(user)));
    }

    @Quando("envio um request de cadastro de usuário com dados validos")
    public void envio_um_request_de_cadastro_de_usuário_com_dados_validos() {
        super.POST();
    }

    @Entao("o usuario deve ser criado corretamente")
    public void o_usuario_deve_ser_criado_corretamente() {
        /*UsersLombok userRetorno = response.jsonPath().getObject("data", UsersLombok.class);

        assertEquals("Falha na comparação ", user, userRetorno);

        assertEquals(body.getString("email"),response.jsonPath().getString("data.email"));
        assertEquals(body.getString("name"),response.jsonPath().getString("data.name"));

        assertEquals(user, userRetorno);*/
    }

    @Entao("o status code do request deve ser {int}")
    public void o_status_code_do_request_deve_ser(int statusEsperado) {
        Assert.assertEquals(statusEsperado, response.statusCode());
    }

    @E("existe um usuario cadastrado na api")
    public void existeUmUsuarioCadastradoNaApi() {
        //userId ="30562";
        envio_um_request_de_cadastro_de_usuário_com_dados_validos();
    }

    @Quando("buscar esse usuario")
    public void buscarEsseUsuario() {
        super.uri = prop.getProp("url.gorest") + "/" + response.jsonPath().getJsonObject("data.id");
        //super.headers = apiHeaders.gorestHeaders(token);
        super.body = new JSONObject();

        super.GET();
    }

    @Entao("os dados dos usuarios devem ser retornados")
    public void osDadosDosUsuariosDevemSerRetornados() {
        System.out.println(response.asString());
    }

    @Quando("altero os dados do usuario")
    public void alteroOsDadosDoUsuario() {
        super.uri = prop.getProp("url.gorest") + "/" + response.jsonPath().getJsonObject("data.id");
        // super.headers = apiHeaders.gorestHeaders(token);

        super.body.put("status", "active");
        //user.setStatus("active");
        super.PUT();
    }

    @Entao("o usuario deve ser alterado com sucesso")
    public void oUsuarioDeveSerAlteradoComSucesso() {
        //assertEquals("Erro na comparação do objeto", user, response.jsonPath().getObject("data", UsersLombok.class));
    }

    @Quando("altero um ou mais dados do usuario")
    public void alteroUmOuMaisDadosDoUsuario() {
        super.uri = prop.getProp("url.gorest") + "/" + response.jsonPath().getJsonObject("data.id");
        //super.headers = apiHeaders.gorestHeaders(token);

        //user.setGender("male");
        super.body = new JSONObject("{\"gender\":\"male\"}");
        super.PATCH();
    }

    @Quando("deleto esse usuario")
    public void deletoEsseUsuario() {
        super.uri = prop.getProp("url.gorest") + "/" + response.jsonPath().getJsonObject("data.id");
        //super.headers = apiHeaders.gorestHeaders(token);
        super.body = new JSONObject();

        super.DELETE();
    }

    @Entao("o usuario e deletado corretamente")
    public void oUsuarioEDeletadoCorretamente() {
        assertEquals("", response.asString());
    }
}
