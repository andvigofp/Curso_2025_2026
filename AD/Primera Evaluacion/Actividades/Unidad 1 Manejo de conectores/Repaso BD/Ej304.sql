# 1. Seleccionar código y peso de los artículos que tienen menos peso que el artículo de código 077WN45.
select  art_codigo as codigo, art_peso as peso
from artigos
where art_peso < (select art_peso from artigos where art_codigo='077WN45')
order by art_peso;

#2. Seleccionar código, nombre, peso y color de los artículos del mismo color que el artículo de código 242C283, 
# y que tengan un peso mayor o igual que el peso medio de todos los artículos.
select  art_codigo, art_nome, art_peso, art_color
from artigos
where art_color = (select art_color from artigos where art_codigo = '242C283')
    and art_peso >= (select avg(art_peso) from artigos);

#3. Seleccionar el código de tienda y nombre del gerente de las tiendas en las que se vendió, por lo menos, una unidad del artículo de código 077WN45.
select te.tda_id as codigo_tienda, em.emp_nome as nombre_gerente
from tendas as te inner join empregados as em on te.tda_xerente = em.emp_id
where te.tda_id = any (select distinct ve.ven_tenda
                        from vendas as ve inner join detalle_vendas as dv 
                        on ve.ven_id = dv.dev_venda
                        where dv.dev_artigo  =  '077WN45');

# 4. Mostrar la lista de artículos con un precio de venta mayor que el precio de venta del artículo más barato de color negro.
select * from artigos
where art_pv > any (select art_pv from artigos where art_color="NEGRO");

# 5. Seleccionar código de artículo, descripción y código del proveedor para que los artículos que suministra el proveedor que tiene el nombre SEAGATE.
select art_codigo as articulo, art_nome as descripcion, art_provedor as 'codigo de proveedor'
from artigos
where art_provedor = (select prv_id from provedores where prv_nome = 'SEAGATE');

# 6. Mostrar información del artículo más caro, seleccionando su código, nombre, precio de venta y nombre del proveedor que lo suministra.
select ar.art_codigo as articulo, ar.art_nome as descripcion, ar.art_pv as precio_venta, pr.prv_nome as proveedor
from artigos as ar inner join provedores as pr on ar.art_provedor=pr.prv_id
where ar.art_pv = (select max(art_pv) from artigos);

# 7. Mostrar el nombre y precio de venta de los artículos que tienen un precio de venta comprendido entre el precio del artículo 0713242 
# y la media de precios de todos los artículos. Los datos deben mostrarse ordenados alfabéticamente por el nombre del artículo.
select art_nome as artigo, art_pv as precio
from artigos
where art_pv between (select art_pv from artigos where art_codigo='0713242') and (select avg(art_pv) from artigos)
order by art_nome;

# 8. Obtener la lista de proveedores que suministran, como mínimo, un artículo de color negro.
select * from  provedores
where prv_id in (select distinct art_provedor from artigos where art_color  =  "NEGRO") ;

# 9. Mostrar el identificador de cliente, apellidos y nombre en la misma columna separados por coma, para los clientes que solo tienen una venta. 
# El resultado debe estar ordenador por el identificador de cliente.
select clt_id as cliente, concat(clt_apelidos,', ',clt_nome) as 'apellidos y nombre'
from clientes where clt_id in (select ven_cliente from vendas group by ven_cliente having count(*) = 1);

# 10. Mostrar el identificador y nombre de los clientes que hicieron alguna compra después del día en que el cliente número 6 hizo su última compra.
select clt_id, clt_nome
from clientes
where clt_id in ( select ven_cliente from vendas where ven_data > (select max(ven_data)
from vendas where ven_cliente=6));

# 11. Mostrar los nombres de los gerentes de las tiendas en las que se hizo alguna venta.
select em.emp_nome as gerente
from tendas as te inner join empregados as em on te.tda_xerente = em.emp_id
where te.tda_id in (select distinct ven_tenda from vendas);

# 12. Importe total de las ventas que se hicieron al cliente LEANDRO FERREIRO BENITEZ.
select sum((dv.dev_cantidade*dv.dev_prezo_unitario)*(1-dv.dev_desconto/100)) as importe_total
from vendas as ve inner join detalle_vendas as dv on ve.ven_id = dv.dev_venda
where ve.ven_cliente in (select clt_id from clientes 
where clt_apelidos='FERREIRO BENITEZ' and clt_nome='LEANDRO');

# 13. Seleccionar el id, apellidos y nombres de los empleados que aún no hicieron ninguna venta.
select emp_id, emp_apelidos, emp_nome
from empregados where emp_id not in (select distinct ven_empregado from vendas);

# 14. Mostrar la lista de artículos con un precio de venta mayor que el precio de venta del artículo más barato de color negro.
select * from artigos as ar1
where exists (select art_color from artigos as ar2
where art_color="negro" and ar1.art_pv > ar2.art_pv);

# 15. Mostrar el nombre de los artículos de color negro que tienen alguna venta de más de 5 unidades.
select art_nome, art_color
from artigos
where art_color = "NEGRO" and exists (select dev_artigo from detalle_vendas
where dev_artigo = art_codigo and dev_cantidade > 5);

# 16. Mostrar nombre y apellidos de los clientes que no hicieron ninguna compra.
select clt_nome, clt_apelidos
from clientes
where not exists (select * from vendas where ven_cliente=clt_id);