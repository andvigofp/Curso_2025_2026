package org.example.Aplicacion;

import org.example.entidades.Director;
import org.example.repositorios.CicloRepositorio;
import org.example.repositorios.DirectorRepositorio;
import org.example.repositorios.InstitutoRepositorio;
import org.example.repositorios.TalleresRepositorio;

import java.util.Scanner;

public class MEJExmamen {
    private static Scanner teclado = new Scanner(System.in);
    private InstitutoRepositorio institutoRepositorio;
    private CicloRepositorio cicloRepositorio;
    private DirectorRepositorio directorRepositorio;
    private TalleresRepositorio talleresRepositorio;

    public MEJExmamen(InstitutoRepositorio institutoRepositorio, CicloRepositorio cicloRepositorio, DirectorRepositorio directorRepositorio, TalleresRepositorio talleresRepositorio) {
        this.institutoRepositorio = institutoRepositorio;
        this.cicloRepositorio = cicloRepositorio;
        this.directorRepositorio = directorRepositorio;
        this.talleresRepositorio = talleresRepositorio;
    }

    public void Menu() {
        final String menu = "1. Crear instituto\n" +
                "2. Eliminar instituto\n" +
                "3. Crear ciclo\n" +
                "4. Eliminar ciclo\n" +
                "5. Modificar telefono instituto\n" +
                "6. Crear Director\n" +
                "7. Asignar director al IES\n" +
                "8. Asignar Ciclo al IES\n" +
                "9. Consulta 1\n" +
                "10. Consulta 2\n" +
                "11. Consulta 3\n" +
                "12. Salir";

        int opcion = -1;


        do {

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    institutoRepositorio.crearInstituto("Pepe", "986375088");
                    break;
                case 2:
                    institutoRepositorio.eliminarInstituto(3);
                    break;
                case 3:
                    cicloRepositorio.crearCiclo("Clico5");
                    break;
                case 4:
                    cicloRepositorio.borrarCiclo(3);
                    break;
                case 5:
                    institutoRepositorio.modificarInstituto(2, "6666666");
                    break;
                case 6:
                    directorRepositorio.crearDirector("JF6", 25, "Juan");
                    break;
                case 7:
                    directorRepositorio.AsignarDirectorInstituto(5, 5);
                    break;
                case 8:
                    cicloRepositorio.asignarCicloATaller(5, 4);
                    break;
                case 9:
                    institutoRepositorio.obtnerNombreTelefonoCodigoDirector(2);
                    break;
                case 10:
                    cicloRepositorio.listarCiclosDeInstituto(2);
                    break;
                case 11:
                    talleresRepositorio.obtenerTalleresPorInstituto(2);
                    break;
                case 12:
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Error: Solo puedes elegir entre las opciones (1-12)");

            }
        } while (opcion != 12);
    }

    public static String pedirString(String mensaje) {
        System.out.println(mensaje);
        return teclado.nextLine();
    }


    public static int pedirInt(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                return teclado.nextInt();
            } catch (Exception e) {
            }

        }
    }
}
