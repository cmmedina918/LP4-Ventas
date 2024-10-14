/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.model;

/**
 *
 * @author DELL
 */
public class vCiudades {
    private int idCiudad;
    private int idDepto;
    private String nombre;
    private String estado;

    public vCiudades() {
    }

    public vCiudades(int idCiudad, int idDepto, String nombre, String estado) {
        this.idCiudad = idCiudad;
        this.idDepto = idDepto;
        this.nombre = nombre;
        this.estado = estado;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
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
