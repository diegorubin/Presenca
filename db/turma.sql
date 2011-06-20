CREATE TABLE `Turma` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `disciplinaId` int(11) NOT NULL,
      `professorId` int(11) NOT NULL,
      `semestre` VARCHAR(1) NOT NULL,
      `ano` VARCHAR(4) NOT NULL,
      PRIMARY KEY (`id`)
);

