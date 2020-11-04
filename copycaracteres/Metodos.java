/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copycaracteres;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Metodos {
      FileReader leer;
      FileWriter escribir;
    
    public void copiarCaracteres() throws IOException{
      
       File ficheiro = new File("/home/oracle/pruebas/texto10.txt");
       File ficheiroCopia = new File("/home/oracle/pruebas/texto11.txt"); 
        
          if (ficheiroCopia.exists() == true) {
            ficheiroCopia.delete();
          }
          
          ficheiroCopia.createNewFile();
          
        leer = new FileReader(ficheiro);
        escribir = new FileWriter(ficheiroCopia);

        int c;
     
        do {
            c = leer.read();
            if (c != -1) {
                escribir.write(c);
            }

        } while (c != -1);
        leer.close();
        escribir.close();

        System.out.println("Ficheiro copiado con exito.");

   
    }
    
}
