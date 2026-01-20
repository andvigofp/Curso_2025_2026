package org.example.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private int idCliente;

    @Column(name = "DNI", columnDefinition = "char")
    private String dni;

    private String nombre;
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Alquiler> listaAlquiler;

    public Cliente(String dni, String nombre, String email) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
    }

    public void addAlquiler(Alquiler alquiler) {
        this.listaAlquiler.add(alquiler);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}