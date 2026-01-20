package org.example.Repositorio;

import java.util.List;

public interface Repositorio <T>{
    void insertarUno(T t);
    void borrar(T t);
        List<T> enocntrarTodos();
    T encontrarUnoPorString(String nombre);
    void actualizar(T t);
}
