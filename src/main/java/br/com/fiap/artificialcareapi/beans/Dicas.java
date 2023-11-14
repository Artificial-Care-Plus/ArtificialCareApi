package br.com.fiap.artificialcareapi.beans;

public class Dicas {
    private Long id;
    private String categoria;
    private String texto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Dicas(Long id, String categoria, String texto) {
        this.id = id;
        this.categoria = categoria;
        this.texto = texto;
    }

    public Dicas(String categoria, String texto) {
        this.categoria = categoria;
        this.texto = texto;
    }

    public Dicas() {
    }
}
