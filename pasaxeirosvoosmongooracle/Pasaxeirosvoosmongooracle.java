/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasaxeirosvoosmongooracle;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;


/**
 *
 * @author oracle
 */
public class Pasaxeirosvoosmongooracle {
    public static Connection conexion = null;
    public static Statement stmt;

    public static MongoClient client;
    public static MongoDatabase database;
    public static MongoCollection<Document> colecion;
    public static MongoCollection<Document> colecion2;

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
    
    public static void main(String[] args) throws SQLException {
        Logger x = Logger.getLogger("org.mongodb.driver");
        x.setLevel(Level.OFF);
        conexion = getConexion();
        conectar_a_servidor();
        conectar_a_base("internacional");
        conectar_a_unha_colecion("pasaxeiros");
        conectar_a_unha_colecion2("voos");
        
         stmt = conexion.createStatement();
          
         
        ResultSet rs = stmt.executeQuery("SELECT * FROM RESERVAS");
        while (rs.next()) {
            int codr = rs.getInt(1);
            String dni = rs.getString(2);
            int idvooida = rs.getInt(3);
            int idvoovolta = rs.getInt(4);
  
            //sumarlle 1 apartado b)
            
            colecion.updateOne(new BasicDBObject("_id", dni), new BasicDBObject("$inc", new BasicDBObject("nreservas", 1)));
            
            System.out.println("CODR: " + codr + " DNI: " + dni + ", ID-VOO-IDA: " + idvooida + ", "
                    + "ID-VOO-VOLTA: " + idvoovolta);
            
            
            //c)inserirse por cada reserva lida unha nova fila na taboa 'confirmadas'
           
            String oIda;
            String oVolta;
            Double pReserva = 0.0;
           
            
            BasicDBObject condicion = new BasicDBObject("_id", idvooida);
            Document doc = colecion2.find(condicion).first(); 
            oIda = doc.getString("orixe");
            Double prezo = doc.getDouble("prezo");
            pReserva = pReserva + prezo;
            
            BasicDBObject condicion2 = new BasicDBObject("_id", idvoovolta);
            Document doc2 = colecion2.find(condicion2).first();
            oVolta = doc2.getString("orixe");
            prezo = doc2.getDouble("prezo");
            pReserva = pReserva + prezo;
            
        stmt = conexion.createStatement();
        stmt.executeUpdate("INSERT INTO CONFIRMADAS " + "VALUES" + " (" + codr + ",'" + dni + "','" + oIda + "','"+oVolta+"',"+prezo+")");
            
        }
        client.close();
        conexion.close();
}

}