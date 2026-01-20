package Ejercicios.EJ6;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Desarrolla un programa en Java permita crear una lista de contactos que puedes agregar, eliminar y mostrar. Cada contacto estará representado por una clase Contacto.
 *
 * El programa implementará un menú que permita:
 *
 * Agregar contacto
 * Mostrar contacto
 * Eliminar contacto
 */

public class Ej6 {
    static Scanner teclado = new Scanner(System.in);
    static ArrayList<Contacto> listaContactos = new ArrayList<Contacto>();

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("Menú Agenda:");
            System.out.println("1. Agregar Contacto:");
            System.out.println("2. Mostrar Contacto:");
            System.out.println("3. Eliminar Contacto:");
            System.out.println("4. Salir");
            System.out.println("Elija una de las opciones: ");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    agregarCOntacto();
                    break;
                case 2:
                    mostarContacto();
                    break;
                case 3:
                    eliminarConatcto();
                    break;
                case 4:
                    System.out.println("Fin de la aplicación");
                    break;
                default:
                    System.out.println("Error, no eligiste la opción correcta");

            }


        }while (opcion!=4);
    }

    //Agregar un contacto
    public static void agregarCOntacto() {
        System.out.println("Introduce el nombre del contacto a crear: ");
        String nombre = teclado.nextLine();

        System.out.println("Introduce el teléfono del contacto a crear");
        String telefono = teclado.nextLine();

        listaContactos.add(new Contacto(nombre, telefono));
        System.out.println("Se agregado el contado a agenda correctamente");

    }

    //Mostrar un contacto
    public static void mostarContacto() {
        System.out.println("Contacto Agenda:");

        for (Contacto contacto: listaContactos) {
            System.out.println(contacto);
        }
    }

    //Eliminar un contacto
    public static void eliminarConatcto() {
        System.out.println("Ingrese el nombre del contacto a eliminar: ");
        String nombreEliminar = teclado.nextLine();

        boolean encontrado = false;

        for (Contacto contacto: listaContactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombreEliminar)) {
                listaContactos.remove(contacto);
                System.out.println("Contacto eliminado con éxicto");
                encontrado = true;
                break;
            }
        }
        if(!encontrado) {
            System.out.println("No se a encontrado níngun conmtacto con ese nombre");

        }

    }
}
