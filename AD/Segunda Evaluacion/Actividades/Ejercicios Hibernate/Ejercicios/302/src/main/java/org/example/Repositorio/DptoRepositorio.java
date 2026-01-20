package org.example.Repositorio;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.example.Entidades.Dpto;
import org.example.Entidades.Emp;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DptoRepositorio implements Repositorio<Dpto> {

    private Session session;

    public DptoRepositorio(Session session) {
        super();
        this.session = session;
    }

    @Override
    public void guardar(Dpto dpto) {
        Transaction trx = this.session.beginTransaction();
        session.persist(dpto);
        trx.commit();
    }

    public void visualiazarDepartamento(int id) {
        Dpto dpto = (Dpto) session
                .createQuery("select d from Dpto d where d.idDpto = :id")
                .setParameter("id", id)
                .getSingleResult();

        System.out.println("Id: " + dpto.getIdDpto() + "\nNombre: " + dpto.getNombre() + "\nNombre: " + dpto.getNombre() + "\nLocalidad: " + dpto.getLocalidad());

        Query query = session.createQuery("select e from Emp e where e.dptoElement.idDpto = :id");
        query.setParameter("id", id);

        List<Emp> empleados = query.getResultList();

        for (Emp empleado : empleados) {
            System.out.println(empleado.toString());
        }

    }

    public void eliminarDepartamento(int id) {
        Transaction trx = this.session.beginTransaction();
        try {
            Dpto dpto = (Dpto) session.createQuery("select d from Dpto d where d.idDpto = :id")
                    .setParameter("id", id)
                    .getSingleResult();

            session.remove(dpto);

            trx.commit();
        } catch (NoResultException e) {
            System.out.println("No se encontró el departamento con esa id: " + id);
            trx.rollback();
        }catch (Exception e) {
            System.out.println("Error al eliminar departamento: " + e.toString());
            trx.rollback();
        }
    }
}
