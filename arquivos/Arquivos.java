/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivos;

import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Arquivos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Metodos p = new Metodos();
       
        // 1)
        
        p.creaDirectorio("/home/oracle/NetBeansProjects/arquivos/");
        p.eDirectorio("/home/oracle/NetBeansProjects/arquivos/");
        
        // 2)
        
        p.creaFicheiro("/home/oracle/NetBeansProjects/arquivos/", "Products1.txt");
        p.eFicheiro("/home/oracle/NetBeansProjects/arquivos/Products1.txt");
        
       // 3)
        
        p.creaDirectorio("/home/oracle/NetBeansProjects/arquivos/arquivosdir");
        p.creaFicheiro("/home/oracle/NetBeansProjects/arquivos/", "Products2.txt");
        
        // 4)
        
        p.mContido("/home/oracle/NetBeansProjects/arquivos/arquivosdir");
        
        // 5)
        
        p.modoAcceso("/home/oracle/NetBeansProjects/arquivos/arquivosdir", "Products1.txt");
        p.calculaLonxitude("/home/oracle/NetBeansProjects/arquivos/arquivosdir","Products.txt");
        
        //6 
        
        p.mLectura("/home/oracle/NetBeansProjects/arquivos/arquivosdir", "Products1.txt");
        
        //7
        
        p.mEscritura("/home/oracle/NetBeansProjects/arquivos/arquivosdir", "Products1.txt");
        
        //8
        
        p.borraFicheiro("/home/oracle/NetBeansProjects/arquivos/arquivosdir", "Products1.txt");
        
        //9
        
        p.borraDirectorio("/home/oracle/NetBeansProjects/arquivos/arquivosdir");
        
        
        
        
    }
    
}