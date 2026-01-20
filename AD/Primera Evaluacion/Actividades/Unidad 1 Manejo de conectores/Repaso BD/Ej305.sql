# 1. Obtén los datos completos de los empleados.
select * from emp;

# 2. Lo mismo para los departamentos.
select * from depto;

# 3. Halla los datos de los empleados con puesto contable.
select *from emp where puesto='contable';

# 4. Halla los datos de los empleados con puesto contable pero ordenados por el nombre.
select * from emp where puesto='contable' order by nomemp;

# 5. El mismo resultado que el anterior pero ordenando de mayor a menor.
select * from emp where puesto='contable' order by nomemp desc;

# 6. Obtén el nombre y salario de los empleados.
select nomemp,sal from emp;

# 7. Halla el nombre de los departamentos.
select nomdep from depto;

# 8. Ídem pero ordenados por el nombre.
select nomdep from depto order by nomdep;

# 9. Lo mismo pero ordenando por localidad.
select nomdep from depto order by localidad;

# 10. Muestra los nombres de los departamentos ordenados por su ciudad, pero en orden inverso.
select nomdep from depto order by localidad desc;

# 11. Obtén el nombre y puesto de los empleados, ordenado por salario.
select nomemp,puesto from emp order by sal;

# 12. Ídem pero ordenando ahora por empleo y salario.
select nomemp,puesto from emp order by puesto,sal;

# 13. Ídem pero ordenando inversamente por empleo y normalmente por salario.
select nomemp,puesto from emp order by puesto desc,sal;

# 14. Obtén los salarios y las comisiones de los empleados del departamento 3.
select sal, comision from emp where numdep=3;

# 15. Ídem, pero ordenando por comisión.
select sal, comision from emp where numdep=3 order by comision;

# 16. Obtén las comisiones. Luego las distintas comisiones.
select comision from emp;

select distinct comision from emp;

# 17. Muestra las distintas comisiones y los nombres de los empleados.
select distinct comision,nomemp from emp;

# 18. Halla los distintos salarios y empleados.
select distinct sal,nomemp from emp;

# 19. Obtén las comisiones y los números de departamento posibles de la empresa, de manera que no se repitan.
select distinct comision, numdep from emp;

# 20. Obtén los nuevos salarios que resultarían de sumar a los empleados del departamento 3 una gratificación de 1000 €.
select sal+1000 as nuevo_sal from emp where numdep=3;

# 21. Ídem pero obteniendo también el salario anterior.
select sal,sal+1000 as nuevo_sal from emp where numdep=3;

# 22. Halla los empleados que tienen una comisión superior a la mitad de su salario.
select * from emp where comision is not null and comision>(sal/2);

# 23. Halla los empleados cuya comisión es menor o igual que el 25% del sueldo.
select * from emp where comision is not null and comision<=sal/4;

# 24. Haz que en cada fila figure Nombre y Puesto anteponiéndose a su respectivo valor.
select 'nombre: ', nomemp, ' puesto: ', puesto from emp;

# 25. Obtén el mismo resultado pero de forma que la etiqueta y el campo salgan en la misma columna.
select concat('nombre: ',nomemp) as nombre, concat(' puesto: ',puesto) as puesto from emp;

# 26. Halla el salario y la comisión de los empleados cuyo número de empleado supera a 7500.
select sal, comision from emp where numemp>7500;

# 27. Si dividimos los empleados en dos grupos, A y B, empezando los del grupo B en la letra J, obtén los nombres y empleos de los del grupo B, por orden alfabético.
select nomemp,puesto from emp where nomemp>='J' order by nomemp;

# 28. Obtén el salario, la comisión y el salario total (salario + comisión). de los empleados con comisión, ordenado por número de empleado.
select sal,comision,sal+comision as sal_total from emp where comision is not null order by numemp;

# 29. Ídem pero para los que no tienen comisión.
select sal,comision,sal as sal_total from emp where comision is null order by numemp;

# 30. Obtén el salario, la comisión y el salario total de todos los empleados.
select sal,comision,sal+comision as sal_total from emp where comision is not null
union
select sal,comision,sal as sal_total from emp where comision is null;

select sal,comision,sal+ifnull(comision,0) as sal_total from emp where comision is null order by numemp;

# 31. Halla el porcentaje que supone la comisión sobre el salario total ordenando por nombre.
# --Opción no válida porque saca nulos cuando la comisión es nula:
select (comision / (sal+comision))*100 as porcentaje from emp order by nomemp; 

# -- Opción válida:
select (ifnull(comision,0) / (sal+ifnull(comision,0)))*100 as porcentaje from emp order by nomemp;

# 32. Halla los empleados del departamento 1 cuyo nombre no contiene la cadena LA.
select * from emp where numdep=1 and nomemp not like '%LA%';

# 33. Obtén los nombres de los departamentos que no sean de VENTAS ni de ADMINISTRACION, ordenados por localidad.
select nomdep from depto where nomdep not in ('Ventas','administracion') order by localidad;

# 34. Deseamos conocer el nombre y departamento de los comerciales que no trabajan en el departamento 1 y cuyo salario es superior a 800, ordenados por la fecha de incorporación.
select nomemp, numdep from emp where puesto='COMERCIAL' and numdep!=1 and sal>800 order by feccont;

# 35. Para los empleados que tienen comisión obtén sus nombres en orden alfabético y el cociente entre salario y comisión.
select nomemp, ifnull(sal/comision,0) from emp where comision is not null order by 1;

# 36. Obtén información de los empleados cuyo nombre contiene exactamente cinco caracteres.
select * from emp where nomemp like '_____';

select * from emp where length(nomemp)=5;

# 37. Ídem pero cuyos nombres contengan al menos cinco caracteres.
select * from emp where nomemp like '_____%';

select * from emp where length(nomemp)>=5;

# 38. Halla los datos de los empleados para los que, su nombre comienza por A ysu salario es mayor que 1000, o, reciben comisión y trabajan en el departamento 3.
select* from emp where (nomemp like 'A%' and sal>1000) or (comision is not null and numdep=3);

# 39. Halla el nombre y salario total de todos los empleados ordenado por este último y por su propio salario.
select nomemp,sal+ifnull(comision,0) as sal_total from emp order by 2,sal;

# 40. Obtén los nombres, salarios y comisiones de los empleados que perciben un salario situado entre la mitad de la comisión y la propia comisión.
select nomemp, sal, comision from emp where sal between ifnull(comision,0)/2 and ifnull(comision,0);

# 41. Obtén el complementario del anterior.
select nomemp, sal, comision from emp where sal not between ifnull(comision,0)/2 and ifnull(comision,0);

# 42. Muestra los nombres y empleos de los empleados tales que su empleo acaba en BLE y su nombre empieza por A usando funciones (rigth y left).
select nomemp, puesto from emp where right(puesto,3)='MAN' and left(puesto,1)='A';

# 43. Intenta obtener el mismo resultado usando un predicado simple (con una expresión regular) .
select nomemp, puesto from emp where puesto like 'A%MAN';

# 44. Halla los nombres de los empleados que tienen como máximo cinco caracteres en su nombre.
select nomemp from emp where nomemp not like '_____%';

# 45. Suponiendo que el año próximo la subida del total percibido por empleado es del 6% y el siguiente del 7%, 
# halla los nombres y salarios totales actuales y futuros de todos los empleados, indicando para cada uno si tienen o no comisión.
select nomemp,sal as salario_actual, sal*1.06 as salario_2021, (sal*1.06)*1.07 as salario_2022,if(comision is null,'No tiene comision','Tiene comision') as tiene_comision from emp;

# 46. Halla el nombre y la fecha de ingreso de los empleados que no son vendedores.
select nomemp,puesto,feccont from emp where puesto!='COMERCIAL';

# 47. Obtén la información disponible de los empleados de cuyo número de empleado sea alguno de los siguientes: 
# 7844, 7900, 7521, 7782, 7934, 7678 y 7369, pero no uno de entre: 7902, 7839, 7499 ni 7878.
select * from emp where numemp in (7844, 7900, 7521, 7782, 7934, 7678 , 7369) and numemp not in (7902, 7839, 7499 , 7878);

# 48. Halla los salarios totales de todos los empleados.
select sum(sal+ifnull(comision,0)) from emp;

# 49. Ordena los empleados por su departamento y luego de manera descendente por su número.
select * from emp order by numdep,numemp desc;

# 50. Para los empleados que tienen como director a algún otro con número mayor que el suyo, obtén los que reciben de salario más de 1000 y menos de 2000, o están en el departamento 3.
select *from emp where numemp <= (select codjefe from depto  where emp.numdep=depto.numdep) and (sal between 1000 and 2000 or numdep=3);

# 51. Obtén el salario más alto de la empresa, el total destinado a comisiones y el número de empleados.
select max(sal),sum(ifnull(comision,0)),count(numemp) from emp;

select max(sal),sum(comision),count(numemp) from emp;

# 52. Halla el nombre del último empleado por orden alfabético.
select max(nomemp) from emp;

# 53. Halla el salario más alto, el más bajo y la diferencia entre ellos.
select count(distinct puesto),count(numemp),count(distinct sal),sum(sal) from emp where numdep=3;

# 54. ¿Cuántos empleos diferentes, empleados y diferentes salarios encontramos en el departamento 3, y a qué cantidad asciende la suma de los salarios de dicho departamento?
select count(distinct puesto),count(numemp),count(distinct sal),sum(sal) from emp where numdep=3;

# 55. ¿Cuántos empleados tienen comisión?
select count(numemp) from emp where comision is not null;

# 56. ¿Cuántos empleados tiene el departamento 2?
select numdep,count(numemp) as empleados from emp group by numdep having count(numemp)>3;

# 57. Halla los departamentos que tienen más de tres empleados y el número de empleados de los mismos.
select numdep,count(numemp) as empleados from emp group by numdep having count(numemp)>3;

# 58. ¿Qué puestos distintos existen en la empresa y cuántos empleados desempeñan cada uno?.
select puesto,count(numemp) from emp group by puesto;

# 59. Halla la suma de los salarios de cada departamento.
select numdep,sum(sal) from emp group by numdep;

# 60. Halla los empleados del departamento 3 por orden descendente de comisión.
select nomemp,comision from emp where numdep=3 order by comision desc;

# 61. ¿Cuáles son los dos empleados con los mayores salarios?
select nomemp,sal from emp order by 2 desc limit 2;

# 62. Halla los datos de los empleados cuyo salario es mayor que el del empleado de código 7934, ordenando por el propio salario.
select * from emp where sal>(select sal from emp where numemp=7934) order by sal;

# 63. Obtén información en la que se reflejen los nombres, empleos y salarios tanto de los que superan el salario de Allen como del propio Allen.
select nomemp, puesto,sal from emp where sal >= (select sal from emp where nomemp='ALLEN');

# 64. ¿Quiénes reciben el salario más alto y más bajo, y a cuánto ascienden?.
select * from emp where sal = (select max(sal) from emp) or sal = (select min(sal) from emp);

# 65. Halla los empleados cuyo salario supera o coincide con la media del salario de la empresa.
select * from emp where sal>=(select avg(sal) from emp);

# 66. Obtén los empleados cuyo salario supera al de sus compañeros de departamento.
select * from emp E1 where sal>=all(select sal from emp E2 where E1.numdep=E2.numdep);

select * from emp E1 where sal=(select max(sal) from emp E2 where E1.numdep=E2.numdep);

select * from emp E1 where not exists(select 1 from emp E2 where e1.numdep=e2.numdep and e1.sal<e2.sal);

# 67. Obtén los empleados del departamento 1 que tienen el mismo empleo que alguien del departamento de VENTAS``.
select * from emp where numdep=1 and puesto in (select puesto from emp where numdep=(select numdep from depto where nomdep='VENTAS'));

# 68. vObtén información sobre los empleados que tienen el mismo trabajo que los empleados que trabajan en Pontevedra.
select * from emp where puesto in (select puesto from emp where numdep in (
        select numdep from depto where localidad='PONTEVEDRA'));

# 69. Obtén todos los departamentos sin empleados.
select * from depto where numdep not in (
    select distinct numdep from emp);
    
# 70. Halla el departamento cuya suma de salarios sea la más alta. Halla también la mencionada suma.
select numdep,sum(sal) from emp group by numdep having sum(sal)>=ALL(select sum(sal) from emp group by numdep);

# 71. Obtén los empleados que no son supervisados por ningún otro.
select * from emp E where exists (select 1 from depto D where d.numdep=e.numdep and d.codjefe=e.numemp);

select * from emp E where numemp in (select codjefe from depto D where E.numdep=D.numdep );

# 72. Halla el nombre de los empleados que, teniendo un salario superior a 1000, tienen como director al empleado de código 7499
select nomemp from emp where sal>1000 and numdep in (select numdep from depto where codjefe=7499);

# 73. Halla el conjunto complementario del resultado del ejercicio anterior.
select nomemp from emp where (sal <=1000 or numdep not in (select numdep from depto where codjefe=7499));

select nomemp from emp where not (sal>1000 and numdep in (select numdep from depto where codjefe=7499));

# 74. Para los empleados que tienen como jefe a algún otro con número menor que el suyo, obtén los que reciben de salario más de 1000 y menos de 2000, o están en el departamento 3.
select * from emp where numemp>(select codjefe from depto where emp.numdep=depto.numdep) and (sal between 1000 and 2000 or numdep=3);

# 75. Halla la media de los salarios de los departamentos cuyo salario mínimo supera a 900, considerando los salarios inferiores a 5000. 
# Además se necesita el código y el nombre de los departamentos.
SELECT  e.numdep, d.nomdep,  avg(e.sal) as media_salario
from  emp e inner join depto d ON e.numdep = d.numdep
where e.sal < 5000
group by e.numdep, d.nomdep
having min(e.sal) > 900;


# 76. Determina las localidades que son sede de departamentos que no tienen empleados, y en las que trabajan al menos cuatro empleados, 
# indicando el número de empleados que trabaja en cada una.
select localidad from depto where localidad not in (select localidad from depto where numdep in (select numdep from emp)) 
UNION
select localidad from depto d,emp e where d.numdep=e.numdep group by localidad having count(numemp)>4;

# 77. ¿Qué empleados trabajan en ciudades que tienen más de cinco letras?. Ordena el resultado inversamente por ciudades y normalmente por nombres de empleados.
select e.nomemp,d.localidad from emp e,depto d where e.numdep=d.numdep 
and d.localidad like '______%' 
order by localidad desc,nomemp;

# 78. Halla los jefes que tienen por lo menos un empleado a su cargo, ordenados inversamente por nombre.
select jefe.nomemp from emp jefe, depto d, emp e where jefe.numemp=d.codjefe and e.numdep=d.numdep and jefe.numemp!=e.numemp 
group by jefe.nomemp having count(e.numemp)>=1 order by 1 desc;

# 79. ¿Cuántos empleos hay en cada departamento y cuál es la media anual del salario de cada uno?. Incluye en el resultado el nombre del departamento para clarificar el resultado.
select d.nomdep,count(distinct e.puesto),avg(ifnull(e.sal,0)) from depto d left outer join emp e on (d.numdep=e.numdep) group by d.nomdep;

# 80. Obtén los empleados que trabajan en Santiago o en Vilagarcía.
select e.nomemp,d.localidad from emp e right outer join depto d on (e.numdep=d.numdep) where d.localidad in ('Santiago','Vilagarcia');

# 81. Obtén un listado en el que se reflejen el código y el nombre de cada supervisor, junto al número de empleados que supervisa directamente. 
# Si son jefes de un departamento pero no tienen empleados a su cargo también deberían aparecer.

select jefe.numemp,jefe.nomemp,count(e.numemp) 
from emp jefe join depto d on (jefe.numemp=d.codjefe) left outer join emp e on (d.numdep=e.numdep) 
group by jefe.numemp, jefe.nomemp;

-- El primero va con join porque quiero que devuelva solo los que SI son jefes de departamento, y el segundo va con left outer join para que me los devuelva tengan empleados a su cargo o no

# 82. Halla los empleados con salario mayor de cada departamento. Se debe incluir el salario y el nombre del departamento.
select e.nomemp,e.sal,d.nomdep from emp e, depto d where e.numdep=d.numdep and sal=(select max(sal) from emp e2 where e2.numdep=d.numdep);

# 83. Para cada departamento halla la suma del salario, el código y el nombre del departamento si éste tiene al menos dos empleados y 
# la media de los salarios del departamento es mayor que la media de los salarios de todos los empleados.
select d.numdep,d.nomdep,sum(e.sal)
from depto d,emp e 
where d.numdep=e.numdep 
group by d.numdep,d.nomdep 
having count(e.numemp)>=2 and avg(e.sal)>(select avg(sal) from emp);

# 84. Comprueba si algún empleado que sea jefe de un departamento que no sea el suyo.
select e.* from emp e, depto d where e.numemp=d.codjefe and e.numdep!=d.numdep;