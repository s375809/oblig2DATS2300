package no.oslomet.cs.algdat;

import java.util.*;

public class DobbeltLenketListe<T> implements Liste<T> {
// Innebygd (Trenger ikke endres)

    /**
     * Node class
     *
     * @param <T>
     */

    private static final class Node<T> {

        private T verdi;                    //Nodens verdi
        private Node<T> forrige, neste;     //pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }
        private Node(T verdi) {
            this(verdi, null, null);}
    }


    public void fraTilKontroll(int fra, int til) {

    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    // Oppgave 0
    public static int gruppeMedlemmer() {
        return 2; // Returner hvor mange som er i gruppa deres
    }

    // Oppgave 1
    public DobbeltLenketListe() {
        hode = null;
        hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {
        // Kaster unntak for null tabell
        if(a == null){ Objects.requireNonNull(a, "Tabellen er tom");
        }
        //oppretter en dobbelt lenket liste basert på en array a
        if (a.length>0){
            int i = 0;
            for (; i < a.length; i++){
                if(a[i] != null){
                    antall++;
                    hode=new Node<>(a[i]);
                    break;
                    //finner den første ikke null verdien i a og oppretter en node som blir hode
                    //øker antallet
                }
            }
            hale = hode;
            //legger til de andre ikke null verdiene i a som noder etter hale
            if (hode != null){
                i++;
                for (; i < a.length; i++){
                    if(a[i] != null){
                        antall++;
                        hale.neste= new Node<>(a[i], hale, null);
                        hale=hale.neste;
                    }
                }
            }
        }
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        if (hode==null){
            return true;
        }
        else {
            return false;
        }
    }

    // Oppgave 2
    @Override
    public String toString() {
        //sjekker først om listen er tom eller ikke hvis den er så returner den bare tomme klamme paranteser
        if (antall==0){
            return "[]";
        }

        StringBuilder stringb= new StringBuilder("["); //lager en string builder som starter med klamme parantes
        Node<T> now= hode; //starter på hode

        //hvis noden ikke er null så blir den lagt til å string builder og en komma og mellom rom blir også lagt til
        while (now != null) {
            stringb.append(now.verdi);
            if (now.neste != null){
                stringb.append(", ");
            }
            now=now.neste;
        }
        stringb.append("]");
        return stringb.toString();
        // Returnerer en tekststreng som representerer listen med verdier omgitt av klammeparenteser.
    }

    public String omvendtString() { //Metode for å representerer listen som en omvendt streng
        StringBuilder stringb = new StringBuilder("["); //lager en string builder som starter med klamme parantes
        Node<T> now = hale; // starter på halen fordi den her skal gå omvendt vei

        //hvis noden ikke er null så blir den lagt til å string builder og en komma og mellom rom blir også lagt til
        while (now != null){
            stringb.append(now.verdi);
            if (now.forrige != null){
                stringb.append(", ");
            }
            now=now.forrige;
        }
        stringb.append("]");
        return stringb.toString();
        // Returnerer en tekststreng som representerer listen med verdier omgitt av klammeparenteser.
    }

    @Override
    public boolean leggInn(T verdi) { //Metode som legger til en verdi i listen
        //sjekker om verdien ikke er null
        Objects.requireNonNull(verdi, "Det er ikke tilatt å legge inn Null verdier");

        Node<T> newnode= new Node<>(verdi);

        //legger til nye verdier i listen samtidig som jeg sjekker om listen er tom
        if (antall==0){
            hode=newnode;
            hale=newnode;
        } else {
            hale.neste = newnode;
            newnode.forrige = hale;
            hale = newnode;
        }
        antall++;
        endringer++;
        return true;
        //øker antallet og endringer

    }

    // Oppgave 3
    private Node<T> finnNode(int indeks) {
        //sjekker indeksen
        indeksKontroll(indeks, false);

        //oppretter en nåvørende node
        Node<T> now;

        //sjekker hvis indeksen er nærmere hode, dersom den er det går den fra hoden oppover mot indeksen
        if (indeks < antall /2) {
            now = hode;
            for (int i = 0; i < indeks; i++){
                now = now.neste;
            }
            return now;
        }
        //sjekker hvis indeksen er nærmere halen, dersom den er det så går den fra halen nedover mot indeksen
        else {
            now = hale;
            for (int i = antall - 1; i > indeks; i--){
                now = now.forrige;
            }
            return now;
        }

    }

    @Override
    public T hent(int indeks) {
        //finner "en node" ved hjelp av "finnNode" metoden, og returnerer verdien til noden
        Node<T> now = finnNode(indeks);
        return now.verdi;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        //sjekker om verdien ikke er null
        Objects.requireNonNull(nyverdi, "ny verdi må være noe");

        //finner noden med den gitte indeksen
        Node<T> node = finnNode(indeks);
        //lagrer  gamel verdien
        T gammelVerdi = node.verdi;
        //oppdaterer verdien til noden
        node.verdi = nyverdi;
        //øker "endringer" og returnerer gammel verdie som ble erstattet
        endringer++;
        return gammelVerdi;
    }


    public Liste<T> subliste(int fra, int til) {
        //sjekker om indeksene er gyldige
        fraTilKontroll(fra, til);

        //lager en ny liste
        Liste<T> liste = new DobbeltLenketListe<>();
        int lengde = til - fra;

        //hvis legden på listen er mindre enn 1 så skal lista returneres
        if (lengde < 1) {
            return liste;
        }

        //finner noden som er på starten av lista
        Node<T> now = finnNode(fra);

        //legger til verdiene fra sublisten inn i den nye listen også returnerer listen
        while (lengde > 0) {
            liste.leggInn(now.verdi);
            now = now.neste;
            lengde--;
        }
        return liste;
    }

    // Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) {
            return -1;
        }

        Node<T> node=hode;
        for (int i = 0; i < antall; i++, node=node.neste){
            if (node.verdi.equals(verdi)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean inneholder(T verdi) {
        //finner indeksTil metoden for å finne indeksen til den gitte verdien og sjekker om det ikke blir -1
        //fordi hvis -1 blir returnert så betyr det at den ikke finnes i listen
        return indeksTil(verdi) != -1;
    }


    // Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        //sjekker om verdien ikke er null
        Objects.requireNonNull(verdi, "Verdien kan ikke være null");

        //Sjekker lengden på indeks
        if (indeks > antall){
            throw new IndexOutOfBoundsException("Indeksen er større enn antall noder");
        } else if (indeks < 0) throw new IndexOutOfBoundsException("Indeksen kan ikke være negativ");

        //håndterer tilfeller om indeksen er tom eller om den er på slutten eller starten av listen
        if (antall == 0 && indeks == 0) {
            hode = hale = new Node<T>(verdi, null, null);
        } else if (indeks == 0) {
            hode = new Node<T>(verdi, null, hode);
            hode.neste.forrige = hode;
        }
        else if (indeks == antall) {
            hale = new Node<T>(verdi, hale, null);
            hale.forrige.neste = hale;
        }
        else {
            Node<T> node = hode;

            for (int i = 0; i < indeks; i++) node = node.neste;{
                node = new Node<T>(verdi, node.forrige, node);
            }
            //setter inn den nye verdien i listen
            node.neste.forrige = node.forrige.neste = node;
        }

        endringer++;
        antall++;

    }

    // Oppgave 6
    @Override
    public T fjern(int indeks) {
        //sjekker om ideksen er gyldig
        indeksKontroll(indeks, false);

        //lager en hjelpemetode for now node
        Node<T> now = hode;
        T verdien;

        //Første fjernes
        if (indeks == 0) {
            verdien = now.verdi;

            if (now.neste != null) {
                hode = now.neste;
                hode.forrige = null;
            } else {
                hode = null;
                hale = null;
            }
        }

        //Siste fjernes
        else if (indeks == antall - 1) {
            now = hale;
            verdien = hale.verdi;

            hale = now.forrige;
            hale.neste = null;
        }

        //Mellom fjernes
        else {
            for (int i = 0; i < indeks; i++) {
                now = now.neste;
            }
            verdien = now.verdi;

            now.forrige.neste = now.neste;  //Node til venstre for now peker på node til høyre
            now.neste.forrige = now.forrige;//Node til høyre for now peker på node til venstre
        }


        endringer++;
        antall--;
        return verdien;
    }

    @Override
    public boolean fjern(T verdi) {
        //om verdien er null så returnerer false
        if (verdi == null) {
            return false;
        }

        //lager en hjelpemetode for now node
        Node<T> now = hode;

        //Første fjernes
        if (verdi.equals(now.verdi)) {
            if (now.neste != null) {
                hode = now.neste;
                hode.forrige = null;
            } else {
                hode = null;
                hale = null;
            }
            antall--;
            endringer++;
            return true;
        }

        //Siste fjernes
        now = hale;
        if (verdi.equals(now.verdi)) {
            hale = now.forrige;
            hale.neste = null;
            endringer++;
            antall--;
            return true;
        }

        //Mellom fjernes
        now = hode.neste;
        for (; now != null; now = now.neste) {
            if (verdi.equals(now.verdi)) {
                now.forrige.neste = now.neste;  //Node til venstre for now peker på node til høyre
                now.neste.forrige = now.forrige;//Node til høyre for now peker på node til venstre
                endringer++;
                antall--;
                return true;
            }
        }
        return false;
    }

    // Oppgave 7
    @Override
    public void nullstill() {
        for (Node<T> t = hode; t != null; t = t.neste) {
            t.verdi = null;
            t.forrige = t.neste = null;
        }
        hode = hale = null;
        antall = 0;
        endringer++;
        for (Node<T> t = hode; t != null; t = t.neste) {
            fjern(0);
        }
    }
    // Oppgave 8
    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean kanFjerne;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;                   // Starter på første i listen
            kanFjerne = false;              // Setter true når next() kalles
            iteratorendringer = endringer;  // Teller antall endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            indeksKontroll(indeks, false);
            denne = hode;
            kanFjerne = false;
            iteratorendringer = endringer;

            for (int i = 0; i < indeks; i++) {
                next();
            }
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            //Kontrollerer om det finnes flere elementer å iterere over
            if (!hasNext()) {
                throw new NoSuchElementException("Ingen elementer i listen!");
            }

            //Kontrollerer om listen endrer på seg
            if (endringer != iteratorendringer) {
                throw new ConcurrentModificationException("Endringer er ikke lov mens itterator er aktiv");
            }


            T tempverdi = denne.verdi;
            denne = denne.neste;

            //Tilsetter til true slik at den kan fjerne elemter
            kanFjerne = true;

            return tempverdi;
        }

        // Oppgave 9
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    // Oppgave 10
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }
}