/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.controller;


import org.example.model.Conexion;
import org.example.model.Departamento;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class deptoController {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSql = "";
    public int totalRegistro;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulo = {"codigo", "idpais", "departamento", "pais"};

        String[] registro = new String[4];
        totalRegistro = 0;
        modelo = new DefaultTableModel(null, titulo);
        sSql = "SELECT d.iddepartamentos AS id_departamento, d.idpais AS id_pais, d.descripcion AS Departamento, p.descripcion AS Pais\n"
                + "FROM ventas.departamentos AS d \n"
                + "INNER JOIN paises AS p ON d.idpais = p.idpais \n"
                + "WHERE (d.descripcion LIKE '%"+buscar+"%' OR p.descripcion LIKE '%" + buscar + "%') \n"
                + "AND d.estado = 'Activo' ORDER BY d.iddepartamentos;";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            while (rs.next()) {
                registro[0] = rs.getString("id_departamento");
                registro[1] = rs.getString("id_pais");
                registro[2] = rs.getString("Departamento");
                registro[3] = rs.getString("Pais");
                totalRegistro = totalRegistro + 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public boolean insertar(Departamento dts) {
        sSql = "INSERT INTO ventas.departamentos( idpais ,descripcion ,estado)values(?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setInt(1, dts.getIdPaises());
            pst.setString(2, dts.getNombre());
            pst.setString(3, "Activo");

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean editar(Departamento dts) {
        sSql = "UPDATE ventas.departamentos SET idpais = ?, descripcion = ?, estado=? WHERE iddepartamentos = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setInt(1, dts.getIdPaises());
            pst.setString(2, dts.getNombre());
            pst.setString(3, "Activo");
            pst.setInt(4, dts.getIdDepto());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean ocultar(Departamento dts) {
        sSql = "UPDATE ventas.departamentos SET estado=? WHERE iddepartamentos = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setString(1, "Inactivo");
            pst.setInt(2, dts.getIdDepto());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
}
