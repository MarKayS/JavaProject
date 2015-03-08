package ui;

import core.Level;
import core.MenuFunctions;
import persistance.DBFunctions;

import java.util.ArrayList;
import java.util.Scanner;

public class Starter {

    public static void main(String[] args) {

        SlickGUI.main(args);
        /*boolean gameover = false;
        Scanner scanner = new Scanner(System.in);
        char direction;

        MenuFunctions.loginprompt();
        int gameID = MenuFunctions.gameselectpromt();
        ArrayList<Level> levels = DBFunctions.getLevels(gameID);
        int levelID =  MenuFunctions.levelselectprompt(levels.size());

      //  System.out.print("MaxX: " + levels.get(0).getMaxX() + "\tMaxY: " + levels.get(0).getMaxY() + "\n");
        levels.get(levelID - 1).render();
        while(!gameover){
            direction = scanner.next().charAt(0);
            levels.get(levelID - 1).move(levels.get(levelID - 1).locatePlayer(),direction);
            levels.get(levelID - 1).render();
        }*/
    }
}
