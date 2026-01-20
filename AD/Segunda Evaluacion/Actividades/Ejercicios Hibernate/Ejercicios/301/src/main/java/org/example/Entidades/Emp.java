package org.example.Entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor

@Table(name = "emp")
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emp")
    private int idEmp;

    @NonNull
    private String nombre;

    @NonNull
    private String puesto;

    @NonNull
    private double sueldo;

    @NonNull
    private int edad;

    @NonNull
    @Column(name = "dni")
    private String DNI;

    @NonNull
    private boolean esJefe;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "dpto_id")
    private Dpto dptoElement;

}
