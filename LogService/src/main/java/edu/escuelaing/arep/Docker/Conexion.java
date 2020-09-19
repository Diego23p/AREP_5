package edu.escuelaing.arep.Docker;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.util.ArrayList;

import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author Diego23p
 */
public class Conexion {

    public Conexion() {}

    public void addData(String cadena) {

        ArrayList<Data> data = new ArrayList<Data>();

        data.add(new Data(cadena, new Date()));

        try {
            // PASO 1: Conexión al Server de MongoDB Pasandole el host y el puerto
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://ec2-54-236-9-109.compute-1.amazonaws.com:27017"));

            // PASO 2: Conexión a la base de datos
            DB db = mongoClient.getDB("Datas");

            // PASO 3: Obtenemos una coleccion para trabajar con ella
            DBCollection collection = db.getCollection("Data");

            // PASO 4: "CREATE"
            for (Data dat : data) {
                collection.insert(dat.toDBObjectData());
            }

            // PASO FINAL: Cerrar la conexion
            mongoClient.close();

        } catch (Exception ex) {
            System.out.println("Exception al conectar al server de Mongo: " + ex.getMessage());
        }
    }

    public JSONObject getAllData() {
        
        JSONObject myObject = new JSONObject();

        try {
            // PASO 1: Conexión al Server de MongoDB Pasandole el host y el puerto
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://ec2-54-236-9-109.compute-1.amazonaws.com:27017"));

            // PASO 2: Conexión a la base de datos
            DB db = mongoClient.getDB("Datas");

            // PASO 3: Obtenemos una coleccion para trabajar con ella
            DBCollection collection = db.getCollection("Data");

            // PASO 4: Obtener todos
            DBCursor cursor = collection.find();
            
            ArrayList<String> lista = new ArrayList<String>();
            
            try {
                while (cursor.hasNext()) {
                    //myObject.append("Resultado", cursor.next().toString() );
                    lista.add(cursor.next().toString());
                }
            } finally {
                cursor.close();
            }
            
            //Ultimos 10 elementos
            myObject = lastElements(lista);
            

            // PASO FINAL: Cerrar la conexion
            mongoClient.close();
            
            return myObject;

        } catch (Exception ex) {
            System.out.println("Exception al conectar al server de Mongo: " + ex.getMessage());
        }
        return myObject;
    }
    
    public JSONObject lastElements(ArrayList lista){
        int j = 0;
        JSONObject objeto = new JSONObject();
        for (int i=lista.size()-1 ; i>=0 && j<=9 ; i--){
            objeto.append("Resultado", lista.get(i));
            j++;
        }
        return objeto;
    }
}
