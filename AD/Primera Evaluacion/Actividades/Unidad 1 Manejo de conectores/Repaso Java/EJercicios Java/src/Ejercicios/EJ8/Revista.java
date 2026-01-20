package Ejercicios.EJ8;

public class Revista extends Material{
    private int numero;

    public Revista(int id, String titulo, int numero) {
        super(id, titulo);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
}
