/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aleatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author oracle
 */
public class Aleatorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String[] codes = {"p1", "p2", "p3"};
        String[] descricion = {"parafusos", "cravos ", "tachas"};
        int[] prices = {3, 4, 5};

        File fich1 = new File("/home/oracle/pruebas/productos2.txt");
        RandomAccessFile ficheiro = new RandomAccessFile(fich1, "rw");

        for (int i = 0; i < codes.length; i++) {
            String codigo = String.format("%-3s", codes[i]).replace(" ", "*");
            String descripcion = String.format("%-10s", descricion[i]).replace(" ", "*");
            int prezo = prices[i];
            ficheiro.writeChars(codigo);
            ficheiro.writeChars(descripcion);
            ficheiro.writeInt(prezo);
        }

        Product obx1 = new Product();
        ficheiro.seek(30);
        String codigoObx = "";
        String descricionObx = "";

        for (int i = 0; i < 3; i++) {
            codigoObx = codigoObx + ficheiro.readChar();
        }

        for (int i = 0; i < 10; i++) {
            descricionObx = descricionObx + ficheiro.readChar();
        }

        obx1.setCodigo(codigoObx.replace("*", ""));
        obx1.setDescricion(descricionObx.replace("*", ""));
        obx1.setPrezo(ficheiro.readInt());

        ficheiro.close();

        System.out.println(obx1.toString());

    }

}
