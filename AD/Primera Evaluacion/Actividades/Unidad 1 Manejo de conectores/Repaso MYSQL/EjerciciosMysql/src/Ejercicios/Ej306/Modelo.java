package Ejercicios.Ej306;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo {
    private static Connection connection = Database.getInstance();

    // TODO: DEFINICIÓN DE LA CONEXIÓN CON LA BASE DE DATOS
    //TODO: CREAR FUNCIÓN PARA LA OBTENCIÓN DE LOS RESULTADOS DE LA CONSULTA


    public Modelo() {
    }

    public String obtenerDatosEmpleado(String numeroEmpleado) {
        String resultadoString="";

        try {
            Statement smt = connection.createStatement();

            ResultSet rs = smt.executeQuery("SELECT * FROM empleado WHERE NSS = '" + numeroEmpleado + "'");

            while (rs.next()) {
                resultadoString = "NSS: " + rs.getString("NSS") +
                        "\nNombre: " + rs.getString("Nombre") +
                        "\nApellido 1:" + rs.getString("Apel1") +
                        "\nApellido 2: " + rs.getString("Apel2") +
                        "\nSexo: " + rs.getString("Sexo") +
                        "\nDirección: " + rs.getString("Dirección") +
                        "\nFecha Nacimiento: " + rs.getString("Fechanac") +
                        "\nSalario: " + rs.getString("Salario") +
                        "\nNúmero Departamento: " + rs.getString("Numdept") +
                        "\nNSSsup: " + rs.getString("NSSsup");
            }
            connection.close();
        }catch (SQLException ex) {
            return "Error de conexión " + ex.toString();
        }
        return resultadoString;
    }
}

