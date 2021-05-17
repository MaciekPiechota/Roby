package robPackage;

import matematyczne.Wektor;
import plansza.Plansza;
import plansza.Rodzaje;

/**
 *  Klasa Instrukcji Jedz
 */
public class Jedz implements Instrukcja {

    private static Jedz jedz = new Jedz();

    public static Jedz stworz() {
        return Jedz.jedz;
    }

    @Override
    public void wykonaj(Rob rob, Plansza plansza) {
        Wektor w = new Wektor(rob.wspolrzedne().x(),rob.wspolrzedne().y());
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    Wektor v = new Wektor(w.x()+i,w.y()+j);
                    if (plansza.rodzajPola(v) == Rodzaje.Zywieniowy) {
                        w = new Wektor(w.x()+i,w.y()+j);
                        rob.setWspolrzedne(w);
                        plansza.przetworz(w,rob);
                        return;
                    }
                }
            }
        }

    }

    @Override
    public String toString() {
        return "J";
    }

    @Override
    public Instrukcja clone() {
        return Jedz.jedz;
    }
}
