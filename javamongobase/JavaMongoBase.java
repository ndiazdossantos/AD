/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamongobase;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.UpdateResult;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author oracle
 */
public class JavaMongoBase {

public static MongoClient client;
public static MongoDatabase database;
public static MongoCollection<Document> colecion;

 public static void  conectar_a_servidor(){
 client = new MongoClient("localhost",27017);
} 
public static void  conectar_a_base(String nomebase){
     database = client.getDatabase(nomebase);
}
public static void  conectar_a_unha_colecion(String coleccion){
colecion = database.getCollection(coleccion);
}

public static void consulta_por_id(ObjectId id1){
    
    BasicDBObject condicion = new BasicDBObject("_id",id1);
    BasicDBObject proxecion = new BasicDBObject();
    
    proxecion.put("_id", 1);
    proxecion.put("kind", 1);
    proxecion.put("score", 1);
    
   // Document doc = colecion.find(condicion).projection(Projections.include("_id","kind","score")).first();
   // sustitución del include por los put, se non quero recolleros aplico 0
    
    
    Document doc = colecion.find(condicion).projection(proxecion).first();
    ObjectId id = doc.getObjectId("_id");
    String knd = doc.getString("kind");
    Double scr = doc.getDouble("score");
    System.out.println(" id "+id+ " kind "+knd+" score "+scr);

}

public static void consulta_por_valor(String clave, Double valor){
    
    BasicDBObject condicion = new BasicDBObject(clave,valor);
    FindIterable<Document> docs = colecion.find(condicion);
    MongoCursor<Document> itrt = docs.iterator();
   
    while(itrt.hasNext()){
    Document doc = itrt.next();
    ObjectId id = doc.getObjectId("_id");
    String knd = doc.getString("kind");
    Double scr = doc.getDouble("score");
    Double std = doc.getDouble("student");
    
    
    System.out.println(" id "+id+ " kind "+knd+" score "+scr+" student "+std);

    }
    
    itrt.close();
    
}

public static void actualizar_por_id(ObjectId id1,String clave, Double valor){
    
    colecion.updateOne(new BasicDBObject("_id",id1), new BasicDBObject("$set",new BasicDBObject(clave,valor)));
    
}

public static void incrementar_por_id(ObjectId id1,String clave, Double valor){
    
    colecion.updateOne(new BasicDBObject("_id",id1), new BasicDBObject("$inc",new BasicDBObject(clave,valor)));
    
}

public static void actualizar_campo(){
    
    
    
    
}


    public static void main(String[] args) {
        
       Logger x = Logger.getLogger("org.mongodb.driver");
       x.setLevel(Level.OFF);
       
       conectar_a_servidor();
       conectar_a_base("training");
       conectar_a_unha_colecion("scores");
       
       consulta_por_id(new ObjectId("4c90f2543d937c033f42471d"));
       consulta_por_valor("student",500.0);
       actualizar_por_id(new ObjectId("4c90f2543d937c033f42471d"),"score",544.0);
       incrementar_por_id(new ObjectId("4c90f2543d937c033f42471d"),"score",2.0);
       
    //prueba del richard
    Document inser1 = new Document("_id",100.0)
            .append("kind","taller")
            .append("score", 222.0)
            .append("enderezo",
                    new Document()
                            .append("rua","Urzaiz")
                            .append("numero", 7));
    colecion.insertOne(inser1);
       
   
       
       
   // actualizar campo 
       
     //  colecion.updateMany(new Document("student",1.0),new Document("$set", new Document("score",0.0)));
  
   // borrar por id
       
       colecion.deleteOne(new BasicDBObject("_id",100.0));
       
   // borrar varios
       
       colecion.deleteMany(new BasicDBObject("student",6.0));
       
       
    BasicDBObject condicion = new BasicDBObject("kind", "essay")
                .append("student", new BasicDBObject("$gt", 0).append("$lt", 3));
      
        FindIterable<Document> docs = colecion.find(condicion);
        MongoCursor<Document> iterator = docs.iterator();
        while (iterator.hasNext()) {
            Document doc = iterator.next();
            ObjectId id = doc.getObjectId("_id");
            String tipo = doc.getString("kind");
            Double puntuacion = doc.getDouble("score");
            Double estudiante = doc.getDouble("student");
            System.out.println("id: " + id + ", kind: " + tipo + ", score: " + puntuacion + " student: " + estudiante);
        }
        iterator.close();
       
       client.close();
    }
    
  
    
    
}
