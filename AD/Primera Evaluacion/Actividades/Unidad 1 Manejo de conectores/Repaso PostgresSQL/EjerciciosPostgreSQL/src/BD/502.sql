-- Crear una base de datos llamada "formula1"
CREATE DATABASE formula1;

-- Crear una tabla para los equipos
CREATE TABLE equipos (
    equipo_id serial PRIMARY KEY,
    nombre VARCHAR(255),
    sede VARCHAR(255),
    director VARCHAR(255)
);

-- Crear una tabla para los pilotos
CREATE TABLE pilotos (
    piloto_id serial PRIMARY KEY,
    nombre VARCHAR(255),
    nacionalidad VARCHAR(50),
    equipo_id INT REFERENCES equipos(equipo_id),
    fecha_nacimiento DATE
);

-- Crear una tabla para las carreras
CREATE TABLE carreras (
    carrera_id serial PRIMARY KEY,
    nombre VARCHAR(255),
    fecha DATE,
    pais VARCHAR(100)
);

-- Crear una tabla para los resultados de las carreras
CREATE TABLE resultados (
    resultado_id serial PRIMARY KEY,
    carrera_id INT REFERENCES carreras(carrera_id),
    piloto_id INT REFERENCES pilotos(piloto_id),
    posicion INT,
    tiempo VARCHAR(50)
);

-- Insertar datos de ejemplo en la tabla equipos
INSERT INTO equipos (nombre, sede, director) VALUES
    ('Mercedes', 'Brackley, Reino Unido', 'Toto Wolff'),
    ('Red Bull Racing', 'Milton Keynes, Reino Unido', 'Christian Horner');

-- Insertar datos de ejemplo en la tabla pilotos
INSERT INTO pilotos (nombre, nacionalidad, equipo_id, fecha_nacimiento) VALUES
    ('Lewis Hamilton', 'Reino Unido', 1, '1985-01-07'),
    ('Max Verstappen', 'Países Bajos', 2, '1997-09-30');

-- Insertar datos de ejemplo en la tabla carreras
INSERT INTO carreras (nombre, fecha, pais) VALUES
    ('Gran Premio de Australia', '2023-03-20', 'Australia'),
    ('Gran Premio de España', '2023-05-14', 'España');

-- Insertar datos de ejemplo en la tabla resultados
INSERT INTO resultados (carrera_id, piloto_id, posicion, tiempo) VALUES
    (1, 1, 1, '1:23:45'),
    (1, 2, 2, '1:23:55'),
    (2, 1, 1, '1:30:00'),
    (2, 2, 2, '1:30:10');
