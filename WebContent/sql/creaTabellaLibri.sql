USE PSM;

CREATE TABLE Libri(
    id INT AUTO_INCREMENT,
    isbn VARCHAR(40) NOT NULL,
    titolo VARCHAR(100) NOT NULL,
    autore VARCHAR(100),
    editore VARCHAR(100),
    genere VARCHAR(50),
    quantita INT NOT NULL DEFAULT 0,
    contatorePrenotazioni INT DEFAULT 0,
    UNIQUE(isbn),
    PRIMARY KEY(id)
);
