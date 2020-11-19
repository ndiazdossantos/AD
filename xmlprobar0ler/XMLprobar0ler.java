/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprobar0ler;

import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author oracle
 */
public class XMLprobar0ler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
         Metodos a = new Metodos();
         File file = new File("/home/oracle/pruebas/XMLproba0.xml");
         
         a.leer(file);
         a.pechar();
    }
    
}
