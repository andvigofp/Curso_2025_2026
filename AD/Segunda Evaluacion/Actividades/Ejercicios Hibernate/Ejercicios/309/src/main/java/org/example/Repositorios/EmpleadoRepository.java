package org.example.Repositorios;

import org.example.Entidades.Empleado;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmpleadoRepository implements Repositorio<Empleado> {
    private Session session;

    public EmpleadoRepository(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Empleado empleado) {
        Transaction trx = session.beginTransaction();
        session.persist(empleado);
        trx.commit();
    }

    @Override
    public void actualizar(Empleado empleado) {
        Transaction trx = session.beginTransaction();
        session.merge(empleado);
        trx.commit();
    }

    @Override
    public void eliminar(Empleado empleado) {
        Transaction trx = session.beginTransaction();
        session.remove(empleado);
        trx.commit();
    }

    public Empleado findById(String dni) {
        return session.find(Empleado.class, dni);
    }

    public List<Empleado> findAll() {
        return session.createQuery("from Empleado", Empleado.class).list();
    }
}
