package Ejercicios.Ejercicio404;

import org.basex.examples.api.BaseXClient;

public class Ejercicio404 {
    public static void main(String[] args) {

        Metodos metodos = new Metodos();

        //1. Título y editorial de todos los libros.
        //Los datos de cada libro deben estar dentro de un elemento <libro>.
        //El título y la editorial de cada libro deben estar separados por un guión medio (-).
        /**metodos.ejecutarConsulta("for $x in /biblioteca/libros/libro\n" +
                "return <libro>{ concat($x/titulo/text(), ' - ', $x/editorial/text()) }</libro>");**/

        //2. El título de todos los libros de menos de 400 páginas.
        //Se debe obtener únicamente los datos, sin etiquetas.
        /**metodos.ejecutarConsulta("for $libro in /biblioteca//libro\n" +
                "where $libro/paginas < 400\n" +
                "return data($libro)");**/

        //3. La cantidad de libros de más de 400 páginas.
        /**metodos.ejecutarConsulta("count(\n" +
                "for $libro in /biblioteca//libro\n" +
                "where $libro/paginas > 400\n" +
                "return $libro\n" +
                ")");**/

        //4. Una lista HTML con el título de los libros de la editorial O'Reilly Media ordenados por título.
        /**metodos.ejecutarConsulta("<libro>\n" +
                "{\n" +
                "  for $libro in /biblioteca//libro\n" +
                "  where $libro/editorial = \"O'Reilly Media\"\n" +
                "  order by $libro/titulo\n" +
                "  return <li>{$libro/titulo}</li>\n" +
                "}\n" +
                "</libro>\n");**/

        //5. Título y editorial de los libros de 2018 y 2019.
        //Los datos de cada libro deben estar dentro de un elemento <libro>.
        //El título y la editorial deben ir dentro de los elementos <titulo> y <editorial> respectivamente.
        /**metodos.ejecutarConsulta("for $libro in /biblioteca//libro\n" +
                "where $libro[@publicacion = \"2018\" or @publicacion = \"2019\"]\n" +
                "return <libro>{$libro/titulo, $libro/editorial}</libro>");**/

        //6. Título y editorial de los libros con más de un autor.
        //Los datos de cada libro deben estar dentro de un elemento <libro>.
        //El título y la editorial deben ir dentro de los elementos <titulo> y <editorial> respectivamente.
        /**metodos.ejecutarConsulta("for $libro in /biblioteca//libro\n" +
                "where count($libro/autor) >1\n" +
                "return <libro>{$libro/titulo, $libro/editorial}</libro>");**/

        //7. Título y año de publicación de los libros que tienen versión electrónica.
        //Los datos de cada libro deben estar dentro de un elemento <libro>.
        //El título y el año de publicación deben ir dentro de los elementos <titulo> y <fecha-publicacion> respectivamente.
        /**metodos.ejecutarConsulta("\n" +
                "for $libro in /biblioteca//libro\n" +
                "where $libro/edicionElectronica\n" +
                "return \n" +
                "<libro>\n" +
                "{$libro/titulo}\n" +
                "<fecha-publicacion>{data($libro/@publicacion)}</fecha-publicacion>\n" +
                "</libro>\n");**/

        //8. Título de los libros que no tienen versión electrónica.
        //Se debe obtener únicamente los datos, sin etiquetas.
        metodos.ejecutarConsulta("for $libro in /biblioteca//libro\n" +
                "where not($libro/edicionElectronica)\n" +
                "return data($libro)\n");
    }
}
