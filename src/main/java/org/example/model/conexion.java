package org.example.model;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class conexion {
    public String BD = "ventas";
    public String URL = "jdbc:mysql://localhost/" + BD;
    public String User = "root";
    public String Pass = "0304";

    public conexion() {}

    public Connection conectar(){
        Connection link = null;

        try {
            link = DriverManager.getConnection
                    (this.URL, this.User, this.Pass);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return link;
    }
    public PreparedStatement prepareStatement(String sql){
        throw new UnsupportedOperationException("No soportado");
    }
}
