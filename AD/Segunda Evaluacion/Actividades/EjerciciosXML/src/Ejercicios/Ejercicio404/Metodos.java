package Ejercicios.Ejercicio404;

import org.basex.examples.api.BaseXClient;

import java.io.IOException;
import java.util.Scanner;

public class Metodos {
    private static Scanner teclado = new Scanner(System.in);
    private static BaseXClient session = ConexionBaseX.conexionBaseX();


    /**final String menu = "1- Título y editorial de todos los libros\n" +
            "2- El título de todos los libros de menos de 400 páginas\n" +
            "3- ";**/


    public void ejecutarConsulta(String consulta) {
        try (BaseXClient.Query query = session.query(consulta)){

            while (query.more()) {
                System.out.println(query.next());
            }
        }catch (IOException e) {
            System.out.println("Error en la consulta");
        }
    }
    public static String pedirString(String mensaje){
        System.out.println(mensaje);
        return teclado.nextLine();
    }

    public static int pedirInt(String mensaje){
        while(true){
            try{
                System.out.println(mensaje);
                return teclado.nextInt();
            }catch (Exception e){}
        }
    }
}
