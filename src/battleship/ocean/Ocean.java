package battleship.ocean;

import battleship.exceptions.WrongCoordsException;
import battleship.ships.*;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.Console;

/**
 * the class of Ocean
 * contains the field
 */
public class Ocean {
    //some constants
    public static final int PLACEMENTSIZE = 2;
    public static final int FIELDSIZE = 10;
    private static final int SHIPCOUNT = 10;
    private static final int CRUISERCOUNT = 2;
    private static final int DESTROYERCOUNT = 3;
    private static final int SUBMARINECOUNT = 4;
    private static final int BATTLESHIPCOUNT = 1;

    /**
     * the field that shows which of the cells were checked
     */
    private boolean[][] fieldShoot = new boolean[FIELDSIZE][FIELDSIZE];

    private Ship[][] ships = new Ship[FIELDSIZE][FIELDSIZE];
    private int shotsFired;
    private int hitCount;
    private int shipsSunk;
    private Ship ship;

    /**
     * this constructor invokes initializing empty sea
     * after that it invokes method that places ships in the sea
     */
    public Ocean(){
        initializeEmptySea();
        placeAllShipsRandomly();
    }

    /**
     * simply initializes array of ships with the instances of EmptySea
     */
    private void initializeEmptySea(){
        for (int i = 0; i < FIELDSIZE; i++) {
            for (int j = 0; j < FIELDSIZE; j++){
                ships[i][j] = new EmptySea();
            }
        }
    }

    /**
     * randomizes integer from min to max
     * @param min minimum
     * @param max maximum
     * @return int from min to max
     */
    private int randomize(int min, int max){
        return (int)(min + Math.random()*(max-min));
    }

    /**
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

    /**
     * fills the required part of the array with <b>shipType</b>
     * @param shipType type from String
     * @param allShips array
     * @param index starting index
     */
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

    /**
     * generates parameters for <b>ship</b>
     * @param ship ship that needs generating params
     */
    private void generateShipParams(Ship ship){
        int row = randomize(0, FIELDSIZE);
        int column = randomize(0, FIELDSIZE);
        boolean horizontal = randomize(0, 3) > 1;
        ship.setParameters(row, column, horizontal);
    }

    /**
     * Includes generating positions, checking them and placing
     * ships on them for all ships
     */
    private void placeAllShipsRandomly(){
        Ship[] allShips = createShips();
        for (Ship ship:allShips) {
            do{
                generateShipParams(ship);
            }while (!ship.okToPlaceShipAt(ship.getBowRow(), ship.getBowColumn(),
                    ship.isHorizontal(),this));

            ship.placeShipAt(ship.getBowRow(), ship.getBowColumn(),
                    ship.isHorizontal(),this);
        }


    }

    /**
     * checks if coord num located inside the field
     * @param coord
     * @return if coord located inside the field returns true
     */
    public boolean checkCoordinateInsideField(int coord) {
        return coord >= 0 && coord < FIELDSIZE;
    }
    public boolean isOccupied(int row, int column)
    {
        return ships[row][column] instanceof EmptySea;
    }
    private void messageShipSunk(Ship ship){
        this.ship = ship;
        System.out.println();
        System.out.println("You just sank a "+ship.getShipType());
    }

    /**
     * Shooting method
     * @param row row of the field
     * @param column column of the field
     * @return if this place was successfully shot for the first time returns true
     * else (shot second time or here you found nothing) return false
     */
    public boolean shootAt(int row, int column){
        shotsFired++;
        fieldShoot[row][column] = true;
        if(ships[row][column].shootAt(row, column)){
            hitCount++;
            if (ships[row][column].isSunk()) {
                shipsSunk++;
                messageShipSunk(ships[row][column]);
                fixSunkShipField(ships[row][column]);
            }
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * this method is added for making field around the sunken ship
     * already checked because of the rules of the game(no ships located near each other)
     * it makes gameplay very comfortable
     * @param ship sunked ship
     */
    private void fixSunkShipField(Ship ship){
        int startRow = ship.getBowRow() > 0 ? ship.getBowRow() - 1: ship.getBowRow();
        int startColumn = ship.getBowColumn() > 0 ? ship.getBowColumn() - 1: ship.getBowColumn();
        int finishRow = ship.isHorizontal() ? Math.min(startRow + Ocean.PLACEMENTSIZE, Ocean.FIELDSIZE - 1):
                Math.min(startRow + ship.getLength() + 1,  Ocean.FIELDSIZE - 1);
        int finishColumn = ship.isHorizontal()? Math.min(startColumn + ship.getLength() + 1, Ocean.FIELDSIZE - 1):
                Math.min(startColumn + Ocean.PLACEMENTSIZE, Ocean.FIELDSIZE - 1);

        for (int i = startRow; i <= finishRow; i++){
            for (int j = startColumn; j <= finishColumn; j++){
                fieldShoot[i][j] = true;
            }
        }
    }
    /**
     * getter for shotsFired
     * @return shotsFired
     */
    public int getShotsFired(){
        return shotsFired;
    }

    /**
     * getter for hitCount
     * @return hitCount
     */
    public int getHitCount(){
        return hitCount;
    }

    /**
     * getter for shipsSunk(number of destroyed ships)
     * @return shipsSunk
     */
    public int getShipsSunk(){
        return shipsSunk;
    }

    /**
     * checks if the game is over
     * @return true if game is over (all ships are destroyed)
     */
    public boolean isGameOver(){
        return shipsSunk == SHIPCOUNT;
    }

    /**
     * getter for ship array(field)
     * @return field
     */
    public Ship[][] getShipArray(){
        return ships;
    }

    /**
     * Prints the ocean
     */
    public void print(){
        //prints first row
        System.out.print("*   ");
        for (int i = 0; i < FIELDSIZE; i++){
            System.out.print(i+"  ");
        }
        System.out.println();
        //prints the rest (column in the left and all rows)
        for (int i = 0; i < FIELDSIZE; i++){
            System.out.print(i+"|  ");
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
