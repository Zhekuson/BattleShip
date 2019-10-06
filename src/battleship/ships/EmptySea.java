package battleship.ships;

public class EmptySea extends Ship {
    public EmptySea(){
        this.length = 1;
    }

    @Override
    boolean shootAt(int row, int column){
        return false;
    }
    @Override
    boolean isSunk(){
        return false;
    }

    @Override
    String getShipType(){
        return this.toString();
    }
}
