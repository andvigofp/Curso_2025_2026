package Ejercicios.EJ2;

import java.util.Scanner;

/**
 * Realiza un programa en Java que pida por teclado el nombre y la edad de una persona mayor de edad (se debe validar el valor introducido) y responda indicando:
 *
 * Si no está jubilado (edad de jubilación = 65 años), cuántos años le quedan para jubilarse.
 * Si ya está jubilada indicará cuántos años lleva jubilado.
 */
public class Ej2 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduzca el nombre de la persona: ");
        String nombre = teclado.nextLine();

        int edad = -1;

        while (edad < 18) {
            System.out.println("Introduzca la edad  la edad");

            try {
                edad = teclado.nextInt();

                if (edad < 18) {
                    System.out.println(nombre +  " Tiene que ser mayor de edad");
                } else if (edad > 65) {
                    System.out.println(nombre + " Lleva jubilado desde hace " + (edad - 65)  + " anhos ");
                } else if (edad == 65) {
                    System.out.println(nombre + " Se va jubilar " );
                }else {
                    System.out.println(nombre + " Le falta para jubilarse " + (edad - 65) + " anhos");
                }

            }catch (Exception e) {
                System.out.println("Error formato no correcto " + e.getMessage());
            }
        }
    }
}
