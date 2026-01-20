# 1. Mostrar los datos de todas las tiendas.
select * from tendas;

# 2. Mostrar los nombres de todos los proveedores.
select prv_nome as nombre from provedores;

# 3. Obtener la lista de las poblaciones en las que existan clientes.
select distinct clt_poboacion from clientes;

# 4. Mostrar el precio de venta de todos los artículos y el precio que resultaría después de aplicarles un incremento del 10%.
select art_nome as nombre, art_pv as precio_venta, art_pv * 1.1 as precio_final
from artigos;

# 5. Mostrar el número de cliente, apellidos y nombre de todos los clientes de Madrid.
select clt_id, clt_apelidos, clt_nome
from clientes
where clt_poboacion = "MADRID";

# 6. Seleccionar el código, descripción y peso de los artículos que pesen más de 500 gramos.
select art_codigo, art_nome, art_peso
from artigos
where art_peso > 500;

# 7. Seleccionar todos los artículos que tengan un precio de venta superior o igual al doble del precio de compra.

INSERT INTO artigos (art_codigo, art_nome, art_pc, art_pv, art_alta) 
VALUES ('9999999', 'Ejemplo Producto', 100, 250, CURDATE());

SELECT *
FROM artigos
WHERE art_pv >= 2 * art_pc;

SELECT art_codigo, art_nome, art_pc, art_pv,
       (2 * art_pc) AS doble_precio_compra
FROM artigos
WHERE art_pv >= 2 * art_pc;

# 8. Seleccionar apellidos, nombre, población y descuento de todos los clientes de Asturias o Valencia que tengan un descuento superior al 2% o que no tengan descuento.
select clt_apelidos as apellidos, clt_nome as nombre, clt_poboacion as poblacion, clt_desconto as descuento
from clientes
where clt_poboacion in ('Asturias', 'Valencia') and (clt_desconto = 0 or clt_desconto > 2);

# 9. Seleccionar todos los artículos de color negro que pesen más de 5000 gramos.
select art_codigo as codigo, art_nome as nombre, art_peso as peso, art_color as color 
from artigos
where art_color= 'NEGRO' and art_peso > 5000;

# 10. Obtener todos los artículos que no son de color negro o que tienen un peso menor o igual de 5000 gramos.
select art_codigo as codigo, art_nome as nombre, art_peso as peso, art_color as color
from artigos
where (art_color!='NEGRO' or art_peso<=5000);

# 11. Seleccionar los artículos que son de color negro y pesan más de 100 gramos, o bien son de color cyan.
select art_codigo as codigo, art_nome as nombre, art_peso as peso, art_color as color
from artigos
where (art_color = 'negro' and art_peso > 100) or art_color = 'cyan';

# 12. Hacer una lista de los artículos que tienen un precio de compra de entre 12 y 18 euros, ambos precios incluidos.
select art_codigo as codigo, art_nome as nombre, art_pc as precio_compra
from artigos
where art_pc between 12 and 18;

# 13. Mostrar una lista de artículos de color negro o color cyan
select art_codigo as codigo, art_nome as nombre, art_color as color
from artigos
where art_color = 'negro' or art_color = 'cyan';

# 14. Buscar un cliente del que se desconoce el apellido exacto, pero se sabe que las dos primeras letras son RO.
select * from clientes
where clt_apelidos like 'RO%';

# 15. Buscar clientes que tengan el nombre de 5 letras, empezando por B y terminando por A.
select clt_nome, clt_apelidos
from clientes
where clt_nome like 'B___A';

# 16. Buscar todos los artículos para los que no se guardó su color.
select * from artigos
where art_color is null;

# 17. Clasificar los artículos teniendo en cuenta su peso, por orden descendente.
select art_codigo as codigo, art_nome as nombre, art_peso as peso
from artigos
order by peso desc;

# 18. Mostrar el código de artículo, nombre, precio de compra, precio de venta y margen de beneficio (precio de venta – precio de compra) 
# de los artículos que tienen un precio de compra superior a 3000 euros, ordenados por el margen.
select art_codigo as codigo, art_nome as nombre, art_pc as precio_compra, art_pv as precio_venta, art_pv-art_pc as margen
from artigos
where art_pc > 3000
order by margen;

# 19. Clasificar nombre, proveedor, stock y peso de los artículos que tienen un peso menor o igual a 1000 gramos, por orden ascendente del proveedor. 
# Cuando los proveedores coincidan, deben clasificarse por el stock en orden descendente.
select art_nome, art_provedor, art_stock, art_peso
from artigos
where art_peso <=1000
order by art_provedor, art_peso desc;

# 20. Seleccionar nombre y apellidos de los clientes que tengan un apellido que empiece por F y termine por Z.
select clt_nome, clt_apelidos
from clientes
where clt_apelidos like 'F%Z %' or clt_apelidos like '% F%Z';

# 21. Seleccionar los clientes cuyo primer apellido empiece por la letra a o por la letra f.
select clt_nome, clt_apelidos
from clientes
where clt_apelidos like 'A%' or clt_apelidos like 'F%';

# 22. Seleccionar los clientes cuyo nombre no empiece por a.
select * from clientes
where clt_nome not like 'A%';

# 23. Seleccionar los clientes que tengan un nombre que tenga exactamente 5 caracteres.
select * from clientes
where clt_nome like '_____';