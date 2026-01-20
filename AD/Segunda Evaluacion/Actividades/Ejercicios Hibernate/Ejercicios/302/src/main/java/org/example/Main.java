package org.example;

import org.example.Entidades.Emp;
import org.example.Repositorio.DptoRepositorio;
import org.example.Repositorio.EmpRepositorio;
import org.hibernate.Session;

/**
 * Realiza las modificaciones necesarias en el código del ejercicio anterior para crear los siguientes métodos:
 *
 * visualizarDepartamento(id): permite visualizar la información de un determinado departamento así como el número y nombre de los empleados adscritos.
 *
 * añadirEmpleado(idDepartamento, empleado): permite añadir un determinado departamento un nuevo empleado.
 *
 * Prueba a añadir al departamento 10 un nuevo empleado que tenga los siguientes datos:
 * Nombre: ANTONIO
 * Puesto: INVESTIGADOR
 * Sueldo: 3500€
 * Edad: 45
 * DNI: 12345678A
 * actualizarJefeDepartamento(idDepart, idEmpl): permite actualizar qué empleado es el jefe de un determinado departamento
 *
 * Prueba a cambiar el jefe de un determinado departamento
 * eliminarDepartamento(id): permite eliminar un determinado departamento.
 *
 * Prueba a eliminar el departamento 4.
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        DptoRepositorio dptoRepositorio = new DptoRepositorio(session);
        EmpRepositorio empRepositorio = new EmpRepositorio(session);

        dptoRepositorio.visualiazarDepartamento(2);

        Emp empleadoEmp = new Emp("ANTONIO", "INVESTIGACION", 3500, 45, "12345678A", false);
        empRepositorio.añadirEmpleado(1, empleadoEmp);
        empRepositorio.actualizarJefeDepartamento(2, 4);

        dptoRepositorio.eliminarDepartamento(4);

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
