package org.example.Repositorios;



import org.example.Entidades.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ClienteRepositorio implements Repositorio<Cliente> {
    private Session session;


    public ClienteRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Cliente cliente) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();
            session.persist(cliente);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al guardar: " + e.toString());
        }
    }

    public Cliente obtenerCliente(int idCliente) {
        Transaction trx = session.beginTransaction();

        Query query = session.createQuery("select c from Cliente c where c.idCliente = :id");
        query.setParameter("id", idCliente);

        Cliente cliente = (Cliente) query.getSingleResult();

        trx.commit();

        return cliente;
    }

    public void modificarCliente(Cliente cliente) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            session.merge(cliente);

            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al modificar: " + e.toString());
            if (trx !=null) trx.rollback();
        }
    }

    public boolean borrarCliente(Cliente cliente) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            session.remove(cliente);

            trx.commit();

            return true;
        }catch (Exception e) {
            System.out.println("Error al borrar");
            return false;
        }
    }
}
