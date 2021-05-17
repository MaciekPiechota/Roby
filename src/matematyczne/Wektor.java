package matematyczne;

public class Wektor {
    /**
     * Klasa Wekter pozwala na tworzenie i dodawanie wektorow w obrebie planszy
     * Pola statyczne oznaczaja rozmiary planszy ktore sa wspolne dla
     * wszystkich wektorów
     */

    private static int osX;
    private static int osY;

    private int x;
    private int y;

    /*
    public Wektor(){
        this.x = 0;
        this.y = 0;
    }


     */
    public Wektor(int x,int y) {
        this.x = mod(x,Wektor.osX());
        this.y = mod(y,Wektor.osY());
    }

    public static void ustaw(int osX, int osY) {
        Wektor.osX = osX;
        Wektor.osY = osY;
    }

    public static int osX() {
        return Wektor.osX;
    }

    public static int osY() {
        return Wektor.osY;
    }

    public int x(){
        return this.x;
    }

    public int y(){
        return this.y;
    }

    private int mod(int a,int b) {
        int result = a%b;
        if (result < 0) {
            result += b;
        }
        return result;
    }

    @Override
    public String toString(){
        return this.x + " " +this.y;
    }

    public Wektor dodajWektor(Wektor b) {
        return new Wektor(mod((this.x+b.x),Wektor.osX()),mod((this.y+b.y),Wektor.osY()));
    }

    @Override
    public Wektor clone() {
        return new Wektor(this.x,this.y);
    }
}
