/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprobar0ler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author oracle
 */
public class Metodos {
    
    XMLStreamReader leer;
    
    public void leer(File file) throws FileNotFoundException, XMLStreamException{
      
     leer = XMLInputFactory.newInstance().createXMLStreamReader(new FileReader(file));
     
     while(leer.hasNext()){
         int eventType = leer.getEventType();
         
         if(eventType == XMLStreamConstants.START_ELEMENT){
             String  nome = leer.getLocalName();
             if(nome.equals("autor")){
                 String atributo = leer.getAttributeValue(0);
                 System.out.println(atributo);
             } else if(nome.equals("nome")){
                 String contidoNome = leer.getElementText();
                 System.out.println(contidoNome);
            }else if(nome.equals("titulo")){
                String contidoTitulo = leer.getElementText();
                 System.out.println(contidoTitulo);
            }
         }
         leer.next();
     }
  
    }
   
    public void pechar() throws XMLStreamException{
        leer.close();
    }
}
