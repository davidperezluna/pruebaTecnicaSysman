package com.tecnica.messages;

public enum ExceptionMessages {

    ERROR203("El dato a actualizar no se encuentra en el sistema");

    private String description;

    private ExceptionMessages(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getMensajeConParametros(String... params) {
        String mensaje = String.format(description, (Object[]) params);
        return mensaje;
    }
}
