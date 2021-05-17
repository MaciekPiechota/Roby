package robPackage;

public class ObrotPrawo extends OperatorLiniowy {
    private static ObrotPrawo obrotPrawo = new ObrotPrawo();

    protected ObrotPrawo() {
        super(0,1,-1,0);
    }

    static public ObrotPrawo stworz() {
        return obrotPrawo;
    }

    public ObrotPrawo clone() {
        return obrotPrawo;
    }

    @Override
    public String toString() {
        return "P";
    }
}
