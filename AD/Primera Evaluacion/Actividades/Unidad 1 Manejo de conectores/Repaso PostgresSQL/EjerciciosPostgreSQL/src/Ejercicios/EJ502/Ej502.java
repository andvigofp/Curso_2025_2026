package Ejercicios.EJ502;

import Ejercicios.EJ502.Database;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Usando Java como lenguaje de programación realiza las siguientes consultas en la siguiente base de datos PostgreSQL:
 *
 * Listar todos los equipos y sus directores
 * Obtener los pilotos y sus equipos actuales
 * Resultados de una carrera específica
 * Piloto más viejo
 * Número de victorias por equipo
 */

public class Ej502 {
    private static Connection connection = Database.getInstance();

    public static void main(String[] args) {
        try {
            Statement statement = connection.createStatement();

            MEj502 metodos = new MEj502();

            metodos.Menu(statement);

        } catch (SQLException e) {
            System.out.println("Error de conexión "+ e.toString());
        }
    }
}
