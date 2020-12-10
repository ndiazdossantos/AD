package exa15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Exa15 {

    public static Connection conexion = null;

    public static Connection getConexion() throws SQLException {
        String usuario = "hr";
        String password = "hr";
        String host = "localhost";
        String puerto = "1521";
        String sid = "orcl";
        String ulrjdbc = "jdbc:oracle:thin:" + usuario + "/" + password + "@" + host + ":" + puerto + ":" + sid;

        conexion = DriverManager.getConnection(ulrjdbc);
        return conexion;
    }

    public static void closeConexion() throws SQLException {
        conexion.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException, XMLStreamException {
        Statement st;
        ResultSet rst;
        
        ResultSet rst2;
        Statement st2;

        String ruta = "/home/oracle/pruebas/platoss";

        FileInputStream fich = new FileInputStream(ruta);
        ObjectInputStream leer = new ObjectInputStream(fich);

        Platos a;
        
        getConexion();
        st = conexion.createStatement();
        st2 = conexion.createStatement();
        a = (Platos) leer.readObject();
        
        
       //ultima parte 
     XMLOutputFactory obx;
     XMLStreamWriter xml;
     FileWriter escribir;
     
     obx = XMLOutputFactory.newInstance();
     escribir = new FileWriter("/home/oracle/pruebas/totalgraxas.xml");
     xml = obx.createXMLStreamWriter(escribir);
     
     xml.writeStartDocument("1.0"); 
     xml.writeStartElement("produtos"); 
        
        //acumulador
        int acumulador =  0;
        
        while (a != null) {
            String codp = a.getCodigop();
            String nomep = a.getNomep();
            
            rst = st.executeQuery("SELECT * FROM composicion where CODP='" + codp + "'");
            while (rst.next()) {

             //  System.out.println(rst.getString("CODP") + "\t" + rst.getString("CODC")+"\t" + rst.getInt("PESO"));
           
                String codc= rst.getString("CODC");
                int peso = rst.getInt("PESO");
                
            rst2 = st2.executeQuery("SELECT * FROM componentes where CODC='" + codc + "'");
            
           
            
            while (rst2.next()) {

           //  System.out.println(rst2.getString("CODC") + "\t" + rst2.getString("NOMEC")+"\t" + rst2.getInt("GRAXA"));
              String graxa= rst2.getString("GRAXA");
              float graxacomponente = (Float.parseFloat(graxa)*peso)/100;
              acumulador+=graxacomponente;
              
         //       System.out.println("Graxacomponente parcial "+graxacomponente);
              
              }
           
            }
       //     System.out.println("Graxa total "+acumulador);
           // acumulador = 0;
            
            
        //    System.out.println(a.getCodigop());
        //    System.out.println(a.getNomep());
         
       //     System.out.println(a.toString());
            
            
            
            // escribir xml
            
             
         xml.writeStartElement("Platos"); 
         
         xml.writeStartElement("Plato");
         xml.writeAttribute("Codigop",codp);
  
         
         xml.writeStartElement("nomep");
         xml.writeCharacters(nomep);
         xml.writeEndElement();
         
         xml.writeStartElement("graxaTotal");
         xml.writeCharacters(String.valueOf(acumulador));
         xml.writeEndElement();
         xml.writeEndElement();
         
         xml.writeEndElement(); //pechamos produto
         a = (Platos) leer.readObject();
            
         
         //hay que igualarlo a 0 al final para poder leer el acumulador
         acumulador = 0;
            
            
        }
     xml.writeEndElement();
     xml.writeEndDocument();
     xml.close();
     leer.close();
     closeConexion();

    }
}
