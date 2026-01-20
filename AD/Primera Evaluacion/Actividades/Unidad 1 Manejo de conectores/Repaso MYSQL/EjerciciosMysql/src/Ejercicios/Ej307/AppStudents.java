package Ejercicios.Ej307;

import Ejercicios.Ej307.ManageStudents;
import Ejercicios.Ej307.Student;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Queremos conectarnos a la base de datos de un instituto llamada school, que está compuesta de una tabla student, que contiene la información de los diferentes estudiantes. La tabla puede crearse con las siguientes sentencias:
 *
 * DROP DATABASE IF EXISTS school;
 * CREATE DATABASE school;
 * USE school;
 *
 * CREATE TABLE student (
 *     id CHAR(9) PRIMARY KEY,
 *     name VARCHAR(50) NOT NULL,
 *     surname VARCHAR(100) NOT NULL,
 *     age INT NOT NULL
 * );
 *
 * INSERT INTO student VALUES ('11111111A','Draco','Malfoy',25);
 * INSERT INTO student VALUES ('22222222B','Hermione','Granger',23);
 * INSERT INTO student VALUES ('33333333C','Harry','Potter',20);
 * INSERT INTO student VALUES ('44444444D','Ron','Weasley',22);
 *
 *
 * Debes diseñar una aplicación en Java llamada app-students que se conecte a la base de datos y permita realizar varias operaciones sobre ella. Para ello, debes definir una clase Student que disponga de los siguientes atributos:
 *
 * id: String.: hace referencia al DNI.
 * name: String.
 * surname: String.
 * age: int.
 * También debes añadirle los métodos que consideres necesarios.
 *
 * Por otra parte, deberás crear una clase llamada ManageStudents, que permitirá realizar diferentes operaciones sobre la base de datos del instituto. La clase tendrá, como mínimo, los siguientes atributos y métodos:
 *
 * Connection connection.
 * void openConnection().
 * void closeConnection().
 * boolean addStudent(Student student): añade un nuevo estudiante a la base de datos.
 * Student getStudent(String id): obtiene la información de un estudiante determinado.
 * boolean deleteStudent(String id): borra la información de un estudiante determinado.
 * boolean modifyStudent(Student student): modifica los datos de un estudiante determinado, si ya existe en la base de datos.
 * ArrayList<Student> getStudentsList(): devuelve una lista con todos los estudiantes almacenados en la BD.
 * Después, debes definir una clase AppStudents en la que le muestres al usuario un menú con las siguientes opciones:
 *
 * MATRICULAR UN ESTUDIANTE.
 * DAR DE BAJA A UN ESTUDIANTE.
 * ACTUALIZAR DATOS DE UN ESTUDIANTE.
 * VER DATOS DE UN ESTUDIANTE.
 * VER DATOS DE TODOS LOS ESTUDIANTES.
 * SALIR.
 * En el caso de que no sea posible realizar alguna de las operaciones, debemos mostrarle un mensaje descriptivo al usuario y continuar con la ejecución de la aplicación.
 */

public class AppStudents {
    private static Scanner teclado = new Scanner(System.in);
    private static ManageStudents ms = new ManageStudents();

    public static void main(String[] args) {

        final String menu = "1. MATRICULAR UN ESTUDIANTE."
                + "\n2. DAR DE BAJA A UN ESTUDIANTE."
                + "\n3. ACTUALIZAR DATOS DE UN ESTUDIANTE."
                + "\n4. VER DATOS DE UN ESTUDIANTE."
                + "\n5. VER DATOS DE TODOS LOS ESTUDIANTES."
                + "\n6. SALIR.";

        int opcion = -1;

        while (opcion !=6) {
            System.out.println(menu);
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    matricularStudent();
                    break;
                case 2:
                    borrarStudent();
                    break;
                case 3:
                   actualizarStudent();
                   break;
                case 4:
                    verDatosStudent();
                    break;
                case 5:
                    verDatosTodosStudent();
                    break;
                case 6:
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Error. no elegiste la opción correca");
                    return;
            }
        }
    }

    // MATRICULAR UN ESTUDIANTE.
    private static void matricularStudent() {
        System.out.println("Introduzca el DNI del alumno:");
        String dni = teclado.next();
        System.out.println("Introduzca el nombre del alumno:");
        String nombre = teclado.next();
        System.out.println("Introduzca el apellido del alumno:");
        String apellido = teclado.next();
        System.out.println("Introduzca la edad del alumno:");
        int edad = teclado.nextInt();
        teclado.nextLine();
        Student st = new Student(dni, nombre, apellido, edad);
        ms.addStudent(st);
        System.out.println("Alumnó añadido correctamente");
    }

    // DAR DE BAJA A UN ESTUDIANTE
    private static void borrarStudent() {
        System.out.println("Introduzca el id del alumno a borrar:");
        String id = teclado.next();
        if(ms.deleteStudent(id)) {
            System.out.println("Se ha dado de baja el alumno de forma correcta");
        }else {
            System.out.println("No se ha dado de baja ningún alumno.");
        }
    }

    // ACTUALIZAR DATOS DE UN ESTUDIANTE
    private static void actualizarStudent() {
        System.out.println("Introduzca el DNI del alumno:");
        String dni = teclado.next();
        System.out.println("Introduzca el nombre del alumno:");
        String nombre = teclado.next();
        System.out.println("Introduzca el apellido del alumno:");
        String apellido = teclado.next();
        System.out.println("Introduzca la edad del alumno:");
        int edad = teclado.nextInt();
        teclado.nextLine();
        Student st = new Student(dni, nombre, apellido, edad);

        if(ms.modifyStudent(st)) {
            System.out.println("Se han actualizado los datos de forma correcta");
        }else {
            System.out.println("No se han podido modificar los datos.");
        }
    }

    // VER DATOS DE UN ESTUDIANTE
    private static void verDatosStudent() {
        System.out.println("Introduzca el id del alumno que quiere consultar:");
        String id = teclado.next();
        Student st = ms.getStudent(id);

        System.out.println("Mostrando datos del alumno: " + id + "\nNombre: " + st.getName() +
                "\nApellido: " + st.getSurname() + "\nEdad: " + st.getAge());
    }


    // VER DATOS DE TODOS LOS ESTUDIANTES
    private static void verDatosTodosStudent() {
        ArrayList<Student> listaEsudiantes = ms.getStudentsList();

        System.out.println("Mostrando información de los estudiantes: ");

        for(Student st : listaEsudiantes) {
            System.out.println("\nMostrando datos del alumno: " + st.getId() + "\nNombre: " + st.getName() +
                    "\nApellido: " + st.getSurname() + "\nEdad: " + st.getAge());
        }
    }
}
