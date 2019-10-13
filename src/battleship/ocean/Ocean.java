package battleship.ocean;

import battleship.ships.*;

import java.io.Console;

public class Ocean {
    public static final int PLACEMENTSIZE = 2;
    public static final int FIELDSIZE = 10;
    public static final int SHIPCOUNT = 10;
    public static final int CRUISERCOUNT = 2;
    public static final int DESTROYERCOUNT = 3;
    public static final int SUBMARINECOUNT = 4;
    public static final int BATTLESHIPCOUNT = 1;
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
                fieldShoot[i][j] = true;//here face
            }
        }
    }
    private int randomize(int Min, int Max){
        return (int)(Min + Math.random()*(Max-Min));
    }

    /**
     *
     * Creates ship array using method fillShipsType
     * it starts from the biggest ship - battleship
     * @return Ship[] array
     */
    private Ship[] createShips(){
        Ship[] allShips = new Ship[SHIPCOUNT];
        int index = 0;
        fillShipsType(Battleship.shipType,allShips,index);
        index += BATTLESHIPCOUNT;
        fillShipsType(Cruiser.shipType,allShips,index);
        index += CRUISERCOUNT;
        fillShipsType(Destroyer.shipType,allShips,index);
        index += DESTROYERCOUNT;
        fillShipsType(Submarine.shipType,allShips,index);
        return allShips;
    }
    private void fillShipsType(String shipType, Ship[] allShips, int index){
        switch (shipType){
            case Cruiser.shipType:
                for (int i = 0; i < CRUISERCOUNT; i++){
                    allShips[i+index] = new Cruiser();
                }
                break;
            case Destroyer.shipType:
                for (int i = 0; i < DESTROYERCOUNT; i++){
                    allShips[i+index] = new Destroyer();
                }
                break;
            case Submarine.shipType:
                for (int i = 0; i < SUBMARINECOUNT; i++){
                    allShips[i+index] = new Submarine();
                }
                break;
            case Battleship.shipType:
                for (int i = 0; i < BATTLESHIPCOUNT; i++){
                    allShips[i+index] = new Battleship();
                }
                break;
        }
    }
    private boolean generatePosition(Ship ship){
        int row = randomize(0, FIELDSIZE);
        int column = randomize(0, FIELDSIZE);
        boolean horizontal = randomize(0, 3) > 1;

        if(ship.okToPlaceShipAt(row,column,horizontal,this)){
            ship.placeShipAt(row,column,horizontal,this);
            return true;
        }
        else
            return false;
    }
    public void placeAllShipsRandomly(){

        Ship[] allShips = createShips();
        for (Ship ship:allShips) {
            while (!generatePosition(ship)){
            }
        }


    }
    public boolean isOccupied(int row, int column)
    {
        return ships[row][column] instanceof EmptySea;
    }
    //TODO remake
    public boolean shootAt(int row, int column){
        shotsFired++;
        fieldShoot[row][column] = true;
        if(ships[row][column].shootAt(row, column)){
            hitCount++;
            if (ships[row][column].isSunk()) shipsSunk++;
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
                    System.out.print(ships[i][j].toString() + "  ");
                }else{
                    System.out.print("."+ "  ");
                }
            }
            System.out.println();
        }
    }

}
