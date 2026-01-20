package org.example;

import org.example.Aplicacion.MEJ308;
import org.example.Repositorio.ClienteRepositorio;
import org.example.Repositorio.FacturaRepositorio;
import org.hibernate.Session;

import java.util.Scanner;

public class App {
   private static Scanner teclado;

    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        teclado = new Scanner(System.in);

        ClienteRepositorio clienteRepositorio = new ClienteRepositorio(session);
        FacturaRepositorio facturaRepositorio = new FacturaRepositorio(session, teclado);

        MEJ308 metodos = new MEJ308(clienteRepositorio, facturaRepositorio);

        metodos.mostrarMenu();

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
