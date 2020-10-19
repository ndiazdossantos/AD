/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitivewritechars;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class PrimitivewriteChars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
         Metodos a = new Metodos();
        String ruta = "/home/oracle/pruebas/texto4.txt";
        File ficheiro = new File(ruta);
        if (ficheiro.exists()==true){
            ficheiro.delete();
        }
        
            ficheiro.createNewFile();
            a.writeChars(ruta);
            a.readChars(ruta);
        
        
    }
    
}
