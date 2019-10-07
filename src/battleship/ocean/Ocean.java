package battleship.ocean;

import battleship.ships.EmptySea;
import battleship.ships.Ship;

public class Ocean {
    public static final int FIELDSIZE = 10;
    Ship[][] ships = new Ship[FIELDSIZE][FIELDSIZE];
    int shotsFired;
    int hitCount;
    int shipsSunk;
    public Ocean(){
        for (int i = 0; i < FIELDSIZE; i++) {
            for (int j = 0; j < FIELDSIZE; j++){
                ships[i][j] = new EmptySea();
            }
        }
    }

    void placeAllShipsRandomly(){

    }
    boolean isOccupied(int row, int column){
        return ships[row][column] instanceof EmptySea;
    }
    //TODO remake
    boolean shootAt(int row, int column){
        shotsFired++;
        return ships[row][column].shootAt(row, column);
    }
    int getShotsFired(){
        return shotsFired;
    }
    int getHitCount(){
        return hitCount;
    }
    int getShipsSunk(){
        return shipsSunk;
    }
    boolean isGameOver(){
        
    }
    public Ship[][] getShipArray(){
        return ships;
    }

    /**
     * Prints the ocean
     */
    void print(){

    }

}
