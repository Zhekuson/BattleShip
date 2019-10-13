package battleship.ships;

public class Battleship extends Ship {
    public final static String shipType = "battleship";

    public Battleship(){
        length = 4;
    }
    @Override
    public String getShipType() {
        return shipType;
    }
}
