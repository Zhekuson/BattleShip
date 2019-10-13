package battleship.ships;

import battleship.ocean.*;

public abstract class Ship {
    private int bowRow; //-- the row (0 to 9) which contains the bow (front) of the ship.
    private int bowColumn;// -- the column (0 to 9) which contains the bow (front) of the ship.
    protected int length;// -- the number of squares occupied by the ship. An "empty sea" location has length 1.
    private boolean horizontal;// -- true if the ship occupies a single row, false otherwise.
    private boolean [] hit = new boolean[4];// -- an array of booleans telling whether that part of
    // the ship has been hit. Only battleships use all four locations;\
    // cruisers use the first three; destroyers 2; submarines 1; and "empty sea" either one or none.

    public int getLength(){
        return length;
    }
    public int getBowRow(){
        return bowRow;
    }
    public int getBowColumn() {
        return bowColumn;
    }

    public boolean isHorizontal(){
        return horizontal;
    }// -- Returns horizontal

    //Setters:
    public void setBowRow(int row) {
        bowRow = row;
    } //the value of bowRow
    public void setBowColumn(int column){
       bowColumn = column;
    }// -- Sets the value of bowColumn
    public void setHorizontal(boolean horizontal){
        this.horizontal = horizontal;
    } //z-- Sets the value of the instance variable horizontal.

    public void setParameters(int bowRow, int bowColumn, Boolean horizontal){
        setBowRow(bowRow);
        setBowColumn(bowColumn);
        setHorizontal(horizontal);
    }
    public abstract String getShipType();


    private boolean checkIsInField(int row, int column, boolean horizontal, int fieldsize){
        if(horizontal){
            return column + length <= fieldsize;
        }
        else {
            return row + length <= fieldsize;
        }
    }
    //Returns the type of this ship. This method exists only to be overridden, so it doesn't much matter what it returns.
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){
        if(!checkIsInField(row, column, horizontal, Ocean.FIELDSIZE)){
            return false;
        }
        else {
            return checkPlacement(row, column, horizontal, ocean);
        }
    }
    private boolean checkPlacement(int row, int column, boolean horizontal,Ocean ocean){
        int startRow = row > 0 ? row - 1: row;
        int startColumn = column > 0 ? column - 1: column;
        int finishRow = horizontal ? Math.min(startRow + Ocean.PLACEMENTSIZE, Ocean.FIELDSIZE - 1):
                Math.min(startRow + length + 1,  Ocean.FIELDSIZE - 1);
        int finishColumn = horizontal? Math.min(startColumn + length + 1, Ocean.FIELDSIZE - 1):
                Math.min(column + Ocean.PLACEMENTSIZE, Ocean.FIELDSIZE - 1);

        for (int i = startRow; i <= finishRow; i++){
            for (int j = startColumn; j <= finishColumn; j++){
                    if (!(ocean.getShipArray()[i][j] instanceof EmptySea)) {
                        return false;
                    }
            }
        }
        return true;
    }

    //Returns true if it is okay to put a ship of this length with its bow in this location,
    // with the given orientation, and returns false otherwise.
    // The ship must not overlap another ship, or touch another ship
    // (vertically, horizontally, or diagonally), and it must not "stick out" beyond the array.
    // Does not actually change either the ship or the Ocean, just says whether it is legal to do so.
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean){
        this.setParameters(row, column, horizontal);
        ocean.getShipArray()[row][column] = this;
        if(horizontal) {
            for (int i = 1; i < length; i++){
                ocean.getShipArray()[row][column+i] = this;
            }
        }else
        {
            for (int i = 1; i < length; i++){
                ocean.getShipArray()[row+i][column] = this;
            }
        }
    }
//"Puts" the ship in the ocean. This involves giving values to
// the bowRow, bowColumn, and horizontal instance variables in
// the ship, and it also involves putting a reference to the
// ship in each of 1 or more locations (up to 4) in the ships
// array in the Ocean object. (Note: This will be as many as
// four identical references; you can't refer to a "part"
// of a ship, only to the whole ship.)
    public boolean shootAt(int row, int column){

            int pos = horizontal? column - bowColumn: row - bowRow;
            if(!hit[pos]) {
                hit[pos] = true;
                return true;
            }
            return false;

    }
  //  If a part of the ship occupies the given row and column,
  //  and the ship hasn't been sunk, mark that part of the ship
  //  as "hit" (in the hit array, 0 indicates the bow) and
  //  return true, otherwise return false.
    public boolean isSunk(){
        for (int i = 0; i < length; i++){
            if(!hit[i]) return false;
        }
        return true;
    }
    //Return true if every part of the ship
    // has been hit, false otherwise
    public String toString(){
        if(this.isSunk()) return "x";
        else return "s";
    }
}
