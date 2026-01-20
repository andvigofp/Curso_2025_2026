package Ejercicios.Ej311;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Se pide, desarrollar una aplicación Java que permita realizar lo siguiente:
 *
 * Crear y borrar una cuenta bancaria de un cliente.
 * Ingresar y retirar una determinada cantidad de dinero en una cuenta bancaria.
 * Realizar una transferencia bancaria entre dos cuentas de un cliente.
 * Mostrar las cuentas bancarias de un cliente con un dni determinado.
 */
public class Ej311 {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);

        GestorBanco gestorBanco = new GestorBanco();

        MEj311 metodos = new MEj311();

        final String menu = "1. CREAR NUEVA CUENTA ."
                + "\n2. BORRAR CUENTA."
                + "\n3. INGRESAR DINERO."
                + "\n4. RETIRAR DINERO."
                + "\n5. REALIZAR TRANSFERENCIA ENTRE CUENTAS."
                + "\n6. MOSTRAR CUENTAS"
                + "\n7. SALIR";


        metodos.solicitarDNI(teclado, gestorBanco);

        int opcion = -1;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    metodos.crearCuenta(teclado, gestorBanco);
                    break;
                case 2:
                    metodos.borrarCuenta(gestorBanco);
                    break;
                case 3:
                    metodos.ingresarDinero(teclado,gestorBanco);
                    break;
                case 4:
                    metodos.retirarDinero(teclado,gestorBanco);
                    break;
                case 5:
                    metodos.realizarTransferencia(teclado,gestorBanco);
                    break;
                case 6:
                    metodos.mostrarCuentas(gestorBanco);
                    break;
                case 7:
                    System.out.println("Fin del programa");
                    teclado.close();
                    return;
                default:
                    System.out.println("Opción no válida, por favor elija una opción del menú entre 1-7");
            }
        }while (opcion !=7);
    }
}
