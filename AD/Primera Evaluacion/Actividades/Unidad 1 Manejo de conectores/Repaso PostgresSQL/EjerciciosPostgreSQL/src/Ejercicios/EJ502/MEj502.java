package Ejercicios.EJ502;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MEj502 {
    static Scanner teclado = new Scanner(System.in);

        // Método menú de opciones
        public void Menu(Statement statement) throws SQLException {
            final String menu = "1. LISTAR TODOS EQIPOS/DIRECTIVOS."
                    + "\n2. OBTENER LOS PILOTOS/EQUIPOS ACTAULES."
                    + "\n3. RESULTADO DE UNA CARRERA ESPECIFICA."
                    + "\n4. PILOTO MÁS VIEJO."
                    + "\n5. NÚMERO DE VICTORIAS POR EQUIPO"
                    + "\n6. SALIR";

            int opcion = -1;

            do {
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        listarEquiposDirectivos(statement);
                        break;
                    case 2:
                        obtenerPilotos(statement);
                        break;
                    case 3:
                        resultadoCarrera(statement);
                        break;
                    case 4:
                        pilotoMasViejo(statement);
                        break;
                    case 5:
                        numeroVictorias(statement);
                        break;
                    case 6:
                        System.out.println("Fin del programa");
                        teclado.close();
                        return;
                    default:
                        System.out.println("Opción no válida, por favor elija una opción del menú entre 1-6");
                }
            } while (opcion != 6);
        }

        //Método para listar todos los equipos y equipos directivos BD formula1
        static void listarEquiposDirectivos(Statement statement) throws SQLException {
            String consultaSQL = "SELECT nombre AS \"Equipo\", director AS \"Director\" FROM equipos";
            ResultSet resultSet = statement.executeQuery(consultaSQL);

            while (resultSet.next()) {
                String equipo = resultSet.getString("Equipo");
                String director = resultSet.getString("Director");
                System.out.println(equipo + " - " + director);
            }
        }

        //Método para Obtner los pilotos y sus equipos actuales
        static void obtenerPilotos(Statement statement) throws SQLException {
            String cosnulta = "SELECT pilotos.nombre AS \"Piloto\", equipos.nombre AS \"Equipo\""
                    + "FROM pilotos "
                    + "INNER JOIN equipos ON pilotos.equipo_id = equipos.equipo_id;";

            ResultSet resultSet = statement.executeQuery(cosnulta);

            while (resultSet.next()) {
                String piloto = resultSet.getString("Piloto");
                String equipo = resultSet.getString("Equipo");
                System.out.println(equipo + "\t" + piloto);
            }
        }

        //Método para mostrar El Resultado de una carrera específica
        static void resultadoCarrera(Statement statement) throws SQLException {
            String consulta = "SELECT equipos.nombre AS \"Equipo\", COUNT(resultados.resultado_id) AS \"Victorias\" " +
                    "FROM resultados " +
                    "INNER JOIN pilotos ON resultados.piloto_id = pilotos.piloto_id " +
                    "INNER JOIN equipos ON pilotos.equipo_id = equipos.equipo_id " +
                    "WHERE resultados.posicion = 1 " +
                    "GROUP BY equipos.nombre";

            ResultSet resultSet = statement.executeQuery(consulta);

            while (resultSet.next()) {
                String equipo = resultSet.getString("Equipo");
                int victorias = resultSet.getInt("Victorias");
                System.out.println(equipo + " - Victorias: " + victorias);
            }
        }

        //Método para obtener el piloto más viejo
        static void pilotoMasViejo(Statement statement) throws SQLException {
            String consulta = "SELECT nombre AS \"Piloto\", nacionalidad AS \"Nacionalidad\", fecha_nacimiento AS \"Fecha de Nacimiento\""
                    + "FROM pilotos "
                    + "WHERE fecha_nacimiento = (SELECT MIN(fecha_nacimiento) FROM pilotos);";

            ResultSet resultSet = statement.executeQuery(consulta);

            while (resultSet.next()) {
                String nacionalidad = resultSet.getString("Nacionalidad");
                String piloto = resultSet.getString("Piloto");
                String fecha = resultSet.getString("Fecha de Nacimiento");
                System.out.println(piloto + " - Nacionalidad: " + nacionalidad + " - Fecha de Nacimiento: " + fecha);
            }
        }

        //Método para obtener el Número de victorias por equipo
        static void numeroVictorias(Statement statement) throws SQLException {
            String consulta = "SELECT equipos.nombre AS \"Equipo\", COUNT(resultados.resultado_id) AS \"Victorias\" " +
                    "FROM resultados " +
                    "INNER JOIN pilotos ON resultados.piloto_id = pilotos.piloto_id " +
                    "INNER JOIN equipos ON pilotos.equipo_id = equipos.equipo_id " +
                    "WHERE resultados.posicion = 1 " +
                    "GROUP BY equipos.nombre";

            ResultSet resultSet = statement.executeQuery(consulta);

            while (resultSet.next()) {
                String equipo = resultSet.getString("Equipo");
                int victorias = resultSet.getInt("Victorias");
                System.out.println(equipo + " - Victorias: " + victorias);
            }
        }
    }

