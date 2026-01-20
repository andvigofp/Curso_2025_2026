package org.example;

import org.example.Aplicacion.MEJ309;
import org.example.Repositorios.*;
import org.hibernate.Session;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        EmpresaRepository empresaRepository = new EmpresaRepository(session);
        EmpleadoRepository empleadoRepository = new EmpleadoRepository(session);
        FijoRepository fijoRepository = new FijoRepository(session);
        ProductoRepository productoRepository = new ProductoRepository(session);
        TemporalRepository temporalRepository = new TemporalRepository(session);
        VentaRepository ventaRepository = new VentaRepository(session);
        Scanner teclado = new Scanner(System.in);

        MEJ309 metodos = new MEJ309(session,empresaRepository, empleadoRepository, fijoRepository, temporalRepository, productoRepository, ventaRepository, teclado);

        metodos.mostrarMenu();

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
