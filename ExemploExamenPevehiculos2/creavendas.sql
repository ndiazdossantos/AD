drop table vendas;
drop type tipo_veh;
create type tipo_veh as object (
codveh varchar2(5),
tasas number
);
/
create table vendas (
id number primary key,
dni varchar2(10),
vehf tipo_veh);

insert into vendas values (1,'364d',tipo_veh('v1',200));
insert into vendas values (2,'361a',tipo_veh('v5',100));
insert into vendas values (3,'363c',tipo_veh('v7',300));
insert into vendas values (4,'366f',tipo_veh('v9',400));

commit;

