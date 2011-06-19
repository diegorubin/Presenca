CREATE TABLE Endereco
	(
	  id int(11) not null auto_increment,
		logradouro VARCHAR(254),
		cep VARCHAR(10),
		numero VARCHAR(10),
		bairro VARCHAR(100),
		municipio VARCHAR(100),
		uf VARCHAR(2),
		
		classeObjeto VARCHAR(10),
		idObjeto int,
		
		primary key(id) 
	);
