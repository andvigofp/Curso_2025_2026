package Ejercicios.EJ8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Biblioteca {
    private Map<Integer, Material> catalogo = new HashMap<>();
    private ArrayList<Material> prestados = new ArrayList<>();

    public void agregarMaterial(Material material) {
        catalogo.put(material.getId(), material);
    }

    public Material buscarMaterial(int id) {
        return catalogo.get(id);
    }

    public void prestarMaterial(int id) {
        Material material = catalogo.get(id);

        if (material !=null) {
            material.prestar();
            if (material.estaDisponible()) {
                prestados.remove(material);
            }else {
                prestados.add(material);
            }
        }else {
            System.out.println("Material no encontrado");
        }
    }

    public void devolverMaterial(int id) {
        Material material = catalogo.get(id);
        if (material !=null) {
            material.devolver();
            if (material.estaDisponible()) {
                prestados.add(material);
            } else {
                prestados.remove(material);
            }
        }else {
            System.out.println("Material no encontrado");
        }
    }

    public void listarPrestados() {
        if (prestados.isEmpty()) {
            System.out.println("No hay materiales prestados en este momento");
        }else {
            System.out.println("Materiales prestados:");

            for (Material material: prestados) {
                System.out.println(material.getTitulo());
            }
        }
    }
}
