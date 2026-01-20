DROP DATABASE IF EXISTS school;
CREATE DATABASE school;
USE school;

CREATE TABLE student (
    id CHAR(9) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    age INT NOT NULL
);

INSERT INTO student VALUES ('11111111A','Draco','Malfoy',25);
INSERT INTO student VALUES ('22222222B','Hermione','Granger',23);
INSERT INTO student VALUES ('33333333C','Harry','Potter',20);
INSERT INTO student VALUES ('44444444D','Ron','Weasley',22);