package battleship.ships;

public class Cruiser extends Ship {
   public final static String shipType = "cruiser";
    /**
     * constructor that should initialize length of the concrete ship
     */
   public Cruiser(){
       length = 3;
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
