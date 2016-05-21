# Bibliotech
Sample web application developed with Java EE technologies

##Descrizione
Bibliotech è una web application sviluppata con tencologie JavaEE che simula il sistema di gestione di una biblioteca, implementata seguendo il pattern MVC.


##MVC
L'applicazione segue il pattern MVC (Model-View-Controller) attraverso l'uso di Servlet per la parte Controller, classi Java che rappresentano i Model e file JSP per la View.

![Model View Controller diagram](https://github.com/matpal/Bibliotech/blob/master/MVC.png)

####Flusso di esecuzione
Quando l'utente invia una richiesta all'applicazione, questa viene processata da una Servlet. A seconda della richiesta presentata, la Servlet sceglie quale Model interrogare e fornire i risultati così ottenuti alla corrispondente JSP. Infine la JSP, contenente le informazioni passate dalla Servlet, viene inviata all'utente.

##Funzionalità
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


##Tecnologie JavaEE usate
Servlet, JSP, JDBC, JSTL, Filters, Listeners, Error Pages
