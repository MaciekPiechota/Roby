package robPackage;

import matematyczne.Wektor;
import plansza.Plansza;
import plansza.Rodzaje;

public class Wachaj implements Instrukcja {
    private static Wachaj wachaj= new Wachaj();


    public static Wachaj stworz() {
        return Wachaj.wachaj;
    }

    @Override
    public void wykonaj(Rob rob, Plansza plansza) {
        int x = rob.wspolrzedne().x();
        int y = rob.wspolrzedne().y();
        Wektor v = new Wektor(x+1,y);
        if (plansza.rodzajPola(v) == Rodzaje.Zywieniowy) {
            rob.kierunek = new Wektor(1,0);
            return;
        }
        v = new Wektor(x,y+1);
        if (plansza.rodzajPola(v) == Rodzaje.Zywieniowy) {
            rob.kierunek = new Wektor(0,1);
            return;
        }
        v = new Wektor(x-1,y);
        if (plansza.rodzajPola(v) == Rodzaje.Zywieniowy) {
            rob.kierunek = new Wektor(-1,0);
            return;
        }
        v = new Wektor(x,y-1);
        if (plansza.rodzajPola(v) == Rodzaje.Zywieniowy) {
            rob.kierunek = new Wektor(-1,0);
            return;
        }
        return;
    }

    @Override
    public Instrukcja clone() {
        return Wachaj.wachaj;
    }

    @Override
    public String toString() {
        return "W";
    }
}
