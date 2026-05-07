package com.web_socket.sisssp.enums;

public enum TipoConversa {
    PRIVADA("PRIVADA"),
    GRUPO("GRUPO");

    private String tipoConversa;
    TipoConversa(String tipoConversa) {
        this.tipoConversa = tipoConversa;
    }
    public String getTipoConversa() {
        return tipoConversa;
    }

    public void setTipoConversa(String tipoConversa) {
        this.tipoConversa = tipoConversa;
    }
}
