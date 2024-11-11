package org.example.controller;

import org.example.model.Conexion;
import org.example.model.cabFactura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class cabFacturaController {


    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSql = "";
    public int totalRegistro;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulo = {"ID", "Fecha", "CI/RUC", "Nombre/Razón", "Total"};

        String[] registro = new String[5];
        totalRegistro = 0;
        modelo = new DefaultTableModel(null, titulo);
        sSql = "SELECT df.idfactura_ventas AS 'ID', df.fecha_emision AS 'Fecha', c.`ci/ruc` AS 'RUC', c.`nombre/razon`,df.total_pago\n"
                + "FROM ventas.factura_ventas AS df\n"
                + "INNER JOIN ventas.clientes AS c on df.codigo_cliente = c.idcliente\n"
                + "WHERE (c.`ci/ruc` LIKE '%" + buscar + "%' OR c.`nombre/razon` LIKE '%" + buscar + "%')\n"
                + "AND df.estado = 'Activo' ORDER BY df.idfactura_ventas;";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            while (rs.next()) {
                registro[0] = rs.getString("ID");
                registro[1] = rs.getString("Fecha");
                registro[2] = rs.getString("RUC");
                registro[3] = rs.getString("nombre/razon");
                registro[4] = rs.getString("total_pago");
                totalRegistro = totalRegistro + 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    public boolean insertar(cabFactura dts) {
        sSql = "INSERT INTO ventas.factura_ventas(codigo_cliente, iva, total_pago,concepto) VALUES(?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setInt(1, dts.getIdCliente());
            pst.setString(2, dts.getIva());
            pst.setDouble(3, 0);
            pst.setString(4, dts.getTipo_venta());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean modify(cabFactura dts){
        sSql = "UPDATE factura_ventas SET codigo_cliente = ?,iva = ?, concepto = ? WHERE idfactura_ventas = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setInt(1, dts.getIdCliente());
            pst.setString(2, dts.getIva());
            pst.setDouble(3, dts.getTotal());
            pst.setString(4, dts.getTipo_venta());
            pst.setInt(5, dts.getId());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

}
