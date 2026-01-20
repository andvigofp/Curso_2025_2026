package org.example.repositorios;

import org.example.entidades.Director;
import org.example.entidades.Instituto;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DirectorRepositorio {
    private Session session;

    public DirectorRepositorio(Session session) {
        this.session = session;
    }

    public void crearDirector(String codigoCuerpo, int edad, String nombre) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            Director director = new Director(codigoCuerpo, edad, nombre);

            session.persist(director);

            trx.commit();
            System.out.println("Se a creado el director exitosamente: " + director.getId());

        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al crear director: " + e.getMessage());
        }
    }


    public void AsignarDirectorInstituto(int codigoDirector, int codigoInstituto) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            Director director = session.createQuery("from Director d where d.id = :id", Director.class)
                    .setParameter("id", codigoDirector)
                    .uniqueResult();

            Instituto instituto = session.createQuery("from Instituto i where i.codigo = :id", Instituto.class)
                    .setParameter("id", codigoInstituto)
                    .uniqueResult();

            if (director == null) {
                System.out.println("No existe ese id del director: " + codigoDirector);
                trx.rollback();
                return;
            }

            if (instituto == null) {
                System.out.println("No existe ese id del instituto: " + codigoInstituto);
                trx.rollback();
                return;
            }

            if (instituto.getDirector() != null) {
                System.out.println("El instituto ya tiene director asignado");
                trx.rollback();
                return;
            }

            if (director.getInstituto() != null) {
                System.out.println("El director ya está asignado a otro instituto");
                trx.rollback();
                return;
            }


           instituto.setDirector(director);
           director.setInstituto(instituto);


            trx.commit();

            System.out.println("Se a añadido el director al instituto: ");
        }catch (Exception e) {
            if (trx !=null)trx.rollback();
            System.out.println("Error al asignar Director al instituto: " + e.getMessage());
        }
    }
}
