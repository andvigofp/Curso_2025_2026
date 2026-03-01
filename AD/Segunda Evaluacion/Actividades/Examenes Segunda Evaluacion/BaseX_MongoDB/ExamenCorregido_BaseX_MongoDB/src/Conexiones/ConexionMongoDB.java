package Conexiones;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionMongoDB {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDatabase conexionMongoDB() {
        try {
            if (mongoClient == null) {
                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                mongoClient = MongoClients.create("mongodb://localhost:27017");
                database = mongoClient.getDatabase("peliculas");
                System.out.println("Conectado exitosamente a la base datos 'peliculas'\n");
                return database;
            }
        }catch (Exception e){
            System.err.println("Error al conectar al base de dados");
        }
        return null;
    }

    public static void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
            database = null;
            System.out.println("Se a cerrado la conexion exitosamente a la base datos 'pelciulas'\n");
        }
    }
}
