package Ejercicios.Ej306;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener{
    
    // TODO: CONSTRUCTOR DEL CONTROLADOR CON LA VISTA Y EL MODELO
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: USAR e.getActionCommand() PARA SABER QUE SE ESTÁ EJECUTANDO
        //TODO: OBTENER PARÁMETROS DE LA VISTA, OBTENER INFORMACIÓN USUARIO Y PINTAR EL VALOR EN LA VISTA
        if (e.getActionCommand().equals("BUSCAR")) {
            String resultado = modelo.obtenerDatosEmpleado(vista.getNumero());
            vista.datosAtabla(resultado);
        }
    } 
}
