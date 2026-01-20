package org.example.Repositorio;

import jakarta.persistence.Query;
import org.example.Entidades.Dpto;
import org.example.Entidades.Emp;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}