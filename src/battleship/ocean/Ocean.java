package battleship.ocean;

import battleship.ships.*;

import java.io.Console;

public class Ocean {
    public static final int FIELDSIZE = 10;
    public static final int SHIPCOUNT = 10;
    public static final int CRUISERCOUNT = 2;
    public static final int DESTROYERCOUNT = 3;
    public static final int SUBMARINECOUNT = 4;
    boolean[][] fieldShoot = new boolean[FIELDSIZE][FIELDSIZE];
    Ship[][] ships = new Ship[FIELDSIZE][FIELDSIZE];
    int shotsFired;
    int hitCount;
    int shipsSunk;
    public Ocean(){
        initializeEmptySea();
        placeAllShipsRandomly();
    }
    void initializeEmptySea(){
        for (int i = 0; i < FIELDSIZE; i++) {
            for (int j = 0; j < FIELDSIZE; j++){
                ships[i][j] = new EmptySea();
                fieldShoot[i][j] = false;
            }
        }
    }
    private int randomize(int Min, int Max){
        return (int)(Min + Math.random()*(Max-Min));
    }
    public void placeAllShipsRandomly(){
        Battleship bship = new Battleship();
        Cruiser[] cruisers = new Cruiser[CRUISERCOUNT];
        Destroyer[] destroyers = new Destroyer[DESTROYERCOUNT];
        Submarine[] submarines = new Submarine[SUBMARINECOUNT];

        bship.placeShipAt(0,0,true,this);
        bship.placeShipAt(0,1,true,this);
    }
    public boolean isOccupied(int row, int column){
        return ships[row][column] instanceof EmptySea;
    }
    //TODO remake
    public boolean shootAt(int row, int column){
        shotsFired++;
        if(ships[row][column].shootAt(row, column)){
            hitCount++;
            fieldShoot[row][column] = true;
            return true;
        }
        else{
            return false;
        }

    }
    public int getShotsFired(){
        return shotsFired;
    }
    public int getHitCount(){
        return hitCount;
    }
    public int getShipsSunk(){
        return shipsSunk;
    }
    public boolean isGameOver(){
        return shipsSunk == SHIPCOUNT;
    }
    public Ship[][] getShipArray(){
        return ships;
    }

    /**
     * Prints the ocean
     */
    public void print(){
        for (int i = 0; i < FIELDSIZE; i++){
            for(int j = 0; j < FIELDSIZE; j++){
                if(fieldShoot[i][j]) {
                    System.out.print(ships[i][j] + "  ");
                }else{
                    System.out.print("."+ "  ");
                }
            }
            System.out.println();
        }
    }

}
