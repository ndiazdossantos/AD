/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitivewriteutf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
    
    DataOutputStream escribir;
    DataInputStream leer;
    FileInputStream ficheiro;
    FileOutputStream ficheiro2;
    
    

    
   public void writeUTF(String ruta) throws FileNotFoundException, IOException{
       
     ficheiro2 = new FileOutputStream(ruta,true); // if true, then bytes will be written to the end of the file rather than the beginning
     escribir = new DataOutputStream(ficheiro2);
     
    
    
     for(int i=0; i<3;++i){
         
         escribir.writeUTF("O tempo está xélido");
         System.out.println("Escribindo a cadea: O tempo está xélido    Cun tamaño de "+escribir.size());
              
     }
   
       System.out.println("Tamañó final do ficheiro : "+escribir.size()+" bytes");
       
   }
   
   public void readUTF(String ruta) throws FileNotFoundException, IOException{
       ficheiro = new  FileInputStream(ruta);
       leer = new DataInputStream(ficheiro);
     
     
       if(leer.available()!=0){
       System.out.println("Pendente de leer"+leer.available()+" bytes");
       System.out.println("Cadea: "+ leer.readUTF());
       
       }else{
           System.out.println("Xa non queda nada por leer");
   }
     escribir.close();
     leer.close();
   
     
     
   }
}
