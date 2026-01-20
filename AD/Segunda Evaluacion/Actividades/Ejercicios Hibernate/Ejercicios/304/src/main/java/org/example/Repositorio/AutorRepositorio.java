package org.example.Repositorio;

import org.hibernate.query.Query;
import org.example.Entidades.Autores;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AutorRepositorio implements Repositorio<Autores>{
    private Session session;

    public AutorRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void insertarUno(Autores autores) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();
            session.persist(autores);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al insertar: " + e.toString());
        }
    }

    @Override
    public void borrar(Autores autores) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();
            session.remove(autores);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al borrar: " + e.toString());
            if (trx != null) trx.rollback();
        }
    }

    @Override
    public List<Autores> enocntrarTodos() {
        Query query = session.createQuery("select a from Autores a");
        List<Autores> listaAutores = query.getResultList();

        return listaAutores;
    }

    @Override
    public Autores encontrarUnoPorString(String dni) {
        Query<Autores> query = session.createQuery(
                "select a from Autores a where a.dniAutor = :dni",
                Autores.class
        );

        query.setParameter("dni", dni);

        return query.uniqueResult();
    }

    @Override
    public void actualizar(Autores autores) {
        Transaction trx  = null;

        try {
            trx = session.beginTransaction();
            session.merge(autores);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al actualizar: " + e.toString());
            if (trx != null) trx.rollback();
        }
    }
}
