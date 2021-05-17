package plansza;

import robPackage.Rob;

public class Puste extends Pole {
    private static Puste puste = new Puste();

    protected Puste(){
        this.rodzaj = Rodzaje.Pusty;
    }

    static public Puste stworz() {return puste;}

    @Override
    public void przetworz(Rob rob) {
        return;
    }

    @Override
    public String toString() {
       return "_";
    }

}

