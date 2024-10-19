/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.controller;

import org.example.model.conexion;

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
public class ciudadesController {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSql = "";
    public int totalRegistro;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulo = {"codigo", "iddepartamento", "ciudad", "departamento"};

        String[] registro = new String[4];
        totalRegistro = 0;
        modelo = new DefaultTableModel(null, titulo);
        sSql = "SELECT c.idciudades AS id_ciudad, d.iddepartamentos AS id_departamento, c.descripcion AS Ciudad, d.descripcion AS Departamento\n"
                + "FROM ciudades AS c\n"
                + "INNER JOIN ventas.departamentos AS d ON c.iddepartamentos = d.iddepartamentos\n"
                + "WHERE (c.descripcion LIKE '%" + buscar + "%' OR d.descripcion LIKE '%" + buscar + "%')\n"
                + "AND c.estado = 'Activo' ORDER BY c.idciudades;";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            while (rs.next()) {
                registro[0] = rs.getString("id_ciudad");
                registro[1] = rs.getString("id_departamento");
                registro[2] = rs.getString("Ciudad");
                registro[3] = rs.getString("Departamento");
                totalRegistro = totalRegistro + 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public boolean insertar(org.example.model.Ciudades dts) {
        sSql = "INSERT INTO ventas.ciudades(iddepartamentos, descripcion, estado)values(?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setInt(1, dts.getIdDepto());
            pst.setString(2, dts.getNombre());
            pst.setString(3, "Activo");

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean editar(org.example.model.Ciudades dts) {
        sSql = "UPDATE ventas.ciudades SET iddepartamentos = ?, descripcion = ?, estado = ? WHERE idciudades = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setInt(1, dts.getIdDepto());
            pst.setString(2, dts.getNombre());
            pst.setString(3, "Activo");
            pst.setInt(4, dts.getIdCiudad());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean ocultar(org.example.model.Ciudades dts) {
        sSql = "UPDATE ventas.ciudades SET estado = ? WHERE idciudades = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setString(1, "Inactivo");
            pst.setInt(2, dts.getIdCiudad());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

}
