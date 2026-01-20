package org.example.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_persona", discriminatorType = DiscriminatorType.INTEGER)
@Data
@NoArgsConstructor
@AllArgsConstructor

public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersona;

    private String nombre;
    private int anhoNacimiento;
    private String DNI;

    @ManyToMany
    @JoinTable(name = "actores_peliculas",
            joinColumns = @JoinColumn(name = "idPersona"),
            inverseJoinColumns = @JoinColumn(name = "idPelicula"))
    List<Pelicula> listaPeliculas = new ArrayList<Pelicula>();

    public Persona(String nombre, String dNI, int anhoNacimiento) {
        super();
        this.nombre = nombre;
        this.DNI = dNI;
        this.anhoNacimiento = anhoNacimiento;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", anhoNacimiento=" + anhoNacimiento +
                ", DNI='" + DNI + '\'' +
                '}';
    }
}
