package Ejercicios.EJ505;

/**
 *Dado el siguiente diagrama de modelo relacional:
 *
 *
 *
 * Donde tenemos las siguientes entidades:
 *
 * Medicos:
 * medico_id: tipo serial y clave primaria
 * datos_personales: tipo Persona
 * medico_info: de tipo Medico
 * Pacientes:
 * paciente_id: de tipo serial y clave primaria
 * datos_personales: de tipo Persona
 * paciente_info: de tipo Paciente
 * CitasMedicas:
 * cita_id: de tipo serial y clave primaria
 * fecha_hora: de tipo fecha y hora
 * relación varios a 1 con Medicos
 * relacion varios a 1 con Pacientes
 * ExamenesMedicos:
 * examen_id: tipo serial y clave primaria
 * relación varios a 1 con Pacientes
 * examen_info: tipo ExamenMedico
 * Además de estas entidades, el esquema presenta los siguientes objetos:
 *
 * Persona:
 * nombre: tipo varchar
 * edad: tipo entero
 * Paciente:
 * numero_historia: tipo varchar
 * grupo_sanguineo: tipo varchar
 * ExamenMedico:
 * nombre_examen: tipo varchar
 * fecha_realizacion: tipo fecha
 * resultado: tipo text
 * Medico:
 * codigo_medico: tipo varchar
 * especialidad: tipo varchar
 * Se pide:
 *
 * Crear el script .sql que permita crear la base de datos PostgreSQL hospital.
 *
 * Crear los métodos que permitan:
 *
 * insertar, eliminar y modificar un Medicos.
 * insertar, eliminar y modificar un Pacientes.
 * insertar, eliminar y modificar un ExamenesMedicos.
 * insertar, eliminar y modificar una CitasMedicas.
 * Crear métodos para realizar las siguientes consultas:
 *
 * Listar toda la información de un Paciente buscándolo por id.
 * Listar toda la información de todos los Paciente.
 * Listar la información de un Medicos buscándolo por id.
 * Listar toda la información de todos los Medicos.
 * Listar toda la información de un CitasMedicas buscándolo por id de Paciente.
 * Listar toda la información de un CitasMedicas buscándolo por id de Médico.
 * Listar todos los ExamenesMedicos de un Paciente.
 * Listar la información de los Pacientes buscándolos por grupo_sanguineo
 * Listar la información de los Medicos que atendieron a un determinado Paciente (se dispone del identificador del Paciente)
 */

public class Ej505 {
    public static void main(String[] args) {
        MEj505 metodos = new MEj505();

        metodos.Menu();
    }
}
