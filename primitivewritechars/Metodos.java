/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitivewritechars;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Metodos {
    
       
    DataOutputStream escribir;
    DataInputStream leer;
    FileInputStream ficheiro;
    FileOutputStream ficheiro2;
    
    
    String cadea ="O tempo está xélido";
    
   public void writeChars(String ruta) throws FileNotFoundException, IOException{
       
     ficheiro2 = new FileOutputStream(ruta,true); // if true, then bytes will be written to the end of the file rather than the beginning
     escribir = new DataOutputStream(ficheiro2);
     
    
    
     for(int i=0; i<2;++i){
         
         escribir.writeChars(cadea);
         System.out.println("Escribindo a cadea: O tempo está xélido    Cun tamaño de "+escribir.size());
              
     }
   
       System.out.println("Tamañó final do ficheiro : "+escribir.size()+" bytes");
       escribir.close();
   }
   
   public void readChars(String ruta) throws FileNotFoundException, IOException{
       ficheiro = new  FileInputStream(ruta);
       leer = new DataInputStream(ficheiro);
       String resultado="";
     
      while(leer.available()!=0){
           for (int i = 0; i<cadea.length(); i++ ){
           resultado = resultado+leer.readChar();    
           }
      System.out.println("Resultado: "+ resultado);
      System.out.println("Pendente de leer "+leer.available()+" bytes");
      
       
       }{
           System.out.println("Xa non queda nada por leer");
   }
     
     leer.close();
   
     
     
   }
    
}
