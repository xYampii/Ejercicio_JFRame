/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.postgres_clientes;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author suyan
 */
public class DBConnection { 
    Connection cn=null;
   
    public Connection establecerConexion(){
        String dbUser="postgres";
        String dbPassword="1234";
        String dbUrl="jdbc:postgresql://localhost:5432/testdb";
        try {
            
            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            JOptionPane.showMessageDialog(null, "Conexion establecida");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
        }
        return cn;
    }

}
