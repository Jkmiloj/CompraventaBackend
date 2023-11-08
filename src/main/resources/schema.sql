CREATE TABLE IF NOT EXISTS PERSON (
    cc INT not null,
    nombre VARCHAR(50) not null,
    apellido1 VARCHAR(20) not null,
    apellido2 VARCHAR(20) not null,
    edad INT not null,
    genero VARCHAR(1) not null,
    estado VARCHAR(1) not null,
    PRIMARY KEY (cc)
    );

CREATE TABLE IF NOT EXISTS VEHICULO (
    placa VARCHAR(6) not null,
    tipo VARCHAR(5) not null,
    cilindraje INT not null,
    modelo INT not null,
    marca VARCHAR(20) not null,
    ciudad VARCHAR(20) not null,
    estado VARCHAR(1) not null,
    PRIMARY KEY (placa)
    );

CREATE TABLE IF NOT EXISTS COMPRA (
    id INT not null,
    cc INT not null,
    placa VARCHAR(6) not null,
    fecha_compra DATE not null,
    PRIMARY KEY (id),
    FOREIGN KEY (cc) REFERENCES PERSON(cc),
    FOREIGN KEY (placa) REFERENCES VEHICULO(placa)
);

INSERT INTO PERSON  (cc, nombre, apellido1, apellido2, edad, genero, estado)
    VALUES
        (42369785, 'Armando', 'Paredes', 'largas', 35, 'M', 'A'),
        (32968322, 'Estela', 'Manco', 'Cuervo', 40, 'F', 'A'),
        (1125365788, 'Efrain', 'Salazar', 'Perez', 20, 'M', 'I'),
        (98765432, 'Juan', 'Franco', 'Mendez', 40, 'M', 'A');


INSERT INTO VEHICULO  (placa, tipo, cilindraje, modelo, marca, ciudad, estado)
    VALUES
        ('AMD256', 'carro', 1400, 2016, 'Chevrolet Aveo', 'Medellin', 'A'),
        ('RTI526', 'carro', 2500, 2020, 'Toyota Runner', 'Pereira', 'I'),
        ('FYZ95B','Moto', 199, 2014, 'Bajaj Pulsar', 'Medellin', 'V'),
        ('XYZ789','Carro', 3500, 2020, 'Toyota Land Cruiser', 'Rionegro', 'V');


INSERT INTO COMPRA (id, cc, placa, fecha_compra)
  VALUES
       (1,32968322, 'FYZ95B', '2023-08-10');