package Ejercicios.EJ5;

import java.util.Scanner;

public class BookingManager {
    public static final int MAX_FRANJAS = 6;
    public static final int MIN_FRANJA = 1;

    private Piscina piscina;
    private String[][] reservasPorFranja;

    public BookingManager(Piscina piscina) {
        this.piscina = piscina;

        this.reservasPorFranja = new String[MAX_FRANJAS][piscina.getAforo()];
    }

    public boolean isDisponible(int franja) {
        for (String item: reservasPorFranja[franja -1]) {
            if (item == null)
                return true;
        }
        return false;
    }

    private boolean isValidDNI(String dni) {
        return dni.matches("\\d{8}[A-Za-z]");
    }

    private boolean yaReservado(String dni) {
        for (int i = 0; i < reservasPorFranja.length; i++) {
            for (int j = 0; j < reservasPorFranja[i].length; j++) {
                if (dni.equalsIgnoreCase(reservasPorFranja[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public void reservar() {
        Scanner teclado = new Scanner(System.in);
        int franja;

        do {
            franja = IOManager.leerEntero("Introduzca número de franja [1-6]:");
        } while (!IOManager.isInRange(MIN_FRANJA, MAX_FRANJAS, franja));

        if (!isDisponible(franja)) {
            System.out.println("No hay plazas disponibles en la franja " + franja);
            return;
        }

        String dni;
        do {
            dni = IOManager.leerCadena("Introduce un DNI válido (8 dígitos y una letra):");

            if (!isValidDNI(dni)) {
                System.out.println("DNI no válido. Debe tener 8 dígitos y una letra al final.");
                continue;
            }

            if (yaReservado(dni)) {
                System.out.println("Este DNI ya ha realizado una reserva en alguna franja.");
                return;
            }

            // Buscar hueco libre en la franja y guardar el DNI
            for (int i = 0; i < reservasPorFranja[franja - 1].length; i++) {
                if (reservasPorFranja[franja - 1][i] == null) {
                    reservasPorFranja[franja - 1][i] = dni;
                    System.out.println("Reserva realizada correctamente en la franja " + franja);
                    return;
                }
            }

        } while (true); // Saldrá solo si hay error de formato o reserva duplicada
    }

    public void mostrarReservas() {
        for (int i = 0; i < MAX_FRANJAS; i++) {
            System.out.println("Franja " + (i + 1) + ":");
            for (String dni : reservasPorFranja[i]) {
                if (dni != null) {
                    System.out.println(" - " + dni);
                }
            }
        }
    }

}
