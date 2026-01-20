package org.example.Repositorio;

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
        Transaction trx =  null;

        try {
            trx = session.beginTransaction();
            session.persist(cliente);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al guardar : " + e.toString());
        }
    }

    public Cliente getClientePorId(int idCliente) {
        Transaction trx = null;

        Cliente cliente = null;
        try {
            trx = session.beginTransaction();

            Query query = session.createQuery("select c from Cliente c where idCliente = :idCliente");
            query.setParameter("idCliente", idCliente);
            cliente = (Cliente) query.getSingleResult();

            trx.commit();
        }catch (Exception e) {
            System.out.println("Error en la consulta: " + e.toString());
        }
        return cliente;
    }
}
