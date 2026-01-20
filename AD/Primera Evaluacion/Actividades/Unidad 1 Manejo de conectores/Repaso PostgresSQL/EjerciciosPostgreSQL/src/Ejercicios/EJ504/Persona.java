package Ejercicios.EJ504;

public class Persona {
    private String nombre;
    private int edad;

    public Persona(String info) {
        String[] info_split = info.substring(1, info.length() -1).split(",");
        this.nombre = info_split[0];
        this.edad = Integer.parseInt(info_split[1]);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
