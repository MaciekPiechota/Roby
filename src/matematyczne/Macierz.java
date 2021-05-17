package matematyczne;

import matematyczne.Wektor;

/**
 * Klasa pozwalająca na tworzenie i mnozenie macierzy 2x2 przez
 * wektor
 */
public class Macierz {
    private int macierz[][];

    public Macierz(int a,int b,int c,int d) {
        this.macierz = new int[2][2];
        macierz[0][0] = a;
        macierz[0][1] = b;
        macierz[1][0] = c;
        macierz[1][1] = d;
    }

    public Wektor razyWektor(Wektor v) {
        return new Wektor(macierz[0][0]*v.x() + macierz[0][1]*v.y(),
                macierz[1][0]*v.x() + macierz[1][1]*v.y());
    }
}
