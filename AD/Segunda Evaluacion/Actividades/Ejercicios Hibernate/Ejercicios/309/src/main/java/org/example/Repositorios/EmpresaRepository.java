package org.example.Repositorios;

import org.example.Entidades.Empresa;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmpresaRepository implements Repositorio<Empresa> {

    private Session session;

    public EmpresaRepository(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Empresa empresa) {
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.persist(empresa);
            trx.commit();
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Empresa empresa) {
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.merge(empresa);
            trx.commit();
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Empresa empresa) {
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.remove(empresa);
            trx.commit();
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    public Empresa findById(String cif) {
        return session.find(Empresa.class, cif);
    }

    public List<Empresa> findAll() {
        return session.createQuery("from Empresa", Empresa.class).list();
    }
}
