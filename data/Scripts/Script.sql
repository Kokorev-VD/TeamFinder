create table UserLoginParamsTable(
id int unique,
login text,
pass text
)

create table UserTable(
id int unique,
tg text,
description text,
imageId int,
foreign key (id) references userloginparamstable (id)
)

create table UserAchievementsTable(
id int,
achievement text,
foreign key (id) references usertable (id)
)

create table PostTable(
id int unique,
creatorId int,
title text,
body text,
foreign key (creatorId) references usertable (id)
)

create table PostExtensionTable(
basedpostid int,
derivedpostid int,
foreign key (basedpostid) references posttable (id),
foreign key (derivedpostid) references posttable (id)
)

create table MarkTable(
postId int,
userId int,
markType int,
foreign key (postId) references posttable (id),
foreign key (userId) references usertable (id)
)

create table TagTable(
id int unique,
title text
)

create table TagToUserTable(
tagId int,
userId int,
foreign key (tagId) references tagtable (id),
foreign key (userId) references usertable (id)
)

create tabl e TagToPostTable(
tagId int,
postId int,
foreign key (tagId) references tagtable (id),
foreign key (postId) references posttable (id)
)

create table TeamTable(
teamId int,
userId int,
foreign key (teamId) references posttable (id),
foreign key (userId) references usertable (id)
)

drop table usertable 

drop table userachivementstable 

drop table posttable 

drop table tagtable

drop table usertotagtable

drop table posttotagtable 

drop table teamtable 

insert into userTable values(0, 'Vitya', '1234', '@shlepa05', 'Hi! My name is Vitya and i am interested in Kotlin', 0)

insert into postTable values(0, 0, 'TestHeader', 'TestBody', 0, 0)

insert into tagTable values(0, 'Math')

insert into usertotagtable values (0, 0)

insert into posttotagtable values (0, 0)

insert into usertoposttable values (0, 0)

insert into userTable values(1, 'Denis', '4321', '@TWNTxygen', 'Hi! My name is Denis and i am interested in cyber security', 'admin', 0)

delete from userTable where login ='Misha' or login='Vanya' 

delete from posttotagtable where postid = 0

select * from userTable

select * from postTable where creator = 'Vitya'

:creator = vitya

update postTable set creator = "Van", header = "d" where id = 0

select * from userTable where id = (select max(id) from userTable)