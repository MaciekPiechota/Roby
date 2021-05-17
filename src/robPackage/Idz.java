package robPackage;

import plansza.Plansza;
import matematyczne.Wektor;

public class Idz implements Instrukcja {

    private static Idz idz = new Idz();


    public void wykonaj(Rob rob, Plansza plansza) {
        Wektor nowaPozycja = rob.wspolrzedne().dodajWektor(rob.kierunek());
        rob.setWspolrzedne(nowaPozycja);
        plansza.przetworz(nowaPozycja,rob);
    }

    static public Idz stworz() {
        return Idz.idz;
    }

    @Override
    public Idz clone() {
        return idz;
    }

    @Override
    public String toString() {
        return "I";
    }

}