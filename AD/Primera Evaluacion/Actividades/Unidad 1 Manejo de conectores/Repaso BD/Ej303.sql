# 1. Seleccionar de la tabla artigos los colores y el precio medio de venta de los artículos de cada color (con dos decimales)
select art_color as color, round(avg(art_pv),2) as precio_medio
from artigos
where art_color is not null
group by art_color;

# 2. Seleccionar de la tabla artigos los colores y el precio medio de venta de los artículos de cada color, 
# excluyendo los artículos que tengan un precio de compra superior a 50 euros.
select art_color as color, avg(art_pv) as precio_medio
from artigos
where art_color is not null and art_pc <= 50
group by art_color;

# 3. Mostrar las ciudades en las que existen clientes y el número de clientes que hay en cada una de ellas. 
# Clasificar la salida en orden descendente por el número de clientes.
select clt_poboacion as ciudad, count(*) as numero_clientes
from clientes
group by clt_poboacion
order by numero_clientes desc;

# 4. Mostrar las estadísticas de ventas del año 2015 por tienda. La información a mostrar es: identificador de la tienda, 
# número de tiendas, número de artículos vendidos, suma de unidades vendidas y 
# la media de los precios unitarios de los artículos vendidos.
select ve.ven_tenda as tienda, max(ve.ven_id) as numero_ventas,
	max(dv.dev_artigo) as numero_articulos, sum(dv.dev_cantidade) as suma_umidades,
    round(avg(dv.dev_prezo_unitario),2) as media_precio
from vendas as ve inner join detalle_vendas as dv on dv.dev_venda = ven_id
where year(ve.ven_data) = 2015
group by ve.ven_tenda; 

select ve.ven_tenda as tienda, count(distinct ve.ven_id) as numero_ventas,
    count(distinct dv.dev_artigo) as numero_articulos, sum(dv.dev_cantidade) as suma_unidades,
    round(avg(dv.dev_prezo_unitario),2) as media_precio
from vendas as ve inner join detalle_vendas as dv on dv.dev_venda = ve.ven_id
where year(ve.ven_data) = 2015
group by ve.ven_tenda;

# 5. Seleccionar de la tabla artigos los colores y el precio medio de venta de los artículos de cada color, 
# para los colores que tengan un precio medio mayor que 100 euros.
select art_color as color, round(avg(art_pv),2) as precio_medio
from artigos
where art_color is not null
group by art_color
having avg(art_pv) > 100; 

select art_color as color, round(avg(art_pv),2) as precio_medio
from artigos
where art_color is not null
group by art_color
having precio_medio > 100;

# 6. Mostrar las tiendas que hicieron más de 2 ventas en el mes de mayo de 2015. 
# Para cada tienda se debe mostrar: número de tienda, número de ventas, 
# número de artículos diferentes vendidos y la suma de unidades vendidas en ese periodo de tiempo.
select ve.ven_tenda as tienda, count(*) as numero_ventas,
	max(dv.dev_artigo) as numero_articulos, sum(dv.dev_cantidade) as numero_unidades_vendidas
	from vendas as ve inner join detalle_vendas as dv on dv.dev_venda = ve.ven_id
    group by ve.ven_tenda
    having numero_ventas > 2;

select ve.ven_tenda as tienda, count(*) as numero_ventas,
    count(distinct dv.dev_artigo) as numero_articulos, sum(dv.dev_cantidade) as numero_unidades_vendidas
from vendas as ve inner join detalle_vendas as dv on dv.dev_venda = ve.ven_id
where month(date(ve.ven_data)) = 5 and year(date(ve.ven_data)) = 2015
group by ve.ven_tenda
having numero_ventas > 2;

# 7. Mostrar el identificador de cliente, fecha de venta, la cantidad de artículos vendidos, 
# la suma de los importes de las ventas en la fecha y el descuento practicado en esas ventas, 
# para los clientes a los que se vendió más de 1200 euros en un solo día.
select ve.ven_cliente as cliente, ve.ven_data as fecha, sum(dv.dev_cantidade) as cantidad,
    round(sum((dv.dev_cantidade*dv.dev_prezo_unitario)*(1-dv.dev_desconto/100)),2) as importe
from vendas as ve inner join detalle_vendas as dv on ve.ven_id = dv.dev_venda
group by ve.ven_cliente, ve.ven_data
having importe >= 1200;

# 8. Mostrar el identificador de cliente, apellidos, nombre y fecha de venta para los clientes que solo tienen una venta.
select ve.ven_cliente as cliente, cl.clt_apelidos as apellidos, cl.clt_nome as nombre, max(ve.ven_data) as fecha
from clientes as cl inner join vendas as ve on cl.clt_id=ve.ven_cliente
group by cl.clt_id
having count(*)=1;