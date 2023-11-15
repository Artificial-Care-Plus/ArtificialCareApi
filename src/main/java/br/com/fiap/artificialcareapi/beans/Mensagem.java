package br.com.fiap.artificialcareapi.beans;

public class Mensagem {
    private String resposta;
    private boolean sucesso;

    public Mensagem(String resposta, boolean sucesso) {
        this.resposta = resposta;
        this.sucesso = sucesso;
    }

    public Mensagem() {
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }
}
