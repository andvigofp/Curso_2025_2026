package Ejercicios.Ej310;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private int id;
    private Cliente cliente;
    private Date fecha;
    private ArrayList<LineaPedido> lineaPedidos;

    public Pedido(int id, Cliente cliente, Date fecha, ArrayList<LineaPedido> lineaPedidos) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.lineaPedidos = lineaPedidos;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public ArrayList<LineaPedido> getLineaPedidos() {
        return lineaPedidos;
    }

    public double getPrecioTotal() {
        double precioTotal = 0.0;

        for (LineaPedido lineaPedido: lineaPedidos) {
            precioTotal += lineaPedido.getProducto().getPrecio() * lineaPedido.getCantidad();
        }

        return precioTotal;
    }

    @Override
    public String toString() {
        String infoPedido = "";

        infoPedido += String.format("Cliente:%n %s%n", cliente);
        infoPedido += String.format("Fecha:%n %s%n", fecha);
        infoPedido += "\nProductos:\n";

        for (LineaPedido lineaPedido: lineaPedidos) {
            infoPedido += lineaPedido.getProducto().toString();
            infoPedido += String.format("Cantidad: %d%n", lineaPedido.getCantidad());
        }

        infoPedido += String.format("Precio total: %.2f%n", getPrecioTotal());

        return infoPedido;
    }
}
