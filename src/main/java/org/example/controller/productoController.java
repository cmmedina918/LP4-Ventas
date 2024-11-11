package org.example.controller;

import org.example.model.Conexion;
import org.example.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class productoController {

    Conexion conMySQL = new Conexion();
    Connection con = conMySQL.conectar();
    public int registerCount = 0;
    String SQLQ;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulo = {"ID", "Producto", "Cantidad", "Unidad", "Costo", "Precio","ID Marca", "Marca"};

        String[] registro = new String[8];
        registerCount = 0;
        modelo = new DefaultTableModel(null, titulo);
        SQLQ = "SELECT p.idproductos AS 'id', p.descripcion AS 'Producto' , stock as 'cantidad', p.unidad_medida AS 'Unidad', "
                + "precio_compra, precio_venta, m.descripcion AS 'Marca', m.id AS 'ID Marca'\n"
                + "FROM productos AS p\n"
                + "INNER JOIN marcas m on p.id_marca = m.id\n"
                + "WHERE p.descripcion LIKE '%" + buscar + "%'\n"
                + "AND p.estado = 'Activo' ORDER BY p.idproductos;";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQLQ);
            while (rs.next()) {
                registro[0] = rs.getString("id");
                registro[1] = rs.getString("Producto");
                registro[2] = rs.getString("cantidad");
                registro[3] = rs.getString("Unidad");
                registro[4] = rs.getString("precio_compra");
                registro[5] = rs.getString("precio_venta");
                registro[6] = rs.getString("ID Marca");
                registro[7] = rs.getString("Marca");

                registerCount = registerCount + 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public boolean insertar(Producto dts) {
        SQLQ = "INSERT INTO productos(descripcion, stock, unidad_medida, precio_compra, precio_venta, estado, id_marca)" +
                "VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(SQLQ);
            pst.setString(1, dts.getDescripcion());
            pst.setInt(2, dts.getCantidad());
            pst.setString(3, dts.getUnidad());
            pst.setFloat(4, dts.getPrecio_compra());
            pst.setFloat(5, dts.getPrecio_venta());
            pst.setString(6, "Activo");
            pst.setInt(7, dts.getId_marca());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean update(Producto dts){
        SQLQ = "UPDATE productos SET descripcion = ?, stock = ?, unidad_medida = ?, precio_compra = ?," +
                "precio_venta = ?, estado = ?, id_marca = ? WHERE idproductos = ?";
        try {
            PreparedStatement pst = con.prepareStatement(SQLQ);
            pst.setString(1, dts.getDescripcion());
            pst.setInt(2, dts.getCantidad());
            pst.setString(3, dts.getUnidad());
            pst.setFloat(4, dts.getPrecio_compra());
            pst.setFloat(5, dts.getPrecio_venta());
            pst.setString(6, "Activo");
            pst.setInt(7, dts.getId_marca());
            pst.setInt(8, dts.getId());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean deactivate(Producto dts) {
        SQLQ = "UPDATE productos SET estado = ? WHERE idproductos = ?";
        try {
            PreparedStatement pst = con.prepareStatement(SQLQ);
            pst.setString(1,"Inactivo");
            pst.setInt(2,dts.getId());
            int n = pst.executeUpdate();
            return n != 0;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
