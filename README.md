# Bibliotech
Sample web application developed with Java EE technologies

##Descrizione
Bibliotech è una web application sviluppata con tencologie JavaEE che simula il sistema di gestione di una biblioteca, implementata seguendo il pattern MVC.
L'applicazione fornisce diverse funzionalità tra cui:

- Gestione utenti 
	- Registrazione, sistema di login/logout, modifica delle informazioni, diverse tipologie di utenza
- Ricerca libri
	- Per titolo, autore e genere
- Ranking
	- Classifica libri più prenotati
	- Ultimi arrivi
- Scheda riassuntiva per i libri
	- Visualizzazione dettagli quali ISBN, titolo, autore, ecc
- Prenotazione e consegna dei libri
	- Prenotazione di massimo 3 libri per utente, durata di 30 giorni
- Strumenti di amministrazione
	- Per gestire utenti, libri e prenotazioni

##Esecuzione
Per eseguire l'applicazione è necessario un ambiente costituito da un application container e da un database. Durante lo sviluppo è stato utilizzato l'IDE Eclipse, Apache Tomcat 7.x e il database MySQL.
E' necessario modificare il file `/WebContent/WEB-INF/web.xml` e fornire le informazioni relative al database.


##Navigazione
Il routing per pagine che non richiedono dati in ingresso è gestito dal file `web.xml` che mappa le servlet con le corrispondenti URL.

Per pagine che richiedono dati in input invece è stata definita una servlet chiamata `NavigationController`, che si occupa di reperire dati dal Model, e passarli successivamente alla View. 
Analogamente, è stata creata una servlet, `AdminController`, che si occupa dello stesso compito, ma solo per le pagine relative agli strumenti di amministrazione dell'applicazione. 

Questa separazione è stata effettuata per marcare meglio la distinzione tra i due compiti e per avere una migliore manutenibilità.


##Tecnologie JavaEE usate
Servlet, JSP, JDBC, JSTL, Filters, Listeners, Error Pages

###Disclaimer
Applicazione sviluppata come progetto d'esame. Non è intesa per l'utilizzo in produzione.
