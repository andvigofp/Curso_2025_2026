package org.example.Aplicacion;

import org.example.Entidades.Cliente;
import org.example.Repositorio.ClienteRepositorio;
import org.example.Repositorio.LineaPedidoRepositorio;
import org.example.Repositorio.PedidoRepositorio;
import org.hibernate.Session;

import java.util.Scanner;

public class MEJPedidos {
    private Session session;
    private static  Scanner teclado = new Scanner(System.in);
    private ClienteRepositorio clienteRepositorio;
    private LineaPedidoRepositorio lineaPedidoRepositorio;
    private PedidoRepositorio pedidoRepositorio;

    public MEJPedidos(LineaPedidoRepositorio lineaPedidoRepositorio, ClienteRepositorio clienteRepositorio, PedidoRepositorio pedidoRepositorio) {
        this.lineaPedidoRepositorio = lineaPedidoRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.pedidoRepositorio = pedidoRepositorio;
    }

    public void mostrarMenu() {

        int opcion = -1;

        do {
            System.out.println("1. Mostrar todos los pedidos\n" +
                    "2. Mostrar pedidos de un cliente\n" +
                    "3. Añadir pedido\n" +
                    "4. Borrar pedido\n" +
                    "5. Salir");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    lineaPedidoRepositorio.mostrarTodosPedidos();
                    break;
                case 2:
                    String dniCliente = pintarPedirString("Introduzca el DNI del cliente: ");
                    Cliente cliente = clienteRepositorio.obtenerClientePorDNI(dniCliente);
                    lineaPedidoRepositorio.mostrarPedidosCliente(cliente);
                    break;
                case 3:
                    String dniClientes = pintarPedirString("Introduzca el DNI del cliente: ");
                    Cliente clientes = clienteRepositorio.obtenerClientePorDNI(dniClientes);
                    if (clientes.getIdCliente() != -1)
                        pedidoRepositorio.addPedido(clientes);
                    else
                        System.out.println("No existe un cliente con ese ID");
                    break;
                case 4:
                    int idPedido = pintarPedirInt("Introduzca el id del pedido");
                    pedidoRepositorio.eliminarPedido(idPedido);
                    break;
                case 5:
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Error. Solo puedes elegir entre las opciones (1-5)");
            }


        }while (opcion !=5);
    }

    public static int pintarPedirInt(String mensaje) {
        System.out.println(mensaje);
        return teclado.nextInt();
    }

    public static String pintarPedirString(String mensaje) {
        System.out.println(mensaje);
        return teclado.next();
    }

}
