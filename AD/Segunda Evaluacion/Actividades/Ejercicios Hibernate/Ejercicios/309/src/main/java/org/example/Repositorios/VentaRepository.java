package org.example.Repositorios;

import org.example.Entidades.Venta;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VentaRepository implements Repositorio<Venta> {
    private Session session;

    public VentaRepository(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Venta venta) {
        Transaction trx = session.beginTransaction();
        session.persist(venta);
        trx.commit();
    }

    @Override
    public void actualizar(Venta venta) {
        Transaction trx = session.beginTransaction();
        session.merge(venta);
        trx.commit();
    }

    @Override
    public void eliminar(Venta venta) {
        Transaction trx = session.beginTransaction();
        session.remove(venta);
        trx.commit();
    }

    public Venta findById(Object[] id) {
        // Como Venta tiene PK compuesta, pasamos un array {fecha, hora, dni, codigo}
        return session.find(Venta.class, id);
    }

    public List<Venta> findAll() {
        return session.createQuery("from Venta", Venta.class).list();
    }
}
