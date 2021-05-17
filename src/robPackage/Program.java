package robPackage;

import plansza.Plansza;

import java.util.ArrayList;
import java.util.Random;

public class Program {
    protected ArrayList<Instrukcja> instrukcje;

    private static double pr_usuniecia;
    private static double pr_dodania_instr;
    private static double pr_zmiany_instr;

    private static ArrayList<Instrukcja> spis_instr;

    public static void dodajParametry(ArrayList<String> spis_instr,
                                      double pr_powielania, double pr_usuniecia,
                                      double pr_dodania_instr,
                                      double pr_zmiany_instr) {

        Program.spis_instr = new ArrayList<>();
        for (String znak : spis_instr) {
            switch (znak) {
                case "j":
                    Program.spis_instr.add(new Jedz());
                    break;
                case "i":
                    Program.spis_instr.add(new Idz());
                    break;
                case "l":
                    Program.spis_instr.add(new ObrotLewo());
                    break;
                case "p":
                    Program.spis_instr.add(new ObrotPrawo());
                    break;
                case "w":
                    Program.spis_instr.add(new Wachaj());
                    break;
            }
        }
        Program.pr_usuniecia = pr_usuniecia;
        Program.pr_dodania_instr = pr_dodania_instr;
        Program.pr_zmiany_instr = pr_zmiany_instr;
    }

    public Program() {
        this.instrukcje = new ArrayList<>();
    }

    public void wypiszProgram() {
        System.out.print("Program: ");
        for (int i = 0; i < this.instrukcje.size(); i++) {
            if (i != 0)
                System.out.print(",");
            System.out.print(this.instrukcje.get(i));
        }
        System.out.println("");
    }

    public void dodajInstrukcje(Instrukcja instrukcja) {
        this.instrukcje.add(instrukcja);
    }

    public Program mutuj() {
        Program nowy = this.clone();
        Random random = new Random();
        double prawd = random.nextDouble();
        if (prawd < Program.pr_usuniecia && nowy.instrukcje.size() >= 1) {
            nowy.instrukcje.remove(nowy.instrukcje.size()-1);
        }
        prawd = random.nextDouble();
        if (prawd < Program.pr_dodania_instr) {
            int indeks = random.nextInt(Program.spis_instr.size());
            nowy.instrukcje.add(Program.spis_instr.get(indeks).clone());
        }
        prawd = random.nextDouble();
        if (prawd < Program.pr_zmiany_instr && nowy.instrukcje.size() >= 1) {
            int indeks = random.nextInt(Program.spis_instr.size());
            int i = random.nextInt(nowy.instrukcje.size());
            nowy.instrukcje.add(i,Program.spis_instr.get(indeks).clone());
        }
        return  nowy;
    }

    public Program clone() {
        Program kopia = new Program();
        for (Instrukcja instrukcja : this.instrukcje) {
            kopia.dodajInstrukcje(instrukcja.clone());
        }
        return kopia;
    }

    public int dlugoscProgramu() {
        return this.instrukcje.size();
    }
}