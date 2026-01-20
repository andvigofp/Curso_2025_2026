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

@Table(name = "telefono")
public class Telefono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTelefono")
    private int idTelefono;

    private String descripcion;
    private String numero;


    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;


    public Telefono(String descripcion, String numero) {
        this.descripcion = descripcion;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Telefono{" +
                "idTelefono=" + idTelefono +
                ", descripcion='" + descripcion + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
