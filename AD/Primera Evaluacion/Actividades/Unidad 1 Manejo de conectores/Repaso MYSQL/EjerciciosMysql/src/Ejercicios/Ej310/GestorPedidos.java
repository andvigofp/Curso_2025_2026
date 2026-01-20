package Ejercicios.Ej310;

import Ejercicios.Ej311.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class GestorPedidos {
    private Connection conn;
    private String usuario = "root";
    private String clave = "abc123.";
    private String url = "jdbc:mysql://localhost:3306/tienda";

    private void abrirConexion() throws SQLException {
        conn = DriverManager.getConnection(url, usuario, clave);
    }

    private void cerrarConexion() throws SQLException {
        conn.close();
    }

    // Método para obtener los productos
    public Producto obtenerProducto(int id) {
        try {
            String sql = "SELECT * FROM producto WHERE id=?";
            abrirConexion();
            PreparedStatement consulta = conn.prepareStatement(sql);
            consulta.setInt(1, id);
            ResultSet resultados = consulta.executeQuery();

            // Seleccionamos la información del producto
            resultados.next();
            String nombre = resultados.getString("nombre");
            String descripcion = resultados.getString("descripcion");
            double precio = resultados.getDouble("precio");

            cerrarConexion();

            // Devolvemos los datos del cliente
            return new Producto(id, nombre, descripcion, precio);

        } catch (SQLException e) {
            return null;
        }
    }

    // Método para obtener los clientes
    public Cliente obtenerClientes(String dni) {

        try {
            String sql = "SELECT * FROM cliente WHERE dni=?";

            abrirConexion();

            PreparedStatement consulta = conn.prepareStatement(sql);
            consulta.setString(1, dni);
            ResultSet resultados = consulta.executeQuery();

            // Seleccionamos la información del cliente
            resultados.next();
            String nombre = resultados.getString("nombre");
            cerrarConexion();

            // Devolvemos los datos del cliente
            return new Cliente(dni, nombre);

        } catch (SQLException e) {
            return null;
        }
    }

    // Método para obetner la lsita de pedidos
    public ArrayList<Pedido> obtenrPedidos(String dniCliente) {
        try {

            // Definimos las consultas que vamos a utilizar
            String sqlPedido = "SELECT * FROM pedido WHERE dniCliente=?";
            String sqlLineaPedido = "SELECT * FROM producto_pedido WHERE idPedido=?";
            String sqlProducto = "SELECT * FROM producto WHERE id=?";

            // Cargamos las consultas
            abrirConexion();
            PreparedStatement consultaPedido = conn.prepareStatement(sqlPedido);
            PreparedStatement consultaLineaPedido = conn.prepareStatement(sqlLineaPedido);
            PreparedStatement consultaProducto = conn.prepareStatement(sqlProducto);

            // Obtenemos los datos del cliente
            Cliente cliente = obtenerClientes(dniCliente);

            // Ejecutamos la consulta que devuelve los pedidos del cliente
            consultaPedido.setString(1, dniCliente);
            ResultSet pedidosSQL = consultaPedido.executeQuery();

            // Creamos una lista que almacene los pedidos
            ArrayList<Pedido> pedidosCliente = new ArrayList<Pedido>();
            while (pedidosSQL.next()) {
                int idPedido = pedidosSQL.getInt("id");

                // Consultamos las lineas del pedido
                consultaLineaPedido.setInt(1, idPedido);
                ResultSet lineasPedidoSQL = consultaLineaPedido.executeQuery();

                // Creamos una lista que almacene las lineas del pedido
                ArrayList<LineaPedido> lineasPedido = new ArrayList<LineaPedido>();
                while (lineasPedidoSQL.next()) {

                    // Obtenemos la información del producto
                    int idProducto = lineasPedidoSQL.getInt("idProducto");
                    consultaProducto.setInt(1, idProducto);
                    ResultSet productoSQL = consultaProducto.executeQuery();
                    productoSQL.next();
                    int id = productoSQL.getInt("id");
                    String nombre = productoSQL.getString("nombre");
                    String descripcion = productoSQL.getString("descripcion");
                    double precio = productoSQL.getDouble("precio");
                    Producto producto = new Producto(id, nombre, descripcion, precio);

                    // Añadimos la información del producto a la linea de pedido
                    LineaPedido lineaPedido = new LineaPedido(idPedido, producto, lineasPedidoSQL.getInt("cantidad"));

                    // Añadimos la linea de pedido al pedido
                    lineasPedido.add(lineaPedido);
                }

                // Añadimos la información del pedido a la lista
                Pedido pedido = new Pedido(idPedido, cliente, pedidosSQL.getDate("fecha"), lineasPedido);
                pedidosCliente.add(pedido);
            }
            cerrarConexion();
            return pedidosCliente;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean addPedido(Pedido pedido) {
        try {
            // Desactivar autocommit para manejar la transacción manualmente
            abrirConexion();
            conn.setAutoCommit(false);

            // Definir sentencias SQL para insertar el pedido y sus líneas
            String sqlInsertarPedido = "INSERT INTO pedido(dniCliente,fecha) VALUES (?,?)";
            String sqlInsertarLineaPedido = "INSERT INTO producto_pedido(idPedido, idProducto, cantidad) VALUES (?,?,?)";

            // Insertar el pedido principal
            PreparedStatement sentenciaInsertarPedido = conn.prepareStatement(sqlInsertarPedido, PreparedStatement.RETURN_GENERATED_KEYS);
            sentenciaInsertarPedido.setString(1, pedido.getCliente().getDni());
            sentenciaInsertarPedido.setTimestamp(2, new Timestamp(pedido.getFecha().getTime()));
            int filasAfectadas = sentenciaInsertarPedido.executeUpdate();

            if (filasAfectadas == 0) {
                conn.rollback();
                throw new SQLException("No se pudo insertar el pedido principal.");
            }

            // Obtener el ID generado del pedido recién insertado
            ResultSet generatedKeys = sentenciaInsertarPedido.getGeneratedKeys();
            int idPedido;
            if (generatedKeys.next()) {
                idPedido = generatedKeys.getInt(1);
            } else {
                conn.rollback();
                throw new SQLException("No se pudo obtener el ID del pedido.");
            }

            // Insertar cada línea de pedido
            PreparedStatement sentenciaInsertarLineaPedido = conn.prepareStatement(sqlInsertarLineaPedido);
            for (LineaPedido lineaPedido : pedido.getLineaPedidos()) {
                sentenciaInsertarLineaPedido.setInt(1, idPedido);  // Usar el ID del pedido recién generado
                sentenciaInsertarLineaPedido.setInt(2, lineaPedido.getProducto().getId());
                sentenciaInsertarLineaPedido.setInt(3, lineaPedido.getCantidad());
                sentenciaInsertarLineaPedido.executeUpdate();
            }

            // Confirmar la transacción
            conn.commit();
            return true;
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // Restaurar autocommit
                    cerrarConexion();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

