package Ejercicios.Ej312;

import java.sql.*;

public class GestorLibros {
   private static Connection connection = Database.getInstance();

    // Método para listar los libros que están esn stock
    public void libros_limite_stock() throws SQLException {
        // Para ejecutar un procedimeinto de la base de datos
        CallableStatement cstmt = connection.prepareCall("CALL pa_libros_limite_stock()");

        ResultSet rs = cstmt.executeQuery();
        while(rs.next())
            System.out.println(rs.getString("titulo") + ": " + Double.toString(rs.getDouble("precio")) + "€");

    }

    // Método para devolver información por el código del libro
    public void getLibroId(int id) throws SQLException {
        // Llama al procedimiento correcto
        CallableStatement cstmt = connection.prepareCall("CALL get_libro_by_id(?)");

        cstmt.setInt(1, id);

        ResultSet rs = cstmt.executeQuery();

        while(rs.next())
            System.out.println(rs.getString("titulo") + ": " + Double.toString(rs.getDouble("precio")) + "€");

    }

    // Método para obtener el número de libros por cierta cantidad
    public void contar_libros_precio(float precio) throws SQLException {

        CallableStatement cstmt = connection.prepareCall("CALL contar_libros_precio(?, ?)");

        cstmt.setDouble(1, precio);
        cstmt.registerOutParameter(2, Types.INTEGER);

        cstmt.executeUpdate();

        System.out.println(cstmt.getInt(2));

    }
}

