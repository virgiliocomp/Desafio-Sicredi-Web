package com.provaSicredi.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePage {
    private WebDriver driver;

    private static String URL = "https://www.grocerycrud.com/v1.x/demo/my_boss_is_in_a_hurry/bootstrap";

    @FindBy(xpath = "//select[@id='switch-version-select']")
    private WebElement selectVersionSelect;

    @FindBy(xpath = "//div[@class='floatL t5']//a[@class='btn btn-default btn-outline-dark']")
    private WebElement botaoAddRecord;


    @FindBy(xpath = "//*[@id='report-success']/p")
    private WebElement textoSucesso1;
    @FindBy(xpath = "//*[@id='report-success']/p/a[1]")
    private WebElement link1;
    @FindBy(xpath = "//*[@id='report-success']/p/a[2]")
    private WebElement link2;

    @FindBy(xpath = "//*[@id='gcrud-search-form']/div[2]/table/thead/tr[2]/td[3]/input")
    private WebElement inputSearchCustomerName;

    @FindBy(xpath = "//*[@id=\"gcrud-search-form\"]/div[2]/table/thead/tr[2]/td[1]/div/input")
    private WebElement checkboxActions;

    @FindBy(xpath = "//*[@id=\"gcrud-search-form\"]/div[2]/table/thead/tr[2]/td[2]/div[1]/a/span")
    private WebElement botaoDelete;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div[3]/div/div/div[2]/p[2]")
    private WebElement textoPopupDelete;

    @FindBy(xpath = "//button[@class='btn btn-danger delete-multiple-confirmation-button']")
    private WebElement botaoPopupDelete;

    @FindBy(xpath = "/html/body/div[3]/span[3]")
    private WebElement caixaConfirmacao;
    public HomePage(WebDriver driver){
        this.driver = driver;
        driver.get(URL);

        PageFactory.initElements(driver,this);
    }

    public void mudarValorSelect(String option){
        Select versionSelect = new Select(selectVersionSelect);
        versionSelect.selectByVisibleText(option);
    }

    public void clicarBotaoAddRecord(){
        botaoAddRecord.click();
    }



    public String mensagemSucesso() throws InterruptedException {
        String s = textoSucesso1.getText();
        Thread.sleep(1000);
        String hiperlink1 = link1.getText();
        Thread.sleep(1000);
        String hiperlink2 = link2.getText();
        Thread.sleep(1000);
        String [] arrayS = s.split("[.]");
        return arrayS[0]+ ". " + hiperlink1 + arrayS[1] + " " + hiperlink2;
    }

    public void clicarLinkGoBackToList(){
        link2.click();
    }

    public void preencherInputCustomerName(String texto){
        inputSearchCustomerName.sendKeys(texto);
    }

    public void preencherCheckboxActions(){
        checkboxActions.click();
    }

    public void clicarBotaoDelete(){
        botaoDelete.click();
    }

    public String mensagemPopupDelete(){
        return textoPopupDelete.getText();
    }

    public void clicarBotaoPopupDelete(){
        botaoPopupDelete.click();
    }

    public String mensagemCaixaConfirmacao(){
        return caixaConfirmacao.getText();
    }
}
