package org.example.Repositorio;

import org.example.Entidades.Dpto;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
