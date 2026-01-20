package org.example;

import org.example.Aplicacion.MEJ308;
import org.example.Repositorio.GeneroRepositorio;
import org.example.Repositorio.PeliculaRepositorio;
import org.example.Repositorio.PersonaRepositorio;
import org.hibernate.Session;

public class App {
    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        PersonaRepositorio personaRepositorio = new PersonaRepositorio(session);
        PeliculaRepositorio peliculaRepositorio = new PeliculaRepositorio(session);
        GeneroRepositorio generoRepositorio = new GeneroRepositorio(session);

        MEJ308 metodos = new MEJ308(personaRepositorio, peliculaRepositorio, generoRepositorio);

        metodos.mostrarMenu();

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
