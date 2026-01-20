package Ejercicios.Ej310;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MEj310 {
    // Método para Consultar la información personal de un cliente.
    public void consultarInformacionCliente(Scanner teclado, GestorPedidos gestorPedidos) {
        System.out.println("Ingrese el DNI del cliente: ");
        String dni = teclado.next();

        Cliente cliente = (Cliente) gestorPedidos.obtenerClientes(dni);

        if (cliente !=null) {
            System.out.println("Información del cliente:");
            System.out.println("DNI: " + cliente.getDni());
            System.out.println("Nombre: " + cliente.getNombre());
        }else {
            System.out.println("No enocntró un cliente con ese DNI.");
        }
    }

    // Método para Consultar los pedidos realizados por un cliente
    public void consultarPedidosCliente(Scanner teclado, GestorPedidos gestorPedidos) {
        System.out.println("Ingrese el DNI del cliente: ");
        String dni = teclado.next();

        ArrayList<Pedido> pedidos = gestorPedidos.obtenrPedidos(dni);

        if (pedidos !=null && !pedidos.isEmpty()) {
            System.out.println("Pedidos del cliente:");

            for (Pedido pedido: pedidos) {
                System.out.println("Fecha del pedido: " + pedido.getFecha());
                System.out.println("Productos: ");

                double total = 0;

                for (LineaPedido linea: pedido.getLineaPedidos()) {
                    Producto producto = linea.getProducto();
                    int cantidad = linea.getCantidad();
                    double subtotal = producto.getPrecio() * cantidad;
                    System.out.println("Nombre: " + producto.getNombre() + ". Precio: " + producto.getPrecio() + ", cantidad: " +  cantidad);
                    total += subtotal;
                }
                System.out.println("Precio total del pedido: " + total);
            }
        }else {
            System.out.println("No se encontraron pedidos para ese cliente.");
        }
    }

    // Método para Realizar un pedido
    public void realizarPedido(Scanner teclado, GestorPedidos gestorPedidos) {
        System.out.println("Ingrese el DNI del cliente: ");
        String dni = teclado.next();

        Cliente cliente = (Cliente) gestorPedidos.obtenerClientes(dni);

        if (cliente == null) {
            System.out.println("Cliente no encontrado. No se puede realizar el pedido.");
            return;
        }

        ArrayList<LineaPedido> lineaPedidos = new ArrayList<>();
        while (true) {
            System.out.print("Ingrese el ID del producto (o 0 para finalizar): ");
            int idProducto = teclado.nextInt();

            if (idProducto == 0) break;

            Producto producto = gestorPedidos.obtenerProducto(idProducto);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Ingrese la cantidad: ");
            int cantidad = teclado.nextInt();

            LineaPedido lineaPedido = new LineaPedido(0, producto, cantidad);
            lineaPedidos.add(lineaPedido);
        }

        if (lineaPedidos.isEmpty()) {
            System.out.println("No se añadió ningún producto. Pedido cancelado.");
            return;
        }

        Pedido pedido = new Pedido(0, cliente, new Date(), lineaPedidos);
        if (gestorPedidos.addPedido(pedido)) {
            System.out.println("Pedido realizado con éxito.");
        } else {
            System.out.println("Hubo un error al realizar el pedido.");
        }
    }

    public void consultarProductos(GestorPedidos gestorPedidos) {
        System.out.println("Lista de Productos Disponibles:");
        int i = 1;

        while (true) {
            Producto producto = gestorPedidos.obtenerProducto(i);
            if (producto == null) break;

            System.out.println("ID: " + producto.getId() + " - Nombre: " + producto.getNombre() + " - Precio: " + producto.getPrecio());
            i++;
        }
    }
}




