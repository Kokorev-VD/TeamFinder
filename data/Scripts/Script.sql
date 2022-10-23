create table usertable(
id int,
login text,
password text,
tg text,
description text,
role text,
imageId int
)

drop table usertable 

insert into userTable values(0, 'Vitya', '1234', '@shlepa05', 'Hi! My name is Vitya and i am interested in Kotlin', 'admin', 0)

insert into userTable values(1, 'Denis', '4321', '@TWNTxygen', 'Hi! My name is Denis and i am interested in cyber security', 'admin', 0)

delete from userTable where login ='Misha' or login='Vanya' 

select * from userTable

select * from userTable where id = (select max(id) from userTable)