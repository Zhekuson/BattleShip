package battleship.ships;

public class Destroyer extends Ship {
    public final static String shipType = "destroyer";
    /**
     * constructor that should initialize length of the concrete ship
     */
    public Destroyer()
    {
        length = 2;
    }
    @Override
    public boolean shootAt(int row, int column) {
        return super.shootAt(row, column);
    }
    /**
     * method  that returns static field with ship type
     * @return shiptype in string
     */
    @Override
    public String getShipType() {
        return shipType;
    }
}
