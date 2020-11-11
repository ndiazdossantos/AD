/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionalcc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
        PreparedStatement prs;
        ResultSet rst;
    
        
    
    public void conexion() throws SQLException{
   
        conn = DriverManager.getConnection(url);
    }
    
    public void insireprep() throws SQLException{ 
       
        conexion();
        String consulta = "insert into produtos(?,?,?)";
        prs = conn.prepareStatement(consulta);
        prs.setString(1, "p4");
        prs.setString(2, "serra");
        prs.executeUpdate();
      
    }
   
    public void actuprep(String COD, int prezoNovo) throws SQLException{ 
        
        conexion();
        String consulta ="UPDATE produtos SET PREZO=? where CODIGO=?";
        prs = conn.prepareStatement(consulta);
        prs.setString(2,COD);
        prs.setInt(1, prezoNovo);
        prs.executeUpdate();
    }
    
   public void amosar() throws SQLException{
       conexion();
       String consulta = "select CODIGO,DESCRICION,PREZO from produtos";
       prs = conn.prepareStatement(consulta);
       rst = prs.executeQuery();
       while(rst.next()){
           System.out.println(rst.getString("CODIGO")+" "+rst.getString("DESCRICION")+" "+rst.getInt("PREZO"));
       }
       
   }
   
    public void pecharConn() throws SQLException {
        conn.close();
    }
    
    
}
