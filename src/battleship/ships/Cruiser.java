package battleship.ships;

public class Cruiser extends Ship {
   public final static String shipType = "cruiser";
   public Cruiser(){
       length = 3;
   }
    @Override
    public String getShipType() {
        return shipType;
    }

}
