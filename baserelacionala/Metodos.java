/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author oracle
 */
public class Metodos {
        String driver = "jdbc:oracle:thin:";
        String host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
        String porto = "1521";
        String sid = "orcl";
        String usuario = "hr";
        String password = "hr";
        String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;
        Connection conn;
        Statement st;
        ResultSet rst;
        
    
    public void conexion() throws SQLException{
        
        conn = DriverManager.getConnection(url);
    }
    
    public void cerrarConexion() throws SQLException{
        
        conn.close();
        
    }
    
    public void insireProducto(String CODIGO, String DESCRICION, int PREZO) throws SQLException{
      st = conn.createStatement();
      st.executeUpdate("INSERT INTO produtos VALUES('"+CODIGO+"','"+DESCRICION+"',"+PREZO+")");
           
    }
    
    public void listaProdutos() throws SQLException{
    st = conn.createStatement();
    rst = st.executeQuery("SELECT * FROM produtos");
    
    while (rst.next()) {
        String fila = "";
        for (int i = 1; i <= 3; i++) {
            fila += rst.getString(i) + ", ";          
        }
        System.out.println(fila);
       

    } 

    }
    
    public void actualizaPre(String  CODIGO, int PREZO) throws SQLException{
        st = conn.createStatement();
        st.executeUpdate("UPDATE produtos SET PREZO="+PREZO+" where CODIGO='"+CODIGO+"'");
    }
    
    public void borrarfila(String CODIGO) throws SQLException{
        st = conn.createStatement();
        st.executeUpdate("DELETE FROM produtos where CODIGO='"+CODIGO+"'"); 
        
    }

   public void amosarFila(String CODIGO) throws SQLException{
       
    st = conn.createStatement();
    rst = st.executeQuery("SELECT * FROM produtos where CODIGO='"+CODIGO+"'");
    
   while(rst.next()) {

         System.out.println(rst.getString("CODIGO")+"\t"+rst.getString("DESCRICION")+rst.getInt("PREZO"));   
   
    }  
    
  }

   
}
