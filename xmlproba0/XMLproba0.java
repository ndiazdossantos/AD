/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlproba0;

import java.io.File;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author oracle
 */
public class XMLproba0 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws XMLStreamException, IOException {
        Metodos a = new Metodos();
        File file = new File("/home/oracle/pruebas/XMLproba0.xml");
        
        a.escribir(file);
        a.pechar();
    }
    
}
