
create table news
(
   id integer auto_increment,
   author varchar(255) not null,
   link varchar(255) not null,
   description_value varchar(255) not null,
   title varchar(255) not null,
   published_date varchar(255) not null,
   retrieved_date varchar(255) not null,
   primary key(id)
);