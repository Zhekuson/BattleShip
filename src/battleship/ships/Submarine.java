package battleship.ships;

public class Submarine extends Ship {
    public final static String shipType = "submarine";
    /**
     * constructor that should initialize length of the concrete ship
     */
    public Submarine(){
        length = 1;
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
