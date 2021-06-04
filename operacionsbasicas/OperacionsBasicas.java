/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacionsbasicas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

/**
 *
 * @author oracle
 */
public class OperacionsBasicas {
    
    public static Connection conexion = null;
    public static Statement stmt;

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

    public static void main(String[] args) throws SQLException {
        conexion = getConexion();
        stmt = conexion.createStatement();
        
        
        /*1- inserir unha fila nova, 
        exemplo inserir esta fila : 
        insert into alumnos  values(5,'elsa',enderezo('faisan,24','vigo',34205),'12/4/1984');*/
               
            stmt.executeUpdate("INSERT INTO ALUMNOS VALUES"
             + " (" + 5 + ",'" + "elsa" + "'," + "enderezo('faisan,24', 'vigo',34205)" + "," + 
           "TO_DATE('12/04/1984','DD/MM/YYYY')"+ ")"); 

      
         /*
         2 - actualiar un valor dun campo tipo obxecto duha fila  
por exemplo cambiar a rúa  do enderezo da fila que ten por código 1 para que pase de ser 'urzaiz'  a ser 'pombal'.
         */
         
         stmt.executeUpdate("update alumnos a set a.enderezot.rua='pombal' where codigo=1");
  
    /*
      3 - ler todas as filas 
         */
    
          ResultSet rs = stmt.executeQuery("SELECT * FROM ALUMNOS");
        while (rs.next()) {
            int codigo = rs.getInt(1);
            String nome = rs.getString(2);
            java.sql.Struct x = (java.sql.Struct) rs.getObject(3);
            Object[] campos = x.getAttributes();
            String rua = (String) campos[0];
            String cidade = (String) campos[1];
            java.math.BigDecimal codpost = (java.math.BigDecimal) campos[2];
            Date fecha = rs.getDate(4);
            System.out.println("Código: "+codigo+" Nombre: " +nome+" Rúa: "+rua+" Cidade: "+cidade+" Código Postal: "+codpost+" Fecha: "+fecha);
    
    }
        
    conexion.close();    
  
    }
    
}
