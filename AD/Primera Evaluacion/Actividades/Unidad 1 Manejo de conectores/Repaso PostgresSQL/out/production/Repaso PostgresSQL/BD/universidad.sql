-- Crear un esquema con soporte para objetos
CREATE SCHEMA objetos;

-- Definir tipos de datos personalizados
CREATE TYPE objetos.Persona AS (
    nombre VARCHAR(255),
    edad INT
);

CREATE TYPE objetos.Estudiante AS (
    matricula VARCHAR(20),
    carrera VARCHAR(100)
);

CREATE TYPE objetos.Profesor AS (
    cedula VARCHAR(20),
    departamento VARCHAR(100)
);

-- Crear una tabla de Estudiantes
CREATE TABLE objetos.Estudiantes (
    estudiante_id serial PRIMARY KEY,
    datos_personales objetos.Persona,
    estudiante_info objetos.Estudiante
);

-- Crear una tabla de Cursos (Varios a Varios)
CREATE TABLE objetos.Cursos (
    curso_id serial PRIMARY KEY,
    nombre VARCHAR(255)
);

-- Crear una tabla de Profesores
CREATE TABLE objetos.Profesores (
    profesor_id serial PRIMARY KEY,
    datos_personales objetos.Persona,
    profesor_info objetos.Profesor,
    curso_id INT REFERENCES objetos.Cursos(curso_id)
);

-- Crear una tabla de Inscripciones (Varios a Varios)
CREATE TABLE objetos.Inscripciones (
    inscripcion_id serial PRIMARY KEY,
    estudiante_id INT REFERENCES objetos.Estudiantes(estudiante_id),
    curso_id INT REFERENCES objetos.Cursos(curso_id)
);

-- Insertar datos de ejemplo
INSERT INTO objetos.Estudiantes (datos_personales, estudiante_info)
VALUES (ROW('Juan Pérez', 20), ROW('2021001', 'Ingeniería Informática'));

INSERT INTO objetos.Estudiantes (datos_personales, estudiante_info)
VALUES (ROW('María Gómez', 22), ROW('2021002', 'Psicología'));

INSERT INTO objetos.Profesores (datos_personales, profesor_info)
VALUES (ROW('Carlos Rodríguez', 40), ROW('PR001', 'Matemáticas'));

INSERT INTO objetos.Profesores (datos_personales, profesor_info)
VALUES (ROW('Ana Martínez', 35), ROW('PR002', 'Historia'));

INSERT INTO objetos.Cursos (nombre)
VALUES ('Álgebra Lineal');

INSERT INTO objetos.Cursos (nombre)
VALUES ('Historia del Arte');

INSERT INTO objetos.Inscripciones (estudiante_id, curso_id)
VALUES (1, 1);

INSERT INTO objetos.Inscripciones (estudiante_id, curso_id)
VALUES (2, 2);

INSERT INTO objetos.Estudiantes (datos_personales, estudiante_info)
VALUES (ROW('Laura Torres', 21), ROW('2021003', 'Biología'));

INSERT INTO objetos.Estudiantes (datos_personales, estudiante_info)
VALUES (ROW('Eduardo López', 23), ROW('2021004', 'Ingeniería Civil'));

-- Insertar más cursos
INSERT INTO objetos.Cursos (nombre)
VALUES ('Programación en C');

INSERT INTO objetos.Cursos (nombre)
VALUES ('Historia Antigua');

-- Insertar más profesores
INSERT INTO objetos.Profesores (datos_personales, profesor_info, curso_id)
VALUES (ROW('Isabel Fernández', 37), ROW('PR003', 'Informática'), 3);

INSERT INTO objetos.Profesores (datos_personales, profesor_info, curso_id)
VALUES (ROW('Luis Sánchez', 45), ROW('PR004', 'Historia del Arte'), 4);

-- Inscríbir más estudiantes en cursos
INSERT INTO objetos.Inscripciones (estudiante_id, curso_id)
VALUES (3, 3);

INSERT INTO objetos.Inscripciones (estudiante_id, curso_id)
VALUES (4, 4);