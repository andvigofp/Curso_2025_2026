package org.example.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLibro")
    private int idLibro;

    private String codigo;
    private String titulo;
    private String autores;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Alquiler> alquiler;

    public void addAlquiler(Alquiler alquiler) {
        this.alquiler.add(alquiler);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", codigo='" + codigo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autores='" + autores + '\'' +
                '}';
    }
}
