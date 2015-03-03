package javaproject;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Level {
    int levelID;
    int gameID;
    String level;

    public Level(int levelID, int gameID, String level){
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

    public String getLevel() {
        return level;
    }
}

