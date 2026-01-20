package Ejercicios.Ej310;

import java.util.Scanner;

/**
 * La estructura de cada factura debería seguir el siguiente modelo:
 *
 * Cliente:
 *  DNI: 22222222B
 * Nombre: Ann
 *
 * Fecha:
 *  2023-07-24
 *
 * Productos:
 * Nombre: bicicleta Precio: 205,60
 * Cantidad: 1
 * Nombre: silla Precio: 20,00
 * Cantidad: 2
 * Precio total: 245,60
 *
 * Después, diseña e implementa la aplicación, que nos debe permitir:
 *
 * Consultar la información personal de un cliente.
 * Consultar los pedidos realizados por un cliente. Se debe mostrar la fecha en la que realizó el pedido,
 * los productos que compró y el precio total del pedido.
 * Realizar un pedido. El cliente introducirá su dni e indicará los productos que quiere añadir al pedido
 */
public class AppPedidos {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        GestorPedidos gestorPedidos = new GestorPedidos();

        MEj310 metodos = new MEj310();

        final String menu = "1. CONSULTAR INFORMACIÓN DE UN CLIENTE ."
                + "\n2. CONSULTAR PEDIDOS REALIZADOS POR UN CLIENTE."
                + "\n3. REALIZAR UN PEDIDO."
                + "\n4. CONUSLTAR PEDIDOS DISPONIBLES."
                + "\n5. SALIR.";

        int opcion = -1;

        while (true) {
            System.out.println(menu);
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    metodos.consultarInformacionCliente(teclado, gestorPedidos);
                    break;
                case 2:
                    metodos.consultarPedidosCliente(teclado, gestorPedidos);
                    break;
                case 3:
                    metodos.realizarPedido(teclado, gestorPedidos);
                    break;
                case 4:
                    metodos.consultarProductos(gestorPedidos);
                    break;
                case 5:
                    System.out.println("Fin del programa");
                    teclado.close();
                    return;
                default:
                    System.out.println("Opción no válida, por favor elija una opción del menú entre 1-5");
            }
        }
    }
}
