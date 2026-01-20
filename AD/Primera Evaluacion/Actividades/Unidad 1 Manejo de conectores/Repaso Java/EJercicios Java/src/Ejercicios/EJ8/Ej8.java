package Ejercicios.EJ8;

/**
 *Desarrolla un programa en Java que permita gestionar los libros de un sistema bibliotecario.
 *
 * Supongamos que necesitas desarrollar un sistema de biblioteca que incluye libros y revistas. Cada libro o revista tiene un título y un número de identificación único. Además, se quiere llevar un registro de los préstamos y devoluciones de estos materiales.
 *
 * Para ello utilizaremos herencia y polimorfismo:
 *
 * Crearemos una clase Material que contendrá los atributos y métodos comunes.
 *
 * De la clase Material extenderán las clases Libro y Revista.
 *
 * Los Libros además del título y el número de identificación queremos almacenar el nombre del autor.
 *
 * Para las Revistas queremos almacenar a mayores el número de la revista.
 *
 * Se creará una clase Biblioteca que permita:
 *
 * almacenar el catálogo de libros que tiene la biblioteca disponibles y los libros que están siendo prestados.
 * prestar libros
 * ver los libros prestados
 * devolver los libros prestados
 * Se creará una clase SistemaBiblioteca que contendrá el método main en el que:
 *
 * Creará un par de libros y revistas
 * Los añadirá a la biblioteca
 * Simulará el préstamo de algún libro/revista
 * Simulará la devolución de esos libros/revistas
 */

public class Ej8 {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Libro libro1 = new Libro(1, "La Sombra del Viento", "Carlos Ruiz Zafón");
        Libro libro2 = new Libro(2, "Cien Años de Soledad", "Gabriel García Márquez");
        Revista revista1 = new Revista(3, "National Geographic", 100);

        biblioteca.agregarMaterial(libro1);
        biblioteca.agregarMaterial(libro2);
        biblioteca.agregarMaterial(revista1);

        biblioteca.prestarMaterial(1);
        biblioteca.prestarMaterial(2);
        biblioteca.prestarMaterial(3);
        biblioteca.listarPrestados();

        biblioteca.devolverMaterial(1);
        biblioteca.listarPrestados();
    }
}
