package org.example;

import org.example.Aplicacion.MEJPedidos;
import org.example.Repositorio.ClienteRepositorio;
import org.example.Repositorio.LineaPedidoRepositorio;
import org.example.Repositorio.PedidoRepositorio;
import org.hibernate.Session;

import java.util.Scanner;

/**
 * Deberás crear un proyecto Hibernate que mapee las tablas de la base de datos. La aplicación debe permitir hacer lo siguiente:
 *
 * Mostrar todos los pedidos de la base de datos.
 * Mostrar todos los pedidos de un cliente.
 * Añadir un pedido y sus productos.
 * Borrar un pedido con sus productos.
 */
public class App {
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        LineaPedidoRepositorio lineaPedidoRepositorio = new LineaPedidoRepositorio(session);
        ClienteRepositorio clienteRepositorio = new ClienteRepositorio(session);
        PedidoRepositorio pedidoRepositorio = new PedidoRepositorio(session, teclado);

        MEJPedidos mejPedidos = new MEJPedidos(lineaPedidoRepositorio, clienteRepositorio, pedidoRepositorio);

        mejPedidos.mostrarMenu();

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
