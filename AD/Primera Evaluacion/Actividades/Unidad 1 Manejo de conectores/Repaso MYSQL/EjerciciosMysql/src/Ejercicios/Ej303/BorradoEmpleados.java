package Ejercicios.Ej303;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * una clase llamada BorradoEmpleados que permita borrar un empleado con un número determinado.
 * Este número será introducido por el usuario.
 */

public class BorradoEmpleados {
    private static Connection connection = Database.getInstance();

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("Introduzca el número de empleado a borrar");

            int numero = -1;

            while (numero < 0) {
                numero = teclado.nextInt();
            }

            Statement stm = connection.createStatement();

            //Desactivamos las foreign_key
            stm.execute("SET FOREIGN_KEY_CHECKS=0");

            PreparedStatement ps = connection.prepareStatement("delete from empleado where NSS = ?");
            ps.setInt(1, numero);
            int numTuplas = ps.executeUpdate();

            System.out.println("Sentencia: " + ps.toString());
            System.out.println("Tuplas afectadas: " + numTuplas);

            ps.close();

            //Volvemos a activarlas las foreign_key
            stm = connection.createStatement();
            stm.execute("SET FOREIGN_KEY_CHECKS=1");
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            try {
                connection.close();
                teclado.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
