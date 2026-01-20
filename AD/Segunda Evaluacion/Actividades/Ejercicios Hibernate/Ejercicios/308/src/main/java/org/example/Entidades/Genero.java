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

@Table(name = "genero")
public class Genero {
    @Id
    @Column(name = "idGenero")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGenero;

    private String nombre;

    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL)
    private List<Pelicula> lisTaPeliculas = new ArrayList<>();

    public Genero(String nombre) {
        super();
        this.nombre = nombre;
    }

    public void addPelicula(Pelicula pelicula) {
        this.lisTaPeliculas.add(pelicula);
    }

    @Override
    public String toString() {
        return "Genero{" +
                "idGenero=" + idGenero +
                ", nombre='" + nombre + '\'' +
                ", lisTaPeliculas=" + lisTaPeliculas +
                '}';
    }
}
