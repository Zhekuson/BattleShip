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

    }
    boolean shootAt(int row, int column){

    }
    int getShotsFired(){

    }
    int getHitCount(){

    }
    int getShipsSunk(){

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
