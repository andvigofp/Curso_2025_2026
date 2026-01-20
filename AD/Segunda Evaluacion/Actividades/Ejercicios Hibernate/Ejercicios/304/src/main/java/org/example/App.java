package org.example;

import org.example.Aplicacion.MEJLibrosAutoresHibernate;
import org.example.Repositorio.AutorRepositorio;
import org.example.Repositorio.LibroRepositorio;
import org.example.Repositorio.TelefonoRepositorio;
import org.hibernate.Session;

public class App {
    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        AutorRepositorio autorRepositorio = new AutorRepositorio(session);
        LibroRepositorio libroRepositorio = new LibroRepositorio(session);
        TelefonoRepositorio telefonoRepositorio = new TelefonoRepositorio(session);

        MEJLibrosAutoresHibernate mejLibrosAutoresHibernate = new MEJLibrosAutoresHibernate(autorRepositorio, libroRepositorio, telefonoRepositorio);

        mejLibrosAutoresHibernate.mostarMenu();

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
