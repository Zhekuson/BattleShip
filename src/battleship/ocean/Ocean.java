package battleship.ocean;

import battleship.ships.EmptySea;
import battleship.ships.Ship;

import java.io.Console;

public class Ocean {
    public static final int FIELDSIZE = 10;
    public static final int SHIPCOUNT = 10;
    Ship[][] ships = new Ship[FIELDSIZE][FIELDSIZE];
    int shotsFired;
    int hitCount;
    int shipsSunk;
    public Ocean(){
        initializeEmptySea();

    }
    void initializeEmptySea(){
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
        return shipsSunk == SHIPCOUNT;
    }
    public Ship[][] getShipArray(){
        return ships;
    }

    /**
     * Prints the ocean
     */
    void print(){
        for (int i = 0; i < FIELDSIZE; i++){
            for(int j = 0; j < FIELDSIZE; j++){
                System.out.print(ships[i][j]);
            }
            System.out.println();
        }
    }

}
