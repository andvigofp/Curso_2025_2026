package org.example;

import org.example.Aplicacion.MEJExmamen;
import org.example.repositorios.CicloRepositorio;
import org.example.repositorios.DirectorRepositorio;
import org.example.repositorios.InstitutoRepositorio;
import org.example.repositorios.TalleresRepositorio;
import org.hibernate.Session;

public class App {
    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        InstitutoRepositorio institutoRepositorio = new InstitutoRepositorio(session);

        CicloRepositorio cicloRepositorio = new CicloRepositorio(session);

        DirectorRepositorio directorRepositorio = new DirectorRepositorio(session);

        TalleresRepositorio talleresRepositorio = new TalleresRepositorio(session);

        MEJExmamen mejExmamen = new MEJExmamen(institutoRepositorio, cicloRepositorio, directorRepositorio, talleresRepositorio);

        mejExmamen.Menu();

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
