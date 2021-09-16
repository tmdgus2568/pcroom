create table CUSTOMER(
                         id varchar2(30) not null primary key,
                         role varchar(10) not null,
                         join_date date not null,
                         remain_time timestamp
);
alter table CUSTOMER add password varchar(20);
alter table CUSTOMER add phone varchar2(20);
alter table CUSTOMER add name varchar2(30);
alter table CUSTOMER add birthday date;
alter table CUSTOMER add address varchar2(100);

alter table CUSTOMER modify remain_time int not null;

select * from CUSTOMER;

drop table MEMBER;

-- create table MEMBER(
--                        mem_id varchar2(30) not null primary key references CUSTOMER(id),
--                        password varchar(20) not null,
--                        phone varchar2(20) not null unique ,
--                        name varchar2(30) not null,
--                        birthday date not null ,
--                        address varchar2(100)
-- );
-- select * from MEMBER;
--
-- drop table NONMEMBER;
-- create table NONMEMBER(
--                           nonmem_id varchar2(30) not null primary key references CUSTOMER(id),
--                           is_use varchar2(1) default 'N' not null
-- );
-- select * from NONMEMBER;

drop table RATEPLAN;

create table RATEPLAN(
                         plan_id int not null primary key,
                         name varchar2(30) not null unique ,
                         apply_time timestamp not null ,
                         price int not null
);
alter table RATEPLAN modify apply_time int not null;
delete RATEPLAN;
select * from RATEPLAN;

truncate table RATEPLAN;

insert into RATEPLAN(name, apply_time, price)
values ('학생 1300원 이벤트',sysdate,2000);

drop table BUYTIME;
create table BUYTIME(
                        id int not null primary key ,
                        plan_id int not null references RATEPLAN(plan_id),
                        customer_id varchar2(30) not null references CUSTOMER(id),
                        payment_date date not null,
                        payment_way varchar2(10) not null
);

select * from BUYTIME;

create table PRODUCT(
                        id int not null primary key,
                        name varchar2(30) not null unique ,
                        price int not null
);

select * from PRODUCT;

create table PORDER(
                       id int not null primary key ,
                       product_id int not null references PRODUCT(id),
                       customer_id varchar2(30) not null references CUSTOMER(id),
                       payment_way varchar2(10) not null ,
                       payment_status varchar2(1) default 'N' not null,
                       request varchar2(100) not null ,
                       payment_date date not null
);

select * from PORDER;

create table SEAT(
    id int not null primary key ,
    is_usavle varchar2(1) default 'N' not null
);

alter table seat rename column is_usavle to is_usable;

select * from SEAT;
insert into SEAT values (1,'Y');
insert into SEAT values (2,'Y');
insert into SEAT values (3,'Y');
insert into SEAT values (4,'Y');
insert into SEAT values (5,'Y');
insert into SEAT values (6,'Y');

create table VISIT(
    id int not null primary key ,
    customer_id varchar2(30) not null references CUSTOMER(id),
    seat_id int not null references SEAT(id),
    visit_date date,
    exit_date date

);

select * from VISIT;




