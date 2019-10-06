package battleship.ocean;

import battleship.ships.EmptySea;
import battleship.ships.Ship;

public class Ocean {
    public static final int  FIELDSIZE = 10;
    Ship[][] ships = new Ship[FIELDSIZE][FIELDSIZE];
    int shotsFired;
    int hitCount;
    int shipsSunk;
    Ocean(){
        for (int i = 0; i < FIELDSIZE; i++) {
            for (int j = 0; j < FIELDSIZE; j++){
                ships[i][j] = new EmptySea();
            }
        }
    }
}
