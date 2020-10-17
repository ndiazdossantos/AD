/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copybytesimaxe;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Metodos {
    
    File orixen;
    File destino;
    FileInputStream ler;
    FileOutputStream escribir;
    BufferedInputStream bLer;
    BufferedOutputStream bEscribir;
    
    
    public void copiarImaxe() throws FileNotFoundException, IOException{

         orixen = new File("/home/oracle/pruebas/foto.jpg");
         ler = new FileInputStream(orixen);
         bLer = new BufferedInputStream(ler);
         destino = new File("/home/oracle/pruebas/foto2.jpg");
         escribir = new FileOutputStream(destino,false); // false no se sobreescribe, true se sobreescribe
         bEscribir = new BufferedOutputStream(escribir);
         int b;
         
      while((b = bLer.read())!=-1){
        bEscribir.write(b);
      }
       
      bLer.close();
      bEscribir.close();
         
    }
}
