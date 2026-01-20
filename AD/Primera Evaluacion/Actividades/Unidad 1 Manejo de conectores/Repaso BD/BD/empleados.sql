drop database if exists emple01;
create database emple01;
use emple01;

CREATE TABLE depto ( 
  numdep decimal(2,0) primary key, 
  nomdep varchar(14) default NULL, 
  localidad varchar(13) default NULL,
  codjefe decimal(4,0) default NULL
); 

INSERT INTO depto VALUES (1,'CONTABILIDAD','SANTIAGO',7782); 
INSERT INTO depto VALUES (2,'ADMINISTRACION','VIGO',7369); 
INSERT INTO depto VALUES (3,'VENTAS','PONTEVEDRA',7499); 
INSERT INTO depto VALUES (4,'OPERACIONES','VILAGARCIA',7934); 

CREATE TABLE emp ( 
  numemp decimal(4,0) primary key, 
  nomemp varchar(10)  , 
  puesto varchar(10)  , 
  feccont date  , 
  sal decimal(7,2)  , 
  comision decimal(7,2)  , 
  numdep decimal(2,0) ,
  constraint fk_emp_depto foreign key (numdep) references depto (numdep)
); 

INSERT INTO emp VALUES (7369,'SMITH','CONTABLE', '1980-12-17' ,800.00, NULL,   2); 
INSERT INTO emp VALUES (7499,'ALLEN','COMERCIAL','1981-02-20' ,1600.00,300.00, 3); 
INSERT INTO emp VALUES (7521,'WARD','COMERCIAL', '1981-02-22' ,1250.00,500.00, 3); 
INSERT INTO emp VALUES (7566,'JONES','MANAGER',  '1981-04-02' ,2975.00,NULL,   2); 
INSERT INTO emp VALUES (7654,'MARTIN','COMERCIAL', '1981-09-28' ,1250.00,1400.00,3); 
INSERT INTO emp VALUES (7698,'BLAKE','MANAGER',  '1981-05-01' ,2850.00,NULL,   3); 
INSERT INTO emp VALUES (7782,'CLARK','MANAGER',  '1981-06-09' ,2450.00,NULL,   1); 
INSERT INTO emp VALUES (7788,'SCOTT','ANALISTA', '1982-12-09' ,3000.00,NULL,   2); 
INSERT INTO emp VALUES (7839,'KING','PRESIDENTE',  '1981-11-17' ,5000.00,NULL,   1); 
INSERT INTO emp VALUES (7844,'TURNER','COMERCIAL', '1981-09-08' ,1500.00,0.00,   3); 
INSERT INTO emp VALUES (7876,'ADAMS','CONTABLE',  '1983-01-12' ,1100.00,NULL,   2); 
INSERT INTO emp VALUES (7900,'JAMES','CONTABLE',  '1981-12-03' ,950.00, NULL,   3); 
INSERT INTO emp VALUES (7902,'FORD','ANALISTA',  '1981-12-03' ,3000.00,NULL,   2); 
INSERT INTO emp VALUES (7934,'MILLER','CONTABLE', '1982-01-23' ,1300.00,NULL,   1); 

alter table depto add constraint fk_depto_jefe foreign key (codjefe) references emp(numemp);