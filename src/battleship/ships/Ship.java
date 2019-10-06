package battleship.ships;

import battleship.ocean.*;

public abstract class Ship {
    private int bowRow; //-- the row (0 to 9) which contains the bow (front) of the ship.
    private int bowColumn;// -- the column (0 to 9) which contains the bow (front) of the ship.
    private int length;// -- the number of squares occupied by the ship. An "empty sea" location has length 1.
    private boolean horizontal;// -- true if the ship occupies a single row, false otherwise.
    boolean [] hit = new boolean[4];// -- an array of booleans telling whether that part of
    // the ship has been hit. Only battleships use all four locations;\
    // cruisers use the first three; destroyers 2; submarines 1; and "empty sea" either one or none.

    int getLength(){
        return 0;
    }
    //Returns the length of this particular ship. This method exists only to be
    // overridden, so it doesn't much matter what it returns; an abstract "ship" doesn't have a fixed length.
           // Getters:
    int getBowRow(){
        return bowRow;
    } //-- Returns bowRow
    int getBowColumn() {
        return bowColumn;
    }

    boolean isHorizontal(){
        return horizontal;
    }// -- Returns horizontal
    //Setters:
    void setBowRow(int row) {
        bowRow = row;
    } //the value of bowRow
    void setBowColumn(int column){
       bowColumn = column;
    }// -- Sets the value of bowColumn
    void setHorizontal(boolean horizontal){
        this.horizontal = horizontal;
    } //z-- Sets the value of the instance variable horizontal.
    String getShipType()
    {
        return "ship";
    }
    //Returns the type of this ship. This method exists only to be overridden, so it doesn't much matter what it returns.
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){
        return false;
    }
    //Returns true if it is okay to put a ship of this length with its bow in this location,
    // with the given orientation, and returns false otherwise.
    // The ship must not overlap another ship, or touch another ship
    // (vertically, horizontally, or diagonally), and it must not "stick out" beyond the array.
    // Does not actually change either the ship or the Ocean, just says whether it is legal to do so.
    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean){

    }
//"Puts" the ship in the ocean. This involves giving values to
// the bowRow, bowColumn, and horizontal instance variables in
// the ship, and it also involves putting a reference to the
// ship in each of 1 or more locations (up to 4) in the ships
// array in the Ocean object. (Note: This will be as many as
// four identical references; you can't refer to a "part"
// of a ship, only to the whole ship.)
    boolean shootAt(int row, int column){
        return false;
    }
  //  If a part of the ship occupies the given row and column,
  //  and the ship hasn't been sunk, mark that part of the ship
  //  as "hit" (in the hit array, 0 indicates the bow) and
  //  return true, otherwise return false.
    boolean isSunk(){
        return true;
    }
    //Return true if every part of the ship
    // has been hit, false otherwise
    public String toString(){
        if(this.isSunk()) return "x";
        else return "s";
    }
}
