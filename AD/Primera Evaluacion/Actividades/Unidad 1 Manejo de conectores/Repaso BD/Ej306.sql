# 1. Mostrar el nombre de los jugadores del equipo Lakers.
select nombre, nombre_equipo from jugadores where nombre_equipo like 'Lakers'; 

select nombre, nombre_equipo from jugadores where nombre_equipo="Lakers"; 

# 2. Mostrar los jugadores españoles (Spain) que juegan en los Lakers.
select * from jugadores
where nombre_equipo like 'Lakers' and procedencia LIKE 'Spain';

# 3. Mostrar los jugadores españoles (Spain) y eslovenos (Slovenia) que juegan en los Lakers.
select * from jugadores
where nombre_equipo like 'Lakers' and (procedencia like 'Spain' or procedencia like 'Slovenia'); 

select * from jugadores
where nombre_equipo like 'Lakers' and procedencia in ('Spain','Slovenia');

# 4. Mostrar los jugadores españoles, eslovenos o serbios (Serbia Montenegro) que juegan en los Lakers.
select * from jugadores
where nombre_equipo like 'Lakers' and procedencia in ('Spain', 'Slovenia', 'Serbia Montenegro');

# 5. Mostrar el nombre del jugador, nombre del equipo y peso de aquellos jugadores cuyo peso esté entre 270 y 300 libras.
select nombre, nombre_equipo, peso from jugadores
where peso between 270 and 300;

# 6. Mostrar el nombre del jugador, nombre del equipo y el peso en kilogramos de los jugadores de la NBA que pesen entre 120 y 150 kg, sabiendo que una libra equivale a 0.4535 kg.
select nombre, nombre_equipo, peso * 0.4535 as peso_kg from jugadores
where peso * 0.4535 between 120 and 150;

# 7. Mostrar el nombre del jugador y nombre del equipo de aquellos jugadores de los que se desconoce su procedencia pero se conoce su altura.
select nombre, nombre_equipo, procedencia, altura from jugadores
where procedencia is null and altura is not null;

# 8. Mostrar el nombre del equipo y procedencia de los equipos cuyo nombre contenga como segunda letra la o y termine por s.
select nombre, ciudad from equipos
where nombre like '_o%s';

# 9. Mostrar el nombre del equipo y procedencia de los equipos cuyo nombre empiece por r y tenga más de 6 caracteres.
select nombre, ciudad from equipos
where nombre like 'r______%';

# 10. Mostrar el nombre de los 5 primeros equipos almacenados en la base de datos.
select nombre from equipos
order by nombre limit 5;

# 11. Mostrar el nombre de los 3 primeros equipos a partir del 5º equipo.
select nombre from equipos
order by nombre limit 4,3;

# 12. Mostrar el peso del jugador más pesado de la liga.
select max(peso) as peso_maximo from jugadores;

# 13. Mostrar el número de jugadores que juegan en los Lakers.
select count(*) as num_jugadores_lakers from jugadores where nombre_equipo='Lakers';

# 14. Mostrar el número de jugadores que juegan en los Warriors.
select count(*) as num_jugadores_warriors from jugadores where nombre_equipo='Warriors';

# 15. Mostrar el peso medio de los jugadores de España, Italia y Francia.
select procedencia, avg(peso) as peso_medio from jugadores
where procedencia in ('Spain', 'Italy', 'France') group by procedencia;

# 16. Mostrar el nombre de los equipos cuyos jugadores pesen de media más de 228 libras y ordenarlos por la media del peso.
select nombre_equipo, avg(peso) as peso_medio from jugadores
group by nombre_equipo having avg(peso) > 228
order by avg(peso) desc;

# 17. Seleccionar los equipos que tienen 1 o más jugadores españoles, ordenándolos por nombre de equipo.
select nombre_equipo from jugadores
where procedencia = 'Spain'
group by nombre_equipo having count(codigo) >= 1
order by nombre_equipo;

# 18. Mostrar el número de jugadores de aquellos equipos que tengan jugadores procedentes de más de 5 sitios diferentes.
select nombre_equipo, count(*) as numero_jugadores from jugadores
group by nombre_equipo having count(distinct procedencia) > 5;

# 19. Mostrar la altura media de los jugadores que son españoles o que no tienen procedencia conocida.
select avg(altura) as altura_media from jugadores
where procedencia is null or procedencia = 'Spain';

# 20. Mostrar el nombre de los jugadores que juegan en equipos de la división SouthWest.
select nombre from jugadores
where nombre_equipo in (select nombre from equipos where division='SouthWest');

# 21. Mostrar el nombre y altura del jugador más alto de la liga.
select nombre, altura from jugadores
where altura = (select max(altura) from jugadores);

# 22. Mostrar el nombre y altura del jugador más alto de cada equipo.
select nombre, altura, nombre_equipo from jugadores j1
where altura = (select max(altura) from jugadores j2 where j1.nombre_equipo=j2.nombre_equipo);

# 23. Si hay jugadores españoles jugando en el equipo Raptors, mostrar el nombre de todos los equipos.
select nombre from equipos
where exists(select codigo from jugadores where procedencia='Spain' and nombre_equipo='Raptors');

# 24. Mostrar el nombre y división de los equipos que no tienen jugadores españoles.
select nombre, division from equipos
where nombre not in (select nombre_equipo from jugadores where procedencia='Spain');

# 25. Mostrar el nombre y peso de los bases (posición = G) que pesan más que algún pívot (posición = C) de la liga.
select nombre, peso from jugadores
where posicion='G' and peso > any (select peso from jugadores where posicion='C');

# 26. Mostrar todos los datos del equipo donde juega el jugador más alto de la liga.
select * from equipos
where nombre in (select nombre_equipo from jugadores where altura = (select max(altura) from jugadores));

# 27. Mostrar los datos de los equipos que no han ganado ningún partido como visitantes.
select * from equipos
where nombre not in (select eq.nombre from equipos eq inner join partidos part on eq.nombre=part.equipo_visitante
where puntos_visitante > puntos_local);

# 28. Mostrar, para cada equipo, el nombre del equipo y el nombre y peso de su jugador más pesado.
select eq.nombre, jug.nombre, jug.peso
from equipos eq inner join jugadores jug on eq.nombre=jug.nombre_equipo
where jug.peso = (select max(peso) from jugadores jug2 where jug2.nombre_equipo=eq.nombre);

# 29. Mostrar el número de jugadores que hay en cada conferencia.
select eq.conferencia, count(jug.codigo) as numero_jugadores
from jugadores jug inner join equipos eq on jug.nombre_equipo=eq.nombre
group by eq.conferencia;

# 30. Mostrar el nombre del equipo, conferencia y posición de los jugadores que hayan anotado más de 35 puntos por partido.
select jug.nombre, jug.posicion, eq.nombre, eq.conferencia
from equipos eq inner join jugadores jug on eq.nombre=jug.nombre_equipo inner join estadisticas est on jug.codigo=est.jugador
where est.puntos_por_partido > 35;

# 31. Para cada equipo, mostrar los datos del equipo y el número de partidos que han jugado como locales.
select eq.*, count(part.codigo) as partidos_local
from equipos eq inner join partidos part on eq.nombre=part.equipo_local
group by nombre, ciudad, conferencia, division;

# 32. Mostrar los nombres de los equipos que hayan jugado algún partido resuelto por un solo punto de diferencia 
# (es decir, que hayan ganado o perdido por sólo 1 punto) y mostrar también la temporada en la que se jugó el partido.
select eq_local.nombre as nombre_local, eq_visitante.nombre as nombre_visitante, puntos_local, puntos_visitante, temporada
from equipos eq_local inner join partidos part on eq_local.nombre=part.equipo_local
inner join equipos eq_visitante on eq_visitante.nombre=part.equipo_visitante
where abs(puntos_local - puntos_visitante) = 1;

# 33. Mostrar los jugadores cuyo peso coincide con el de otro jugador de su mismo equipo.
select * from jugadores jug1
where peso = any (select peso from jugadores jug2 
where jug1.codigo!=jug2.codigo and jug1.nombre_equipo=jug2.nombre_equipo);

# 34. Mostrar el nombre, conferencia y número de jugadores de cada equipo. Si un equipo no tiene jugadores se debe mostrar un 0.
select eq.nombre, eq.conferencia, count(jug.codigo)
from equipos eq left join jugadores jug on eq.nombre=jug.nombre_equipo
group by eq.nombre, eq.conferencia;

# 35. Mostrar los equipos que no han jugado ningún partido como visitante.
select eq.nombre, count(part.codigo) as partidos_visitante
from equipos eq left join partidos part on eq.nombre=part.equipo_visitante
group by eq.nombre
having count(part.codigo) = 0;