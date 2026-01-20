package Ejercicios.EJ9;

import java.util.ArrayList;

/**
 * Desarrolla un programa en Java que permita eliminar los elementos duplicados de un array y devuelva la longitud final sin los duplicados
 *
 * ejemplo funcionamiento
 * array = [20, 20, 30, 40, 50, 50, 50]
 *
 * Devolverá 4
 */
public class Ej9 {
    public static void main(String[] args) {
        int[] array = new int[] {20, 20, 30, 40, 50, 50, 50};
        ArrayList<Integer> nuevo = new ArrayList<>();

        for (int a: array) {
            if (!nuevo.contains(a)) {
                nuevo.add(a);
            }
        }
        System.out.println("El núevo tamaño es de: " + nuevo.size());
    }
}
