package org.example;

import org.example.Entidades.Cliente;
import org.example.Repositorios.AlquilerRepositorio;
import org.example.Repositorios.ClienteRepositorio;
import org.hibernate.Session;

/**
 * Deberás crear un proyecto Hibernate que mapee las tablas de la base de datos. Las clases que interaccionan con la base de datos deberán estar en un paquete llamado modelo.
 *
 * La aplicación debe tener, al menos, los siguientes métodos:
 *
 * obtenerCliente(idCliente): devuelve el objeto del cliente solicitado.
 * añadirCliente(cliente): añade el cliente a la BD.
 * modificarCliente(cliente): modifica el cliente en la BD.
 * borrarCliente(cliente): borra un cliente en el caso de que no tenga ningún libro alquilado. Devuelve un booleano en función de si el cliente ha sido borrado o no.
 * esAlquilado(idLibro): devuelve un booleano en función de si el libro está alquilado.
 * alquilar(idLibro, idCliente): crea el alquiler de un libro. Se debe comprobar que el libro y el cliente existan y que el libro no esté ya alquilado.
 * devolver(idLibro): devuelve un libro si estaba alquilado.
 * En el programa principal debes probar que cada uno de los métodos funciona correctamente.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        ClienteRepositorio clienteRepositorio = new ClienteRepositorio(session);
        AlquilerRepositorio alquilerRepositorio = new AlquilerRepositorio(session);

        Cliente cliente = clienteRepositorio.obtenerCliente(2);
        System.out.println(cliente.toString());

        Cliente nuevoCliente = new Cliente("123456", "Cliente1", "Cliente1@cliente.es");
        clienteRepositorio.guardar(nuevoCliente);
        System.out.println(nuevoCliente.toString());

        nuevoCliente.setDni("789456");
        clienteRepositorio.modificarCliente(nuevoCliente);
        System.out.println(nuevoCliente.toString());

        clienteRepositorio.borrarCliente(nuevoCliente);

        alquilerRepositorio.alquilar(3, 1);

        alquilerRepositorio.devolver(3);

        alquilerRepositorio.alquilar(3, 1);
        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
