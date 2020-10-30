/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitiveutfchars;

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
    FileInputStream fich1;
    FileOutputStream fich2;
    DataInputStream leer;
    DataOutputStream escribir;
    
    public void copiarUTFChars(String ruta) throws FileNotFoundException, IOException{
    String cadena = "Está en casa ";
    
    fich1 = new FileInputStream(ruta);
    fich2 = new FileOutputStream(ruta, true);
    leer = new DataInputStream(fich1);
    escribir = new DataOutputStream(fich2);
    
    
    
    escribir.writeUTF(cadena);
    System.out.println("Escribiendo writeUTF: "+cadena);
    System.out.println("Bytes totales escritos: "+escribir.size());
    escribir.writeChars(cadena);
    System.out.println("writeChars escribindo: "+cadena);
    System.out.println("Bytes totales escritos:  "+escribir.size());
    escribir.writeUTF(cadena);
    System.out.println("Escribiendo writeUTF: "+cadena);
    System.out.println("Bytes totales escritos:  "+escribir.size());
    
    System.out.println("Bytes restantes por leer "+leer.available());
    System.out.println(leer.readUTF());
    System.out.println("Numero de bytes por leer "+leer.available());
  
    
    for (int i = 0; i < cadena.length(); i++) {
        System.out.print(leer.readChar());    
        }
    
    System.out.println("Numero de bytes por leer "+leer.available());
    System.out.println(leer.readUTF());
    System.out.println("Numeros de bytes por leer "+leer.available());
    
        escribir.close();
        leer.close();
       
    
}


    
    
}
