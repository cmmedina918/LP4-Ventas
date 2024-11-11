package org.example.model;

public class cabFactura {
    private int id;
    private int idCliente;
    private String iva;
    private String fecha;
    private String estado;
    private String tipo;
    private Double total;
    private String tipo_venta;

    public cabFactura() {
    }

    public cabFactura(int id,
                      int idCliente,
                      String iva,
                      String fecha,
                      String estado,
                      Double total,
                      String tipo,
                      String tipo_venta) {
        this.id = id;
        this.idCliente = idCliente;
        this.iva = iva;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
        this.tipo = tipo;
        this.tipo_venta = tipo_venta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo_venta() {
        return tipo_venta;
    }

    public void setTipo_venta(String tipo_venta) {
        this.tipo_venta = tipo_venta;
    }
}
