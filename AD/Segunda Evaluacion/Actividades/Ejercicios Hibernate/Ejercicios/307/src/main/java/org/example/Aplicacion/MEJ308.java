package org.example.Aplicacion;

import org.example.Entidades.Cliente;
import org.example.Repositorio.ClienteRepositorio;
import org.example.Repositorio.FacturaRepositorio;
import org.hibernate.Session;

import java.util.Scanner;

public class MEJ308 {
    private static Scanner teclado = new Scanner(System.in);
    private Session session;
    private ClienteRepositorio clienteRepositorio;
    private FacturaRepositorio facturaRepositorio;

    public MEJ308(ClienteRepositorio clienteRepositorio, FacturaRepositorio facturaRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
        this.facturaRepositorio = facturaRepositorio;
    }

    public void mostrarMenu() {
        int opcion = -1;

        do {
            System.out.println("1. Mostrar todas las facturas\n" +
                    "2. Añadir factura\n" +
                    "3. Modificar factura\n" +
                    "4. Borrar factura\n" +
                    "5. Salir");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    facturaRepositorio.mostrarFacturas();
                    break;
                case 2:
                    int idCliente = pedirInt("Introduzca el ID del cliente: ");
                    Cliente cliente = clienteRepositorio.getClientePorId(idCliente);
                    facturaRepositorio.addFactura(cliente);
                    break;
                case 3:
                    facturaRepositorio.modificarFactura();
                    break;
                case 4:
                    facturaRepositorio.borrarFactura();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error. Solo puedes elegir entre las opciones (1-5)");
            }
        }while (opcion != 5);
    }

    private static int pedirInt(String string) {
        System.out.println(string);
        return teclado.nextInt();
    }
}
