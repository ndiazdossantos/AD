drop table confirmadas cascade constraint;
drop table reservas cascade constraint;
create table reservas
(
codr integer,
dni varchar2(4),
idvooida integer,
idvoovolta integer
);
	
insert into reservas values(1,'361a',1,2);		
insert into reservas values(2,'362b',3,4); 		
insert into reservas values(3,'361a',5,6); 		

create table confirmadas
(
codr integer,
dni varchar2(4),
orixeida varchar2(10),
orixevolta varchar2(10),
prezoreserva integer
);
commit;
