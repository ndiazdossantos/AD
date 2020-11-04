/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textodelimitado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author oracle
 */
public class TextoDelimitado {

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
      
        FileWriter fich1;
        fich1 = new FileWriter(ficheiro);
        
        BufferedWriter fich2;
        fich2 = new BufferedWriter(fich1);
        
        PrintWriter escribir;
        escribir = new PrintWriter(fich2);
        
        FileReader fich3;
        fich3 = new FileReader(ficheiro);
        
        BufferedReader fich4;
        fich4 = new BufferedReader(fich3);
        
        BufferedReader leer;
        leer = new BufferedReader(fich4);
        
        String[] cod = {"p1", "p2", "p3"};
        String[] desc = {"parafusos", "cravos", "tachas"};
        Double[] prezo = {3.0, 4.0, 5.0};
        
         for (int i = 0; i < cod.length; i++) {
            escribir.println(cod[i] + "\t" + desc[i] + "\t" + prezo[i]);
        }
        escribir.close();
        
      
        Product p = new Product();
        for (int j = 0; j < cod.length; j++) {
            String leido = leer.readLine();
            String[] partes = leido.split("\t");
            for (int i = 0; i < partes.length; i++) {
                switch (i) {
                    case 0:
                        p.setCodigo(partes[i]);
                        break;
                    case 1:
                        p.setDescricion(partes[i]);
                        break;
                    case 2:
                        p.setPrezo(Double.parseDouble(partes[i]));
                        break;
                }

            }
            System.out.println(p.toString() + "\n");
        }
        leer.close();
    }
    
}
