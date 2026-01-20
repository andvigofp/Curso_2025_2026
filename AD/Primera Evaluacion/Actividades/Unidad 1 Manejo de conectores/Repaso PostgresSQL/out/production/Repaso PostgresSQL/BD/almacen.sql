-- Crear una base de datos llamada "almacen"
CREATE DATABASE almacen;

-- Crear un esquema con soporte para objetos
CREATE SCHEMA productos;

CREATE TYPE productos.Producto AS (
    codigo_producto VARCHAR(255),
    nombre VARCHAR(255),
    precio DECIMAL(10, 2),
    descripcion TEXT
);

CREATE TABLE productos.Productos (
    producto_id serial PRIMARY KEY,
    producto_info productos.Producto
);

INSERT INTO productos.Productos (producto_info)
VALUES (ROW('P01', 'Producto 1', 10.99, 'Descripción del Producto 1'));

INSERT INTO productos.Productos (producto_info)
VALUES (ROW('P02', 'Producto 2', 15.50, 'Descripción del Producto 2'));

CREATE OR REPLACE FUNCTION productos.InsertarProducto(
    codigo_producto VARCHAR,
    nombre VARCHAR,
    precio DECIMAL,
    descripcion TEXT
) RETURNS VOID AS $$
BEGIN
    BEGIN
        INSERT INTO productos.Productos (producto_info)
        VALUES (ROW(codigo_producto, nombre, precio, descripcion));
    EXCEPTION
        WHEN others THEN
            RAISE EXCEPTION 'Error al insertar el producto';
    END;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION productos.ObtenerProductoPorCodigo(codigo VARCHAR) RETURNS TABLE (
    codigo_producto VARCHAR,
    nombre VARCHAR,
    precio DECIMAL,
    descripcion TEXT
) AS $$
DECLARE
    producto productos.Producto;
BEGIN
    RETURN QUERY
    SELECT (producto_info).codigo_producto, (producto_info).nombre, (producto_info).precio, (producto_info).descripcion
    FROM productos.Productos
    WHERE (producto_info).codigo_producto = codigo;
END;
$$ LANGUAGE plpgsql;