USE PSM;

CREATE TABLE Utenti(
    id INT AUTO_INCREMENT,
    nome VARCHAR(40),
    cognome VARCHAR(40),
    username VARCHAR(40) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    telefono VARCHAR(30),
    utenza VARCHAR(30) NOT NULL,
    UNIQUE(username),
    PRIMARY KEY(id)
);
