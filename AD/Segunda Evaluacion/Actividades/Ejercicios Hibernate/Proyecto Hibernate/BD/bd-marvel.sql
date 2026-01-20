DROP DATABASE IF EXISTS MarvelDB;
CREATE DATABASE MarvelDB;
USE MarvelDB;

-- Tabla Equipos (1:1 con Personaje)
CREATE TABLE Traje (
    id INT PRIMARY KEY,
    especificacion VARCHAR(100) NOT NULL
);

-- Tabla Personajes
CREATE TABLE Personaje (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    alias VARCHAR(100),
    id_traje INT UNIQUE,
    FOREIGN KEY (id_traje) REFERENCES Traje(id)
);

-- Tabla Habilidades (N:M sin atributos)
CREATE TABLE Habilidad (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);

-- Tabla intermedia Personaje_Habilidad (N:M sin atributos)
CREATE TABLE Personaje_Habilidad (
    id_personaje INT,
    id_habilidad INT,
    PRIMARY KEY (id_personaje, id_habilidad),
    FOREIGN KEY (id_personaje) REFERENCES Personaje(id),
    FOREIGN KEY (id_habilidad) REFERENCES Habilidad(id)
);

-- Tabla Eventos
CREATE TABLE Evento (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    lugar VARCHAR(100)
);

-- Tabla intermedia Participa (N:M con atributos)
CREATE TABLE Participa (
    id_personaje INT,
    id_evento INT,
    fecha DATE,
    rol VARCHAR(50),
    PRIMARY KEY (id_personaje, id_evento),
    FOREIGN KEY (id_personaje) REFERENCES Personaje(id),
    FOREIGN KEY (id_evento) REFERENCES Evento(id)
);

-- Datos de ejemplo
-- Datos de trajes (1:1 con personaje)
INSERT INTO Traje (id, especificacion) VALUES
(1, 'Armadura Mark LXXXV con nanobots'),
(2, 'Traje de vibranium con escudo magnético'),
(3, 'Traje de batalla psíquico'),
(4, 'Traje de combate reforzado con adamantium'),
(5, 'Uniforme espacial con propulsores');

-- Datos de personajes (relación 1:1 con traje por id_traje)
INSERT INTO Personaje (id, nombre, alias, id_traje) VALUES
(1, 'Tony Stark', 'Iron Man', 1),
(2, 'Steve Rogers', 'Capitán América', 2),
(3, 'Jean Grey', 'Phoenix', 3),
(4, 'Logan', 'Wolverine', 4),
(5, 'Peter Quill', 'Star-Lord', 5);

-- Habilidades
INSERT INTO Habilidad (id, nombre, descripcion) VALUES
(1, 'Vuelo', 'Capacidad de desplazarse por el aire'),
(2, 'Superfuerza', 'Fuerza física muy superior a la humana'),
(3, 'Regeneración', 'Curación acelerada de heridas'),
(4, 'Telepatía', 'Habilidad para leer y controlar mentes'),
(5, 'Combate cuerpo a cuerpo', 'Entrenamiento avanzado en lucha');

-- Relación Personaje-Habilidad (N:M sin atributos)
INSERT INTO Personaje_Habilidad VALUES
(1, 1), -- Iron Man - Vuelo
(1, 2), -- Iron Man - Superfuerza
(2, 2), -- Cap - Superfuerza
(2, 5), -- Cap - Combate
(3, 4), -- Jean Grey - Telepatía
(4, 3), -- Wolverine - Regeneración
(4, 5), -- Wolverine - Combate
(5, 1), -- Star-Lord - Vuelo
(5, 5); -- Star-Lord - Combate

-- Eventos
INSERT INTO Evento (id, nombre, lugar) VALUES
(1, 'Batalla de Nueva York', 'Nueva York'),
(2, 'Guerra Civil', 'Leipzig-Halle'),
(3, 'Infinity War', 'Titán');

-- Participación en eventos con rol y fecha (N:M con atributos)
INSERT INTO Participa (id_personaje, id_evento, fecha, rol) VALUES
(1, 1, '2012-05-04', 'Líder tecnológico'),
(2, 1, '2012-05-04', 'Combatiente principal'),
(2, 2, '2016-05-06', 'Líder del bando legalista'),
(3, 3, '2018-04-27', 'Apoyo mental'),
(4, 3, '2018-04-27', 'Tanque ofensivo'),
(5, 3, '2018-04-27', 'Explorador espacial');
