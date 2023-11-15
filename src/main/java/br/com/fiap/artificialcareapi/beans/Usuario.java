package br.com.fiap.artificialcareapi.beans;

import java.sql.Date;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Date nascimento;
    private double peso;
    private Double altura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Usuario(Long id, String nome, String email, String senha, Date nascimento, double peso, Double altura) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.nascimento = nascimento;
        this.peso = peso;
        this.altura = altura;
    }

    public Usuario(String nome, String email, String senha, Date nascimento, double peso, Double altura) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.nascimento = nascimento;
        this.peso = peso;
        this.altura = altura;
    }

    public Usuario(String email) {
        this.email = email;
    }

    public Usuario() {
    }
}
