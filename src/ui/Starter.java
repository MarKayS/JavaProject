package ui;

import core.Level;
import core.MenuFunctions;
import persistance.DBFunctions;

import java.util.ArrayList;
import java.util.Scanner;

public class Starter {
    public static void main(String[] args) {
        ArrayList<Level> levels = new ArrayList<>();
        levels.add(DBFunctions.getLevel("1", "2"));

        boolean gameover = false;
        Scanner scanner = new Scanner(System.in);
        char direction;

        MenuFunctions.loginprompt();
        /*
        System.out.print("MaxX: " + levels.get(0).getMaxX() + "\tMaxY: " + levels.get(0).getMaxY() + "\n");
        levels.get(0).render();
        while(!gameover){
            direction = scanner.next().charAt(0);
            levels.get(0).move(levels.get(0).locatePlayer(),direction);
            levels.get(0).render();
        }*/
    }
}
