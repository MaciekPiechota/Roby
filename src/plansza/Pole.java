package plansza;

import robPackage.Rob;

public abstract class Pole {
    protected Rodzaje rodzaj;
    public abstract void przetworz(Rob rob);

    public Rodzaje rodzaj() {
        return this.rodzaj;
    }

    public void aktualizuj(){ }

    public int jedzenie(){
        return 0;
    }
}
