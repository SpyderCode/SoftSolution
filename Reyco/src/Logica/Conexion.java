package Logica;

import Clases.LoginUser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;

public class Conexion {
    
    static Connection con = null;
    public static String usuario;
    public static String password;
    public static boolean status = false;  
    public static  Connection getConection(){
    	
        status = false;
        String url = "jdbc:mysql://localhost:3306/reyco";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
        }catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "No se pudo establece la conexion... revisar Conexion con Base De Datos" + e.getMessage(),
            "Error de Conexion",JOptionPane.ERROR_MESSAGE);
        }
        try{
            con = (Connection) DriverManager.getConnection(url, Conexion.usuario, Conexion.password);
            status = true;
        }catch (SQLException e){
             JOptionPane.showMessageDialog(null, "Error" + e.getMessage(),
            "Error de Conexion",JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
    
    
    public static void setcuenta(String usuario, String password){
        Conexion.usuario = usuario;
        Conexion.password = password;
    }
    
    public static boolean getstatus(){
        return  status;
    }
    
    public static ResultSet Consulta(String consulta){
        Connection con = getConection();
        Statement declara;
        try{
            declara=con.createStatement();
            ResultSet respuesta = declara.executeQuery(consulta);
            return respuesta;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(),
            "Error de Conexion",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
}



