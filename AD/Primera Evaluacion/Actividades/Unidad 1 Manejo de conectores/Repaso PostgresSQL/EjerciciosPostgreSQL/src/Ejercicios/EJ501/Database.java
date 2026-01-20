package Ejercicios.EJ501;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection;
    private final String usuario = "postgres";
    private final String clave = "abc123.";
    private final String url = "jdbc:postgresql://localhost:5432/";

    // Constructor privado para que no se pueda instanciar desde fuera
    private Database() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, usuario, clave);
            }
        } catch (SQLException  e) {
            System.out.println("Error al abrir la conexión: " + e.toString());
        }
    }


    // Método para obtener la conexión
    public static Connection getInstance() {
        if (Database.connection == null) {
            new Database();
        }
        return Database.connection;
    }

    // Método para cerrar conexion
    public static void cerrarConexion() {
        try {
            if(connection !=null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada con éxito.");
            }
        }catch (SQLException e) {
            System.out.println("Error al cerrar la conexión " + e.toString());
        }
    }
}


