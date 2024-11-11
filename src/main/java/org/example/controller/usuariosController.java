package org.example.controller;

import org.example.model.User;
import org.example.model.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;

public class usuariosController {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSql = "";
    public int totalRegistros = 0;

    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        String [] titulo = {"codigo", "User", "password", "Acceso", "Nombre", "Apellido", "Documento"};

        String [] registro = new String[7];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulo);
        sSql="SELECT * FROM ventas.usuarios WHERE (CONCAT(nombre,apellido) LIKE '%" + buscar + "%' OR ci_nro LIKE '%"
                + buscar + "%') AND estado = 'Activo' ORDER BY idusuario";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            while (rs.next()) {
                registro[0]=rs.getString("idusuario");
                registro[1]=rs.getString("user");
                registro[2]=rs.getString("password");
                registro[3]=rs.getString("acceso");
                registro[4]=rs.getString("nombre");
                registro[5]=rs.getString("apellido");
                registro[6]=rs.getString("ci_nro");
                totalRegistros += 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public boolean insertar(User dts) {
        sSql="INSERT INTO ventas.usuarios(user, password, acceso, estado, nombre, apellido, ci_nro) " +
                "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setString(1, dts.getUser());
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

    public boolean editar(User dts) {
        sSql="UPDATE ventas.usuarios SET user = ?, password = ?, acceso = ?, " +
                "estado = ?, nombre = ?, apellido = ?, ci_nro = ?" +
                "WHERE idusuario = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            pst.setString(1, dts.getUser());
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

    public boolean ocultar(User dts) {
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

    public boolean eliminar(User dts) {
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

    public boolean init(String user,String pass) {
        sSql = "SELECT * FROM usuarios WHERE (user = '" + user + "' AND password = '" + pass + "') AND " +
                "estado = 'Activo'";
        try {
            PreparedStatement pst = cn.prepareStatement(sSql);
            ResultSet rs = pst.executeQuery();
            return  rs.next();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public static void reportUser(String busqueda){
        Map map = new HashMap();
        map.put("busqueda", busqueda);
        try {
            JasperReport report = JasperCompileManager.compileReport("./org/example/utils/userReport.jrxml");
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "reporte_generado.pdf");

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

}
