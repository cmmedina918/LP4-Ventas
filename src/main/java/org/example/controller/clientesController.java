package org.example.controller;

import org.example.model.Ciudad;
import org.example.model.Cliente;
import org.example.model.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class clientesController {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSql = "";
    public int totalRegistro;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulo = {"codigo", "Nombre/Razón", "CI/RUC", "Número", "Email","Dirección","idciudad","Ciudad"};

        String[] registro = new String[8];
        totalRegistro = 0;
        modelo = new DefaultTableModel(null, titulo);
        sSql = "SELECT c.idcliente AS 'id', c.`nombre/razon`, c.`ci/ruc`," +
                "c.telefono, c.email, c.direccion,d.idciudades, d.descripcion AS 'Ciudad'\n"
                + "FROM clientes AS c\n"
                + "INNER JOIN ventas.ciudades AS d ON c.idciudades = d.idciudades\n"
                + "WHERE (c.`nombre/razon` LIKE '%" + buscar + "%' OR c.`ci/ruc` LIKE '%" + buscar + "%')\n"
                + "AND c.estado = 'Activo' ORDER BY c.idcliente;";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            while (rs.next()) {
                registro[0] = rs.getString("id");
                registro[1] = rs.getString("nombre/razon");
                registro[2] = rs.getString("ci/ruc");
                registro[3] = rs.getString("telefono");
                registro[4] = rs.getString("email");
                registro[5] = rs.getString("direccion");
                registro[6] = rs.getString("idciudades");
                registro[7] = rs.getString("Ciudad");
                totalRegistro = totalRegistro + 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public boolean insertar(Cliente dts) {
        sSql = "INSERT INTO ventas.clientes(idciudades, `nombre/razon`, `ci/ruc`, direccion, telefono, email, estado) " +
                "VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setInt(1, dts.getId_ciudad());
            pst.setString(2, dts.getNombre_razon());
            pst.setString(3, dts.getCi_ruc());
            pst.setString(4, dts.getDireccion());
            pst.setString(5, dts.getNro());
            pst.setString(6, dts.getEmail());
            pst.setString(7, "Activo");

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean editar(Cliente dts) {
        sSql = "UPDATE clientes SET idciudades = ?,`nombre/razon` = ?, `ci/ruc` = ?, direccion = ?,telefono = ?," +
                "email = ?, estado = ? WHERE idcliente = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setInt(1, dts.getId_ciudad());
            pst.setString(2, dts.getNombre_razon());
            pst.setString(3, dts.getCi_ruc());
            pst.setString(4, dts.getDireccion());
            pst.setString(5, dts.getNro());
            pst.setString(6, dts.getEmail());
            pst.setString(7, "Activo");
            pst.setInt(8, dts.getId());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean ocultar(Cliente dts) {
        sSql = "UPDATE clientes SET estado = ? WHERE idcliente = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setString(1, "Inactivo");
            pst.setInt(2, dts.getId());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
