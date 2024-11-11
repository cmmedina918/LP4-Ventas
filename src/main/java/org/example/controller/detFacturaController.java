package org.example.controller;

import org.example.model.Conexion;
import org.example.model.cabFactura;
import org.example.model.detFactura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class detFacturaController {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSql = "";
    public int totalRegistro;

    public boolean insertar(detFactura dts) {
        sSql = "INSERT INTO ventas.detalle_factura(idfactura_ventas, idproductos, cantidad, precio_venta) VALUES(?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setInt(1, dts.getIdCab());
            pst.setInt(2, dts.getIdProducto());
            pst.setInt(3, dts.getCantidad());
            pst.setDouble(4, dts.getPrecio());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public DefaultTableModel mostrar(String buscar, String idCab) {
        DefaultTableModel modelo;
        String[] titulo = {"ID", "Descipcion", "Precio", "Cantidad"};

        String[] registro = new String[4];
        totalRegistro = 0;
        modelo = new DefaultTableModel(null, titulo);
        sSql = "SELECT p.idproductos AS 'ID',descripcion, p.precio_venta AS 'Precio',cantidad\n"
                + "FROM ventas.detalle_factura AS df\n"
                + "INNER JOIN ventas.productos AS p on df.idproductos = p.idproductos\n"
                + "WHERE (p.descripcion LIKE '%" + buscar + "%' OR df.precio_venta LIKE '%" + buscar + "%')\n"
                + "AND (df.estado = 'Activo' AND df.idfactura_ventas LIKE " + idCab + ");";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            while (rs.next()) {
                registro[0] = rs.getString("ID");
                registro[1] = rs.getString("descripcion");
                registro[2] = rs.getString("Precio");
                registro[3] = rs.getString("cantidad");
                totalRegistro = totalRegistro + 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
