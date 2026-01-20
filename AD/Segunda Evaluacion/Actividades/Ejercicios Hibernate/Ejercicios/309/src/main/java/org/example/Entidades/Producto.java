package org.example.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @Column(name = "codigo")
    private String codigo;

    @Column(name = "stock_almacen")
    private int stockActualAlmacen;

    @Column(name = "stock_minimo")
    private final int STOCK_MINIMO = 30;

    @Column(name = "precio_unitario")
    private float precioUnitario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cif_empresa")
    private Empresa empresa;

    @OneToMany(mappedBy = "producto")
    private Set<Venta> ventas;

    public Producto(String codigo, int stockAlmacen, float precioUnitario) {
        this.codigo = codigo;
        this.stockActualAlmacen = stockAlmacen;
        this.precioUnitario = precioUnitario;
    }
}