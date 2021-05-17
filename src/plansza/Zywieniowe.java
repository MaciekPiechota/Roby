package plansza;

import robPackage.Rob;

public class Zywieniowe extends Pole {
    private int czasDoRegeneracji;
    private static int ileJedzenia;
    private static int ileRosnie;

    public Zywieniowe() {
        this.czasDoRegeneracji = 0;
        this.rodzaj = Rodzaje.Zywieniowy;
    }

    @Override
    public void przetworz(Rob rob) {
        if (this.czasDoRegeneracji == 0) {
            rob.dodajEnergie(ileJedzenia);
            this.czasDoRegeneracji = Zywieniowe.ileRosnie;
        }
    }

    @Override
    public String toString() {
        return "x";
    }

    public static void setParametry(int ileJedzenia,int ileRosnie) {
        Zywieniowe.ileJedzenia = ileJedzenia;
        Zywieniowe.ileRosnie = ileRosnie;
    }

    public void aktualizuj() {
        if (this.czasDoRegeneracji > 0)
            this.czasDoRegeneracji--;
    }

    @Override
    public int jedzenie() {
        if (this.czasDoRegeneracji == 0)
            return 1;
        return 0;
    }
}