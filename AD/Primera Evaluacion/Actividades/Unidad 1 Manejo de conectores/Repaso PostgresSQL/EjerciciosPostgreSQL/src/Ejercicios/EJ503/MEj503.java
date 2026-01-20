package Ejercicios.EJ503;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MEj503 {
    static Scanner teclado = new Scanner(System.in);
    static Connection connection = Database.getInstance();

    public void Menu() throws SQLException {
        final String menu = "1. INSERTAR UN NUEVO POKEMON."
                + "\n2. MODIFICAR UN POKEMON."
                + "\n3. ELIMINAR UN POKMEON."
                + "\n4. CONSULTAR POKEMON"
                + "\n5. SALIR.";

        int opcion = -1;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    insertarPokemon();
                    break;
                case 2:
                    modificarPokemon();
                    break;
                case 3:
                    eliminarPokemon();
                    break;
                case 4:
                    consultaPokemon();
                    break;
                case 5:
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Opción no válida, por favor eliga una opción del menu entre 1-5");
            }
        }while (opcion !=5);
    }


    private void insertarPokemon() throws SQLException {
        System.out.println("Nombre del pokémon: ");
        String nombre = teclado.nextLine();
        System.out.println("Tipo de pokémon: ");
        String tipo = teclado.nextLine();
        System.out.println("Nivel del pokémon: ");
        int nivel = teclado.nextInt();

        String inserSQL = "INSERT INTO objetos.Pokemons (pokemon) VALUES (ROW(?, ?, ?))";
        PreparedStatement preparedStatement = connection.prepareStatement(inserSQL);
        preparedStatement.setString(1, nombre);
        preparedStatement.setString(2, tipo);
        preparedStatement.setInt(3, nivel);

        int rowInserted = preparedStatement.executeUpdate();

        if (rowInserted > 0) {
            System.out.println("Pokémon insertado con éxito.");
        }else {
            System.out.println("No se pudo insertar el Pokémon");
        }
    }

    private void modificarPokemon() throws SQLException {
        System.out.println("ID del Pokémon a modificar: ");
        int idPokemon = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Nuevo nombre del Pokémon: ");
        String nuevoNombre = teclado.nextLine();
        System.out.println("Nuevo tipo del Pokémon: ");
        String nuevoTipo = teclado.nextLine();
        System.out.println("Nuevo nivel del Pokémon: ");
        int nuevoNivel = teclado.nextInt();

        String updateSQL = "UPDATE objetos.Pokemon SET pokemon = ROW(?, ?, ?) WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
        preparedStatement.setString(1, nuevoNombre);
        preparedStatement.setString(2, nuevoTipo);
        preparedStatement.setInt(3, nuevoNivel);
        preparedStatement.setInt(4, idPokemon);

        int rowUpdate = preparedStatement.executeUpdate();

        if (rowUpdate > 0) {
            System.out.println("Pokémon actualizado con éxito");
        }else {
            System.out.println("No se pudo actualizar el Pokémon. Verifica el ID.");
        }
    }

    private void eliminarPokemon() throws SQLException {
        System.out.println("ID del Pokémon a elminar: ");
        int idPokemon = teclado.nextInt();

        String deleteSQL = "DELETE FROM objetos.Pokemons WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
        preparedStatement.setInt(1, idPokemon);

        int rowDeleted = preparedStatement.executeUpdate();

        if (rowDeleted > 0) {
            System.out.println("Pokémon eliminado con éxito.");
        }else {
            System.out.println("No se pudo eliminar el Pokémon. Veririfique el ID");
        }
    }

    private void consultaPokemon() throws SQLException {
        String querySQL = "SELECT p.id, (p.pokemon).nombre AS nombre, (p.pokemon).tipo AS tipo, (p.pokemon).nivel AS nivel FROM objetos.Pokemons p";

        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println("Lista de Pokémon:");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            String tipo = resultSet.getString("tipo");
            int nivel = resultSet.getInt("nivel");

            System.out.printf("ID: %d, Nombre: %s, Tipo: %s, Nivel: %d%n", id, nombre, tipo, nivel);
        }

        resultSet.close();
        preparedStatement.close();
    }
}
