package org.example.repositorios;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.entidades.Ciclo;
import org.example.entidades.Director;
import org.example.entidades.Instituto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class InstitutoRepositorio {
    private Session session;

    public InstitutoRepositorio(Session session) {
        this.session = session;
    }

    public void crearInstituto(String nombre, String telefono) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            Instituto instituto = session.createQuery("from Instituto i where i.nombre = :nombre", Instituto.class)
                    .setParameter("nombre", nombre)
                    .uniqueResult();


            if (instituto !=null) {
                System.out.println("Ya existe ese nombre " + nombre);
                return;
            }



            instituto = new Instituto(nombre, telefono);


            session.persist(instituto);

            trx.commit();

            System.out.println("Se a creado el insituto exitosamente: ");

        }catch (Exception e) {
            System.out.println("Error al crear instituto" + e.getMessage());
        }
    }

    public void eliminarInstituto(int id) {
        Transaction trx= null;

        try {
            trx = session.beginTransaction();



            Instituto instituto = session.createQuery("from Instituto i where i.codigo = :id", Instituto.class)
                    .setParameter("id", id)
                    .uniqueResult();

            if (instituto ==null) {
                System.out.println("No existe esa id del instituto: " + id);
                return;
            }

            session.remove(instituto);

            session.flush();

            trx.commit();
            System.out.println("Se a elminado el instituto: ");
            System.out.println("ID: " + instituto.getCodigo());
        }catch (Exception e) {
            if (trx !=null)trx.commit();
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    public void modificarInstituto(int id, String tft) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            Instituto instituto = session.createQuery("from Instituto i where i.codigo = :id", Instituto.class)
                    .setParameter("id", id)
                    .uniqueResult();

            if (instituto ==null) {
                System.out.println("No existe la id del instituto: " + id);
                trx.rollback();
                return;
            }

            instituto.setTf(tft);

            trx.commit();

            System.out.println("Se a actualizado exitosamente el télefono: " + instituto.getTf());
        }catch (Exception e) {
            System.out.println("Error al modificar el instituto: " + e.getMessage());
        }

    }

    public void obtnerNombreTelefonoCodigoDirector(int id) {
        try {
            Director director = session.createQuery("from Director d where d.id = :id", Director.class)
                    .setParameter("id", id)
                    .uniqueResult();

            if (director == null) {
                System.out.println("No existe ese id del director: " + id);
            }

            Instituto instituto = director.getInstituto();

            System.out.println("Nombre: " + instituto.getNombre());
            System.out.println("Teléfono: " + instituto.getTf());
        }catch (Exception e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
}
