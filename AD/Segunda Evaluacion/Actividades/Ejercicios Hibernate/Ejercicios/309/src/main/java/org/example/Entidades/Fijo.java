package org.example.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empleados_fijos")
@PrimaryKeyJoinColumn(name = "DNI_EMPLEADO")
public class Fijo extends Empleado implements Serializable {

    @Column(name = "salario_base")
    private int salarioBase;

    private int trienios;
    private float sueldo;

    public Fijo(String dni, String nombre, String telefono, float porcentajeRetencion,
                int salarioBase, int trienios) {

        super(dni, nombre, telefono, porcentajeRetencion);
        this.salarioBase = salarioBase;
        this.trienios = trienios;
    }

    @Override
    public void calcularNomina() {
        sueldo = (salarioBase + trienios) * (1 - getPorcentajeRetencion());
    }
}