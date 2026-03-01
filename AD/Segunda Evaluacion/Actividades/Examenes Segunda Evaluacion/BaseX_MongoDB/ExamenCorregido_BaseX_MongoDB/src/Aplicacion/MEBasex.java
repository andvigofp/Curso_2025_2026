package Aplicacion;

import Conexiones.ConexionBaseX;
import org.basex.examples.api.BaseXClient;

public class MEBasex {
    private static BaseXClient sessionBaseX = ConexionBaseX.conexionBaseX();

    public void gestionConsulta(String input) {
        try {
            BaseXClient.Query query = sessionBaseX.query(input);

            while (query.more()) {
                System.out.println(query.next());
            }
            System.out.println(query.info());
        }catch (Exception ex){
            System.out.println("Error. En la consulta. "+ex.getMessage());
        }
    }

    public void eliminarPeliculta() {
        String query = "xquery for $l in /biblioteca/libro[año_publicacion < 1900]\n" +
                "return delete node $l";

        try {
            sessionBaseX.execute(query);

            System.out.println("Se a eliminado la pelicula exitosmaente");
        }catch (Exception ex){
            System.out.println("Error. al eliminar la pelicula. "+ex.getMessage());
        }
    }
}
