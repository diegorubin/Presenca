CREATE TABLE `AlunoTurma` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `idAluno` int(11) NOT NULL,
      `idTurma` int(11) NOT NULL,
      PRIMARY KEY (`id`)
);

INSERT INTO Disciplina (nome) VALUES ('Matematica');
INSERT INTO Disciplina (nome) VALUES ('Fisica');
INSERT INTO Disciplina (nome) VALUES ('Geografia');
