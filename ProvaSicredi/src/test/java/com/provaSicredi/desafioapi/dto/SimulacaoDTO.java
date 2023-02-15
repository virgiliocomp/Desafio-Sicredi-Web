package com.provaSicredi.desafioapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimulacaoDTO {


    private int id;
    private String cpf;
    private String nome;
    private String email;
    private float valor;
    private int parcelas;
    private boolean seguro;


    public int getId() {
        return id;
    }

    public SimulacaoDTO setId(int id) {
        this.id = id;
        return this;
    }
    public String getCpf() {
        return cpf;
    }

    public SimulacaoDTO setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public SimulacaoDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SimulacaoDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public float getValor() {
        return valor;
    }

    public SimulacaoDTO setValor(float valor) {
        this.valor = valor;
        return this;
    }

    public int getParcelas() {
        return parcelas;
    }

    public SimulacaoDTO setParcelas(int parcelas) {
        this.parcelas = parcelas;
        return this;
    }

    public boolean isSeguro() {
        return seguro;
    }

    public SimulacaoDTO setSeguro(boolean seguro) {
        this.seguro = seguro;
        return this;
    }
}
