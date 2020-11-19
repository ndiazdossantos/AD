/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlproba0;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author oracle
 */
public class Metodos {
    XMLStreamWriter escribir;
    
    public void escribir(File file) throws XMLStreamException, IOException{
        
        escribir = XMLOutputFactory.newInstance().createXMLStreamWriter(new FileWriter(file));
        
        escribir.writeStartDocument("1.0"); 
        escribir.writeStartElement("autores");
        escribir.writeStartElement("autor");
        escribir.writeAttribute("codigo","a1");
        escribir.writeStartElement("nome"); 
        escribir.writeCharacters("Alexandre Dumas");
        escribir.writeEndElement(); // pecho nome
        escribir.writeStartElement("titulo");
        escribir.writeCharacters("EL conde de montecristo");
        escribir.writeEndElement(); // pecho titulo
        escribir.writeStartElement("titulo");
        escribir.writeCharacters("Los miserables");
        escribir.writeEndElement(); // Pecho titulo
        escribir.writeEndElement(); // pecho autor
        
        
        escribir.writeStartElement("autor");
        escribir.writeAttribute("codigo","a2");
        escribir.writeStartElement("nome"); 
        escribir.writeCharacters("Fiodor Dostoyevski");
        escribir.writeEndElement(); // pecho nome
        escribir.writeStartElement("titulo");
        escribir.writeCharacters("El idiota");
        escribir.writeEndElement(); // pecho titulo
        escribir.writeStartElement("titulo");
        escribir.writeCharacters("Noches blancas");
        escribir.writeEndElement(); // Pecho titulo
        escribir.writeEndElement(); // pecho autor
        escribir.writeEndElement(); // pecho autores
        escribir.writeEndDocument(); // pecho documento
    }
    
    public void pechar() throws XMLStreamException{
        escribir.close();
    }
    
}
