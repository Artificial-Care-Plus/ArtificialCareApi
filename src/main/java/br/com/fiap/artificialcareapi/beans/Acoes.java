package br.com.fiap.artificialcareapi.beans;

import java.sql.Date;

public class Acoes {
    private Long id;
    private Long idUsuario;
    private int score;
    private String descricao;
    private double duracao;
    private Date data;


    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Acoes(Long id, Long idUsuario, int score, String descricao, double duracao, Date data) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.score = score;
        this.descricao = descricao;
        this.duracao = duracao;
        this.data = data;
    }

    public Acoes(int score, String descricao, double duracao, Date data) {
        this.score = score;
        this.descricao = descricao;
        this.duracao = duracao;
        this.data = data;
    }

    public Acoes() {
    }
}
