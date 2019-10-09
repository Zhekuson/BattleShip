package battleship.ships;

public class Destroyer extends Ship {
    @Override
    String getShipType(){
        return "destroyer";
    }

    @Override
    public boolean shootAt(int row, int column) {
        return super.shootAt(row, column);
    }
}
