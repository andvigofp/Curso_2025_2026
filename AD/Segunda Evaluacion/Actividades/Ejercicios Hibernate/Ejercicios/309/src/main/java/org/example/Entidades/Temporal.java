package org.example.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empleados_temporales")
@PrimaryKeyJoinColumn(name = "DNI_EMPLEADO")
public class Temporal extends Empleado implements Serializable {

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "pago_dia")
    private float pagoDia;

    private float suplemento;
    private float sueldo;

    public Temporal(String dni, String nombre, String telefono, float porcentajeRetencion,
                    Date fechaInicio, Date fechaFin, float pagoDia) {
        super(dni, nombre, telefono, porcentajeRetencion);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.pagoDia = pagoDia;
    }

    @Override
    public void calcularNomina() {
        long dias = (fechaFin.getTime() - fechaInicio.getTime()) / 86400000;
        sueldo = pagoDia * dias * (1 - getPorcentajeRetencion()) + suplemento;
    }
}