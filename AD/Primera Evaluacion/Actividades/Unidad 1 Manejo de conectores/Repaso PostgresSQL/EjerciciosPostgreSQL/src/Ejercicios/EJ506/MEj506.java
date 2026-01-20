package Ejercicios.EJ506;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class MEj506 {
    private static Connection connection = Database.getInstance();
    private static Scanner teclado = new Scanner(System.in);

    public void Menu() {
        final String menu = "1. PROCEDIMIENTO INSERTAR PRODUCTO."
                + "\n2. FUNCIÓN OBTENER PRODUCTO POR CÓDIGO."
                + "\n3. SALIR";

        int opcion = -1;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    insertarProducto();
                    break;
                case 2:
                    obtenerProductoPorCodigo();
                    break;
                case 3:
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Error. Solo se puedes elegir entre las opciones entre (1-3)");
            }
        } while (opcion != 3);
    }

    private double pedirDouble(String mensaje) {
        while (true) {
            System.out.println(mensaje);

            try {
                return teclado.nextDouble();
            } catch (Exception e) {
                System.out.println("Error. " + e.toString());
            }
        }
    }

    private String pedirString(String mensaje) {
        while (true) {
            System.out.println(mensaje);

            try {
                return teclado.nextLine();
            } catch (Exception e) {
                System.out.println("Error. " + e.toString());
            }
        }
    }

    private void insertarProducto() {
        try {
            String codigo = pedirString("Código del producto: ");
            String nombre = pedirString("Nombre del producto: ");
            BigDecimal precio = new BigDecimal(pedirString("Precio del producto: "));
            String descripcion = pedirString("Descrición del producto: ");

            String sql = "SELECT productos.InsertarProducto(?, ?, ?, ?)";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, codigo);
            statement.setString(2, nombre);
            statement.setBigDecimal(3, precio);
            statement.setString(4,descripcion);

            statement.execute();

            System.out.println("Producto insertado Correctamente");
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    private void obtenerProductoPorCodigo() {
        try {
            String codigo = pedirString("Código del producto: ");


            String sql = "SELECT * FROM productos.ObtenerProductoPorCodigo(?)";
            CallableStatement statement = connection.prepareCall(sql);

            statement.setString(1, codigo);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("\nInformación del Producto con código: " + codigo);
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Precio: " + resultSet.getDouble("precio"));
                System.out.println("Descripción: " + resultSet.getString("descripcion"));
            } else {
                System.out.println("No se encontró ningún producto con ese código.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
