package Ejercicios.Ej310;

public class Cliente {
    private String dni;
    private String nombre;

    public Cliente(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return String.format("DNI: %s%nNombre: %s%n", dni, nombre);
    }
}
