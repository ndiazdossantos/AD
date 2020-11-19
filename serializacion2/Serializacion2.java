/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion2;

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
public class Serializacion2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
       
        String ruta = "/home/oracle/pruebas/serial2.txt";

        FileOutputStream ficheiro = new FileOutputStream(ruta);
        ObjectOutputStream escribir = new ObjectOutputStream(ficheiro);
        FileInputStream ficheiro2 = new FileInputStream(ruta);
        ObjectInputStream leer = new ObjectInputStream(ficheiro2);

        String[] cod = {"p1", "p2", "p3"};
        String[] desc = {"parafusos", "cravos ", "tachas"};
        Double[] prezo = {3.0, 4.0, 5.0};
        
        Product obx1 = new Product(cod[0],desc[0],prezo[0]);
        Product obx2 = new Product(cod[1],desc[1],prezo[1]);
        Product obx3 = new Product(cod[2],desc[2],prezo[2]);
        Product obx4 = null;
        
        escribir.writeObject(obx1);
        escribir.writeObject(obx2);
        escribir.writeObject(obx3);
        escribir.writeObject(obx4);
        escribir.close();
       
        Product a;
         a = (Product) leer.readObject();
        
        while(a!=null){
             System.out.println(a.toString());
             a = (Product) leer.readObject();
        }
        
        leer.close();
        

    }
    
}
