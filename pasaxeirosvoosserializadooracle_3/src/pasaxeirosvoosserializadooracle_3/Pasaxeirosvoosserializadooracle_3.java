/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasaxeirosvoosserializadooracle_3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author oracle
 */
public class Pasaxeirosvoosserializadooracle_3 {

    public static Connection conexion = null;

    public static Connection getConexion() throws SQLException {
        String usuario = "hr";
        String password = "hr";
        String host = "localhost";
        String puerto = "1521";
        String sid = "orcl";
        String ulrjdbc = "jdbc:oracle:thin:" + usuario + "/" + password + "@" + host + ":" + puerto + ":" + sid;

        conexion = DriverManager.getConnection(ulrjdbc);
        return conexion;
    }

    public static void closeConexion() throws SQLException {
        conexion.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {

        Statement st;
        ResultSet rst;
        Statement st2;
        ResultSet rst2;
       
        Statement st3;
        ResultSet rst3;
        
        Statement st4;
        ResultSet rst4;
        
        Statement st5;
        ResultSet rst5;
        
        Statement st6;
        ResultSet rst6;
        
        PreparedStatement prs;
        
        
        String ruta = "/home/oracle/pruebas/reservas";

        FileInputStream fich = new FileInputStream(ruta);
        ObjectInputStream leer = new ObjectInputStream(fich);

        
        getConexion();
        st = conexion.createStatement();
        st2 = conexion.createStatement();
        st3= conexion.createStatement();
        st4= conexion.createStatement();
        st5= conexion.createStatement();
        st6 = conexion.createStatement();
        Reserva a;
        
        a = (Reserva) leer.readObject();

        
        int contador = 0;
        
        while (a != null) {
       
        
        System.out.println("codr: " + a.getCodr() + "\t" + "dni: " + a.getDni() + "\t" + "idvooida: " + a.getIdvooida() + "\t" + "idvoovolta: " + a.getIdvoovolta());
       
        String dni;
        int idvooida;
        int idvoovolta;
        dni=a.getDni();
        idvooida =a.getIdvooida();
        idvoovolta = a.getIdvoovolta();
        
        
     
         
          rst = st.executeQuery("SELECT * FROM voos where voo='" + idvooida+ "'");
         
          
            while (rst.next()) {
        
         int prezo1 = rst.getInt("prezo");
         int voo = rst.getInt("VOO");
         rst2 = st2.executeQuery("SELECT * FROM voos where voo='" + idvoovolta+ "'");
         
         while (rst2.next()) {

         int prezo2 = rst2.getInt("prezo");
         
         int prezoreserva = prezo1+prezo2;
         
              System.out.println("prezoreserva: "+prezoreserva);
              
              rst3 = st3.executeQuery("SELECT nome FROM pasaxeiros where dni='" +dni+ "'");
             
              while (rst3.next()) {
                   
                String nome = rst3.getString("nome");
                   System.out.println("nome "+nome);
                   
                    st4.executeUpdate("INSERT INTO reservasfeitas VALUES("+voo+",'"+dni+"','"+nome+"',"+prezoreserva+")");
            
                   rst5 = st5.executeQuery("SELECT * from pasaxeiros where DNI='" +a.getDni()+ "'");
                   
                    while (rst5.next()) {
                        
          int numeroreservas = rst5.getInt("NRESERVAS");
          numeroreservas = numeroreservas+1;
          
           st6.executeUpdate("UPDATE pasaxeiros SET NRESERVAS="+numeroreservas+" where DNI='"+dni+"'");      
                
                        
                    }
               }
              
 
            }
         
            }
             a = (Reserva) leer.readObject();
        }
        System.out.println(contador);
        leer.close ();
        closeConexion();
    }

    
}
