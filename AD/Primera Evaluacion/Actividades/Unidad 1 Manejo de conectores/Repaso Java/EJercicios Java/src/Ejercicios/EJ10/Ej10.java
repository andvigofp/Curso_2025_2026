package Ejercicios.EJ10;

import java.util.Scanner;

/**
 * Desarrolla un programa en Java que permita cifrar y descifrar un mensaje usando el cifrado César.
 *
 * Cifrado Cesar
 * El cifrado César consiste en coger cada letra y desplazarla n posiciones hacia arriba. Es decir, si n=4 y tenemos el mensaje hola el resultado sería lspe: h → l o → s l → p a → e
 *
 * El programa solicitará al usuario que introduzca dos números:
 *
 * El primer número indicará el desplazamiento:
 * Se tendrá en cuenta el desplazamiento siguiendo la tabla ASCII que cuenta con un alfabeto de 26 letras
 * El segundo número indicará:
 * 1: cifrar.
 * 2: descifrar.
 * Se deberán tener en cuenta el uso de las mayúsculas y de las minúsculas
 *
 * tabla ascii
 */
public class Ej10 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // Solicitar el desplazamiento
        System.out.print("Introduce el desplazamiento (número entero): ");
        int desplazamiento = teclado.nextInt();
        teclado.nextLine();  // Limpiar el buffer del scanner

        // Solicitar la acción (cifrar o descifrar)
        System.out.print("Introduce 1 para cifrar o 2 para descifrar: ");
        int accion = teclado.nextInt();
        teclado.nextLine();  // Limpiar el buffer del scanner

        // Solicitar el mensaje
        System.out.print("Introduce el mensaje: ");
        String mensaje = teclado.nextLine();

        // Realizar la acción correspondiente
        if (accion == 1) {
            String mensajeCifrado = CifradoCesar.cifrar(mensaje, desplazamiento);
            System.out.println("Mensaje cifrado: " + mensajeCifrado);
        } else if (accion == 2) {
            String mensajeDescifrado = CifradoCesar.descifrar(mensaje, desplazamiento);
            System.out.println("Mensaje descifrado: " + mensajeDescifrado);
        } else {
            System.out.println("Acción no válida. Introduce 1 para cifrar o 2 para descifrar.");
        }

        teclado.close();
    }
}
