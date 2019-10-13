package battleship.ships;

public class Battleship extends Ship {
    public final static String shipType = "battleship";

    /**
     * constructor that should initialize length of the concrete ship
     */
    public Battleship(){
        length = 4;
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
