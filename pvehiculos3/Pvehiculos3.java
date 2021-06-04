/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pvehiculos3;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.bson.Document;

/**
 *
 * @author oracle
 */
public class Pvehiculos3 {
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
        conectar_a_servidor();
        conectar_a_base("test");
        conectar_a_unha_colecion("finalveh");
        conexion = getConexion();
        stmt = conexion.createStatement();
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("/home/oracle/Desktop/compartido/Pvehiculos3/vehicli.odb");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Vendas> query =
            em.createQuery("SELECT venda FROM Vendas venda", Vendas.class);
        List<Vendas> results = query.getResultList();
        for (Vendas venda : results) {
            int id = venda.getId();
            String dni = venda.getDni();
            String codvh = venda.getCodvh();
            Double tasas = venda.getTasas();
            System.out.println("ID: "+id+",\tDNI: "+dni+",\tCODVH: "+codvh
            +",\tTASAS: "+tasas);
            
            java.math.BigDecimal prezoorixe = new java.math.BigDecimal(0);
            java.math.BigDecimal anomatricula= new java.math.BigDecimal(0);
            java.math.BigDecimal ncompras= new java.math.BigDecimal(0);
            String nomcli="";
            String nomeveh="";
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM VEHICULOS where idv='"+codvh+"'");
            
        while (rs.next()) {
            nomeveh=rs.getString(2);
            java.sql.Struct y = (java.sql.Struct) rs.getObject(3);
            Object[] campos = y.getAttributes();
            prezoorixe = (java.math.BigDecimal) campos[0];
            anomatricula = (java.math.BigDecimal) campos[1];

           
            System.out.println("NomeVeh: "+nomeveh+", prezoOrixe: "+prezoorixe+", anoMatricula: "+anomatricula);
        }
        
         ResultSet rs2 = stmt.executeQuery("SELECT * FROM CLIENTES where idcli='"+dni+"'");
         
        while (rs2.next()) {
            java.sql.Struct z = (java.sql.Struct) rs2.getObject(2);
            Object[] campos = z.getAttributes();
            nomcli = (String) campos[0];
            ncompras = (java.math.BigDecimal) campos[1];

           
            System.out.println("NomeC: "+nomcli+", NCompras: "+ncompras);
        }
        
        
        int desconto = 0;
            if (ncompras.intValue() >= 1) {
                desconto = 500;
            }
            
            int prezoFinal = prezoorixe.intValue()-((2019-anomatricula.intValue())*500)-desconto+tasas.intValue();
            System.out.println("prezoFinal: "+prezoFinal);
            System.out.println("");
            
          
            
            
                Document doc1 = new Document("_id",id)
                .append("dni", dni)
                .append("nomecli",nomcli)
                .append("nomeveh", nomeveh)
                .append("prezoFinal", prezoFinal);
        colecion.insertOne(doc1);    
            
        }
        
        conexion.close();
        client.close();
        em.close();
        emf.close();
    
    
    
    
    
    }
    
}
