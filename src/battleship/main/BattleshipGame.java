package battleship.main;
import battleship.exceptions.WrongCoordsException;
import battleship.ocean.Ocean;
import battleship.ships.*;

import java.awt.*;
import java.io.WriteAbortedException;
import java.util.InputMismatchException;
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
        play();

    }
    static void scriptGameOver(){
        System.out.println("Game is over! You needed  " + ocean.getShotsFired() +
                " shots - this is your score (lower values are better)");
        System.out.println("Do you want to try again? print \"no\"" +
                " to exit, press any key to continue:");
    }
    static void inputAndShoot(){
        System.out.println("Input integer shooting coordinates [0;9]: x and y");
        int row = 0;
        int column = 0;
        boolean successInput = false;
        do {
            try {
                row = Integer.parseInt(scanner.next());
                column = Integer.parseInt(scanner.next());
                if(!ocean.checkCoordinateInsideField(row)){
                    throw new WrongCoordsException("wrong row!");
                }
                if(!ocean.checkCoordinateInsideField(column)){
                    throw new WrongCoordsException("wrong column!");
                }
                successInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Error in input");
                scanner.nextLine();
            }
            catch (WrongCoordsException e){
                System.out.println(e.getMessage());
            }
        }
        while (!successInput);
        ocean.shootAt(row, column);
    }
    public static void play(){
        String cmd;
        do {

            do {
                ocean.print();
                inputAndShoot();

                if(!ocean.isGameOver()){
                    printStats();
                }
            } while (!ocean.isGameOver());
            scriptGameOver();
            cmd = scanner.nextLine();
        }while (cmd != "no");
    }
    public static void printStats(){
        System.out.println("Hit count: " + ocean.getHitCount() + " shots fired: " + ocean.getShotsFired());
        System.out.println("Ships sunk: " + ocean.getShipsSunk());
    }
    public static void Input(){

    }

}

