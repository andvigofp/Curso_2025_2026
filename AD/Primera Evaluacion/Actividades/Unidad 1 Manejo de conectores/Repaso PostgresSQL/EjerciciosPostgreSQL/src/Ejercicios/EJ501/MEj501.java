package Ejercicios.EJ501;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MEj501 {
    private static Connection connection = Database.getInstance();

    public MEj501(Connection connection) {
        this.connection = connection;
    }

    // Método menú de opciones
    public void Menu(Scanner teclado) throws SQLException {
        final String menu = "1. INSERTAR LIBROS."
                + "\n2. CONSULTAR LIBROS."
                + "\n3. ACTAULIZAR LIBROS."
                + "\n4. ELIMINAR LIBROS."
                + "\n5. SALIR.";

        int opcion = -1;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    insertar(teclado);
                    break;
                case 2:
                    consultar(teclado);
                    break;
                case 3:
                    actualizar(teclado);
                    break;
                case 4:
                    eliminar(teclado);
                    break;
                case 5:
                    System.out.println("Fin del programa");
                    teclado.close();
                    return;
                default:
                    System.out.println("Opción no válida, por favor elija una opción del menú entre 1-5");
            }
        } while (opcion != 5);
    }

    //Método para insertar datos BD listalibros
    static void insertar(Scanner teclado) throws SQLException {
        // Solicitar el título del libro
        System.out.print("Título del libro: ");
        String titulo = teclado.nextLine();

        System.out.print("Autor del libro: ");
        String autor = teclado.nextLine();

        System.out.print("Año de nacimiento del autor: ");
        int nacimientoAutor = teclado.nextInt();
        teclado.nextLine(); // Consumir el salto de línea que queda después de nextInt()

        System.out.print("Año del libro: ");
        int ano = teclado.nextInt();
        teclado.nextLine(); // Consumir el salto de línea que queda después de nextInt()

        // Preparar la consulta SQL para insertar datos en la tabla
        String insertSQL = "INSERT INTO objetos.libros (titulo, autor, año_publicacion) VALUES(?, ROW(?, ?), ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
        preparedStatement.setString(1, titulo);
        preparedStatement.setString(2, autor);
        preparedStatement.setInt(3, nacimientoAutor);
        preparedStatement.setInt(4, ano);

        // Ejecutar la consulta de inserción
        int rowInserted = preparedStatement.executeUpdate();

        // Comprobar si se insertó el libro correctamente
        if (rowInserted > 0) {
            System.out.println("Libro insertado con éxito");
        } else {
            System.out.println("No se pudo insertar el libro");
        }
    }

    //Método para consultar datos BD listalibros
    static void consultar(Scanner teclado) throws SQLException {
        System.out.println("Buscar libros por título o autor: ");
        String consulta = teclado.nextLine();

        String selectSQL = "SELECT * FROM objetos.libros WHERE titulo LIKE ? OR (autor).nombre_autor LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setString(1, "%" + consulta + "%");
        preparedStatement.setString(2, "%" + consulta + "%");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println("Título: " + resultSet.getString("titulo"));
            System.out.println("Autor: " + resultSet.getString("autor"));
            System.out.println("Año: " + resultSet.getInt("año_publicacion"));
            System.out.println("-----------------------------");
        }
    }

    //Método para actualizar datos BD listalibros
    static void actualizar(Scanner teclado) throws SQLException {
        System.out.println("ID del libro a actualizar: ");
        int idLibro = teclado.nextInt();
        teclado.nextLine(); // Consumir la nueva línea

        System.out.println("Nuevo título del libro");
        String nuevoTitulo = teclado.nextLine();

        System.out.println("Nuevo nombre del autor del libro");
        String nuevoAutor = teclado.nextLine();

        System.out.println("Nueva fecha de nacimiento del autor del libro: ");
        int fechaNacimiento = teclado.nextInt();

        // En lugar de asignar a cada campo, crea un nuevo objeto autor
        String updateSQL = "UPDATE objetos.libros SET titulo = ?, autor = ROW(?, ?) WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
        preparedStatement.setString(1, nuevoTitulo);
        preparedStatement.setString(2, nuevoAutor);
        preparedStatement.setInt(3, fechaNacimiento);
        preparedStatement.setInt(4, idLibro);

        int rowsUpdated = preparedStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Libro actualizado con éxito");
        } else {
            System.out.println("No se pudo actualizar el libro. Verifica el ID.");
        }
    }

    //Método para eliminar datos BD listalibros
    static void eliminar(Scanner teclado) throws SQLException {
        System.out.println("ID del libro a eliminar: ");
        int idLibro = teclado.nextInt();
        teclado.nextLine(); // Consumir la nueva línea

        // Cambia "objeto" por "objetos"
        String deleteSQL = "DELETE FROM objetos.libros WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
        preparedStatement.setInt(1, idLibro);

        int rowsDeleted = preparedStatement.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Libro eliminado con éxito");
        } else {
            System.out.println("No se pudo eliminar el libro. Verifica el ID.");
        }
    }
}
