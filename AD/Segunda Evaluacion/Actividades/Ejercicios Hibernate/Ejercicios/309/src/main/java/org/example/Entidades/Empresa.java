package org.example.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empresa")
@NamedQueries({
        @NamedQuery(name="empleadosEmpresa", query="select e from Empleado e where e.empresa.cif=:cif"),
        @NamedQuery(name="productosEmpresa", query="select p from Producto p where p.empresa.cif=:cif"),
        @NamedQuery(name="empresas", query="from Empresa")
})
public class Empresa implements Serializable {

    @Id
    @Column(name = "CIF", nullable = false)
    private String cif;

    private String nombre;
    private String direccion;
    private String telefono;

    @OneToMany(mappedBy = "empresa")
    private Set<Producto> productos;

    @OneToMany(mappedBy = "empresa")
    private Set<Empleado> empleados;

    public Empresa(String cif) {
        this.cif = cif;
    }

    public Empresa(String cif, String nombre, String direccion, String telefono) {
        this.cif = cif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

}