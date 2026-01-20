package Ejercicios.Ej309;



import Ejercicios.Ej310.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Operaciones {
    private static Connection connection = Database.getInstance();

    public boolean existsClient(String idCliente) {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("SELECT * FROM client where idClient = ?");

            ps.setString(1, idCliente);

            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next())
                count++;

            return (count > 0);

        } catch (SQLException e) {
            e.printStackTrace();
            Database.cerrarConexion();
            return false;
        }
    }

    public boolean existsBook(String idLibro) {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("SELECT * FROM book where idClient = ?");

            ps.setString(1, idLibro);

            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next())
                count++;

            return (count > 0);

        } catch (SQLException e) {
            e.printStackTrace();
           Database.cerrarConexion();
            return false;
        }
    }

    public boolean isBorrowed(String code) {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(
                    "SELECT l.borrowed FROM book as b, loan as l WHERE b.code = ? and b.idBook = l.idBook and l.borrowed = true");

            ps.setString(1, code);

            ResultSet rs = ps.executeQuery();
            boolean borrowed = false;
            while (rs.next())
                borrowed = borrowed || rs.getBoolean("borrowed");

            return borrowed;

        } catch (SQLException e) {
            e.printStackTrace();
            Database.cerrarConexion();
            return false;
        }
    }

    public void addLoan(String code, String idCliente) {
        if (!existsClient(idCliente))
            System.out.println("No existe el cliente");
        if (isBorrowed(code))
            System.out.println("El libro ya está siendo prestado");

        try {
            PreparedStatement psIdLibro = connection.prepareStatement("SELECT idBook FROM book" + " WHERE code = ?");

            psIdLibro.setString(1, code);

            ResultSet rsIdLibro = psIdLibro.executeQuery();
            int idLibro = -1;
            while (rsIdLibro.next())
                idLibro = rsIdLibro.getInt("idBook");
            if (idLibro == -1) {
                System.out.println("Error en el código del libro");
                Database.cerrarConexion();
                return;
            }

            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO loan (idBook, idClient, date, borrowed)" + " VALUES (?, ?, ?, ?)");

            ps.setInt(1, idLibro);
            ps.setInt(2, Integer.parseInt(idCliente));
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setBoolean(4, true);

            if (ps.executeUpdate() <= 0)
                System.out.println("Se ha producido un error al insertar un nuevo préstamo");

        } catch (SQLException e) {
            e.printStackTrace();
            Database.cerrarConexion();
        }
    }

    public void addReturn(String code) {
        if (!isBorrowed(code))
            System.out.println("El libro no está siendo prestado");

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE loan as l, book as b SET "
                    + "l.borrowed = false WHERE b.idBook = l.idBook and b.code = ? and l.borrowed = true");

            ps.setString(1, code);

            if (ps.executeUpdate() <= 0)
                System.out.println("Se ha producido un error al devolver un préstamo");

        } catch (SQLException e) {
            e.printStackTrace();
            Database.cerrarConexion();
        }
    }

    public ArrayList<Libro> borrowedBooksList() {
        PreparedStatement ps;
        ArrayList<Libro> listaLibros = new ArrayList<Libro>();
        try {
            ps = connection.prepareStatement("SELECT b.idBook, b.code, b.title, b.authors, b.year FROM"
                    + " book as b, loan as l where b.idBook = l.idBook and l.borrowed = true");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaLibros.add(new Libro(rs.getInt("idBook"), rs.getString("code"), rs.getString("title"),
                        rs.getString("authors"), rs.getInt("year")));
            }

            return listaLibros;

        } catch (SQLException e) {
            e.printStackTrace();
            Database.cerrarConexion();
            return listaLibros;
        }
    }

    public ArrayList<Libro> availableBooksList() {
        PreparedStatement ps;
        ArrayList<Libro> listaLibros = new ArrayList<Libro>();
        try {
            ps = connection.prepareStatement("SELECT b.idBook, b.code, b.title, b.authors, b.year FROM"
                    + " book as b, loan as l where b.idBook = l.idBook and l.borrowed = false");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaLibros.add(new Libro(rs.getInt("idBook"), rs.getString("code"), rs.getString("title"),
                        rs.getString("authors"), rs.getInt("year")));
            }

            return listaLibros;

        } catch (SQLException e) {
            e.printStackTrace();
            Database.cerrarConexion();
            return listaLibros;
        }
    }
}
