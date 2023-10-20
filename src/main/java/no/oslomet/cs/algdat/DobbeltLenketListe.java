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

        hode = null; // Setter hode til null for å lage en tom liste
        hale = null; // Setter hale til null for å lage en tom liste
        antall = 0;  // Setter antall til 0 siden listen er tom
        endringer = 0; // Setter endringer til 0
    }

    public DobbeltLenketListe(T[] a) {
        throw new UnsupportedOperationException();

        Objects.requireNonNull(a, "Array a kan ikke være null."); // Sjekker om a er null og kaster en NullPointerException om det er tilfelle
        for (T verdi : a) {
            if (verdi != null) { // Sjekker om verdi er null
                if (hode == null) {
                    hode = hale = new Node<>(verdi); // Setter både hode og hale til en ny node med verdien
                } else {
                    hale = hale.neste = new Node<>(verdi, hale, null); // Oppretter en ny node bak hale og setter hale til den nye noden
                }
                antall++; // Øker antall elementer i listen
            }
        }
        endringer = 0; // Setter endringer til 0
    }

    @Override
    public int antall() {
        throw new UnsupportedOperationException();

        return antall; // Returnerer antall elementer i listen
    }

    @Override
    public boolean tom() {
        throw new UnsupportedOperationException();

        return antall == 0; // Returnerer true hvis antall er lik 0 (listen er tom), ellers false
    }

    // Oppgave 2
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
        if (hode== null){
            return "[]"; //Hvis listen er tom, returneres det en tom liste.
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = hode;
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
        if (hale == null){
            return "[]"; //Hvis liste er tom, returner en tom liste
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = hale;
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
        if (hode == null) {
            hode = nyNode; //Hvis listen er tom, blir den nye noden både første og siste.
            hale = nyNode;
        } else {
            hale.neste = nyNode; //sett den nye noden som "neste" frem til den nåværende siste node
            nyNode.forrige = hale; //setter den nåværende siste noden som "forrige" til den nye noden
            hale = nyNode; //Oppdaterer pekeren til siste node til den nye node.
        }
        endringer++; //øker antallet endringer i listen
        antall++; //øker antall elementer i listen

        return true; //returerer true for å indikere at verdi har blitt lagt til
    }

    // Oppgave 3
    private Node<T> finnNode(int indeks) {
        throw new UnsupportedOperationException();

        indeksKontroll(indeks, false); // Sjekker om indeksen er lovlig

        Node<T> current;
        if (indeks < antall / 2) {
            current = hode; // Starter fra hodet
            for (int i = 0; i < indeks; i++) {
                current = current.neste;
            }
        } else {
            current = hale; // Starter fra halen
            for (int i = antall - 1; i > indeks; i--) {
                current = current.forrige;
            }
        }

        return current;
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();

        indeksKontroll(indeks, false); // Sjekker om indeksen er lovlig

        Node<T> node = finnNode(indeks);
        return node.verdi;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();

        indeksKontroll(indeks, false); // Sjekker om indeksen er lovlig

        Node<T> node = finnNode(indeks);
        T gammelVerdi = node.verdi;
        node.verdi = nyverdi;
        endringer++;
        return gammelVerdi;
    }


    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();

        fraTilKontroll(fra, til, antall); // Sjekker om indeksene er lovlige

        Liste<T> subliste = new DobbeltLenketListe<>();
        Node<T> current = finnNode(fra);

        for (int i = fra; i < til; i++) {
            subliste.leggInn(current.verdi);
            current = current.neste;
        }

        return subliste;
    }

    // Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();

        if (verdi == null) {
            return -1;
        }

        Node<T> currentNode = hode;
        for (int i = 0; i < antall; i++) {
            if (verdi.equals(currentNode.verdi)) {
                return i;
            }
            currentNode = currentNode.neste;
        }

        return -1;
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
        return indeksTil(verdi) != -1;
    }

    // Indre Node-klasse for å representere elementer i listen
    private static final class Node<T> {
        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();

        // Sjekker om indeksen er lovlig for innsetting
        indeksKontroll(indeks, true);

        // Sjekker om verdi er null
        Objects.requireNonNull(verdi, "Det er ikke tillatt å legge til null-verdier i listen.");

        if (indeks == 0) {
            // Legger til i starten av listen
            hode = new Node<>(verdi, null, hode);
            if (hode.neste != null) {
                hode.neste.forrige = hode;
            }
            if (antall == 0) {
                hale = hode;
            }
        } else if (indeks == antall) {
            // Legger til i slutten av listen
            hale = new Node<>(verdi, hale, null);
            if (hale.forrige != null) {
                hale.forrige.neste = hale;
            }
            if (antall == 0) {
                hode = hale;
            }
        } else {
            // Legger til i midten av listen
            Node<T> forrigeNode = finnNode(indeks - 1);
            Node<T> nyNode = new Node<>(verdi, forrigeNode, forrigeNode.neste);
            forrigeNode.neste = nyNode;
            nyNode.neste.forrige = nyNode;
        }

        antall++;
        endringer++;
    }

    // Oppgave 6
    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();

        indeksKontroll(indeks, false); // Sjekker om indeksen er lovlig

        Node<T> node = finnNode(indeks);

        if (antall == 1) {
            // Hvis det er kun ett element i listen
            hode = hale = null;
        } else if (indeks == 0) {
            // Hvis første element fjernes
            hode = hode.neste;
            hode.forrige = null;
        } else if (indeks == antall - 1) {
            // Hvis siste element fjernes
            hale = hale.forrige;
            hale.neste = null;
        } else {
            // Hvis et element i midten fjernes
            node.forrige.neste = node.neste;
            node.neste.forrige = node.forrige;
        }

        T verdi = node.verdi;
        node.forrige = node.neste = null; // Bryter pekerne til den fjernede noden
        antall--;
        endringer++;
        return verdi;
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();

        if (verdi == null) return false;

        for (Node<T> node = hode; node != null; node = node.neste) {
            if (verdi.equals(node.verdi)) {
                fjern(indeks(node.verdi)); // Bruker indeks-metoden for å finne indeksen
                return true;
            }
        }

        return false;
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

            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Listen er endret utenfor iteratoren.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Ingen flere elementer i listen.");
            }

            T verdi = denne.verdi;
            denne = denne.neste;
            kanFjerne = true;
            return verdi;
        }
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
