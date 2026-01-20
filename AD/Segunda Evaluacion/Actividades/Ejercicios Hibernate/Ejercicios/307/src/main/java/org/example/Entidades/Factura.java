package org.example.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFactura")
    private int idFactura;

    private String descripcion;
    private double precio;

    @Column(name = "fecha", columnDefinition = "Date")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;

    public Factura(String descripcion, double precio, Date fecha) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = fecha;
    }


    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", fecha=" + fecha +
                '}';
    }
}
