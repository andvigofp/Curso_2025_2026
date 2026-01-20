package org.example.Repositorios;

import org.example.Entidades.Fijo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FijoRepository implements Repositorio<Fijo> {
    private Session session;

    public FijoRepository(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Fijo fijo) {
        Transaction trx = session.beginTransaction();
        session.persist(fijo);
        trx.commit();
    }

    @Override
    public void actualizar(Fijo fijo) {
        Transaction trx = session.beginTransaction();
        session.merge(fijo);
        trx.commit();
    }

    @Override
    public void eliminar(Fijo fijo) {
        Transaction trx = session.beginTransaction();
        session.remove(fijo);
        trx.commit();
    }

    public Fijo findById(String dni) {
        return session.find(Fijo.class, dni);
    }

    public List<Fijo> findAll() {
        return session.createQuery("from Fijo", Fijo.class).list();
    }
}
