CREATE TABLE `Disciplina` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `nome` varchar(254) NOT NULL,
      PRIMARY KEY (`id`)
);

INSERT INTO Disciplina (nome) VALUES ('Matematica');
INSERT INTO Disciplina (nome) VALUES ('Fisica');
INSERT INTO Disciplina (nome) VALUES ('Geografia');
