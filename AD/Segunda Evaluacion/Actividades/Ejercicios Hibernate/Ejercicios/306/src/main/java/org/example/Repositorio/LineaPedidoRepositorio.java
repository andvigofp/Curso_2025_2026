package org.example.Repositorio;


import org.example.Entidades.Cliente;
import org.example.Entidades.LineaPedido;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class LineaPedidoRepositorio implements Repositorio<LineaPedido> {
    private Session session;

    public LineaPedidoRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(LineaPedido lineaPedido) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();
            session.persist(lineaPedido);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al guardar: " + e.toString());
        }
    }

    public void mostrarTodosPedidos() {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            Query query = session.createQuery("select lp from LineaPedido lp");
            ArrayList<LineaPedido> lineaPedidos = (ArrayList<LineaPedido>) query.getResultList();

            for (LineaPedido lPedido : lineaPedidos) {
                System.out.println(lPedido.toString());
            }
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error en la consulta: " + e.toString());
        }
    }

    public void mostrarPedidosCliente(Cliente cliente) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            Query query = session.createQuery("select lp from LineaPedido lp where lp.pedido.cliente.idCliente = :idCliente");
            query.setParameter("idCliente", cliente.getIdCliente());


            ArrayList<LineaPedido> lineaPedidos = (ArrayList<LineaPedido>) query.getResultList();

            for (LineaPedido lPedido : lineaPedidos) {
                System.out.println(lPedido.toString());
            }

            trx.commit();
        }catch (Exception e) {
            System.out.println("Error en la consulta: " + e.toString());
        }
    }
}
