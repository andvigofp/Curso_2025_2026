package org.example.Repositorio;

import jakarta.persistence.Query;
import org.example.Entidades.Libros;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LibroRepositorio implements Repositorio<Libros> {
    private Session session;

    public LibroRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void insertarUno(Libros libros) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();
            session.persist(libros);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al insertar: " + e.toString());
        }
    }

    @Override
    public void borrar(Libros libros) {
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.remove(libros);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al borrar: " + e.toString());
            if (trx != null) trx.rollback();
        }

    }

    @Override
    public List<Libros> enocntrarTodos() {
        Query query = session.createQuery("select l from Libros l");
        List<Libros> listaLibros = query.getResultList();

        return listaLibros;
    }

    @Override
    public Libros encontrarUnoPorString(String nombre) {
        Query query = session.createQuery("select l from Libros l where l.titulo = :nombre");

        query.setParameter("nombre", nombre);

        return (Libros) query.getSingleResult();
    }

    @Override
    public void actualizar(Libros libros) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();
            session.merge(libros);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al actualizar: " + e.toString());
            if (trx != null) trx.rollback();
        }
    }
}
