package org.example.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "alquiler")
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlquiler")
    private int idAlquiler;

    private LocalDate fecha;
    private boolean alquilado;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idLibro")
    private Libro libro;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    public Alquiler(LocalDate fecha, boolean alquilado) {
        super();
        this.fecha = fecha;
        this.alquilado = alquilado;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
        libro.addAlquiler(this);
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        cliente.addAlquiler(this);
    }


    @Override
    public String toString() {
        return "Alquiler{" +
                "idAlquiler=" + idAlquiler +
                ", fecha=" + fecha +
                ", alquilado=" + alquilado +
                '}';
    }
}
