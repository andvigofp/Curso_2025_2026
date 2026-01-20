package org.example.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ventas")
public class Venta implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "codigo_articulo")
    private Producto producto;

    @Id
    @ManyToOne
    @JoinColumn(name = "DNI_EMPLEADO")
    private Empleado empleado;

    @Id
    @Column(name = "fecha_venta")
    private Date fechaVenta;

    @Id
    @Column(name = "hora")
    private Time hora;

    @Column(name = "numero_unidades")
    private int numeroUnidades;

    private float importe;

    public Venta(Producto p, Empleado e, int unidades) {
        this.producto = p;
        this.empleado = e;
        this.numeroUnidades = unidades;

        this.fechaVenta = new Date(System.currentTimeMillis());
        this.hora = new Time(System.currentTimeMillis());

        this.importe = unidades * p.getPrecioUnitario();
    }
}