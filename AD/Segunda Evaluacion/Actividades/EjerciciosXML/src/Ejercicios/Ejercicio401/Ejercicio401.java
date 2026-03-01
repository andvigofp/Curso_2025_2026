package Ejercicios.Ejercicio401;

import org.basex.examples.api.BaseXClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio401 {
    private static Scanner teclado = new Scanner(System.in);


    public static void main(String[] args) {
        ArrayList<XML401> info = new ArrayList<>();

        String opcion = "";

        do {
            String nombre = introducirString("Introduce el nombre del alumno");
            String apellidos = introducirString("Introduce el apellido del alumno");
            int edad = introducirInt("Introduce la edad del alumno");
            teclado.nextLine();
            String correo = introducirString("Introduce el email de alumno");

            info.add(new XML401(nombre, apellidos, edad, correo));

            opcion = introducirString("Desea seguir introduciendo datos (si/no)");
        } while (!opcion.equalsIgnoreCase("no"));

        //Crear la sessión
        try (BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {

            //Creamos los datos de entrada
            InputStream bais = new ByteArrayInputStream(info.get(0).toString().getBytes());

            // Crear una base de datos llamada "ejercicio401" e insertar el primer documento
            session.create("ejercicio401", bais);

            for (int i = 0; i < info.size(); i++) {
                bais = new ByteArrayInputStream(info.get(i).toString().getBytes());
                session.add("ejercicio401/alumno" + i + ".xml", bais);
            }

            System.out.println(session.info());

            String menu = "1. Número total de documentos en el sistema\n" +
                    "2. Media de edad de los alumnos.\n" +
                    "3. Alumno mayor y menor.\n" +
                    "4. Mostrar el nombre de los alumnos ordenado por edad de mayor a menor.\n" +
                    "5. Mostrar el nombre de un alumno de forma aleatoria.\n" +
                    "6. Salir";

            int opcionMenu;

            do {
                opcionMenu = introducirInt(menu);

                switch (opcionMenu) {
                    case 1:
                        gestionConsulta(session, "count(for $i in db:get('ejercicio401') return $i)");

                        //gestionConsulta(session, "count(for $i in /alumno return $i)");
                        break;
                    case 2:
                        gestionConsulta(session, "avg(for $i in db:get('ejercicio401')/alumno/edad return $i)");

                        //gestionConsulta(session, "avg(for $i in /alumno/edad return $i)");
                        break;
                    case 3:
                        gestionConsulta(session, "let $minimo :=(/alumno/edad)\n" +
                                "let $maximo :=(/alumno/edad)\n" +
                                "return <alumno><min>{$minimo}</min>\n" +
                                "<max>{$maximo}</max></alumno>");
                        break;
                    case 4:
                        gestionConsulta(session, "for $alumno in /alumno\n" +
                                "order by $alumno/edad descending\n" +
                                "return $alumno/nombre/text()");
                        break;
                    case 5:
                        int totalDocumentos = obtenerResultadoConsulta(session, "count(for $i in /alumno return $i)");
                        teclado.nextLine();
                        int numRandom = (int) (Math.random() * totalDocumentos) + 1;
                        gestionConsulta(session, "let $documents := /alumno\n" +
                                "return $documents[" + numRandom + "]");
                        break;
                    case 6:
                        System.out.println("Fin del programa");
                        break;
                    default:
                        System.out.println("Error. Solo puedes elegir entre las opciones (1-6)");
                }
            } while (opcionMenu != 6);
        } catch (IOException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }


    private static void gestionConsulta(BaseXClient session, String input) {
        try (BaseXClient.Query query = session.query(input)) {

            while (query.more()) {
                System.out.println(query.next());
            }

            System.out.println(query.info());
        } catch (IOException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    private static int obtenerResultadoConsulta(BaseXClient session, String info) {
        try (BaseXClient.Query query = session.query(info)) {

            return Integer.parseInt(query.next());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }




    private static String introducirString(String mensaje) {
        while(true){
            try{
                System.out.print(mensaje + ": ");
                return teclado.nextLine();
            }catch (Exception e){
                System.out.println("Error al introducir los datos. Vuelva a introducirlos.");
            }
        }
    }

    private static int introducirInt(String mensaje) {
        while(true){
            try{
                System.out.print(mensaje + ": ");
                return teclado.nextInt();
            }catch (Exception e){
                System.out.println("Error al introducir los datos. Vuelva a introducirlos.");
            }
        }
    }
}
