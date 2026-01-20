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

@Table(name = "ciclo")
public class Ciclo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;


    private String nombreCiclo;

    @OneToMany(mappedBy = "ciclo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Uso> listaUsoCiclo = new ArrayList<>();

    @ManyToMany(mappedBy = "listaCiclo", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Instituto> listaInstituto = new ArrayList<>();

    public Ciclo(String nombreCiclo) {
        this.nombreCiclo = nombreCiclo;
    }

   public void addUso(Uso uso) {
        listaUsoCiclo.add(uso);
        uso.setCiclo(this);
   }
}
