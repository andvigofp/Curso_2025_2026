package org.example.Repositorios;

import org.example.Entidades.Temporal;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TemporalRepository implements Repositorio<Temporal> {
    private Session session;

    public TemporalRepository(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Temporal temporal) {
        Transaction trx = session.beginTransaction();
        session.persist(temporal);
        trx.commit();
    }

    @Override
    public void actualizar(Temporal temporal) {
        Transaction trx = session.beginTransaction();
        session.merge(temporal);
        trx.commit();
    }

    @Override
    public void eliminar(Temporal temporal) {
        Transaction trx = session.beginTransaction();
        session.remove(temporal);
        trx.commit();
    }

    public Temporal findById(String dni) {
        return session.find(Temporal.class, dni);
    }

    public List<Temporal> findAll() {
        return session.createQuery("from Temporal", Temporal.class).list();
    }
}
