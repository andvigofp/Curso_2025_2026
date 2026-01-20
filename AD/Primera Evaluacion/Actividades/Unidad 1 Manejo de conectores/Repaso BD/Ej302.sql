# 1. Seleccionar los artículos de color negro y mostrar su número, nombre y peso, así como el nombre del proveedor.
select ar.art_codigo as codigo_artigo, ar.art_nome as nombre_articulo, ar.art_peso as peso_articulo, pr.prv_nome as nombre_provedor
from artigos as ar inner join provedores as pr on ar.art_provedor=pr.prv_id
where ar.art_color='NEGRO'; 

# 2. Mostrar para cada venta: nombre y apellidos del cliente y fecha de venta.
select cl.clt_nome as nombre, cl.clt_apelidos as apellidos, ve.ven_data as fecha_venta
from clientes as cl inner join vendas as ve on cl.clt_id=ve.ven_cliente;

# 3. Mostrar: número total de ventas, número de artículos vendidos, suma de unidades vendidas y la media de los precios de los artículos vendidos.
# select max(ve_id) as numeros
select max(ven_id) as numero_ventas,
	   count(distinct dev_artigo) as mumero_articulos,
       sum(dev_cantidade) as suma_unidades,
       avg(dev_cantidade) as media_precio
from vendas inner join detalle_vendas on dev_venda = ven_id;
        
select count(distinct ve.ven_id) as numero_ventas,
    count(distinct dv.dev_artigo) as numero_articulos,
    sum(dv.dev_cantidade) as suma_unidades,
    round(avg(dv.dev_prezo_unitario),2) as media_precio
from vendas as ve inner join detalle_vendas as dv on dv.dev_venda = ve.ven_id;

# 4. Seleccionar para cada artículo su número, nombre, peso y el nombre que corresponde al peso (peso_nome), teniendo en cuenta la información contenida en la tabla pesos, 
# que dá un nombre a los pesos en función del intervalo al que pertenece, Ordenar el resultado por el peso del artículo, de mayor a menor.
select ar.art_codigo, ar.art_nome, ar.art_peso, pe.peso_nome
from artigos as ar inner join pesos as pe on ar.art_peso between peso_min and peso_max
order by ar.art_peso;

# 5. Seleccionar para cada venta:
# Datos de la venta: identificar y fecha de la venta.
# Datos del cliente: nombre del cliente (nombre y apellidos separados por coma).
# Datos del empleado: nombre del empleado (nombre y apellidos separados por coma). Mostrar los datos ordenados por el apellido y nombre del cliente.
select ven_id, ven_data as ventas,
concat(clt_apelidos, ' , ', clt_nome) as clientes,
concat(emp_apelidos, ' , ', emp_nome) as empregados
from vendas
inner join clientes on ven_cliente = clt_id
inner join empregados on ven_empregado = emp_id
order by clt_apelidos and clt_nome;



select ve.ven_id as numero_venta, ve.ven_data as fecha_venta,
concat(cl.clt_apelidos, ' , ',cl.clt_nome) as apellidos_nombre_cliente,
concat(em.emp_apelidos,' , ',em.emp_nome) as apellidos_nombre_empleado
from vendas as ve
inner join clientes as cl on ve.ven_cliente = cl.clt_id
inner join empregados as em on ve.ven_empregado = em.emp_id
order by apellidos_nombre_cliente;

# 6. Para todos los clientes con identificador menor o igual a 10, seleccionar los datos de las ventas que se hicieron. 
# Hay que mostrar, para cada venta, el identificador del cliente, apellidos, nombre y fecha de venta. 
# Si a alguno de esos clientes no se le hizo ninguna venta, deberá aparecer en el resultado con su identificador, 
# apellidos, nombre y el texto SIN COMPRAS en la columna de fecha de venta.
select distinct cl.clt_id as codigo_cliente, cl.clt_apelidos as apellidos_cliente,
	cl.clt_nome as nombre_cliente, ifnull(ve.ven_data, 'SIN COMPRAS') as fecha_compra
from clientes as cl left join vendas as ve on cl.clt_id = ve.ven_cliente
where cl.clt_id<=10;

# 7.  Seleccionar el código (emp_id), apellidos y nombre de todos los empleados. Añadir una columna en el resultado, 
# con el alias ventas, en la que se muestre el literal 
# Si si el empleado hizo alguna venta, o el literal No en el caso de que aún no hiciese ninguna venta.
select distinct emp_id as codigo, emp_dni as dni, emp_apelidos as apellidos, emp_nome as nombre,
	if(ven_id is null, 'NO', 'SI') as ventas
from empregados left join vendas on emp_id = ven_empregado;

# 8. Obtener una lista de todos los artículos que tengan un precio de compra superior al precio de compra del artículo con código 0713242.
select ar1.art_codigo as numero, ar1.art_nome as nombre, ar1.art_pc as precio,
    ar2.art_pc as precio_articulo_0713242
from artigos as ar1 inner join artigos as ar2 on ar1.art_pc > ar2.art_pc
where ar2.art_codigo='0713242';

# 9. Seleccionar todos los artículos negros, junto con los artículos que pesan más de 5000 gramos, 
# escribiendo dos consultas y utilizando el operador de unión de consultas.
select art_codigo as codigo, art_nome as nombre, art_peso as peso, art_color as color
from artigos where art_peso > 5000
union
select art_codigo as codigo, art_nome as nombre, art_peso as peso, art_color as color
from artigos where art_color="negro"
order by codigo;    