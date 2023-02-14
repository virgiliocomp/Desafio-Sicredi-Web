package com.provaSicredi.apitests.tests;

import com.provaSicredi.apitests.dto.RestricoesDTO;
import com.provaSicredi.apitests.dto.SimulacaoDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProvaTecnicaTest {
    private static RequestSpecification spec;

    @BeforeAll
    public static void initSpec(){
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080/")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    @Order(1)
    public void consultarCpfComRestricao(){
        RestricoesDTO cpfComRestricao = new RestricoesDTO()
                .setCpf("60094146012");

        RestricoesDTO retrievedRestricao = given()
                .spec(spec)
                .when()
                .get("api/v1/restricoes/" + cpfComRestricao.getCpf())
                .then()
                .statusCode(200)
                .extract().as(RestricoesDTO.class);
        Assertions.assertEquals("O CPF 60094146012 tem problema", retrievedRestricao.getMensagem());
    }

    @Test
    @Order(2)
    public void consultarCpfSemRestricao(){
        RestricoesDTO cpfSemRestricao = new RestricoesDTO()
                .setCpf("11615734619");

        ValidatableResponse retrievedRestricao = given()
                .spec(spec)
                .when()
                .get("api/v1/restricoes/" + cpfSemRestricao.getCpf())
                .then()
                .statusCode(204);
    }

    @Test
    @Order(3)
    public void criarEValidarSimulacao(){
        SimulacaoDTO novaSimulacao = criarDummySimulacao();
        SimulacaoDTO simulacaoResource = createSimulacao("api/v1/simulacoes", novaSimulacao);
        SimulacaoDTO retrievedSimulacao = getSimulacao("api/v1/simulacoes/", "11615734619");
        Assertions.assertEquals(simulacaoResource.getId(), retrievedSimulacao.getId());

        deleteSimulacao("/api/v1/simulacoes/",simulacaoResource.getId());
    }

    @Test
    @Order(4)
    public void criarEValidarSimulacaoSemCpf(){
        SimulacaoDTO novaSimulacao = criarDummySimulacaoSemCpf();
        SimulacaoDTO simulacaoResource = createSimulacao("api/v1/simulacoes", novaSimulacao);
        SimulacaoDTO retrievedSimulacao = getSimulacaoErro("api/v1/simulacoes/", "11615734619");

        deleteSimulacao("/api/v1/simulacoes/",simulacaoResource.getId());
    }


    @Test
    @Order(5)
    public void getAllSimulacoesList(){
        SimulacaoDTO[] retrievedSimulacoes = given()
                .spec(spec)
                .when()
                .get("/api/v1/simulacoes")
                .then()
                .statusCode(200)
                .extract().as(SimulacaoDTO[].class);
        assertThat(retrievedSimulacoes.length).isGreaterThan(0);
    }

    @Test
    @Order(6)
    public void getAllSimulacoesListErro(){
        limpaBase();

       SimulacaoDTO[] retrievedSimulacoes = given()
                .spec(spec)
                .when()
                .get("/api/v1/simulacoes")
                .then()
                .statusCode(200)
                .extract().as(SimulacaoDTO[].class);
        assertThat(retrievedSimulacoes.length).isEqualTo(0);
    }

    @Test
    @Order(7)
    public void getSimulacaoByCpf(){
        SimulacaoDTO novaSimulacao = criarDummySimulacao();
        SimulacaoDTO simulacaoResource = createSimulacao("api/v1/simulacoes", novaSimulacao);
        SimulacaoDTO retrievedSimulacao = getSimulacao("api/v1/simulacoes/", "11615734619");

        Assertions.assertEquals(simulacaoResource.getId(), retrievedSimulacao.getId());

        deleteSimulacao("/api/v1/simulacoes/",simulacaoResource.getId());


    }

    @Test
    @Order(8)
    public void erroGetSimulacaoByCpf(){
        SimulacaoDTO retrievedSimulacao = getSimulacaoErro("api/v1/simulacoes/", "75360381604");
    }

    @Test
    @Order(9)
    public void removerEValidarSimulacaoById(){
        SimulacaoDTO novaSimulacao = criarDummySimulacao();
        SimulacaoDTO simulacaoResource = createSimulacao("api/v1/simulacoes", novaSimulacao);
        SimulacaoDTO retrievedSimulacao = getSimulacao("api/v1/simulacoes/", "11615734619");

        deleteSimulacao("/api/v1/simulacoes/",retrievedSimulacao.getId());
        getSimulacaoErro("/api/v1/simulacoes/", retrievedSimulacao.getCpf());



    }

    //Documentacao errada
    @Test
    @Order(10)
    public void erroRemoverEValidarSimulacaoById(){

        deleteSimulacaoErro("/api/v1/simulacoes/", 9999);
    }





    private SimulacaoDTO criarDummySimulacaoSemCpf(){
        return new SimulacaoDTO()
                .setCpf("")
                .setEmail("virgiliocomp@gmail.com")
                .setNome("Virgilio Cruvinel")
                .setValor(1050)
                .setParcelas(5)
                .setSeguro(true);
    }


    private SimulacaoDTO criarDummySimulacao(){
        return new SimulacaoDTO()
                .setCpf("11615734619")
                .setEmail("virgiliocomp@gmail.com")
                .setNome("Virgilio Cruvinel")
                .setValor(1050)
                .setParcelas(5)
                .setSeguro(true);
    }

    private SimulacaoDTO createSimulacao(String path, Object bodyPayLoad){
        return given()
                .spec(spec)
                .body(bodyPayLoad)
                .when()
                .post(path)
                .then()
                .statusCode(201)
                .extract().as(SimulacaoDTO.class);
    }
    private SimulacaoDTO createSimulacaoSemCpf(String path, Object bodyPayLoad){
        return given()
                .spec(spec)
                .body(bodyPayLoad)
                .when()
                .post(path)
                .then()
                .statusCode(400)
                .extract().as(SimulacaoDTO.class);
    }

    private SimulacaoDTO getSimulacao(String path, String cpf){
        return given()
                .spec(spec)
                .when()
                .get(path + cpf)
                .then()
                .statusCode(200)
                .extract().as(SimulacaoDTO.class);
    }

    private SimulacaoDTO getSimulacaoErro(String path, String cpf){
        return given()
                .spec(spec)
                .when()
                .get(path + cpf)
                .then()
                .statusCode(404)
                .extract().as(SimulacaoDTO.class);

    }

    private void deleteSimulacao(String path, int id){
          given()
                .spec(spec)
                .when()
                .delete(path + id)
                .then()
                .statusCode(200);

    }

    private void limpaBase(){
        SimulacaoDTO[] retrievedSimulacoes = given()
                .spec(spec)
                .when()
                .get("/api/v1/simulacoes")
                .then()
                .extract().as(SimulacaoDTO[].class);
        for (SimulacaoDTO retrievedSimulacao : retrievedSimulacoes) {
            deleteSimulacao("/api/v1/simulacoes/", retrievedSimulacao.getId());
        }
    }
    private void deleteSimulacaoErro(String path, int id){
         given()
                .spec(spec)
                .when()
                .delete(path + id)
                .then()
                .statusCode(404);
    }
}
