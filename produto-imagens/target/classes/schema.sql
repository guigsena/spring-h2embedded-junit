create table produto
  (id int not null, nome varchar(50), descricao varchar(50), idProdutoPai int);
  
create table imagem
  (id int not null, tipo varchar(15), idProduto int);

--INSERE PAIS
insert into produto(id, nome, descricao) values (1, 'prod 1', 'descricao prod 1');
insert into produto(id, nome, descricao) values (2, 'prod 2', 'descricao prod 2');
insert into produto(id, nome, descricao) values (3, 'prod 3', 'descricao prod 3');
--INSERE FILHOS
insert into produto(id, nome, descricao, idProdutoPai) values (4, 'prod 4', 'descricao prod 4', 2);
insert into produto(id, nome, descricao, idProdutoPai) values (5, 'prod 5', 'descricao prod 5', 2);
insert into produto(id, nome, descricao, idProdutoPai) values (6, 'prod 6', 'descricao prod 6', 2);
insert into produto(id, nome, descricao, idProdutoPai) values (7, 'prod 7', 'descricao prod 7', 3);
insert into produto(id, nome, descricao, idProdutoPai) values (8, 'prod 8', 'descricao prod 8', 3);
insert into produto(id, nome, descricao, idProdutoPai) values (9, 'prod 9', 'descricao prod 9', 3);
insert into produto(id, nome, descricao, idProdutoPai) values (10, 'prod 10', 'descricao prod 10', 3);
--INSERE IMAGENS
insert into imagem(id, tipo, idProduto) values (1, 'tipo1 prod 1', 1);
insert into imagem(id, tipo, idProduto) values (2, 'tipo2 prod 1', 1);
insert into imagem(id, tipo, idProduto) values (3, 'tipo1 prod 2', 2);
insert into imagem(id, tipo, idProduto) values (4, 'tipo1 prod 3', 3);
insert into imagem(id, tipo, idProduto) values (5, 'tipo1 prod 4', 4);
insert into imagem(id, tipo, idProduto) values (6, 'tipo1 prod 5', 5);
insert into imagem(id, tipo, idProduto) values (7, 'tipo1 prod 6', 6);
insert into imagem(id, tipo, idProduto) values (8, 'tipo1 prod 7', 7);
insert into imagem(id, tipo, idProduto) values (9, 'tipo1 prod 8', 8);
insert into imagem(id, tipo, idProduto) values (10, 'tipo1 prod 9', 9);
insert into imagem(id, tipo, idProduto) values (11, 'tipo1 prod 10', 10);
insert into imagem(id, tipo, idProduto) values (12, 'tipo2 prod 10', 10);
