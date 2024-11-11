package org.example.controller;


import org.example.model.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class marcaController {
    Conexion conMySQL = new Conexion();
    Connection con = conMySQL.conectar();
    public int registerCount = 0;
    String SQLQ;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulo = {"ID", "Marca"};

        String[] registro = new String[2];
        registerCount = 0;
        modelo = new DefaultTableModel(null, titulo);
        SQLQ = "SELECT * FROM marcas AS p\n"
                + "WHERE descripcion LIKE '%" + buscar + "%'\n"
                + "AND estado = 'Activo' ORDER BY id;";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQLQ);
            while (rs.next()) {
                registro[0] = rs.getString("id");
                registro[1] = rs.getString("descripcion");
                registerCount = registerCount + 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
