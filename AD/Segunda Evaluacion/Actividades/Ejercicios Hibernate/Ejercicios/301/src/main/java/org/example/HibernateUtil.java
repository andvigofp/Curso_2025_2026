package org.example;

import java.io.File;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Desarrolla en Java un programa que utilizando Hibernate permita crear una clase base de datos llamada empleados la cual permita representar los empleados (Emp) que hay en un departamento (Depto) sabiendo que:
 *
 * un departamento está formado por varios empleados
 * un empleado solo puede estar en un departamento.
 * Además, para la tabla de departamentos (Depto) se quiere almacenar:
 *
 * id de departamento que será auto incremental
 * nombre del departamento
 * localidad
 * Por otro lado, para la tabla de empleados (Emp) se quiere almacenar:
 *
 * id del empleado que será auto incremental
 * nombre
 * puesto
 * sueldo
 * edad
 * DNI
 * esJefe
 * Crea una clase App que permita crear 5 departamentos y 10 empleados de tal forma que cada departamento tenga 2 empleados.
 */

public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            String hibernatePropsFilePath = "./src/hibernate.cfg.xml"; // Ruta al fichero

            File hibernatePropsFile = new File(hibernatePropsFilePath);

            SESSION_FACTORY = new Configuration().configure(hibernatePropsFile).buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Error al crear la configuración de hibernate" + ex.getMessage());
            throw new ExceptionInInitializerError();
        }
    }

    public static SessionFactory get() {
        return SESSION_FACTORY;
    }
}