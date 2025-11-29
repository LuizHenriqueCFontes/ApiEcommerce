CREATE TABLE usuario(
	id_usuarios VARCHAR(36),
	username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role TINYTEXT,
    
    PRIMARY KEY(id_usuarios)

);

CREATE TABLE produtos(
	id_produtos BIGINT AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL,
	
    PRIMARY KEY(id_produtos)
);