package Ejercicios.Ej310;

public class LineaPedido {
    private int idPedido;
    private Producto producto;
    private int cantidad;

    public LineaPedido(int idPedido, Producto producto, int cantidad) {
        this.idPedido = idPedido;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return "LineaPedido{" +
                "idPedido=" + idPedido +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                '}';
    }
}
