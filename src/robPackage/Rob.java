package robPackage;

import matematyczne.Wektor;
import plansza.Plansza;

import java.util.Random;

public class Rob {
    private Wektor wspolrzedne;
    protected Wektor kierunek;
    protected Program program;
    private int energia;
    private int iloscDzieci;

    private static int limit_powielania;
    private static int kosztTury;
    private static double pr_powielenia;
    private static double ulamek_energi_rodzica;


    public Rob(Program programStartowy, Wektor pozycja,int energia) {
        this.kierunek = new Wektor(0,1);
        this.wspolrzedne = pozycja;
        this.program = programStartowy.clone();
        this.energia = energia;
    }

    public Wektor kierunek() {
        return this.kierunek;
    }

    public void setWspolrzedne(Wektor w) {
        this.wspolrzedne = w;
    }

    public Wektor wspolrzedne() {
        return this.wspolrzedne;
    }

    public void wypiszProgram() {
        this.program.wypiszProgram();
    }

    public void dodajEnergie(double energia) {
        this.energia += energia;
    }

    public void wykonajProgram(Plansza p) {
        this.energia -= Rob.kosztTury;
        for (Instrukcja instrukcja : this.program.instrukcje) {
            if (this.energia > 0) {
                instrukcja.wykonaj(this,p);
                this.energia--;
            }
            else{
                return;
            }
        }
    }

    public Rob powielaj() {
        Random random = new Random();
        double prawd = random.nextDouble();

        if (prawd <= Rob.pr_powielenia && this.iloscDzieci < Rob.limit_powielania) {
            this.iloscDzieci++;
            double energiaSyna = this.energia * Rob.ulamek_energi_rodzica;
            Rob syn = new Rob(this.program.mutuj(),this.wspolrzedne.clone(),
                    (int)energiaSyna);
            return syn;
        }
        return null;
    }

    public int energia() {
        return this.energia;
    }

    public static void przekazParametry(double pr_powielenia,int kosztTury,
                                        double ulamek_energi_rodzica,
                                        int limit_powielania) {
        Rob.limit_powielania = limit_powielania;
        Rob.pr_powielenia = pr_powielenia;
        Rob.ulamek_energi_rodzica = ulamek_energi_rodzica;
        Rob.kosztTury = kosztTury;
    }

    public int dlProgr() {
        return this.program.dlugoscProgramu();
    }
}
