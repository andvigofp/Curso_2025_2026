package Ejercicios.EJ504;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MEj504 {
    static Scanner teclado = new Scanner(System.in);
    private static Connection connection = Database.getInstance();

    public void Menu() throws SQLException {
        final String menu = "1. INSERTAR ESTUDIANTE."
                + "\n2. ELIMINAR ESTUDIANTE."
                + "\n3. MODIFICAR ESTUDIANTE."
                + "\n4. INSERTAR PROFESOR."
                + "\n5. ELIMINAR PROFESOR."
                + "\n6. MODIFICAR PROFESOR."
                + "\n7. INSERTAR CURSO."
                + "\n8. ELIMINAR CURSO."
                + "\n9. MODIFICAR CURSO."
                + "\n10. INSCRIBIR ESTUDIANTE EN CURSO."
                + "\n11. DESINSCRIBIR PROFESOR EN CURSO."
                + "\n12. INSCRIBIR PROFESOR EN CURSO."
                + "\n13. DESINSCRIBIR PORFESOR DE CURSOS."
                + "\n14. LISTAR ESTUDIANTE POR ID."
                + "\n15. LISTAR TODOS LOS ESTUDIANTES."
                + "\n16. LISTAR CURSO POR ID."
                + "\n17. LISTAR TODOS LOS CURSOS."
                + "\n18. LISTAR PROFESORES POR ID."
                + "\n19. LISTAR TODOS LOS PROFESORES."
                + "\n20. LISTAR TODOS LOS ESTUDIANTES/MATRICULAS."
                + "\n21. LISTAR CURSOS DE UN ESTUDIANTE."
                + "\n22. NÚMERO DE ESTUDIANTES POR CARRERA"
                + "\n23. SALIR.";

        int opcion = -1;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    insertarEstudiante();
                    break;
                case 2:
                    eliminarEstudiante();
                    break;
                case 3:
                    modificarEstudiante();
                    break;
                case 4:
                    insertarProfesor();
                    break;
                case 5:
                    eliminarProfesor();
                    break;
                case 6:
                    modificarProfesor();
                    break;
                case 7:
                    insertarCurso();
                    break;
                case 8:
                    eliminarCurso();
                    break;
                case 9:
                    modificarCurso();
                    break;
                case 10:
                    inscribirEstudianteCurso();
                    break;
                case 11:
                    desinscribirEstudianCurso();
                    break;
                case 12:
                    inscribirProfesorCurso();
                    break;
                case 13:
                    desinscribirProfesorCurso();
                    break;
                case 14:
                    listarEstudianteID();
                    break;
                case 15:
                    listarTodosEstudiantes();
                    break;
                case 16:
                    int cursoId = pedirInt("ID del Curso: ");
                    listarCursoID(cursoId);
                    break;
                case 17:
                    listarTodosCursos();
                    break;
                case 18:
                    int profesorID = pedirInt("ID del profesor: ");
                    listarProfesorID(profesorID);
                    break;
                case 19:
                    listarTodosProfesores();
                    break;
                case 20:
                    listarEstudianteCursos();
                    break;
                case 21:
                    listarCursosEstudiante();
                    break;
                case 22:
                    numeroEstudiantesCurso();
                    break;
                case 23:
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Opción no válida, por favor elija una opción del menú entre 1-23");
            }
        } while (opcion != 23);
    }

    private int pedirInt(String mensaje) {
        while (true) {
            System.out.println(mensaje);

            try {
                return teclado.nextInt();
            } catch (Exception e) {
                System.out.println("Error. " + e.toString());
            }
        }
    }

    private String pedirString(String mensaje) {
        while (true) {
            System.out.println(mensaje);

            try {
                return MEj504.teclado.nextLine();
            } catch (Exception e) {
                System.out.println("Error. " + e.toString());
            }
        }
    }

    private void insertarEstudiante() {
        String nombre = pedirString("Nombre del estudiante");
        int edad = pedirInt("Edad del estudiante");
        teclado.nextLine();
        String matricula = pedirString("Matricula del estudiante");
        String carrera = pedirString("Carrera del estudiante");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO objetos.Estudiantes (datos_personales, estudiante_info) VALUES (ROW(?, ?), ROW(?, ?))");
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, edad);
            preparedStatement.setString(3, matricula);
            preparedStatement.setString(4, carrera);

            int rowInsert = preparedStatement.executeUpdate();

            if (rowInsert > 0) {
                System.out.println("Se ha insertado el estudiante correctamente");
            } else {
                System.out.println("No se pudo insertar el estudiante");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar los datos de estudiante." + e.toString());
        }
    }

    private void eliminarEstudiante() {
        int estudianteID = pedirInt("ID del estudiante a eliminar: ");

        try {
            // Paso 1: Eliminar inscripciones relacionadas
            PreparedStatement eliminarInscripciones = connection.prepareStatement(
                    "DELETE FROM objetos.Inscripciones WHERE estudiante_id = ?"
            );
            eliminarInscripciones.setInt(1, estudianteID);
            eliminarInscripciones.executeUpdate();

            // Paso 2: Eliminar estudiante
            PreparedStatement eliminarEstudiante = connection.prepareStatement(
                    "DELETE FROM objetos.Estudiantes WHERE estudiante_id = ?"
            );
            eliminarEstudiante.setInt(1, estudianteID);

            int rowDelete = eliminarEstudiante.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("El estudiante se eliminó correctamente.");
            } else {
                System.out.println("No se pudo eliminar el estudiante.");
            }

        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }


    private void modificarEstudiante() {
        int estudianteIDModificar = pedirInt("ID del estudiante a modificar: ");
        teclado.nextLine();
        String nuevoNombre = pedirString("Nuevo nombre del estudiante: ");
        int nuevaEdad = pedirInt("Nueva edad: ");
        teclado.nextLine();
        String nuevaMatricula = pedirString("Nueva matricula: ");
        String nuevaCarrera = pedirString("Nueva carrera: ");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE objetos.Estudiantes SET datos_personales = ROW(?, ?), estudiante_info = ROW(?, ?) WHERE estudiante_id = ?");
            preparedStatement.setString(1, nuevoNombre);
            preparedStatement.setInt(2, nuevaEdad);
            preparedStatement.setString(3, nuevaMatricula);
            preparedStatement.setString(4, nuevaCarrera);
            preparedStatement.setInt(5, estudianteIDModificar);

            int rowUpdate = preparedStatement.executeUpdate();

            if (rowUpdate > 0) {
                System.out.println("El estudiante se actualizado correctamente.");
            } else {
                System.out.println("No se a podido modificar el estudiante.");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    private void insertarProfesor() {
        String nombreP = pedirString("Nombre Profesor: ");
        int edadP = pedirInt("Edad del profesor: ");
        teclado.nextLine();
        String cedula = pedirString("Cédula del profesor: ");
        String departamento = pedirString("Departamento del profesor: ");
        int cursoID = pedirInt("Curso del profesor: ");

        if (!listarCursoID(cursoID)) {
            System.out.println("No existe ese ID del curso");
            return;
        }

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO objetos.Profesores (datos_personales, profesor_info, curso_id) "
                    + "VALUES (ROW(?, ?), ROW(?, ?), ?)");

            statement.setString(1, nombreP);
            statement.setInt(2, edadP);
            statement.setString(3, cedula);
            statement.setString(4, departamento);
            statement.setInt(5, cursoID);

            int rowInserted = statement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Los datos de los profesores se ha insertado correctamente.");
            } else {
                System.out.println("No se podido insertar los datos de los profesores, revisa los atributos");
            }

        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }



    private void eliminarProfesor() {
        int profosorId = pedirInt("ID del profesor a eliminar: ");

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM objetos.Profesores WHERE profesor_id = ?");
            statement.setInt(1, profosorId);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("El profesor se a borrado correctamente.");
            } else {
                System.out.println("No se a podido eliminar el profesor, revisa la ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    private void modificarProfesor() {
        int profesorId = pedirInt("ID del profesor : ");
        teclado.nextLine();

        // Verificar si el profesor existe
        if (!listarProfesorID(profesorId)) {
            System.out.println("No existe ese ID del profesor");
            return;
        }

        // Obtener nuevos datos del profesor
        String nombreP = pedirString("Nombre del profesor: ");
        int edadP = pedirInt("Edad del profesor: ");
        teclado.nextLine();
        String cedula = pedirString("Cédula del profesor: ");
        String departamento = pedirString("Departamento del profesor: ");
        int cursoID = pedirInt("Curso del profesor: ");

        // Verificar si el curso existe
        if (!listarCursoID(cursoID)) {
            System.out.println("No existe ese ID con ese curso");
            return;
        }

        try {
            // Preparar la actualización del profesor
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE objetos.Profesores SET datos_personales = ROW(?, ?), profesor_info = ROW(?, ?), curso_id = ? WHERE profesor_id = ?"
            );

            // Asignar valores a la consulta
            statement.setString(1, nombreP);
            statement.setInt(2, edadP);
            statement.setString(3, cedula);
            statement.setString(4, departamento);
            statement.setInt(5, cursoID);
            statement.setInt(6, profesorId);

            // Ejecutar la actualización
            int rowUpdate = statement.executeUpdate();

            // Confirmar resultado
            if (rowUpdate > 0) {
                System.out.println("Se ha actualizado los datos del profesor correctamente.");
            } else {
                System.out.println("No se ha podido actualizar los datos del profesor.");
            }
        } catch (SQLException e) {
            System.out.println("Error en la actualización: " + e.toString());
        }
    }

    private void insertarCurso() {
        String nombreCurso = pedirString("Nombre del curso: ");

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO objetos.Cursos (nombre) VALUES (?)");
            statement.setString(1, nombreCurso);

            int rowInserted = statement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("El curso se a insertado correctamente");
            }else {
                System.out.println("No se a podido insertar el curso fíjate en los atributos");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }



    private void eliminarCurso() {
        int idCurso = pedirInt("ID del curso: ");

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM objetos.Cursos WHERE curso_id = ?");
            statement.setInt(1, idCurso);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("Se ha eliminado el curso correctamente");
            }else {
                System.out.println("No se a podido eliminar el curso, comprueba la ID");
            }

        } catch (SQLException e) {
            System.out.println("Error." + e.toString());
        }
    }

    private void modificarCurso() {
        int idCurso = pedirInt("ID del curso: ");
        teclado.nextLine();
        String nombreCurso = pedirString("Nombre del curso: ");

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE objetos.Cursos SET nombre = ? WHERE curso_id = ?");
            statement.setString(1, nombreCurso);
            statement.setInt(2, idCurso);

            int rowUpdate = statement.executeUpdate();

            if (rowUpdate > 0) {
                System.out.println("El curso se actualizado correctamente");
            }else {
                System.out.println("No se a podido actualizar fijate en la ID");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    private void inscribirEstudianteCurso() {
        int estudianteId = pedirInt( "ID del estudiante: ");
        int cursoId = pedirInt("ID del curso: ");

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO objetos.Inscripciones (estudiante_id, curso_id) VALUES (?, ?)");
            statement.setInt(1, estudianteId);
            statement.setInt(2, cursoId);

            int rowInsertd = statement.executeUpdate();

            if (rowInsertd > 0) {
                System.out.println("El estudiante se a añadido a ese curso correctamente");
            }else {
                System.out.println("No se a podido añadir al estudiante a ese curso, revisa los atributos");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    private void desinscribirEstudianCurso() {
        int estudianteId = pedirInt("ID del estudiante: ");
        int cursoId = pedirInt("ID del curso: ");

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM objetos.Inscripciones WHERE estudiante_id = ? AND curso_id = ?");
            statement.setInt(1, estudianteId);
            statement.setInt(2, cursoId);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("El estudiante se a desinscrito de ese curso correctamente");
            }else {
                System.out.println("No se a podido desinscribir ese estudiante de ese curso");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    private void inscribirProfesorCurso() {
        int profesorId = pedirInt( "ID del profesor: ");
        int cursoId = pedirInt("ID del curso: ");

        // Verificar que el profesor y el curso existan antes de proceder
        if (!listarProfesorID(profesorId)) {
            System.out.println("El ID del profesor no existe.");
            return;
        }
        if (!listarCursoID(cursoId)) {
            System.out.println("El ID del curso no existe.");
            return;
        }

        try {
            // Insertar la inscripción en la tabla 'Inscripciones'
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO objetos.Inscripciones (profesor_id, curso_id) VALUES (?, ?)"
            );
            statement.setInt(1, profesorId);
            statement.setInt(2, cursoId);

            int rowInserted = statement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("El profesor se ha añadido al curso correctamente.");
            } else {
                System.out.println("No se ha podido añadir al profesor al curso. Revisa los atributos.");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    private void desinscribirProfesorCurso() {
        int profesorId = pedirInt( "ID del profesor: ");
        int cursoId = pedirInt("ID del curso: ");

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM objetos.Inscripciones WHERE profesor_id = ? AND curso_id = ?");
            statement.setInt(1, profesorId);
            statement.setInt(2, cursoId);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("El profesor se ha desinscrito de ese curso correctamente.");
            } else {
                System.out.println("No se ha podido desinscribir a ese profesor de ese curso.");
            }
        } catch (SQLException e) {
            System.out.println("Error al desinscribir al profesor del curso: " + e.toString());
        }
    }

    private void listarEstudianteID() {
        int estudianteId = pedirInt("ID del estudiante: ");

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM objetos.Estudiantes WHERE estudiante_id = ?");
            statement.setInt(1, estudianteId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int estudianteIdRecuperado = resultSet.getInt("estudiante_id");
                Persona persona = new Persona(resultSet.getString("datos_personales"));
                Estudiante estudiante = new Estudiante(resultSet.getString("estudiante_info"));

                System.out.println("ID: " + estudianteIdRecuperado);
                System.out.println(persona);
                System.out.println(estudiante);
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());;
        }
    }

    private void listarTodosEstudiantes() {

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM objetos.Estudiantes");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int estudianteID = resultSet.getInt("estudiante_id");
                Persona persona = new Persona(resultSet.getString("datos_personales"));
                Estudiante estudiante = new Estudiante(resultSet.getString("estudiante_info"));

                System.out.println("ID: " + estudianteID);
                System.out.println(persona);
                System.out.println(estudiante);

            }
        } catch (SQLException e) {
            System.out.println("Error, comprueba que este bien los atributos." + e.toString());
        }
    }

    private boolean listarCursoID(int cursoID) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM objetos.Cursos WHERE curso_id = ?");
            statement.setInt(1, cursoID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                cursoID = resultSet.getInt("curso_id");
                String nombre = resultSet.getString("nombre");

                System.out.println("ID: " + cursoID);
                System.out.println("Nombre: " + nombre);
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
            return false;
        }
    }

    private void listarTodosCursos() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM objetos.Cursos");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int cursoID = resultSet.getInt("curso_id");
                String nombre = resultSet.getString("nombre");

                System.out.println("ID: " + cursoID);
                System.out.println("Nombre: " + nombre);
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    private boolean listarProfesorID(int profesorId) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM objetos.Profesores WHERE profesor_id = ?");
            statement.setInt(1, profesorId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                profesorId = resultSet.getInt("profesor_id");
                Persona persona = new Persona(resultSet.getString("datos_personales"));
                Profesor profesor = new Profesor(resultSet.getString("profesor_info"));

                System.out.println("ID: " + profesorId);
                System.out.println(persona);
                System.out.println(profesor);
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
            return false;
        }
    }

    private void listarTodosProfesores() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM objetos.Profesores");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int profesorID = resultSet.getInt("profesor_id");
                Persona persona = new Persona(resultSet.getString("datos_personales"));
                Profesor profesor = new Profesor(resultSet.getString("profesor_info"));

                System.out.println("ID: " + profesorID);
                System.out.println(persona);
                System.out.println(profesor);
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    private void listarEstudianteCursos() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT (e.datos_personales).nombre AS nombre_estudiante, c.nombre AS nombre_curso"
                    + " FROM objetos.Estudiantes e "
                    + " INNER JOIN objetos.Inscripciones i ON e.estudiante_id = i.estudiante_id "
                    + " INNER JOIN objetos.Cursos c ON i.curso_id = c.curso_id;");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nombreEstudiante = resultSet.getString("nombre_estudiante");
                String nombreCurso = resultSet.getString("nombre_curso");

                System.out.println("Estudiante: " + nombreEstudiante + " Asiste al curso: " + nombreCurso);
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }
    private void listarCursosEstudiante() {
        int idEstudiante = pedirInt( "ID del estudiante");

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT c.nombre AS nombre_curso "
                    + " FROM objetos.Cursos c "
                    + " INNER JOIN objetos.Inscripciones i ON c.curso_id = i.curso_id "
                    + " WHERE i.estudiante_id = ?;");

            statement.setInt(1, idEstudiante);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombreCurso = resultSet.getString("nombre_curso");

                System.out.println("Asiste al curso: " + nombreCurso);
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    private void numeroEstudiantesCurso() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT c.nombre AS nombre_curso, COUNT(e.estudiante_id) AS numero_estudiantes "
                    + " FROM objetos.Cursos c "
                    + " LEFT JOIN objetos.Inscripciones i ON c.curso_id = i.curso_id "
                    + " LEFT JOIN objetos.Estudiantes e ON i.estudiante_id = e.estudiante_id "
                    + " GROUP BY c.nombre "
                    + " ORDER BY c.nombre");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombreCurso = resultSet.getString("nombre_curso");
                int numeroEstudiantes = resultSet.getInt("numero_estudiantes");

                System.out.println("Curso: " + nombreCurso + " Asisten: " + numeroEstudiantes + " estudiantes");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }
}