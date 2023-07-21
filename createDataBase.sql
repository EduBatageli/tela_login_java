create database login;
use login;
create table cadastro_usuario(usuario varchar(150), senha varchar(50));

insert into cadastro_usuario(usuario, senha) values ("teste", "1234546");
select * from cadastro_usuario;


