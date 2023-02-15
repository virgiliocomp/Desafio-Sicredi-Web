package com.provaSicredi.desafioapi.dto;

public class RestricoesDTO {
    private String cpf;
    private String url;
    private String mensagem;



    public String getUrl() {
        return url;
    }

    public RestricoesDTO setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public RestricoesDTO setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public String getMensagem() {
        return mensagem;
    }

    public RestricoesDTO setMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }




}
