package Ejercicios.Ej310;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;

    public Producto(int id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

   @Override
   public String toString() {
        return String.format("Nombre: %s Precio %.2f%n", nombre, precio);
   }
}
