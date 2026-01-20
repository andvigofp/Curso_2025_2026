package org.example.Entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("1")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Actor extends Persona{
    private int numeroOscars;

    public Actor(String nombre, String dNI, int anhoNacimiento) {
        super(nombre, dNI, anhoNacimiento);
    }

    public Actor(String nombre, String dNI, int numeroOscars, int anhoNacimiento) {
        super(nombre, dNI, anhoNacimiento);
        this.numeroOscars = numeroOscars;
    }
}
