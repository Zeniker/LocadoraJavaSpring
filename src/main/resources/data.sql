INSERT INTO usuario(id, nome, email, senha) VALUES(1, 'Guilherme Lopes', 'gui.lls@hotmail.com', '$2a$10$cVTR9CGfht.Z5MBsg0zrOeaPpPjKNRHlikPphyuPY.mj9m.KxcBpC');

INSERT INTO diretor VALUES (1, 'James Cameron');
INSERT INTO diretor VALUES (2, 'John Musker');
INSERT INTO diretor VALUES (3, 'Guillermo del Toro');
INSERT INTO diretor VALUES (4, 'Gabriele Muccino');

INSERT INTO filme VALUES(1, 'Moana', 2);
INSERT INTO filme VALUES(2, 'Avatar', 1);
INSERT INTO filme VALUES(3, 'Exterminador do Futuro', 1);
INSERT INTO filme VALUES(4, 'Círculo de Fogo', 3);
INSERT INTO filme VALUES(5, 'A procura da felicidade', 4);

INSERT INTO copia VALUES(1, 1);
INSERT INTO copia VALUES(2, 1);
INSERT INTO copia VALUES(3, 2);
INSERT INTO copia VALUES(4, 3);
INSERT INTO copia VALUES(5, 4);
INSERT INTO copia VALUES(6, 5);
INSERT INTO copia VALUES(7, 5);

INSERT INTO locacao(id, copia_id, locador_id, data_locacao, data_devolucao) VALUES(1, 7, 1, now(), null);