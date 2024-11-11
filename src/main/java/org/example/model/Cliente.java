package org.example.model;

public class Cliente {
    private int id;
    private String nombre_razon;
    private String ci_ruc;
    private String nro;
    private String email;
    private int id_ciudad;
    private String direccion;

    public Cliente() {
    }

    public Cliente(int id,
                   String nombre_razon,
                   String ci_ruc,
                   String nro,
                   String email,
                   int id_ciudad,
                   String direccion) {
        this.id = id;
        this.nombre_razon = nombre_razon;
        this.ci_ruc = ci_ruc;
        this.nro = nro;
        this.email = email;
        this.id_ciudad = id_ciudad;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_razon() {
        return nombre_razon;
    }

    public void setNombre_razon(String nombre_razon) {
        this.nombre_razon = nombre_razon;
    }

    public String getCi_ruc() {
        return ci_ruc;
    }

    public void setCi_ruc(String ci_ruc) {
        this.ci_ruc = ci_ruc;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
