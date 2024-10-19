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
@SuppressWarnings("ALL")
public class paisesController {
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSql="";
    public int totalRegistro;
    
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        String [] titulo = {"codigo", "nombre", "estado"};
        
        String [] registro = new String[3];
        totalRegistro=0;
        modelo = new DefaultTableModel(null, titulo);
        sSql="SELECT * FROM ventas.paises WHERE descripcion LIKE '%" + buscar + "%' AND estado = 'Activo' ORDER BY idpais";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            while (rs.next()) {                
                registro[0]=rs.getString("idpais");
                registro[1]=rs.getString("descripcion");
                registro[2]=rs.getString("estado");
                totalRegistro=totalRegistro+1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public boolean insertar(org.example.model.Paises dts) {
        sSql="INSERT INTO ventas.paises( descripcion, estado) VALUES(?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setString(1, dts.getNombre());
            pst.setString(2, "Activo");
            
            int n = pst.executeUpdate();
            if (n != 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public boolean editar(org.example.model.Paises dts) {
        sSql="UPDATE ventas.paises SET descripcion = ?, estado = ? WHERE paises.idpais = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setString(1, dts.getNombre());
            pst.setString(2, "Activo");
            pst.setInt(3, dts.getIdpais());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public boolean ocultar(org.example.model.Paises dts) {
        sSql="UPDATE ventas.paises SET estado = ? WHERE paises.idpais=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setString(1, "Inactivo");
            pst.setInt(2, dts.getIdpais());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public boolean eliminar(org.example.model.Paises dts) {
        sSql="DELETE FROM ventas.paises where idpais = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setInt(1, dts.getIdpais());
            
            int n = pst.executeUpdate();
            if (n != 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
