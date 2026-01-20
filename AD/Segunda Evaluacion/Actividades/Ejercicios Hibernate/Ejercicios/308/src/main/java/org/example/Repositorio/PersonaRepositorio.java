package org.example.Repositorio;

import org.example.Entidades.Persona;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PersonaRepositorio implements Repositorio<Persona> {
    private Session session;

    public PersonaRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Persona persona) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();
            session.persist(persona);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al guardar: " + e.toString());
        }
    }

    @Override
    public void eliminar(Persona persona) {
        session.persist(persona);
    }

    @Override
    public Persona buscarPorId(int id) {
        Transaction trx = null;
        Query query = null;
        Persona persona = null;

        try {
            trx = session.beginTransaction();
            query = session.createQuery("select p from Atriz p where p.idPersona = :idPersona");
            query.setParameter("idPersona", id);
            persona = (Persona) query.getSingleResult();
        }catch (Exception e) {
            try {
                query = session.createQuery("select p from Actor p where p.idPersona = :idPersona");
                query.setParameter("idPersona", id);
                persona = (Persona) query.getSingleResult();
            }catch (Exception e1) {
                System.out.println("Error en recuperar los valores del actor: " +e1.toString());
            }
        }
        trx.commit();

        return persona;
    }

    @Override
    public List<Persona> buscarPorCadena(String cadena) {
        Transaction trx = null;
        List<Persona> listaPersona = null;
        Query query = null;

        try {
            trx = session.beginTransaction();
            query = session.createQuery("select p from Persona p where p.anhoNacimiento > : cadena");
            query.setParameter("cadena", cadena);
            listaPersona = (List<Persona>) query.getResultList();
        }catch (Exception e) {
            try {
                query = session.createQuery("select p from Actor p where p.anhoNacimiento > :cadena");
                query.setParameter("cadena", cadena);
                listaPersona = (List<Persona>) query.getResultList();
            }catch (Exception e1) {
                System.out.println("Error al recuperar los valores del actor: " + e1.toString());
            }
        }
        trx.commit();

        return listaPersona;
    }

    public List<Persona> buscarPorAnho(int cadena) {
        Transaction trx = session.beginTransaction();
        List<Persona> listaPersona = null;
        Query query = null;
        try {
            query = session.createQuery("select p from Actriz p where p.anhoNacimiento > :cadena");
            query.setParameter("cadena", cadena);
            listaPersona = (List<Persona>) query.getResultList();
        }catch (Exception e) {
            try {
                query = session.createQuery("select p from Actor p where p.anhoNacimiento > :cadena");
                query.setParameter("cadena", cadena);
                listaPersona = (List<Persona>) query.getResultList();
            }catch (Exception e1) {
                System.out.println("Error recuperando valores actor" + e1.toString());
            }

        }
        trx.commit();

        return listaPersona;
    }

    @Override
    public void actualizar(Persona persona) {
        session.merge(persona);
    }

    public List<Persona> buscarPorMejorActriz(int mejorActriz) {
        Transaction trx = null;
        List<Persona> listaPersona = null;
        try {
            trx = session.beginTransaction();
            Query query = session.createQuery("select p from Actriz p where p.numeroMejorActriz >= :mejorActriz");
            query.setParameter("mejorActriz", mejorActriz);

            listaPersona = (List<Persona>) query.getResultList();

        }catch (Exception e) {
            System.out.println("Error al recuperar los datos de la atriz " + e.toString());
        }
        trx.commit();
        return listaPersona;
    }

    public List<Persona> buscarPorMejorActor(int mejorActor) {
        Transaction trx = null;
        List<Persona> listaPersona = null;

        try {
        trx = session.beginTransaction();
        Query query = session.createQuery("select p from Actor p where p.numeroOscars >= :numeroOscars");
        query.setParameter("numeroOscars", mejorActor);

       listaPersona = (List<Persona>) query.getResultList();

    }catch (Exception e) {
            System.out.println("Error al recuperar los datos del actor");
        }
        trx.commit();

        return listaPersona;
    }
}