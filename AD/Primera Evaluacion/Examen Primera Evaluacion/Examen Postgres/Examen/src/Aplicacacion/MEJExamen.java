package Aplicacacion;

import Conexion.DatabasePostgres;

import java.sql.*;
import java.util.Scanner;

public class MEJExamen {
    private static Connection conn = DatabasePostgres.getInstance();
    private static Scanner teclado = new Scanner(System.in);

    public void Menu() {
        final String menu =  "1. Crear esquema desguace\n" +
                "2. Crear tipo Componente\n" +
                "3. Insertar nueva venta\n" +
                "4. Actualizar la información de un coche\n" +
                "5. Eliminar la información de un coche\n" +
                "6. Consulta 1\n" +
                "7. Consulta 2\n" +
                "8. Consulta 3\n" +
                "9. Consulta 4\n" +
                "10. Consulta 5\n" +
                "11. Consulta 6\n" +
                "12. Salir";

        int opcion = -1;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    crearEsquemaDesguace();
                    break;
                case 2:
                    crearTipoComponenete();
                    break;
                case 3:
                    crearNuevaVenta(1, 2, "2000-04-12", 23.00);
                    break;
                case 4:
                    actualizarInformacionCoche("Ford Fiestra", 1);
                    break;
                case 5:
                    eliminarInformacionCocheVentasClientes(1);
                    break;
                case 6:
                    obtenerEdadPromedio();
                    break;
                case 7:
                    mostrarNombreVendedorEspecialidad();
                    break;
                case 8:
                    ListarCochesClientesMayoresTreinta();
                    break;
                case 9:
                    obtenerDetallesCocheNombreVendedor();
                    break;
                case 10:
                    mostrarNombreVendedoresTotalVentas();
                    break;
                case 11:
                    ListarNombreEspecialidadSinVentas();
                    break;
                case 12:
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Error: Solo puedes elegir entre las opciones (1-12)");
            }
        }while (opcion != 12);
    }

    private void ListarNombreEspecialidadSinVentas() {
        String sql = """
                	select (v.datos_personales).cedula AS nombre,
                    (v.datos_personales).especialidad
                	from concesionario.Vendedores v
                	where not exists (
                    select
                    from concesionario.Coches c
                    inner join concesionario.Ventas vt ON vt.coche_id = c.coche_id
                    where c.vendedor_id = v.vendedor_id
                );
                """;

        try {
            PreparedStatement smt = conn.prepareStatement(sql);
            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                System.out.println("Nombre Vendedor: " + rs.getString(1));
                System.out.println("Especialidad: " + rs.getString(2));

                System.out.println("-------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.toString());
        }
    }

    private void mostrarNombreVendedoresTotalVentas() {
        String sql = """
                	select (ve.datos_personales).cedula as nombre, count(v.venta_id) as total_ventas
                	from concesionario.Coches c
                	inner join concesionario.Vendedores ve on ve.vendedor_id = c.vendedor_id
                	inner join concesionario.Ventas v on v.coche_id = c.coche_id
                	group by (ve.datos_personales).cedula;
                """;

        try {
            PreparedStatement smt = conn.prepareStatement(sql);
            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                System.out.println("Nombre Vendedor: " + rs.getString(1));
                System.out.println("Total Ventas: " + rs.getInt(2));

                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.toString());
        }
    }

    private void obtenerDetallesCocheNombreVendedor() {
        String sql = """
                select (c.detalles_coche).modelo as modelo, (c.detalles_coche).año_fabricacion as fecha,\s
                (v.datos_personales).cedula as nombre
                from concesionario.Coches c
                inner join concesionario.Vendedores v on c.vendedor_id = v.vendedor_id;
                """;

        try {
            PreparedStatement smt = conn.prepareStatement(sql);
            ResultSet rs = smt.executeQuery();

            while(rs.next()) {
                System.out.println("Modelo: " + rs.getString(1));
                System.out.println("Año Frabricación: " + rs.getInt(2));
                System.out.println("Nombre Vendedor: " + rs.getString(3));

                System.out.println("--------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.toString());
        }
    }

    private void mostrarNombreVendedorEspecialidad() {
        String sql = """
                select (datos_personales).cedula as nombre, (datos_personales).especialidad as especialidad
                from concesionario.Vendedores;
                """;

        try {
            PreparedStatement smt = conn.prepareStatement(sql);
            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                System.out.println("Nombre Vendedor: " + rs.getString(1));
                System.out.println("Especialidad: " + rs.getString(2));

                System.out.println("------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.toString());
        }
    }

    private void ListarCochesClientesMayoresTreinta() {
        String sql = """
                select (c.detalles_coche).modelo as modelo, (c.detalles_coche).año_fabricacion as fecha,
                (cl.datos_personales).nombre as nombre, (cl.datos_personales).edad as edad
                from concesionario.Ventas v
                inner join concesionario.Coches c on c.coche_id = v.coche_id
                inner join concesionario.Clientes cl on cl.cliente_id = v.cliente_id
                where (cl.datos_personales).edad > 30;
                """;

        try {
            PreparedStatement smt = conn.prepareStatement(sql);
            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                System.out.println("Modelo: " + rs.getString(1));
                System.out.println("Año Fracación: " + rs.getInt(2));
                System.out.println("Nombre Cliente: " + rs.getString(3));
                System.out.println("Edad Cliente: " + rs.getInt(4));

                System.out.println("-----------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.toString());
        }
    }

    private void obtenerEdadPromedio() {
        String sql = "select avg((datos_personales).edad) edad_media from concesionario.Clientes;";

        try {
            PreparedStatement smt = conn.prepareStatement(sql);
            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                System.out.println("Edad Media: " + rs.getDouble(1));

                System.out.println("-----------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al consulta: " + sql);
        }
    }

    private void eliminarInformacionCocheVentasClientes(int cocheId) {

        String sqlEliminarVentas = "DELETE FROM concesionario.Ventas WHERE coche_id = ?";

        String sqlEliminarClientes = "DELETE FROM concesionario.Clientes WHERE coche_preferido_id = ?";

        String sqlEliminarCoche = "DELETE FROM concesionario.Coches WHERE coche_id = ?";

        try {
            conn.setAutoCommit(false);

            // 1. Eliminar ventas del coche
            PreparedStatement stmtVentas = conn.prepareStatement(sqlEliminarVentas);
            stmtVentas.setInt(1, cocheId);
            stmtVentas.executeUpdate();

            // 2. Eliminar clientes asociados al coche
            PreparedStatement stmtClientes = conn.prepareStatement(sqlEliminarClientes);
            stmtClientes.setInt(1, cocheId);
            stmtClientes.executeUpdate();

            // 3. Eliminar coche
            PreparedStatement stmtCoche = conn.prepareStatement(sqlEliminarCoche);
            stmtCoche.setInt(1, cocheId);
            int rowsDeleted = stmtCoche.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Se eliminó el coche y sus datos asociados: " + cocheId);
                conn.commit();
            } else {
                System.out.println("No existe el coche con id: " + cocheId);
                conn.rollback();
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Error en rollback: " + ex.getMessage());
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


    private void actualizarInformacionCoche(String modelo, int idCoche) {
        String sql = "update concesionario.Coches set detalles_coche.modelo = ? where coche_id = ?";

        try {
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString(1, modelo);
            smt.setInt(2, idCoche);

            int rowUpdate = smt.executeUpdate();

            if (rowUpdate > 0) {
                System.out.println("Se a actualizado la infromación del coche: ");
                System.out.println("-------------------------------------------------");

                System.out.println("Modelo del coche: " + modelo);
                System.out.println("ID del coche: " + idCoche);

                System.out.println("---------------------------------------");
            }else {
                System.out.println("No se podido insertar la infromación del coche");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.toString());
        }
    }

    private void crearNuevaVenta(int clienteID, int cocheID, String fecha, double precio) {
        String sql = "insert into concesionario.Ventas (cliente_id, coche_id, fecha_venta, precio_venta) values (?, ?, ?, ?)";

        try {
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setInt(1, clienteID);
            smt.setInt(2, cocheID);
            smt.setDate(3, Date.valueOf(fecha));
            smt.setDouble(4, precio);

            int rowInsert = smt.executeUpdate();

            if (rowInsert > 0) {
                System.out.println("Se a insertado la venta exitosamente: " + sql);

                System.out.println("-------------------------------------------------");

                System.out.println("Cliente ID: " + clienteID);
                System.out.println("Coche ID: " + cocheID);
                System.out.println("Fecha: " + fecha);
                System.out.println("Precio: "+ precio);

                System.out.println("-------------------------------------------");
            }else {
                System.out.println("No se a insertado la conexión");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar: ");
        }

    }

    private void crearTipoComponenete() {
        String sql = """
                create type desguace.componente as (
                nombre varchar(100),
                precio double precision,
                tamaño int
                )
                """;

        try {
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.executeUpdate();

            System.out.println("Se insertado el tipo de componenete: " + sql);
        } catch (SQLException e) {
            System.out.println("Error al crear el tipo componenete: " + e.toString());
        }
    }

    private void crearEsquemaDesguace() {
        String sql = "create schema desguace";

        try {
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.executeUpdate();

            System.out.println("Se a añadido el esquema exitosamente: " + sql);
        } catch (SQLException e) {
            System.out.println("Error al crear el esqema " + e.toString());
        }
    }
}
