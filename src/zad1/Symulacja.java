package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Symulacja {
	public static void main(String args[]) throws FileNotFoundException {

		// wczytywanie planszy
		ArrayList<String> planPlanszy = new ArrayList<String>();
		try (Scanner sc = new Scanner(new File(args[0]))){
			while (sc.hasNextLine()) {
			    planPlanszy.add(sc.nextLine());
			}
		}
		// wczytywanie parametrow
		ArrayList<String> parametry = new ArrayList<String>();
		try (Scanner sc = new Scanner(new File(args[1]))){
			while (sc.hasNextLine()) {
				parametry.add(sc.nextLine());
			}
		}

		Swiat swiat = new Swiat(planPlanszy,parametry);
		swiat.symulujEwolucje();
	}
}
