CREATE TABLE `usuario` (
  `codigo` bigint(20) NOT NULL,
  `login` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `senha` varchar(150) NOT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `permissao` (
  `codigo` bigint(20) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `usuario_permissao` (
  `login_usuario` varchar(50) NOT NULL,
  `codigo_permissao` bigint(20) NOT NULL,
  PRIMARY KEY (`login_usuario`,`codigo_permissao`),
  KEY `codigo_permissao` (`codigo_permissao`),
  CONSTRAINT `usuario_permissao_ibfk_1` FOREIGN KEY (`login_usuario`) REFERENCES `usuario` (`login`),
  CONSTRAINT `usuario_permissao_ibfk_2` FOREIGN KEY (`codigo_permissao`) REFERENCES `permissao` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(200) NOT NULL,
  `author` varchar(50) DEFAULT NULL,
  `path` varchar(500) DEFAULT NULL,
  `description` text,
  `content` mediumblob,
  `extension` varchar(10) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `login_usuario` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `login_usuario` (`login_usuario`),
  CONSTRAINT `files_ibfk_1` FOREIGN KEY (`login_usuario`) REFERENCES `usuario` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


INSERT INTO usuario (codigo, login, email, senha) values (1, 'admin', 'admin@estore.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario (codigo, login, email, senha) values (2, 'maria', 'maria@estore.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permissao (codigo, descricao) values (1, 'ALL');

INSERT INTO usuario_permissao (login_usuario, codigo_permissao) values ('admin', 1);

INSERT INTO usuario_permissao (login_usuario, codigo_permissao) values ('maria', 1);