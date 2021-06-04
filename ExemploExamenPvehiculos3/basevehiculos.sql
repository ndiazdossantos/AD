drop table vehiculos;
drop type datosv ;
create type datosv as object (
prezoorixe number,
anomatricula number
);
/

create table vehiculos(
idv varchar2(2) ,
nomv varchar2(15),
datos datosv
);



insert into vehiculos values('v1', 'hyundai', datosv( 12000,  2013));
insert into vehiculos values('v10', 'audi',  datosv(21000,  2011));
insert into vehiculos values('v2', 'honda',  datosv(21000,  2015));
insert into vehiculos values('v3', 'seat', datosv( 10000,  2019));
insert into vehiculos values('v4', 'renault', datosv( 19000,  2000));
insert into vehiculos values('v5', 'citroen', datosv( 20000,  2009));
insert into vehiculos values('v6', 'volvo',  datosv(9000,  2004));
insert into vehiculos values('v7', 'mercedes',  datosv(11000,  2007));
insert into vehiculos values('v8', 'jaguar', datosv( 13000,  2010));
insert into vehiculos values('v9', 'mazda',  datosv(9000,  2015));

drop table clientes;
drop type clientv ;
create type clientv as object (
nomec varchar2(15),
ncompras number
);
/
create table clientes(
idcli varchar2(5) ,
cliente clientv
);


insert into clientes values ('3610d', clientv('zoila',  0));
insert into clientes values ('361a', clientv('luis',  0));
insert into clientes values ('362b', clientv('pedro',  1));
insert into clientes values ('363c', clientv('paula',  2));
insert into clientes values ('364d', clientv('eva',  1));
insert into clientes values ('365e', clientv('jairo',  0));
insert into clientes values ('366f', clientv('xiu',  3));
insert into clientes values ('367g', clientv('jhon',  1));
insert into clientes values ('368h', clientv('ainara',  0));
insert into clientes values ('369k', clientv('uxia',  1));

commit;



