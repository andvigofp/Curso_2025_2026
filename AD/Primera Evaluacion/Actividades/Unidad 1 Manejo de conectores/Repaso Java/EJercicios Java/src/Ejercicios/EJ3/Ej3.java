package Ejercicios.EJ3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Realiza un programa en Java que gestione la compra de los productos de un supermercado.
 *
 * El programa solicitará el nombre del producto y su precio en bucle.
 *
 * Después de de introducir estos datos, preguntará si deseamos continuar introduciendo más productos. Las posibles respuestas serán SI para seguir en el bucle y NO para salir.
 *
 * Al terminar, mostrará el importe total de la compra y cuántos productos hemos comprado.
 */

public class Ej3 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        String opcion = "SI";
        ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();

        String nombre;
        double precio, suma =0.0;
        while (!opcion.toUpperCase().equals("NO")) {
            System.out.println("Introduzca el nombre del articulo");
            nombre = teclado.nextLine();

            System.out.println("Introduzca el precio del articulo");
            precio = teclado.nextDouble();

            listaArticulos.add(new Articulo(nombre, precio));

            do {
                System.out.println("Desea seguir introduciendo valores? Posibles valores si/no");
                opcion = teclado.next();
                teclado.nextLine();
            }while (!opcion.toUpperCase().equals("SI") && !opcion.toUpperCase().equals("NO"));
        }

        System.out.println("Total de productos: " + listaArticulos.size());

        for (Articulo articulo: listaArticulos) {
            System.out.println(articulo.toString());
            suma += articulo.getPrecio();
        }
        System.out.println("\nTotal: " + suma);
    }

}
