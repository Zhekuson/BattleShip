package battleship.ships;

public class EmptySea extends Ship {

    /**
     * constructor initializes length with 1
     */
    public EmptySea(){
        this.length = 1;
    }

    /**
     * overridden shootAt for emptysea
     * @param row
     * @param column
     * @return always return false
     */
    @Override
    public boolean shootAt(int row, int column){
        return false;
    }

    /**
     * empty sea cannot be sunk
     * @return false
     */
    @Override
    public boolean isSunk(){
        return false;
    }

    /**
     * used for displaying on the screen
     * @return "-" because empty sea is empty when checked
     */
    public String toString(){
        return "-";
    }

    /**
     * method  that returns static field with ship type
     * @return shiptype in string
     */
    @Override
    public String getShipType(){
        return this.toString();
    }
}
