package org.example.Aplicacion;

import org.example.Entidades.*;
import org.example.Repositorio.GeneroRepositorio;
import org.example.Repositorio.PeliculaRepositorio;
import org.example.Repositorio.PersonaRepositorio;

import java.util.List;
import java.util.Scanner;

public class MEJ308 {
    private static Scanner teclado = new Scanner(System.in);
    private PersonaRepositorio personaRepositorio;
    private PeliculaRepositorio peliculaRepositorio;
    private GeneroRepositorio generoRepositorio;

    public MEJ308(PersonaRepositorio personaRepositorio, PeliculaRepositorio peliculaRepositorio, GeneroRepositorio generoRepositorio) {
        this.personaRepositorio = personaRepositorio;
        this.peliculaRepositorio = peliculaRepositorio;
        this.generoRepositorio = generoRepositorio;
    }

    public void mostrarMenu() {
        int opcion = -1;

        do {
            System.out.println("1. Agregar nuevos géneros\n" +
                    "2. Eliminar registros de un cierto género\n" +
                    "3. Buscando género por identificador\n" +
                    "4. Buscando género  por nombre\n" +
                    "5. Devolver todos los registros de la tabla género\n" +
                    "6. Guardar nueva persona\n" +
                    "7. Buscar persona por id\n" +
                    "8. Buscar persona según fecha de nacimiento\n" +
                    "9. Buscar persona por veces que ganó el premio mejor actriz.\n" +
                    "10. Buscar persona por veces que ganó el oscar.\n" +
                    "11. Agregar nueva pelicula \n" +
                    "12. Eliminar pelicula.\n" +
                    "13. Buscar película por título\n" +
                    "14. Buscando película por identificador\n" +
                    "15. Devolver todos las películas\n" +
                    "16. Recuperar todas las películas en las que participó cierta persona.\n"
                    + "17. Salir");

           opcion = teclado.nextInt();
           teclado.nextLine();

           switch (opcion) {
               case 1:
                   String nombreGenero = pedirString("Nombre del nuevo genero");

                   generoRepositorio.guardar(new Genero(nombreGenero));
                   break;
               case 2:
                   int idGenero = pedirInt("Id del genero a eliminar");

                   try {
                       Genero genero = generoRepositorio.buscarPorId(idGenero);
                       generoRepositorio.eliminar(genero);
                   }catch (Exception e) {
                       System.out.println("Error al eliminar un género");
                   }
                   break;
               case 3:
                   int idGeneros = pedirInt("Id del genero a buscar");

                   Genero genero = generoRepositorio.buscarPorId(idGeneros);
                   System.out.println(genero.toString());
                   break;
               case 4:
                   String cadena = pedirString("Nombre del genero a buscar");

                   List<Genero> listaGenero = generoRepositorio.buscarPorCadena(cadena);

                   for (Genero generos: listaGenero)
                       System.out.println(generos.toString());
                   break;
               case 5:
                   List<Genero> listaGeneros = (List<Genero>) generoRepositorio.buscarTodo();

                   for(Genero generos: listaGeneros)
                       System.out.println(generos.toString());
                   break;
               case 6:
                   String actizActor = "";

                   do{
                       actizActor = pedirString("Es actriz o actor?").trim();
                   }while(!actizActor.equals("actor") && !actizActor.equals("actriz") );

                   int anhoNacimiento = pedirInt("Año de nacimiento");


                   if (actizActor.equals("actor")) {

                       String nombre = pedirString("Nombre del actor");
                       String dni = pedirString("DNI del actor");
                       int numeroOscars = pedirInt("Número de oscars");

                       Persona actor = new Actor(nombre, dni, numeroOscars, anhoNacimiento);

                       personaRepositorio.guardar(actor);
                   }else if(actizActor.equals("actriz")) {
                       String nombre = pedirString("Nombre de la actriz");
                       String dni = pedirString("DNI de la actriz");
                       int numeroMejorActriz = pedirInt("Número de veces ganó el premio mejor actriz");

                       Persona actriz = new Actriz(nombre, dni, numeroMejorActriz, anhoNacimiento);
                       personaRepositorio.guardar(actriz);
                   }
                   break;
               case 7:
                   int id = pedirInt("Introduzca el ID ");

                   Persona persona = personaRepositorio.buscarPorId(id);

                   System.out.println(persona.toString());
                   break;
               case 8:
                   int anho = pedirInt("Introduzca el año a partir del cual quieres buscar");

                   List<Persona> listaPersonas = personaRepositorio.buscarPorAnho(anho);

                   for (Persona personas: listaPersonas)
                       System.out.println(personas.toString());
                   break;
               case 9:
                   int vecesGano = pedirInt("Introduzca el número de veces que alguien ganó el premio a la mejor actriz.");

                   List<Persona> listaPersona = (List<Persona>) personaRepositorio.buscarPorMejorActriz(vecesGano);

                   for(Persona personas: listaPersona)
                       System.out.println(personas.toString());
                   break;
               case 10:
                   int vecesQueGano = pedirInt("Introduzca el número de veces que alguien ganó el premio a la mejor actriz.");

                   List<Persona> listasPersonas = (List<Persona>) personaRepositorio.buscarPorMejorActor(vecesQueGano);

                   for(Persona personas: listasPersonas)
                       System.out.println(personas.toString());
                   break;
               case 11:
                   String titulo = pedirString("Introduzca el nombre de la película");
                   int anhos = pedirInt("Introduzca el año de la película");
                   teclado.nextLine();
                   int idGeneross = pedirInt("Introduzca el ID del género de la película");

                   try {
                       Genero generos = generoRepositorio.buscarPorId(idGeneross);
                       Pelicula pelicula = new Pelicula(titulo, anhos, generos);
                       peliculaRepositorio.guardar(pelicula);
                   } catch (Exception e) {
                       System.out.println("ID del género no es válido");
                   }
                   break;
               case 12:
                   int idPelicula = pedirInt("Introduzca el ID de la película a eliminar");
                   try {
                       Pelicula pelicula = peliculaRepositorio.buscarPorId(idPelicula);
                       peliculaRepositorio.eliminar(pelicula);
                   }catch (Exception e) {
                       System.out.println("No exista ese ID para la película");
                   }
                   break;
               case 13:
                   String titulos = pedirString("Introduzca el titulo de la película");

                   try {
                       List<Pelicula> listaPeliculas = peliculaRepositorio.buscarPorCadena(titulos);

                       for(Pelicula pelicula: listaPeliculas)
                           System.out.println(pelicula.toString());
                   }catch (Exception e) {
                       System.out.println("No existe una película con ese título");
                   }
                   break;
               case 14:
                   int ids = pedirInt("Introduzca el id de la película");

                   try {
                       Pelicula pelicula = (Pelicula) peliculaRepositorio.buscarPorId(ids);
                       System.out.println(pelicula.toString());
                   }catch (Exception e) {
                       System.out.println("No existe una película con ese ID");
                   }
                   break;
               case 15:
                   List<Pelicula> listaPeliculas = (List<Pelicula>) peliculaRepositorio.buscarTodo();

                   for (Pelicula pelicula: listaPeliculas)
                       System.out.println(pelicula.toString());
                   break;
               case 16:
                   break;
               case 17:
                   System.exit(0);
                   break;
               default:
                   System.out.println("Error: Solo puedes elegir entre las opciones (1-17)");
           }
        }while (opcion != 17);
    }

    public static int pedirInt(String string) {
        System.out.println(string);
        return teclado.nextInt();
    }

    public static Double pedirDouble(String string) {
        System.out.println(string);
        return teclado.nextDouble();
    }

    public static String pedirString(String string) {
        System.out.println(string);
        return teclado.nextLine();
    }
}
