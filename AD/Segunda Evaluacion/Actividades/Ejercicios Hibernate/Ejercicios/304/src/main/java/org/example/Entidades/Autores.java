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
@Table(name = "Autores")
public class Autores {

    @Id
    @Column(name = "DniAutor")
    private String dniAutor;  // NO AUTOINCREMENT porque es texto

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Nacionalidad")
    private String nacionalidad;

    // Relación muchos a muchos con Libros
    @ManyToMany
    @JoinTable(
            name = "Libros_Autores",
            joinColumns = @JoinColumn(name = "DniAutor"),
            inverseJoinColumns = @JoinColumn(name = "idLibro")
    )
    private List<Libros> listaLibros = new ArrayList<>();

    // Relación uno a uno INVERSE SIDE
    @OneToOne(mappedBy = "autor")
    private Telefono telefono;

    public Autores(String dniAutor, String nombre, String nacionalidad) {
        this.dniAutor = dniAutor;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public void addLibro(Libros libro) {
        this.listaLibros.add(libro);
        libro.getListaAutores().add(this);
    }

    @Override
    public String toString() {
        return "Autores{" +
                "dniAutor='" + dniAutor + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}