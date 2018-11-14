# Como criar um teste automatizado para validar o Consumo da API dos Correios

Neste post será mostrado como criar um teste automatizado do método GET que valida o envio de retorno de informações com dados de endereços extraidos diretamente da API dos Correios.</br>

No cenário a seguir, esta descrito o desejo do usuário na busca de informações de endereços disponibilizados pelos Correios, que através do projeto [Postmon API](https://postmon.com.br/) retorna os dados de endereços por meio da consulta do CEP que aujará a enriquecer as funções de cadastros das aplicações web externas.</br>

> <strong>Cenário</strong>: Eu como usuário do site dos correios quero buscar informações do meu endereço para enriquecer meu cadastro de clientes.</br>
> <strong>Dado</strong> um CEP válido </br>
> <strong>Quando</strong> buscar pelo endereço do CEP fornecido</br>
> <strong>Então</strong> deve ser retornado um endereço válido</br>
> <strong>E</strong> conter logradouro, bairro, cidade e estado.</br>

A primeira coisa que vamos precisar é configurar o ambiente para desenvolver o teste e essas são as ferramentas que vamos utilizar são:
* Java JRE 1.8.0_144
* IDE Eclipse
* Maven

Agora basta abrir nossa IDE Eclipse e criar um novo projeto Maven clicando em File > New > Project > Maven > Maven Project, e em seguida configurar as dependencias abaixo no arquivo POM.xml.
```sh
<dependencies>
  <dependency>
      <groupId>com.jayway.restassured</groupId>
      <artifactId>rest-assured</artifactId>
      <version>2.4.1</version>
      <scope>test</scope>
  </dependency>
  <dependency>
      <groupId>com.jayway.restassured</groupId>
      <artifactId>json-path</artifactId>
      <version>2.4.1</version>
  </dependency>
  <dependency>
      <groupId>com.jayway.restassured</groupId>
      <artifactId>json-schema-validator</artifactId>
      <version>2.4.1</version>
      <scope>test</scope>
  </dependency>
  <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
  </dependency>		
</dependencies>
```

Agora vamos criar uma classe java do tipo "JUnit Test" Case com o nome <strong>RestAssured</strong> e adiconar os imports
```sh
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
```

No construtor da classe adicione a URL do serviço que será automatizado.</br>
```sh
public class RestAssured {

	public RestAssured() {
		baseURI = "http://api.postmon.com.br/v1/";
	}
}
```

Como próximo passo, sera criado o método de Teste que validará o envio da requisição com o método do tipo GET, que foi desenvolvido na estrutura baseada em conceitos ágeis com a utilização das palavras chaves Given, When e Then aonde:
* O método Given entende que a URL a ser chamada é a definida no construtor da classe;
* No método When indica que a requisição foi feita usando o método do tipo GET;
* O método Then valida o resultado esperado é que o status de retorno igual a 200 e que JSON enviado no retorno contenha os dados logradouro, bairro, cidade e estado.

```sh
@Test
	public void TestConsultarCepGet() {
		
		String numeroCep = "06436020";

		given().contentType("application/json")
		
		.when().get("cep/"+numeroCep)
		
		.then()		
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("logradouro", equalTo("Rua Vera"))
			.body("bairro", equalTo("Parque dos Camargos"))
			.body("cidade", equalTo("Barueri"))		
			.body("estado", equalTo("SP"));
	}
```
# Outros recursos utilizados
Para uma boa legibilidade do código foram utilizadas as tecnologias:

* Linguaguem Java: Como linguagem de programação.
* Framework Junit: Por oferecer o suporte à criação de testes automatizados na linguagem de programação Java.
* Framework RestAssured: Por possuir suporte para automatizar o consumo da API com método do tipo GET, além de permitir fazer as validações dos retornos enviados utilizando do conceito de BDD.

Pronto!! Difícil? Não 🙂. Com poucos passos criamos um teste automatizado para Consumir a API dos Correios.

Agora para executar o teste, basta clonar ou baixar o código fonte a partir da ferramenta GIT, depois abrir o projeto no IDE Eclipse, em seguia clicar com o botão direito no projeto e por fim selecionar o menu “Run As > JUnit Test“.
