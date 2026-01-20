package Ejercicios.Ej309;

import Ejercicios.Ej310.Database;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A partir de la base de datos Librería la cual tiene el siguiente diagrama:
 *
 *
 *
 * Se pide crear un programa Java que tenga un paquete llamado dataBase que tenga dos clases:
 *
 * Clase Operaciones.java: que se encargará de la conexión a la base de datos y contendrá las siguientes funciones:
 *
 * existsClient: recibirá un id de cliente (idClient) y devolverá true o false indicando si el cliente existe en la BD o no.
 * existsBook: recibe un id de libro (idBook) y devuelve true o false indicando si el libro existe en la BD o no.
 * isBorrowed: recibe el código (code) de un libro y devuelve true o false si el libro está prestado o no.
 * addLoan: recibe el código (code) de un libro y el id de cliente (idClient) y usa los métodos anteriores para hacer las comprobaciones necesarias y añadir un préstamos a la BD.
 * addReturn: recibe el código de un libro y modifica el campo prestado (borrowed) en la tabla de préstamos (loan).
 * borrowedBooksList: devuelve un ArrayList de libros con la lista de libros prestados hasta el momento.
 * availableBooksList: devuelve un ArrayList de libros con la lista de libros que están disponibles para ser prestados.
 * Clase Main.java: permitirá lanzar la aplicación, mostrará un menú que permita:
 *
 * Prestar un libro.
 * Devolver un libro.
 * Listar los libros prestados.
 * Listar los libros que se pueden prestar.
 * NOTA: tendrán que notificarse todos los posibles errores.
 */

public class Ej309 {
   static Operaciones operaciones = new Operaciones();
   static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        final String menu = "1. PRESTAR UN LIBRO."
                + "\n2. DEVOLVER UN LIBRO."
                + "\n3. LISTAR LOS LIBROS PRESTADOS."
                + "\n4. LISTAR LIBROS QUE PUEDEN SER PESTADOS."
                + "\n5. SALIR.";

        int opcion = -1;

        while (opcion !=5) {
            System.out.println(menu);
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    prestarLibro();
                    break;
                case 2:
                    devolverLibro();
                    break;
                case 3:
                    listarLibrosPrestados();
                    break;
                case 4:
                    listarLibrosPuedenSerPrestados();
                    break;
                case 5:
                    Database.cerrarConexion();
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Error. Solo puedes elegir entre la opciones 1-5");
                    return;
            }
        }
    }

    private static void prestarLibro() {
        System.out.println("Introduce tu número de usuario");
        String idUsuario = teclado.next();
        System.out.println("Introduzca el código del libro que quieres coger:");
        String codigo = teclado.next();

        operaciones.addLoan(codigo, idUsuario);

        System.out.println("Libro prestado correctamente");
    }

    private static void devolverLibro() {
        System.out.println("Introduzca el código del libro que quiere devolver:");
        String codigo = teclado.next();

        operaciones.addReturn(codigo);

        System.out.println("El libro ha sido devueltoCorrectamente");
    }

    private static void listarLibrosPrestados() {
        ArrayList<Libro> listaLibros = operaciones.borrowedBooksList();

        for (Libro l : listaLibros)
            System.out.println(l.toString());
    }

    private static void listarLibrosPuedenSerPrestados() {
        ArrayList<Libro> listaLibros = operaciones.availableBooksList();

        for (Libro l : listaLibros)
            System.out.println(l.toString());
    }
}
