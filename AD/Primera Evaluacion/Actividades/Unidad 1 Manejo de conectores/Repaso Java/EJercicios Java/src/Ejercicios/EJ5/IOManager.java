package Ejercicios.EJ5;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class IOManager {
    public static int leerEntero(String cadena) {
        Scanner teclado = new Scanner(System.in);
        int entero = 0;

        try {
            do {
                System.out.println(cadena);

                while (!teclado.hasNextInt()) {
                    System.out.println("Ha oucrrido una excepción. Solo perimte enteros");
                    teclado.next();
                    System.out.println("Introduce un número entero positivo: ");
                }
                entero = teclado.nextInt();

                if (entero <= 0) {
                    System.out.println("El número debe ser positivo");
                }
            }while (entero <= 0);
        }catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Se produjo una execepción " + e.getMessage());
        }

        return entero;
    }

    public static boolean isInRange(int min, int max, int valor) {
        return ((valor >= min) && (valor <= max));
    }

    public static String leerCadena(String cadena) {
        Scanner teclado = new Scanner(System.in);
        String entrada = "";

        try {
         do {
             System.out.println(cadena);
             entrada = teclado.nextLine();


         }while (entrada.isEmpty());

        }catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Se produjo una excepción " + e.getMessage());
        }
        return entrada;
    }
}
