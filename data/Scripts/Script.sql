create table postsTable(
id int,
login text,
password text
)

insert into userTable values(0, 'Vitya', '1234')

insert into userTable values(1, 'Denis', '4321')

delete from userTable where login ='Misha lox' or login='Vanya' 

select * from userTable

select * from userTable where id = (select max(id) from userTable)