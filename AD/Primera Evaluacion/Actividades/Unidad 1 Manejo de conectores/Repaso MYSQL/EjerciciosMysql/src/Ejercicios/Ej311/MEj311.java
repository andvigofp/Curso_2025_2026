package Ejercicios.Ej311;

import java.sql.SQLException;
import java.util.Scanner;

public class MEj311 {
    private static String DNI;
    private static int id;

    // Método para solicitar el DNI
    public void solicitarDNI( Scanner teclado, GestorBanco gestorBanco) throws SQLException {

        System.out.println("Introduzca su DNI: ");
        do {
            DNI = teclado.next();
        } while (DNI.isEmpty());
        id = gestorBanco.getId(DNI);
    }

    // Método para pedir al usuario para crear una cuenta bancaria
    public void crearCuenta(Scanner teclado, GestorBanco gestorBanco) throws SQLException {
        System.out.println("Con cuánto dinero quieres crear la cuenta?");
        float dinero = teclado.nextFloat();
        gestorBanco.crearCuenta(id, dinero);
        System.out.println("Cuenta Creada correctamente");
    }

    // Método para borrar una cuenta bancaria
    public void borrarCuenta(GestorBanco gestorBanco) throws SQLException {
        gestorBanco.borrarCuenta(id);
        System.out.println("Cuenta Borrada satisfactoriamente");
    }

    // Método para pedir por teclado, para ingresar Dinero a una cuenta bancaria
    public void ingresarDinero(Scanner teclado, GestorBanco gestorBanco) throws SQLException {
        System.out.println("¿Cuánto dinero quieres ingresar?");
        float dinero = teclado.nextFloat();
        System.out.println("¿En qué cuenta quieres ingresarlo?");
        int cuentaIngresado = teclado.nextInt();
        gestorBanco.ingreso(DNI, cuentaIngresado, dinero);
        System.out.println("Dinero Ingresado correctamente");
    }

    // Método para pedir por teclado, para retirar dinero a una cuenta bancaria
    public void retirarDinero(Scanner teclado, GestorBanco gestorBanco) throws SQLException {
        System.out.println("¿Cuánto dinero quieres retirar?");
        float dinero = teclado.nextFloat();
        System.out.println("¿De qué cuenta quieres retirarlo?");
        int cuentaRetiro = teclado.nextInt();
        gestorBanco.retiro(DNI, cuentaRetiro, dinero);
        System.out.println("Dinero Retirado correctamente");
    }

    // Método para pedir por teclado, para realizar una transferencia de dinero a una cuenta bancaria a otra
    public void realizarTransferencia(Scanner teclado, GestorBanco gestorBanco) throws SQLException {
        System.out.println("¿Cuánto dinero quieres transferir?");
        float dinero = teclado.nextFloat();
        System.out.println("¿Cuál es la cuenta de la que quieres sacar el dinero?");
        int cuentaRetiro = teclado.nextInt();
        System.out.println("¿En qué cuenta quieres ingresarlo?");
        int cuentaIngreso = teclado.nextInt();
        gestorBanco.transferencia(DNI, cuentaRetiro, cuentaIngreso, dinero);
        System.out.println("Dinero Transferido correctamente");
    }

    // Método para mostrar las cuentas bancarias
    public void mostrarCuentas(GestorBanco gestorBanco) throws SQLException {
        gestorBanco.mostrarCuentasBancarias(DNI);
    }
}

