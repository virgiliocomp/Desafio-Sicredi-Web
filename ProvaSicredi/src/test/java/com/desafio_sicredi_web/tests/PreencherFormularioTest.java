package com.desafio_sicredi_web.tests;

import com.desafio_sicredi_web.webpages.AddRecordPage;
import com.desafio_sicredi_web.webpages.HomePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PreencherFormularioTest {
    WebDriver driver;

    @BeforeEach
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void desafio1() throws InterruptedException {
        List<String> dadosFormulario = new ArrayList<>();
        dadosFormulario.add("Teste Sicredi");
        dadosFormulario.add("Teste");
        dadosFormulario.add("seu nome");
        dadosFormulario.add("51 9999-9999");
        dadosFormulario.add("Av Assis Brasil, 3970");
        dadosFormulario.add("Torre D");
        dadosFormulario.add("Porto Alegre");
        dadosFormulario.add("RS");
        dadosFormulario.add("91000-000");
        dadosFormulario.add("Brasil");
        dadosFormulario.add("");
        dadosFormulario.add("200");
        dadosFormulario.add("");
        
        HomePage home = new HomePage(driver);
        AddRecordPage addRecordPage = new AddRecordPage(driver);
        home.mudarValorSelect("Bootstrap V4 Theme");
        home.clicarBotaoAddRecord();
        addRecordPage.preencherFormulario(dadosFormulario);
        addRecordPage.clicarBotaoSave();
        Assertions.assertEquals("Your data has been successfully stored into the database. Edit Record or Go back to list",home.mensagemSucesso());

        limpaBase();
    }

    @Test
    public void desafio2() throws InterruptedException {
        List<String> dadosFormulario = new ArrayList<>();
        dadosFormulario.add("Teste Sicredi");
        dadosFormulario.add("Teste");
        dadosFormulario.add("seu nome");
        dadosFormulario.add("51 9999-9999");
        dadosFormulario.add("Av Assis Brasil, 3970");
        dadosFormulario.add("Torre D");
        dadosFormulario.add("Porto Alegre");
        dadosFormulario.add("RS");
        dadosFormulario.add("91000-000");
        dadosFormulario.add("Brasil");
        dadosFormulario.add("");
        dadosFormulario.add("200");
        dadosFormulario.add("");

        HomePage home = new HomePage(driver);
        AddRecordPage addRecordPage = new AddRecordPage(driver);
        home.mudarValorSelect("Bootstrap V4 Theme");
        home.clicarBotaoAddRecord();
        addRecordPage.preencherFormulario(dadosFormulario);
        addRecordPage.clicarBotaoSave();

        home.clicarLinkGoBackToList();
        home.preencherInputCustomerName("Teste Sicredi");
        Thread.sleep(5000);
        home.preencherCheckboxActions();
        home.clicarBotaoDelete();
        Assertions.assertEquals("Are you sure that you want to delete this 1 item?", home.mensagemPopupDelete());
        home.clicarBotaoPopupDelete();
        Thread.sleep(3000);
        Assertions.assertEquals("Your data has been successfully deleted from the database.", home.mensagemCaixaConfirmacao());
    }


    @AfterEach
    public void quit(){
        driver.quit();
    }

    public void limpaBase() throws InterruptedException {
        HomePage home = new HomePage(driver);
        home.preencherInputCustomerName("Teste Sicredi");
        Thread.sleep(3000);
        home.preencherCheckboxActions();
        home.clicarBotaoDelete();
        home.clicarBotaoPopupDelete();
    }
}
