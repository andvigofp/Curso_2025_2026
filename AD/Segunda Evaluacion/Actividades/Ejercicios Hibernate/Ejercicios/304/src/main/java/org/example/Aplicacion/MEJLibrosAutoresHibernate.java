package org.example.Aplicacion;

import org.example.Entidades.Autores;
import org.example.Entidades.Libros;
import org.example.Entidades.Telefono;
import org.example.Repositorio.AutorRepositorio;
import org.example.Repositorio.LibroRepositorio;
import org.example.Repositorio.TelefonoRepositorio;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class MEJLibrosAutoresHibernate {
    private static final Scanner teclado = new Scanner(System.in);
    private static Session session;
    private static AutorRepositorio actorRepositorio;
    private static LibroRepositorio libroRepositorio;
    private static TelefonoRepositorio telefonoRepositorio;


    public MEJLibrosAutoresHibernate(AutorRepositorio actorRepositorio, LibroRepositorio libroRepositorio, TelefonoRepositorio telefonoRepositorio) {
        this.actorRepositorio = actorRepositorio;
        this.libroRepositorio = libroRepositorio;
        this.telefonoRepositorio = telefonoRepositorio;
    }


    public  void mostarMenu() {


        int opcion = -1;

        do {
            System.out.println("1. Insertar nueva fila\n" +
                    "2. Borrar fila\n" +
                    "3. Consultar\n" +
                    "4. Salir");

            opcion = teclado.nextInt();
            teclado.nextLine();
            
            switch (opcion) {
                case 1:
                    mostrarMenuInsertar();
                    break;
                case 2:
                    mostrarMenuBorrar();
                    break;
                case 3:
                    mostrarMenuConsultas();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error solo se puede elegir entre las opciones (1-4)");
            }
        }while (opcion !=4);
    }



    private  void mostrarMenuInsertar() {
        int opcion = -1;

        do {
            System.out.println("\n1. Insertar nuevo autor" +
                    "\n2. Insertar nuevo libro" +
                    "\n3. Enlazar autor-libro" +
                    "\n4. Insertar teléfono para un autor" +
                    "\n5. Atrás");
            
            opcion = teclado.nextInt();
            teclado.nextLine();
            
            switch (opcion) {
                case 1:
                    insertarAutor(false);
                    break;
                case 2:
                    insertarLibro(false);
                    break;
                case 3:
                    enlazarAutorLibro();
                    break;
                case 4:
                    insertarTelefonoAutor();
                    break;
                case 5:
                    mostarMenu();
                    break;
                default:
                    System.out.println("Error solo se puede elegir entre las opciones (1-5)");
            }

        }while (opcion < 1 || opcion > 5);
    }




    public static String pedirString(String mensaje) {
        System.out.println(mensaje);
        return teclado.nextLine(); // Lee el título del libro
    }

    public static String pedirStringS(String mensaje) {
        System.out.println(mensaje);
        if (teclado.hasNextLine()) {
            teclado.nextLine(); // Limpiar el buffer de entrada
        }
        return teclado.nextLine(); // Leer la línea completa, incluidos los espacios
    }

    public int pedirInt(String mensaje) {
        System.out.println(mensaje);
        return teclado.nextInt();
    }

    // Método para pedir un precio y manejar posibles errores
    public static double pedirPrecio(String mensaje) {
        System.out.println(mensaje);
        while (!teclado.hasNextDouble()) {
            System.out.println("Por favor, introduzca un precio válido.");
            teclado.next();
        }
        double precio = teclado.nextDouble();
        teclado.nextLine(); // Limpiar el salto de línea residual
        return precio;
    }



    private void insertarAutor(boolean vieneDeAutor) {
        String dni = pedirString("Introduzca el DNI del autor: ");
        String nombre = pedirString("Introduzca el nombre del autor: ");
        String localidad = pedirString("Introduzca la localidad del autor: ");

        Autores autores = new Autores(dni, nombre, localidad);
        actorRepositorio.insertarUno(autores);
    }

    private static void insertarLibro(boolean b) {
        String titulo = pedirString("Introduzca el título del libro: ");
        double precio = pedirPrecio("Introduzca el precio del libro: ");

        Libros libros = new Libros(titulo, precio);
        libroRepositorio.insertarUno(libros);
    }

    private static void enlazarAutorLibro() {
        String dni = pedirString("Introduzca el DNI del autro");
        String titulo = pedirString("Título del libro");

        Autores autor = actorRepositorio.encontrarUnoPorString(dni);
        Libros libro = libroRepositorio.encontrarUnoPorString(titulo);

        autor.addLibro(libro);
        actorRepositorio.actualizar(autor);
        libroRepositorio.actualizar(libro);
    }


    private static void insertarTelefonoAutor() {
        String DNI = pedirString("Introduzca el DNI del autor");
        String numTel = pedirString("Introduzca el número de telefono");

        Autores autor = actorRepositorio.encontrarUnoPorString(DNI);
        Telefono telefono = new Telefono(autor, numTel);


        autor.setTelefono(telefono);

        actorRepositorio.actualizar(autor);
        telefonoRepositorio.insertarUno(telefono);
    }


    private  void mostrarMenuBorrar() {
        int opcion = -1;

        do {
            System.out.println("\n1. Borrar autor" +
                    "\n2. Borar libro" +
                    "\n3. Atrás");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    String dni = pedirString("Introduzca el DNI del autor: ");

                    Autores autores = actorRepositorio.encontrarUnoPorString(dni);

                    if (autores !=null) {
                        actorRepositorio.borrar(autores);
                        System.out.println("Autor borrado exitosamente");
                    }else {
                        System.out.println("No se encontró un autor con el DNI: " + dni);
                    }
                    break;
                case 2:
                    String titulo = pedirString("Introduzca el título del libro");

                    Libros libros = libroRepositorio.encontrarUnoPorString(titulo);

                    if (titulo !=null) {
                        libroRepositorio.borrar(libros);
                        System.out.println("Libro borrado exitosamente");
                    }else {
                        System.out.println("No se encontró un libro con el título: " + titulo);
                    }
                    break;
                case 3:
                    mostarMenu();
                    break;
                default:
                    System.out.println("Error solo se puede elegir entre las opciones (1-3)");
            }
        }while (opcion !=3);
    }

    private  void mostrarMenuConsultas() {
        int opcion = -1;

        do {

            System.out.println("\n1. Visualizar datos de un libro a partir del título" +
                "\n2. Visualizar libros de un determinado autor" +
                "\n3. Visualizar todos los libros" +
                "\n4. Visualizar todos los autores y sus libros " +
                "\n5. Atrás");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    String titulo = pedirString("Introduzca el título de un libro: ");

                    Libros libros = libroRepositorio.encontrarUnoPorString(titulo);

                    if (titulo !=null) {
                        System.out.println(libros.toString());
                    }else {
                        System.out.println("No se encontró un libro con el título: " + titulo);
                    }
                    break;
                case 2:
                    String DNI = pedirString("Introduzca el DNI del autor: ");

                    Autores autores = actorRepositorio.encontrarUnoPorString(DNI);

                    if (autores !=null) {
                        for (Libros libro : autores.getListaLibros())
                            System.out.println(libro.toString());
                    }else {
                        System.out.println("No se encontró un autor con el DNI: " + DNI);
                    }
                    break;
                case 3:
                    List<Libros> listaLibros = libroRepositorio.enocntrarTodos();

                    for (Libros libro : listaLibros)
                        System.out.println(libro.toString());

                    break;
                case 4:
                    List<Autores> listaAutores = actorRepositorio.enocntrarTodos();

                    for (Autores autor : listaAutores) {
                        System.out.println(autor.toString() + "\nLibros");
                        for (Libros libro : autor.getListaLibros()) {
                            System.out.println(libro.toString());
                        }
                    }
                    break;
                case 5:
                    mostarMenu();
                    break;
                default:
                    System.out.println("Error solo se puede elegir entre las opciones (1-5)");
            }
        }while (opcion !=5);

    }
}
