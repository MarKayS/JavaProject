package javaproject;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Level {
    int levelID;
    int gameID;
    Character[][] level;
    int maxX;
    int maxY;

    public Level(int levelID, int gameID, String levelString){
        this.levelID = levelID;
        this.gameID = gameID;
        int x = 0;
        int y = 0;
        for(int i = 0; i < levelString.length(); i++){
            if(levelString.charAt(i) == '\n'){
                x++;
            }
            if(levelString.charAt(i) != '\n' && x == 0){
                //System.out.print(y + " " +  levelString.charAt(i) + "\n");
                y++;
            }

        }
        y--; // final enter
        //System.out.print("\nx: " + x + "\ty: " + y + "\n");

        Character[][] level = new Character[x][y];

        int c = 0;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                level[i][j] = levelString.charAt(c);
                c++;
            }
            c++;
        }
        this.level=level;
        this.maxX=y;
        this.maxY=x;
    }

    public int getLevelID() {
        return levelID;
    }

    public int getGameID() {
        return gameID;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public Character[][] getLevel() {
        return level;
    }

    public void render(){
        for(int i = 0; i < maxX; i++){
            for(int j = 0; j < maxY; j++){
                System.out.print(level[i][j]);
            }
            System.out.print("\n");
        }
    }
}

