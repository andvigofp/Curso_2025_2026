package Ejercicios.EJ8;

public class Material {
    private int id;
    private String titulo;
    private boolean disponible;

    public Material(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.disponible = true;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void prestar() {
        if (disponible) {
            disponible = false;
            System.out.println(titulo + " ha sido prestado.");
        }else {
            System.out.println(titulo + " no está disponible en este momento.");
        }
    }

    public void devolver() {
        if (!disponible) {
            disponible = true;
            System.out.println(titulo + " ha sido devuelto.");
        }else {
            System.out.println(titulo + " ya está disponible.");
        }
    }
}
