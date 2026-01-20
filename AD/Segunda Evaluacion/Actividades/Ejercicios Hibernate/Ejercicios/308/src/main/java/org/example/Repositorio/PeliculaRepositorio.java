package org.example.Repositorio;

import org.example.Entidades.Pelicula;
import org.example.Entidades.Persona;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PeliculaRepositorio implements Repositorio<Pelicula> {
    private Session session;

    public PeliculaRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Pelicula pelicula) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();
            session.persist(pelicula);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al guardar: " + e.toString());
        }
    }

    @Override
    public void eliminar(Pelicula pelicula) {
        session.remove(pelicula);
    }

    @Override
    public Pelicula buscarPorId(int id) {
        Transaction trx = null;
        Pelicula pelicula = null;
        try {
            trx = session.beginTransaction();
            Query query = session.createQuery("select p from Pelicula p where p.idPelicula = : idPelicula");
            query.setParameter("idPelicula", id);

            pelicula = (Pelicula) query.getSingleResult();
        }catch (Exception e) {
            System.out.println("Error al recuperar los datos de pelicula: " + e.toString());
        }
        trx.commit();

        return pelicula;
    }

    @Override
    public List<Pelicula> buscarPorCadena(String cadena) {
        Transaction trx = null;
        List<Pelicula> listaPelicula = null;
        try {
            trx = session.beginTransaction();
            Query query = session.createQuery("select p from Pelicula p where p.titulo = :cadena");
            query.setParameter("cadena", cadena);

            listaPelicula = (List<Pelicula>) query.getResultList();
        }catch (Exception e) {
            System.out.println("Error al recuperar los datos de pelicula: " + e.toString());
        }

        trx.commit();
        return listaPelicula;
    }

    @Override
    public void actualizar(Pelicula pelicula) {
        session.merge(pelicula);
    }

    public List<Pelicula> buscarTodo() {
        Transaction trx = null;

        List<Pelicula> listaPelicula = null;
        try {
            trx = session.beginTransaction();

            Query query = session.createQuery("select p from Pelicula p");

            listaPelicula = (List<Pelicula>) query.getResultList();
        }catch (Exception e) {
            System.out.println("Error al recuperar los datos de peñicula: " + e.toString());
        }

        trx.commit();
        return listaPelicula;
    }

    public List<Pelicula> peliculasPorPersona(Persona persona) {
        Transaction trx = null;
        List<Pelicula> listaPelicula = null;
        try {
            trx = session.beginTransaction();

            Query query = session.createQuery("select pel from Pelicula pel, Persona p, listaPeliculas lp "
                    + "where pel.idPelicula=lp.idPelicula and lp.idPersona=:idPersona");
            query.setParameter("idPersona", persona.getIdPersona());

            listaPelicula = (List<Pelicula>) query.getResultList();
        }catch (Exception e) {
            System.out.println("Error al recuperar los datos de pelicula: " + e.toString());
        }

        trx.commit();
        return listaPelicula;
    }
}