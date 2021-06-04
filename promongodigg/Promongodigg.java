/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package promongodigg;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

public class Promongodigg {

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
public static void consulta_por_valor(String clave, Double valor){
    
    BasicDBObject condicion = new BasicDBObject (clave,new BasicDBObject("$gt",valor));
    FindIterable<Document> docs = colecion.find(condicion);
    MongoCursor<Document> itrt = docs.iterator();
   
    while(itrt.hasNext()){
    Document doc = itrt.next();
    String media = doc.getString("media");
    String status = doc.getString("status");

    
    System.out.println(" Media: "+media+" || Status: "+status);

    }
    
    itrt.close();
    
}

public static void consulta_por_idDouble(Double id1) {
    
        BasicDBObject condicion = new BasicDBObject("_id", id1);
        BasicDBObject proxecion = new BasicDBObject();
        proxecion.put("_id", 0);
        proxecion.put("status", 1);
        proxecion.put("diggs", 1);
        Document doc = colecion.find(condicion).projection(proxecion).first();
        String status = doc.getString("status");
        Double diggs = doc.getDouble("diggs");
        System.out.println("Status: "+status+" || Diggs:"+diggs);
        
    }

   public static void consulta_por_idObjectID(ObjectId id1) {
        BasicDBObject condicion = new BasicDBObject("_id", id1);
        BasicDBObject proxecion = new BasicDBObject();
        proxecion.put("_id", 1);
        proxecion.put("status", 1);
        proxecion.put("diggs", 1);
        Document doc = colecion.find(condicion).projection(proxecion).first();
        ObjectId id = doc.getObjectId("_id");
        String status = doc.getString("status");
        int diggs = doc.getInteger("diggs");
        System.out.println("Status: " + status + ", diggs: " + diggs);
    }
   
  public static void incrementar_por_campo(String clave, String valor, String claveAumentar, Double valorAumentar) {
        colecion.updateMany(new BasicDBObject("status", valor), new BasicDBObject("$inc", new BasicDBObject(claveAumentar, valorAumentar)));

    }
  
  public static void decrementar_por_campo(String clave, String valor, String claveDecrementar, Double valorDecrementar) {
        colecion.updateMany(new BasicDBObject("status", valor), new BasicDBObject("$inc", new BasicDBObject(claveDecrementar, -valorDecrementar)));

    }
  
public static void multiplicar_por_valor(String clave, String valor, String claveMultiplicar, Double valorMultiplicar){
    
   BasicDBObject condicion = new BasicDBObject(clave,valor);
    FindIterable<Document> docs = colecion.find(condicion);
    MongoCursor<Document> iterator = docs.iterator();
   
    while(iterator.hasNext()){
    Document doc = iterator.next();
  //  ObjectId id = doc.getObjectId("_id");
    Double id = doc.getDouble("_id");
    Double diggs = doc.getDouble("diggs");

    colecion.updateOne(new BasicDBObject("_id", id), new BasicDBObject("$set", new BasicDBObject(claveMultiplicar, valorMultiplicar*diggs)));
   
    }
    
    
    
    iterator.close();
    
    
}
   
           


    public static void main(String[] args) {
       Logger x = Logger.getLogger("org.mongodb.driver");
       x.setLevel(Level.OFF);
       
       conectar_a_servidor();
       conectar_a_base("digg");
       conectar_a_unha_colecion("stories");
      
       /*
       1) Amosar valores dos campos media e  status dos documentos onde o campo 
       diggs sexa maior que un valor pasado por parametro 
       ( usa o valor de referencia que ti queiras,  relacionado cos valores 
       que  teñen os documentos da coleción) 
       */
       
       consulta_por_valor("diggs",7000.0);
       
       /*
       2) Inserir dende tres documentos cos seguintes contidos 
       */
       
       Document documento = new Document("_id", 1.0)
                .append("media", "papel")
                .append("status", "a")
                .append("diggs", 1000.0);
        Document documento2 = new Document("_id", 2.0)
                .append("media", "papel")
                .append("status", "b")
                .append("diggs", 2000.0);
        Document documento3 = new Document("_id", 3.0)
                .append("media", "carton")
                .append("status", "a")
                .append("diggs", 3000.0);
        ArrayList<Document> lista = new ArrayList<>();
        lista.add(documento);
        lista.add(documento2);
        lista.add(documento3);
        
  //      colecion.insertMany(lista);
       
        /*
        3) Amose somente os valores dos   
        campos  status e diggs dun documento da clave _id = 2.0
        */   
         consulta_por_idDouble(2.0);
         
         
         /*
         4) amose somente os valores dos   campos  status e diggs dun 
         documento de clave _id = unha das que ten xeneradas automaticamente 
         (tipo ObjectId("………….."))
         */
         consulta_por_idObjectID(new ObjectId("4ba27413238d3ba3ca0012bc"));
         //db.stories.find({status:{$exists:true}, diggs:{$exists:true}}, {status:1,diggs:1}).count()
         
         /*
                 5)Incremente nunha cantidade 
         X  o campo diggs dos documentos con campo status  con valor  
         Y ( sendo X e Y dous valores elexidos por ti pero tendo en conta que o valor
         Y elexido debe estar polo menos en dous documentos  ) . lo incrementar en 3.0
         o valor do campo diggs dos documentos que teñen por campo status o valor ”a”. 
                 
         */
       
        // incrementar_por_campo("status","a","diggs",3.0);
        // db.stories.find({status:"a"})
       
         /*
         
         6) Facer o mesmo pero ahora disminuindo en 2 o valor de ditos campos.
         
         */
         
        // decrementar_por_campo("status","a","diggs",3.0);
        // db.stories.find({status:"a"})
       
         /*
         
         7) Multiplicar por 3 o campo diggs dos documentos con campo status 
         con valor  Y ( sendo X e Y dous valores elexidos por ti pero tendo en 
         conta que o valor Y elexido debe estar polo menos en dous documentos  )
         Por exemplo multiplicar por 3 o valor do campo diggs dos documentos que 
         teñen por campo status o valor“”a”. 
         */
         
       //  multiplicar_por_valor("status","a","diggs",4.0);
       // db.stories.find({status:"a"})
         
    }
    
}
