/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlwriter16;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import serializacion2.Product;
/**
 *
 * @author oracle
 */
public class XmlWriter16 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws javax.xml.stream.XMLStreamException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, XMLStreamException, ClassNotFoundException {
     String ruta = "/home/oracle/pruebas/serial2.txt";
     
     FileInputStream fich = new FileInputStream(ruta);
     ObjectInputStream leer = new ObjectInputStream(fich);
    
     XMLOutputFactory obx;
     XMLStreamWriter xml;
     FileWriter escribir;
     
     obx = XMLOutputFactory.newInstance();
     escribir = new FileWriter("/home/oracle/pruebas/products.xml");
     xml = obx.createXMLStreamWriter(escribir);
     
     Product a;
     a = (Product) leer.readObject();
   
     xml.writeStartDocument("1.0"); 
     xml.writeStartElement("produtos"); 
     
     while(a !=null){
         
         xml.writeStartElement("produto"); 
         
         xml.writeStartElement("codigo");
         xml.writeCharacters(a.getCodigo());
         xml.writeEndElement();
         
         xml.writeStartElement("descricion");
         xml.writeCharacters(a.getDescricion());
         xml.writeEndElement();
         
         xml.writeStartElement("prezo");
         xml.writeCharacters(String.valueOf(a.getPrezo()));
         xml.writeEndElement();
         
         xml.writeEndElement(); //pechamos produto
         a = (Product) leer.readObject();
     }
     
     xml.writeEndElement();
     xml.writeEndDocument();
     escribir.close();
     leer.close();

     
    }
    
}
