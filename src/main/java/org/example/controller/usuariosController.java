package org.example.controller;

import org.example.model.conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class usuariosController {
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSql = "";
    public int totalRegistro = 0;

    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        String [] titulo = {"codigo", "login", "password", "Acceso", "Nombre", "Apellido", "Documento"};

        String [] registro = new String[7];
        totalRegistro = 0;
        modelo = new DefaultTableModel(null, titulo);
        sSql="SELECT * FROM ventas.usuarios WHERE (CONCAT(nombre,apellido) LIKE '%" + buscar + "%' OR ci_nro LIKE '%"
                + buscar + "%') AND estado = 'Activo' ORDER BY idusuario";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            while (rs.next()) {
                registro[0]=rs.getString("idusuario");
                registro[1]=rs.getString("login");
                registro[2]=rs.getString("password");
                registro[3]=rs.getString("acceso");
                registro[4]=rs.getString("nombre");
                registro[5]=rs.getString("apellido");
                registro[6]=rs.getString("ci_nro");
                totalRegistro += 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public boolean insertar(org.example.model.Usuario dts) {
        sSql="INSERT INTO ventas.usuarios(login, password, acceso, estado, nombre, apellido, ci_nro) " +
                "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setString(1, dts.getLogin());
            pst.setString(2, dts.getPassword());
            pst.setString(3, dts.getAcceso());
            pst.setString(4, "Activo");
            pst.setString(5, dts.getNombre());
            pst.setString(6, dts.getApellido());
            pst.setString(7, dts.getCiNro());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean editar(org.example.model.Usuario dts) {
        sSql="UPDATE ventas.usuarios SET login = ?, password = ?, acceso = ?, " +
                "estado = ?, nombre = ?, apellido = ?, ci_nro = ?" +
                "WHERE idusuario = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setString(1, dts.getLogin());
            pst.setString(2, dts.getPassword());
            pst.setString(3, dts.getAcceso());
            pst.setString(4, "Activo");
            pst.setString(5, dts.getNombre());
            pst.setString(6, dts.getApellido());
            pst.setString(7, dts.getCiNro());
            pst.setInt(8, dts.getId());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean ocultar(org.example.model.Usuario dts) {
        sSql="UPDATE ventas.usuarios SET estado = ? WHERE usuarios.idusuario=?";
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

    public boolean eliminar(org.example.model.Usuario dts) {
        sSql="DELETE FROM ventas.usuarios where idusuario = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setInt(1, dts.getId());
            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
