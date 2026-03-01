package Aplicacion;

import Conexiones.ConexionBaseX;
import Conexiones.ConexionMongoDB;

import java.util.Scanner;

public class MEmenu {
    private static Scanner teclado = new Scanner(System.in);
    private static MEBasex basex = new MEBasex();
    private static MEMongoDB mongoDB = new MEMongoDB();

    final String menu = "XML\n" +
            "\t1. Consulta 1\n" +
            "\t2. Consulta 2\n" +
            "\t3. Consulta 3\n" +
            "\t4. Consulta 4\n" +
            "\t5. Eliminar libros\n" +
            "MongoDB\n" +
            "\t6. Consulta 1\n" +
            "\t7. Consulta 2\n" +
            "\t8. Consulta 3\n" +
            "\t9. Consulta 4\n" +
            "\t10. Eliminar peliculas\n" +
            "\t11. Anhadir nuevo documento\n" +
            "12. Salir";

    public void metodos() {
        int opcion = -1;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    basex.gestionConsulta("let $max := max(/biblioteca/libro/detalles/paginas)\n" +
                            "for $l in /biblioteca/libro\n" +
                            "where $l/detalles/paginas = $max\n" +
                            "return $l/titulo");
                    break;
                case 2:
                    basex.gestionConsulta("for $l in /biblioteca/libro\n" +
                            "order by $l/detalles/valoracion/puntuacion ascending\n" +
                            "return $l/titulo");
                    break;
                case 3:
                    basex.gestionConsulta("avg(\n" +
                            "  for $l in /biblioteca/libro\n" +
                            "  where $l/detalles/paginas > 300 and $l/detalles/valoracion/resenas/resena/usuario = \"usuario7\"\n" +
                            "  return $l/detalles/valoracion/puntuacion\n" +
                            ")");
                    break;
                case 4:
                    basex.gestionConsulta("for $autor in distinct-values(/biblioteca/libro/autor)\n" +
                            "return\n" +
                            "<autor>\n" +
                            "<nombre>{$autor}</nombre>\n" +
                            "<numeroLibros>{count(/biblioteca/libro[autor = $autor])}</numeroLibros>\n" +
                            "</autor>");
                    break;
                case 5:
                    basex.eliminarPeliculta();
                    break;
                case 6:
                    mongoDB.obtenerPeliculasSuperior();
                    break;
                case 7:
                    mongoDB.obtnerPelciulasGeneroDrama();
                    break;
                case 8:
                    mongoDB.obtenerTituloDirectorDuracion();
                    break;
                case 9:
                    mongoDB.obtenerTituloAñoLanzamientoNumeroActores();
                    break;
                case 10:
                    mongoDB.eliminarPelicula();
                    break;
                case 11:
                    mongoDB.insertarPelciula();
                    break;
                case 12:
                    System.out.println("Fin del programa");
                    ConexionBaseX.cerrarConexion();
                    ConexionMongoDB.cerrarConexion();
                    break;
                default:
                    System.out.println("Error. Solo puedes elegir entre las opciones (1-12)");
            }
        }while (opcion !=12);
    }
}
