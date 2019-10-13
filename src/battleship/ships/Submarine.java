package battleship.ships;

public class Submarine extends Ship {
    public final static String shipType = "submarine";
    public Submarine(){
        length = 1;
    }
    @Override
    public String getShipType() {
        return shipType;
    }
}
