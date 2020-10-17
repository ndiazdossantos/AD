/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitivewriteutf;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class PrimitivewriteUTF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        Metodos a = new Metodos();
        String ruta = "/home/oracle/pruebas/texto3.txt";
        File ficheiro = new File(ruta);
        if (ficheiro.exists()==true){
            ficheiro.delete();
        }
        
            ficheiro.createNewFile();
            a.writeUTF(ruta);
            a.readUTF(ruta);
        
        
    }
    
}
