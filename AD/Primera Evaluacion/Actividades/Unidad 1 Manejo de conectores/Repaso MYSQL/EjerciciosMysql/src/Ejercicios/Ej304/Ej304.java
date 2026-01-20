package Ejercicios.Ej304;

import Ejercicios.Ej302.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Implementa un programa en Java donde mediante una clase llamada TransaccionEmpleado se permita insertar en la base de datos Empleados
 * tres nuevos contables: Pedro, Lucía y Daniel que pertenecerán al departamento 1.
 *
 * ¿Qué pasa si hay un error al insertar alguno de los empleados?
 *
 * Utiliza transacciones para controlar que se inserten los 3 empleados a la vez y si ocurre un error, no se insertará ninguno.
 */

public class Ej304 {
    private static Connection connection = Database.getInstance();

    public static void main(String[] args) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into empleado(NSS, Nombre, Numdept) values (?, ?, ?)");

            connection.setAutoCommit(false);

            ps.setInt(1, 1);
            ps.setString(2, "Pedro");
            ps.setInt(3, 1);
            ps.executeUpdate();

            ps.setInt(1, 2);
            ps.setString(2, "Annabel");
            ps.executeUpdate();

            ps.setInt(1, 3);
            ps.setString(2, "Paco");
            ps.executeUpdate();


            connection.commit();

            System.out.println("Todos los datos han sido introducidos");

        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }finally {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
