package battleship.ships;

import battleship.ocean.*;

public abstract class Ship {

    private int bowRow; //the row (0 to 9) which contains the bow (front) of the ship.
    private int bowColumn;//the column (0 to 9) which contains the bow (front) of the ship.
    protected int length;//the number of squares occupied by the ship. An "empty sea" location has length 1.
    private boolean horizontal;//true if the ship occupies a single row, false otherwise.

    /**
     * an array of booleans telling whether that part of
     * the ship has been hit. Only battleships use all four locations;
     */
    private boolean [] hit = new boolean[4];




    /**
     * getter for length
     * @return length
     */
    public int getLength(){
        return length;
    }
    /**
     * getter for bowRow
     * @return bowRow
     */
    public int getBowRow(){
        return bowRow;
    }
    /**
     * getter for bowColumn
     * @return bowColumn
     */
    public int getBowColumn() {
        return bowColumn;
    }
    /**
     * getter for horizontal
     * @return horizontal
     */
    public boolean isHorizontal(){
        return horizontal;
    }// -- Returns horizontal

    //Setters:

    /**
     * setter for bowRow
     * @param row
     */
    private void setBowRow(int row) {
        bowRow = row;
    }
    /**
     * setter for bowColumn
     * @param column
     */
    private void setBowColumn(int column){
       bowColumn = column;
    }
    /**
     * setter for horizontal
     * @param horizontal
     */
    private void setHorizontal(boolean horizontal){
        this.horizontal = horizontal;
    }


    /**
     * uses setters and sets all three params
     * @param bowRow
     * @param bowColumn
     * @param horizontal
     */
    public void setParameters(int bowRow, int bowColumn, Boolean horizontal){
        setBowRow(bowRow);
        setBowColumn(bowColumn);
        setHorizontal(horizontal);
    }

    /**
     * method for overriding later
     * @return string of the shiptype
     */
    public abstract String getShipType();

    /**
     * inconvenient version of method @see battleship.ocean.Ocean#checkCoordinateInsideField
     * @param row
     * @param column
     * @param horizontal
     * @param fieldsize
     * @return
     */
    private boolean checkIsInField(int row, int column, boolean horizontal, int fieldsize){
        if(horizontal){
            return column + length <= fieldsize;
        }
        else {
            return row + length <= fieldsize;
        }
    }

    /**
     *
     *       Returns true if it is okay to put a ship of this length with its bow in this location,
     *      with the given orientation, and returns false otherwise.
     *       The ship must not overlap another ship, or touch another ship
     *       (vertically, horizontally, or diagonally), and it must not "stick out" beyond the array.
     *      Does not actually change either the ship or the Ocean, just says whether it is legal to do so.
     *
     * @param row
     * @param column
     * @param horizontal
     * @param ocean concrete ocean
     * @return is ok or not
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){
        if(!checkIsInField(row, column, horizontal, Ocean.FIELDSIZE)){
            return false;
        }
        else {
            return checkPlacement(row, column, horizontal, ocean);
        }
    }

    /**
     * checks position for legal ship placement
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     * @return is ok or not
     */
    private boolean checkPlacement(int row, int column, boolean horizontal,Ocean ocean){
        int startRow = row > 0 ? row - Ocean.PLACEMENTSIZE: 0;
        int startColumn = column > 0 ? column - Ocean.PLACEMENTSIZE: 0;

        int finishRow = horizontal ? Math.min(row + Ocean.PLACEMENTSIZE, Ocean.FIELDSIZE - 1):
                Math.min(row + length + Ocean.PLACEMENTSIZE,  Ocean.FIELDSIZE - 1);
        int finishColumn = horizontal? Math.min(column + length + Ocean.PLACEMENTSIZE, Ocean.FIELDSIZE - 1):
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


    /**
     * "Puts" the ship in the ocean. This involves giving values to
     *  the <b>bowRow</b>, <b>bowColumn</b>, and <b>horizontal</b> instance variables in
     *  the ship, and it also involves putting a reference to the
     *  ship in each of 1 or more locations (up to 4) in the ships
     *  array in the Ocean object.
     * @param row bowRow
     * @param column bowColumn
     * @param horizontal horizontal
     * @param ocean ocean
     */
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

    /**
     *     If a part of the ship occupies the given row and column,
     *     and the ship hasn't been sunk, mark that part of the ship
     *     as "hit" (in the hit array, 0 indicates the bow) and
     *      return true, otherwise return false.
     * @param row
     * @param column
     * @return returns true if this part was damaged for first time else returns false
     */
    public boolean shootAt(int row, int column){

            int pos = horizontal? column - bowColumn: row - bowRow;
            if(!hit[pos]) {
                hit[pos] = true;
                return true;
            }
            return false;

    }

    /**
     *      Return true if every part of the ship
     *      has been hit, false otherwise
     * @return is sunk or not
     */
    public boolean isSunk(){
        for (int i = 0; i < length; i++){
            if(!hit[i]) return false;
        }
        return true;
    }

    /**
     * used for displaying ship on the screen
     * @return "x" if ship is sunk, "s" if damaged but not sunk
     */
    public String toString(){
        if(this.isSunk()) return "x";
        else return "s";
    }
}
