package org.example.entidades;

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

@Table(name = "director")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String codigoCuerpo;

    @Column(nullable = false)
    private int edad;

    private String nombre;

    @OneToOne(mappedBy = "director", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Instituto instituto;


    public Director(String codigoCuerpo, int edad, String nombre) {
        this.codigoCuerpo = codigoCuerpo;
        this.edad = edad;
        this.nombre = nombre;
    }
}
