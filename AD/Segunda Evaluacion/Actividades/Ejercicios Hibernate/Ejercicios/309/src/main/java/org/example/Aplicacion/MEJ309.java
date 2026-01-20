package org.example.Aplicacion;

import org.example.Entidades.*;
import org.example.Repositorios.*;
import org.hibernate.Session;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MEJ309 {

    private Session session;
    private EmpresaRepository empresaRepo;
    private EmpleadoRepository empleadoRepo;
    private FijoRepository fijoRepo;
    private TemporalRepository temporalRepo;
    private ProductoRepository productoRepo;
    private VentaRepository ventaRepo;
    private static Scanner teclado = new Scanner(System.in);

    public MEJ309(Session session, EmpresaRepository empresaRepository, EmpleadoRepository empleadoRepository, FijoRepository fijoRepository, TemporalRepository temporalRepository, ProductoRepository productoRepository, VentaRepository ventaRepository, Scanner teclado) {
        this.session = session;
        this.empresaRepo = empresaRepository;
        this.empleadoRepo = empleadoRepository;
        this.fijoRepo = fijoRepository;
        this.temporalRepo = temporalRepository;
        this.productoRepo = productoRepository;
        this.ventaRepo = ventaRepository;
        this.teclado = teclado;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Altas");
            System.out.println("2. Bajas");
            System.out.println("3. Modificaciones");
            System.out.println("4. Consultas");
            System.out.println("0. Salir");
            opcion = pedirInt("Elige opción: ");

            switch (opcion) {
                case 1 -> menuAltas();
                case 2 -> menuBajas();
                case 3 -> menuModificaciones();
                case 4 -> menuConsultas();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    // --- ALTAS ---
    private void menuAltas() {
        System.out.println("\n--- ALTAS ---");
        System.out.println("1. Empresas");
        System.out.println("2. Empleados");
        System.out.println("3. Productos");
        System.out.println("4. Ventas");
        int opcion = pedirInt("Elige opción: ");

        switch (opcion) {
            case 1 -> altaEmpresa();
            case 2 -> altaEmpleado();
            case 3 -> altaProducto();
            case 4 -> altaVenta();
            default -> System.out.println("Opción inválida");
        }
    }

    private void altaEmpresa() {
        String cif = pedirString("CIF: ");
        String nombre = pedirString("Nombre: ");
        String direccion = pedirString("Dirección: ");
        String telefono = pedirString("Teléfono: ");

        Empresa empresa = new Empresa(cif, nombre, direccion, telefono);
        empresaRepo.guardar(empresa);
        System.out.println("Empresa guardada.");
    }

    private void altaEmpleado() {
        int tipo = pedirInt("Tipo de empleado: 1. Fijo  2. Temporal");
        String dni = pedirString("DNI: ");
        String nombre = pedirString("Nombre: ");
        String telefono = pedirString("Teléfono: ");
        float retencion = pedirFloat("Porcentaje retención (0-1): ");
        String cif = pedirString("CIF empresa: ");

        Empresa empresa = empresaRepo.findById(cif);
        if (empresa == null) {
            System.out.println("Empresa no encontrada");
            return;
        }

        if (tipo == 1) { // Fijo
            int salario = pedirInt("Salario base: ");
            int trienios = pedirInt("Trienios: ");
            Fijo fijo = new Fijo(dni, nombre, telefono, retencion, salario, trienios);
            fijo.setEmpresa(empresa);
            fijoRepo.guardar(fijo);
        } else { // Temporal
            Date inicio = Date.valueOf(pedirString("Fecha inicio (YYYY-MM-DD): "));
            Date fin = Date.valueOf(pedirString("Fecha fin (YYYY-MM-DD): "));
            float pago = pedirFloat("Pago día: ");
            Temporal temp = new Temporal(dni, nombre, telefono, retencion, inicio, fin, pago);
            temp.setEmpresa(empresa);
            temporalRepo.guardar(temp);
        }
        System.out.println("Empleado guardado.");
    }

    private void altaProducto() {
        String codigo = pedirString("Código: ");
        int stock = pedirInt("Stock almacen: ");
        float precio = pedirFloat("Precio unitario: ");
        String cif = pedirString("CIF empresa: ");

        Empresa empresa = empresaRepo.findById(cif);
        if (empresa == null) {
            System.out.println("Empresa no encontrada");
            return;
        }

        Producto p = new Producto(codigo, stock, precio);
        p.setEmpresa(empresa);
        productoRepo.guardar(p);
        System.out.println("Producto guardado.");
    }

    private void altaVenta() {
        String dni = pedirString("DNI empleado: ");
        Empleado e = empleadoRepo.findById(dni);
        if (e == null) { System.out.println("Empleado no encontrado"); return; }

        String codigo = pedirString("Código producto: ");
        Producto p = productoRepo.findById(codigo);
        if (p == null) { System.out.println("Producto no encontrado"); return; }

        int unidades = pedirInt("Número unidades: ");
        Venta v = new Venta(p, e, unidades);
        ventaRepo.guardar(v);
        System.out.println("Venta registrada.");
    }

    // --- BAJAS ---
    private void menuBajas() {
        System.out.println("\n--- BAJAS ---");
        System.out.println("1. Empleados Fijos");
        System.out.println("2. Empleados Temporales");
        int opcion = pedirInt("Elige opción: ");

        String dni = pedirString("DNI: ");
        if (opcion == 1) {
            Fijo f = fijoRepo.findById(dni);
            if (f != null) fijoRepo.eliminar(f);
        } else {
            Temporal t = temporalRepo.findById(dni);
            if (t != null) temporalRepo.eliminar(t);
        }
        System.out.println("Empleado eliminado.");
    }

    // --- MODIFICACIONES ---
    private void menuModificaciones() {
        System.out.println("\n--- MODIFICACIONES ---");
        System.out.println("1. Precio de producto");
        System.out.println("2. Sueldo base empleado fijo");
        System.out.println("3. Porcentaje retención empleado");
        System.out.println("4. Importe día empleado temporal");
        int opcion = pedirInt("Elige opción: ");

        switch (opcion) {
            case 1 -> {
                String cod = pedirString("Código producto: ");
                Producto p = productoRepo.findById(cod);
                if (p == null) { System.out.println("Producto no encontrado"); return; }
                float precio = pedirFloat("Nuevo precio: ");
                p.setPrecioUnitario(precio);
                productoRepo.actualizar(p);
            }
            case 2 -> {
                String dni = pedirString("DNI empleado fijo: ");
                Fijo f = fijoRepo.findById(dni);
                if (f == null) { System.out.println("Empleado no encontrado"); return; }
                int sal = pedirInt("Nuevo salario base: ");
                f.setSalarioBase(sal);
                fijoRepo.actualizar(f);
            }
            case 3 -> {
                String dni = pedirString("DNI empleado: ");
                Empleado e = empleadoRepo.findById(dni);
                if (e == null) { System.out.println("Empleado no encontrado"); return; }
                float pr = pedirFloat("Nuevo porcentaje retención: ");
                e.setPorcentajeRetencion(pr);
                empleadoRepo.actualizar(e);
            }
            case 4 -> {
                String dni = pedirString("DNI empleado temporal: ");
                Temporal t = temporalRepo.findById(dni);
                if (t == null) { System.out.println("Empleado no encontrado"); return; }
                float imp = pedirFloat("Nuevo importe día: ");
                t.setPagoDia(imp);
                temporalRepo.actualizar(t);
            }
            default -> System.out.println("Opción inválida");
        }
    }

    // --- CONSULTAS ---
    private void menuConsultas() {
        System.out.println("\n--- CONSULTAS ---");
        System.out.println("1. Listado empresas con empleados");
        System.out.println("2. Listado productos de empresa");
        int opcion = pedirInt("Elige opción: ");

        switch (opcion) {
            case 1 -> {
                List<Empresa> empresas = empresaRepo.findAll();
                for (Empresa emp : empresas) {
                    System.out.println("Empresa: " + emp.getNombre());
                    System.out.println("  Empleados Fijos:");
                    for (Empleado e : emp.getEmpleados()) {
                        if (e instanceof Fijo) System.out.println("    " + e.getNombre() + " (Fijo)");
                    }
                    System.out.println("  Empleados Temporales:");
                    for (Empleado e : emp.getEmpleados()) {
                        if (e instanceof Temporal) System.out.println("    " + e.getNombre() + " (Temporal)");
                    }
                }
            }
            case 2 -> {
                String cif = pedirString("CIF empresa: ");
                Empresa emp = empresaRepo.findById(cif);
                if (emp == null) { System.out.println("Empresa no encontrada"); return; }
                for (Producto p : emp.getProductos()) {
                    System.out.println("Producto: " + p.getCodigo() + " Precio: " + p.getPrecioUnitario());
                }
            }
            default -> System.out.println("Opción inválida");
        }
    }

    // --- MÉTODOS PEDIR VALORES ---
    public static int pedirInt(String mensaje) {
        System.out.println(mensaje);
        int valor = teclado.nextInt();
        teclado.nextLine(); // Limpiar buffer
        return valor;
    }

    public static float pedirFloat(String mensaje) {
        System.out.println(mensaje);
        float valor = teclado.nextFloat();
        teclado.nextLine(); // Limpiar buffer
        return valor;
    }

    public static String pedirString(String mensaje) {
        System.out.println(mensaje);
        return teclado.nextLine();
    }
}
