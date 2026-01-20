package org.example.Entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("2")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actriz extends Persona{
    private int numeroMejorActriz;

    public Actriz(String nombre, String dNI, int anhoNacimiento) {
        super(nombre, dNI, anhoNacimiento);
    }

    public Actriz(String nombre, String dNI, int numeroMejorActriz, int anhoNacimiento) {
        super(nombre, dNI, anhoNacimiento);
        this.numeroMejorActriz = numeroMejorActriz;
    }
}
