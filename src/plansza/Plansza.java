package plansza;

import matematyczne.Wektor;
import robPackage.Rob;

import java.util.ArrayList;

public class Plansza {
    public ArrayList<ArrayList<Pole>> p;
    private final ArrayList<Wektor> polaJedzenia;

    public Plansza(ArrayList<String> wejscie) {
        Wektor.ustaw(wejscie.get(0).length(),wejscie.size());

        this.p = new ArrayList<>(wejscie.size());
        this.polaJedzenia = new ArrayList<>();

        for (int y = 0; y < Wektor.osY(); y++) {
            this.p.add(new ArrayList<>());
            for (int x = 0; x < Wektor.osX(); x++) {
                switch (wejscie.get(y).charAt(x)) {
                    case 'x':
                        this.p.get(y).add(x, new Zywieniowe());
                        this.polaJedzenia.add(new Wektor(x, y));
                        break;
                case ' ':
                    this.p.get(y).add(x, Puste.stworz());
                    break;
                }
            }
        }
    }

    public int ileJedzenia() {
        int licznik = 0;
        for (Wektor v: this.polaJedzenia) {
            licznik += this.p.get(v.y()).get(v.x()).jedzenie();
        }
        return licznik;
    }

    public Rodzaje rodzajPola(Wektor v) {
        return this.p.get(v.y()).get(v.x()).rodzaj();
    }

    public void przetworz(Wektor wspolrzedne, Rob rob) {
        p.get(wspolrzedne.y()).get(wspolrzedne.x()).przetworz(rob);
    }

    public void rosnijJedzenie() {
        for (Wektor v : this.polaJedzenia) {
            this.p.get(v.y()).get(v.x()).aktualizuj();
        }
    }

    public void wypiszPlansze() {
        for (int y = 0; y < Wektor.osY(); y++) {
            for (int x = 0; x < Wektor.osX(); x++) {
                System.out.print(p.get(y).get(x).toString());
            }
            System.out.println();
        }
    }
}
