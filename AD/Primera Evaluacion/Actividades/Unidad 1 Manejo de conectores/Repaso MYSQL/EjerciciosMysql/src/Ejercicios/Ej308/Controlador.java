package Ejercicios.Ej308;

import Ejercicios.Ej308.Modelo;
import Ejercicios.Ej308.Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador implements ActionListener{

	// TODO: CONSTRUCTOR DEL CONTROLADOR CON LA VISTA Y EL MODELO
	private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void actualizarTabla() {
        ArrayList<Student> listaEstudiantes = modelo.getStudentsList();
        vista.actualizarDatos(listaEstudiantes);


    }

    @Override
	public void actionPerformed(ActionEvent e) {
		//TODO: USAR e.getActionCommand() PARA SABER QUE SE EST� EJECUTANDO
        //TODO: OBTENER PAR�METROS DE LA VISTA, OBTENER INFORMACI�N DE LOS ALUMNOS Y ACTUALIZAR VALOR DE LA TABLA

        String[] datos = vista.getDatos();
        if (e.getActionCommand().equals("GUARDAR")) {
            Student student = modelo.getStudent(datos[0]); // Ver si existe
            Student new_student = new Student(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]));

            if (student == null) {
                // No existe → insertar
                modelo.addStudent(new_student);
            } else {
                // Sí existe → modificar
                modelo.modifyStudent(new_student);
            }

        }else if(e.getActionCommand().equals("ACTUALIZAR")) {
            Student new_student = new Student(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]));
            modelo.modifyStudent(new_student);

        }else if(e.getActionCommand().equals("BORRAR")) {
            if (!modelo.getStudent(datos[0]).getId().equals(null)) {
                modelo.deleteStudent(datos[0]);
            }
        }
        ArrayList<Student> listaEstudiantes = modelo.getStudentsList();
        vista.actualizarDatos(listaEstudiantes);
    }
}
