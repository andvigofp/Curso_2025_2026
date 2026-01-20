package org.example.Repositorio;

import org.example.Entidades.Telefono;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TelefonoRepositorio implements Repositorio<Telefono>{
    private Session session;

    public TelefonoRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void insertarUno(Telefono telefono) {
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.persist(telefono);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al insertar: " + e.toString());
        }

    }

    @Override
    public void borrar(Telefono telefono) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();
            session.remove(telefono);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al borrar: " + e.toString());
            if (trx !=null) trx.rollback();
        }
    }

    @Override
    public List<Telefono> enocntrarTodos() {
        return null;
    }

    @Override
    public Telefono encontrarUnoPorString(String nombre) {
        return null;
    }

    @Override
    public void actualizar(Telefono telefono) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();
            session.merge(telefono);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al actualizar: " + e.toString());
            if (trx !=null) trx.rollback();
        }
    }
}