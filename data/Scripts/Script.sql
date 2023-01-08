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

create table AchievementTable(
id int unique,
achievement text
)

create table usertoachievementtable(
achievementId int,
userId int,
foreign key (achievementId) references achievementTable(id),
foreign key (userId) references usertable(id)
)

create table userCreatorToPostTable(
userId int,
postId int,
foreign key (userId) references usertable (id),
foreign key (postId) references postTable (id)
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

create table TagToPostTable(
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

create table CityTable(
id int unique,
name text
)

create table CityToUserTable(
cityId int,
userId int,
foreign key (cityId) references citytable(id),
foreign key (userId) references usertable(id)
)

create table JobTable(
id int unique,
name text
)


create table JobToUserTable(
jobId int,
userId int,
foreign key (jobId) references jobtable(id),
foreign key (userId) references usertable(id)
)

create table AchievementToTagTable(
achievementId int,
tagId int,
foreign key (achievementId) references achievementtable (id),
foreign key (tagId) references tagtable (id)
) 

drop table achievementtypetotagtable 

insert into usercreatortoposttable values(1, 1)

alter table posttable drop column creatorId

create table achievementTypeTable(
id int unique,
value int,
name text
)



create table achievementToTypeTable(
achievementId int,
typeId int,
foreign key (achievementId) references achievementTable(id),
foreign key (typeId) references achievementTypeTable(id)
)

drop table usertable 

drop table userachievem entstable 

drop table posttable 

drop table tagtable

drop table usertotagtable

drop table posttotagtable 

drop table teamtable 

select * from teamtable

insert into userloginparamstable values(0, 'Denis', '1234')

insert into userTable values(0, '@shlepa05', 'Hi! My name is Vitya and i am interested in Kotlin', 0)

insert into posttable values(1, 0, 'TestPost2', 'A')

insert into tagtoposttable values(1, 0)

delete from tagtoposttable where tagid = 1



insert into teamtable values(0, 0)

insert into postextensiontable values(0, 1)

insert into marktable values(1, 0, -1)

select * from posttable

delete from teamtable where userid = 0

insert into tagtable values(0, 'Kotlin')

insert into tagtable values(1, 'Math')

delete from tagtable where id = 1

insert into tagtousertable values(0, 0)

insert into tagtousertable values(1, 0)

delete from tagtousertable where tagid = 1

insert into userachievementstable values(0, 'Winner of the Step into Science competition')

delete from userachievementstable where id=0

insert into postTable values(0, 0, 'TestHeader', 'TestBody', 0, 0)

insert into tagTable values(3, 'Java')

select * from tagtable

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