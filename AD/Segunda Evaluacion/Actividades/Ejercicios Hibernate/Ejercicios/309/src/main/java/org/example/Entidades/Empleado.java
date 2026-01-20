package org.example.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empleados")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Empleado implements Serializable {

    @Id
    @Column(name = "DNI")
    private String dni;

    private String nombre;
    private String telefono;

    @Column(name = "porcentaje_retencion")
    private float porcentajeRetencion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cif_empresa")
    private Empresa empresa;

    // Las ventas pertenecen a Empleado, NO a Temporal
    @OneToMany(mappedBy = "empleado")
    private Set<Venta> ventas;

    public Empleado(String dni, String nombre, String telefono, float porcentajeRetencion) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.porcentajeRetencion = porcentajeRetencion;
    }

    // Método abstracto
    public abstract void calcularNomina();
}