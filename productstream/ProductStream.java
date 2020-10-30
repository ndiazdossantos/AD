/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productstream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class ProductStream {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        
        String ruta = "/home/oracle/pruebas/productos.txt";
        File ficheiro = new File(ruta);
        if (ficheiro.exists()==true){
            ficheiro.delete();
        }
        
            ficheiro.createNewFile();
            
        FileInputStream fich1;
        FileOutputStream fich2;
        DataInputStream leer;
        DataOutputStream escribir;
        fich1 = new FileInputStream(ruta);
        fich2 = new FileOutputStream(ruta, true);
        leer = new DataInputStream(fich1);
        escribir = new DataOutputStream(fich2);
        
        Product a = new Product("cod1","parafusos",3.0);
        
        escribir.writeUTF(a.getCodigo());
        escribir.writeUTF(a.getDescricion()); 
        escribir.writeDouble(a.getPrezo());
        
        Product a2 = new Product("cod2","cravos",4.0);
        escribir.writeUTF(a2.getCodigo());
        escribir.writeUTF(a2.getDescricion()); 
        escribir.writeDouble(a2.getPrezo());
        
        
        Product a3 = new Product();
        System.out.println("Lista de productos gardados no ficheiro productos.txt");
        
        a3.setCodigo(leer.readUTF());
        a3.setDescricion(leer.readUTF());
        a3.setPrezo(leer.readDouble());
        
        System.out.println(a3.toString());
        
        a3.setCodigo(leer.readUTF());
        a3.setDescricion(leer.readUTF());
        a3.setPrezo(leer.readDouble());
        
        System.out.println(a3.toString());
        
        escribir.close();
        leer.close();
        
        
        
        
        
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
    }
    
}
