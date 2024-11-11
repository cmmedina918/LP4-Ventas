package org.example.model;

public class Marca {
    private int id;
    private String description;
    private String estado;

    public Marca() {}

    public Marca(int id, String description, String estado) {
        this.id = id;
        this.description = description;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
