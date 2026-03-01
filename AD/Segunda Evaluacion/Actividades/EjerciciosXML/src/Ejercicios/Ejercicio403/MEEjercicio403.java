package Ejercicios.Ejercicio403;

import org.basex.examples.api.BaseXClient;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MEEjercicio403 {
    private static Scanner teclado = new Scanner(System.in);

    int opcion=-1;
    String nombreBd;

    final String menuOne = "1. Crear base de datos\n" +
                        "2. Seleccionar base de datos\n" +
                        "3. Eliminar base de datos\n" +
                        "4. Salir";

    public void metodos() {
        try(BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {

        do {
            System.out.println(menuOne);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    nombreBd = pedirString("Introduzca el nombre de la base de datos: ");
                    crearBd(session, nombreBd);
                    break;
                case 2:
                    nombreBd = pedirString("Introduzca el nombre de la base de datos: ");
                    seleccionarBD(session, nombreBd);
                    break;
                case 3:
                    nombreBd = pedirString("Introduzca el nombre de la base de datos: ");
                    eliminarBD(session, nombreBd);
                    break;
                case 4:
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Error. Solo puedes elegir entre las opciones (1-4)");
            }
        }while (opcion !=4);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }




    private void crearBd(BaseXClient session, String nombreBd) {
        try (BaseXClient.Query query = session.query("for $i in db:get('" + nombreBd + "') return $i")){

            if (query.more()) {
                System.out.println("La base de datos ya existe: " + nombreBd);
            }
        }catch (Exception e) {
            try {
                session.execute("create db " + nombreBd);
                System.out.println("Base de datos creada: " + nombreBd);
            } catch (IOException ex) {
                System.out.println("Error al crear la base de datos: ");
                e.printStackTrace();
            }
        }
    }

    final String menuTwo = "1. Gestión de documentos\n" +
            "2. Realizar consultas\n" +
            "3. Atras";

    private void seleccionarBD(BaseXClient session, String nombreBd) {
        try(BaseXClient.Query query = session.query("db:get('" + nombreBd + "')" )) {
            do {
                System.out.println(menuTwo);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion){
                    case 1:
                        gestionDocumentos(session, nombreBd);
                        break;
                    case 2:
                        realizarConsultas(session, nombreBd);
                        break;
                    case 3:
                        metodos();
                        break;
                    default:
                        System.out.println("Error. Solo puedes elegir entre las opciones (1-3)");
                }
            }while (opcion !=3);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void eliminarBD(BaseXClient session, String nombreBd) {
        try(BaseXClient.Query query = session.query("db:get('" + nombreBd + "')" )) {

            // Comprobación e iteración de los resultados
            session.execute("drop db " + nombreBd);

        }catch (Exception e){
            System.out.println("Error al eliminar la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    final String menuThree = "1. Añadir nuevo documento\n" +
            "2. Modificar documento\n" +
            "3. Eliminar documentos.\n" +
            "4. Atrás";

    private void gestionDocumentos(BaseXClient session, String nombreBd) {
        try {
            session.execute("open " + nombreBd);

            do {
                System.out.println(menuThree);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion){
                    case 1:
                        anhadirDocumento(session, nombreBd);
                        break;
                    case 2:
                        modificarDocumento(session, nombreBd);
                        break;
                    case 3:
                        eliminarDocumento(session, nombreBd);
                        break;
                    case 4:
                        seleccionarBD(session, nombreBd);
                        break;
                    default:
                        System.out.println("Error. Solo puedes elegir entre las opciones (1-4)");
                }
            }while (opcion !=4);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    final String menuFour = "1. Listar número total de documentos\n" +
            "2. Listar por campos\n" +
            "3. Atrás";

    private void realizarConsultas(BaseXClient session, String nombreBd) {
        do {
            System.out.println(menuFour);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    String numero = ejecutarConsultaUnitaria(session, "count(for $i in db:get('" + nombreBd + "') return $i)");
                    System.out.println(numero);
                    break;
                case 2:
                    String estructura = ejecutarConsultaUnitaria(session, "let $documentos := for $i in db:get('" + nombreBd + "')/ return $documentos[1]");
                    Document doc = parsear(estructura);
                    break;
                case 3:
                    seleccionarBD(session, nombreBd);
                    break;
                default:
                    System.out.println("Error. Solo puedes elegir entre las opciones (1-3)");

            }
        }while (opcion !=3);
    }


    private String ejecutarConsultaUnitaria(BaseXClient session, String consulta) {
        try(BaseXClient.Query query = session.query(consulta )) {

            // Comprobación e iteración de los resultados
            if(query.more()) {
                return query.next();
            }
        }catch (Exception e){}
        return null;
        }

    private Document parsear(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        return doc;
    }


    private void anhadirDocumento(BaseXClient session, String nombreBd) {
        int num = Integer.parseInt(ejecutarConsultaUnitaria(session, "count(for $i in db:get('" + nombreBd + "') return $i)"));

        do{
            String documento = pedirXML("Introduzca el documento XML");
            try{
                Document doc = parsear(documento);
                doc.getDocumentElement().normalize();
                session.add(nombreBd + "/doc" + num + ".xml", new ByteArrayInputStream(documento.getBytes()));
                return;
            }catch (Exception e) {
                System.out.println("El documento no es válido. Vuelve a introducirlo");
            }
        }while(true);
    }

    private String pedirXML(String introduzcaElDocumentoXml) {
        StringBuilder fileXML = new StringBuilder();
        System.out.println(introduzcaElDocumentoXml);
        String linea;
        while(!(linea = teclado.next()).equalsIgnoreCase("fin"))
            fileXML.append(linea);

        return fileXML.toString();
    }

    private void modificarDocumento(BaseXClient session, String nombreBd) {
        ArrayList<String> resultados = ejecutarConsultaMultiple(session, "for $i in db:get('" + nombreBd + "') return $i");
        for(int i = 0; i < resultados.size(); i++)
            System.out.println(i + " --> " + resultados.get(i));

        int posicion = pedirInt("Indique la posición del documento a reemplazar: ");
        String resultado = "";
        do {
            String campoModificar = pedirString("Introduzca el campo que quiere cambiar: ");
            String nuevoValorCampo = pedirString("Nuevo valor del campo: ");

            resultado = ejecutarConsultaUnitaria(session, "replace value of node db:get('" + nombreBd + "')[" + (posicion + 1) + "]//" + campoModificar + " " +
                    "with '" + nuevoValorCampo + "'");
        }while(resultado != null);
    }

    private void eliminarDocumento(BaseXClient session, String nombreBd) {
        try {
            ArrayList<String> resultados = ejecutarConsultaMultiple(session, "for $i in db:get('" + nombreBd + "') return $i");
            for(int i = 0; i < resultados.size(); i++)
                System.out.println(i + " --> " + resultados.get(i));

            int posicion = pedirInt("Indique la posición del documento a reemplazar: ");
            if(resultados.size() == 1 && posicion == 0){
                session.execute("drop database " + nombreBd);
            }else{
                ejecutarConsultaUnitaria(session, "delete node db:get('" + nombreBd + "')[" + (posicion + 1) + "]");
            }
        }catch (IOException e) {
            System.out.println("Error al eliminar el documento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private ArrayList<String> ejecutarConsultaMultiple(BaseXClient session, String consulta) {
        ArrayList<String> lista = new ArrayList<>();
        // Comprobamos si existe o si no la BD
        try(BaseXClient.Query query = session.query(consulta )) {

            // Comprobación e iteración de los resultados
            while(query.more()) {
                lista.add(query.next());
            }
        }catch (Exception e){
            System.out.println("Error en la consulta: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    private static String pedirString(String mensaje) {
        while(true){
            try{
                System.out.println(mensaje);
                return teclado.nextLine();
            }catch (Exception e){}
        }
    }

    private static int pedirInt(String mensaje) {
        while(true){
            try{
                System.out.println(mensaje);
                return teclado.nextInt();
            }catch (Exception e){}
        }
    }
}
