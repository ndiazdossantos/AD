/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionala;

import java.sql.SQLException;

/**
 *
 * @author oracle
 */
public class BaseRelacionalA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
      Metodos a = new Metodos();
      a.conexion();
      
     // a.insireProducto("p1","parafusos", 3);
     // a.insireProducto("p2","cravos" , 4);
     // a.insireProducto("p3","tachas", 6);
    //  a.listaProdutos();
      
    //  a.actualizaPre("p1", 6);
    //  System.out.println("");  
    //  a.listaProdutos();
      
    //  a.borrarfila("p1");
    //   System.out.println("");
    //  a.listaProdutos();
      a.amosarFila("p2");
      
      a.cerrarConexion();
      
    }
    
}
