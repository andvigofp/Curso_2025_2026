package Ejercicios.Ejercicio404;

import org.basex.examples.api.BaseXClient;

import java.io.IOException;

public class ConexionBaseX {

    public static BaseXClient conexionBaseX() {
        try {
            BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123");

            session.execute("open biblioteca_404");

            System.out.println("Conexión exitosa a la base de datos 'biblioteca'");

            return session;
        }catch (IOException e) {
            System.out.println("Error de conexión con el servidor BaseX: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static void cerrarConexion(BaseXClient session) {
        if (session !=null) {
            try {
                session.close();
                System.out.println("Conexión BaseX cerrada correctamente");
            } catch (IOException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
