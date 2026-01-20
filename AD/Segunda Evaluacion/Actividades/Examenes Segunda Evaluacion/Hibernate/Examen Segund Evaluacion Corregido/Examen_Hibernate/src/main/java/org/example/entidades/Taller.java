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

@Table(name = "taller")
public class Taller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    private String nombre;

    @OneToMany(mappedBy = "taller", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Uso> listaUsoTaller = new ArrayList<>();

    public Taller(String nombre) {
        this.nombre = nombre;
    }

    public void adduso(Uso uso) {
        listaUsoTaller.add(uso);
        uso.setTaller(this);
    }
}
