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
    private StateBasedGame game;
    private ArrayList<Level> levels;
    private LevelRenderer lrend;
    public int gamestate = 0;  // 0 intro, 1 game, 2 finish, -1 gameover(fail)

    Game(int i){
        this.id = i;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        lrend = new LevelRenderer(container.getGraphics(),container.getScreenHeight(),container.getScreenWidth());
        levels = DBFunctions.getLevels(1);
        Input i = container.getInput();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        lrend.Render(levels.get(0), g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }


    @Override
    public int getID() {
        return this.id;
    }
}
