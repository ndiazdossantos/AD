drop table finalveh;
drop type tipo_vehf;
create type tipo_vehf as object (
nomeveh varchar2(20),
pf number
);
/
create table finalveh (
id number primary key,
dni varchar2(10),
nomec varchar2(30),
vehf tipo_vehf);

-- insert into finalveh values (1,'369g','luis',tipo_vehf('kiara',300));


commit;

