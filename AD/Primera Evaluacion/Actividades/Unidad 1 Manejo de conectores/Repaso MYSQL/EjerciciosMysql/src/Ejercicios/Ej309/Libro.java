package Ejercicios.Ej309;

public class Libro {
    private int idLibro;
    private String codigo;
    private String titulo;
    private String autores;
    private int anhio;

    public Libro() {
    }

    public Libro(int idLibro, String codigo, String titulo, String autores, int anhio) {
        this.idLibro = idLibro;
        this.codigo = codigo;
        this.titulo = titulo;
        this.autores = autores;
        this.anhio = anhio;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public int getAnhio() {
        return anhio;
    }

    public void setAnhio(int anhio) {
        this.anhio = anhio;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", codigo='" + codigo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autores='" + autores + '\'' +
                ", anhio=" + anhio +
                '}';
    }
}
