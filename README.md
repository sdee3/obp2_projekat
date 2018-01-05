# obp2_projekat
Java Swing aplikacija za naručivanje pice.

Opis:

Potrebno je kreirati klijent-server aplikaciju  čija funkcionalnost treba da simulira proces naručivanja pice. Aplikacija treba da ispuni sledeće uslove koji su dati u  nastavku. 

Klijent deo: 

Na klijentskoj strani kreirati grafički interfejs koji će omogućiti korisniku da kreira svoju narudžbinu. U okviru narudžbine navode se sledeće stavke: Veličina pice(25,32,50), vrsta pice(Margarita,Funghi, Quatro Stagione, Vegeteriana, itd), dodaci (kečap, majonez, origano itd ) moguće je izabrati više dodataka, način plaćanja, adresa na kojoj se vrši isporuka, broj telefona kao i napomenu. Grafički interfejs kreirati tako da se korisniku maksimalno smanji mogućnost pravljenja greške pri kreiranju narudžbine. Nakon unosa podataka korisnik šalje narudžbinu server strani i čeka odgovor koji predstavlja vreme koje je potrebno da se izvrši isporuka. 

Server deo: 

Server deo prima narudžbine od klijenta i generiše odgovor. Vreme koje je potrebno da se isporuči narudžbina generisati pomoću generatora slučajnih vrednosti ( 10 – 50 min ). Server u okviru svog grafičkog interfejsa prikazuje neisporučene i isporučene narudžbine u dva odvojena segmenta. Pored svake neisporučene narudžbine napisati i preostalo vrme do isporuke.
