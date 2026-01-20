package Ejercicios.Ej303;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * una clase llamada ConsultaNombres que devuelva los nombres de los empleados
 * que empiezan por una letra determinada. Esta letra será introducida por el usuario.
 */

public class ConsultaNombres {
    private static Connection connection = Database.getInstance();

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        try {
            System.out.println("Introduzca la letra por la que querría buscar");

            String letra = "";
            while (letra.equals("") || letra.length() != 1) {
                letra = teclado.next().toUpperCase();
            }

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from empleado where Nombre like '" + letra + "%'");

            while (rs.next()) {
                System.out.println("\tNSS: " + rs.getInt("NSS") +
                        ", Nombre; " + rs.getString("Nombre") +
                        ", Apellido 1: " + rs.getString("Apel1") +
                        ", Apellido 2: " + rs.getString("Apel2") +
                        ", Sexo: " + rs.getString("Sexo") +
                        ", Dirección: " + rs.getString("Dirección") +
                        ", Fecha nacimiento: " + rs.getString("Numdept") +
                        ", NuSSsup: " + rs.getString("NSSsup"));
            }
        }catch (SQLException e) {
            System.out.println("Error");
        } finally {
            try {
                connection.close();
                teclado.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
