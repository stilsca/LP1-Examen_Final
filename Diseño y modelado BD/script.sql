CREATE TABLE usuarios (
  idUsuario INT NOT NULL ,
  usuario TEXT NOT NULL,
  password TEXT NOT NULL,
  PRIMARY KEY (idUsuario));

CREATE TABLE marcas (
  idMarca INT NOT NULL ,
  nombreMarca TEXT NOT NULL,
  PRIMARY KEY (idMarca))
;

CREATE TABLE  clientes (
  idCliente INT NOT NULL ,
  documento TEXT NOT NULL,
  razonSocial TEXT NOT NULL,
  direccion TEXT NULL,
  contacto TEXT NULL,
  activo INT NOT NULL,
  PRIMARY KEY (idCliente))
;

CREATE TABLE impuestos (
  idImpuesto INT NOT NULL,
  nombreImpuesto TEXT NOT NULL,
  porcentajeImpuesto FLOAT NOT NULL,
  PRIMARY KEY (idImpuesto))
;

CREATE TABLE  productos (
  idProducto INT NOT NULL,
  idMarca INT NOT NULL,
  idImpuesto INT NOT NULL,
  codigo TEXT NULL,
  nombreProducto TEXT NOT NULL,
  existencia INT NOT NULL,
  precio INT NOT NULL,
  PRIMARY KEY (idProducto))
;

CREATE TABLE  condiciones (
  idCondicion INT NOT NULL,
  nombreCondicion TEXT NOT NULL,
  PRIMARY KEY (idCondicion))
;

CREATE TABLE  ventas (
  idVenta INT NOT NULL ,
  idCliente INT NOT NULL,
  idUsuario INT NOT NULL,
  idCondicion INT NOT NULL,
  fecha DATE NOT NULL,
  anulado INT NOT NULL,
  PRIMARY KEY (idVenta))
;

CREATE TABLE  ventas_det (
  idVentaDet INT NOT NULL ,
  idVenta INT NOT NULL,
  idProducto INT NOT NULL,
  cantidad FLOAT NOT NULL,
  precioUnidad INT NOT NULL,
  impuesto FLOAT NOT NULL,
  PRIMARY KEY (idVentaDet))
;

