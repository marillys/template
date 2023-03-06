# Template
## Descrição do Projeto
Este é um projeto base para executar testes de API e ao final da execução gerar relatório do Extent Reports.

> Status do Projeto: Em desenvolvimento :warning:

## Tecnologias
- Extent Report;
- TestNG;
- REST-assured.

## Configuração
No arquivo de configuração [config.properties](src/test/resources/config.properties) pode se definir as propriedades de configuração do relatório e dos testes também.
As primeiras linhas são reservadas para as configurações do relatório como nome e endereço do arquivo, título e nome do relatório e formato de data dentro do documento. As demais, não são obrigadas a existirem e estão relacionadas com os testes de exemplo.

Ex de configuração do relatório:
```properties
# ----------------- Configuração do relatório ------------------
caminhoRelatorio = ./target/reports/
nomeArquivo = relatorio2.htm
tituloDocumento = Relatório Template
nomeRelatorio = Resultados dos Testes de Exemplo
formatoData = dd/MM/yyyy HH:mm:ss.SSS
````

## Como rodar a aplicação
1. Clone o projeto;
2. Entre na pasta [testes](src/test/java/testes), escolha uma das classes e execute ou se preferir executar todos os testes, digite o comando abaixo no cmd;
```bash
mvn clean test
```

## Formas de criar testes

1. Através de classes na pasta [testes](src/test/java/testes)

As classes de testes devem herdar a BaseTests por causa das configurações do relatório do Extent Reports. Ex:
```java
public class TestsPets extends BaseTests
{
    
}
```

Cada classe de teste precisa ter um _@BeforeClass_ contendo as pré configurações de ambientes como _basePath_ e _baseUri_.
> o base_url_pets do trecho de código abaixo vem do arquivo de [configuração](src/test/resources/config.properties)
Ex. de Before
   ```java
public class TestsPets extends BaseTests {
    @BeforeClass
    public void configClasse() {
        super.request.baseUri(prop.getProp("base_url_pets"));
        super.uri = "/pet";
    }
}
   ```

Abaixo temos um exemplo de teste

```java
public class TestsPets extends BaseTests {
    @Test
    public void teste() throws IOException {
        //populando os dados da request

        String body = arquivos.lerJson("src/test/resources/data/petCadastrar.json");
        super.request.body(body);
        super.request.contentType(ContentType.JSON);

        //Enviando requisição
        super.POST();

        //Validações
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
   ```

## Utilizando body da request em forma de arquivo
Você consegue salvar o JSON da requisição em um arquivo. O único detalhe é que sempre que for enviada uma requisição, o json não mudará automaticamente. 
Para isso, inclua os arquivos na pasta [data](src/test/resources/data) para manter a organização. Informe endereço do arquivo + nome como parametro do método lerJson("arquivo")
da classe de [ManipulacaoArquivos](src/test/java/utils/ManipulacaoArquivos.java). 
- Exemplo de como usar
```java
public class TestsPets extends BaseTests {
    @Test
    public void teste() throws IOException {
        String body = arquivos.lerJson("src/test/resources/data/petCadastrar.json");
    }
}
```
- Exemplo de arquivo .json contendo o body da requisição
```json
{
  "id": 4203,
  "category": {
    "id": 1,
    "name": "dog"
  },
  "name": "Lili",
  "photoUrls": [
    "string"
  ],
  "tags": [
    {
      "id": 1,
      "name": "adotar"
    }
  ],
  "status": "adopted"
}
```