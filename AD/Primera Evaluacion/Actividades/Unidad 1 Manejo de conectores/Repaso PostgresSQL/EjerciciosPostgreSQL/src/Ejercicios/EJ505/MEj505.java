package Ejercicios.EJ505;


import Ejercicios.EJ506.Database;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MEj505 {
    static Scanner teclado = new Scanner(System.in);
    private static Connection connection = Database.getInstance();

    public void Menu() {
        final String menu = "1. INSERTAR MÉDICO."
                + "\n2. INSERTAR PACIENTE."
                + "\n3. INSERTAR EXAMEN MEDICO."
                + "\n4. INSERTAR CITA MEDICA."
                + "\n5. MODIFICAR MEDICO."
                + "\n6. MODIFICAR PACIENTE."
                + "\n7. MODIFICAR EXAMEN MEDICO."
                + "\n8. MODIFICAR CITA MEDICA."
                + "\n9. ELIMINAR MEDICO."
                + "\n10. ELIMINAR PACIENTE."
                + "\n11. ELIMINAR EXAMEN MEDICO."
                + "\n12. ELIMINAR CITA MEDICA."
                + "\n13. LISTAR INFORMACIÓN DE UN PACIENTE POR id."
                + "\n14. LISTAR INFORMACIÓN DE TODOS LOS PACIENTES."
                + "\n15. LISTAR INFORMACIÓN DE UN MÉDICO POR MÉDICO POR ID."
                + "\n16. LISTAR INFORMACIÓN DE TODOS LOS MÉDICOS."
                + "\n17. LISTAR INFORMACIÓN DE UNA CITAMEDICA POR ID DE UN PACIENTE."
                + "\n18. LISTAR INFORMACIÓN DE UNA CITAMEDICA POR ID DE UN MÉDICO,"
                + "\n19. LISTAR INFORMACIÓN DE TODOS LOS EXÁMENES MÉDICOS DE UN PACIENTE."
                + "\n20. LISTAR INFORMACIÓN DE UN PACIENTE BUSCANDO POR UN GRUPO SANGUÍNEO."
                + "\n21. LISTAR INFORMACIÓN DE UN MÉDICO QUE ES ATENDIDA A UN DETERMINADO PACIENTE."
                + "\n22. SALIR";

        int opcion = -1;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    insertarMedico();
                    break;
                case 2:
                    insertarPaciente();
                    break;
                case 3:
                    insertarExmaneMedico();
                    break;
                case 4:
                    insertarCitaMedica();
                    break;
                case 5:
                    modificarMedico();
                    break;
                case 6:
                    modificarPaciente();
                    break;
                case 7:
                    modificarExamenMedico();
                    break;
                case 8:
                    modificarCitaMedica();
                    break;
                case 9:
                    eliminarMedico();
                    break;
                case 10:
                    eliminarPaciente();
                    break;
                case 11:
                    eliminarExamenMedico();
                    break;
                case 12:
                    eliminarCitaMedica();
                    break;
                case 13:
                    listarInformacionPacienteID();
                    break;
                case 14:
                    listarInformacionTodosPacientes();
                    break;
                case 15:
                    listarInformacionMedicoID();
                    break;
                case 16:
                    listarInformacionTodosMedicos();
                    break;
                case 17:
                    listarInformacionCitaMedicaIDPaciente();
                    break;
                case 18:
                    listarInformacionCitaMedicaIDMedico();
                    break;
                case 19:
                    listarInformacionTodosExamenesMedicosPacientes();
                    break;
                case 20:
                    listarInformacionPacienteGrupoSanguineo();
                    break;
                case 21:
                    listarInformacionMedicoAtendidoPaciente();
                    break;
                case 22:
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Error. Solo se puedes elegir entre las opciones entre (1-22)");
            }
        }while (opcion !=22);
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
                return teclado.nextLine();
            } catch (Exception e) {
                System.out.println("Error. " + e.toString());
            }
        }
    }

    private void insertarMedico() {
        String nombre = pedirString("Ingrese el nombre del médico: ");
        int edad = pedirInt("Ingrese la edad del médico: ");
        teclado.nextLine();
        String codigoMedico = pedirString("Ingrese el código del médico: ");
        String especialidad = pedirString("Ingrese la especialidad del médico: ");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO objetos.Medicos (datos_personales, medico_info) VALUES (ROW(?, ?), ROW(?, ?))");
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, edad);
            preparedStatement.setString(3, codigoMedico);
            preparedStatement.setString(4, especialidad);

            int rowInserted = preparedStatement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Médico insertado correctamente.");
            }else {
                System.out.println("No se a podido insertar correctamente");
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.toString());
        }
    }

    private void insertarPaciente() {
        String nombre = pedirString("Ingrese el nombre paciente: ");
        int edad = pedirInt("Ingrese la edad del paciente");
        teclado.nextLine();
        String numeroHistorial = pedirString("Ingrese el número del historial del paciente: ");
        String grupoSanguineo = pedirString("Ingrese el grupo sanguineo delpaciente: ");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO objetos.Pacientes (datos_personales, paciente_info) VALUES (ROW(?, ?), ROW(?, ?))");
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, edad);
            preparedStatement.setString(3, numeroHistorial);
            preparedStatement.setString(4, grupoSanguineo);

            int rowInserted = preparedStatement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Paciente insertado correctamente.");
            }else {
                System.out.println("No se a podido insertar el paciente.");
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.toString());
        }
    }

    private void insertarExmaneMedico() {
        int idPaciente = pedirInt("Ingrese la id del paciente: ");
        teclado.nextLine();
        String nombreExamen = pedirString("Ingrese el nombre del examen del paciente a realizar: ");
        String fechaRealizacion = pedirString("Ingrese la fecha de la realización de la prueba: ");
        String resultado = pedirString("Inrese el resultado de la prueba");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO objetos.ExamenesMedicos (paciente_id, examen_info) VALUES (ROW(?, ?), ROW(?, ?))");
            preparedStatement.setInt(1, idPaciente);
            preparedStatement.setString(2, nombreExamen);
            preparedStatement.setString(3, fechaRealizacion);
            preparedStatement.setString(4, resultado);

            int rowInserted = preparedStatement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("El exmamen médico se a insertado correctamente.");
            }else {
                System.out.println("No se a podido insertar el examen médico.");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    private void insertarCitaMedica() {
        String fechaHora = pedirString("Inrese la fecha y hora de la cita: ");
        int pacienteID = pedirInt("Ingsrese el id del paciente: ");
        teclado.nextLine();
        int medicoID = pedirInt("Ingrse el id del médico: ");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO objetos.CitasMedica (fecha_hora, paciente_id, medico_id)  VALUES (?, ?, ?)");
            preparedStatement.setString(1, fechaHora);
            preparedStatement.setInt(2, pacienteID);
            preparedStatement.setInt(3, medicoID);

            int rowInserted = preparedStatement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Se a insertado la cita medica correctamente.");
            }else {
                System.out.println("No se a podido insertar la cita médica");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    private void modificarMedico() {
        int medicoId = pedirInt("Ingrese el ID del médico a modificar: ");
        teclado.nextLine();
        String nuevoNombre = pedirString("Ingrese ek nuevo del médico: ");
        int nuevaEdad = pedirInt("Ingrese el nueva edad del médico: ");
        teclado.nextLine();
        String nuevoCodigoMedico = pedirString("Ingrese el nuevo código del médico: ");
        String nuevaEspecialidad = pedirString("Ingrese la nueva especialidad del médico: ");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE objetos.Medicos SET (datos_personales = ROW(?, ?), medico_info = ROW(?, ?) WHERE medico_id = ?)");

            preparedStatement.setString(1, nuevoNombre);
            preparedStatement.setInt(2, nuevaEdad);
            preparedStatement.setString(3, nuevoCodigoMedico);
            preparedStatement.setString(4, nuevaEspecialidad);
            preparedStatement.setInt(5, medicoId);

            int rowUpdate = preparedStatement.executeUpdate();

            if (rowUpdate > 0) {
                System.out.println("Se a modificado los datos del médico correctamente.");
            }else {
                System.out.println("No se a podido modificar los datos del médico.");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    private void modificarPaciente() {
        int pacienteId = pedirInt("Ingrese el ID del paciente a modificar: ");
        teclado.nextLine(); //Limpiar el buffer
        String nuevoNombre = pedirString("Ingrese el nuevo nombre del paciente: ");
        int nuevaEdad = pedirInt("Ingrese la nueva edad del paciente: ");
        teclado.nextLine(); //Limpiar el buffer
        String nuevoNumeroHistoria = pedirString("Ingrese el nuevo número de historia del paciente: ");
        String nuevoGrupoSanguineo = pedirString("Ingrese el nuevo grupo sanguíneo del paciente: ");

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE objetos.Pacientes SET datos_personales = ROW(?, ?), paciente_info = ROW(?, ?) WHERE paciente_id = ?");

            statement.setString(1, nuevoNombre);
            statement.setInt(2, nuevaEdad);
            statement.setString(3, nuevoNumeroHistoria);
            statement.setString(4, nuevoGrupoSanguineo);
            statement.setInt(5, pacienteId);

            int rowUpdate = statement.executeUpdate();

            if (rowUpdate > 0) {
                System.out.println("Los datos del paciente se a modificado correctamente");
            }else {
                System.out.println("No se pudo modificar los datos del paciente");
            }

        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    private void modificarExamenMedico() {
        int examenId = pedirInt("Ingrese el ID del examen médico a modificar: ");
        teclado.nextLine();
        String nuevoNombreExamen = pedirString("Ingrese el nuevo nombre del examen médico: ");
        String nuevaFechaRealizacion = pedirString("Ingrese la nueva fecha de realización del examen médico (YYYY-MM-DD): ");
        String nuevoResultado = pedirString("Ingrese el nuevo resultado del examen médico: ");

        if (!nuevaFechaRealizacion.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Formato de fecha incorrecto. Debe ser YYYY-MM-DD.");
            return;
        }

        // Convertir la fecha a tipo java.sql.Date (se asume que la fecha está en formato correcto)
        java.sql.Date fecha = java.sql.Date.valueOf(nuevaFechaRealizacion);

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE objetos.ExamenesMedicos SET examen_info = ROW(?, ?, ?) WHERE examen_id = ?");

            statement.setString(1, nuevoNombreExamen);
            statement.setDate(2, fecha);
            statement.setString(3, nuevoResultado);
            statement.setInt(4, examenId);

            int rowUpdate = statement.executeUpdate();

            if (rowUpdate > 0) {
                System.out.println("Examen médico modificado correctamente");
            } else {
                System.out.println("No se a podido modificar el examen médico");
            }
        } catch (SQLException | InputMismatchException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para modificar una cita médica
   private void modificarCitaMedica() {
        int citaId = pedirInt("Ingrese el ID de la cita médica a modificar: ");
        teclado.nextLine(); //Limpiar el buffer
        String fechaHoraStr = pedirString("Ingrese la nueva fecha y hora de la cita médica (YYYY-MM-DD HH:mm:ss): ");
        int nuevoPacienteId = pedirInt("Ingrese el nuevo ID del paciente para la cita médica: ");
        teclado.nextLine();
        int nuevoMedicoId = pedirInt("Ingrese el nuevo ID del médico para la cita médica: ");

        try {
            // Convertir la fecha y hora ingresada a un objeto Timestamp
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date parsedDate = dateFormat.parse(fechaHoraStr);
            Timestamp nuevaFechaHora = new Timestamp(parsedDate.getTime());

            PreparedStatement statement = connection.prepareStatement("UPDATE objetos.CitasMedicas SET fecha_hora = ?, paciente_id = ?, medico_id = ? WHERE cita_id = ?");

            statement.setTimestamp(1, nuevaFechaHora);
            statement.setInt(2, nuevoPacienteId);
            statement.setInt(3, nuevoMedicoId);
            statement.setInt(4, citaId);

            int rowUpdate = statement.executeUpdate();

            if (rowUpdate > 0) {
                System.out.println("Cita médica modificada correctamente.");
            }else {
                System.out.println("No se a podido modificar una cita médica.");
            }

        } catch (SQLException |ParseException e) {
            System.out.println("Error. " + e.toString());
        }
   }

    //Método para eliminar un médico de BD hospital
    private void eliminarMedico() {
        int medicoId = pedirInt("Ingrese el ID del médico a eliminar: ");

        try {
            // Primero, eliminar las citas médicas asociadas al médico
            PreparedStatement deleteCitasStmt = connection.prepareStatement("DELETE FROM objetos.CitasMedicas WHERE medico_id = ?");
            deleteCitasStmt.setInt(1, medicoId);
            int rowDeleteCita = deleteCitasStmt.executeUpdate(); // Ejecuta una sola vez

            if (rowDeleteCita > 0) {
                System.out.println("Citas médicas asociadas al médico eliminadas correctamente.");
            } else {
                System.out.println("No se ha encontrado ninguna cita médica asociada al médico.");
            }

            // Luego, eliminar el médico
            PreparedStatement statement = connection.prepareStatement("DELETE FROM objetos.Medicos WHERE medico_id = ?");
            statement.setInt(1, medicoId);
            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("Médico eliminado correctamente.");
            } else {
                System.out.println("No se ha podido eliminar el médico.");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para eliminar un paciente
    private void eliminarPaciente() {
        int pacienteId = pedirInt("Ingrese el ID del paciente a eliminar: ");

        try {
            // Primero, eliminar el examen médico asociado al paciente
            PreparedStatement deleteExamenMedicosStmt = connection.prepareStatement("DELETE FROM objetos.ExamenesMedicos WHERE paciente_id = ?");
            deleteExamenMedicosStmt.setInt(1, pacienteId);
            int rowDeleteExamenMedico = deleteExamenMedicosStmt.executeUpdate(); // Ejecuta una sola vez

            if (rowDeleteExamenMedico > 0) {
                System.out.println("Examen médico asociadas al paciente eliminadas correctamente.");
            } else {
                System.out.println("No se ha encontrado ningún examen médico asociada al paciente.");
            }

            // Luego, eliminar el paciente
            PreparedStatement statement = connection.prepareStatement("DELETE FROM objetos.Pacientes WHERE paciente_id = ?");

            statement.setInt(1, pacienteId);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("Paciente eliminado correctamente.");
            }else {
                System.out.println("No se a podido eliminar ese paciente");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para eliminar un examen médico de la BD hospital
    private void eliminarExamenMedico() {
        int examenId = pedirInt("Ingrese el ID del examen médico a eliminar: ");

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM objetos.ExamenesMedicos WHERE examen_id = ?");

            statement.setInt(1, examenId);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("Examen médico eliminado correctamente.");
            }else {
                System.out.println("No se podido eliminar el examen médico.");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para eliminar una cita médica
    private void eliminarCitaMedica() {
        int citaId = pedirInt("Ingrese el ID de la cita médica a eliminar: ");

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM objetos.CitasMedicas WHERE cita_id = ?");

            statement.setInt(1, citaId);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("Cita médica eliminada correctamente.");
            }else {
                System.out.println("No se a podido eliminar una cita médica.");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }


    //Método para listar la información de un Paciente por ID
    private void listarInformacionPacienteID() {
        int pacienteID = pedirInt("Ingrese el ID del paciente para ver la información: ");

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT paciente_id, " +
                    " (datos_personales).nombre AS nombre, " +
                    " (datos_personales).edad AS edad, " +
                    " (paciente_info).numero_historia AS numero_historia, " +
                    " (paciente_info).grupo_sanguineo AS grupo_sanguineo " +
                    " FROM objetos.Pacientes " +
                    " WHERE paciente_id = ?");

            statement.setInt(1, pacienteID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("\nInformación del paciente con ID: " + pacienteID);
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Edad: " + resultSet.getInt("edad"));
                System.out.println("Número de Historia: " + resultSet.getString("numero_historia"));
                System.out.println("Grupo Sanguíneo: " + resultSet.getString("grupo_sanguineo"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar toda la información de los pacientes
    private void listarInformacionTodosPacientes() {
        try {
            ////Consulta para obtener todos los pacientes
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT paciente_id, " +
                            " (datos_personales).nombre, (datos_personales).edad, " +
                            " (paciente_info).numero_historia, (paciente_info).grupo_sanguineo " +
                            " FROM objetos.Pacientes"
            );

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Mostrar los resultados
            while (resultSet.next()) {
                System.out.println("\nID: " + resultSet.getInt("paciente_id"));
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Edad: " + resultSet.getInt("edad"));
                System.out.println("Número de Historia: " + resultSet.getString("numero_historia"));
                System.out.println("Grupo Sanguíneo: " + resultSet.getString("grupo_sanguineo"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar la información médica por ID
    private void listarInformacionMedicoID() {
        int medicoID = pedirInt("Ingrese el ID del médico para ver la información: ");

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT medico_id, " +
                    " (datos_personales).nombre AS nombre, " +
                    " (datos_personales).edad AS edad, " +
                    " (medico_info).codigo_medico AS codigo_medico, " +
                    " (medico_info).especialidad AS especialidad " +
                    " FROM objetos.Medicos " +
                    " WHERE medico_id = ?");

            statement.setInt(1, medicoID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("\nInformación del médico con ID: " + medicoID);
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Edad: " + resultSet.getInt("edad"));
                System.out.println("Código de Médico: " + resultSet.getString("codigo_medico"));
                System.out.println("Especialidad: " + resultSet.getString("especialidad"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar toda la información de todos los médicos
    private void listarInformacionTodosMedicos() {
        try {
            //Consulta para obtener todos los médicos
            PreparedStatement statement = connection.prepareStatement("SELECT medico_id, " +
                    " (datos_personales).nombre, (datos_personales).edad, " +
                    " (medico_info).codigo_medico, (medico_info).especialidad " +
                    " FROM objetos.Medicos");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("\nID: " + resultSet.getInt("medico_id"));
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Edad: " + resultSet.getInt("edad"));
                System.out.println("Código Médico: " + resultSet.getString("codigo_medico"));
                System.out.println("Especialidad: " + resultSet.getString("especialidad"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }


    //Método para listar la información de una cita Médica por ID de un Paciente
    private void listarInformacionCitaMedicaIDPaciente() {
        int pacienteID = pedirInt("Ingrese el ID del paciente para listar las citas médicas: ");

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM objetos.CitasMedicas WHERE paciente_id = ?");

            statement.setInt(1, pacienteID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("\nID: " + resultSet.getInt("cita_id"));
                System.out.println("Fecha y Hora: " + resultSet.getString("fecha_hora"));
                System.out.println("Paciente ID: " + resultSet.getInt("paciente_id"));
                System.out.println("Médico ID: " + resultSet.getInt("medico_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar la información de una cita Médica por ID de un Médico
    private void listarInformacionCitaMedicaIDMedico() {
        int medicoID = pedirInt("Ingrese el ID del médico para listar las citas médicas: ");

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM objetos.CitasMedicas WHERE medico_id = ?");

            statement.setInt(1, medicoID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("\nID: " + resultSet.getInt("cita_id"));
                System.out.println("Fecha y Hora: " + resultSet.getString("fecha_hora"));
                System.out.println("Paciente ID: " + resultSet.getInt("paciente_id"));
                System.out.println("Médico ID: " + resultSet.getInt("medico_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar información de todos los exámenes médicos de un paciente
    private void listarInformacionTodosExamenesMedicosPacientes() {
        int pacienteID = pedirInt("Ingese el ID del paciente para listar los exámenes médicos: ");

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT examen_id, " +
                    " (examen_info).nombre_examen, (examen_info).fecha_realizacion, (examen_info).resultado " +
                    " FROM objetos.ExamenesMedicos " +
                    " WHERE paciente_id = ?");

            statement.setInt(1, pacienteID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("\nID de Examen: " + resultSet.getInt("examen_id"));
                System.out.println("Nombre del Examen: " + resultSet.getString("nombre_examen"));
                System.out.println("Fecha de Realización: " + resultSet.getDate("fecha_realizacion"));
                System.out.println("Resultado: " + resultSet.getString("resultado"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar la información de un paciente por su grupo sanquineo
    private void listarInformacionPacienteGrupoSanguineo() {
        String grupoSanguineo = pedirString( "Ingrese el grupo sanguineo para listar los pacientes: ").toUpperCase();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT paciente_id, " +
                    " (datos_personales).nombre, (datos_personales).edad, " +
                    " (paciente_info).numero_historia, (paciente_info).grupo_sanguineo " +
                    " FROM objetos.Pacientes " +
                    " WHERE (paciente_info).grupo_sanguineo = ?");

            statement.setString(1, grupoSanguineo);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("\nID: " + resultSet.getInt("paciente_id"));
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Edad: " + resultSet.getInt("edad"));
                System.out.println("Número de Historia: " + resultSet.getString("numero_historia"));
                System.out.println("Grupo Sanguíneo: " + resultSet.getString("grupo_sanguineo"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar la información de un médico atendido por un paciente
    private void listarInformacionMedicoAtendidoPaciente() {
        int pacienteID = pedirInt("Ingrese el ID del paciente para listar el médico que lo atendió:");

        String query = "SELECT m.medico_id, (m.datos_personales).nombre AS medico_nombre, (m.datos_personales).edad AS medico_edad, "
                + "(m.medico_info).codigo_medico, (m.medico_info).especialidad "
                + "FROM objetos.Medicos m "
                + "JOIN objetos.CitasMedicas c ON m.medico_id = c.medico_id "
                + "WHERE c.paciente_id = ?";


        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pacienteID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Mostrar los resultados si existen
                    System.out.println("\nMédico atendiendo al paciente ID: " + pacienteID);
                    System.out.println("ID Médico: " + resultSet.getInt("medico_id"));
                    System.out.println("Nombre Médico: " + resultSet.getString("medico_nombre"));
                    System.out.println("Edad Médico: " + resultSet.getInt("medico_edad"));
                    System.out.println("Código Médico: " + resultSet.getString("codigo_medico"));
                    System.out.println("Especialidad Médico: " + resultSet.getString("especialidad"));
                } else {
                    System.out.println("No se encontró un médico atendiendo a este paciente.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar la información del médico: " + e.toString());
        }
    }
}



