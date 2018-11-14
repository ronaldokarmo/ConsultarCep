package br.com.automation.cielo.restassured;

import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class RestAssured {

	public RestAssured() {

		baseURI = "http://api.postmon.com.br/v1/";
	}

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
	
	@Test
	public void TestConsultarCepGetFail() {
		
		String numeroCep = "99999999";

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
}