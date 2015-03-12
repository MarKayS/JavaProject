package ui;


import core.Level;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import persistance.DBFunctions;

import java.math.*;
import java.util.ArrayList;
import java.util.Random;


public class Game extends BasicGameState {
    private int id;
    private Graphics g;
    private Input i;
    private StateBasedGame game;
    private ArrayList<Level> levels;
    private LevelRenderer lrend;
    static boolean faceRight = true;
    public int gamestate = 0;  // 0 intro, 1 game, 2 finish, -1 gameover(fail)
    int levelNumber, gameNumber;

    Game(int i, int l, int g){
        this.id = i;
        this.levelNumber = l;
        this.gameNumber = g;
    }

    private Level getLevel(int gameNumber, int levelNumber){
        return null;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;

        lrend = new LevelRenderer(container.getGraphics(),container.getScreenHeight(),container.getScreenWidth());
        levels = DBFunctions.getLevels(gameNumber);
        i = container.getInput();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        lrend.Render(levels.get(levelNumber), g);
        if(gamestate == 1) {
            g.drawString("U WON", container.getScreenWidth()/2, container.getScreenHeight()/2);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(levels.get(levelNumber).checkWin()){
            gamestate = 1;
        }
        if (i.isKeyPressed(Input.KEY_UP)||i.isKeyPressed(Input.KEY_W)){
            levels.get(levelNumber).move(levels.get(levelNumber).locatePlayer(),'w');
        }
        else if (i.isKeyPressed(Input.KEY_DOWN)||i.isKeyPressed(Input.KEY_S)){
            levels.get(levelNumber).move(levels.get(levelNumber).locatePlayer(),'s');
        }
        else if (i.isKeyPressed(Input.KEY_LEFT)||i.isKeyPressed(Input.KEY_A)){
            faceRight = false;
            levels.get(levelNumber).move(levels.get(levelNumber).locatePlayer(),'a');
        }
        else if (i.isKeyPressed(Input.KEY_RIGHT)||i.isKeyPressed(Input.KEY_D)){
            faceRight = true;
            levels.get(levelNumber).move(levels.get(levelNumber).locatePlayer(),'d');
        }
    }


    @Override
    public int getID() {
        return this.id;
    }
}
