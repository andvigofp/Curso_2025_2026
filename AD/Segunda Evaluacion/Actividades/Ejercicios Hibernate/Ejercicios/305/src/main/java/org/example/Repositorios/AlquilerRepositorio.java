package org.example.Repositorios;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.example.Entidades.Alquiler;
import org.example.Entidades.Cliente;
import org.example.Entidades.Libro;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDate;
import java.util.List;

public class AlquilerRepositorio implements Repositorio<Alquiler> {
    private Session session;

    public AlquilerRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Alquiler alquiler) {
        Transaction trx = session.beginTransaction();
        session.persist(alquiler);
        trx.commit();
    }

    //Comprobar si un libro está alquilado
    public boolean esAlquilado(int idLibro) {

        Query query = session.createQuery(
                "select a from Alquiler a where a.libro.idLibro = :idLibro and a.alquilado = true"
        );

        query.setParameter("idLibro", idLibro);

        List<Alquiler> listaAlquileres = query.getResultList();

        return !listaAlquileres.isEmpty();
    }

  //Alquiler Libro
  public void alquilar(int idLibro, int idCliente) {

      Transaction trx = session.beginTransaction();

      try {
          //1) Obtener libro por HQL

          Query qLibro = session.createQuery(
                  "select l from Libro l where l.idLibro = :idLibro"
          );
          qLibro.setParameter("idLibro", idLibro);

          Libro libro;

          try {
              libro = (Libro) qLibro.getSingleResult();
          } catch (NoResultException e) {
              System.out.println("El libro no existe");
              trx.rollback();
              return;
          }


          //2) Verificar si está alquilado
          if (esAlquilado(idLibro)) {
              System.out.println("Libro ya alquilado");
              trx.rollback();
              return;
          }

          //3) Obtener cliente por HQL

          Query qCliente = session.createQuery("select c from Cliente c where c.idCliente = :idCliente");
          qCliente.setParameter("idCliente", idCliente);

          Cliente cliente;

          try {
              cliente = (Cliente) qCliente.getSingleResult();
          } catch (NoResultException e) {
              System.out.println("El cliente no existe");
              trx.rollback();
              return;
          }

          // 4) Crear alquiler
          Alquiler alquiler = new Alquiler(LocalDate.now(), true);
          alquiler.setLibro(libro);
          alquiler.setCliente(cliente);

          session.persist(alquiler);

          trx.commit();
          System.out.println("Libro alquilado");

      } catch (Exception e) {
          trx.rollback();
          System.out.println("Error en alquiler: " + e);
      }
  }

    //Devolver Libro
    public void devolver(int idLibro) {
        Transaction trx = session.beginTransaction();

        try {
            Query query = session.createQuery(
                    "select a from Alquiler a where a.libro.idLibro = :idLibro and a.alquilado = true"
            );

            query.setParameter("idLibro", idLibro);

            Alquiler alquiler = (Alquiler) query.getSingleResult();

            alquiler.setAlquilado(false);
            session.merge(alquiler);

            trx.commit();
            System.out.println("✔️ Libro devuelto");

        } catch (NoResultException e) {
            trx.rollback();
            System.out.println("No hay alquiler activo para este libro");
        } catch (Exception e) {
            trx.rollback();
            System.out.println("Error en devolución: " + e);
        }
    }
}