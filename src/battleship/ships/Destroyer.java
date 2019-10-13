package battleship.ships;

public class Destroyer extends Ship {
    public final static String shipType = "destroyer";
    public Destroyer()
    {
        length = 2;
    }
    @Override
    public boolean shootAt(int row, int column) {
        return super.shootAt(row, column);
    }
    @Override
    public String getShipType() {
        return shipType;
    }
}
