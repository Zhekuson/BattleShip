package battleship.main;
import battleship.ocean.Ocean;
import battleship.ships.*;

import java.util.Scanner;

/**
 *
 *
 */
public class BattleshipGame {
    public static Ocean ocean;
    public static Scanner scanner =  new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Hello and welcome to the battleship game");
        ocean = new Ocean();
        Play();

    }
    public static void Play(){
        do {
            ocean.print();
            System.out.println("Input integer shooting coordinates [0;9]: x and y");
            int row = scanner.nextInt();
            int column = scanner.nextInt();

            ocean.shootAt(row,column);
            printStats();
           // Cruiser cruiser = new Cruiser();
          //  System.out.println(cruiser.getShipType());
        }while (!ocean.isGameOver());
    }
    public static void printStats(){
        System.out.println("Hit count: " + ocean.getHitCount() + " shots fired: " + ocean.getShotsFired());
    }
    public static void Input(){

    }

}

