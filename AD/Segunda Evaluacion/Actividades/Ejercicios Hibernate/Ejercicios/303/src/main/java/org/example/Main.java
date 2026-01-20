package org.example;

import org.example.Repositorio.DptoRepositorio;
import org.example.Repositorio.EmpRepositorio;
import org.hibernate.Session;

/**
 * Realiza las modificaciones necesarias en el código del ejercicio anterior para crear los siguientes métodos:
 *
 * empleadosTécnicos(): permite recuperar el número y nombre de los empleados que son Técnicos.
 *
 * empleadoConMayorSueldo(): devuelve el empleado que tenga mayor sueldo.
 *
 * aumentarSalarioJefes(int cantidad): permite aumentar el sueldo de todos los jefes una determinada cantidad.
 *
 * borrarEmpleadosDepartamento(int idDepartamento): elimina los empleados de un determinado departamento.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        DptoRepositorio dptoRepositorio = new DptoRepositorio(session);
        EmpRepositorio empRepositorio = new EmpRepositorio(session);

        empRepositorio.empleadosTecnicos();

        empRepositorio.empleadoConMayorSueldo();

        empRepositorio.aumentarSalarioJefes(2000);

        empRepositorio.borrarEmpleadosDepartamento(4);
        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
