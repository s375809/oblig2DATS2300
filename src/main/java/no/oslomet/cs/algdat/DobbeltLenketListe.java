package no.oslomet.cs.algdat;

import java.util.Comparator;
import java.util.Iterator;

public class DobbeltLenketListe<T> implements Liste<T> {
    // Innebygd (Trenger ikke endres)

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {

        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi; this.forrige = forrige; this.neste = neste;
        }
        private Node(T verdi) {this(verdi, null, null);}
    }

    private Node<T> hode;
    private Node<T> hale;
    private int antall;
    private int endringer;

    public void fraTilKontroll(int fra, int til) {
        if (fra < 0) throw new IndexOutOfBoundsException("fra("+fra+") er negativ.");
        if (til > antall) throw new IndexOutOfBoundsException("til("+til+") er større enn antall("+antall+")");
        if (fra > til) throw new IllegalArgumentException("fra("+fra+") er større enn til("+til+") - Ulovlig intervall.");
    }

    // Oppgave 0
    public static int gruppeMedlemmer() {
        return 2; // Returner hvor mange som er i gruppa deres
    }

    // Oppgave 1
    public DobbeltLenketListe() {
        throw new UnsupportedOperationException();

    }

    public DobbeltLenketListe(T[] a) {
        throw new UnsupportedOperationException();

    }

    @Override
    public int antall() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tom() {
        throw new UnsupportedOperationException();
    }

    // Oppgave 2
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
        if (første== null){
            return "[]"; //Hvis listen er tom, returneres det en tom liste.
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = første;
        while (current.neste != null){
            sb.append(current.verdi); //Legger til verdi i Strengen
            sb.append(", "); //Tilsetter komma og mellomrom etter en verdi.
            current = current.neste; // Går til neste node
        }
        sb.append(current.verdi); // Legger til siste verdi uten komma og mellomrom
        sb.append("]"); //Legger til avsluttende klammeparantes

        return sb.toString(); //Konverterer StringBuilder til en String og deretter returnerer
    }

    public String omvendtString() { //Metode for å representerer listen som en omvendt streng
        throw new UnsupportedOperationException();
        if (siste == null){
            return "[]"; //Hvis liste er tom, returner en tom liste
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = siste;
        while (current.forrige != null){
            sb.append(current.verdi); //Legger til verdien i strengen
            sb.append(", "); //Legger til komma og mellomrom etter en verdi.
            current = current.forrige; //Går til forrige node
        }
        sb.append(current.verdi); //Legger til siste verdi, uten komma og mellomrom
        sb.append("]"); //Legger til avsluttende klammeparantes

        return sb.toString();
    }

    @Override
    public boolean leggInn(T verdi) { //Metode som legger til en verdi i listen
        if (verdi == null) {
            throw new UnsupportedOperationException("Kan ikke legge til null");
        }

        Node<T> nyNode = new Node<>(verdi); //Lager ny node med den angitte verdien
        if (første == null) {
            første = nyNode; //Hvis listen er tom, blir den nye noden både første og siste.
            siste = nyNode;
        } else {
            siste.neste = nyNode; //sett den nye noden som "neste" frem til den nåværende siste node
            nyNode.forrige = siste; //setter den nåværende siste noden som "forrige" til den nye noden
            siste = nyNode; //Oppdaterer pekeren til siste node til den nye node.
        }
        endringer++; //øker antallet endringer i listen
        antall++; //øker antall elementer i listen

        return true; //returerer true for å indikere at verdi har blitt lagt til
    }

    // Oppgave 3
    private Node<T> finnNode(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }


    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    // Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    // Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    // Oppgave 6
    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    // Oppgave 7
    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    // Oppgave 8
    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean kanFjerne;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;                   // Starter på første i lista
            kanFjerne = false;              // Settes true når next() kalles
            iteratorendringer = endringer;  // Teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        // Oppgave 9:
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
