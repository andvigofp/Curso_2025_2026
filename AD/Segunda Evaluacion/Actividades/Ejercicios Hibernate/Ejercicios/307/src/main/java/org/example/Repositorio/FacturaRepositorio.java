package org.example.Repositorio;

import org.example.Entidades.Cliente;
import org.example.Entidades.Factura;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class FacturaRepositorio implements Repositorio<Factura> {
    private Session session;
    private static Scanner teclado;

    public FacturaRepositorio(Session session, Scanner teclado) {
        this.session = session;
        this.teclado = teclado;
    }
    @Override
    public void guardar(Factura factura) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();
            session.persist(factura);
            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al guardar: " + e.toString());
        }
    }

    public void mostrarFacturas(){
        Query<Factura> query = session.createQuery("select f from Factura f", Factura.class);
        List<Factura> listaFacturas = query.getResultList();

        for (Factura f: listaFacturas) {
            System.out.println(f.toString());
        }
    }

    public void addFactura(Cliente cliente) {
        String descripcion = pedirString("Introduce la descripción de la factura: ");
        double precio = pedirDouble("Introduce el precio de la factura");

        Factura factura = new Factura(descripcion, precio, new Date());
        factura.setCliente(cliente);
        guardar(factura);
    }

    public void modificarFactura() {
        int idFactura = pedirInt("Introduzca el ID de la factura a modificar: ");
        teclado.nextLine();
        String descripcion = pedirString("Introduzca la nueva descripcón. Déjelo en blanco si queire conservar la anterior");
        double precio = pedirDouble("Introduzca la nueva precio. -1 para manterner la anterior precio: ");

        Transaction trx = null;
        try {
            trx = session.beginTransaction();

            Factura factura = getIdFactura(idFactura);

            if (!descripcion.isEmpty()) {
                factura.setDescripcion(descripcion);
            }

            if (precio !=-1) {
                factura.setPrecio(precio);
            }

            session.merge(factura);

            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al modificar: " + e.toString());

        }
    }

    public void borrarFactura() {
        int idFactura = pedirInt("Introduce el ID de la factura: ");

        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            Query<Factura> query = session.createQuery("from Factura f where f.idFactura = :idFactura", Factura.class);
            query.setParameter("idFactura", idFactura);

            Factura factura = query.getSingleResult();

            session.remove(factura);

            trx.commit();

        }catch (Exception e) {
            System.out.println("Error al borrar: " + e.toString());
            if (trx !=null) trx.rollback();
        }
    }

    private Factura getIdFactura(int idFactura) {
        Query query = session.createQuery("select f from Factura f where f.idFactura = :idFactura");
        query.setParameter("idFactura", idFactura);

        Factura factura = (Factura) query.getSingleResult();

        return factura;
    }

    private int pedirInt(String string) {
        System.out.println(string);
        return teclado.nextInt();
    }

    private Double pedirDouble(String string) {
        System.out.println(string);
        return teclado.nextDouble();
    }

    private String pedirString(String string) {
        System.out.println(string);
        return teclado.nextLine();
    }
}
