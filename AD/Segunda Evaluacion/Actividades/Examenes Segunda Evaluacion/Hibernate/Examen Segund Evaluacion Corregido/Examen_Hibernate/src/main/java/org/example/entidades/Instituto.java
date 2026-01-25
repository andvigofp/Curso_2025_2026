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

@Table(name = "instituto")
public class Instituto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    private String nombre;

    private String tf;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "director")
    private Director director;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ies_ciclos",
            joinColumns = @JoinColumn(name = "cod_instituto", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "cod_ciclo")
    )
    private List<Ciclo> listaCiclo = new ArrayList<>();


    public Instituto(String nombre, String tf) {
        this.nombre = nombre;
        this.tf = tf;
    }

    public void addCiclo(Ciclo ciclo) {
        listaCiclo.add(ciclo);
        ciclo.getListaInstituto().add(this);
    }
}
