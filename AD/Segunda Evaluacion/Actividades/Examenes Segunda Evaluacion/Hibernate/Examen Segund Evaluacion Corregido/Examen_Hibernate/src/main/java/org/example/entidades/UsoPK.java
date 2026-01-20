package org.example.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsoPK implements Serializable {
    @Column(nullable = false)
    private LocalTime hora;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private int taller_codigo;

    @Column(nullable = false)
    private int ciclo_codigo;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsoPK usoPK = (UsoPK) o;
        return taller_codigo == usoPK.taller_codigo && ciclo_codigo == usoPK.ciclo_codigo && Objects.equals(hora, usoPK.hora) && Objects.equals(fecha, usoPK.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hora, fecha, taller_codigo, ciclo_codigo);
    }
}
