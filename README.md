[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/k3TSQYDa)
# Obligatorisk Oppgave 2 i DATS2300 - Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i DATS2300 - Algoritmer og datastrukturer. Den er innlevert av følgende studenter:
* s375809@oslomet.no
* s375098@oslomet.no

## Arbeidsfordeling
I oppgaven har vi hatt følgende arbeidsfordeling:
* s375809 har hatt ansvar oppgave 0, 2, 4 og 6.
* s654321 har hatt ansvar oppgave 1, 3, 5.
* Vi har samarbeidet om oppgave 7 og 8.

## Oppgavebeskrivelser

# Oppgave 1
- vi lager konstruktøren dobbeltlenkeliste. 
- Denne konstruktøren initialiserer hode og hale til null, og setter antall og endringer til 0. 
- Dette resulterer i en tom liste når objektet opprettes. 
- vi lageer en kontruktør til. 
- Denne konstruktøren tar imot et array a og lager en liste ut av elementene i a. 
- Den sjekker først om a er null, og kaster en NullPointerException hvis det er tilfelle. 
- Deretter går den gjennom hvert element i a. 
- Hvis elementet ikke er null, legger den det til i listen ved å oppdatere hale og antall. 
- Til slutt settes endringer til 0
# Oppgave 2
- handler om å implementere toString()-metoden for å lage en tekstlig representasjon av listen. 
- Denne metoden starter med å sjekke om listen er tom (hvis hode er null), og i så fall returnerer den en tom liste. 
- Deretter oppretter den en StringBuilder for å bygge opp den tekstlige representasjonen. 
- Den går gjennom nodene og legger til verdiene i strengen, og inkluderer kommaer og mellomrom mellom verdiene. 
- Til slutt returneres den ferdige strengen.

# Oppgave 3
- Denne metoden starter med å sjekke om listen er tom (hvis hode er null), og i så fall returnerer den en tom liste. 
- Deretter oppretter den en StringBuilder for å bygge opp den tekstlige representasjonen. 
- Den går gjennom nodene og legger til verdiene i strengen, og inkluderer kommaer og mellomrom mellom verdiene. 
- Til slutt returneres den ferdige strengen.

# Oppgave 4
Metoden starter med å sjekke om verdi er null. I så fall returnerer den -1, siden null-verdier ikke er tillatt i listen.
Deretter går den gjennom nodene i listen ved hjelp av en løkke.
For hver node sammenlignes verdi med verdien i noden. Hvis de er like, returneres indeksen i.
Hvis verdi ikke finnes i listen, returneres -1.

# Oppgave 5
Metoden starter med å sjekke om indeksen er lovlig ved hjelp av indeksKontroll.
Deretter sjekker den om verdi er null, og kaster en NullPointerException hvis det er tilfelle.
Metoden har tre tilfeller avhengig av hvor verdi skal legges til:
Hvis indeks er 0, legges verdien til i starten av listen.
Hvis indeks er lik antall elementer, legges verdien til i slutten av listen.
Ellers legges verdien til i midten av listen ved å oppdatere pekere til de omliggende nodene.
Til slutt økes antall og endringer.

# Oppgave 6
Metoden starter med å sjekke om indeksen er lovlig ved hjelp av indeksKontroll.
Den bruker hjelpemetoden finnNode for å finne noden som skal fjernes.
Metoden har fire tilfeller avhengig av hvor noden som skal fjernes befinner seg:
Hvis listen kun har ett element, blir både hode og hale satt til null.
Hvis første element fjernes, blir hode oppdatert til å peke på neste node og forrige-pekeren til den første noden blir satt til null.
Hvis siste element fjernes, blir hale oppdatert til å peke på forrige node og neste-pekeren til den siste noden blir satt til null.
Hvis et element i midten fjernes, blir pekerne rundt den fjernede noden oppdatert for å hoppe over den.
Til slutt brytes pekerne til den fjernede noden, antall reduseres og endringer økes. Metoden returnerer verdien som ble fjernet.

# Oppgave 7
Metoden starter med å sjekke om verdi er null. I så fall returnerer den false, siden null-verdier ikke er tillatt i listen.
Deretter går den gjennom nodene i listen ved hjelp av en løkke.
For hver node sammenlignes verdi med verdien i noden. Hvis de er like, bruker metoden fjern(int indeks) for å fjerne elementet på indeks i og returnerer true.
Hvis verdi ikke finnes i listen, returneres false.
Denne metoden fjerner den første forekomsten av verdi i listen og returnerer true hvis fjerningen var vellykket, ellers returneres false.

# Oppgave 8
DobbeltLenketListeIterator er en indre klasse som implementerer Iterator<T>.
Konstruktøren DobbeltLenketListeIterator starter iterasjonen ved å sette denne til å peke på hode, og initialiserer kanFjerne til false og iteratorendringer til endringer for å kunne detektere endringer i listen.
hasNext() sjekker om det finnes en neste node ved å se om denne er null.
next() returnerer verdien til den nåværende noden og flytter denne til neste node. Den kaster unntak hvis det ikke finnes flere elementer eller hvis listen har blitt endret under iterasjonen. Den setter også kanFjerne til true for å tillate fjerning.
Denne iterator-klasse tillater å iterere gjennom elementene i DobbeltLenketListe og gir funksjonalitet for å sjekke om det er flere elementer (hasNext()) og hente neste element (next()). Den kaster også unntak ved ugyldig iterasjons- og endringstilstand.