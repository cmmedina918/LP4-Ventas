/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.model;

/**
 *
 * @author DELL
 */
public class vDepto {
    private int idDepto;
    private int idPaises;
    private String nombre;
    private String estado;

    public vDepto() {
    }

    public vDepto(int idDepto, int idPaises, String nombre, String estado) {
        this.idDepto = idDepto;
        this.idPaises = idPaises;
        this.nombre = nombre;
        this.estado = estado;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }

    public int getIdPaises() {
        return idPaises;
    }

    public void setIdPaises(int idPaises) {
        this.idPaises = idPaises;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
