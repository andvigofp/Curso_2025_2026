package Ejercicios.Ej310;

import java.util.ArrayList;
import java.util.Date;

public class AppPedidosB {

    public static void main(String[] args) {
        GestorPedidos gestorBD = new GestorPedidos();

        // DNI de ejemplo
        String dniCliente = "22222222B";

        // Obtenemos la información del cliente
        Cliente cliente = (Cliente) gestorBD.obtenerClientes(dniCliente);
        System.out.println(cliente);

        // Obtenemos los pedidos de ese cliente
        ArrayList<Pedido> pedidos = gestorBD.obtenrPedidos(dniCliente);
        for (Pedido pedido: pedidos) {
            System.out.println(pedido);
        }

        // Creamos un nuevo pedido para el cliente
        Producto producto = gestorBD.obtenerProducto(1);
        LineaPedido lineaPedido = new LineaPedido(3, producto, 5);
        ArrayList<LineaPedido> lineasPedido = new ArrayList<LineaPedido>();

        lineasPedido.add(lineaPedido);

        Date fechaActual = new Date(System.currentTimeMillis());
        Pedido nuevoPedido = new Pedido(3, cliente, fechaActual, lineasPedido);
        System.out.println(nuevoPedido);
        gestorBD.addPedido(nuevoPedido);
    }
}

