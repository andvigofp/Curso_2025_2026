package org.example.Repositorios;

public interface Repositorio<T> {
    void guardar(T t);
    void actualizar(T t);
    void eliminar(T t);
}

