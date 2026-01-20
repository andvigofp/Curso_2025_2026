package org.example.Repositorios;

import org.example.Entidades.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductoRepository implements Repositorio<Producto> {
    private Session session;

    public ProductoRepository(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Producto producto) {
        Transaction trx = session.beginTransaction();
        session.persist(producto);
        trx.commit();
    }

    @Override
    public void actualizar(Producto producto) {
        Transaction trx = session.beginTransaction();
        session.merge(producto);
        trx.commit();
    }

    @Override
    public void eliminar(Producto producto) {
        Transaction trx = session.beginTransaction();
        session.remove(producto);
        trx.commit();
    }

    public Producto findById(String codigo) {
        return session.find(Producto.class, codigo);
    }

    public List<Producto> findAll() {
        return session.createQuery("from Producto", Producto.class).list();
    }
}
