package Conexiones;

import org.basex.examples.api.BaseXClient;

import java.io.IOException;

public class ConexionBaseX {
    private static BaseXClient session;

    private static final String host = "localhost";
    private static final int port = 1984;
    private static final String user = "admin";
    private static final String password = "abc123";

    public static BaseXClient conexionBaseX() {
        try {
            if (session == null) {
                session = new BaseXClient(host, port, user, password);
                session.execute("open biblioteca");
                System.out.println("Conectado exitosmanete a la base datos 'biblioteca'\n");
            }
            return session;
        } catch (Exception e) {
            System.out.println("Error al conecatr a la base datos. " + e.getMessage());
        }
        return null;
    }

    public static void cerrarConexion() {
        try {
            if (session != null) {
                session.close();
                System.out.printf("se a cerrando la base datos.\n");
            }
        } catch (IOException e) {
            System.out.printf("Error al cerrar la base datos. " + e.getMessage());
        }
    }
}
