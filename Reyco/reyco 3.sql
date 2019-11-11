create database reyco;     *
****************************
use reyco;

CREATE TABLE  inventario (
  idProducto varchar(45)  PRIMARY KEY,
  nombre varchar(45) ,
  descripcion varchar(100) ,
  cantidad int,
  marca varchar(45),
 );


CREATE TABLE apartado (
nombre varchar(45),
direccion varchar(50),
estatus varchar(45),
idApartado int primary key
);


CREATE TABLE  exhibicion(
idProducto varchar(45) primary key,
cantidad  int,
marca varchar(45),
prodcuto varchar(45),
descripcion varchar(100));

create table pedidos(
ID int auto_increment primary key,
marca varchar(45),
producto varchar(45), 
cantidad int);

create table bitacora(
     id integer primary key AUTO_INCREMENT,
     operacion varchar(40),
     usuario varchar(40),
     host varchar (30),
     modificado datetime,
     tabla varchar (40))
    engine=innodb;


********************************************************************************************************************

insert into inventario values('912B3','AZULEJO','ROJO',5,'SONIC');
insert into pedidos values(12,'vitrox','producto',12);
insert into exhibicion values('B121',12,'vitrox','producto','rojo');
insert into apartado values('sergio daniel','brasil #9','sin entregar',02);

********************************************************************************************************************
delimiter //
create trigger Apartado_bitacora1 after INSERT  on apartado
     for each row
     begin
     insert into Bitacora(operacion, usuario,host, modificado, tabla) VALUES
     ('INSERTO DATOS EN APARTADO', Substring_index(USER(),'@',1),Substring_index(USER(),'@',-1),NOW(),'Apartado');
     end;
     //

delimiter //
create trigger Apartado_bitacora2 after delete  on apartado
     for each row
     begin
     insert into Bitacora(operacion, usuario,host, modificado, tabla) VALUES
     ('BORRO DATOS EN APARTADO', Substring_index(USER(),'@',1),Substring_index(USER(),'@',-1),NOW(),'Apartado');
     end;
     //

delimiter //
create trigger Inventario_bitacora1 after INSERT  on inventario
     for each row
     begin
     insert into Bitacora(operacion, usuario,host, modificado, tabla) VALUES
     ('INSERTO DATOS EN INVENTARIO', Substring_index(USER(),'@',1),Substring_index(USER(),'@',-1),NOW(),'inventario');
     end;
     //     

delimiter //
create trigger Inventario_bitacora2 after delete  on inventario
     for each row
     begin
     insert into Bitacora(operacion, usuario,host, modificado, tabla) VALUES
     ('BORRO DATOS EN INVENTARIO', Substring_index(USER(),'@',1),Substring_index(USER(),'@',-1),NOW(),'inventario');
     end;
     //

delimiter //
create trigger Exhibicion_bitacora1 after INSERT  on exhibicion
     for each row
     begin
     insert into Bitacora(operacion, usuario,host, modificado, tabla) VALUES
     ('INSERTO DATOS EN EXHIBICION', Substring_index(USER(),'@',1),Substring_index(USER(),'@',-1),NOW(),'exhibicion');
     end;
     //     

delimiter //
create trigger Exhibicion_bitacora2 after delete  on exhibicion
     for each row
     begin
     insert into Bitacora(operacion, usuario,host, modificado, tabla) VALUES
     ('BORRO DATOS EN EXHIBICION', Substring_index(USER(),'@',1),Substring_index(USER(),'@',-1),NOW(),'exhibicion');
     end;
     //

     delimiter //
create trigger Pedidos_bitacora1 after INSERT  on pedidos
     for each row
     begin
     insert into Bitacora(operacion, usuario,host, modificado, tabla) VALUES
     ('INSERTO DATOS EN PEDIDOS', Substring_index(USER(),'@',1),Substring_index(USER(),'@',-1),NOW(),'pedidos');
     end;
     //     

delimiter //
create trigger Inventario_bitacora2 after delete  on pedidos
     for each row
     begin
     insert into Bitacora(operacion, usuario,host, modificado, tabla) VALUES
     ('BORRO DATOS EN PEDIDOS', Substring_index(USER(),'@',1),Substring_index(USER(),'@',-1),NOW(),'pedidos');
     end;
     //

*****************************************************************************************************************
usuarios 

grant select on reyco.* to "invitado"@"localhost"identified by  "invitado";

grant all privileges on reyco.* to 'administrador'@'localhost' identified by 'admin'
     with grant option;
     
grant all privileges on reyco.* to 'tester'@'localhost' identified by 'tester'
     with grant option;
 