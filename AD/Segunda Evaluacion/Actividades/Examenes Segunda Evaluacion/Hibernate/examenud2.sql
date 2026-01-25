DROP DATABASE IF EXISTS examenud2;
CREATE DATABASE examenud2;

USE examenud2;

CREATE TABLE director (
  id INT NOT NULL AUTO_INCREMENT,
  codigoCuerpo VARCHAR(255) NULL DEFAULT NULL,
  edad INT(11) NOT NULL,
  nombre VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE instituto (
  codigo INT(11) NOT NULL AUTO_INCREMENT,
  nombre VARCHAR (255) NULL DEFAULT NULL,
  tf CHAR(9) NULL DEFAULT NULL,
  director INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (codigo),
  FOREIGN KEY (director) REFERENCES director (id)
);

CREATE TABLE ciclo (
  codigo INT(11) NOT NULL AUTO_INCREMENT,
  nombreCiclo VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (codigo)
);

CREATE TABLE ies_ciclos (
  cod_instituto INT(11) NOT NULL,
  cod_ciclo INT(11) NOT NULL,
  FOREIGN KEY (cod_ciclo) REFERENCES ciclo (codigo),
  FOREIGN KEY (cod_instituto) REFERENCES instituto (codigo)
);

CREATE TABLE IF NOT EXISTS taller (
  codigo INT(11) NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (codigo)
);

CREATE TABLE uso (
  hora TIME NOT NULL,
  fecha DATE NOT NULL,
  taller_codigo INT(11) NOT NULL,
  ciclo_codigo INT(11) NOT NULL,
  PRIMARY KEY (taller_codigo, hora, fecha, ciclo_codigo),
  FOREIGN KEY (ciclo_codigo) REFERENCES ciclo (codigo),
  FOREIGN KEY (taller_codigo) REFERENCES taller (codigo)
);

INSERT INTO director (codigoCuerpo, edad, nombre) VALUES
('ABC123', 40, 'Director1'),
('XYZ456', 35, 'Director2'),
('DEF789', 45, 'Director3'),
('GHI012', 38, 'Director4');

INSERT INTO instituto (nombre, tf, director) VALUES
('Instituto 1', '123456789', 1),
('Instituto 2', '987654321', 2),
('Instituto 3', '555666777', 3),
('Instituto 4', '999888777', 4);

INSERT INTO ciclo (nombreCiclo) VALUES
('Ciclo1'),
('Ciclo2'),
('Ciclo3'),
('Ciclo4');

INSERT INTO ies_ciclos (cod_instituto, cod_ciclo) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

INSERT INTO taller (nombre) VALUES
('Taller1'),
('Taller2'),
('Taller3'),
('Taller4');

INSERT INTO uso (hora, fecha, taller_codigo, ciclo_codigo) VALUES
('08:00:00', '2024-02-05', 1, 1),
('10:30:00', '2024-02-06', 2, 2),
('14:00:00', '2024-02-07', 1, 2),
('15:30:00', '2024-02-08', 2, 3),
('09:45:00', '2024-02-09', 3, 4),
('11:00:00', '2024-02-10', 4, 3);

