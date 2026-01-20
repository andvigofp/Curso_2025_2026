package Ejercicios.Ej312;

import java.sql.SQLException;
import java.util.Scanner;

public class MEj312 {
    public void mostarLibros(GestorLibros gestorLibros) throws SQLException {
        gestorLibros.libros_limite_stock();
    }

    public void obtenerLibros(Scanner teclado,GestorLibros gestorLibros) throws SQLException {
        System.out.println("Introduzca el código del libro");
        int id = teclado.nextInt();
        gestorLibros.getLibroId(id);
    }

    public void numeroLibrosPrecioSuperior(Scanner teclado, GestorLibros gestorLibros) throws SQLException {
        System.out.println("Cuánto es el precio por el que quieres buscar?");
        float precio = teclado.nextFloat();
        gestorLibros.contar_libros_precio(precio);
    }
}
