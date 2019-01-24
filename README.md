
# PGR200 Hovedinnlevering 
Laget av: Svamik17 & Marchr17

# Hvordan kjøre programmet

[![Build Status](https://travis-ci.com/Westerdals/pgr200-eksamen-ChrisFFRR.svg?token=BLkMpfb4QjWubHqxD1yb&branch=master)](https://travis-ci.com/Westerdals/pgr200-eksamen-ChrisFFRR)

* OBS innlevering.properties ligger i pgr200-eksamen-ChrisFFRR/database/src/main/resources/innlevering.properties
  
  * åpne terminal og naviger til prosjektmappe
  * kjør mvn clean install i prosjektfolder 
  * kjør mvn package 
  * naviger til http/target mappen
  * skriv inn java -cp no.pgr200.eksamen.http-0.0.1.jar no.kristiania.pgr200.http.Server
  * åpne nytt terminal/cmd vindu
  * naviger til cli/target mappe
  * skriv inn java -cp no.pgr200.eksamen.cli-0.0.1.jar no.kristiania.pgr200.cli.Client
  * OBS!  terminal vinduet du kjører clienten fra bør være fullscreen for å få riktig formattering av foredrag.
  
  * IDE
  * Kjør /pgr200-eksamen-ChrisFFRR/http/src/main/java/no/kristiania/pgr200/http/Server.java
  * Kjør /pgr200-eksamen-ChrisFFRR/cli/src/main/java/no/kristiania/pgr200/cli/Client.java
  
  * Følg meny-valg i client terminal vindu. 
  
 


![picture](doc/eksempelkjøring_1.png)

![picture](doc/eksempelkjøring_2.png)

![picture](doc/eksempelkjøring_3.png)

![picture](doc/eksempelkjøring_4.png)

![picture](doc/Datamodell.png)

# Link til screencast av Ping Pong programmering
1: https://youtu.be/DSzJ1lX7BPY 2: https://youtu.be/BMf13V9nuLU


# Egenvurdering.
Oppgaven gikk ut på at vi inserte/oppdatere/endre informasjon inn i en Database ved hjelp av å bruke post/get/put requests. 
Vi begge synes oppgaven har godt greit og vi synes av i jobber bra sammen. Vi jobbet en del sammen på skolen og når vi ikke         var på skolen jobbet vi som i forrige innlevering via Discord, da delte vi skjermen og diskuterte koden ilag. Denne oppgaven hadde større scope som førte til at vi kunne jobbe på flere klasser samtidig. Som skapte en workflow i prosjektet våres.
  
Det gikk mye tid på å løse småfeil i koden når kodebasen vokste og da fikk vi lærdom i det at det enkleste er ikke alltid det beste siden vi fort kunne ende opp i å ha "tech debt", siden den enkle løsningen tidligere skapte problemer senere prosjektet hvis vi ville utvide funksjonaliteten. Blant problemene vi har slitet litt med er for eksempel det at Clienten våres støtter ikke ÆØÅ, den skjønner ikke disse bokstavene selvom vi har prøvet å få alt til å kjøre via UTF-8 unicode standarden. Derfor velger vi å spesifisere at de som bruker programmet må unngå disse tegnene.

Vi sammen bestemte oss får å bruke våres personlige CLI som vi lagde i forrige innlevering, og utvide den lit for ny funksjonalitet, den er veldig enkel og kunne f.eks bruke SWITCH isteden for IF/ELSE/WHILE men vi hadde litt dårlig tid så det er en del forbedringer vi er klar over som vi kunne ha implementert om tiden strakk til. Blant ideène våres var å faktisk flytte databasen opp til Azure MySQL database, det lå en guide på dette på microsoft sine sider og vi tror dette hadde vært en ganske enkel løsning men tiden som sagt strakk ikke helt til.

Vi synes vi har jobbet kvikt og effektivt. Vi har ett rimelig nivå Enhetstester for databasedelen, kjører på Travis CI, kode uten større skrivefeil eller feil innrykk, vi har også sikret oss mot SQL injections og vi har en god readme som beskriver steg for å demonstrere programmet. Koden våres er enkel å lese, konsis og utrykksfull. Vi føler vi har jobbet veldig hardt på dette så vi synes at vi har ihvertfall oppnådd graden C. Vi skulle gjerne ønske at vi kunne få en B men vi føler kanskje at det er noen mangler som gjør at vi ikke strekker helt til - men dette er opp til de som vurderer innleveringen våres.

Vi vil gjerne gjøre de som vurderer denne innleveringen oppmerksom på at vi har ingen dedikert SHOW funksjonalitet siden vi følte at det ikke var nødvendig siden Klienten viser alt til deg uansett hva du gjør, så det er ingen passelig plass å vise dette. Vi har en "list all talks" som fullfører dette.
  
  
  
- Problemer med ÆØÅ & UTF-8
- Noe funksjonalitet som ikke er implementert.
- Lagde våres egen CLI.
- Planen var å flytte det opp i Azure men hadde ikke tid.
- Jobbet bra, klarte å splitte arbeidet opp mer enn forrige innlevering.
- Jobbet via Discord, og møtte opp på skolen.


## Sjekkliste for innleveringen

- [ ] Kodekvalitet
  - [x] Koden er klonet fra GitHub classrom
  - [X] Produserer `mvn package` en executable jar? (tips: Bruk `maven-shade-plugin`)
  - [X] Bruker koden Java 8 og UTF-8
  - [X] Bygger prosjektet på [https://travis-ci.com](https://travis-ci.com)?
  - [ ] Har du god test-dekning? (tips: Sett opp jacoco-maven-plugin til å kreve at minst 65% av linjene har testdekning)
  - [X] Er koden delt inn i flere Maven `<modules>`?
  - [X] Bruker kommunikasjon mellom klient og server HTTP korrekt?
  - [X] Kobler serveren seg opp mot PostgreSQL ved hjelp av konfigurasjon i fila `innlevering.properties` i *current working directory* med `dataSource.url`, `dataSource.username`, `dataSource.password`?
- [ ] Funksjonalitet
  - [X] add: Legg til et foredrag i databasen med title, description og topic (valgfritt)
  - [X] list: List opp alle foredrag i basen 
  - [ ] show: Vis detaljer for et foredrag
  - [X] update: Endre title, description eller topic for et foredrag
  - [ ] Valgfri tillegg: Kommandoer for å sette opp hvor mange dager og timer konferansen skal vare og hvor mange parallelle spor den skal inneholde.
- [X] Dokumentasjon i form av README.md
  - [X] Navn og Feide-ID på dere de som var på teamet
  - [X] Inkluderer dokumentasjonen hvordan man tester ut funksjonaliteten programmet manuelt? (Inkludert eventuell ekstra funksjonalitet dere har tatt med)
  - [X] Inkluderer dokumentasjonen en evaluering av hvordan man jobbet sammen?
  - [X] Inkluderer dokumentasjonen en screencast av en parprogrammeringsesjon?
  - [ ] Inkluderer dokumentasjonen en evaluering *fra* en annen gruppe og en evaluering *til* en annen gruppe?
  - [X] Inkluderer dokumentasjonen en UML diagram med datamodellen?
  - [X] Inkluderer dokumentasjonen en link til screencast av programmeringsesjon?
  - [X] Inkluderer dokumentasjonen en egenevaluering med hvilken karakter gruppen mener de fortjener?

### Forberedelse

- [X] Finn endelig grupperpartner innen 1. november
- [X] Finn en gruppe for gjensidig evaluering innen 1. november

### Innlevering

- [X] Gi veilederne `hakonschutt` og `mudasar187` tilgang til repository
- [ ] Tag koden med `innlevering` i GitHub
- [ ] Ta en zip-eksport fra GitHub
- [ ] Last opp zip-fil i WiseFlow
- [X] Dersom innlevering #1 eller innlevering #2 ikke ble godkjent *i WiseFlow*, last opp zip-fil med hver av disse innleveringene

## Retningslinjer for vurdering

### Minimum krav for bestått

- Kompilerende kode som er sjekket inn i GitHub
- Tester som gjør noe ikke totalt ufornuftig (eksempel på ufornuftlig `assertTrue(true)` eller `assertEquals(4, 2+2)`)
- Kjørbart program som legger inn data i databasen

### Minimum krav for C

- Skriv og les programmet fra databasen i Java i henhold til deres egen dokumentasjon
- Les og skriv data over socket
- Kode lagret på GitHub, kompilerer og utfører en oppgave uten å krasje

### Minimum krav for B

De fleste av følgende må være oppfyllt:

- Et rimelig nivå med enhetstester som kjører på Travis CI
- Kode uten større skrivefeil, feil innrykk, slukte exceptions eller advarsler fra Eclipse
- Readme som beskriver 4-10 steg for å demonstrere programmet
- God kode: Enkel, konsis, uttrykksfull, velformattert kode uten vestlige feil eller mangler
- Ingen alvorlige feil, SQL injection hull, krasj ved uventet input

### Krav for A

Løsningen må oppfylle alle krav til B og ha 2-3 områder som hever den ytterligere:

- Velskrevet (men ikke nødvendigvis omfattende) dokumentasjon
- At videoen får fram kvalitetene i designet
- Uttrykksfulle enhetstester som er effektive på å fange feil og som kjører på Travis CI
- En velbegrunnet datamodell med 4-8 klasser
- En lettfattelig og utvidbar http-server
- Spennende generisk kode som egentlig er unødvendig kompleks for å løse problemet
- Enkel kode som løser problemet presist og konsist (i konflikt med forrige)

Grupper på 3 må ha flere av disse enn grupper på 2 for å få en A.
