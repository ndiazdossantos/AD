/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proff;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import javax.persistence.*;
import java.util.*;
public class Proff {

   public static Connection conexion = null;
    public static Statement stmt;

    public static MongoClient client;
    public static MongoDatabase database;
    public static MongoCollection<Document> colecion;

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

    public static void conectar_a_servidor() {
        client = new MongoClient("localhost", 27017);
    }

    public static void conectar_a_base(String nomebase) {
        database = client.getDatabase(nomebase);
    }

    public static void conectar_a_unha_colecion(String coleccion) {
        colecion = database.getCollection(coleccion);
    }
    public static void main(String[] args) throws SQLException {
      Logger x = Logger.getLogger("org.mongodb.driver");
        x.setLevel(Level.OFF);
        
        //conectar a mongo        
        conectar_a_servidor();
        conectar_a_base("test");
        conectar_a_unha_colecion("vendas");
        
        
         EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("/home/oracle/Desktop/compartido/ExemploExameVehiculos1/vehicli.odb");
        EntityManager em = emf.createEntityManager();
        
          FindIterable<Document> docs = colecion.find();
        MongoCursor<Document> iterator = docs.iterator();
        
           while (iterator.hasNext()) {
            Document doc = iterator.next();
            Double _id = doc.getDouble("_id");
            String dni = doc.getString("dni");
            String codveh = doc.getString("codveh");
            System.out.println("_id: " + _id + ",\tdni: " + dni + ",\tcodveh: " + codveh);
            
            TypedQuery<Vehiculos> query = em.createQuery("SELECT a  FROM Vehiculos a where codveh='" + codveh + "'", Vehiculos.class);
            Vehiculos vehiculo = query.getSingleResult();
            String nomveh = vehiculo.getNomveh();
            int prezoorixe = vehiculo.getPrezoorixe();
            int anomatricula = vehiculo.getAnomatricula();
            
              System.out.println("nomvhe: " + nomveh + ", prezoorixe:" + prezoorixe + ", anomatricula: " + anomatricula);
              
            TypedQuery<Clientes> query2 = em.createQuery("SELECT a  FROM Clientes a where dni='" + dni + "'", Clientes.class);
            Clientes cliente = query2.getSingleResult();
            String nomec = cliente.getNomec();
            int ncompras = cliente.getNcompras();
            
             System.out.println("nomec: " + nomec + ", ncompras: " + ncompras);
            
            int desconto = 0;
            if (ncompras >= 1) {
                desconto = 500;
            }
            
            
            
            
             
            int pf = prezoorixe-((2019-anomatricula)*500)-desconto;
            System.out.println("prezoFinal: "+pf);
            System.out.println("");
            
            
            stmt.executeUpdate("INSERT INTO FINALVEH VALUES "
                   +"("+_id+", '"+dni+"', '"+nomec+"', tipo_vehf('"+nomveh+"',"+pf+")"
                   +")");
            
          }
        iterator.close();
        conexion.close();
        em.close();
        emf.close();
        client.close();
        
    }
    
}
