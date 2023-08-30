/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.postgres_clientes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author suyan
 */
public class CClientes {
    String NOMBRE,RAZON,NIT;
    int codigo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getRAZON() {
        return RAZON;
    }

    public void setRAZON(String RAZON) {
        this.RAZON = RAZON;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }
    public void MostrarClientes(JTable paramTablaTotalClientes){
        DBConnection objetoConexion=new DBConnection();
        DefaultTableModel modelo=new DefaultTableModel();
        String sql="SELECT *FROM CLIENTES;";
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Nit");
        modelo.addColumn("Razon");
        paramTablaTotalClientes.setModel(modelo);
        String[] datos=new String[4];
        Statement stmt;
        try{
            stmt=objetoConexion.establecerConexion().createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(4);
                datos[3]=rs.getString(3);
                
                modelo.addRow(datos);
            }
            paramTablaTotalClientes.setModel(modelo);
            
        }catch(Exception e){
          JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());

        }
        
    }
    public void insertarCliente(JTextField paramNombres, JTextField paramRazon,JTextField paramNit){
        setNOMBRE(paramNombres.getText());
        setRAZON(paramRazon.getText());
        setNIT(paramNit.getText());
        DBConnection objetoConexion= new DBConnection();
        String insert="INSERT INTO clientes (nombre,razon,nit) VALUES(?,?,?);";
        try{
            CallableStatement cs=objetoConexion.establecerConexion().prepareCall(insert);
            cs.setString(1,getNOMBRE());
            cs.setString(2,getRAZON());
            cs.setString(3,getNIT());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se han insertado los datos correctamente");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    public void SeleccionarCliente(JTable paramTablaCliente,JTextField paramCodigo,JTextField paramNombre, JTextField paramRazon,JTextField paramNit){
        try{
           int fila=paramTablaCliente.getSelectedRow();
           if(fila>=0){
               paramCodigo.setText(paramTablaCliente.getValueAt(fila,0).toString());
               paramNombre.setText(paramTablaCliente.getValueAt(fila,1).toString());
               paramRazon.setText(paramTablaCliente.getValueAt(fila,3).toString());
               paramNit.setText(paramTablaCliente.getValueAt(fila,2).toString()); 
           }else{
               JOptionPane.showMessageDialog(null, "fila no seleccionada");
           }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    public void ActualizarCliente(JTextField paramCodigo,JTextField paramNombres, JTextField paramRazon,JTextField paramNit){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNOMBRE(paramNombres.getText());
        setRAZON(paramRazon.getText());
        setNIT(paramNit.getText());
        DBConnection objetoConexion= new DBConnection();
        String update="UPDATE clientes set nombre=?,razon=?,nit=? WHERE clientes.id_cliente=?;";
        try{
            CallableStatement cs=objetoConexion.establecerConexion().prepareCall(update);
            cs.setString(1,getNOMBRE());
            cs.setString(2,getRAZON());
            cs.setString(3,getNIT());
            cs.setInt(4,getCodigo());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se modificaron los datos correctamente");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    public void EliminarCliente(JTextField paramCodigo){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
       
        DBConnection objetoConexion= new DBConnection();
        String update="DELETE FROM clientes WHERE clientes.id_cliente=?;";
        try{
            CallableStatement cs=objetoConexion.establecerConexion().prepareCall(update);
            cs.setInt(1,getCodigo());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se eliminaron los datos correctamente");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
