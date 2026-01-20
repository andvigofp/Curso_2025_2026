package Ejercicios.Ej306;

import java.sql.SQLException;

/**
 * Desarrolla una aplicación, siguiendo el patrón MVC, que muestre los datos de un empleado con un número determinado.
 * Para ello puedes utilizar el siguiente proyecto como plantilla.
 *
 * TODO
 * En el proyecto de plantilla hay comentarios marcados con la palabra TODO que indica lo que habría que hacer.
 */

public class main {
    
    public static void main(String[] args) throws SQLException {
        
        // TODO: INSTANCIAR EL MODELO, LA VISTA Y EL CONTROLADOR
        // TODO: ARRANCAR LA VISTA Y ESTABLECERLER EL CONTROLADOR
        //El modelo
        Modelo modelo = new Modelo();
        //La vista
        Vista vista = new Vista();
        //EL controlador
        Controlador controlador = new Controlador(vista, modelo);
        //Arrancar  la interfaz (vista)
        vista.arrancar();
        //Configurar la vista
        vista.setControlador(controlador);
    }
    
}
