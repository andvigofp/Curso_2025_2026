package org.example.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Telefonos")
public class Telefono implements Serializable {

    @Id
    @Column(name = "DniAutor")
    private String dniAutor;   // PK = FK

    @OneToOne
    @JoinColumn(name = "DniAutor", referencedColumnName = "DniAutor")
    private Autores autor;

    @Column(name = "NumeroTf")
    private String numeroTf;

    public Telefono(Autores autor, String numeroTf) {
        this.autor = autor;
        this.numeroTf = numeroTf;
        this.dniAutor = autor.getDniAutor();
    }
}