package Aplicacion;


import Conexiones.ConexionMongoDB;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class MEMongoDB {
    private static MongoDatabase sessionMongoDB = ConexionMongoDB.conexionMongoDB();

    //Obtener todas las películas con una puntuación de críticas superior a 4.5
    public void obtenerPeliculasSuperior() {
        try {
            Bson filter = gt("criticas.puntuacion", 4.5d);

            MongoCollection<Document> collection = sessionMongoDB.getCollection("peliculas");

            FindIterable<Document> result = collection.find(filter);

            for (Document document : result) {
                System.out.println(document.toJson());
            }
        }catch (Exception e){
            System.out.println("Error al en la consulta. " + e.getMessage());
        }
    }

    //Obtener todas las películas del género Drama dirigidas por Christopher
    //Nolan.
    //Resultado no salir nada es correcto, este director no tiene ninguna pelicula con genero Drama
    public void obtnerPelciulasGeneroDrama() {
        try {
            Bson filter = and(eq("genero", "Drama"), eq("director", "Chistopher Nolan"));

            MongoCollection<Document> collection = sessionMongoDB.getCollection("peliculas");
            FindIterable<Document> result = collection.find(filter);

            for (Document document : result) {
                System.out.println(document.toJson());
            }
        }catch (Exception e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    //Obtener el título y el director de las películas con una duración superior a
    //120 minutos y una clasificación R.
    public void obtenerTituloDirectorDuracion() {
        try {
            Bson filter = and(gt("duracion_minutos", 120L), eq("clasificacion", "R"));
            Bson project = and(eq("_id", 0L), eq("titulo", 1L), eq("director", 1L));


            MongoCollection<Document> collection = sessionMongoDB.getCollection("peliculas");
            FindIterable<Document> result = collection.find(filter)
                    .projection(project);

            for (Document document : result) {
                System.out.println(document.toJson());
            }
        }catch (Exception e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public void obtenerTituloAñoLanzamientoNumeroActores() {
        try {
            MongoCollection<Document> collection = sessionMongoDB.getCollection("peliculas");

            AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                            new Document("genero", "Ciencia ficción")),
                    new Document("$project",
                            new Document("_id", 0L)
                                    .append("titulo", 1L)
                                    .append("año", 1L)
                                    .append("numero_actores",
                                            new Document("$size", "$actores")))));

            for (Document document : result) {
                System.out.println(document.toJson());
            }
        }catch (Exception e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    //Eliminar todas las películas lanzadas antes de 1990, que tengan una duración
    //superior a 120 minutos pero que tengan una puntuación inferior a 3.5
    //Saldra por else porque no cumple la última condición no existe ninguna puntuación inferior a 3.5. Es correcto
    public void eliminarPelicula() {
        try {
            MongoCollection<Document> collection = sessionMongoDB.getCollection("peliculas");

            Document document = new Document("año", new Document("$lt", 1990L))
                    .append("duracion_minutos", new Document("$gt", 120L))
                    .append("criticas.puntuacion", new Document("$lt", 3.5d));

            DeleteResult deleteResult = collection.deleteOne(document);

            if (deleteResult.getDeletedCount() > 0) {
                System.out.println("Eliminando la pelicula exitosamente" + deleteResult.getDeletedCount());
            }else  {
                System.out.println("No se a podido eliminar la pelicula " + deleteResult.getDeletedCount());
            }
        }catch (Exception e) {
            System.out.println("Error al eliminar la pelciula: " + e.getMessage());
        }
    }

    public void insertarPelciula() {
        try {
            MongoCollection<Document> collection = sessionMongoDB.getCollection("peliculas");

            List<Document> actores = Arrays.asList(new Document()
                    .append("nombre", "Tim Robbins")
                    .append("personaje", "Andy Dufresne")
                    .append("edad", 36),
            new Document()
                    .append("nombre", "Monga Freeman")
                    .append("personaje", "Red")
                    .append("nacionalidad", "Estadounidense"));

            Document document = new Document()
                    .append("titulo", "Cadena perpetua")
                    .append("director", "Frank Darabont")
                    .append("año", 1994)
                    .append("duracion_minutos", 142)
                    .append("actores", actores);

            collection.insertOne(document);

            System.out.println("Inserido la pelciula exitosamente" + document.toJson());
        }catch (Exception e) {
            System.out.println("Error al insertar la pelciula: " + e.getMessage());
        }
    }
}
