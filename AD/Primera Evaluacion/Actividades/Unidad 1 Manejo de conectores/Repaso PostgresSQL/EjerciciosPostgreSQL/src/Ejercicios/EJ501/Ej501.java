package Ejercicios.EJ501;

import java.sql.*;
import java.util.Scanner;

/**
 * Usando Java se pide crear una base de datos llamada listaLibros que contenga una tabla libros con los siguientes atributos:
 *
 * id: de tipo serial y será la clave primaria
 * titulo: cadena de texto
 * autor: del tipo Autor
 * año_publicacion: tipo entero
 * Se tendrá que crear también el tipo Autor el cual tiene la siguiente estructura:
 *
 * nombre_autor: cadena de texto
 * fecha: tipo varchar
 * Se utilizará Java para la creación de dicha tabla
 *
 * Además, deberá permitir:
 *
 * Insertar datos
 * Consulta datos
 * Actualizar los datos
 * Eliminación de datos.
 */
public class Ej501 {
    static final Scanner teclado = new Scanner(System.in);
    static Connection connection;

    public static void main(String[] args)  {

        String databaseName = "listalibros";

        connection=null;
        //boolean existe = false;

        try  {
            // Comprobamos conexión a la base de datos para ver si existe
            connection = Database.getInstance();
            // Existe, la eliminamos y la volvemos a crear
            Statement stmt = connection.createStatement();

            // Cierra todas las conexiones a la base de datos si existe.
            //closeActiveConnections(connInicial, databaseName);

                try {
                    stmt.executeUpdate("DROP DATABASE " + databaseName);
                } catch (SQLException e) {
                    // Captura el error si la base de datos no existe.
                    System.out.println("La base de datos no existe, creando una nueva.");
                }
                stmt = connection.createStatement();
                stmt.executeUpdate("CREATE DATABASE " + databaseName);
                System.out.println("Base de datos " + databaseName + " creada exitosamente.");
                Database.cerrarConexion();
        } catch (SQLException e) {

        }finally {
            Database.cerrarConexion();
        }

        try (Connection connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/" + databaseName, "postgres", "abc123.")) {
            // Crear el esquema y la tabla
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate("CREATE SCHEMA objetos");
                stmt.executeUpdate("CREATE TYPE objetos.tipo_autor AS (nombre_autor varchar(255), fechaNacimiento varchar(100))");
                String createTableSQL = "CREATE TABLE objetos.libros (id serial PRIMARY KEY, titulo VARCHAR, autor objetos.tipo_autor, año_publicacion integer)";
                stmt.executeUpdate(createTableSQL);
                System.out.println("Tabla libros creada exitosamente.");
            }
            // Aquí puedes llamar a tu método para el menú
            MEj501 metodos = new MEj501(connection);
            metodos.Menu(teclado);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeActiveConnections(Connection conn, String databaseName) {
        String sql = "SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = '" + databaseName + "' AND pid <> pg_backend_pid();";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Conexiones activas a " + databaseName + " terminadas.");
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexiones activas: " + e.getMessage());
        }
    }
}
