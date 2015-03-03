package javaproject;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Level {
    int levelID;
    int gameID;
    Character[][] level;

    public Level(int levelID, int gameID, Character[][] level){
        this.levelID = levelID;
        this.gameID = gameID;
        this.level = level;
    }

    public int getLevelID() {
        return levelID;
    }

    public int getGameID() {
        return gameID;
    }

    public Character[][] getLevel() {
        return level;
    }
}

