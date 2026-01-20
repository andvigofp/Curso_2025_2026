package Ejercicios.EJ503;


import java.sql.SQLException;
import java.util.Scanner;

/**
 * Usando Java se pide realizar las siguientes funcionalidades usando como referencia la siguiente base de datos sobre pokemons
 *
 * Las funciones a implementar será aquellas que permitan:
 *
 * Insertar un nuevo pokemon.
 * Modificar un pokemon.
 * Eliminar un pokemon.
 */

public class Ej503 {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            MEj503 metodos = new MEj503();

            metodos.Menu();
        } catch (SQLException e) {
            System.out.println("Error de conexión " + e.toString());
        }
        teclado.close();
    }
}
