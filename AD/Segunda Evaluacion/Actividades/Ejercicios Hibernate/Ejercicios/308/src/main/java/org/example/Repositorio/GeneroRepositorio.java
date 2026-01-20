package org.example.Repositorio;

import org.example.Entidades.Genero;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class GeneroRepositorio implements Repositorio<Genero>{
    private Session session;

    public GeneroRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Genero genero) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();
            session.persist(genero);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al guardar: " + e.toString());
        }
    }

    @Override
    public void eliminar(Genero genero) {
        session.remove(genero);
    }

    @Override
    public Genero buscarPorId(int id) {
        Transaction trx = null;
        Genero genero = null;

        try {
            trx = session.beginTransaction();
            Query query = session.createQuery("select g from Genero g where g.idGenero=:idGenero");
            query.setParameter("idGenero", id);
            genero = (Genero) query.getSingleResult();

        }catch (Exception e) {
            System.out.println("Error al recuperar los datos de genero: " + e.toString());
        }
        trx.commit();

        return genero;
    }

    @Override
    public List<Genero> buscarPorCadena(String cadena) {
        Transaction trx = null;
        List<Genero> listaGenero = null;

        try {
            trx = session.beginTransaction();
            Query query = session.createQuery("select g from Genero g where g.nombre=:cadena");
            query.setParameter("cadena", cadena);

            listaGenero = (List<Genero>) query.getResultList();
        }catch (Exception e) {
            System.out.println("Error al recuperar los datos de genero: " + e.toString());
        }
        trx.commit();
        return listaGenero;
    }

    @Override
    public void actualizar(Genero genero) {
        session.remove(genero);
    }

    public List<Genero> buscarTodo() {
        Transaction trx = null;
        List<Genero> listaGenero = null;

        try {
            trx = session.beginTransaction();
            Query query = session.createQuery("select g from Genero g");

            listaGenero = (List<Genero>) query.getResultList();
        }catch (Exception e) {
            System.out.println("Error al recuperar los datos de genero: " + e.toString());
        }
        trx.commit();
        return listaGenero;
    }
}