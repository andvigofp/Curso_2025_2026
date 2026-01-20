create database library;

use library;

create table book (idBook int primary key AUTO_INCREMENT, code varchar(20), title varchar(200), authors varchar(300), year int);
create table client (idClient int primary key AUTO_INCREMENT, DNI char(9), name varchar(200), email varchar(200));
create table loan (idLoan int primary key AUTO_INCREMENT, idBook int, idClient int, date Date, borrowed bool, foreign key (idBook) references book(idBook), foreign key (idClient) references client(idClient));

insert into book(idBook,code, title, authors, year) values (1,'asdfg', 'Pride and Prejudice', 'Jane Austen',1870);
insert into book(idBook,code, title, authors, year) values (2,'asrwqeg', 'Persuation', 'Jane Austen',1875);
insert into book(idBook,code, title, authors, year) values (3,'asdfdsa', 'El Quijote', 'Miguel de Cervantes',1700);
insert into client(idClient, DNI, name, email) values (1,'11111111A', 'Ana', 'ana@gmail.com');
insert into client(idClient, DNI, name, email) values (2,'22222222B', 'Pablo', 'pablo@gmail.com');

insert into loan values (1,1,1,'2013-11-05', 0);
insert into loan values (2,2,1,'2013-02-05', 1);
insert into loan values (3,3,1,'2013-12-10', 1);
insert into loan values (4,2,2,'2013-11-05', 0);
insert into loan values (5,3,2,'2013-11-05', 0);
