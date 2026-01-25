package org.example.repositorios;

import org.example.entidades.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class CicloRepositorio {
    private Session session;

    public CicloRepositorio(Session session) {
        this.session = session;
    }

    public void crearCiclo(String nombre) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            Ciclo ciclo = session.createQuery("from Ciclo c where c.nombreCiclo = : nombre", Ciclo.class)
                    .setParameter("nombre", nombre)
                    .uniqueResult();


            if (ciclo !=null) {
                System.out.println("No existe ese ciclo con ese nombre: " + ciclo);
                return;
            }

           ciclo = new Ciclo(nombre);

           session.persist(ciclo);

           trx.commit();

            System.out.println("Se añadido el ciclo exitosamente");

            System.out.println("Nombre: " + nombre);
        }catch (Exception e) {
            System.out.println("Error al crear el ciclo " + e.getMessage());
        }
    }

    public void borrarCiclo(int id) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            Ciclo ciclo = session.createQuery("from Ciclo c where c.codigo = :id", Ciclo.class)
                    .setParameter("id", id)
                    .uniqueResult();

            if (ciclo ==null) {
                System.out.println("No existe ese id de ciclo: " + id);
                return;
            }

            for (Instituto instituto : ciclo.getListaInstituto()) {
                instituto.getListaCiclo().remove(ciclo);
            }
            ciclo.getListaInstituto().clear();

            session.remove(ciclo);

            trx.commit();

            System.out.println("Se a eliminado el cilico exitosamente");
            System.out.println("ID: " + ciclo.getCodigo());
            System.out.println("Nombre: " + ciclo.getNombreCiclo());
        }catch (Exception e) {
            if (trx !=null) trx.rollback();
            System.out.println("Error al eliminar el ciclo: " + e.getMessage());
        }
    }

    public void asignarCicloATaller(int codigoCiclo, int codigoTaller) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            //1. Obtener entidades
            Ciclo ciclo = session.createQuery("from Ciclo c where c.codigo = : id", Ciclo.class)
                    .setParameter("id", codigoCiclo)
                    .uniqueResult();

            Taller taller = session.createQuery("from Taller t where t.codigo = :id", Taller.class)
                    .setParameter("id", codigoTaller)
                    .uniqueResult();

            //Validar existencia
            if (ciclo == null) {
                System.out.println("No existe el ciclo con código: " + codigoCiclo);
                trx.rollback();
                return;
            }

            if (taller == null) {
                System.out.println("No existe el taller con código: " + codigoTaller);
                trx.rollback();
                return;
            }

            //3. Crear PK con fecha y hora actuales
            LocalDate fecha = LocalDate.now();
            LocalTime hora = LocalTime.now();
            UsoPK usoPK = new UsoPK(hora, fecha, codigoTaller, codigoCiclo);

            //4. Crear entidad Uso
            Uso uso = new Uso(usoPK, taller, ciclo);
            uso.setId(usoPK);
            uso.setTaller(taller);
            uso.setCiclo(ciclo);

            //5. Asignación bidireccional
            taller.adduso(uso);  // agrega uso a lista del taller
            ciclo.addUso(uso);   // agrega uso a lista del ciclo

            //6. Persistir
            session.persist(uso);

            trx.commit();
            System.out.println("Ciclo asignado al taller correctamente para fecha y hora actuales");

        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al asignar ciclo al taller: " + e.getMessage());
        }
    }

    public void listarCiclosDeInstituto(int codigoInstituto) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            Instituto instituto = session.createQuery("from Instituto i where i.codigo = :id", Instituto.class)
                    .setParameter("id", codigoInstituto)
                    .uniqueResult();

            if (instituto == null) {
                System.out.println("No existe el instituto con ese código: " + codigoInstituto);
                trx.rollback();
                return;
            }

            List<Ciclo> listaCiclos = instituto.getListaCiclo(); // ahora es lista de ciclos

            if (listaCiclos.isEmpty()) {
                System.out.println("Este instituto no imparte ningún ciclo");
            } else {
                System.out.println("Ciclos que imparte el instituto " + instituto.getNombre() + ":");
                for (Ciclo c : listaCiclos) {
                    System.out.println("- " + c.getNombreCiclo());
                }
            }

            trx.commit();

        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al listar ciclos: " + e.getMessage());
        }
    }

}
