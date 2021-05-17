package zad1;

import matematyczne.Wektor;
import plansza.Plansza;
import plansza.Zywieniowe;
import robPackage.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Swiat {
    private final Plansza p;
    private ArrayList<Rob> roby;

    //Parametry
    private final HashMap<String,Double> parametryProcentowe;
    private final HashMap<String,Integer> parametryCalkowite;
    private final int iloscParametrow = 17;
    private ArrayList<String> spisPoprInstr;
    private boolean poprawneParametry = true;
    private Program poczProgram;

    //Statystyki
    private int minProgr;
    private double avrProgr;
    private int maxProgr;

    private int minEnerg;
    private double avrEnerg;
    private int maxEnerg;

    public Swiat(ArrayList<String> planPlanszy, ArrayList<String> parametry) {
        this.parametryCalkowite = new HashMap<>();
        this.parametryProcentowe = new HashMap<>();

        uzupelnijParametry();
        this.p = new Plansza(planPlanszy);

        if (parametry.size() != this.iloscParametrow) {
            System.out.println("Zła ilość parametróœ");
            this.poprawneParametry = false;
        }

        ArrayList<String> spis_instr = new ArrayList<>();

        for (String parametr : parametry) {
            String[] rozlaczne = parametr.split(" ");
            if (rozlaczne[0].equals("pocz_progr")) {
                this.poczProgram = new Program();
                for (int i = 1; i < rozlaczne.length; i++) {
                    if (rozlaczne[i].equals("l"))
                        this.poczProgram.dodajInstrukcje(ObrotLewo.stworz());
                    else if (rozlaczne[i].equals("p"))
                        this.poczProgram.dodajInstrukcje(ObrotPrawo.stworz());
                    else if (rozlaczne[i].equals("i"))
                        this.poczProgram.dodajInstrukcje(Idz.stworz());
                    else if (rozlaczne[i].equals("w"))
                        this.poczProgram.dodajInstrukcje(Wachaj.stworz());
                    else if (rozlaczne[i].equals("j"))
                        this.poczProgram.dodajInstrukcje(Jedz.stworz());
                    else {
                        this.poprawneParametry= false;
                        System.out.println("Niepoprawne polecenie programu");
                        return;
                    }
                }
            }
            else if (rozlaczne[0].equals("spis_instr")){
                for (int i = 1; i < rozlaczne.length; i++) {
                    if (this.spisPoprInstr.contains(rozlaczne[i])) {
                        spis_instr.add(rozlaczne[i]);
                    }
                    else {
                        System.out.println("Zły rodzaj instrukcji w "+
                                "spis_instr");
                    }
                }
            }
            else if (rozlaczne.length > 2) {
                System.out.println("Zły format paremetru: "+rozlaczne[0]);
                this.poprawneParametry = false;
                return;
            }
            else if (this.parametryCalkowite.containsKey(rozlaczne[0])) {
                if (this.parametryCalkowite.get(rozlaczne[0]) != -1){
                    System.out.println("Dwa razy ten sam parametr");
                    this.poprawneParametry = false;
                    return;
                }
                this.parametryCalkowite.replace(rozlaczne[0],
                        Integer.valueOf(rozlaczne[1]));

            }
            else if (this.parametryProcentowe.containsKey(rozlaczne[0])) {
                if (this.parametryProcentowe.get(rozlaczne[0]) != -1.0){
                    System.out.println("Dwa razy ten sam parametr");
                    this.poprawneParametry = false;
                    return;
                }
                this.parametryProcentowe.replace(rozlaczne[0],
                        Double.valueOf(rozlaczne[1]));
            }
            else{
                System.out.println("Błędny parametr: "+rozlaczne[0]);
                return;
            }
        }

        Zywieniowe.setParametry(
                this.parametryCalkowite.get("ile_daje_jedzenie"),
                this.parametryCalkowite.get("ile_rośnie_jedzenie"));

        Program.dodajParametry(spis_instr,
                this.parametryProcentowe.get("pr_powielenia"),
                this.parametryProcentowe.get("pr_usunięcia_instr"),
                this.parametryProcentowe.get("pr_dodania_instr"),
                this.parametryProcentowe.get("pr_zmiany_instr"));
    }

    private void uzupelnijParametry() {
        this.parametryCalkowite.put("ile_tur",-1);
        this.parametryCalkowite.put("rozmiar_planszy_x",-1);
        this.parametryCalkowite.put("rozmiar_planszy_y",-1);
        this.parametryCalkowite.put("pocz_ile_robów",-1);
        this.parametryCalkowite.put("pocz_progr",-1);
        this.parametryCalkowite.put("pocz_energia",-1);
        this.parametryCalkowite.put("ile_daje_jedzenie",-1);
        this.parametryCalkowite.put("ile_rośnie_jedzenie",-1);
        this.parametryCalkowite.put("koszt_tury",-1);
        this.parametryCalkowite.put("limit_powielania",-1);
        this.parametryCalkowite.put("co_ile_wypisz",-1);

        this.parametryProcentowe.put("ułamek_energii_rodzica",-1.0);
        this.parametryProcentowe.put("pr_usunięcia_instr",-1.0);
        this.parametryProcentowe.put("pr_dodania_instr",-1.0);
        this.parametryProcentowe.put("pr_zmiany_instr",-1.0);
        this.parametryProcentowe.put("pr_powielenia",-1.0);
        this.spisPoprInstr = new ArrayList<>();
        this.spisPoprInstr.add("l");
        this.spisPoprInstr.add("p");
        this.spisPoprInstr.add("i");
        this.spisPoprInstr.add("w");
        this.spisPoprInstr.add("j");
    }

    private void inicjalizujSymulacje() {

        Rob.przekazParametry(this.parametryProcentowe.get("pr_powielenia"),
                this.parametryCalkowite.get("koszt_tury"),
                this.parametryProcentowe.get("ułamek_energii_rodzica"),
                this.parametryCalkowite.get("limit_powielania"));

        this.roby = new ArrayList<>();
        int pocz_ile_robow = this.parametryCalkowite.get("pocz_ile_robów");
        int pocz_energia = this.parametryCalkowite.get("pocz_energia");

        for (int i = 0; i < pocz_ile_robow; i++) {
            Random random = new Random();
            int x = random.nextInt(Wektor.osX());
            int y = random.nextInt(Wektor.osY());
            Wektor pozycja = new Wektor(x,y);
            roby.add(new Rob(this.poczProgram,pozycja,pocz_energia));
        }
    }

    private void aktualizujStatystyki() {
        this.minProgr = 0;
        this.maxProgr = 0;
        this.avrProgr = 0;
        this.minEnerg = 0;
        this.maxEnerg = 0;
        this.avrEnerg = 0;

        for (Rob r : this.roby) {
            this.minEnerg = Math.min(this.minEnerg,r.energia());
            this.maxEnerg = Math.max(this.maxEnerg,r.energia());
            this.avrProgr += r.dlProgr();
            this.avrEnerg += r.energia();
            this.minProgr = Math.min(this.minProgr,r.dlProgr());
            this.maxProgr = Math.max(this.maxProgr,r.dlProgr());
        }
        if (this.roby.size() != 0){
            this.avrEnerg /= this.roby.size();
            this.avrProgr /= this.roby.size();
        }
    }

    private void symulujTure() {
        this.p.rosnijJedzenie();
        ArrayList<Rob> noweRoby = new ArrayList<>();
        for (Rob r : this.roby) {
            r.wykonajProgram(this.p);
            Rob powielenie = r.powielaj();
            if (powielenie != null) {
                noweRoby.add(powielenie);
            }
        }
        this.roby.addAll(noweRoby);

        ArrayList<Rob> martweroby = new ArrayList<>();
        for (Rob r: this.roby) {
            if (r.energia() <= 0)
                martweroby.add(r);
        }
        this.roby.removeAll(martweroby);

        aktualizujStatystyki();
    }

    private void wypiszStatystyki(int nrTury) {
        System.out.println(nrTury+", rob: "+this.roby.size()+" żyw: "+
                this.p.ileJedzenia()+" ,prg: "+ this.minProgr+"/"+this.avrProgr+
                "/"+this.maxProgr +",energ: "+ this.minEnerg+"/"+this.avrEnerg+
                "/"+this.maxEnerg);
    }

    public void symulujEwolucje() {
        if (this.poprawneParametry){
            this.inicjalizujSymulacje();
            int ileTur = this.parametryCalkowite.get("ile_tur");
            int coIleTur = this.parametryCalkowite.get("co_ile_wypisz");
            wypiszRoby();
            for (int i = 1; i <= ileTur; i++) {
                if (coIleTur != 0 && i % coIleTur == 0) {
                    wypiszRoby();
                }
                symulujTure();
                wypiszStatystyki(i);
            }
            wypiszRoby();

        }
        else {
            System.out.println("Błędne parametry");
        }
    }

    public void wypiszRoby(){
        int numer = 1;
        for (Rob r: this.roby){
            System.out.println("Rob nr "+numer+": ");
            System.out.print("(x,y) = ("+r.wspolrzedne().x()+","+
                    r.wspolrzedne().y()+")");
            System.out.print(" ,energia: "+r.energia()+" ,");
            r.wypiszProgram();
            numer++;
        }
    }
}