package org.example.Repositorio;

import org.example.Entidades.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class ProductoRepositorio implements Repositorio<Producto> {
   private Session session;

   public ProductoRepositorio(Session session) {
       this.session = session;
   }

    @Override
    public void guardar(Producto producto) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();
            session.persist(producto);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al guardar: " + e.toString());
        }
    }

    public Producto getProducto(int idProducto) {
       try {
           Query query = session.createQuery("select p from Producto p where p.idProducto = : idProducto");
           query.setParameter("idProducto", idProducto);

           Producto producto = (Producto) query.getSingleResult();

           return producto;
       }catch (Exception e) {
           return new Producto(-1);
       }
    }
}
