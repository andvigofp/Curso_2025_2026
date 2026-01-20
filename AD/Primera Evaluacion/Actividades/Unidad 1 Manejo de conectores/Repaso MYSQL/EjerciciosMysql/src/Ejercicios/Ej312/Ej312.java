package Ejercicios.Ej312;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Se pide, desarrollar una aplicación Java que permita realizar consultas y recuperar los datos utilizando los siguientes procedimientos almacenados:
 *
 * pa_libros_limite_stock(): devuelve la lista de libros en stock
 * get_libro_by_id(IN id int): devuelve la información de un libro por le código
 * contar_libros_precio(IN precio decimal(5,2), OUT total int): permite obtener el número de libros que cuestan más que una determinada cantidad
 */
public class Ej312 {
    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);

        GestorLibros gestorLibros = new GestorLibros();

        MEj312 metodos = new MEj312();


        final String menu = "1. OBTENER LIBROS EN STCOK ."
                + "\n2. OBTENER LIBRO POR ID."
                + "\n3. NÚMERO DE LIBROS CON UN PRECIO SUPERIOR."
                + "\n4. SALIR.";

        int opcion = -1;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    metodos.mostarLibros(gestorLibros);
                    break;
                case 2:
                    metodos.obtenerLibros(teclado, gestorLibros);
                    break;
                case 3:
                    metodos.numeroLibrosPrecioSuperior(teclado, gestorLibros);
                    break;
                case 4:
                    System.out.println("Fin del programa");
                    teclado.close();
                    return;
                default:
                    System.out.println("Opción no válida, por favor elija una opción del menú entre 1-4");
            }
        } while (opcion != 4);
    }
}
