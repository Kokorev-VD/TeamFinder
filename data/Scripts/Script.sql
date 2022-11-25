create table usertable(
id int,
login text,
password text,
tg text,
description text,
role text,
imageId int
)

create table posttable(
id int,
creator int,
header text,
body text,
pos_mark int,
neg_mark int
)

alter table usertable
add tags text


drop table usertable 

drop table posttable 

insert into userTable values(0, 'Vitya', '1234', '@shlepa05', 'Hi! My name is Vitya and i am interested in Kotlin', 'admin', 0)

insert into postTable values(0, 0, 'TestHeader', 'TestBody', 0, 0)

insert into userTable values(1, 'Denis', '4321', '@TWNTxygen', 'Hi! My name is Denis and i am interested in cyber security', 'admin', 0)

delete from userTable where login ='Misha' or login='Vanya' 

delete from postTable where id = 0 or id = 1 

select * from userTable

select * from postTable where creator = 'Vitya'

:creator = vitya

update postTable set creator = "Van", header = "d" where id = 0

select * from userTable where id = (select max(id) from userTable)