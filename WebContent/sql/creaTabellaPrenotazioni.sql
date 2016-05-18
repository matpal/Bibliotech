USE PSM;

CREATE TABLE Prenotazioni(
    id INT AUTO_INCREMENT,
    idUtente INT NOT NULL,
    idLibro INT NOT NULL,
    titoloLibro VARCHAR(100) NOT NULL,
    inizioPrenotazione DATE NOT NULL,
    finePrenotazione DATE NOT NULL,
    PRIMARY KEY(id),
    UNIQUE KEY unique_utente_libro (idUtente,idLibro)
);
