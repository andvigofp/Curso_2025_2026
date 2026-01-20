package org.example.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "dptos")
public class Dpto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dpto_id")
    private int idDpto;


    private String nombre;

    private String localidad;

    @OneToMany(mappedBy = "dptoElement", cascade = CascadeType.ALL)
    private List<Emp> empts;

    public Dpto(String nombre, String localidad) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.empts = new ArrayList<>();
    }

    public void addEmps(Emp emp) {
        this.empts.add(emp);
        emp.setDptoElement(this);
    }

    @Override
    public String toString() {
        return "Dpto{" +
                "idDpto=" + idDpto +
                ", nombre='" + nombre + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}
