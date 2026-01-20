-- Crear una base de datos llamada "pokemons"
CREATE DATABASE pokemons;

-- Crear un esquema con soporte para objetos
CREATE SCHEMA objetos;

-- Definir el tipo de datos personalizado "Pokemon"
CREATE TYPE objetos.Pokemon AS (
    nombre VARCHAR(255),
    tipo VARCHAR(50),
    nivel INT
);

-- Crear una tabla de Pokémon que utiliza el tipo de datos personalizado "Pokemon"
CREATE TABLE objetos.Pokemons (
    id serial PRIMARY KEY,
    pokemon objetos.Pokemon
);

-- Insertar datos de ejemplo
INSERT INTO objetos.Pokemons (pokemon)
VALUES (ROW('Pikachu', 'Eléctrico', 30));

INSERT INTO objetos.Pokemons (pokemon)
VALUES (ROW('Charizard', 'Fuego/Volador', 50));
