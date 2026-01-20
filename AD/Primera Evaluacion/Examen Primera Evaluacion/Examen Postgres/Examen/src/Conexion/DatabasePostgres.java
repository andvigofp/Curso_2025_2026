package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabasePostgres {
    private static Connection conn;
    private final String usuario = "postgres";
    private final String password = "abc123.";
    private final String url = "jdbc:postgresql://localhost:5432/examenUnidadUno";

    private DatabasePostgres() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, usuario, password);
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.toString());
        }
    }

    public static Connection getInstance() {
        if (DatabasePostgres.conn == null) {
            System.out.println("Conexión exitosa");
            new DatabasePostgres();
        }
        return DatabasePostgres.conn;
    }

    public static void getCerrarConexion() {
        try {
            if (conn !=null && !conn.isClosed()) {
                System.out.println("Se ha cerrado la conexión ");
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.toString());
        }
    }

}
