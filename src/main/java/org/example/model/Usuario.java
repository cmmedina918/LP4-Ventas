package org.example.model;

public class Usuario {
    private int id;
    private String user;
    private String password;
    private String acceso;
    private String status;
    private String nombre;
    private String apellido;
    private String ciNro;

    public Usuario() {
    }


    public Usuario(int id,
                   String user,
                   String password,
                   String acceso,
                   String status,
                   String nombre,
                   String apellido,
                   String ciNro) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.acceso = acceso;
        this.status = status;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciNro = ciNro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCiNro() {
        return ciNro;
    }

    public void setCiNro(String ciNro) {
        this.ciNro = ciNro;
    }
}
