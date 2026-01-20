package Ejercicios.EJ504;

import java.sql.SQLException;

/**
 * Dado el siguiente diagrama de modelo relacional:
 *
 *
 *
 * Donde tenemos las siguientes entidades:
 *
 * Estudiantes:
 * estudiante_id: de tipo serial y clave primaria
 * datos_personales: tipo Persona
 * estudiante_info: tipo Estudiante
 * Inscripciones:
 * inscripción_id: de tipo serial y clave primaria
 * relación varios a 1 con Estudiantes
 * relación varios a 1 con Cursos
 * Cursos:
 * curso_id: tipo serial y clave primaria.
 * nombre: tipo varchar
 * Profesores:
 * profesor_id: tipo serial y clave primaria
 * datos_personales: tipo Persona
 * profesor_info: tipo Profesor
 * relación varios a 1 con Cursos
 * Además de estas entidades, el esquema presenta los siguientes objetos:
 *
 * Persona:
 * nombre: tipo varchar
 * edad: tipo entero
 * Profesor:
 * cedula: tipo varchar
 * departamento: tipo varchar
 * Estudiante:
 * matricula: tipo varchar
 * carrera: tipo varchar
 * Se pide:
 *
 * Crear el script .sql que permita crear la base de datos PostgreSQL universidad.
 *
 * Crear los métodos que permitan:
 *
 * insertar, eliminar y modificar un Estudiante.
 * insertar, eliminar y modificar un Profesor.
 * insertar, eliminar y modificar un Curso.
 * Inscribir y desinscribir un alumno de un curso.
 * Inscribir y desinscribir un profesor de un curso.
 * Crear métodos para realizar las siguientes consultas:
 *
 * Listar toda la información de un Estudiante buscándolo por id.
 * Listar toda la información de todos los Estudiante.
 * Listar la información de un Curso buscándolo por id.
 * Listar toda la información de todos los Curso.
 * Listar toda la información de un Profesor buscándolo por id.
 * Listar toda la información de todos los Profesor.
 * Listar todos los estudiantes y sus matriculas.
 * Encontrar los Cursos en los que está inscrito un estudiante.
 * Obtener el número de estudiantes en cada carrera.
 */
public class Ej504 {
    public static void main(String[] args) {
      try {
          MEj504 mEj504 = new MEj504();

          mEj504.Menu();
      }catch (SQLException e) {
          System.out.println("Error de conexión " + e.toString());
      }
    }
}
