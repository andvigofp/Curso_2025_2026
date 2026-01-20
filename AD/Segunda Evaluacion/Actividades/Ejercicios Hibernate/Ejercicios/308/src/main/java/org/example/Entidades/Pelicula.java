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

@Table(name = "peliculas")
public class Pelicula {
    @Id
    @Column(name = "idPelicula")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPelicula;

    private String titulo;
    private int anhoPublicacion;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idGenero")
    private Genero genero;

    @ManyToMany(mappedBy = "listaPeliculas")
    private List<Persona> listaPersonas;

    public Pelicula(String titulo, int anhoPublicacion, Genero genero) {
        super();
        this.titulo = titulo;
        this.anhoPublicacion = anhoPublicacion;
        this.genero = genero;
        this.listaPersonas = new ArrayList<Persona>();
        genero.addPelicula(this);
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "idPelicula=" + idPelicula +
                ", titulo='" + titulo + '\'' +
                ", anhoPublicacion=" + anhoPublicacion +
                '}';
    }
}
