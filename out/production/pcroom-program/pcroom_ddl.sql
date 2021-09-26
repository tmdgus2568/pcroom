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
                         id varchar2(30) not null primary key ,
                         apply_time int not null ,
                         price int not null,
                         role varchar2(10) not null
);

alter table RATEPLAN add role varchar2(10) not null;
alter table RATEPLAN modify apply_time int;
delete RATEPLAN;
select * from RATEPLAN;

insert into RATEPLAN values ('회원 1시간 요금제',60,1000,'회원');
insert into RATEPLAN values ('회원 2시간 요금제',120,2000,'회원');
insert into RATEPLAN values ('회원 3시간 요금제',180,3000,'회원');
insert into RATEPLAN values ('회원 6시간 요금제',3600,5000,'회원');
commit;



truncate table RATEPLAN;

insert into RATEPLAN(name, apply_time, price)
values ('학생 1300원 이벤트',sysdate,2000);

drop table BUYTIME;
create table BUYTIME(
                        id int not null primary key ,
                        plan_id varchar2(30) not null references RATEPLAN(id),
                        customer_id varchar2(30) not null references CUSTOMER(id),
                        payment_date date not null
);

select * from BUYTIME;

create table PRODUCT(
                        id int not null primary key,
                        name varchar2(30) not null unique ,
                        price int not null
);

select * from PRODUCT;

alter table PRODUCT add kinds varchar2(30) not null;
insert into PRODUCT(name, price, kinds) values ('콜라',1000,'음료');
insert into PRODUCT(name, price, kinds) values ('환타',1000,'음료');
insert into PRODUCT(name, price, kinds) values ('마운틴듀',1000,'음료');
insert into PRODUCT(name, price, kinds) values ('사이다',1000,'음료');
insert into PRODUCT(name, price, kinds) values ('신라면',2000,'라면');
insert into PRODUCT(name, price, kinds) values ('진라면',2000,'라면');
insert into PRODUCT(name, price, kinds) values ('불닭볶음면',2000,'라면');
insert into PRODUCT(name, price, kinds) values ('딜러핫치킨마요볶음면',3000,'라면');
insert into PRODUCT(name, price, kinds) values ('김치볶음밥',3500,'식사류');
insert into PRODUCT(name, price, kinds) values ('치킨마요덮밥',4500,'식사류');
insert into PRODUCT(name, price, kinds) values ('불닭마요덮밥',4500,'식사류');
insert into PRODUCT(name, price, kinds) values ('신라면+콜라',2800,'세트메뉴');
insert into PRODUCT(name, price, kinds) values ('진라면+콜라',2800,'세트메뉴');
insert into PRODUCT(name, price, kinds) values ('김치볶음밥+콜라',4200,'세트메뉴');
insert into PRODUCT(name, price, kinds) values ('치킨마요덮밥+콜라',5000,'세트메뉴');
commit ;

alter table PORDER drop column product_id;

create table PORDER(
                       id int not null primary key ,
                       product_id int not null references PRODUCT(id),
                       customer_id varchar2(30) not null references CUSTOMER(id),
                       payment_way varchar2(10) not null ,
                       payment_status varchar2(1) default 'N' not null,
                       request varchar2(100) not null ,
                       payment_date date not null
);

alter table PORDER modify request null;
commit;

select PORDER.*,PORDER_DETAIL.*
from porder inner join PORDER_DETAIL on PORDER.id = PORDER_DETAIL.porder_id
where PORDER.payment_status='N';

select * from PORDER;
delete PORDER where id=;
commit ;
alter table PORDER add seat_id int not null references SEAT(id);

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
commit;

update SEAT set is_usable='Y';
update SEAT set customer_id=null;
commit ;
alter table seat add customer_id varchar2(30) references CUSTOMER(id);

select  * from SEAT;


create table VISIT(
    id int not null primary key ,
    customer_id varchar2(30) not null references CUSTOMER(id),
    seat_id int not null references SEAT(id),
    visit_date date,
    exit_date date

);

select * from VISIT;

insert into visit(customer_id, seat_id, exit_date)
values('morphing25',5,sysdate);


drop table PORDER_DETAIL;
create table PORDER_DETAIL(
                      porder_id int not null references PORDER(id),
                      product_id int not null references PRODUCT(id),
                      num int default 1 not null,
                      constraint pk primary key(porder_id, product_id)

);
select * from PORDER_DETAIL;
alter table PORDER_DETAIL add price int not null;
alter table PORDER_DETAIL add name varchar2(30) not null;

select * from PORDER;

delete from PORDER_DETAIL;
delete from PORDER;


alter table PORDER add price_sum int not null;

select * from cols;

