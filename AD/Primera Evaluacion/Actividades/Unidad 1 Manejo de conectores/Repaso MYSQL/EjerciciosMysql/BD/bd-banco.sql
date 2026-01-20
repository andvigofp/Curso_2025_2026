DROP DATABASE IF EXISTS banco;
CREATE DATABASE banco;

USE banco;

CREATE TABLE cliente (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    dni CHAR(9) NOT NULL UNIQUE,
    nombre VARCHAR(255) NOT NULL,
    edad INT UNSIGNED NOT NULL
);

CREATE TABLE cuenta_bancaria (
	numero INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT UNSIGNED,
    dinero FLOAT NOT NULL DEFAULT 0,
    CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO cliente(dni, nombre, edad) VALUES ('11111111A', 'Jorge Perez', 32);
INSERT INTO cliente(dni, nombre, edad) VALUES ('22222222B', 'Carolina Vazquez', 80);

INSERT INTO cuenta_bancaria(id_cliente, dinero) VALUES (1, 500);
INSERT INTO cuenta_bancaria(id_cliente, dinero) VALUES (1, 100);
INSERT INTO cuenta_bancaria(id_cliente) VALUES (2);  