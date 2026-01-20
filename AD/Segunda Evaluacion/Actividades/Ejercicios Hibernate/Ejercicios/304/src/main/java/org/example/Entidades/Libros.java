package org.example.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "Libros",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"Titulo"},
                name = "tituloUniqueConstraint"
        )
)
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLibro")
    private int id;

    @Column(name = "Titulo")
    private String titulo;

    @Column(name = "Precio")
    private double precio;

    @ManyToMany(mappedBy = "listaLibros")
    private List<Autores> listaAutores = new ArrayList<>();

    public Libros(String titulo, double precio) {
        this.titulo = titulo;
        this.precio = precio;
    }

    public void addAutor(Autores autor) {
        this.listaAutores.add(autor);
        autor.getListaLibros().add(this);
    }

    @Override
    public String toString() {
        return "Libros{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", precio=" + precio +
                '}';
    }
}
