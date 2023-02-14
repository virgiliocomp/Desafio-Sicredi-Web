package com.provaSicredi.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddRecordPage {
    private WebDriver driver;

    @FindBy(xpath = "//input[@id='field-customerName']")
    private WebElement inputCustomerName;

    @FindBy(xpath = "//input[@id='field-contactLastName']")
    private WebElement inputContactLastName;

    @FindBy(xpath = "//input[@id='field-contactFirstName']")
    private WebElement inputContactFirstName;

    @FindBy(xpath = "//input[@id='field-phone']")
    private WebElement inputPhone;

    @FindBy(xpath = "//input[@id='field-addressLine1']")
    private WebElement inputAdressLine1;

    @FindBy(xpath = "//input[@id='field-addressLine2']")
    private WebElement inputAdressLine2;

    @FindBy(xpath = "//input[@id='field-city']")
    private WebElement inputCity;

    @FindBy(xpath = "//input[@id='field-state']")
    private WebElement inputState;

    @FindBy(xpath = "//input[@id='field-postalCode']")
    private WebElement inputPostalCode;

    @FindBy(xpath = "//input[@id='field-country']")
    private WebElement inputCountry;

    @FindBy(xpath = "//input[@id='field-salesRepEmployeeNumber']")
    private WebElement inputSalesRepEmployeeNumber;

    @FindBy(xpath = "//input[@id='field-creditLimit']")
    private WebElement inputCreditLimit;

    @FindBy(xpath = "//input[@id='field-deleted']")
    private WebElement inputDeleted;


    @FindBy(xpath = "//button[@id='form-button-save']")
    private WebElement botaoSave;


    public AddRecordPage(WebDriver driver){
        this.driver = driver;

        PageFactory.initElements(driver,this);
    }

    public void preencherFormulario(List<String> dadosFormulario){
        inputCustomerName.sendKeys(dadosFormulario.get(0));
        inputContactLastName.sendKeys(dadosFormulario.get(1));
        inputContactFirstName.sendKeys(dadosFormulario.get(2));
        inputPhone.sendKeys(dadosFormulario.get(3));
        inputAdressLine1.sendKeys(dadosFormulario.get(4));
        inputAdressLine2.sendKeys(dadosFormulario.get(5));
        inputCity.sendKeys(dadosFormulario.get(6));
        inputState.sendKeys(dadosFormulario.get(7));
        inputPostalCode.sendKeys(dadosFormulario.get(8));
        inputCountry.sendKeys(dadosFormulario.get(9));
        inputSalesRepEmployeeNumber.sendKeys(dadosFormulario.get(10));
        inputCreditLimit.sendKeys(dadosFormulario.get(11));
        inputDeleted.sendKeys(dadosFormulario.get(12));
    }

    public void clicarBotaoSave(){
        botaoSave.click();
    }

}
