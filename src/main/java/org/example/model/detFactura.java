package org.example.model;

public class detFactura {
    private int idCab;
    private int idProducto;
    private int cantidad;
    private Double precio;

    public detFactura() {
    }

    public detFactura(int idCab,
                      int idProducto,
                      int cantidad,
                      Double precio) {
        this.idCab = idCab;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getIdCab() {
        return idCab;
    }

    public void setIdCab(int idCab) {
        this.idCab = idCab;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
