/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pvehiculos2;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author oracle
 */
public class Pvehiculos2 {

    public static Connection conexion = null;
    public static Statement stmt;
    
    public static MongoClient client;
    public static MongoDatabase database;
    public static MongoCollection<Document> colecion;
    public static MongoCollection<Document> colecion2;

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
    public static void conectar_a_unha_colecion2(String coleccion) {
        colecion2 = database.getCollection(coleccion);
    }
    
    public static void main(String[] args) throws SQLException {
        Logger x = Logger.getLogger("org.mongodb.driver");
        x.setLevel(Level.OFF);
        conectar_a_servidor();
        conectar_a_base("basevehiculos");
        conectar_a_unha_colecion("vehiculos");
        conectar_a_unha_colecion2("clientes");
        conexion = getConexion();
        stmt = conexion.createStatement();
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("finalveh.odb");
        EntityManager em = emf.createEntityManager();
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM vendas");
        
        while (rs.next()) {
            int id = rs.getInt(1);
            String dni = rs.getString(2);
            java.sql.Struct tipoVeh = (java.sql.Struct) rs.getObject(3);
            Object[] campos = tipoVeh.getAttributes();
            String codveh = (String) campos[0];
            java.math.BigDecimal tasas = (java.math.BigDecimal) campos[1];
            
              System.out.println("ID: " + id + ", DNI: " + dni + "\nCODVEH: " + codveh + ", TASAS: " + tasas);
           
               BasicDBObject condicion = new BasicDBObject("_id", codveh);
               BasicDBObject proxecion = new BasicDBObject();
               proxecion.put("_id", 1);
               proxecion.put("prezoorixe", 1);
               proxecion.put("anomatricula", 1);
               proxecion.put("nomveh", 1);
               
               Document doc = colecion.find(condicion).projection(proxecion).first();
               Double prezoorixe = doc.getDouble("prezoorixe");
               Double anomatricula = doc.getDouble("anomatricula");
               String nomveh = doc.getString("nomveh");
               System.out.println("PrezoOrixe: " + prezoorixe.intValue() + ", AnoMatricula: " + anomatricula.intValue()+", NomeVehiculo: "+nomveh);
               
                BasicDBObject condicion2 = new BasicDBObject("_id", dni);
                BasicDBObject proxecion2 = new BasicDBObject();
                proxecion.put("_id", 1);
                proxecion2.put("nomec", 1);
                proxecion2.put("ncompras", 1);
                Document doc2 = colecion2.find(condicion2).projection(proxecion2).first();
                String nomec = doc2.getString("nomec");
                Double ncompras = doc2.getDouble("ncompras");
                System.out.println("NomeC: " + nomec + ", NCompras: " + ncompras.intValue());
                
                
                int desconto = 0;
                if (ncompras >= 1) {
                desconto = 500;
            }
                
            int prezoFinal=prezoorixe.intValue()-((2019-anomatricula.intValue())*500)-desconto+tasas.intValue();
            System.out.println("PrezoFinal: "+prezoFinal);
            System.out.println("");
            
            Venfin venta = new Venfin(Double.valueOf(id), dni, nomec, nomveh, Double.valueOf(prezoFinal));
            em.getTransaction().begin();
            em.persist(venta);
            em.getTransaction().commit();
            
        }
        conexion.close();
        em.close();
        emf.close();
        client.close();
        
     
    }
    
}
