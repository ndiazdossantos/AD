/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author oracle
 */
public class Serializacion1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

        Mclase a = new Mclase("ola",-7, 2.7E10);
        
        String ruta = "/home/oracle/pruebas/serial.txt";
        FileOutputStream ficheiro = new FileOutputStream(ruta);
        ObjectOutputStream escribir = new ObjectOutputStream(ficheiro);
      
        escribir.writeObject(a);
        escribir.close();
        
        Mclase b = new Mclase();
        
        FileInputStream ficheiro2 = new FileInputStream(ruta);
        ObjectInputStream leer = new ObjectInputStream(ficheiro2);
        
        b = (Mclase) leer.readObject();
        leer.close();
        
        System.out.println(b.toString());
    
    }
    
}
