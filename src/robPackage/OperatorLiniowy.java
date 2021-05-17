package robPackage;

import plansza.Plansza;
import matematyczne.Macierz;

public abstract class OperatorLiniowy implements Instrukcja {
    private Macierz macierz;

    protected OperatorLiniowy(int a,int b,int c,int d) {
        this.macierz = new Macierz(a, b, c, d);
    }

    @Override
    public void wykonaj(Rob rob, Plansza plansza) {
        macierz.razyWektor(rob.kierunek());
    }

    public abstract OperatorLiniowy clone();
}
