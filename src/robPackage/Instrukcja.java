package robPackage;

import plansza.Plansza;

public abstract interface Instrukcja {

    public void wykonaj(Rob rob, Plansza plansza);

    public Instrukcja clone();

}
