package org.example.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "uso")
public class Uso {
    @EmbeddedId
    private UsoPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("taller_codigo")
    @JoinColumn(name = "taller_codigo")
    private Taller taller;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ciclo_codigo")
    @JoinColumn(name = "ciclo_codigo")
    private Ciclo ciclo;
}
