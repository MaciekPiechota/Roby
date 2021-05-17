package robPackage;

public class ObrotLewo extends OperatorLiniowy {
    private static ObrotLewo obrotLewo= new ObrotLewo();

    protected ObrotLewo() {
        super(0,-1,1,0);
    }

    static public ObrotLewo stworz() {
        return obrotLewo;
    }

    public ObrotLewo clone() {
        return obrotLewo;
    }

    @Override
    public String toString() {
        return "L";
    }
}
