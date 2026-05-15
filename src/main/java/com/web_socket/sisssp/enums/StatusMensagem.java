package com.web_socket.sisssp.enums;

public enum StatusMensagem {

    LIDA("LIDA"),
    NAO_LIDA("NAO_LIDA");

    private String tipoNotificacao;

    StatusMensagem(String tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public String getTipoNotificacao() {
        return tipoNotificacao;
    }
    public void setTipoNotificacao(String tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }
}
