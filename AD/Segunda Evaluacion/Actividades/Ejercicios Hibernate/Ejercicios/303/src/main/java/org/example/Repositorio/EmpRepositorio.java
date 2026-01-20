package org.example.Repositorio;

import jakarta.persistence.Query;
import org.example.Entidades.Dpto;
import org.example.Entidades.Emp;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class EmpRepositorio implements Repositorio<Emp>{
    private Session session;

    public EmpRepositorio(Session session) {
        super();
        this.session = session;
    }

    @Override
    public void guardar(Emp emp) {
        Transaction trx = this.session.beginTransaction();
        session.persist(emp);
        trx.commit();
    }

    public void añadirEmpleado(int idDepartamento, Emp empleado) {
        Transaction trx = this.session.beginTransaction();

        Dpto dpto = (Dpto) session.createQuery("select d from Dpto d where d.idDpto = :id")
                .setParameter("id", idDepartamento)
                .getSingleResult();

        dpto.addEmps(empleado);

        this.session.persist(empleado);
        trx.commit();
    }

    public void actualizarJefeDepartamento(int idDepart, int idEmpl) {
        Transaction trx = this.session.beginTransaction();

        Query query = session.createQuery("select e from Emp e where e.dptoElement.idDpto = :id1 AND e.idEmp = :id2");

        query.setParameter("id1", idDepart);
        query.setParameter("id2", idEmpl);

        Emp emp = (Emp) query.getSingleResult();

        emp.setEsJefe(true);

        this.session.merge(emp);

        trx.commit();
    }

    public void empleadosTecnicos() {
       Query query = session.createQuery("select e from Emp e where e.puesto = 'Técnico'");

       List<Emp> listaEmpleados = query.getResultList();

       System.out.println("Total de empleados: " + listaEmpleados);

        for (Emp emp : listaEmpleados) {
            System.out.println(emp.toString());
        }
    }

    public List<Emp> empleadoConMayorSueldo() {
        Query query = session.createQuery("select e from Emp e where e.sueldo = (select max(em.sueldo) from Emp em)");

        List<Emp> listaEmpleado = query.getResultList();

        for (Emp emp: listaEmpleado) {
            System.out.println(emp.toString());
        }
        return listaEmpleado;
    }

    public void aumentarSalarioJefes(double cantidad) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            Query query = session.createQuery("update Emp e set e.sueldo = e.sueldo + :cantidad where e.esJefe=true");

            query.setParameter("cantidad", cantidad);

            int elementos_afectados = query.executeUpdate();

            System.out.println("Se han modificado: " + elementos_afectados);

            trx.commit();
        }catch (Exception e) {
            System.out.println("Error al actualizar: " + e.toString());
            if (trx !=null) trx.rollback();        }
    }

    public void borrarEmpleadosDepartamento(int idDepartamento) {
        Transaction trx = null;

        try {
            trx = session.beginTransaction();

            Query query = session.createQuery("select e From Emp e where e.dptoElement.id = :id");

            query.setParameter("id", idDepartamento);

            List<Emp> listaEmpleado = query.getResultList();

            for (Emp emp : listaEmpleado) {
                session.remove(emp);
            }

            trx.commit();

            System.out.println("Empleado eliminado del departamento exitosamente");
        }catch (Exception e) {
            System.out.println("Error al borrar: " + e.toString());
            if (trx !=null) trx.rollback();
        }
    }
}