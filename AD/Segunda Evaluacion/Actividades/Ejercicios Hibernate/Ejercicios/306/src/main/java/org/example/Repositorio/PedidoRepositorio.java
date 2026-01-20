package org.example.Repositorio;

import org.example.Entidades.Cliente;
import org.example.Entidades.LineaPedido;
import org.example.Entidades.Pedido;
import org.example.Entidades.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class PedidoRepositorio implements Repositorio<Pedido> {
    private Session session;
    private Scanner teclado;
    private ProductoRepositorio productoRepositorio;

    public PedidoRepositorio(Session session, Scanner teclado)  {
        this.session = session;
        this.teclado = teclado;
        this.productoRepositorio = new ProductoRepositorio(session);
    }

    @Override
    public void guardar(Pedido pedido) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();
            session.persist(pedido);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al guardar: " + e.toString());
        }

    }

    public void eliminarPedido(int idPedido) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            Query query = session.createQuery("select p from Pedido p where p.idPedido = : idPedido");
            query.setParameter("idPedido", idPedido);

            Pedido pedido = (Pedido) query.getSingleResult();

            session.remove(pedido);

            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al eliminar: " + e.toString());
            trx.rollback();
        }
    }

    public void addPedido(Cliente cliente) {
        ArrayList<LineaPedido> lineaPedidos = new ArrayList<>();

        // Crear el pedido asociado al cliente
        Pedido pedido = new Pedido(new Date(), cliente);

        String opcion = "";

        do {
            int idProducto = pintarPedirInt("Introduce el id del producto: ");

            Producto producto = productoRepositorio.getProducto(idProducto);

            if (producto.getIdProducto() != -1) {
                int cantidad = pintarPedirInt("Introduzca la cantidad de productos: ");

                // Crear la línea de pedido asociada al pedido y al producto
                LineaPedido lPedido = new LineaPedido();
                lPedido.setCantidad(cantidad);
                lPedido.setPedido(pedido);
                lPedido.setProducto(producto);

                lineaPedidos.add(lPedido);
            }
            opcion = pintarPedirString("Quieres seguir introduciendo productos al pedido? (y/n)");

        } while (!opcion.toLowerCase().equals("n"));

        // Asociar la lista de lineas al pedido
        pedido.setListaPedidos(lineaPedidos);

        // Guardar todo en la base de datos
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.persist(pedido); // Esto guardará también las lineas por cascade
            trx.commit();
            System.out.println("Pedido añadido correctamente.");
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al guardar el pedido: " + e);
        }
    }


    public int pintarPedirInt(String mensaje) {
        System.out.println(mensaje);
        return this.teclado.nextInt();
    }

    public String pintarPedirString(String mensaje) {
        System.out.println(mensaje);
        return this.teclado.next();
    }
}
