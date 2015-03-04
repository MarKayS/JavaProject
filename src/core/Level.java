package core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Level {
    int levelID;
    int gameID;
    Character[][] level;
    int maxX;
    int maxY;
    boolean playerX = false;

    public Level(int levelID, int gameID, String levelString){
        this.levelID = levelID;
        this.gameID = gameID;
        int x = 1;
        int y = 0;
        for(int i = 0; i < levelString.length(); i++){
            if(levelString.charAt(i) == '\n'){
                x++;
            }
            if(levelString.charAt(i) != '\n' && x == 1){
                //System.out.print(y + " " +  levelString.charAt(i) + "\n");
                y++;
            }

        }
        y--;// final enter
        Character[][] level = new Character[x][y];

        int c = 0;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                while(levelString.charAt(c)!= 'W' &&  levelString.charAt(c)!= 'P' && levelString.charAt(c)!= 'B' && levelString.charAt(c)!= ' ' && levelString.charAt(c)!= 'X'){
                    c++;
                }
                level[i][j] = levelString.charAt(c);
                c++;
            }
        }
        this.level = level;
        this.maxX = y;
        this.maxY = x;
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
        for(int i = 0; i < maxY; i++){
            for(int j = 0; j < maxX; j++){
                System.out.print(level[i][j]);
            }
            System.out.print("\n");
        }
    }
    public Point locatePlayer(){
        for(int i = 0; i < maxY; i++){
            for(int j = 0; j < maxX; j++){
                if (level[i][j] == 'P')
                    return new Point(j,i);
            }
        }
        return new Point(-1,-1);
    }
    public boolean move(Point from, char direction){
        int x = 0, y = 0;

        if (direction == 'w'){
            y = -1;
        }
        else if(direction == 's'){
            y = +1;
        }
        else if(direction == 'a'){
            x = -1;
        }
        else if (direction == 'd'){
            x = +1;
        }
        Point to = new Point(from.x+x,from.y+y);
        if(level[from.y][from.x] != 'B' && level[from.y][from.x] != '0' && level[from.y][from.x] != 'P'){
            return false;
        }
        else if(level[to.y][to.x] == 'W'){
            return false;
        }
        else {
            if(level[from.y][from.x] == 'P'){
                if (level[to.y][to.x] == 'B' || level[to.y][to.x] == '0'){
                    if(move(to,direction)){
                    }
                    else {
                        return false;
                    }
                }
            }
            if(level[from.y][from.x] == '0'){
                level[from.y][from.x] = 'X';
                level[to.y][to.x] = 'B';
            }
            else if (level[to.y][to.x] == 'X'){
                if(level[from.y][from.x] == 'B')
                    level[to.y][to.x] = '0';
                else if(level[from.y][from.x] == 'P'){
                    level[from.y][from.x] = ' ';
                    level[to.y][to.x] = 'P';
                    playerX = true;
                }
            }
            else {
                level[to.y][to.x] = level[from.y][from.x];
                if (playerX && level[from.y][from.x] == 'P'){
                    level[from.y][from.x] = 'X';
                    playerX = false;
                }
                else {level[from.y][from.x] = ' ';}
            }
            return true;
        }
    }
}

