package Ejercicios.Ejercicio402;

public class Autor {
    private String nombre;
    private String apellidos;

    @Override
    public String toString() {
        return "<autor>" +
                "<nombre>" + nombre + "</nombre>" +
                "<apellidos>" + apellidos + "</apellidos>" +
                "</autor>";
    }

    public Autor generarAleatorio() {
        this.nombre = "Nombre" + (int) (Math.random() * 20);
        this.apellidos = "Apellido" + (int) (Math.random() * 20) +
                " Apellido" + (int) (Math.random() * 20);
        return this;
    }
}
