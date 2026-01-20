package Ejercicios.Ej305;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controlador implements ActionListener{
    
    // TODO CONSTRUCTOR DEL CONTROLADOR CON LA VISTA Y EL MODELO
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: USAR e.getActionCommand() PARA SABER QUE SE ESTÁ EJECUTANDO
        //TODO: OBTENER PARÁMETROS DE LA VISTA, LLAMAR A SUMAR Y PINTAR EL VALOR EN LA VISTA

        if (e.getActionCommand().equals("SUMAR")) {
            String[] param = vista.getCampos();
            modelo.sumar(Integer.parseInt(param[0]), Integer.parseInt(param[1]));
            int resultado = modelo.getSuma();
            vista.escribirResultado(Integer.toString(resultado));
        }
    } 
}
