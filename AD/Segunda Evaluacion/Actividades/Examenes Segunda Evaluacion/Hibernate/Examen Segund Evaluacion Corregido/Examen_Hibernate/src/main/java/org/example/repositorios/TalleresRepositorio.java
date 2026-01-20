package org.example.repositorios;

import org.example.entidades.Ciclo;
import org.example.entidades.Instituto;
import org.example.entidades.Taller;
import org.example.entidades.Uso;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.mapping.Set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TalleresRepositorio {
    private Session session;

    public TalleresRepositorio(Session session) {
        this.session = session;
    }

    public void obtenerTalleresPorInstituto(int codigoInstituto) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            // 1️⃣ Obtener instituto

            Instituto instituto = session.createQuery("from Instituto i where i.codigo = :id", Instituto.class)
                    .setParameter("id", codigoInstituto)
                    .uniqueResult();

            if (instituto == null) {
                System.out.println("No existe el instituto con código: " + codigoInstituto);
                trx.rollback();
                return;
            }

            List<Ciclo> listaCiclos = instituto.getListaCiclo(); // suponiendo ManyToMany correcta

            if (listaCiclos.isEmpty()) {
                System.out.println("Este instituto no imparte ningún ciclo");
                trx.commit();
                return;
            }

            //2. Conjunto para evitar duplicados
            List<Taller> talleres = new ArrayList<>();

            // 3. Recorrer ciclos y usar sus usos
            for (Ciclo ciclo : listaCiclos) {
                for (Uso uso : ciclo.getListaUsoCiclo()) {
                    talleres.add(uso.getTaller());
                }
            }

            // 4. Mostrar resultados
            if (talleres.isEmpty()) {
                System.out.println("No hay talleres asignados a este instituto");
            } else {
                System.out.println("Talleres utilizados por el instituto " + instituto.getNombre() + ":");
                for (Taller t : talleres) {
                    System.out.println("- " + t.getNombre());
                }
            }

            trx.commit();

        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al obtener talleres: " + e.getMessage());
        }
    }

}
