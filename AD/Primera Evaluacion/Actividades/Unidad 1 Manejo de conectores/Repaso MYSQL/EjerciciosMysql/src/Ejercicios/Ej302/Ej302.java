package Ejercicios.Ej302;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Dada la siguiente base de datos de empleados se pide desarrollar un programa Java que se conecte a la base de datos utilizando
 * el usuario root y la contraseña abc123. y permita:
 *
 * Mostrar la información de la base de datos.
 * Mostrar la información de la tabla proyectos.
 * Insertar un nuevo proyecto con los siguientes datos (11, 'Acceso a datos', 'Lugo', 3) en la tabla proyecto.
 * Eliminar una fila de la tabla proyecto a partir de su número de proyecto numproy.
 * NOTA: Sería conveniente crear una función independiente para cada una de las funcionalidades
 * de forma que se puedan reutilizar en un futuro. NOTA: Será importante gestionar los posibles errores que pueda generar el código.
 *
 * Información adicional
 * Algunas de las clases qeu se deberían utilizar para implementar este ejercicio son:
 *
 * Statement: para gestionar las consultas
 * PreparedStatement: para poder reutilizar consultas.
 * SQLException: para gestionar las excepciones
 */

public class Ej302 {
    private static Connection connection = Database.getInstance();

    public static void main(String[] args) {

        try {
            amosarInformacionBD();
            amosarProxectos();
            inserirProxecto(11, "Base de datos", "Lugo", 3);
            amosarProxectos();
            eliminarProxecto(11);
            amosarProxectos();
        }catch (Exception e) {
            Logger.getLogger(Ej302.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static void amosarInformacionBD() {


        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            String xestor = databaseMetaData.getDatabaseProductName();
            String conector = databaseMetaData.getDriverName();
            String url = databaseMetaData.getURL();
            String usuario = databaseMetaData.getDriverName();

            System.out.println("Información de la Base de datos");
            System.out.println("--------------------------------");
            System.out.println("Xestor: " +  xestor);
            System.out.println("Conector: " + conector);
            System.out.println("URL: " + url);
            System.out.println("Usuario: " + usuario);
        }catch (SQLException e) {

        }
    }

    private static void amosarProxectos() {
        Statement stmt;
        ResultSet rs;
        String sql = "select * from proyecto";

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println("Proxectos:");

            while (rs.next()) {
                System.out.println("\tNúmero: " + rs.getInt("Numproy") +
                        ", Nome: " + rs.getString("Nombreproy") +
                        ", Lugar: " + rs.getString("Lugarproy") +
                        ", Departamento: " + rs.getString("departamento_Numdep"));

            }
            stmt.close();
            rs.close();
        }catch (SQLException e) {
            Logger.getLogger(Ej302.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static void inserirProxecto(int num, String nome, String lugar, int depto) {
        PreparedStatement ps;
        Statement st;
        String sql = "insert into proyecto values (?,?,?,?);";

        try {
            ps = connection.prepareStatement(sql);

            ps.setInt(1,num);
            ps.setString(2,nome);
            ps.setString(3, lugar);
            ps.setInt(4, depto);

            int numTuplas = ps.executeUpdate();
            System.out.println("Sentenza: " + ps.toString());
            System.out.println("Tuplas afectadas: " + numTuplas);
            ps.close();
        }catch (SQLException e) {
            Logger.getLogger(Ej302.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static void eliminarProxecto(int numero) {
        PreparedStatement ps;
        String sql = "delete from proyecto where Numproy = ?;";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, numero);

            int numTuplas = ps.executeUpdate();
            System.out.println("Sentenza: " + ps.toString());
            System.out.println("Tuplas afectadas: " + numTuplas);
            ps.close();
        }catch (SQLException e) {
            Logger.getLogger(Ej302.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}