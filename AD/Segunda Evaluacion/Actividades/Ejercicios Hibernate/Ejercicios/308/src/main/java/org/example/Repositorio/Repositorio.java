package org.example.Repositorio;

import java.util.List;

public interface Repositorio <T>{
    void guardar(T t);
    void eliminar(T t);
    T buscarPorId(int id);
    List<T> buscarPorCadena(String cadena);
    void actualizar(T t);
}
