insert ignore into usuario(id,usuario,senha) values(1,'admin','admin');
insert ignore into usuario(id,usuario,senha) values(2,'usuario','usuario');
insert ignore into PAPEL(id,PAPEL) values(1,'admin');
insert ignore into PAPEL(id,PAPEL) values(2,'user');
insert ignore into USUARIO_LISTA_PAPEIS (usuario_id,lista_papeis_id) values(1,1);
insert ignore into USUARIO_LISTA_PAPEIS (usuario_id,lista_papeis_id) values(2,2);