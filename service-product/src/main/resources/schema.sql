create table tbl_categories
(
   id integer not null,
   name varchar(100) not null,
   primary key(id)
);

create table tbl_products
(
   id integer not null,
   name varchar(100) not null,
   code varchar(60) not null,
   description varchar(255) null,
   stock DOUBLE null,
   price DOUBLE null,
   status varchar(20) null,
   category_id integer not null,
   create_at TIMESTAMP not null,
   primary key(id)
);
